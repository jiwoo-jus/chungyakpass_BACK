package com.hanium.chungyakpassback.service.vertification;

import com.hanium.chungyakpassback.entity.apt.AptInfoAmount;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.standard.AreaLevel1;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.standard.AreaLevel1Repository;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class GeneralPrivateVerificationServiceImpl implements GeneralPrivateVerificationService {

    final HouseMemberRepository houseMemberRepository;
    final HouseRepository houseRepository;
    final UserBankbookRepository userBankbookRepository;
    final DepositArea depositArea; //예치금액지역구분
    final AreaLevel1Repository areaLevel1Repository;
    final UserRepository userRepository;
    final AptInfoRepository aptInfoRepository;//아파트 분양정보
    final HouseMemberChungyakRepository houseMemberChungyakRepository;//세대구성원_청약신청이력repository
    final AptInfoTargetRepository aptInfoTargetRepository;

    // 객체 생성
    //회원_청약통장 청약통장 = 회원_청약통장.builder().청약통장종류(청약통장종류.청약저축).build();
    UserBankbook userBankbook = UserBankbook.builder().bankbook(Bankbook.주택청약종합저축).deposit(500).validYn(Yn.y).joinDate(LocalDate.of(2021, 6, 28)).build();

    //세대 세대1 = 세대.builder().build();
    House house = House.builder().addressLevel1(AddressLevel1.서울).build();

    //세대구성원 구성원 = 세대구성원.builder().세대(세대1).세대주여부(여부.y).이름("이지은").생년월일(LocalDate.of(1998, 11, 28)).외국인여부(여부.y).장기복무군인여부(여부.n).build();
    HouseMember houseMember = HouseMember.builder().house(house).name("이지은").birthDate(LocalDate.of(1998, 11, 28)).foreignerYn(Yn.y).soldierYn(Yn.n).build();
    HouseMember houseMember1 = HouseMember.builder().house(house).name("김지호").birthDate(LocalDate.of(1998, 2, 12)).foreignerYn(Yn.n).soldierYn(Yn.n).build();


    User user = User.builder().houseMember(houseMember).email("kim41900@naver.com").password("kim41900").build();
    HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder().user(user).opponent(houseMember).relation(Relation.본인).build();
    //HouseMemberRelation houseMemberRelation1 = HouseMemberRelation.builder().user(user).opponent(houseMember1).relation(Relation.배우자).build();
    List<HouseMemberProperty> houseMemberPropertyList = new ArrayList<>();
    List<HouseMemberChungyak> houseMemberChungyakList = new ArrayList<>();


    // 아파트분양정보 분양정보 = 아파트분양정보.builder().주택유형(주택유형.민영).투기과열지구(여부.y).청약과열지역(여부.n).build();
    AptInfo aptInfo = AptInfo.builder().addressLevel1(AddressLevel1.제주).housingType(HousingType.국민).speculationOverheated(Yn.y).atrophyArea(Yn.y).subscriptionOverheated(Yn.n).publicHosingDistrict(Yn.n).publicRentalHousing(Yn.n).build();
    AptInfoAmount aptInfoAmount = AptInfoAmount.builder().aptInfo(aptInfo).housingType("046.9300A").build();
    AptInfoTarget aptInfoTarget = AptInfoTarget.builder().aptInfo(aptInfo).housingType("046.9300A").build();

    //주택유형(주택유형.민영).투기과열지구(여부.y).청약과열지역(여부.n).build();
    //HousingType housingType = HousingType.(HousingType.민영주택).
    //세대구성원_청약신청이력 신청이력 = 세대구성원_청약신청이력.builder().주택명("테스트").결과(결과.당첨).build();
    //HouseMemberApplicationDetails houseMemberApplicationDetails = HouseMemberApplicationDetails.builder().houseName("테스트").result(Result.당첨).build();

    String typeChange = aptInfoAmount.getHousingType().substring(0, aptInfoAmount.getHousingType().indexOf("."));
    int int_typeChange = Integer.parseInt(typeChange);
    int hasHouse = 0;
    LocalDate now = LocalDate.now();
    LocalDate parsedBirthDate = houseMember.getBirthDate();
    int americanAge = now.minusYears(parsedBirthDate.getYear()).getYear();

    @Override
    public boolean accountStatus() {
        if (aptInfoRepository.findById(2021000580).get().getHousingType().equals(HousingType.국민)) { // 주택유형이 국민일 경우 청약통장종류는 주택청약종합저축 or 청약저축이어야 true
            if (userBankbook.getBankbook().equals(Bankbook.주택청약종합저축) || userBankbook.getBankbook().equals(Bankbook.청약저축)) {
                return true;
            }
        }

        if (aptInfoRepository.findById(2021000580).get().getHousingType().equals(HousingType.민영)) {// 주택유형이 민영일 경우 청약통장종류는 주택청약종합저축 or 청약예금 or 청약부금이어야 true
            if (userBankbook.getBankbook().equals(Bankbook.주택청약종합저축) || userBankbook.getBankbook().equals(Bankbook.청약예금) || userBankbook.getBankbook().equals(Bankbook.청약부금)) {
                return true;
            }
        }

        return false; // 그 이외에는 false
    }


    @Override
    public int americanAgeCount() {
        // 생일이 지났는지 여부를 판단
        if (parsedBirthDate.plusYears(americanAge).isAfter(now)) {
            americanAge = americanAge - 1;
        }
        return americanAge;
    }


//    @Override
//    public boolean householderYn() {
//        if (houseMember.getHouseholderYn().equals(Yn.y)) {
//            return true; // 세대주여부 y 이면 true 출력
//        } else if (houseMember.getHouseholderYn().equals(Yn.n)) {
//            return false; // 세대주여부 n 이면 false 출력
//        }
//        return false;
//    }

    @Override
    public boolean surroundingArea() {//아파트 분양정보의 인근지역과 거주지의 인근지역이 같다면
        AddressLevel1 userAdressLevel1 = user.getHouseMember().getHouse().getAddressLevel1();
        AddressLevel1 aptAddressLevel1 = aptInfo.getAddressLevel1();

        AreaLevel1 userAreaLevel1 = areaLevel1Repository.findByAddressLevel1(userAdressLevel1);
        AreaLevel1 aptAreaLevel1 = areaLevel1Repository.findByAddressLevel1(aptAddressLevel1);

        if (userAdressLevel1.equals(aptAddressLevel1))
            return true;
        else return false;
    }

    @Override
    public boolean restrictedAreaYn() {
        if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y)) {
            return true; // 투기과열지구 or 청약과열지역이면 규제지역
        }
        return false; // 그 외에는 false
    }

    @Override
    public boolean termsOfPolicy() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        UserBankbook userBankbook = userBankbookRepository.findById(user.getId()).get(); // user_id(fk)를 통해서 해당하는 user의 통장 정보를 가져옴

        LocalDate now = LocalDate.now();
        LocalDate joinDate = userBankbook.getJoinDate();
        Period period = joinDate.until(now);
        int joinPeriod = period.getYears() * 12 + period.getMonths(); // 가입날짜를 받아와서 현재까지의 개월수를 계산

        System.out.println(joinPeriod);
        if (userBankbook.getValidYn().equals(Yn.y)) {
            if (joinPeriod >= 24 && (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y))) {
                return true;
            }
            if (joinPeriod >= 1 && (aptInfo.getSpeculationOverheated().equals(Yn.n) && aptInfo.getSubscriptionOverheated().equals(Yn.n) && aptInfo.getAtrophyArea().equals(Yn.y))) { // 위축지역, 1개월 이상 충족 (수도권 여부 조건 추가해야됨)
                return true;
            }
            if (joinPeriod >= 12 && (aptInfo.getSpeculationOverheated().equals(Yn.n) && aptInfo.getSubscriptionOverheated().equals(Yn.n) && aptInfo.getAtrophyArea().equals(Yn.n)))

                return false;
        }
        return false;
    }

    public int houseTypeConverter() { // . 기준으로 주택형 자른후 면적 비교를 위해서 int 형으로 형변환
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType("064.9300A");

        String housingTypeChange = aptInfoTarget.getHousingType().substring(0, aptInfoTarget.getHousingType().indexOf("."));
        int intTypeChange = Integer.parseInt(housingTypeChange);

        return intTypeChange;
    }

    // 예치금액충족 여부
        public boolean depositAmount() {
            User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
            UserBankbook userBankbook = userBankbookRepository.findByUserId(user.getId()).get(); // user_id(fk)를 통해서 해당하는 user의 통장 정보를 가져옴

            int housingTypeChange = houseTypeConverter();
            System.out.println(user.getHouseMember().getHouse().getAddressLevel1());
            System.out.println(housingTypeChange);
            System.out.println(userBankbook.getDeposit());

            if ((user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.서울) || user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.부산))) { // 지역_레벨1이 서울 or 부산일 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 3000000) {
                    return true;
                }
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 6000000) {
                    return true;
                }
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 10000000) {
                    return true;
                }
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 15000000) {
                    return true;
                }
            } else if ((user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.인천) || user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.대구) || user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.울산) || user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.대전) || user.getHouseMember().getHouse().getAddressLevel1().equals(AddressLevel1.광주))) { // 지역_레벨1이 기타광역시에 해당할 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 2500000) {
                    return true;
                }
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 4000000) {
                    return true;
                }
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 7000000) {
                    return true;
                }
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 10000000) {
                    return true;
                }

            } else { // 지역_레벨1이 기타시군일 경우
                if (housingTypeChange <= 85 && userBankbook.getDeposit() >= 2000000) {
                    return true;
                }
                if (housingTypeChange <= 102 && userBankbook.getDeposit() >= 3000000) {
                    return true;
                }
                if (housingTypeChange <= 135 && userBankbook.getDeposit() >= 4000000) {
                    return true;
                }
                if (housingTypeChange > 135 && userBankbook.getDeposit() >= 5000000) {
                    return true;
                }
            }
            return false;
        }


    @Override
    public boolean specialNote() {
        return (int_typeChange > 85 && aptInfo.getPublicRentalHousing().equals(Yn.y)) || (aptInfo.getHousingType().equals(HousingType.민영) && aptInfo.getPublicHosingDistrict().equals(Yn.y) && aptInfo.getAddressLevel1().equals(areaLevel1Repository.findAllByMetropolitanArea(Yn.y)));//진행
//1순위
    }

    @Override
    public Integer hasHouseYn() {
        HouseMemberProperty houseMemberproperty =
                HouseMemberProperty.builder()
                        .houseMember(houseMember)
                        .property(Property.건물)
                        .saleRightYn(Yn.y)
                        .residentialBuildingYn(Yn.y)
                        .residentialBuilding(ResidentialBuilding.오피스텔)
                        .nonResidentialBuilding(NonResidentialBuilding.건물)
                        .acquisitionDate(LocalDate.of(1998, 11, 28))
                        .dispositionDate(LocalDate.of(1998, 11, 28))
                        .exclusiveArea(21)
                        .amount(123400)
                        .taxBaseDate(LocalDate.of(1998, 11, 28))
                        .build();
        houseMemberPropertyList.add(houseMemberproperty);


        HouseMemberProperty houseMemberproperty1 =
                HouseMemberProperty.builder()
                        .houseMember(houseMember1)
                        .property(Property.건물)
                        .saleRightYn(Yn.y)
                        .residentialBuildingYn(Yn.y)
                        .residentialBuilding(ResidentialBuilding.단독주택)
                        .nonResidentialBuilding(NonResidentialBuilding.건물)
                        .acquisitionDate(LocalDate.of(1998, 11, 28))
                        .dispositionDate(LocalDate.of(1998, 11, 28))
                        .exclusiveArea(21)
                        .amount(123400)
                        .taxBaseDate(LocalDate.of(1998, 11, 28))
                        .build();
        houseMemberPropertyList.add(houseMemberproperty1);

        for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {

            if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                if (houseMemberProperty.getExclusiveArea() <= 20 || houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔) || (houseMemberRelation.getRelation().equals(Relation.부모) && americanAge >= 60)) {
                } else {
                    hasHouse = hasHouse + 1;
                }
            }

        }
        return hasHouse;
    }


//    //    @Override
//    public boolean 규제지역로직() {
//        if (규제지역여부()) { // 규제지역여부가 true 일 경우 세대주여부 메소드를 실행
//            System.out.println("세대주여부 : " + 세대주여부());
//        }
//    }


    //    public List<세대> findAll() {
//        return 세대repository.findAll();
//    }
//
    @Override
    public boolean winningHistory() {
        LocalDate now = LocalDate.now();
        HouseMemberChungyak houseMemberChungyak1 = HouseMemberChungyak.builder().houseMember(houseMember).winningDate(LocalDate.of(2020, 2, 12)).build();
        houseMemberChungyakList.add(houseMemberChungyak1);
        HouseMemberChungyak houseMemberChungyak2 = HouseMemberChungyak.builder().houseMember(houseMember1).winningDate(LocalDate.of(1998, 2, 12)).build();
        houseMemberChungyakList.add(houseMemberChungyak2);
        for (HouseMemberChungyak houseMemberChungyak : houseMemberChungyakList) {
            int periodYear = now.minusYears(houseMemberChungyak.getWinningDate().getYear()).getYear();
            if (periodYear <= 5) {
                return false;
            }
        }
        return true;
    }
}
