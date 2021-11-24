package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfGeneralMinyeongResponseDto;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfGeneralMinyeongService {

    List<PointOfGeneralMinyeongResponseDto> readGeneralMinyeongResponsePointCalculations();

    PointOfGeneralMinyeongResponseDto createGeneralMinyeongPointCalculation();

    Integer periodOfHomelessness(User user);

    Integer bankbookJoinPeriod(User user);

    Integer numberOfDependents(User user);

    boolean bankBookVaildYn(User user);
}
