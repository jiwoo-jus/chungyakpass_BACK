package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongOldParentsSupportResponseDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialOldParentsSupportDto;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfSpecialMinyeongOldParentSupportService {

    List<PointOfSpecialMinyeongOldParentsSupportResponseDto> readOldParentsSupportPointCalculations();

    PointOfSpecialMinyeongOldParentsSupportResponseDto createOldParentsSupportPointCalculation(PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto);

    Integer periodOfHomelessness(User user);

    Integer bankbookJoinPeriod(User user);

    Integer numberOfDependents(User user, PointOfSpecialOldParentsSupportDto pointOfSpecialOldParentsSupportDto);

    boolean bankBookVaildYn(User user);
}
