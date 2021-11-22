package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.record.UserVerificationRecordDto;
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

    @GetMapping("/record/all") //청악자격결과전체조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readAllUserVerificationRecord() {

        return new ResponseEntity<>(verificationRecordService.readAllUserVerificationRecord(), HttpStatus.OK);
    }

    @GetMapping("/general/minyeong") //일반민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readGeneralMinyeongVerification() {
        return new ResponseEntity(verificationRecordService.recordGeneralMinyeongResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong") //일반민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> generalMinyeong(@RequestBody GeneralMinyeongDto generalMinyeongDto) {
        return new ResponseEntity<>(generalPrivateVerificationService.generalMinyeongService(generalMinyeongDto), HttpStatus.OK);
    }

    @PatchMapping("/general/minyeong/{verificationRecordGeneralMinyeongId}") //일반민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponseDto> generalMinyeongUpdateDto(@PathVariable Long verificationRecordGeneralMinyeongId, @RequestBody GeneralMinyeongUpdateDto generalMinyeongUpdateDto) {
        return ResponseEntity.ok(generalPrivateVerificationService.generalMinyeongUpdateDto(verificationRecordGeneralMinyeongId, generalMinyeongUpdateDto));
    }

    @GetMapping("/general/kookmin") //일반국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readGeneralKookminVerification() {
        return new ResponseEntity(verificationRecordService.recordGeneralKookminResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/general/kookmin") //일반국민저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> generalKookmin(@RequestBody GeneralKookminDto generalKookminDto) {
        return new ResponseEntity<>(generalKookminVerificationService.generalKookminService(generalKookminDto), HttpStatus.OK);
    }

    @PatchMapping("/general/kookmin/{verificationRecordGeneralKookminId}") //일반국민업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralKookminResponseDto> generalKookminUpdateDto(@PathVariable Long verificationRecordGeneralKookminId, @RequestBody GeneralKookminUpdateDto generalKookminUpdateDto) {
        return ResponseEntity.ok(generalKookminVerificationService.generalKookminUpdateDto(verificationRecordGeneralKookminId, generalKookminUpdateDto));
    }

    @GetMapping("/special/minyeong/multichild") //특별다자녀민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialMinyeongMultiChildVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialMinyeongMultiChildResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/multichild") //특별다자녀민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> specialMultiChild(@RequestBody SpecialMinyeongMultiChildDto specialMinyeongMultiChildDto) {
        return new ResponseEntity<>(specialPrivateMultiChildVerificationService.specialMinyeongMultiChildService(specialMinyeongMultiChildDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/multichild/{verificationRecordSpecialMinyeongMultiChildId}") //특별다자녀민영업데이트
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongMultiChildResponseDto> specialMinyeongMultiChildUpdateDto(@PathVariable Long verificationRecordSpecialMinyeongMultiChildId, @RequestBody SpecialMinyeongMultiChildUpdateDto specialMinyeongMultiChildUpdateDto) {
        return ResponseEntity.ok(specialPrivateMultiChildVerificationService.specialMinyeongMultiChildUpdateDto(verificationRecordSpecialMinyeongMultiChildId, specialMinyeongMultiChildUpdateDto));
    }

    @GetMapping("/special/kookmin/public/multichild") //특별다자녀국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialKookminMultiChildVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialMinyeongMultiChildResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/multichild") //특별다자녀국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> specialMultiChild(@RequestBody SpecialKookminPublicMultiChildDto specialKookminPublicMultiChildDto) {
        return new ResponseEntity<>(specialKookminPublicMultiChildVerificationService.specialKookminPublicMultiChildService(specialKookminPublicMultiChildDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/multichild/{verificationRecordSpecialKookminMultiChildId}") //특별다자녀국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicMultiChildResponseDto> specialKookminPublicMultiChildUpdateDto(@PathVariable Long verificationRecordSpecialKookminMultiChildId, @RequestBody SpecialKookminPublicMultiChildUpdateDto specialKookminPublicMultiChildUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicMultiChildVerificationService.specialKookminPublicMultiChildUpdateDto(verificationRecordSpecialKookminMultiChildId, specialKookminPublicMultiChildUpdateDto));
    }

    @GetMapping("/special/minyeong/oldparent") //특별노부모민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialMinyeongOldParentVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialMinyeongOldParentResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/oldparent") //특별노부모민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> specialOldParent(@RequestBody SpecialMinyeongOldParentDto specialMinyeongOldParentDto) {
        return new ResponseEntity<>(specialPrivateOldParentVerificationService.specialMinyeongOldParentService(specialMinyeongOldParentDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/oldparent/{verificationRecordSpecialMinyeongOldParentId}") //특별노부모민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongOldParentResponseDto> specialMinyeongOldParentUpdateDto(@PathVariable Long verificationRecordSpecialMinyeongOldParentId, @RequestBody SpecialMinyeongOldParentUpdateDto specialMinyeongOldParentUpdateDto) {
        return ResponseEntity.ok(specialPrivateOldParentVerificationService.specialMinyeongOldParentUpdateDto(verificationRecordSpecialMinyeongOldParentId, specialMinyeongOldParentUpdateDto));
    }

    @GetMapping("/special/kookmin/public/oldparent") //특별노부모국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialKookminOldParentVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialKookminOldParentResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/oldparent") //특별노부모국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> specialOldParent(@RequestBody SpecialKookminPublicOldParentDto specialKookminPublicOldParentDto) {
        return new ResponseEntity<>(specialKookminPublicOldParentVerificationService.specialKookminPublicOldParentService(specialKookminPublicOldParentDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/oldparent/{verificationRecordSpecialKookminOldParentId}") //특별노부모국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicOldParentResponseDto> specialKookminPublicOldParentUpdateDto(@PathVariable Long verificationRecordSpecialKookminOldParentId, @RequestBody SpecialKookminPublicOldParentUpdateDto specialKookminPublicOldParentUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicOldParentVerificationService.specialKookminPublicOldParentUpdateDto(verificationRecordSpecialKookminOldParentId, specialKookminPublicOldParentUpdateDto));
    }

    @GetMapping("/special/minyeong/newlymarried") //특별신혼부부민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialMinyeongNewlyMarriedVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialMinyeongNewlyMarriedResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/newlymarried") //특별신혼부부민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialMinyeongNewlyMarriedDto specialMinyeongNewlyMarriedDto) {
        return new ResponseEntity<>(specialPrivateNewlyMarriedVerificationService.specialMinyeongNewlyMarriedService(specialMinyeongNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/newlymarried/{verificationRecordSpecialMinyeongNewlyMarriedId}") //특별신혼부부민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongNewlyMarriedResponseDto> specialMinyeongNewlyMarriedUpdateDto(@PathVariable Long verificationRecordSpecialMinyeongNewlyMarriedId, @RequestBody SpecialMinyeongNewlyMarriedUpdateDto specialMinyeongNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialPrivateNewlyMarriedVerificationService.specialMinyeongNewlyMarriedUpdateDto(verificationRecordSpecialMinyeongNewlyMarriedId, specialMinyeongNewlyMarriedUpdateDto));
    }

    @GetMapping("/special/kookmin/newlymarried") //특별신혼부부국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialKookminPublicNewlyMarriedVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialKookminPublicNewlyMarriedResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/newlymarried") //특별신혼부부국민
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialKookminNewlyMarriedDto specialKookminNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminNewlyMarriedVerificationService.specialKookminNewlyMarriedService(specialKookminNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/newlymarried/{verificationRecordSpecialKookminNewlyMarriedId}") //특별신혼부부국민업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminNewlyMarriedResponseDto> specialKookminNewlyMarriedUpdateDto(@PathVariable Long verificationRecordSpecialKookminNewlyMarriedId, @RequestBody SpecialKookminNewlyMarriedUpdateDto specialKookminNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialKookminNewlyMarriedVerificationService.specialKookminNewlyMarriedUpdateDto(verificationRecordSpecialKookminNewlyMarriedId, specialKookminNewlyMarriedUpdateDto));
    }

    @PostMapping("/special/kookmin/public/newlymarried") //특별신혼부부국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicNewlyMarriedResponseDto> specialNewlyMarried(@RequestBody SpecialKookminPublicNewlyMarriedDto specialKookminPublicNewlyMarriedDto) {
        return new ResponseEntity<>(specialKookminPublicNewlyMarriedVerificationService.specialKookminPublicNewlyMarriedService(specialKookminPublicNewlyMarriedDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/newlymarried/{verificationRecordSpecialKookminNewlyMarriedId}")
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicNewlyMarriedResponseDto> specialKookminPublicNewlyMarriedUpdateDto(@PathVariable Long verificationRecordSpecialKookminNewlyMarriedId, @RequestBody SpecialKookminPublicNewlyMarriedUpdateDto specialKookminPublicNewlyMarriedUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicNewlyMarriedVerificationService.specialKookminPublicNewlyMarriedUpdateDto(verificationRecordSpecialKookminNewlyMarriedId, specialKookminPublicNewlyMarriedUpdateDto));
    }

    @GetMapping("/special/minyeong/firstLife") //특별생애최초민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialMinyeongFirstLifeVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialMinyeongFirstLifeResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/minyeong/firstLife") //특별생애최초민영
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongFirstLifeResponseDto> specialFirstLife(@RequestBody SpecialMinyeongFirstLifeDto specialMinyeongFirstLifeDto) {
        return new ResponseEntity<>(specialPrivateFirstLifeVerificationService.specialMinyeongFirstLifeService(specialMinyeongFirstLifeDto), HttpStatus.OK);
    }

    @PatchMapping("/special/minyeong/firstLife/{verificationRecordSpecialMinyeongFirstLifeId}") //특별생애최초민영업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongFirstLifeResponseDto> specialMinyeongFirstLifeUpdateDto(@PathVariable Long verificationRecordSpecialMinyeongFirstLifeId, @RequestBody SpecialMinyeongFirstLifeUpdateDto specialMinyeongFirstLifeUpdateDto) {
        return ResponseEntity.ok(specialPrivateFirstLifeVerificationService.specialMinyeongFirstLifeUpdateDto(verificationRecordSpecialMinyeongFirstLifeId, specialMinyeongFirstLifeUpdateDto));
    }

    @GetMapping("/special/kookmin/public/firstLife") //특별생애최초국민조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<UserVerificationRecordDto> readSpecialKookminFirstLifeVerification() {
        return new ResponseEntity(verificationRecordService.recordSpecialKookminFirstLifeResponseVerification(), HttpStatus.OK);
    }

    @PostMapping("/special/kookmin/public/firstLife") //특별생애최초국민공공주택
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicFirstLifeResponseDto> specialFirstLife(@RequestBody SpecialKookminPublicFirstLifeDto specialKookminPublicFirstLifeDto) {
        return new ResponseEntity<>(specialKookminPublicFirstLifeVerificationService.specialKookminPublicFirstLifeService(specialKookminPublicFirstLifeDto), HttpStatus.OK);
    }

    @PatchMapping("/special/kookmin/public/firstLife/{verificationRecordSpecialKookminFirstLifeId}") //특별생애최초국민공공주택업데이트
    //특별신혼부부국민공공주택업데이트
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialKookminPublicFirstLifeResponseDto> specialKookminPublicFirstLifeUpdateDto(@PathVariable Long verificationRecordSpecialKookminFirstLifeId, @RequestBody SpecialKookminPublicFirstLifeUpdateDto specialKookminPublicFirstLifeUpdateDto) {
        return ResponseEntity.ok(specialKookminPublicFirstLifeVerificationService.specialKookminPublicFirstLifeUpdateDto(verificationRecordSpecialKookminFirstLifeId, specialKookminPublicFirstLifeUpdateDto));
    }

}