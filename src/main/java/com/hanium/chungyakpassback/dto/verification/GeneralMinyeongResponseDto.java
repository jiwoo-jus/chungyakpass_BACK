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
    boolean houseHolderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean hasHouse;
    boolean meetBankbookJoinPeriodTf;
    boolean meetDepositTf;
    boolean specialTf;

}
