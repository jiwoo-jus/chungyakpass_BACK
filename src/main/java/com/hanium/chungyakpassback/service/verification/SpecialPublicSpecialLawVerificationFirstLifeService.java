package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

public interface SpecialPublicSpecialLawVerificationFirstLifeService {
    boolean targetHouseAmount( AptInfo aptInfo, AptInfoTarget aptInfoTarget);
    boolean monthOfAverageIncome(User user);
    boolean homelessYn(User user);
    boolean vaildObject(User user, AptInfo aptInfo);
    boolean meetDeposit(User user);
    boolean meetStandardProperty(User user);

}
