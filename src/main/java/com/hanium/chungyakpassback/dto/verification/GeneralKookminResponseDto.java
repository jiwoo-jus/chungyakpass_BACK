package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralKookminResponseDto {
    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetNumberOfPaymentsTf;
}
