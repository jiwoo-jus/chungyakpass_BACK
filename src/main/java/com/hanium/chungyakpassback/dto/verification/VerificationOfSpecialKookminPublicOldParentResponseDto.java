package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminOldParent;
import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialKookminPublicOldParentResponseDto {

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetMonthlyAverageIncomeTf; //월평균소득기준충족여부
    private boolean meetPropertyTf; //자산기준충족여부
    private boolean meetOldParentSupportMore3yearsTf; //3년이상노부모부양충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private KookminType kookminType; //국민주택종류
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    @Builder
    public VerificationOfSpecialKookminPublicOldParentResponseDto(VerificationOfSpecialKookminOldParent verificationOfSpecialKookminOldParent) {
        this.americanAge = verificationOfSpecialKookminOldParent.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminOldParent.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminOldParent.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationOfSpecialKookminOldParent.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationOfSpecialKookminOldParent.isMeetPropertyTf();
        this.meetOldParentSupportMore3yearsTf = verificationOfSpecialKookminOldParent.isMeetOldParentSupportMore3yearsTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminOldParent.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationOfSpecialKookminOldParent.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialKookminOldParent.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfSpecialKookminOldParent.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminOldParent.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminOldParent.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfSpecialKookminOldParent.isMeetNumberOfPaymentsTf();
        this.id = verificationOfSpecialKookminOldParent.getId();
        this.sibilingSupportYn = verificationOfSpecialKookminOldParent.getSibilingSupportYn();
        this.kookminType = verificationOfSpecialKookminOldParent.getKookminType();
        this.ranking = verificationOfSpecialKookminOldParent.getRanking();
        this.createdDate = verificationOfSpecialKookminOldParent.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminOldParent.getModifiedDate();
    }
}
