package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.User;

public interface PointCalculationOfSingleParentsService {
    Integer numberOfMinors(User user);
    Integer bankbookPaymentsCount(User user);
    Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo);
    Integer ageOfMostYoungChild(User user);
    Integer monthOfAverageIncome(User user);


}
