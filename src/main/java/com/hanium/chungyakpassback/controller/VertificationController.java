package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.vertification.GeneralMinyeongDto;
import com.hanium.chungyakpassback.dto.vertification.GeneralMinyeongResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.service.vertification.GeneralPrivateVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/vertification")
public class VertificationController {
    private final GeneralPrivateVerificationService generalPrivateVerificationService;
    private final AptInfoRepository aptInfoRepository;
    private final AptInfoTargetRepository aptInfoTargetRepository;

    public VertificationController(GeneralPrivateVerificationService generalPrivateVerificationService, AptInfoRepository aptInfoRepository, AptInfoTargetRepository aptInfoTargetRepository) {
        this.generalPrivateVerificationService = generalPrivateVerificationService;
        this.aptInfoRepository = aptInfoRepository;
        this.aptInfoTargetRepository = aptInfoTargetRepository;
    }

    @PostMapping("/general/minyeoung")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> generalMinyeong(@RequestBody GeneralMinyeongDto generalMinyeongDto) {
        AptInfo aptInfo = aptInfoRepository.findById(generalMinyeongDto.getNotificationNumber()).get();
        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingType(generalMinyeongDto.getHousingType());

        boolean surroundingAreaTf = generalPrivateVerificationService.surroundingArea(aptInfo);
        boolean accountTf = generalPrivateVerificationService.accountStatus(aptInfo, aptInfoTarget);
        Integer americanAge = generalPrivateVerificationService.americanAgeCount();
        boolean houseHolderTf = generalPrivateVerificationService.householder();
        boolean restrictedAreaTf = generalPrivateVerificationService.restrictedArea(aptInfo);
        boolean winningHistoryTf = generalPrivateVerificationService.winningHistory();
        boolean hasHouseYn = generalPrivateVerificationService.hasHouseYn();
        boolean termsOfPolicyTf = generalPrivateVerificationService.termsOfPolicy(aptInfo);
        boolean depositAmountTf = generalPrivateVerificationService.depositAmount(aptInfoTarget);
        boolean specialTf = generalPrivateVerificationService.specialNote(aptInfo, aptInfoTarget);


        return new ResponseEntity<>(new GeneralMinyeongResponseDto(surroundingAreaTf, accountTf, americanAge, houseHolderTf, restrictedAreaTf, winningHistoryTf, hasHouseYn, termsOfPolicyTf, depositAmountTf, specialTf), HttpStatus.OK);
    }
}
