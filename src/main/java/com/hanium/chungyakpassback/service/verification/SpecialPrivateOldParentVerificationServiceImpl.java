package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpecialPrivateOldParentVerificationServiceImpl implements SpecialPrivateOldParentVerificationService {

    final AddressLevel1Repository addressLevel1Repository;
    final UserBankbookRepository userBankbookRepository;
    final HouseMemberRepository houseMemberRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final HouseMemberChungyakRepository houseMemberChungyakRepository;


    public int houseTypeConverter(AptInfoTarget aptInfoTarget) { // . 기준으로 주택형 자른후 면적 비교를 위해서 int 형으로 형변환
        String housingTypeChange = aptInfoTarget.getHousingType().substring(0, aptInfoTarget.getHousingType().indexOf("."));

        return Integer.parseInt(housingTypeChange);
    }

    public Long calcDate(LocalDate transferdate) { //주민등록표에 등재된 기간 구하기
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime departure = transferdate.atStartOfDay();

        Long days = Duration.between(departure, today).toDays();

        return days;
    }


    @Override
    public int calcAmericanAge(LocalDate birthday) { // 만나이계산
        LocalDate now = LocalDate.now();
        int americanAge = now.minusYears(birthday.getYear()).getYear();

        if (birthday.plusYears(americanAge).isAfter(now)) // 생일이 지났는지 여부를 판단
            americanAge = americanAge - 1;

        return americanAge;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget) { // 청약통장유형조건충족여부
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
    public boolean meetOldParentSupportMore3years(User user) { //3년이상노부모부양충족여부
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse());

        int oldParentSupportCount = 0;

        for (HouseMember houseMember : houseMemberListUser) {
            HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();

            if (user.getHouse() == houseMember.getHouse()) { // 신청자와 같은 세대인지 판단 후,
                if (calcAmericanAge(houseMember.getBirthDay()) >= 65 && ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부)))) { // 만 65세 이상의 직계존속(배우자의 직계존속)인지 판단 후,
                    if (calcDate(houseMember.getTransferDate()) >= 1095 && calcDate(user.getHouseMember().getTransferDate()) >= 1095) { //신청자와 부양자가 둘 다 3년 이상 등본에 등재한 경우
                        oldParentSupportCount++;
                    }
                }
            }
        }

        System.out.println("노부모부양수 : " + oldParentSupportCount);

        if (oldParentSupportCount >= 1) // 1명 이상의 노부모 부양을 할 경우 true
            return true;
        else // 그렇지 않으면 false
            return false;
    }

    @Override
    public boolean meetHomelessHouseholdMembers(User user) { //전세대원무주택세대구성원충족여부
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        int houseCount = 0;

        //배우자와 같은 세대이거나, 미혼일 경우
        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용일경우
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부)) && calcAmericanAge(houseMember.getBirthDay()) >= 60) //직계존속의 나이가 만60세일 경우
                            continue;
                        else if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) //주거용건물유형이 오피스텥일 경우
                            continue;
                        else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) //2018.12.11 이전에 취득한 분양권일 경우
                            continue;
                        else
                            houseCount++;
                    } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getExclusiveArea() <= 20) { //20제곱미터 이하의 분양권을 소유하고 있는 경우
                        flag++;
                        if (flag == 1) // 단, 2호 또는 2세대 이상의 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                            continue;
                        else
                            houseCount++;
                    }
                }
            }
        }

        // 배우자분리세대일 경우
        else {
            List<HouseMember> houseMemberListSpouse = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); //배우자분리세대일 경우, 배우자의 세대구성원 가져오기

            for (HouseMember houseMember : houseMemberListUser) { // 신청자의 세대 조회
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용일경우
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부)) && calcAmericanAge(houseMember.getBirthDay()) >= 60) //직계존속의 나이가 만60세일 경우
                            continue;
                        else if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔))
                            continue;
                        else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11")))
                            continue;
                        else
                            houseCount++;
                    } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getExclusiveArea() <= 20) { //20제곱미터 이하의 분양권을 소유하고 있는 경우
                        flag++;
                        if (flag == 1)
                            continue;
                        else
                            houseCount++;
                    }
                }
            }
            for (HouseMember houseMember : houseMemberListSpouse) { // 배우자 세대 조회
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용일경우
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모)) && calcAmericanAge(houseMember.getBirthDay()) >= 60) //직계존속의 나이가 만60세일 경우
                            continue;
                        else if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔))
                            continue;
                        else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) //2018.12.11 이전에 취득한 분양권일 경우
                            continue;
                        else
                            houseCount++;
                    } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getExclusiveArea() <= 20) { //20제곱미터 이하의 분양권을 소유하고 있는 경우
                        flag++;
                        if (flag == 1)
                            continue;
                        else
                            houseCount++;
                    }
                }
            }
        }

        System.out.println("주택수 :" + houseCount);

        if (houseCount == 0) // 주택수가 0일 경우 무주택세대구성원으로 판별
            return true;
        else // 아닐 경우 유주택세대구성원으로 판별, 탈락
            return false;
    }

    @Override
    public boolean isHouseholder(User user) { // 세대주여부
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
    public boolean isRestrictedArea(AptInfo aptInfo) { // 규제지역여부
        if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y))
            return true;
        return false;
    }

    @Override
    public boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo) { //가입기간충족여부
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
    public boolean meetDeposit(User user, AptInfoTarget aptInfoTarget) { // 예치금액충족여부확인
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
        } else if ((user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.인천) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.대구) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.울산) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.대전) || user.getHouseMember().getHouse().getAddressLevel1().equals(com.hanium.chungyakpassback.enumtype.AddressLevel1.광주))) { // 지역_레벨1이 기타광역시에 해당할 경우
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
