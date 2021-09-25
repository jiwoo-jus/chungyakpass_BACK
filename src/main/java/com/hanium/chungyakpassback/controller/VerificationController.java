package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.verification.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/verification")
public class VerificationController {
    private final UserRepository userRepository;
    private final AptInfoRepository aptInfoRepository;
    private final AptInfoTargetRepository aptInfoTargetRepository;
    private final GeneralPrivateVerificationService generalPrivateVerificationService;
    private final GeneralKookminVerificationService generalKookminVerificationService;
    private final SpecialPrivateMultiChildVerificationService specialPrivateMultiChildVerificationService;
    private final SpecialKookminPublicMultiChildVerificationService specialKookminPublicMultiChildVerificationService;
    private final SpecialPrivateOldParentVerificationService specialPrivateOldParentVerificationService;
    private final SpecialKookminPublicOldParentVerificationService specialKookminPublicOldParentVerificationService;
    private final SpecialPrivateVerificationFirstLifeService specialPrivateVerificationFirstLifeService;
    private final SpecialPublicVerificationFirstLifeService specialPublicVerificationFirstLifeService;


    public VerificationController(UserRepository userRepository,SpecialPublicVerificationFirstLifeService specialPublicVerificationFirstLifeService,SpecialPrivateVerificationFirstLifeService specialPrivateVerificationFirstLifeService, GeneralPrivateVerificationService generalPrivateVerificationService, AptInfoRepository aptInfoRepository, AptInfoTargetRepository aptInfoTargetRepository,GeneralKookminVerificationService generalKookminVerificationService, SpecialPrivateMultiChildVerificationService specialPrivateMultiChildVerificationService, SpecialKookminPublicMultiChildVerificationService specialKookminPublicMultiChildVerificationService, SpecialPrivateOldParentVerificationService specialPrivateOldParentVerificationService, SpecialKookminPublicOldParentVerificationService specialKookminPublicOldParentVerificationService) {
        this.userRepository = userRepository;
        this.generalPrivateVerificationService = generalPrivateVerificationService;
        this.specialPrivateVerificationFirstLifeService = specialPrivateVerificationFirstLifeService;
        this.specialPublicVerificationFirstLifeService =  specialPublicVerificationFirstLifeService;
        this.aptInfoRepository = aptInfoRepository;
        this.aptInfoTargetRepository = aptInfoTargetRepository;
        this.generalKookminVerificationService = generalKookminVerificationService;
        this.specialPrivateMultiChildVerificationService = specialPrivateMultiChildVerificationService;
        this.specialKookminPublicMultiChildVerificationService = specialKookminPublicMultiChildVerificationService;
        this.specialPrivateOldParentVerificationService = specialPrivateOldParentVerificationService;
        this.specialKookminPublicOldParentVerificationService = specialKookminPublicOldParentVerificationService;
    }

