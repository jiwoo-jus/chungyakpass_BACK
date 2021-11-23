package com.hanium.chungyakpassback.controller;

import com.hanium.chungyakpassback.dto.point.*;
import com.hanium.chungyakpassback.dto.point.ReadAllUserPointDto;
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
    private final ReadAllUserPointService readAllUserPointService;

    public PointController(PointOfService pointOfService, PointOfMultiChildService pointOfMultiChildService, PointOfNewlyMarriedService pointOfNewlyMarriedService, PointOfOldParentSupportService pointOfOldParentSupportService, PointOfSingleParentsService pointOfSingleParentsService, ReadAllUserPointService readAllUserPointService) {
        this.pointOfService = pointOfService;
        this.pointOfMultiChildService = pointOfMultiChildService;
        this.pointOfNewlyMarriedService = pointOfNewlyMarriedService;
        this.pointOfOldParentSupportService = pointOfOldParentSupportService;
        this.pointOfSingleParentsService = pointOfSingleParentsService;
        this.readAllUserPointService = readAllUserPointService;
    }

    @GetMapping("/record/all") //청악가배점결과전체조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readAllUserPointRecord() {

        return new ResponseEntity<>(readAllUserPointService.readAllUserPointRecord(), HttpStatus.OK);
    }

    @GetMapping("/general/minyeong") //일반민영조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readGeneralMinyeongResponsePointCalculations() {
        return new ResponseEntity(pointOfService.readGeneralMinyeongResponsePointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/general/minyeong") //일반민영저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfGeneralMinyeongResponseDto> createGeneralMinyeongPointCalculation(@RequestBody PointOfGeneralMinyeongDto pointOfGeneralMinyeongDto) {
        return new ResponseEntity<>(pointOfService.createGeneralMinyeongPointCalculation(pointOfGeneralMinyeongDto), HttpStatus.OK);
    }

    @GetMapping("/special/multi-child") //다자녀조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readMultiChildPointCalculations() {
        return new ResponseEntity(pointOfMultiChildService.readMultiChildPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/multi-child") //다자녀저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongMultiChildResponseDto> createMultiChildPointCalculation(@RequestBody PointOfSpecialMinyeongMultiChildDto pointOfSpecialMinyeongMultiChildDto) {
        return new ResponseEntity<>(pointOfMultiChildService.createMultiChildPointCalculation(pointOfSpecialMinyeongMultiChildDto), HttpStatus.OK);
    }

    @GetMapping("/special/newly-married") //신혼부부조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readNewlyMarriedPointCalculations() {
        return new ResponseEntity(pointOfNewlyMarriedService.readNewlyMarriedPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/newly-married") //신혼부부저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongNewlyMarriedResponseDto> createNewlyMarriedPointCalculation(@RequestBody PointOfSpecialMinyeongNewlyMarriedDto pointOfSpecialMinyeongNewlyMarriedDto) {

        return new ResponseEntity<>(pointOfNewlyMarriedService.createNewlyMarriedPointCalculation(pointOfSpecialMinyeongNewlyMarriedDto), HttpStatus.OK);
    }

    @GetMapping("/special/single-parents") //한부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readSingleParentsPointCalculations() {
        return new ResponseEntity(pointOfSingleParentsService.readSingleParentsPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/single-parents") //한부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongSingleParentsResponseDto> createSingleParentsPointCalculation(@RequestBody PointOfSpecialMinyeongSingleParentsDto pointOfSpecialMinyeongSingleParentsDto) {
        return new ResponseEntity<>(pointOfSingleParentsService.createSingleParentsPointCalculation(pointOfSpecialMinyeongSingleParentsDto), HttpStatus.OK);
    }

    @GetMapping("/special/old-parents-support") //노부모조회
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<ReadAllUserPointDto> readOldParentsSupportPointCalculations() {
        return new ResponseEntity(pointOfOldParentSupportService.readOldParentsSupportPointCalculations(), HttpStatus.OK);
    }

    @PostMapping("/special/old-parents-support") //노부모저장
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<PointOfSpecialMinyeongOldParentsSupportResponseDto> createOldParentsSupportPointCalculation(@RequestBody PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto) {
        return new ResponseEntity<>(pointOfOldParentSupportService.createOldParentsSupportPointCalculation(pointOfSpecialOldParentsSupportDto), HttpStatus.OK);
    }


}