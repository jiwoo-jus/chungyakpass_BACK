package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongNewlyMarriedDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongNewlyMarriedResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfNewlyMarriedService {

    List<PointOfSpecialMinyeongNewlyMarriedResponseDto> readNewlyMarriedPointCalculations();

    PointOfSpecialMinyeongNewlyMarriedResponseDto createNewlyMarriedPointCalculation(PointOfSpecialMinyeongNewlyMarriedDto pointOfSpecialMinyeongNewlyMarriedDto);

    Integer numberOfMinors(User user);

    Integer periodOfMarriged(User user);

    Integer bankbookPaymentsCount(User user);

    Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo);

    Integer monthOfAverageIncome(User user);

}
