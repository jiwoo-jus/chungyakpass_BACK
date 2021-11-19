package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongMultiChildResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    Integer calcMinorChildren;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean isRestrictedAreaTf;
    boolean meetHouseHavingLessThan2AptTf;
    boolean isPriorityApt;
    boolean meetDepositTf;
    boolean meetBankbookJoinPeriodTf;

    Long verificationRecordSpecialMinyeongMultiChildId;
}
