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

    Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    boolean meetOldParentSupportMore3yearsTf; //3년이상노부모부양충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean householderTf; //세대주여부
    boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetDepositTf; //예치금액충족여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

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
        this.restrictedAreaTf = verificationOfSpecialMinyeongOldParent.isRestrictedAreaTf();
        this.meetDepositTf = verificationOfSpecialMinyeongOldParent.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongOldParent.isMeetBankbookJoinPeriodTf();
        this.id = verificationOfSpecialMinyeongOldParent.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongOldParent.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongOldParent.getRanking();
        this.createdDate = verificationOfSpecialMinyeongOldParent.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongOldParent.getModifiedDate();
    }
}
