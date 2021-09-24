package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponseDto {

    boolean meetLivingInSurroundArea;
    boolean meetBankbookType;
    Integer calcAmericanAge;
    boolean isHouseholder;
    boolean isRestrictedArea;
    boolean meetAllHouseMemberNotWinningIn5years;
    boolean meetHouseHavingLessThan2Apt;
    boolean meetBankbookJoinPeriod;
    boolean meetDeposit;
    boolean isPriorityApt;
}
