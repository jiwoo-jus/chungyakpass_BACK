package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongSingleParentsDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongSingleParentsResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;

import java.util.List;

public interface PointOfSpecialMinyeongSingleParentsService {

    List<PointOfSpecialMinyeongSingleParentsResponseDto> readSingleParentsPointCalculations();

    PointOfSpecialMinyeongSingleParentsResponseDto createSingleParentsPointCalculation(PointOfSpecialMinyeongSingleParentsDto pointOfSpecialMinyeongSingleParentsDto);

    Integer numberOfMinors(User user);

    Integer bankbookPaymentsCount(User user);

    Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo);

    Integer ageOfMostYoungChild(User user);

    Integer monthOfAverageIncome(User user);


}
