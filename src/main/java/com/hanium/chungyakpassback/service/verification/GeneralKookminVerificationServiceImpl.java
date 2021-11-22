package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordGeneralKookmin;
import com.hanium.chungyakpassback.entity.standard.PriorityJoinPeriod;
import com.hanium.chungyakpassback.entity.standard.PriorityPaymentsCount;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.verification.VerificationRecordGeneralKookminRepository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.BankbookRepository;
import com.hanium.chungyakpassback.repository.standard.PriorityPaymentsCountRepository;
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
public class GeneralKookminVerificationServiceImpl implements GeneralKookminVerificationService {

    final UserBankbookRepository userBankbookRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final AptInfoRepository aptInfoRepository;
    final HouseMemberRepository houseMemberRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final HouseMemberChungyakRepository houseMemberChungyakRepository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final com.hanium.chungyakpassback.repository.standard.PriorityJoinPeriodRepository priorityJoinPeriodRepository;
    final PriorityPaymentsCountRepository priorityPaymentsCountRepository;
    final BankbookRepository bankbookRepository;
    final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;
    final UserRepository userRepository;
    final AptInfoTargetRepository aptInfoTargetRepository;
    final VerificationRecordGeneralKookminRepository verificationRecordGeneralKookminRepository;

    @Override //일반국민조회
    public List<GeneralKookminResponseDto> readGeneralKookminVerifications() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<GeneralKookminResponseDto> generalKookminResponseDtos = new ArrayList<>();
        for (VerificationRecordGeneralKookmin verificationRecordGeneralKookmin : verificationRecordGeneralKookminRepository.findAllByUser(user)) {
            GeneralKookminResponseDto generalKookminResponseDto = new GeneralKookminResponseDto(verificationRecordGeneralKookmin);
            generalKookminResponseDtos.add(generalKookminResponseDto);
        }

