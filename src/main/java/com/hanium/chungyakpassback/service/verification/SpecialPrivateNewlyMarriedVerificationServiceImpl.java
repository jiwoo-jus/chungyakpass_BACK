package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.entity.standard.PriorityDeposit;
import com.hanium.chungyakpassback.entity.standard.PriorityJoinPeriod;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.*;
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
public class SpecialPrivateNewlyMarriedVerificationServiceImpl implements SpecialPrivateNewlyMarriedVerificationService {

    final UserBankbookRepository userBankbookRepository;
    final BankbookRepository bankbookRepository;
    final HouseMemberRepository houseMemberRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final HouseMemberChungyakRepository houseMemberChungyakRepository;
    final IncomeRepository incomeRepository;
    final PriorityDepositRepository priorityDepositRepository;
    final PriorityJoinPeriodRepository priorityJoinPeriodRepository;
    final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;


    public int houseTypeConverter(AptInfoTarget aptInfoTarget) { // 주택형 변환 메소드
        // . 기준으로 주택형 자른후 면적 비교를 위해서 int 형으로 형변환
        String housingTypeChange = aptInfoTarget.getHousingType().substring(0, aptInfoTarget.getHousingType().indexOf("."));

        return Integer.parseInt(housingTypeChange);
    }


    public Long calcDate(LocalDate transferdate) { //신혼 기간 구하기
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime departure = transferdate.atStartOfDay();

        Long days = Duration.between(departure, today).toDays();

        return days;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int calcAmericanAge(LocalDate birthday) { //만나이계산 메소드
        if (birthday == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_BIRTHDAY); //생일이 입력되지 않은 경우 경고문을 띄워줌.
        }

        LocalDate now = LocalDate.now();
        int americanAge = now.minusYears(birthday.getYear()).getYear();

        if (birthday.plusYears(americanAge).isAfter(now)) // 생일이 지났는지 여부를 판단
            americanAge = americanAge - 1;

        return americanAge;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget) { // 청약통장유형조건충족여부 메소드
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty()) { // 만약 사용자의 청약통장이 입력되지 않았다면 경고문을 띄워줌
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        } else {
            // 청약통장 기준 정보 테이블에 담겨 있는 데이터와 사용자의 청약통장 정보랑 일치하는 데이터를 가져옴
            Optional<com.hanium.chungyakpassback.entity.standard.Bankbook> stdBankbook = bankbookRepository.findByBankbook(optUserBankbook.get().getBankbook());
            int housingTypeChange = houseTypeConverter(aptInfoTarget); // 주택형변환 메소드 호출
            if (stdBankbook.get().getPrivateHousingSupplyIsPossible().equals(Yn.y)) { // 사용자의 청약통장이 민영주택을 공급받을 수 있는 통장이라면,
                if (stdBankbook.get().getBankbook().equals(Bankbook.청약부금)) { // 사용자의 청약통장 종류가 청약 부금인지 확인하고, (주택청약종합저축, 청약예금은 이 부분 확인 안 함)
                    if (housingTypeChange <= stdBankbook.get().getRestrictionSaleArea()) { // 사용자가 선택한 아파트의 주택형 <= 분양면적제한 85제곱미터일 경우, true
                        return true;
                    } else if (housingTypeChange > stdBankbook.get().getRestrictionSaleArea()) {
                        throw new CustomException(ErrorCode.BAD_REQUEST_OVER_AREA_BANKBOOK); //청약부금일 경우, 면적이 85제곱미터를 초과할 경우 경고문을 띄워줌.
                    }
                    return false;
                }
                return true;
            } else {
                throw new CustomException(ErrorCode.BAD_REQUEST_BANKBOOK); // 사용자의 청약통장이 민영주택을 공급받을 수 있는 통장이 아닌 경우(청약저축) 경고문을 띄워줌.
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetMonthlyAverageIncomePriority(User user) { //월평균소득기준충족여부_우선공급
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAll();

        Optional<Income> noneDualIncome = incomeRepository.findBySpecialSupplyAndSupplyAndDualIncomeAndApplicationPublicHousingSpecialLaws(SpecialSupply.신혼부부, Supply.우선공급, Yn.n, Yn.n);
        Optional<Income> dualIncome = incomeRepository.findBySpecialSupplyAndSupplyAndDualIncomeAndApplicationPublicHousingSpecialLaws(SpecialSupply.신혼부부, Supply.우선공급, Yn.y, Yn.n);

        int houseMemberCount = 0; //세대구성원수
        int sumIncome = 0; // 소득합산

        for (HouseMember houseMember : houseMemberListUser) {
            houseMemberCount++;
            if (calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                sumIncome += houseMember.getIncome();
        }

        System.out.println("세대구성원 수 : " + houseMemberCount);
        System.out.println("소득합산 : " + sumIncome);

        // 배우자가 세대구성원에 등록되어 있지 않을 경우 경고문을 띄워줌.
        if (user.getSpouseHouseMember() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_SPOUSE);
        }

        //user나 배우자 둘 중에 한 명이 소득이 없을 경우(외벌이)
        if (user.getHouseMember().getIncome() == null || user.getSpouseHouseMember().getIncome() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_INCOME); //신청자나 배우자 둘 중에 한명이라도 income에 null 값이 들어올 경우 경고문을 띄워줌.
        }

        if (user.getHouseMember().getIncome() == 0 || user.getSpouseHouseMember().getIncome() == 0) {
            if (houseMemberCount <= 3) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow()) {
                    return true;
                }
            } else if (houseMemberCount <= 4) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome4peopleLessBelow()) {
                    return true;
                }
            } else if (houseMemberCount <= 5) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome5peopleLessBelow()) {
                    return true;
                }
            }
        }
        // 맞벌이일 경우
        else {
            if (!(user.getHouseMember().getIncome() > noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow() || user.getSpouseHouseMember().getIncome() > noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow())) { // 맞벌이일 경우, 부부 중 1인의 소득이 월평균소득의 100%를 초과하면 안됨.
                if (houseMemberCount <= 3) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome3peopleLessBelow()) {
                        return true;
                    }
                } else if (houseMemberCount <= 4) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome4peopleLessBelow()) {
                        return true;
                    }
                } else if (houseMemberCount <= 5) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome5peopleLessBelow()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetMonthlyAverageIncomeGeneral(User user) { //월평균소득기준충족여부_일반공급
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAll();

        Optional<Income> noneDualIncome = incomeRepository.findBySpecialSupplyAndSupplyAndDualIncomeAndApplicationPublicHousingSpecialLaws(SpecialSupply.신혼부부, Supply.일반공급, Yn.n, Yn.n);
        Optional<Income> dualIncome = incomeRepository.findBySpecialSupplyAndSupplyAndDualIncomeAndApplicationPublicHousingSpecialLaws(SpecialSupply.신혼부부, Supply.일반공급, Yn.y, Yn.n);

        int houseMemberCount = 0; //세대구성원수
        int sumIncome = 0; // 소득합산

        for (HouseMember houseMember : houseMemberListUser) {
            houseMemberCount++;
            if (calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                sumIncome += houseMember.getIncome();
        }

        System.out.println("세대구성원 수 : " + houseMemberCount);
        System.out.println("소득합산 : " + sumIncome);

        // 배우자가 세대구성원에 등록되어 있지 않을 경우 경고문을 띄워줌.
        if (user.getSpouseHouseMember() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_SPOUSE);
        }

        //user나 배우자 둘 중에 한 명이 소득이 없을 경우(외벌이)
        if (user.getHouseMember().getIncome() == null || user.getSpouseHouseMember().getIncome() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_INCOME); //신청자나 배우자 둘 중에 한명이라도 income에 null 값이 들어올 경우 경고문을 띄워줌.
        }
        if (user.getHouseMember().getIncome() == 0 || user.getSpouseHouseMember().getIncome() == 0) {
            if (houseMemberCount <= 3) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow() && sumIncome > noneDualIncome.get().getAverageMonthlyIncome3peopleLessExcess()) {
                    return true;
                }
            } else if (houseMemberCount <= 4) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome4peopleLessBelow() && sumIncome > noneDualIncome.get().getAverageMonthlyIncome4peopleLessExcess()) {
                    return true;
                }
            } else if (houseMemberCount <= 5) {
                if (sumIncome <= noneDualIncome.get().getAverageMonthlyIncome5peopleLessBelow() && sumIncome > noneDualIncome.get().getAverageMonthlyIncome5peopleLessExcess()) {
                    return true;
                }
            }
        }
        // 맞벌이일 경우
        else {
            if (!(user.getHouseMember().getIncome() > noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow() || user.getSpouseHouseMember().getIncome() > noneDualIncome.get().getAverageMonthlyIncome3peopleLessBelow())) { // 맞벌이일 경우, 부부 중 1인의 소득이 월평균소득의 140%를 초과하면 안됨.
                if (houseMemberCount <= 3) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome3peopleLessBelow() && sumIncome > dualIncome.get().getAverageMonthlyIncome3peopleLessExcess()) {
                        return true;
                    }
                } else if (houseMemberCount <= 4) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome4peopleLessBelow() && sumIncome > dualIncome.get().getAverageMonthlyIncome4peopleLessExcess()) {
                        return true;
                    }
                } else if (houseMemberCount <= 5) {
                    if (sumIncome <= dualIncome.get().getAverageMonthlyIncome5peopleLessBelow() && sumIncome > dualIncome.get().getAverageMonthlyIncome5peopleLessExcess()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetMarriagePeriodIn7years(User user) {
        if (user.getHouseMember().getMarriageDate() == null) { //혼인신고일이 null일 경우 경고문을 띄워줌
            throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);
        } else if (calcDate(user.getHouseMember().getMarriageDate()) < 2555) { //신청자의 혼인기간이 7년 이내일 경우 true
            return true;
        }
        return false; //그렇지 않을 경우 false
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean hasMinorChildren(User user) {
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();

                // 만19세 미만의 미성년 자녀가 있거나, 태아가 있을 경우 1순위
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반) && calcAmericanAge(houseMember.getBirthDay()) < 19) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_태아))
                    return true;
            }
        }

        // 배우자 분리세대일 경우
        else {
            List<HouseMember> houseMemberListSpouse = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse());

            for (HouseMember houseMember : houseMemberListUser) {
                HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();

                // 만19세 미만의 미성년 자녀가 있거나, 태아가 있을 경우 1순위
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반) && calcAmericanAge(houseMember.getBirthDay()) < 19) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_태아))
                    return true;
            }

            for (HouseMember houseMember : houseMemberListSpouse) {
                HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();

                // 만19세 미만의 미성년 자녀가 있거나, 태아가 있을 경우 1순위
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반) && calcAmericanAge(houseMember.getBirthDay()) < 19) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_태아))
                    return true;
            }
        }
        return false; //그렇지 않으면, 2순위
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean secondChungyak(User user) {
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (user.getHouseMember().getMarriageDate().isBefore(houseMemberProperty.getDispositionDate())) //혼인신고일 이후에 처분일 이력이 있고,
                        if (houseMemberProperty.getDispositionDate().isBefore(LocalDate.of(2018, 12, 10))) // 2018년 12월 10일 이전에 처분했을 경우,
                            return true; // true(2순위 신청만 가능)
                }
            }
        }
        // 배우자 분리세대일 경우
        else {
            List<HouseMember> houseMemberListSpouse = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); //배우자분리세대일 경우, 배우자의 세대구성원 가져오기

            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (user.getHouseMember().getMarriageDate().isBefore(houseMemberProperty.getDispositionDate())) //혼인신고일 이후에 처분일 이력이 있고,
                        if (houseMemberProperty.getDispositionDate().isBefore(LocalDate.of(2018, 12, 10))) // 2018년 12월 10일 이전에 처분했을 경우,
                            return true; // true(2순위 신청만 가능)
                }
            }

            for (HouseMember houseMember : houseMemberListSpouse) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (user.getHouseMember().getMarriageDate().isBefore(houseMemberProperty.getDispositionDate())) //혼인신고일 이후에 처분일 이력이 있고,
                        if (houseMemberProperty.getDispositionDate().isBefore(LocalDate.of(2018, 12, 10))) // 2018년 12월 10일 이전에 처분했을 경우,
                            return true; // true(2순위 신청만 가능)
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetHomelessHouseholdMembers(User user) { //전세대원무주택세대구성원충족여부
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        int houseCount = 0;

        //배우자와 같은 세대이거나, 미혼일 경우
        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                int specialCase = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                            if (calcAmericanAge(houseMember.getBirthDay()) >= 60) {
                                specialCase++;
                                continue;
                            }
                        }
                        if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { //주거용건물유형이 오피스텥일 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { //2018.12.11 이전에 취득한 분양권일 경우
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 60) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                                flag++;
                                if (specialCase <= 0)
                                    continue;
                                else
                                    houseCount = flag;
                                if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                                    continue;
                                else
                                    houseCount = flag;
                            }
                        }
                        houseCount++;
                    }
                }
            }
        }
        //배우자 분리세대일 경우
        else {
            List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse());

            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                int specialCase = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                            if (calcAmericanAge(houseMember.getBirthDay()) >= 60) {
                                specialCase++;
                                continue;
                            }
                        }
                        if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { //주거용건물유형이 오피스텥일 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { //2018.12.11 이전에 취득한 분양권일 경우
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 60) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                                flag++;
                                if (specialCase <= 0)
                                    continue;
                                else
                                    houseCount = flag;
                                if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                                    continue;
                                else
                                    houseCount = flag;
                            }
                        }
                        houseCount++;
                    }
                }
            }
            for (HouseMember houseMember : spouseHouseMemberList) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                int specialCase = 0;
                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) {
                            if (calcAmericanAge(houseMember.getBirthDay()) >= 60) {
                                specialCase++;
                                continue;
                            }
                        }
                        if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { //주거용건물유형이 오피스텥일 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { //2018.12.11 이전에 취득한 분양권일 경우
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 60) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                                flag++;
                                if (specialCase <= 0)
                                    continue;
                                else
                                    houseCount = flag;
                                if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                                    continue;
                                else
                                    houseCount = flag;
                            }
                        }
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
    @Transactional(rollbackFor = Exception.class)
    public boolean isHouseholder(User user) { // 세대주여부 메소드
        if (user.getHouse().getHouseHolder() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_HOLDER); //세대주 지정이 안되어있을 경우 경고를 띄움.
        } else if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) { // 사용자의 세대의 세대주 id가 사용자의 세대구성원id와 같으면 true
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetLivingInSurroundArea(User user, AptInfo aptInfo) { //인근지역거주조건충족여부 메소드
        if (user.getHouseMember() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_MEMBER); // 세대구성원->세대를 통해서 주소를 user의 지역_레벨1을 가져오는 것이기 때문에 user의 세대구성원이 비어있으면 안됨.
        }

        com.hanium.chungyakpassback.entity.standard.AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        com.hanium.chungyakpassback.entity.standard.AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));

        if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea()) { // 아파트 분양정보의 인근지역과 거주지의 인근지역이 같다면 true
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isRestrictedArea(AptInfo aptInfo) { // 규제지역여부 메소드
        if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y)) // 사용자가 선택한 아파트분양정보가 투기과열지구 또는 청약과열지역에 해당하는 경우 true
            return true;
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo) { //가입기간충족여부확인
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        LocalDate joinDate = userBankbook.getJoinDate();
        LocalDate now = LocalDate.now();
        Period period = joinDate.until(now);
        int joinPeriod = period.getYears() * 12 + period.getMonths(); // 가입날짜를 받아와서 현재까지의 개월수를 계산

        List<PriorityJoinPeriod> priorityJoinPeriodList = priorityJoinPeriodRepository.findAll();

        for (PriorityJoinPeriod priorityJoinPeriod : priorityJoinPeriodList) {
            if (priorityJoinPeriod.getSupply().equals(Supply.특별공급) && priorityJoinPeriod.getSpecialSupply().equals(SpecialSupply.신혼부부)) {
                if (priorityJoinPeriod.getSpeculationOverheated().equals(aptInfo.getSpeculationOverheated()) && priorityJoinPeriod.getSubscriptionOverheated().equals(aptInfo.getSubscriptionOverheated()) && priorityJoinPeriod.getAtrophyArea().equals(aptInfo.getAtrophyArea()) && priorityJoinPeriod.getMetropolitanAreaYn().equals(addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).get().getMetropolitanAreaYn())) {
                    if (joinPeriod >= priorityJoinPeriod.getSubscriptionPeriod())
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetDeposit(User user, AptInfoTarget aptInfoTarget) { // 예치금액충족여부확인드 메소드
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user); // 사용자의 청약통장정보를 가져옴
        if (optUserBankbook.isEmpty()) // 만약 사용자의 청약통장이 입력되지 않았다면 경고문을 띄워줌
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        int housingTypeChange = houseTypeConverter(aptInfoTarget); // houseTypeConverter 메소드를 통해 변환한 주택형을 housingTypeChange 변수에 담음
        List<PriorityDeposit> priorityDepositList = priorityDepositRepository.findAll(); // 납입금의 기준정보를 List로 가져옴

        for (PriorityDeposit priorityDeposit : priorityDepositList) { // 반복문을 통해 납입금 기준 정보 List를 돌면서,
            if (priorityDeposit.getDepositArea().equals(user.getHouse().getAddressLevel1().getDepositArea())) { // 기준정보의 예치금액지역구분과 사용자의 지역_레벨1의 지역이 동일하다면,
                if (priorityDeposit.getAreaOver() < housingTypeChange && priorityDeposit.getAreaLessOrEqual() >= housingTypeChange && userBankbook.getDeposit() >= priorityDeposit.getDeposit()) { // 기준정보의 면적_초과 < housingTypeChange 이고, 기준정보의 면적_이하 >= housingTypeChange 이고, 사용자의 청약통장 예치금액 >= 기준정보의 예치금액 조건들을 충족한다면 true
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetAllHouseMemberRewinningRestriction(User user) { //전세대원재당첨제한여부
        LocalDate now = LocalDate.now(); //현재 날짜 가져오기

        List<HouseMemberChungyak> houseMemberListUser = houseMemberChungyakRepository.findAllByHouseMember(user.getHouseMember());

        //배우자와 같은 세대이거나, 미혼일 경우
        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMemberChungyak houseMemberChungyak : houseMemberListUser) {
                List<HouseMemberChungyakRestriction> houseMemberChungyakRestrictionList = houseMemberChungyakRestrictionRepository.findAllByHouseMemberChungyak(houseMemberChungyak);

                for (HouseMemberChungyakRestriction houseMemberChungyakRestriction : houseMemberChungyakRestrictionList) {
                    if (!(houseMemberChungyakRestriction.getReWinningRestrictedDate() == null)) { // 재당첨제한 날짜가 있는 세대구성원만 조회
                        if (houseMemberChungyakRestriction.getReWinningRestrictedDate().isAfter(now)) { // 현재 날짜와 비교하여 재당첨제한 기간이 끝났는지 판단
                            return false;
                        }
                    }
                }
            }
        }
        //배우자 분리세대일 경우
        else {
            List<HouseMemberChungyak> spouseHouseMemberListUser = houseMemberChungyakRepository.findAllByHouseMember(user.getSpouseHouseMember());

            for (HouseMemberChungyak houseMemberChungyak : houseMemberListUser) {
                List<HouseMemberChungyakRestriction> houseMemberChungyakRestrictionList = houseMemberChungyakRestrictionRepository.findAllByHouseMemberChungyak(houseMemberChungyak);

                for (HouseMemberChungyakRestriction houseMemberChungyakRestriction : houseMemberChungyakRestrictionList) {
                    if (!(houseMemberChungyakRestriction.getReWinningRestrictedDate() == null)) { // 재당첨제한 날짜가 있는 세대구성원만 조회
                        if (houseMemberChungyakRestriction.getReWinningRestrictedDate().isAfter(now)) { // 현재 날짜와 비교하여 재당첨제한 기간이 끝났는지 판단
                            return false;
                        }
                    }
                }
            }

            for (HouseMemberChungyak houseMemberChungyak : spouseHouseMemberListUser) {
                List<HouseMemberChungyakRestriction> houseMemberChungyakRestrictionList = houseMemberChungyakRestrictionRepository.findAllByHouseMemberChungyak(houseMemberChungyak);

                for (HouseMemberChungyakRestriction houseMemberChungyakRestriction : houseMemberChungyakRestrictionList) {
                    if (!(houseMemberChungyakRestriction.getReWinningRestrictedDate() == null)) { // 재당첨제한 날짜가 있는 세대구성원만 조회
                        if (houseMemberChungyakRestriction.getReWinningRestrictedDate().isAfter(now)) { // 현재 날짜와 비교하여 재당첨제한 기간이 끝났는지 판단
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
