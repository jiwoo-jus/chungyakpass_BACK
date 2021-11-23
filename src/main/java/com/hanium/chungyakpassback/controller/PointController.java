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
    private final PointOfService pointOfService;
    private final PointOfMultiChildService pointOfMultiChildService;
    private final PointOfNewlyMarriedService pointOfNewlyMarriedService;
    private final PointOfOldParentSupportService pointOfOldParentSupportService;
    private final PointOfSingleParentsService pointOfSingleParentsService;
    private final ReadPointCalculationService readPointCalculationService;

    public PointController(PointOfService pointOfService, PointOfMultiChildService pointOfMultiChildService, PointOfNewlyMarriedService pointOfNewlyMarriedService, PointOfOldParentSupportService pointOfOldParentSupportService, PointOfSingleParentsService pointOfSingleParentsService, ReadPointCalculationService readPointCalculationService) {
        this.pointOfService = pointOfService;
        this.pointOfMultiChildService = pointOfMultiChildService;
        this.pointOfNewlyMarriedService = pointOfNewlyMarriedService;
        this.pointOfOldParentSupportService = pointOfOldParentSupportService;
        this.pointOfSingleParentsService = pointOfSingleParentsService;
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
        return new ResponseEntity(pointOfService.readGeneralMinyeongResponsePointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong") //일반민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfGeneralMinyeongResponseDto> createGeneralMinyeongPointCalculation(@RequestBody PointOfGeneralMinyeongDto pointOfGeneralMinyeongDto) {
        return new ResponseEntity<>(pointOfService.createGeneralMinyeongPointCalculation(pointOfGeneralMinyeongDto), HttpStatus.OK);
    }

    @GetMapping("/special/multiChild") //다자녀조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readMultiChildPointCalculations() {
        return new ResponseEntity(pointOfMultiChildService.readMultiChildPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/multiChild") //다자녀저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongMultiChildResponseDto> createMultiChildPointCalculation(@RequestBody PointOfSpecialMinyeongMultiChildDto pointOfSpecialMinyeongMultiChildDto) {
        return new ResponseEntity<>(pointOfMultiChildService.createMultiChildPointCalculation(pointOfSpecialMinyeongMultiChildDto), HttpStatus.OK);
    }

    @GetMapping("/special/newlyMarried") //신혼부부조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readNewlyMarriedPointCalculations() {
        return new ResponseEntity(pointOfNewlyMarriedService.readNewlyMarriedPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/newlyMarried") //신혼부부저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongNewlyMarriedResponseDto> createNewlyMarriedPointCalculation(@RequestBody PointOfSpecialMinyeongNewlyMarriedDto pointOfSpecialMinyeongNewlyMarriedDto) {

        return new ResponseEntity<>(pointOfNewlyMarriedService.createNewlyMarriedPointCalculation(pointOfSpecialMinyeongNewlyMarriedDto), HttpStatus.OK);
    }

    @GetMapping("/special/singleParents") //한부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readSingleParentsPointCalculations() {
        return new ResponseEntity(pointOfSingleParentsService.readSingleParentsPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/singleParents") //한부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongSingleParentsResponseDto> createSingleParentsPointCalculation(@RequestBody PointOfSpecialMinyeongSingleParentsDto pointOfSpecialMinyeongSingleParentsDto) {
        return new ResponseEntity<>(pointOfSingleParentsService.createSingleParentsPointCalculation(pointOfSpecialMinyeongSingleParentsDto), HttpStatus.OK);
    }

    @GetMapping("/special/oldParentsSupport") //노부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadPointCalculationDto> readOldParentsSupportPointCalculations() {
        return new ResponseEntity(pointOfOldParentSupportService.readOldParentsSupportPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/oldParentsSupport") //노부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongOldParentsSupportResponseDto> createOldParentsSupportPointCalculation(@RequestBody PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto) {
        return new ResponseEntity<>(pointOfOldParentSupportService.createOldParentsSupportPointCalculation(pointOfSpecialOldParentsSupportDto), HttpStatus.OK);
    }


}