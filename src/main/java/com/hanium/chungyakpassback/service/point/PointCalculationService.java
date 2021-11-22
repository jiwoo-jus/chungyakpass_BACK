package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.GeneralMinyeongPointDto;
import com.hanium.chungyakpassback.dto.point.GeneralMinyeongResponsePointDto;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointCalculationService {

    List<GeneralMinyeongResponsePointDto> readGeneralMinyeongResponsePointCalculations();

    GeneralMinyeongResponsePointDto createGeneralMinyeongPointCalculation(GeneralMinyeongPointDto generalMinyeongPointDto);

    Integer periodOfHomelessness(User user);

    Integer bankbookJoinPeriod(User user);

    Integer numberOfDependents(User user, GeneralMinyeongPointDto generalMinyeongPointDto);

    boolean bankBookVaildYn(User user);
}
