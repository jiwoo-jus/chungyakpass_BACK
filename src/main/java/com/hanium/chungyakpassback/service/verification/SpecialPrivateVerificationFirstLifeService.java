package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

public interface SpecialPrivateVerificationFirstLifeService {
    boolean targetHousingType(AptInfoTarget aptInfoTarget);
    boolean targetHouseAmount( AptInfo aptInfo, AptInfoTarget aptInfoTarget);
    boolean homelessYn(User user);
    boolean validObject(User user, AptInfo aptInfo);
    boolean monthOfAverageIncomePriority(User user);
    boolean monthOfAverageIncomeGeneral(User user);

}
