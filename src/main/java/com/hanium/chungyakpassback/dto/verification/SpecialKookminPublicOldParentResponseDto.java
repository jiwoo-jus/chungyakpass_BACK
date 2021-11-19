package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminOldParent;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicOldParentResponseDto {

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetMonthlyAverageIncomeTf;
    boolean meetPropertyTf;
    boolean meetOldParentSupportMore3yearsTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetNumberOfPaymentsTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordSpecialKookminOldParentId;

    @Builder
    public SpecialKookminPublicOldParentResponseDto(VerificationRecordSpecialKookminOldParent verificationRecordSpecialKookminOldParent) {
        this.americanAge = verificationRecordSpecialKookminOldParent.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminOldParent.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminOldParent.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationRecordSpecialKookminOldParent.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationRecordSpecialKookminOldParent.isMeetPropertyTf();
        this.meetOldParentSupportMore3yearsTf = verificationRecordSpecialKookminOldParent.isMeetOldParentSupportMore3yearsTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminOldParent.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialKookminOldParent.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordSpecialKookminOldParent.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialKookminOldParent.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminOldParent.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminOldParent.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationRecordSpecialKookminOldParent.isMeetNumberOfPaymentsTf();
        this.verificationRecordSpecialKookminOldParentId = verificationRecordSpecialKookminOldParent.getId();
        this.createdDate = verificationRecordSpecialKookminOldParent.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminOldParent.getModifiedDate();
    }
}
