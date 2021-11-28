package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongMultiChildDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongMultiChildResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfSpecialMinyeongMultiChildService {

    List<PointOfSpecialMinyeongMultiChildResponseDto> readMultiChildPointCalculations();

    PointOfSpecialMinyeongMultiChildResponseDto createMultiChildPointCalculation(PointOfSpecialMinyeongMultiChildDto pointOfSpecialMinyeongMultiChildDto);

    Integer numberOfChild(User user);

    Integer numberOfChildUnder6Year(User user);

    Integer bankbookJoinPeriod(User user);

    Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo);

    Integer periodOfHomelessness(User user);

    Integer generationComposition(PointOfSpecialMinyeongMultiChildDto pointOfSpecialMinyeongMultiChildDto);
}
