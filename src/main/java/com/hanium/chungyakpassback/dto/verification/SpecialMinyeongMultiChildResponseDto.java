package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialMinyeongMultiChild;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongMultiChildResponseDto {

    private Long verificationRecordSpecialMinyeongMultiChildId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private Integer calcMinorChildren;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetHouseHavingLessThan2AptTf;
    private boolean priorityApt;
    private boolean meetDepositTf;
    private boolean meetBankbookJoinPeriodTf;
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public SpecialMinyeongMultiChildResponseDto(VerificationRecordSpecialMinyeongMultiChild verificationRecordSpecialMinyeongMultiChild) {
        this.americanAge = verificationRecordSpecialMinyeongMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongMultiChild.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationRecordSpecialMinyeongMultiChild.getCalcMinorChildren();
        this.householderTf = verificationRecordSpecialMinyeongMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordSpecialMinyeongMultiChild.isRestrictedAreaTf();
        this.meetHouseHavingLessThan2AptTf = verificationRecordSpecialMinyeongMultiChild.isMeetHouseHavingLessThan2AptTf();
        this.priorityApt = verificationRecordSpecialMinyeongMultiChild.isPriorityApt();
        this.meetDepositTf = verificationRecordSpecialMinyeongMultiChild.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongMultiChild.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialMinyeongMultiChildId = verificationRecordSpecialMinyeongMultiChild.getId();
        this.sibilingSupportYn = verificationRecordSpecialMinyeongMultiChild.getSibilingSupportYn();
        this.ranking = verificationRecordSpecialMinyeongMultiChild.getRanking();
        this.createdDate = verificationRecordSpecialMinyeongMultiChild.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongMultiChild.getModifiedDate();
    }
}
