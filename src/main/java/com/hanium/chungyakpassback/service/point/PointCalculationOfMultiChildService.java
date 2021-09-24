package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.SpecialPointOfMultiChildDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;

public interface PointCalculationOfMultiChildService {
    Integer numberOfChild(User user);
    Integer numberOfChildUnder6Year(User user);
    Integer bankbookJoinPeriod(User user);
    Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo);
    Integer periodOfHomelessness(User user);
    Integer generationComposition(SpecialPointOfMultiChildDto specialPointOfMultiChildDto);
}
