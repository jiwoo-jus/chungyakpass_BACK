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
    boolean secondChungyak;
    boolean meetHomelessHouseholdMembersTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetNumberOfPaymentsTf;

    Long verificationRecordSpecialKookminNewlyMarriedId;
}
