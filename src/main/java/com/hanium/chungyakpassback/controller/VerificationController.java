package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.record.UserVerificationRecordDto;
import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.repository.record.*;
import com.hanium.chungyakpassback.service.verification.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/verification")
public class VerificationController {
    private final GeneralPrivateVerificationService generalPrivateVerificationService;
    private final GeneralKookminVerificationService generalKookminVerificationService;
    private final SpecialPrivateMultiChildVerificationService specialPrivateMultiChildVerificationService;
    private final SpecialKookminPublicMultiChildVerificationService specialKookminPublicMultiChildVerificationService;
    private final SpecialPrivateOldParentVerificationService specialPrivateOldParentVerificationService;
    private final SpecialKookminPublicOldParentVerificationService specialKookminPublicOldParentVerificationService;
    private final SpecialPrivateNewlyMarriedVerificationService specialPrivateNewlyMarriedVerificationService;
    private final SpecialKookminNewlyMarriedVerificationService specialKookminNewlyMarriedVerificationService;
    private final SpecialKookminPublicNewlyMarriedVerificationService specialKookminPublicNewlyMarriedVerificationService;
    private final SpecialPrivateFirstLifeVerificationService specialPrivateFirstLifeVerificationService;
    private final SpecialKookminPublicFirstLifeVerificationService specialKookminPublicFirstLifeVerificationService;
    public final VerificationRecordService verificationRecordService;

    public VerificationController(GeneralPrivateVerificationService generalPrivateVerificationService, GeneralKookminVerificationService generalKookminVerificationService, SpecialPrivateMultiChildVerificationService specialPrivateMultiChildVerificationService, SpecialKookminPublicMultiChildVerificationService specialKookminPublicMultiChildVerificationService, SpecialPrivateOldParentVerificationService specialPrivateOldParentVerificationService, SpecialKookminPublicOldParentVerificationService specialKookminPublicOldParentVerificationService, SpecialPrivateNewlyMarriedVerificationService specialPrivateNewlyMarriedVerificationService, SpecialKookminNewlyMarriedVerificationService specialKookminNewlyMarriedVerificationService, SpecialKookminPublicNewlyMarriedVerificationService specialKookminPublicNewlyMarriedVerificationService, SpecialPrivateFirstLifeVerificationService specialPrivateFirstLifeVerificationService, SpecialKookminPublicFirstLifeVerificationService specialKookminPublicFirstLifeVerificationService, VerificationRecordService verificationRecordService) {
        this.generalPrivateVerificationService = generalPrivateVerificationService;
        this.generalKookminVerificationService = generalKookminVerificationService;
        this.specialPrivateMultiChildVerificationService = specialPrivateMultiChildVerificationService;
        this.specialKookminPublicMultiChildVerificationService = specialKookminPublicMultiChildVerificationService;
        this.specialPrivateOldParentVerificationService = specialPrivateOldParentVerificationService;
        this.specialKookminPublicOldParentVerificationService = specialKookminPublicOldParentVerificationService;
        this.specialPrivateNewlyMarriedVerificationService = specialPrivateNewlyMarriedVerificationService;
        this.specialKookminNewlyMarriedVerificationService = specialKookminNewlyMarriedVerificationService;
        this.specialKookminPublicNewlyMarriedVerificationService = specialKookminPublicNewlyMarriedVerificationService;
        this.specialPrivateFirstLifeVerificationService = specialPrivateFirstLifeVerificationService;
        this.specialKookminPublicFirstLifeVerificationService = specialKookminPublicFirstLifeVerificationService;
        this.verificationRecordService = verificationRecordService;
    }

    @GetMapping("/general/minyeong")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readGeneralMinyeongPoint() {
        return new ResponseEntity(verificationRecordService.recordGeneralMinyeongResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> generalMinyeong(@RequestBody GeneralMinyeongDto generalMinyeongDto) {
        return new ResponseEntity<>(generalPrivateVerificationService.generalMinyeongService(generalMinyeongDto), HttpStatus.OK);
    }


    @PostMapping("/general/kookmin") //일반국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> generalKookmin(@RequestBody GeneralKookminDto generalKookminDto) {
        return new ResponseEntity<>(generalKookminVerificationService.generalKookminService(generalKookminDto), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/multichild") //특별다자녀민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> specialMultiChild(@RequestBody SpecialMinyeongMultiChildDto specialMinyeongMultiChildDto) {
        return new ResponseEntity<>(specialPrivateMultiChildVerificationService.specialMinyeongMultiChildService(specialMinyeongMultiChildDto), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/multichild") //특별다자녀국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> specialMultiChild(@RequestBody SpecialKookminPublicMultiChildDto specialKookminPublicMultiChildDto) {
        return new ResponseEntity<>(specialKookminPublicMultiChildVerificationService.specialKookminPublicMultiChildService(specialKookminPublicMultiChildDto), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/oldparent") //특별노부모민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> specialOldParent(@RequestBody SpecialMinyeongOldParentDto specialMinyeongOldParentDto) {
        return new ResponseEntity<>(specialPrivateOldParentVerificationService.specialMinyeongOldParentService(specialMinyeongOldParentDto), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/oldparent") //특별노부모국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> specialOldParent(@RequestBody SpecialKookminPublicOldParentDto specialKookminPublicOldParentDto) {
        return new ResponseEntity<>(specialKookminPublicOldParentVerificationService.specialKookminPublicOldParentService(specialKookminPublicOldParentDto), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/newlymarried") //특별신혼부부민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialMinyeongNewlyMarriedDto specialMinyeongNewlyMarriedDto) {
        return new ResponseEntity<>(specialPrivateNewlyMarriedVerificationService.specialMinyeongNewlyMarriedService(specialMinyeongNewlyMarriedDto), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/newlymarried") //특별신혼부부국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialKookminNewlyMarriedDto specialKookminNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminNewlyMarriedVerificationService.specialKookminNewlyMarriedService(specialKookminNewlyMarriedDto), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/newlymarried") //특별신혼부부국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialKookminPublicNewlyMarriedDto specialKookminPublicNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminPublicNewlyMarriedVerificationService.specialKookminPublicNewlyMarriedService(specialKookminPublicNewlyMarriedDto), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/firstLife") //특별생애최초민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongFirstLifeResponseDto> specialFirstLife(@RequestBody SpecialMinyeongFirstLifeDto specialMinyeongFirstLifeDto) {
        return new ResponseEntity<>(specialPrivateFirstLifeVerificationService.specialMinyeongFirstLifeService(specialMinyeongFirstLifeDto), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/firstLife") //특별생애최초국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicFirstLifeResponseDto> specialFirstLife(@RequestBody SpecialKookminPublicFirstLifeDto specialKookminPublicFirstLifeDto) {
        return new ResponseEntity<>(specialKookminPublicFirstLifeVerificationService.specialKookminPublicFirstLifeService(specialKookminPublicFirstLifeDto), HttpStatus.OK);
    }
}