        return generalKookminResponseDtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GeneralKookminResponseDto createGeneralKookminVerification(GeneralKookminDto generalKookminDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(generalKookminDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingTypeAndAptInfo(generalKookminDto.getHousingType(), aptInfo).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        Integer americanAge = calcAmericanAge(Optional.ofNullable(houseMember.getBirthDay()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BIRTHDAY)));
        boolean meetLivingInSurroundAreaTf = meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetHomelessHouseholdMembersTf = meetHomelessHouseholdMembers(user);
        boolean meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestriction(user);
        boolean householderTf = isHouseholder(user);
        boolean isRestrictedAreaTf = isRestrictedArea(aptInfo);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = meetAllHouseMemberNotWinningIn5years(user);
        boolean meetBankbookJoinPeriodTf = meetBankbookJoinPeriod(user, aptInfo);
        boolean meetNumberOfPaymentsTf = meetNumberOfPayments(user, aptInfo);

        VerificationRecordGeneralKookmin verificationRecordGeneralKookmin = new VerificationRecordGeneralKookmin(user, americanAge, meetLivingInSurroundAreaTf, accountTf, meetHomelessHouseholdMembersTf, householderTf, meetAllHouseMemberNotWinningIn5yearsTf, meetAllHouseMemberRewinningRestrictionTf, meetBankbookJoinPeriodTf, meetNumberOfPaymentsTf, isRestrictedAreaTf, aptInfo, aptInfoTarget);
//        verificationRecordGeneralKookmin.setRanking(generalKookminDto.getRanking());
//        verificationRecordGeneralKookmin.setSibilingSupportYn(generalKookminDto.getSibilingSupportYn());
//        verificationRecordGeneralKookmin.setTwentiesSoleHouseHolderYn(generalKookminDto.getTwentiesSoleHouseHolderYn());
        verificationRecordGeneralKookminRepository.save(verificationRecordGeneralKookmin);
        return new GeneralKookminResponseDto(verificationRecordGeneralKookmin);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public GeneralKookminResponseDto updateGeneralKookminVerification(Long verificationRecordGeneralKookminId, GeneralKookminUpdateDto generalKookminUpdateDto) {
        VerificationRecordGeneralKookmin verificationRecordGeneralKookmin = verificationRecordGeneralKookminRepository.findById(verificationRecordGeneralKookminId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_VERIFICATION_RECORD_ID));
        verificationRecordGeneralKookmin.setSibilingSupportYn(generalKookminUpdateDto.getSibilingSupportYn());
        verificationRecordGeneralKookmin.setTwentiesSoleHouseHolderYn(generalKookminUpdateDto.getTwentiesSoleHouseHolderYn());
        verificationRecordGeneralKookmin.setRanking(generalKookminUpdateDto.getRanking());
        verificationRecordGeneralKookminRepository.save(verificationRecordGeneralKookmin);
        return new GeneralKookminResponseDto(verificationRecordGeneralKookmin);
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
    public boolean meetLivingInSurroundArea(User user, AptInfo aptInfo) {//아파트 분양정보의 인근지역과 거주지의 인근지역이 같다면
        if (user.getHouseMember() == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_MEMBER); // 세대구성원->세대를 통해서 주소를 user의 지역_레벨1을 가져오는 것이기 때문에 user의 세대구성원이 비어있으면 안됨.
        }

        com.hanium.chungyakpassback.entity.standard.AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        com.hanium.chungyakpassback.entity.standard.AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));

        if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea()) {
            return true;
        }
        return false;
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
            if (stdBankbook.get().getNationalHousingSupplyPossible().equals(Yn.y)) {
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
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) {
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) { // 주택예외사항해당여부에 해당하는 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 20) { // 20제곱미터 이하의 주택을 소유하고 있는 경우
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
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) {
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 20) { //20제곱미터 이하의 주택을 소유하고 있는 경우
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
                        } else if (!(houseMemberProperty.getDispositionDate() == null)) {
                            specialCase++;
                            continue;
                        } else if (houseMemberProperty.getExceptionHouseYn().equals(Yn.y)) { // 주택예외사항해당여부에 해당하는 경우 specialCase 증가
                            specialCase++;
                            continue;
                        } else {
                            if (houseMemberProperty.getExclusiveArea() <= 20) { //20제곱미터 이하의 주택을 소유하고 있는 경우
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
            throw new CustomException(ErrorCode.NOT_FOUND_HOUSE_HOLDER); //세대주 지정이 안되어있을 경우 경고를 띄움.
        } else if (user.getHouse().getHouseHolder().getId().equals(user.getHouseMember().getId())) {
            return true;
        }
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
            if (priorityJoinPeriod.getSupply().equals(Supply.일반공급)) { // 청약통장이 유효하면서 공급유형이 일반공급인 경우,
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
    public boolean meetNumberOfPayments(User user, AptInfo aptInfo) { //납입횟수충족여부확인
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new RuntimeException("등록된 청약통장이 없습니다.");
        UserBankbook userBankbook = optUserBankbook.get();

        List<PriorityPaymentsCount> priorityPaymentsCountList = priorityPaymentsCountRepository.findAll();

        for (PriorityPaymentsCount priorityPaymentsCount : priorityPaymentsCountList) {
            if (priorityPaymentsCount.getSupply().equals(Supply.일반공급)) { // 청약통장이 유효하면서 공급유형이 일반공급인 경우,
                if (priorityPaymentsCount.getSpeculationOverheated().equals(aptInfo.getSpeculationOverheated()) && priorityPaymentsCount.getSubscriptionOverheated().equals(aptInfo.getSubscriptionOverheated()) && priorityPaymentsCount.getAtrophyArea().equals(aptInfo.getAtrophyArea()) && priorityPaymentsCount.getMetropolitanAreaYn().equals(addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).get().getMetropolitanAreaYn())) {
                    if (userBankbook.getPaymentsCount() >= priorityPaymentsCount.getCountPayments())
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
