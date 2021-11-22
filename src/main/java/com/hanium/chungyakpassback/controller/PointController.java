package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.point.*;
import com.hanium.chungyakpassback.dto.point.ReadPointCalculationDto;
import com.hanium.chungyakpassback.service.point.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/point")
public class PointController {
    private final PointCalculationService pointCalculationService;
    private final PointCalculationOfMultiChildService pointCalculationOfMultiChildService;
    private final PointCalculationOfNewMarriedService pointCalculationOfNewMarriedService;
    private final PointCalculationOfOldParentSupportService pointCalculationOfOldParentSupportService;
    private final PointCalculationOfSingleParentsService pointCalculationOfSingleParentsService;
    private final ReadPointCalculationService readPointCalculationService;

    public PointController(PointCalculationService pointCalculationService, PointCalculationOfMultiChildService pointCalculationOfMultiChildService, PointCalculationOfNewMarriedService pointCalculationOfNewMarriedService, PointCalculationOfOldParentSupportService pointCalculationOfOldParentSupportService, PointCalculationOfSingleParentsService pointCalculationOfSingleParentsService, ReadPointCalculationService readPointCalculationService) {
        this.pointCalculationService = pointCalculationService;
        this.pointCalculationOfMultiChildService = pointCalculationOfMultiChildService;
        this.pointCalculationOfNewMarriedService = pointCalculationOfNewMarriedService;
        this.pointCalculationOfOldParentSupportService = pointCalculationOfOldParentSupportService;
        this.pointCalculationOfSingleParentsService = pointCalculationOfSingleParentsService;
        this.readPointCalculationService = readPointCalculationService;
    }

    @GetMapping("/record/point") //청악가배점결과전체조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readAllUserPointRecord() {

        return new ResponseEntity<>(readPointCalculationService.readAllUserPointRecord(), HttpStatus.OK);
    }

    @GetMapping("/general/minyeong") //일반민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readGeneralMinyeongResponsePointCalculations() {
        return new ResponseEntity(pointCalculationService.readGeneralMinyeongResponsePointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong") //일반민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<GeneralMinyeongResponsePointDto> createGeneralMinyeongPointCalculation(@RequestBody GeneralMinyeongPointDto generalMinyeongPointDto) {
        return new ResponseEntity<>(pointCalculationService.createGeneralMinyeongPointCalculation(generalMinyeongPointDto), HttpStatus.OK);
    }

    @GetMapping("/special/multiChild") //다자녀조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readMultiChildPointCalculations() {
        return new ResponseEntity(pointCalculationOfMultiChildService.readMultiChildPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/multiChild") //다자녀저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfMultiChildResponseDto> createMultiChildPointCalculation(@RequestBody SpecialMinyeongPointOfMultiChildDto specialMinyeongPointOfMultiChildDto) {
        return new ResponseEntity<>(pointCalculationOfMultiChildService.createMultiChildPointCalculation(specialMinyeongPointOfMultiChildDto), HttpStatus.OK);
    }

    @GetMapping("/special/newlyMarried") //신혼부부조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readNewlyMarriedPointCalculations() {
        return new ResponseEntity(pointCalculationOfNewMarriedService.readNewlyMarriedPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/newlyMarried") //신혼부부저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfNewMarriedResponseDto> createNewlyMarriedPointCalculation(@RequestBody SpecialMinyeongPointOfNewMarriedDto specialMinyeongPointOfNewMarriedDto) {

        return new ResponseEntity<>(pointCalculationOfNewMarriedService.createNewlyMarriedPointCalculation(specialMinyeongPointOfNewMarriedDto), HttpStatus.OK);
    }

    @GetMapping("/special/singleParents") //한부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readSingleParentsPointCalculations() {
        return new ResponseEntity(pointCalculationOfSingleParentsService.readSingleParentsPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/singleParents") //한부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfSingleParentsResponseDto> createSingleParentsPointCalculation(@RequestBody SpecialMinyeongPointOfSingleParentsDto specialMinyeongPointOfSingleParentsDto) {
        return new ResponseEntity<>(pointCalculationOfSingleParentsService.createSingleParentsPointCalculation(specialMinyeongPointOfSingleParentsDto), HttpStatus.OK);
    }

    @GetMapping("/special/oldParentsSupport") //노부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readOldParentsSupportPointCalculations() {
        return new ResponseEntity(pointCalculationOfOldParentSupportService.readOldParentsSupportPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/oldParentsSupport") //노부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<SpecialMinyeongPointOfOldParentsSupportResponseDto> createOldParentsSupportPointCalculation(@RequestBody SpecialPointOfOldParentsSupportDto specialPointOfOldParentsSupportDto) {
        return new ResponseEntity<>(pointCalculationOfOldParentSupportService.createOldParentsSupportPointCalculation(specialPointOfOldParentsSupportDto), HttpStatus.OK);
    }


}