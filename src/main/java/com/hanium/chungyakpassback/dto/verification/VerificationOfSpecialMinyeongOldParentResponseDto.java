package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongOldParent;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongOldParentResponseDto {

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
    public VerificationOfSpecialMinyeongOldParentResponseDto(VerificationOfSpecialMinyeongOldParent verificationOfSpecialMinyeongOldParent) {
        this.americanAge = verificationOfSpecialMinyeongOldParent.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialMinyeongOldParent.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialMinyeongOldParent.isAccountTf();
        this.meetOldParentSupportMore3yearsTf = verificationOfSpecialMinyeongOldParent.isMeetOldParentSupportMore3yearsTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialMinyeongOldParent.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationOfSpecialMinyeongOldParent.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfSpecialMinyeongOldParent.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialMinyeongOldParent.isMeetAllHouseMemberRewinningRestrictionTf();
        this.isRestrictedAreaTf = verificationOfSpecialMinyeongOldParent.isRestrictedAreaTf();
        this.meetDepositTf = verificationOfSpecialMinyeongOldParent.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongOldParent.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialMinyeongOldParentId = verificationOfSpecialMinyeongOldParent.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongOldParent.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongOldParent.getRanking();
        this.createdDate = verificationOfSpecialMinyeongOldParent.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongOldParent.getModifiedDate();
    }
}
