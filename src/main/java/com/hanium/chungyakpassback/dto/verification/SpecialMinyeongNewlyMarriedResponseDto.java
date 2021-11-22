package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialMinyeongNewlyMarried;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongNewlyMarriedResponseDto {

    Long verificationRecordSpecialMinyeongNewlyMarriedId;

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetMonthlyAverageIncomePriorityTf;
    boolean meetMonthlyAverageIncomeGeneralTf;
    boolean meetMarriagePeriodIn7yearsTf;
    boolean hasMinorChildren;
    boolean secondChungyak;
    boolean meetHomelessHouseholdMembersTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetDepositTf;
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Builder
    public SpecialMinyeongNewlyMarriedResponseDto(VerificationRecordSpecialMinyeongNewlyMarried verificationRecordSpecialMinyeongNewlyMarried) {
        this.americanAge = verificationRecordSpecialMinyeongNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongNewlyMarried.isAccountTf();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetMarriagePeriodIn7yearsTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetMarriagePeriodIn7yearsTf();
        this.hasMinorChildren = verificationRecordSpecialMinyeongNewlyMarried.isHasMinorChildren();
        this.secondChungyak = verificationRecordSpecialMinyeongNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationRecordSpecialMinyeongNewlyMarried.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordSpecialMinyeongNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationRecordSpecialMinyeongNewlyMarried.isMeetDepositTf();
        this.verificationRecordSpecialMinyeongNewlyMarriedId = verificationRecordSpecialMinyeongNewlyMarried.getId();
        this.sibilingSupportYn = verificationRecordSpecialMinyeongNewlyMarried.getSibilingSupportYn();
        this.ranking = verificationRecordSpecialMinyeongNewlyMarried.getRanking();
        this.createdDate = verificationRecordSpecialMinyeongNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongNewlyMarried.getModifiedDate();
    }
}
