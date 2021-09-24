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
    Integer calcMinorChildren;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean isRestrictedAreaTf;
    boolean hasHouse;
    boolean specialTf;
    boolean meetDepositTf;
    boolean meetBankbookJoinPeriodTf;
}
