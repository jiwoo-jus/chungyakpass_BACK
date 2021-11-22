package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialMinyeongOldParent;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongOldParentResponseDto {

    Long verificationRecordSpecialMinyeongOldParentId;

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetOldParentSupportMore3yearsTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean isRestrictedAreaTf;
    boolean meetDepositTf;
    boolean meetBankbookJoinPeriodTf;
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Builder
    public SpecialMinyeongOldParentResponseDto(VerificationRecordSpecialMinyeongOldParent verificationRecordSpecialMinyeongOldParent) {
        this.americanAge = verificationRecordSpecialMinyeongOldParent.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongOldParent.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongOldParent.isAccountTf();
        this.meetOldParentSupportMore3yearsTf = verificationRecordSpecialMinyeongOldParent.isMeetOldParentSupportMore3yearsTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongOldParent.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialMinyeongOldParent.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialMinyeongOldParent.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongOldParent.isMeetAllHouseMemberRewinningRestrictionTf();
        this.isRestrictedAreaTf = verificationRecordSpecialMinyeongOldParent.isRestrictedAreaTf();
        this.meetDepositTf = verificationRecordSpecialMinyeongOldParent.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongOldParent.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialMinyeongOldParentId = verificationRecordSpecialMinyeongOldParent.getId();
        this.sibilingSupportYn = verificationRecordSpecialMinyeongOldParent.getSibilingSupportYn();
        this.ranking = verificationRecordSpecialMinyeongOldParent.getRanking();
        this.createdDate = verificationRecordSpecialMinyeongOldParent.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongOldParent.getModifiedDate();
    }
}
