package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class GeneralPrivateVerificationServiceImpl implements GeneralPrivateVerificationService {

    final HouseMemberRepository houseMemberRepository;
    final HouseRepository houseRepository;
    final UserBankbookRepository userBankbookRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final UserRepository userRepository;
    final AptInfoRepository aptInfoRepository;
    final HouseMemberChungyakRepository houseMemberChungyakRepository;
    final AptInfoTargetRepository aptInfoTargetRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;



    public int houseTypeConverter(AptInfoTarget aptInfoTarget) { // . 기준으로 주택형 자른후 면적 비교를 위해서 int 형으로 형변환
        String housingTypeChange = aptInfoTarget.getHousingType().substring(0, aptInfoTarget.getHousingType().indexOf("."));

        return Integer.parseInt(housingTypeChange);
    }


    @Override
    public int calcAmericanAge(LocalDate birthday) {
        LocalDate now = LocalDate.now();
        int americanAge = now.minusYears(birthday.getYear()).getYear();

        if (birthday.plusYears(americanAge).isAfter(now)) // 생일이 지났는지 여부를 판단
            americanAge = americanAge - 1;

        return americanAge;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        int housingTypeChange = houseTypeConverter(aptInfoTarget); // 주택형변환 메소드 호출

        if (aptInfo.getHousingType().equals(HousingType.국민))// 주택유형이 국민일 경우 청약통장종류는 주택청약종합저축 or 청약저축이어야 true
            if (userBankbook.getBankbook().equals(Bankbook.주택청약종합저축) || (userBankbook.getBankbook().equals(Bankbook.청약저축)))
                return true;
        if (aptInfo.getHousingType().equals(HousingType.민영)) // 주택유형이 민영일 경우 청약통장종류는 주택청약종합저축 or 청약예금 or 청약부금이어야 true
            if (userBankbook.getBankbook().equals(Bankbook.주택청약종합저축) || userBankbook.getBankbook().equals(Bankbook.청약예금) || userBankbook.getBankbook().equals(Bankbook.청약부금) && (housingTypeChange <= 85))
                return true;
        return false;
    }


    @Override
    public boolean isHouseholder(User user) {
        if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId()))
            return true;

        return false;
    }


    @Override
    public boolean meetLivingInSurroundArea(User user, AptInfo aptInfo) {//아파트 분양정보의 인근지역과 거주지의 인근지역이 같다면
        AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));

        if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea())
            return true;
        return false;
    }


    @Override
    public boolean isRestrictedArea(AptInfo aptInfo) {
        if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y))
            return true;
        return false;
    }


    @Override
    public boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        LocalDate joinDate = userBankbook.getJoinDate();
        LocalDate now = LocalDate.now();
        Period period = joinDate.until(now);
        int joinPeriod = period.getYears() * 12 + period.getMonths(); // 가입날짜를 받아와서 현재까지의 개월수를 계산

        if (userBankbook.getValidYn().equals(Yn.y)) {
            if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y))
                if (joinPeriod >= 24)
                    return true;
            else if (aptInfo.getAtrophyArea().equals(Yn.y))
                if (joinPeriod >= 1)
                    return true;
            else if (addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).get().getMetropolitanAreaYn().equals(Yn.y))
                    if (joinPeriod >= 12)
                        return true;
            else if (joinPeriod >= 6)
                return true;
            }
        return false;
    }




    // 예치금액충족 여부
        public boolean meetDeposit(User user, AptInfoTarget aptInfoTarget) {
            Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
            if (optUserBankbook.isEmpty())
                throw new RuntimeException("등록된 청약통장이 없습니다.");
            UserBankbook userBankbook = optUserBankbook.get();

            int housingTypeChange = houseTypeConverter(aptInfoTarget);

            if ((user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.서울) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.부산))) { // 지역_레벨1이 서울 or 부산일 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 3000000)
                    return true;
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 6000000)
                    return true;
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 10000000)
                    return true;
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 15000000)
                    return true;
            }
            else if ((user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.인천) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.대구) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.울산) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.대전) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.광주))) { // 지역_레벨1이 기타광역시에 해당할 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 2500000)
                    return true;
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 4000000)
                    return true;
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 7000000)
                    return true;
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 10000000)
                    return true;

            } else { // 지역_레벨1이 기타시군일 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 2000000)
                    return true;
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 3000000)
                    return true;
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 4000000)
                    return true;
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 5000000)
                    return true;
            }
            return false;
        }


    @Override
    public boolean isPriorityApt(AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        if ((houseTypeConverter(aptInfoTarget) > 85 && aptInfo.getPublicRentalHousing().equals(Yn.y)))
            return true;
        else if (aptInfo.getHousingType().equals(HousingType.민영) && aptInfo.getPublicHosingDistrict().equals(Yn.y) && addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).equals(addressLevel1Repository.findAllByMetropolitanAreaYn(Yn.y)))
            return true;
        return false;
    }


    @Override
    public boolean meetHouseHavingLessThan2Apt(User user) {
        List<HouseMember> houseMemberList = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse());

        int houseCount = 0;
        for (HouseMember houseMember : houseMemberList) {
            List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

            int flag = 0;
            for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                    HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                    if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모)) && calcAmericanAge(houseMember.getBirthDay()) >= 60)
                        continue;
                    else if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔))
                        continue;
                    else if (houseMemberProperty.getExclusiveArea() <= 60){
                        flag++;
                        if (flag == 0)
                            houseCount++;
                        else
                            continue;
                    }
                    else if (houseMemberProperty.getSaleRightYn().equals(Yn.y)&&houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11")))
                        continue;
                    else
                        houseCount++;
                }
            }
        }
        if (houseCount < 2)
            return true;
        else return false;
    }

    @Override
    public boolean meetAllHouseMemberNotWinningIn5years(User user) {
        HouseMember houseMember = user.getHouseMember();
        List<HouseMemberChungyak> houseMemberChungyakList = houseMemberChungyakRepository.findAllByHouseMember(houseMember);
        LocalDate now = LocalDate.now();
        int periodYear = 0;

        for (HouseMemberChungyak houseMemberChungyak : houseMemberChungyakList) {
            periodYear = now.minusYears(houseMemberChungyak.getWinningDate().getYear()).getYear();
            if (periodYear <= 5)
                return false;
        }
        return true;
    }
}
