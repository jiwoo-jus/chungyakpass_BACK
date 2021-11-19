package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongFirstLife;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongFirstLifeResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetRecipientTf;
    boolean meetMonthlyAverageIncomePriorityTf;
    boolean meetMonthlyAverageIncomeGeneralTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean isRestrictedAreaTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetDepositTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordSpecialMinyeongFirstLifeId;

    @Builder
    public SpecialMinyeongFirstLifeResponseDto(VerificationRecordSpecialMinyeongFirstLife verificationRecordSpecialMinyeongFirstLife) {
        this.americanAge = verificationRecordSpecialMinyeongFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongFirstLife.isAccountTf();
        this.meetRecipientTf = verificationRecordSpecialMinyeongFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialMinyeongFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialMinyeongFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.isRestrictedAreaTf = verificationRecordSpecialMinyeongFirstLife.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongFirstLife.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationRecordSpecialMinyeongFirstLife.isMeetDepositTf();
        this.verificationRecordSpecialMinyeongFirstLifeId = verificationRecordSpecialMinyeongFirstLife.getId();
        this.createdDate = verificationRecordSpecialMinyeongFirstLife.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongFirstLife.getModifiedDate();
    }
}
