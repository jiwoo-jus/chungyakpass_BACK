package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.point.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.point.*;
import com.hanium.chungyakpassback.util.SecurityUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/point")
public class PointController {
    private final PointCalculationService pointCalculationService;
    private final UserRepository userRepository;
    private final AptInfoRepository aptInfoRepository;
    private final PointCalculationOfMultiChildService pointCalculationOfMultiChildService;
    private final PointCalculationOfNewMarriedService pointCalculationOfNewMarriedService;
    private final PointCalculationOfOldParentSupportService pointCalculationOfOldParentSupportService;
    private final PointCalculationOfSingleParentsService pointCalculationOfSingleParentsService;

    public PointController(PointCalculationService pointCalculationService, UserRepository userRepository, AptInfoRepository aptInfoRepository, PointCalculationOfMultiChildService pointCalculationOfMultiChildService, PointCalculationOfNewMarriedService pointCalculationOfNewMarriedService, PointCalculationOfOldParentSupportService pointCalculationOfOldParentSupportService, PointCalculationOfSingleParentsService pointCalculationOfSingleParentsService)  {
        this.pointCalculationService = pointCalculationService;
        this.userRepository = userRepository;
        this.aptInfoRepository = aptInfoRepository;
        this.pointCalculationOfMultiChildService = pointCalculationOfMultiChildService;
        this.pointCalculationOfNewMarriedService = pointCalculationOfNewMarriedService;
        this.pointCalculationOfOldParentSupportService = pointCalculationOfOldParentSupportService;
        this.pointCalculationOfSingleParentsService = pointCalculationOfSingleParentsService;
    }


    @PostMapping("/genereal/minyeoung")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponsePointDto> generalMinyeongPoint(@RequestBody GeneralMinyeongPointDto generalMinyeongPointDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        Integer periodOfHomelessness = pointCalculationService.periodOfHomelessness(user);
        Integer periodOfBankbook = pointCalculationService.bankbookJoinPeriod(user);
        Integer numberOfDependents = pointCalculationService.numberOfDependents(user, generalMinyeongPointDto);
        return new ResponseEntity<>(new GeneralMinyeongResponsePointDto(periodOfHomelessness, periodOfBankbook, numberOfDependents), HttpStatus.OK);
    }


    @PostMapping("/special/multiChild")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialPointOfMultiChildResponseDto> specialMultiChildPoint(@RequestBody SpecialPointOfMultiChildDto specialPointOfMultiChildDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialPointOfMultiChildDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        Integer numberOfChild = pointCalculationOfMultiChildService.numberOfChild(user);
        Integer numberOfChildUnder6Year = pointCalculationOfMultiChildService.numberOfChildUnder6Year(user);
        Integer bankbookJoinPeriod = pointCalculationOfMultiChildService.bankbookJoinPeriod(user);
        Integer periodOfApplicableAreaResidence = pointCalculationOfMultiChildService.periodOfApplicableAreaResidence(user, aptInfo);
        Integer periodOfHomelessness = pointCalculationOfMultiChildService.periodOfHomelessness(user);
        Integer generationComposition = pointCalculationOfMultiChildService.generationComposition (specialPointOfMultiChildDto);
        return new ResponseEntity<>(new SpecialPointOfMultiChildResponseDto(numberOfChild,numberOfChildUnder6Year,bankbookJoinPeriod,periodOfApplicableAreaResidence,periodOfHomelessness,generationComposition), HttpStatus.OK);
    }


    @PostMapping ("/special/newMarried")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfNewMarriedResponseDto> specialMinyeongPointOfNewMarried(@RequestBody SpecialMinyeongPointOfNewMarriedDto specialMinyeongPointOfNewMarriedDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialMinyeongPointOfNewMarriedDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        Integer numberOfMinors = pointCalculationOfNewMarriedService.numberOfMinors(user);
        Integer periodOfMarriged = pointCalculationOfNewMarriedService.periodOfMarriged(user);
        Integer bankbookPaymentsCount = pointCalculationOfNewMarriedService.bankbookPaymentsCount(user);
        Integer periodOfApplicableAreaResidence =  pointCalculationOfNewMarriedService.periodOfApplicableAreaResidence ( user, aptInfo);
        Integer monthOfAverageIncome = pointCalculationOfNewMarriedService.monthOfAverageIncome(user);

        return new ResponseEntity<>(new SpecialMinyeongPointOfNewMarriedResponseDto(numberOfMinors,periodOfMarriged,bankbookPaymentsCount,periodOfApplicableAreaResidence,monthOfAverageIncome), HttpStatus.OK);
    }

    @PostMapping ("/special/singleParents")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfSingleParentsResponseDto> specialMinyeongPointOfNewMarried(@RequestBody SpecialMinyeongPointOfSingleParentsDto specialMinyeongPointOfSingleParentsDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(specialMinyeongPointOfSingleParentsDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));

        Integer numberOfMinors = pointCalculationOfSingleParentsService.numberOfMinors(user);
        Integer ageOfMostYoungChild = pointCalculationOfSingleParentsService.ageOfMostYoungChild(user);
        Integer bankbookPaymentsCount = pointCalculationOfSingleParentsService.bankbookPaymentsCount(user);
        Integer periodOfApplicableAreaResidence =  pointCalculationOfSingleParentsService.periodOfApplicableAreaResidence ( user, aptInfo);
        Integer monthOfAverageIncome = pointCalculationOfSingleParentsService.monthOfAverageIncome(user);

        return new ResponseEntity<>(new SpecialMinyeongPointOfSingleParentsResponseDto(numberOfMinors,ageOfMostYoungChild,bankbookPaymentsCount,periodOfApplicableAreaResidence,monthOfAverageIncome), HttpStatus.OK);
    }

    @PostMapping("/special/oldParentsSupport")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialPointOfOldParentsSupportResponseDto> generalMinyeongPoint(@RequestBody SpecialPointOfOldParentsSupportDto specialPointOfOldParentsSupportDto){
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        Integer periodOfHomelessness = pointCalculationOfOldParentSupportService.periodOfHomelessness(user);
        Integer periodOfBankbook = pointCalculationOfOldParentSupportService.bankbookJoinPeriod(user);
        Integer numberOfDependents = pointCalculationOfOldParentSupportService.numberOfDependents(user, specialPointOfOldParentsSupportDto);
        return new ResponseEntity<>(new SpecialPointOfOldParentsSupportResponseDto(periodOfHomelessness, periodOfBankbook, numberOfDependents), HttpStatus.OK);
    }

}