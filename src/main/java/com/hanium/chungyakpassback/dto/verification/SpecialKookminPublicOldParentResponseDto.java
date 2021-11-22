package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminOldParent;
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
public class SpecialKookminPublicOldParentResponseDto {

    private Long verificationRecordSpecialKookminOldParentId;

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
    public SpecialKookminPublicOldParentResponseDto(VerificationRecordSpecialKookminOldParent verificationRecordSpecialKookminOldParent) {
        this.americanAge = verificationRecordSpecialKookminOldParent.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminOldParent.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminOldParent.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationRecordSpecialKookminOldParent.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationRecordSpecialKookminOldParent.isMeetPropertyTf();
        this.meetOldParentSupportMore3yearsTf = verificationRecordSpecialKookminOldParent.isMeetOldParentSupportMore3yearsTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminOldParent.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialKookminOldParent.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordSpecialKookminOldParent.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialKookminOldParent.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminOldParent.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminOldParent.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationRecordSpecialKookminOldParent.isMeetNumberOfPaymentsTf();
        this.verificationRecordSpecialKookminOldParentId = verificationRecordSpecialKookminOldParent.getId();
        this.sibilingSupportYn = verificationRecordSpecialKookminOldParent.getSibilingSupportYn();
        this.kookminType = verificationRecordSpecialKookminOldParent.getKookminType();
        this.ranking = verificationRecordSpecialKookminOldParent.getRanking();
        this.createdDate = verificationRecordSpecialKookminOldParent.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminOldParent.getModifiedDate();
    }
}
