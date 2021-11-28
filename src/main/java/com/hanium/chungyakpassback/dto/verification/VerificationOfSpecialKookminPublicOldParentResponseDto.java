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

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetMonthlyAverageIncomeTf;
    private boolean meetPropertyTf;
    private boolean meetOldParentSupportMore3yearsTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetNumberOfPaymentsTf;
    private Yn sibilingSupportYn;
    private KookminType kookminType;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

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
