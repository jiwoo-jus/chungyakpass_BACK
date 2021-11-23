package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.verification.ReadVerificationDto;
import com.hanium.chungyakpassback.dto.verification.*;
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
    public final ReadVerificationService readVerificationService;

    public VerificationController(GeneralPrivateVerificationService generalPrivateVerificationService, GeneralKookminVerificationService generalKookminVerificationService, SpecialPrivateMultiChildVerificationService specialPrivateMultiChildVerificationService, SpecialKookminPublicMultiChildVerificationService specialKookminPublicMultiChildVerificationService, SpecialPrivateOldParentVerificationService specialPrivateOldParentVerificationService, SpecialKookminPublicOldParentVerificationService specialKookminPublicOldParentVerificationService, SpecialPrivateNewlyMarriedVerificationService specialPrivateNewlyMarriedVerificationService, SpecialKookminNewlyMarriedVerificationService specialKookminNewlyMarriedVerificationService, SpecialKookminPublicNewlyMarriedVerificationService specialKookminPublicNewlyMarriedVerificationService, SpecialPrivateFirstLifeVerificationService specialPrivateFirstLifeVerificationService, SpecialKookminPublicFirstLifeVerificationService specialKookminPublicFirstLifeVerificationService, ReadVerificationService readVerificationService) {
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
        this.readVerificationService = readVerificationService;
    }

    @GetMapping("/record/all") //청악자격결과전체조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readAllVerifications() {

        return new ResponseEntity<>(readVerificationService.readAllVerifications(), HttpStatus.OK);
    }

    @GetMapping("/general/minyeong") //일반민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readGeneralMinyeongVerifications() {
        return new ResponseEntity(generalPrivateVerificationService.readGeneralMinyeongVerifications(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong") //일반민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> createGeneralMinyeongVerification(@RequestBody GeneralMinyeongDto generalMinyeongDto) {
        return new ResponseEntity<>(generalPrivateVerificationService.createGeneralMinyeongVerification(generalMinyeongDto), HttpStatus.OK);
    }

    @PatchMapping("/general/minyeong/{verificationRecordGeneralMinyeongId}") //일반민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> updateGeneralMinyeongVerification(@PathVariable Long verificationRecordGeneralMinyeongId, @RequestBody GeneralMinyeongUpdateDto generalMinyeongUpdateDto) {
        return ResponseEntity.ok(generalPrivateVerificationService.updateGeneralMinyeongVerification(verificationRecordGeneralMinyeongId, generalMinyeongUpdateDto));
    }

    @GetMapping("/general/kookmin") //일반국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readGeneralKookminVerifications() {
        return new ResponseEntity(generalKookminVerificationService.readGeneralKookminVerifications(), HttpStatus.OK);
    }

    @PostMapping("/general/kookmin") //일반국민저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> createGeneralKookminVerification(@RequestBody GeneralKookminDto generalKookminDto) {
        return new ResponseEntity<>(generalKookminVerificationService.createGeneralKookminVerification(generalKookminDto), HttpStatus.OK);
    }

    @PatchMapping("/general/kookmin/{verificationRecordGeneralKookminId}") //일반국민업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> updateGeneralKookminVerification(@PathVariable Long verificationRecordGeneralKookminId, @RequestBody GeneralKookminUpdateDto generalKookminUpdateDto) {
        return ResponseEntity.ok(generalKookminVerificationService.updateGeneralKookminVerification(verificationRecordGeneralKookminId, generalKookminUpdateDto));
    }

    @GetMapping("/special/minyeong/multiChild") //특별다자녀민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialMinyeongMultiChildVerifications() {
        return new ResponseEntity(specialPrivateMultiChildVerificationService.readSpecialMinyeongMultiChildVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/multiChild") //특별다자녀민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> createSpecialMinyeongMultiChildVerification(@RequestBody SpecialMinyeongMultiChildDto specialMinyeongMultiChildDto) {
        return new ResponseEntity<>(specialPrivateMultiChildVerificationService.createSpecialMinyeongMultiChildVerification(specialMinyeongMultiChildDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/multiChild/{verificationRecordSpecialMinyeongMultiChildId}") //특별다자녀민영업데이트
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> updateSpecialMinyeongMultiChildVerification(@PathVariable Long verificationRecordSpecialMinyeongMultiChildId, @RequestBody SpecialMinyeongMultiChildUpdateDto specialMinyeongMultiChildUpdateDto) {
        return ResponseEntity.ok(specialPrivateMultiChildVerificationService.updateSpecialMinyeongMultiChildVerification(verificationRecordSpecialMinyeongMultiChildId, specialMinyeongMultiChildUpdateDto));
    }

    @GetMapping("/special/kookmin/public/multiChild") //특별다자녀국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialKookminMultiChildVerifications() {
        return new ResponseEntity(specialKookminPublicMultiChildVerificationService.readSpecialKookminMultiChildVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/multiChild") //특별다자녀국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> createSpecialKookminPublicMultiChildVerification(@RequestBody SpecialKookminPublicMultiChildDto specialKookminPublicMultiChildDto) {
        return new ResponseEntity<>(specialKookminPublicMultiChildVerificationService.createSpecialKookminPublicMultiChildVerification(specialKookminPublicMultiChildDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/multiChild/{verificationRecordSpecialKookminMultiChildId}") //특별다자녀국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> updateSpecialKookminPublicMultiChildVerification(@PathVariable Long verificationRecordSpecialKookminMultiChildId, @RequestBody SpecialKookminPublicMultiChildUpdateDto specialKookminPublicMultiChildUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicMultiChildVerificationService.updateSpecialKookminPublicMultiChildVerification(verificationRecordSpecialKookminMultiChildId, specialKookminPublicMultiChildUpdateDto));
    }

    @GetMapping("/special/minyeong/oldParent") //특별노부모민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialMinyeongOldParentVerifications() {
        return new ResponseEntity(specialPrivateOldParentVerificationService.readSpecialMinyeongOldParentVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/oldParent") //특별노부모민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> createSpecialMinyeongOldParentVerification(@RequestBody SpecialMinyeongOldParentDto specialMinyeongOldParentDto) {
        return new ResponseEntity<>(specialPrivateOldParentVerificationService.createSpecialMinyeongOldParentVerification(specialMinyeongOldParentDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/oldParent/{verificationRecordSpecialMinyeongOldParentId}") //특별노부모민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> updateSpecialMinyeongOldParentVerification(@PathVariable Long verificationRecordSpecialMinyeongOldParentId, @RequestBody SpecialMinyeongOldParentUpdateDto specialMinyeongOldParentUpdateDto) {
        return ResponseEntity.ok(specialPrivateOldParentVerificationService.updateSpecialMinyeongOldParentVerification(verificationRecordSpecialMinyeongOldParentId, specialMinyeongOldParentUpdateDto));
    }

    @GetMapping("/special/kookmin/public/oldparent") //특별노부모국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialKookminOldParentVerifications() {
        return new ResponseEntity(specialKookminPublicOldParentVerificationService.readSpecialKookminOldParentVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/oldParent") //특별노부모국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> createSpecialKookminPublicOldParentVerification(@RequestBody SpecialKookminPublicOldParentDto specialKookminPublicOldParentDto) {
        return new ResponseEntity<>(specialKookminPublicOldParentVerificationService.createSpecialKookminPublicOldParentVerification(specialKookminPublicOldParentDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/oldParent/{verificationRecordSpecialKookminOldParentId}") //특별노부모국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> updateSpecialKookminPublicOldParentVerification(@PathVariable Long verificationRecordSpecialKookminOldParentId, @RequestBody SpecialKookminPublicOldParentUpdateDto specialKookminPublicOldParentUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicOldParentVerificationService.updateSpecialKookminPublicOldParentVerification(verificationRecordSpecialKookminOldParentId, specialKookminPublicOldParentUpdateDto));
    }

    @GetMapping("/special/minyeong/newlyMarried") //특별신혼부부민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialMinyeongNewlyMarriedVerifications() {
        return new ResponseEntity(specialPrivateNewlyMarriedVerificationService.readSpecialMinyeongNewlyMarriedVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/newlyMarried") //특별신혼부부민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongNewlyMarriedResponseDto> createSpecialMinyeongNewlyMarriedVerification(@RequestBody SpecialMinyeongNewlyMarriedDto specialMinyeongNewlyMarriedDto) {
        return new ResponseEntity<>(specialPrivateNewlyMarriedVerificationService.createSpecialMinyeongNewlyMarriedVerification(specialMinyeongNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/newlyMarried/{verificationRecordSpecialMinyeongNewlyMarriedId}") //특별신혼부부민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongNewlyMarriedResponseDto> updateSpecialMinyeongNewlyMarriedVerification(@PathVariable Long verificationRecordSpecialMinyeongNewlyMarriedId, @RequestBody SpecialMinyeongNewlyMarriedUpdateDto specialMinyeongNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialPrivateNewlyMarriedVerificationService.updateSpecialMinyeongNewlyMarriedVerification(verificationRecordSpecialMinyeongNewlyMarriedId, specialMinyeongNewlyMarriedUpdateDto));
    }

    @GetMapping("/special/kookmin/newlyMarried") //특별신혼부부국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialKookminPublicNewlyMarriedVerifications() {
        return new ResponseEntity(specialKookminPublicNewlyMarriedVerificationService.readSpecialKookminPublicNewlyMarriedVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/newlyMarried") //특별신혼부부국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminNewlyMarriedResponseDto> createSpecialKookminNewlyMarriedVerification(@RequestBody SpecialKookminNewlyMarriedDto specialKookminNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminNewlyMarriedVerificationService.createSpecialKookminNewlyMarriedVerification(specialKookminNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/newlyMarried/{verificationRecordSpecialKookminNewlyMarriedId}") //특별신혼부부국민업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminNewlyMarriedResponseDto> updateSpecialKookminNewlyMarriedVerification(@PathVariable Long verificationRecordSpecialKookminNewlyMarriedId, @RequestBody SpecialKookminNewlyMarriedUpdateDto specialKookminNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialKookminNewlyMarriedVerificationService.updateSpecialKookminNewlyMarriedVerification(verificationRecordSpecialKookminNewlyMarriedId, specialKookminNewlyMarriedUpdateDto));
    }

    @PostMapping("/special/kookmin/public/newlyMarried") //특별신혼부부국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicNewlyMarriedResponseDto> createSpecialKookminPublicNewlyMarriedVerification(@RequestBody SpecialKookminPublicNewlyMarriedDto specialKookminPublicNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminPublicNewlyMarriedVerificationService.createSpecialKookminPublicNewlyMarriedVerification(specialKookminPublicNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/newlyMarried/{verificationRecordSpecialKookminNewlyMarriedId}")
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicNewlyMarriedResponseDto> updateSpecialKookminPublicNewlyMarriedVerification(@PathVariable Long verificationRecordSpecialKookminNewlyMarriedId, @RequestBody SpecialKookminPublicNewlyMarriedUpdateDto specialKookminPublicNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicNewlyMarriedVerificationService.updateSpecialKookminPublicNewlyMarriedVerification(verificationRecordSpecialKookminNewlyMarriedId, specialKookminPublicNewlyMarriedUpdateDto));
    }

    @GetMapping("/special/minyeong/firstLife") //특별생애최초민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialMinyeongFirstLifeVerifications() {
        return new ResponseEntity(specialPrivateFirstLifeVerificationService.readSpecialMinyeongFirstLifeVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/firstLife") //특별생애최초민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongFirstLifeResponseDto> createSpecialMinyeongFirstLifeVerification(@RequestBody SpecialMinyeongFirstLifeDto specialMinyeongFirstLifeDto) {
        return new ResponseEntity<>(specialPrivateFirstLifeVerificationService.createSpecialMinyeongFirstLifeVerification(specialMinyeongFirstLifeDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/firstLife/{verificationRecordSpecialMinyeongFirstLifeId}") //특별생애최초민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongFirstLifeResponseDto> updateSpecialMinyeongFirstLifeVerification(@PathVariable Long verificationRecordSpecialMinyeongFirstLifeId, @RequestBody SpecialMinyeongFirstLifeUpdateDto specialMinyeongFirstLifeUpdateDto) {
        return ResponseEntity.ok(specialPrivateFirstLifeVerificationService.updateSpecialMinyeongFirstLifeVerification(verificationRecordSpecialMinyeongFirstLifeId, specialMinyeongFirstLifeUpdateDto));
    }

    @GetMapping("/special/kookmin/public/firstLife") //특별생애최초국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadVerificationDto> readSpecialKookminFirstLifeVerifications() {
        return new ResponseEntity(specialKookminPublicFirstLifeVerificationService.readSpecialKookminFirstLifeVerifications(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/firstLife") //특별생애최초국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicFirstLifeResponseDto> createSpecialKookminPublicFirstLifeVerification(@RequestBody SpecialKookminPublicFirstLifeDto specialKookminPublicFirstLifeDto) {
        return new ResponseEntity<>(specialKookminPublicFirstLifeVerificationService.createSpecialKookminPublicFirstLifeVerification(specialKookminPublicFirstLifeDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/firstLife/{verificationRecordSpecialKookminFirstLifeId}") //특별생애최초국민공공주택업데이트
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicFirstLifeResponseDto> updateSpecialKookminPublicFirstLifeVerification(@PathVariable Long verificationRecordSpecialKookminFirstLifeId, @RequestBody SpecialKookminPublicFirstLifeUpdateDto specialKookminPublicFirstLifeUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicFirstLifeVerificationService.updateSpecialKookminPublicFirstLifeVerification(verificationRecordSpecialKookminFirstLifeId, specialKookminPublicFirstLifeUpdateDto));
    }

}