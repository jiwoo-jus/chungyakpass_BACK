package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminNewlyMarriedResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetMonthlyAverageIncomePriorityTf;
    boolean meetMonthlyAverageIncomeGeneralTf;
    boolean meetMarriagePeriodIn7yearsTf;
    boolean hasMinorChildren;
    boolean is2ndChungyak;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean isRestrictedAreaTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetNumberOfPaymentsTf;

}
