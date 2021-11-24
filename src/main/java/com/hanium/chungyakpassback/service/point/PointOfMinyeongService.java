package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfGeneralMinyeongDto;
import com.hanium.chungyakpassback.dto.point.PointOfGeneralMinyeongResponseDto;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfMinyeongService {

    List<PointOfGeneralMinyeongResponseDto> readGeneralMinyeongResponsePointCalculations();

    PointOfGeneralMinyeongResponseDto createGeneralMinyeongPointCalculation(PointOfGeneralMinyeongDto pointOfGeneralMinyeongDto);

    Integer periodOfHomelessness(User user);

    Integer bankbookJoinPeriod(User user);

    Integer numberOfDependents(User user, PointOfGeneralMinyeongDto pointOfGeneralMinyeongDto);

    boolean bankBookVaildYn(User user);
}
