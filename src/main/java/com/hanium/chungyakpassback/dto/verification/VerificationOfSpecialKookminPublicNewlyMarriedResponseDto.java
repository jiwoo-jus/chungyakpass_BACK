package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminNewlyMarried;
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
public class VerificationOfSpecialKookminPublicNewlyMarriedResponseDto {

    private Long verificationRecordSpecialKookminNewlyMarriedId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetRecipientTf;
    private boolean hasMinorChildren;
    private boolean meetMonthlyAverageIncomePriorityTf;
    private boolean meetMonthlyAverageIncomeGeneralTf;
    private boolean meetPropertyTf;
    private boolean secondChungyak;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetNumberOfPaymentsTf;
    private Yn sibilingSupportYn;
    private Yn preNewMarriedYn;
    private KookminType kookminType;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Builder
    public VerificationOfSpecialKookminPublicNewlyMarriedResponseDto(VerificationOfSpecialKookminNewlyMarried verificationOfSpecialKookminNewlyMarried) {
        this.americanAge = verificationOfSpecialKookminNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminNewlyMarried.isAccountTf();
        this.meetRecipientTf = verificationOfSpecialKookminNewlyMarried.isMeetRecipientTf();
        this.hasMinorChildren = verificationOfSpecialKookminNewlyMarried.isHasMinorChildren();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationOfSpecialKookminNewlyMarried.isMeetPropertyTf();
        this.secondChungyak = verificationOfSpecialKookminNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationOfSpecialKookminNewlyMarried.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialKookminNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfSpecialKookminNewlyMarried.isMeetNumberOfPaymentsTf();
        this.verificationRecordSpecialKookminNewlyMarriedId = verificationOfSpecialKookminNewlyMarried.getId();
        this.sibilingSupportYn = verificationOfSpecialKookminNewlyMarried.getSibilingSupportYn();
        this.preNewMarriedYn = verificationOfSpecialKookminNewlyMarried.getPreNewMarriedYn();
        this.kookminType = verificationOfSpecialKookminNewlyMarried.getKookminType();
        this.ranking = verificationOfSpecialKookminNewlyMarried.getRanking();
        this.createdDate = verificationOfSpecialKookminNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminNewlyMarried.getModifiedDate();
    }
}
