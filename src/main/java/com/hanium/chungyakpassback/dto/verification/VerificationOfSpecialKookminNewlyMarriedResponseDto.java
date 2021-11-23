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
public class VerificationOfSpecialKookminNewlyMarriedResponseDto {

    private Long verificationRecordSpecialKookminNewlyMarriedId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetMonthlyAverageIncomePriorityTf;
    private boolean meetMonthlyAverageIncomeGeneralTf;
    private boolean meetMarriagePeriodIn7yearsTf;
    private boolean hasMinorChildren;
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
    public VerificationOfSpecialKookminNewlyMarriedResponseDto(VerificationOfSpecialKookminNewlyMarried verificationOfpecialKookminNewlyMarried) {
        this.americanAge = verificationOfpecialKookminNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfpecialKookminNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfpecialKookminNewlyMarried.isAccountTf();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfpecialKookminNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfpecialKookminNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetMarriagePeriodIn7yearsTf = verificationOfpecialKookminNewlyMarried.isMeetMarriagePeriodIn7yearsTf();
        this.hasMinorChildren = verificationOfpecialKookminNewlyMarried.isHasMinorChildren();
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
