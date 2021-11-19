package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminMultiChild;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicMultiChildResponseDto {
    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetMonthlyAverageIncomeTf;
    boolean meetPropertyTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    Integer calcMinorChildren;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetBankbookJoinPeriodTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordSpecialKookminMultiChildId;

    @Builder
    public SpecialKookminPublicMultiChildResponseDto(VerificationRecordSpecialKookminMultiChild verificationRecordSpecialKookminMultiChild) {
        this.americanAge = verificationRecordSpecialKookminMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminMultiChild.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationRecordSpecialKookminMultiChild.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationRecordSpecialKookminMultiChild.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationRecordSpecialKookminMultiChild.getCalcMinorChildren();
        this.householderTf = verificationRecordSpecialKookminMultiChild.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordSpecialKookminMultiChild.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminMultiChild.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminMultiChildId = verificationRecordSpecialKookminMultiChild.getId();
        this.createdDate = verificationRecordSpecialKookminMultiChild.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminMultiChild.getModifiedDate();
    }
}
