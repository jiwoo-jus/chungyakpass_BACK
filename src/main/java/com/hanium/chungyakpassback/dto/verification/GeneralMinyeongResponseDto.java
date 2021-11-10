package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponseDto {

    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    Integer americanAge;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean meetHouseHavingLessThan2AptTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetDepositTf;
    boolean isPriorityApt;

}
