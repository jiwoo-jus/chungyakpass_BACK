package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminNewlyMarried;
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
public class SpecialKookminNewlyMarriedResponseDto {

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
    public SpecialKookminNewlyMarriedResponseDto(VerificationRecordSpecialKookminNewlyMarried verificationRecordSpecialKookminNewlyMarried) {
        this.americanAge = verificationRecordSpecialKookminNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminNewlyMarried.isAccountTf();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetMarriagePeriodIn7yearsTf = verificationRecordSpecialKookminNewlyMarried.isMeetMarriagePeriodIn7yearsTf();
        this.hasMinorChildren = verificationRecordSpecialKookminNewlyMarried.isHasMinorChildren();
        this.secondChungyak = verificationRecordSpecialKookminNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationRecordSpecialKookminNewlyMarried.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordSpecialKookminNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationRecordSpecialKookminNewlyMarried.isMeetNumberOfPaymentsTf();
        this.verificationRecordSpecialKookminNewlyMarriedId = verificationRecordSpecialKookminNewlyMarried.getId();
        this.sibilingSupportYn = verificationRecordSpecialKookminNewlyMarried.getSibilingSupportYn();
        this.preNewMarriedYn = verificationRecordSpecialKookminNewlyMarried.getPreNewMarriedYn();
        this.kookminType = verificationRecordSpecialKookminNewlyMarried.getKookminType();
        this.ranking = verificationRecordSpecialKookminNewlyMarried.getRanking();
        this.createdDate = verificationRecordSpecialKookminNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminNewlyMarried.getModifiedDate();
    }
}
