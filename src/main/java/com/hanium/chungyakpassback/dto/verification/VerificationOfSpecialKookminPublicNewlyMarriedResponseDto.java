package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfpecialKookminNewlyMarried;
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
    public VerificationOfSpecialKookminPublicNewlyMarriedResponseDto(VerificationOfpecialKookminNewlyMarried verificationOfpecialKookminNewlyMarried) {
        this.americanAge = verificationOfpecialKookminNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfpecialKookminNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfpecialKookminNewlyMarried.isAccountTf();
        this.meetRecipientTf = verificationOfpecialKookminNewlyMarried.isMeetRecipientTf();
        this.hasMinorChildren = verificationOfpecialKookminNewlyMarried.isHasMinorChildren();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfpecialKookminNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfpecialKookminNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationOfpecialKookminNewlyMarried.isMeetPropertyTf();
        this.secondChungyak = verificationOfpecialKookminNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationOfpecialKookminNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfpecialKookminNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationOfpecialKookminNewlyMarried.isHouseholderTf();
        this.restrictedAreaTf = verificationOfpecialKookminNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfpecialKookminNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfpecialKookminNewlyMarried.isMeetNumberOfPaymentsTf();
        this.verificationRecordSpecialKookminNewlyMarriedId = verificationOfpecialKookminNewlyMarried.getId();
        this.sibilingSupportYn = verificationOfpecialKookminNewlyMarried.getSibilingSupportYn();
        this.preNewMarriedYn = verificationOfpecialKookminNewlyMarried.getPreNewMarriedYn();
        this.kookminType = verificationOfpecialKookminNewlyMarried.getKookminType();
        this.ranking = verificationOfpecialKookminNewlyMarried.getRanking();
        this.createdDate = verificationOfpecialKookminNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationOfpecialKookminNewlyMarried.getModifiedDate();
    }
}
