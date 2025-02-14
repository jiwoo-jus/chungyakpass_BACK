package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongFirstLife;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.entity.standard.PriorityDeposit;
import com.hanium.chungyakpassback.entity.standard.PriorityJoinPeriod;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.verification.VerificationOfSpecialMinyeongFirstLifeRepository;
import com.hanium.chungyakpassback.repository.standard.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VerificationOfSpecialMinyeongFirstLifeServiceImpl implements VerificationOfSpecialMinyeongFirstLifeService {

    final UserBankbookRepository userBankbookRepository;
    final BankbookRepository bankbookRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final PriorityJoinPeriodRepository priorityJoinPeriodRepository;
    final PriorityDepositRepository priorityDepositRepository;
    final HouseMemberRepository houseMemberRepository;
    final HouseMemberChungyakRepository houseMemberChungyakRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;
    final IncomeRepository incomeRepository;
    final UserRepository userRepository;
    final AptInfoTargetRepository aptInfoTargetRepository;
    final AptInfoRepository aptInfoRepository;
    final VerificationOfSpecialMinyeongFirstLifeRepository verificationOfSpecialMinyeongFirstLifeRepository;

    @Override //특별생애최초민영조회
    public List<VerificationOfSpecialMinyeongFirstLifeResponseDto> readSpecialMinyeongFirstLifeVerifications() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<VerificationOfSpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeResponseDtos = new ArrayList<>();
        for (VerificationOfSpecialMinyeongFirstLife verificationOfSpecialMinyeongFirstLife : verificationOfSpecialMinyeongFirstLifeRepository.findAllByUser(user)) {
            VerificationOfSpecialMinyeongFirstLifeResponseDto specialMinyeongFirstLifeResponseDto = new VerificationOfSpecialMinyeongFirstLifeResponseDto(verificationOfSpecialMinyeongFirstLife);
            specialMinyeongFirstLifeResponseDtos.add(specialMinyeongFirstLifeResponseDto);
        }

        return specialMinyeongFirstLifeResponseDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VerificationOfSpecialMinyeongFirstLifeResponseDto createSpecialMinyeongFirstLifeVerification(VerificationOfSpecialMinyeongFirstLifeDto verificationOfSpecialMinyeongFirstLifeDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(verificationOfSpecialMinyeongFirstLifeDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingTypeAndAptInfo(verificationOfSpecialMinyeongFirstLifeDto.getHousingType(), aptInfo).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        Integer americanAge = calcAmericanAge(Optional.ofNullable(houseMember.getBirthDay()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BIRTHDAY)));
        boolean meetLivingInSurroundAreaTf = meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetRecipientTf = meetRecipient(user);
        boolean meetMonthlyAverageIncomePriorityTf = meetMonthlyAverageIncomePriority(user);
        boolean meetMonthlyAverageIncomeGeneralTf = meetMonthlyAverageIncomeGeneral(user);
        boolean meetHomelessHouseholdMembersTf = meetHomelessHouseholdMembers(user);
        boolean householderTf = isHouseholder(user);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = meetAllHouseMemberNotWinningIn5years(user);
        boolean meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestriction(user);
        boolean isRestrictedAreaTf = isRestrictedArea(aptInfo);
        boolean meetBankbookJoinPeriodTf = meetBankbookJoinPeriod(user, aptInfo);
        boolean meetDepositTf = meetDeposit(user, aptInfoTarget);

        VerificationOfSpecialMinyeongFirstLife verificationOfSpecialMinyeongFirstLife = new VerificationOfSpecialMinyeongFirstLife(user, americanAge, meetLivingInSurroundAreaTf, accountTf, meetRecipientTf, meetMonthlyAverageIncomePriorityTf, meetMonthlyAverageIncomeGeneralTf, meetHomelessHouseholdMembersTf, householderTf, meetAllHouseMemberNotWinningIn5yearsTf, meetAllHouseMemberRewinningRestrictionTf, meetBankbookJoinPeriodTf, meetDepositTf, isRestrictedAreaTf, aptInfo, aptInfoTarget);
        verificationOfSpecialMinyeongFirstLifeRepository.save(verificationOfSpecialMinyeongFirstLife);
        return new VerificationOfSpecialMinyeongFirstLifeResponseDto(verificationOfSpecialMinyeongFirstLife);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VerificationOfSpecialMinyeongFirstLifeResponseDto updateSpecialMinyeongFirstLifeVerification(Long id, VerificationOfSpecialMinyeongFirstLifeUpdateDto verificationOfSpecialMinyeongFirstLifeUpdateDto) {
        VerificationOfSpecialMinyeongFirstLife verificationOfSpecialMinyeongFirstLife = verificationOfSpecialMinyeongFirstLifeRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFICATION_RECORD_ID));
        verificationOfSpecialMinyeongFirstLife.setSibilingSupportYn(verificationOfSpecialMinyeongFirstLifeUpdateDto.getSibilingSupportYn());
        verificationOfSpecialMinyeongFirstLife.setTaxOver5yearsYn(verificationOfSpecialMinyeongFirstLifeUpdateDto.getTaxOver5yearsYn());
        verificationOfSpecialMinyeongFirstLife.setFirstRankHistoryYn(verificationOfSpecialMinyeongFirstLifeUpdateDto.getFirstRankHistoryYn());
        verificationOfSpecialMinyeongFirstLife.setRanking(verificationOfSpecialMinyeongFirstLifeUpdateDto.getRanking());
        verificationOfSpecialMinyeongFirstLifeRepository.save(verificationOfSpecialMinyeongFirstLife);
        return new VerificationOfSpecialMinyeongFirstLifeResponseDto(verificationOfSpecialMinyeongFirstLife);
    }

    public int houseTypeConverter(AptInfoTarget aptInfoTarget) { // . 기준으로 주택형 자른후 면적 비교를 위해서 int 형으로 형변환
        String housingTypeChange = aptInfoTarget.getHousingType().substring(0, aptInfoTarget.getHousingType().indexOf("."));

        return Integer.parseInt(housingTypeChange);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int calcAmericanAge(LocalDate birthday) {
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
    public boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        } else {
            Optional<com.hanium.chungyakpassback.entity.standard.Bankbook> stdBankbook = bankbookRepository.findByBankbook(optUserBankbook.get().getBankbook());
            int housingTypeChange = houseTypeConverter(aptInfoTarget); // 주택형변환 메소드 호출
            if (stdBankbook.get().getPrivateHousingSupplyIsPossible().equals(Yn.y)) {
                if (stdBankbook.get().getBankbook().equals(Bankbook.청약부금)) {
                    if (housingTypeChange <= stdBankbook.get().getRestrictionSaleArea()) {
                        return true;
                    } else if (housingTypeChange > stdBankbook.get().getRestrictionSaleArea()) { // 청약부금인데, 면적이 85제곱미터를 초과할 경우 false
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetRecipient(User user) { //생애최초대상자충족여부
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse());

        // 혼인 중인 경우
        if (!(user.getSpouseHouseMember() == null)) {
            if (user.getHouseMember().getMarriageDate() == null) // 혼인 중인데 혼인신고일이 null 일 경우 에러를 띄워줌
                throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);

            return true;
        }
        // 혼인 중이 아닌 경우
        else if (user.getSpouseHouseMember() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                if ((houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반) && houseMember.getMarriageDate() == null)) {// 혼인 중이 아닌 경우에는 미혼인 자녀가 있어야 인정
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetMonthlyAverageIncomePriority(User user) { //월평균소득기준충족여부_우선공급
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        Optional<Income> income = incomeRepository.findBySpecialSupplyAndSupplyAndAndHousingType(SpecialSupply.생애최초, Supply.우선공급, HousingType.민영);

        int houseMemberCount = 0; //세대구성원수
        int sumIncome = 0; // 소득합산

        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
        }
        //배우자 분리세대일 경우
        else {
            List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); // 신청자의 배우자의 전세대구성원의 자산 정보를 List로 가져옴

            for (HouseMember houseMember : houseMemberListUser) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
            for (HouseMember houseMember : spouseHouseMemberList) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
        }

        System.out.println("세대구성원 수 : " + houseMemberCount);
        System.out.println("소득합산 : " + sumIncome);

//        // 배우자가 세대구성원에 등록되어 있지 않을 경우 경고문을 띄워줌.
//        if (user.getSpouseHouseMember() == null) {
//            throw new CustomException(ErrorCode.NOT_FOUND_SPOUSE);
//        }

        if (!(user.getSpouseHouseMember() == null)) { // 배우자가 있을 경우
            if (user.getHouseMember().getIncome() == null || user.getSpouseHouseMember().getIncome() == null) {
                throw new CustomException(ErrorCode.NOT_FOUND_INCOME); //신청자나 배우자 둘 중에 한명이라도 income에 null 값이 들어올 경우 경고문을 띄워줌.
            }
        }

        if (houseMemberCount <= 3) {
            if (sumIncome <= income.get().getAverageMonthlyIncome3peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome3peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 4) {
            if (sumIncome <= income.get().getAverageMonthlyIncome4peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome4peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 5) {
            if (sumIncome <= income.get().getAverageMonthlyIncome5peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome5peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 6) {
            if (sumIncome <= income.get().getAverageMonthlyIncome6peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome6peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 7) {
            if (sumIncome <= income.get().getAverageMonthlyIncome7peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome7peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 8) {
            if (sumIncome <= income.get().getAverageMonthlyIncome8peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome8peopleLessExcess()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetMonthlyAverageIncomeGeneral(User user) { //월평균소득기준충족여부_일반공급
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        Optional<Income> income = incomeRepository.findBySpecialSupplyAndSupplyAndAndHousingType(SpecialSupply.생애최초, Supply.일반공급, HousingType.민영);

        int houseMemberCount = 0; //세대구성원수
        int sumIncome = 0; // 소득합산

        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
        } //배우자 분리세대일 경우
        else {
            List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); // 신청자의 배우자의 전세대구성원의 자산 정보를 List로 가져옴

            for (HouseMember houseMember : houseMemberListUser) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
            for (HouseMember houseMember : spouseHouseMemberList) {
                houseMemberCount++;
                if (!(houseMember.getBirthDay() == null) && calcAmericanAge(houseMember.getBirthDay()) >= 19 && houseMember.getIncome() != null) //만19세 이상만 소득 산정
                    sumIncome += houseMember.getIncome();
            }
        }

        System.out.println("세대구성원 수 : " + houseMemberCount);
        System.out.println("소득합산 : " + sumIncome);

//        // 배우자가 세대구성원에 등록되어 있지 않을 경우 경고문을 띄워줌.
//        if (user.getSpouseHouseMember() == null) {
//            throw new CustomException(ErrorCode.NOT_FOUND_SPOUSE);
//        }

        if (!(user.getSpouseHouseMember() == null)) { // 배우자가 있을 경우
            if (user.getHouseMember().getIncome() == null || user.getSpouseHouseMember().getIncome() == null) {
                throw new CustomException(ErrorCode.NOT_FOUND_INCOME); //신청자나 배우자 둘 중에 한명이라도 income에 null 값이 들어올 경우 경고문을 띄워줌.
            }
        }

        if (houseMemberCount <= 3) {
            if (sumIncome <= income.get().getAverageMonthlyIncome3peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome3peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 4) {
            if (sumIncome <= income.get().getAverageMonthlyIncome4peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome4peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 5) {
            if (sumIncome <= income.get().getAverageMonthlyIncome5peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome5peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 6) {
            if (sumIncome <= income.get().getAverageMonthlyIncome6peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome6peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 7) {
            if (sumIncome <= income.get().getAverageMonthlyIncome7peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome7peopleLessExcess()) {
                return true;
            }
        } else if (houseMemberCount <= 8) {
            if (sumIncome <= income.get().getAverageMonthlyIncome8peopleLessBelow() && sumIncome > income.get().getAverageMonthlyIncome8peopleLessExcess()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetHomelessHouseholdMembers(User user) { //전세대원무주택세대구성원충족여부 메소드
        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse()); //신청자의 세대구성원 가져오기

        int houseCount = 0;

        //배우자와 같은 세대이거나, 미혼일 경우
        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                int specialCase = 0; // 예외주택에 해당하는 경우를 count 하기 위해 specialCase라는 변수에 0으로 초기 세팅

                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) { // 반복문을 통해서 세대구성원의 자산 정보 List를 돌면서,
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) { // 신청자와의 관계가 부, 모, 조부, 조모, 배우자의부, 배우자의모, 배우자의조부, 배우자의조모일 경우
                            if (calcAmericanAge(houseMember.getBirthDay()) >= 60) { // 만나이가 60세 이상일 경우 specialCase 증가시킴
                                specialCase++;
                                continue;
                            }
                        }
                        if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { // 주거용건물유형이 오피스텥일 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { // 2018.12.11 이전에 취득한 분양권일 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) { // 주택예외사항해당여부에 해당하는 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 60) { // 60제곱미터 이하의 주택을 소유하고 있는 경우
                                flag++;
                                if (specialCase <= 0) // 만약, specialCase가 없을 경우 continue
                                    continue;
                                else // 그렇지 않으면, flag 값을 houseCount에 넣음
                                    houseCount = flag;
                                if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                                    continue;
                                else
                                    houseCount = flag;
                            }
                        }
                        houseCount++; // 그 외 주택은 houseCount 값 증가시킴
                    }
                }
            }
        }
        //배우자 분리세대일 경우
        else {
            List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); // 신청자의 배우자의 전세대구성원의 자산 정보를 List로 가져옴

            for (HouseMember houseMember : houseMemberListUser) { // 반복문을 통해서 신청자의 세대구성원 List를 돔
                List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(houseMember);

                int flag = 0;
                int specialCase = 0;

                for (HouseMemberProperty houseMemberProperty : houseMemberPropertyList) {
                    if (houseMemberProperty.getResidentialBuildingYn().equals(Yn.y)) {//소유주택이 주거용이면
                        HouseMemberRelation houseMemberRelation = houseMemberRelationRepository.findByUserAndOpponent(user, houseMember).get();
                        if ((houseMemberRelation.getRelation().getRelation().equals(Relation.부) || houseMemberRelation.getRelation().getRelation().equals(Relation.모) || houseMemberRelation.getRelation().getRelation().equals(Relation.조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.조모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의모) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조부) || houseMemberRelation.getRelation().getRelation().equals(Relation.배우자의조모))) { // 신청자와의 관계가 부, 모, 조부, 조모, 배우자의부, 배우자의모, 배우자의조부, 배우자의조모일 경우
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
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) {
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
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
                            if (calcAmericanAge(houseMember.getBirthDay()) >= 60) { // 만나이가 60세 이상일 경우 specialCase 증가시킴
                                specialCase++;
                                continue;
                            }
                        }
                        if (houseMemberProperty.getResidentialBuilding().equals(ResidentialBuilding.오피스텔)) { // 주거용건물유형이 오피스텥일 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getSaleRightYn().equals(Yn.y) && houseMemberProperty.getAcquisitionDate().isBefore(LocalDate.parse("2018-12-11"))) { // 2018.12.11 이전에 취득한 분양권일 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) { // 주택 처분일이 있을 경우
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) { // 주택예외사항해당여부에 해당하는 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 60) { //60제곱미터 이하의 주택을 소유하고 있는 경우
                                flag++;
                                if (specialCase <= 0) // 만약, specialCase가 없을 경우 continue
                                    continue;
                                else // 그렇지 않으면, flag 값을 houseCount에 넣음
                                    houseCount = flag;
                                if (flag <= 1) // 단, 2호 또는 2세대 이상의 주택 또는 분양권은 제외. 즉, 하나까진 count 안 한다는 의미.
                                    continue;
                                else
                                    houseCount = flag;
                            }
                        }
                        houseCount++; // 그 외 주택은 houseCount 값 증가시킴
                    }
                }
            }
        }

        if (houseCount == 0) // 주택수가 0일 경우 무주택세대구성원으로 판별
            return true;
        else // 아닐 경우 유주택세대구성원으로 판별, 탈락
            return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isHouseholder(User user) {
        if (user.getHouse().getHouseHolder() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSEHOLDER); //세대주 지정이 안되어있을 경우 경고를 띄움.
        } else if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetLivingInSurroundArea(User user, AptInfo aptInfo) {//아파트 분양정보의 인근지역과 거주지의 인근지역이 같다면
        com.hanium.chungyakpassback.entity.standard.AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        com.hanium.chungyakpassback.entity.standard.AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));

        if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea())
            return true;
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean isRestrictedArea(AptInfo aptInfo) { // 규제지역여부
        if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y))
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
            if (priorityJoinPeriod.getSupply().equals(Supply.특별공급) && priorityJoinPeriod.getSpecialSupply().equals(SpecialSupply.생애최초)) {
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
    public boolean meetDeposit(User user, AptInfoTarget aptInfoTarget) { // 예치금액충족여부확인
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        int housingTypeChange = houseTypeConverter(aptInfoTarget);
        List<PriorityDeposit> priorityDepositList = priorityDepositRepository.findAll();


        for (PriorityDeposit priorityDeposit : priorityDepositList) {
            if (priorityDeposit.getDepositArea().equals(user.getHouse().getAddressLevel1().getDepositArea())) {
                if (priorityDeposit.getAreaOver() < housingTypeChange && priorityDeposit.getAreaLessOrEqual() >= housingTypeChange && userBankbook.getDeposit() >= priorityDeposit.getDeposit()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetAllHouseMemberNotWinningIn5years(User user) { // 과거 5년 이내에 다른 주택에 당첨된 자가 속해 있는 무주택세대구성원
        LocalDate now = LocalDate.now();
        int periodYear = 0;

        List<HouseMember> houseMemberListUser = houseMemberRepository.findAllByHouse(user.getHouseMember().getHouse());

        //배우자와 같은 세대이거나, 미혼일 경우
        if (user.getHouse() == user.getSpouseHouse() || user.getSpouseHouse() == null) {
            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberChungyak> houseMemberChungyakList = houseMemberChungyakRepository.findAllByHouseMember(houseMember);

                for (HouseMemberChungyak houseMemberChungyak : houseMemberChungyakList) {
                    periodYear = now.minusYears(houseMemberChungyak.getWinningDate().getYear()).getYear();

                    if (periodYear <= 5)
                        return false;
                }
                return true;
            }
        }
        //배우자 분리세대일 경우
        else {
            List<HouseMember> spouseHouseMemberList = houseMemberRepository.findAllByHouse(user.getSpouseHouseMember().getHouse()); // 신청자의 배우자의 전세대구성원의 자산 정보를 List로 가져옴

            for (HouseMember houseMember : houseMemberListUser) {
                List<HouseMemberChungyak> houseMemberChungyakList = houseMemberChungyakRepository.findAllByHouseMember(houseMember);

                for (HouseMemberChungyak houseMemberChungyak : houseMemberChungyakList) {
                    periodYear = now.minusYears(houseMemberChungyak.getWinningDate().getYear()).getYear();

                    if (periodYear <= 5)
                        return false;
                }
            }

            for (HouseMember houseMember : spouseHouseMemberList) {
                List<HouseMemberChungyak> houseMemberChungyakList = houseMemberChungyakRepository.findAllByHouseMember(houseMember);

                for (HouseMemberChungyak houseMemberChungyak : houseMemberChungyakList) {
                    periodYear = now.minusYears(houseMemberChungyak.getWinningDate().getYear()).getYear();

                    if (periodYear <= 5)
                        return false;
                }
            }
        }
        return true;
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
