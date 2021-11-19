package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongMultiChild;
import lombok.*;

import java.time.LocalDateTime;

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
    boolean isRestrictedAreaTf;
    boolean meetHouseHavingLessThan2AptTf;
    boolean isPriorityApt;
    boolean meetDepositTf;
    boolean meetBankbookJoinPeriodTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordSpecialMinyeongMultiChildId;

    @Builder
    public SpecialMinyeongMultiChildResponseDto(VerificationRecordSpecialMinyeongMultiChild verificationRecordSpecialMinyeongMultiChild) {
        this.americanAge = verificationRecordSpecialMinyeongMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongMultiChild.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationRecordSpecialMinyeongMultiChild.getCalcMinorChildren();
        this.householderTf = verificationRecordSpecialMinyeongMultiChild.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordSpecialMinyeongMultiChild.isRestrictedAreaTf();
        this.meetHouseHavingLessThan2AptTf = verificationRecordSpecialMinyeongMultiChild.isMeetHouseHavingLessThan2AptTf();
        this.isPriorityApt = verificationRecordSpecialMinyeongMultiChild.isPriorityApt();
        this.meetDepositTf = verificationRecordSpecialMinyeongMultiChild.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongMultiChild.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialMinyeongMultiChildId = verificationRecordSpecialMinyeongMultiChild.getId();
        this.createdDate = verificationRecordSpecialMinyeongMultiChild.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongMultiChild.getModifiedDate();
    }
}
