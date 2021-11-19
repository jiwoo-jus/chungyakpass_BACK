package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminFirstLife;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicFirstLifeResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetRecipientTf;
    boolean meetMonthlyAverageIncomePriorityTf;
    boolean meetMonthlyAverageIncomeGeneralTf;
    boolean meetPropertyTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean isRestrictedAreaTf;
    boolean meetNumberOfPaymentsTf;
    boolean meetBankbookJoinPeriodTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordSpecialKookminFirstLifeId;

    public SpecialKookminPublicFirstLifeResponseDto(VerificationRecordSpecialKookminFirstLife verificationRecordSpecialKookminFirstLife) {
        this.americanAge = verificationRecordSpecialKookminFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminFirstLife.isAccountTf();
        this.meetRecipientTf = verificationRecordSpecialKookminFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialKookminFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialKookminFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationRecordSpecialKookminFirstLife.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialKookminFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialKookminFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.isRestrictedAreaTf = verificationRecordSpecialKookminFirstLife.isRestrictedAreaTf();
        this.meetNumberOfPaymentsTf = verificationRecordSpecialKookminFirstLife.isMeetNumberOfPaymentsTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminFirstLife.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminFirstLifeId = verificationRecordSpecialKookminFirstLife.getId();
        this.createdDate = verificationRecordSpecialKookminFirstLife.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminFirstLife.getModifiedDate();
    }
}
