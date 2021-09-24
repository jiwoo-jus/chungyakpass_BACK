package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

public interface SpecialPrivateVerificationFirstLifeService {
    boolean targetHousingType(AptInfoTarget aptInfoTarget);
    boolean targetHouseAmount( AptInfo aptInfo, AptInfoTarget aptInfoTarget);
    boolean monthOfAverageIncome(User user);
    boolean HomelessYn(User user);
    boolean vaildObject(User user, AptInfo aptInfo);

}