    @PostMapping("/general/minyeong")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> generalMinyeong(@RequestBody GeneralMinyeongDto generalMinyeongDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(generalMinyeongDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(generalMinyeongDto.getHousingType()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        boolean meetLivingInSurroundArea = generalPrivateVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean meetBankbookType = generalPrivateVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        Integer calcAmericanAge = generalPrivateVerificationService.calcAmericanAge(Optional.ofNullable(houseMember.getBirthDay()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_BIRTHDAY)));
        boolean isHouseholder = generalPrivateVerificationService.isHouseholder(user);
        boolean isRestrictedArea = generalPrivateVerificationService.isRestrictedArea(aptInfo);
        boolean meetAllHouseMemberNotWinningIn5years = generalPrivateVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean meetHouseHavingLessThan2Apt = generalPrivateVerificationService.meetHouseHavingLessThan2Apt(user);
        boolean meetBankbookJoinPeriod = generalPrivateVerificationService.meetBankbookJoinPeriod(user, aptInfo);
        boolean meetDeposit = generalPrivateVerificationService.meetDeposit(user, aptInfoTarget);
        boolean isPriorityApt = generalPrivateVerificationService.isPriorityApt(aptInfo, aptInfoTarget);


        return new ResponseEntity<>(new GeneralMinyeongResponseDto(meetLivingInSurroundArea, meetBankbookType, calcAmericanAge, isHouseholder, isRestrictedArea, meetAllHouseMemberNotWinningIn5years, meetHouseHavingLessThan2Apt, meetBankbookJoinPeriod, meetDeposit, isPriorityApt), HttpStatus.OK);
    }

    @PostMapping("/general/kookmin") //일반국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> generalKookmin(@RequestBody GeneralKookminDto generalKookminDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(generalKookminDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(generalKookminDto.getHousingType()).orElseThrow();

        Integer americanAge = generalKookminVerificationService.calcAmericanAge(houseMember.getBirthDay());
        boolean meetLivingInSurroundAreaTf = specialPrivateMultiChildVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = generalKookminVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetHomelessHouseholdMembersTf = generalKookminVerificationService.meetHomelessHouseholdMembers(user);
        boolean householderTf = generalKookminVerificationService.isHouseholder(user);
        boolean isRestrictedAreaTf = generalKookminVerificationService.isRestrictedArea(aptInfo);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = generalKookminVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean meetBankbookJoinPeriodTf = generalKookminVerificationService.meetBankbookJoinPeriod(user, aptInfo);
        boolean meetNumberOfPaymentsTf = generalKookminVerificationService.meetNumberOfPayments(user, aptInfo);

        return new ResponseEntity<>(new GeneralKookminResponseDto(americanAge, meetLivingInSurroundAreaTf, accountTf, meetHomelessHouseholdMembersTf, householderTf, isRestrictedAreaTf, meetAllHouseMemberNotWinningIn5yearsTf, meetBankbookJoinPeriodTf, meetNumberOfPaymentsTf), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/multichild") //특별다자녀민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> specialMultiChild(@RequestBody SpecialMinyeongMultiChildDto generalMultiChildDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(generalMultiChildDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(generalMultiChildDto.getHousingType()).orElseThrow();

        Integer americanAge = specialPrivateMultiChildVerificationService.calcAmericanAge(houseMember.getBirthDay());
        boolean meetLivingInSurroundAreaTf = specialPrivateMultiChildVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = specialPrivateMultiChildVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetHomelessHouseholdMembersTf = specialPrivateMultiChildVerificationService.meetHomelessHouseholdMembers(user);
        Integer calcMinorChildren = specialPrivateMultiChildVerificationService.calcMinorChildren(user);
        boolean householderTf = specialPrivateMultiChildVerificationService.isHouseholder(user);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = specialPrivateMultiChildVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean isRestrictedAreaTf = specialPrivateMultiChildVerificationService.isRestrictedArea(aptInfo);
        boolean meetHouseHavingLessThan2Apt = specialPrivateMultiChildVerificationService.meetHouseHavingLessThan2Apt(user);
        boolean specialTf = specialPrivateMultiChildVerificationService.isPriorityApt(aptInfo, aptInfoTarget);
        boolean meetDepositTf = specialPrivateMultiChildVerificationService.meetDeposit(user, aptInfoTarget);
        boolean meetBankbookJoinPeriodTf = specialPrivateMultiChildVerificationService.meetBankbookJoinPeriod(user, aptInfo);

        return new ResponseEntity<>(new SpecialMinyeongMultiChildResponseDto(americanAge, meetLivingInSurroundAreaTf, accountTf, meetHomelessHouseholdMembersTf, calcMinorChildren, householderTf, meetAllHouseMemberNotWinningIn5yearsTf, isRestrictedAreaTf, meetHouseHavingLessThan2Apt, specialTf, meetDepositTf, meetBankbookJoinPeriodTf), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/multichild") //특별다자녀국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> specialMultiChild(@RequestBody SpecialKookminPublicMultiChildDto specialKookminPublicMultiChildDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(specialKookminPublicMultiChildDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(specialKookminPublicMultiChildDto.getHousingType()).orElseThrow();

        Integer americanAge = specialKookminPublicMultiChildVerificationService.calcAmericanAge(houseMember.getBirthDay());
        boolean meetLivingInSurroundAreaTf = specialKookminPublicMultiChildVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = specialKookminPublicMultiChildVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetMonthlyAverageIncomeTf = specialKookminPublicMultiChildVerificationService.meetMonthlyAverageIncome(user);
        boolean meetPropertyTf = specialKookminPublicMultiChildVerificationService.meetProperty(user);
        boolean meetHomelessHouseholdMembersTf = specialKookminPublicMultiChildVerificationService.meetHomelessHouseholdMembers(user);
        Integer calcMinorChildren = specialKookminPublicMultiChildVerificationService.calcMinorChildren(user);
        boolean householderTf = specialKookminPublicMultiChildVerificationService.isHouseholder(user);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = specialKookminPublicMultiChildVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean isRestrictedAreaTf = specialKookminPublicMultiChildVerificationService.isRestrictedArea(aptInfo);
        boolean meetBankJoinPeriodTf = specialKookminPublicMultiChildVerificationService.meetBankbookJoinPeriod(user, aptInfo);
        boolean meetNumberOfPaymentsTf = specialKookminPublicMultiChildVerificationService.meetNumberOfPayments(user, aptInfo);

        return new ResponseEntity<>(new SpecialKookminPublicMultiChildResponseDto(americanAge, meetLivingInSurroundAreaTf, accountTf, meetMonthlyAverageIncomeTf, meetPropertyTf, meetHomelessHouseholdMembersTf, calcMinorChildren, householderTf, meetAllHouseMemberNotWinningIn5yearsTf, isRestrictedAreaTf, meetBankJoinPeriodTf, meetNumberOfPaymentsTf), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/oldparent") //특별노부모민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> specialOldParent(@RequestBody SpecialMinyeongOldParentDto specialMinyeongOldParentDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(specialMinyeongOldParentDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(specialMinyeongOldParentDto.getHousingType()).orElseThrow();

        Integer americanAge = specialPrivateOldParentVerificationService.calcAmericanAge(houseMember.getBirthDay());
        boolean meetLivingInSurroundAreaTf = specialPrivateOldParentVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = specialPrivateOldParentVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetOldParentSupportMore3yearsTf = specialPrivateOldParentVerificationService.meetOldParentSupportMore3years(user);
        boolean meetHomelessHouseholdMembersTf = specialPrivateOldParentVerificationService.meetHomelessHouseholdMembers(user);
        boolean householderTf = specialPrivateOldParentVerificationService.isHouseholder(user);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = specialPrivateOldParentVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean isRestrictedAreaTf = specialPrivateOldParentVerificationService.isRestrictedArea(aptInfo);
        boolean meetBankJoinPeriodTf = specialPrivateOldParentVerificationService.meetBankbookJoinPeriod(user, aptInfo);
        boolean meetDepositTf = specialPrivateOldParentVerificationService.meetDeposit(user, aptInfoTarget);

        return new ResponseEntity<>(new SpecialMinyeongOldParentResponseDto(americanAge, meetLivingInSurroundAreaTf, accountTf, meetOldParentSupportMore3yearsTf, meetHomelessHouseholdMembersTf, householderTf, meetAllHouseMemberNotWinningIn5yearsTf, isRestrictedAreaTf, meetBankJoinPeriodTf, meetDepositTf), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/oldparent") //특별노부모국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> specialOldParent(@RequestBody SpecialKookminPublicOldParentDto specialKookminPublicOldParentDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        HouseMember houseMember = user.getHouseMember();
        AptInfo aptInfo = aptInfoRepository.findById(specialKookminPublicOldParentDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(specialKookminPublicOldParentDto.getHousingType()).orElseThrow();

        Integer americanAge = specialKookminPublicOldParentVerificationService.calcAmericanAge(houseMember.getBirthDay());
        boolean meetLivingInSurroundAreaTf = specialKookminPublicOldParentVerificationService.meetLivingInSurroundArea(user, aptInfo);
        boolean accountTf = specialKookminPublicOldParentVerificationService.meetBankbookType(user, aptInfo, aptInfoTarget);
        boolean meetMonthlyAverageIncome = specialKookminPublicOldParentVerificationService.meetMonthlyAverageIncome(user);
        boolean meetPropertyTf = specialKookminPublicOldParentVerificationService.meetProperty(user);
        boolean meetOldParentSupportMore3yearsTf = specialKookminPublicOldParentVerificationService.meetOldParentSupportMore3years(user);
        boolean meetHomelessHouseholdMembersTf = specialKookminPublicOldParentVerificationService.meetHomelessHouseholdMembers(user);
        boolean householderTf = specialKookminPublicOldParentVerificationService.isHouseholder(user);
        boolean isRestrictedAreaTf = specialKookminPublicOldParentVerificationService.isRestrictedArea(aptInfo);
        boolean meetAllHouseMemberNotWinningIn5yearsTf = specialKookminPublicOldParentVerificationService.meetAllHouseMemberNotWinningIn5years(user);
        boolean meetBankJoinPeriodTf = specialKookminPublicOldParentVerificationService.meetBankbookJoinPeriod(user, aptInfo);
        boolean meetNumberOfPaymentsTf = specialKookminPublicOldParentVerificationService.meetNumberOfPayments(user, aptInfo);

        return new ResponseEntity<>(new SpecialKookminPublicOldParentResponseDto(americanAge, meetLivingInSurroundAreaTf, accountTf, meetMonthlyAverageIncome, meetPropertyTf, meetOldParentSupportMore3yearsTf, meetHomelessHouseholdMembersTf, householderTf, isRestrictedAreaTf, meetAllHouseMemberNotWinningIn5yearsTf, meetBankJoinPeriodTf, meetNumberOfPaymentsTf), HttpStatus.OK);
    }


    @PostMapping("/special/private/firstLife")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialPrivateFirstLifeResponseDto> generalMinyeong(@RequestBody SpecialPrivateFirstLifeDto specialPrivateFirstLifeDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialPrivateFirstLifeDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(specialPrivateFirstLifeDto.getHousingType()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        boolean targetHousingType = specialPrivateVerificationFirstLifeService.targetHousingType(aptInfoTarget);
        boolean targetHouseAmount = specialPrivateVerificationFirstLifeService.targetHouseAmount(aptInfo,aptInfoTarget);
        boolean monthOfAverageIncome = specialPrivateVerificationFirstLifeService.monthOfAverageIncome(user);
        boolean HomelessYn = specialPrivateVerificationFirstLifeService.HomelessYn(user);
        boolean vaildObject = specialPrivateVerificationFirstLifeService.vaildObject(user, aptInfo);
        return new ResponseEntity<>(new SpecialPrivateFirstLifeResponseDto(targetHousingType,targetHouseAmount,monthOfAverageIncome,HomelessYn,vaildObject), HttpStatus.OK);
    }

    @PostMapping("/special/public/firstLife")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialPublicFirstLifeResponseDto> generalMinyeong(@RequestBody SpecialPublicFirstLifeDto specialPublicFirstLifeDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialPublicFirstLifeDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(specialPublicFirstLifeDto.getHousingType()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        boolean targetHouseAmount = specialPublicVerificationFirstLifeService.targetHouseAmount(aptInfo,aptInfoTarget);
        boolean monthOfAverageIncome = specialPublicVerificationFirstLifeService.monthOfAverageIncome(user);
        boolean HomelessYn = specialPublicVerificationFirstLifeService.HomelessYn(user);
        boolean vaildObject = specialPublicVerificationFirstLifeService.vaildObject(user, aptInfo);
        boolean meetDeposit = specialPublicVerificationFirstLifeService.meetDeposit(user);

        return new ResponseEntity<>(new SpecialPublicFirstLifeResponseDto(targetHouseAmount,monthOfAverageIncome,HomelessYn,vaildObject,meetDeposit), HttpStatus.OK);
    }

}