package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicOldParentResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetMonthlyAverageIncomeTf;
    boolean meetPropertyTf;
    boolean meetOldParentSupportMore3yearsTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetBankJoinPeriodTf;
    boolean meetNumberOfPaymentsTf;

}
