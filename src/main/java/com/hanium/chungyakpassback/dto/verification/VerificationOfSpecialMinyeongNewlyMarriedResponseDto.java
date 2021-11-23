package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongNewlyMarried;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongNewlyMarriedResponseDto {

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
    public VerificationOfSpecialMinyeongNewlyMarriedResponseDto(VerificationOfSpecialMinyeongNewlyMarried verificationOfSpecialMinyeongNewlyMarried) {
        this.americanAge = verificationOfSpecialMinyeongNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialMinyeongNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialMinyeongNewlyMarried.isAccountTf();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfSpecialMinyeongNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfSpecialMinyeongNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetMarriagePeriodIn7yearsTf = verificationOfSpecialMinyeongNewlyMarried.isMeetMarriagePeriodIn7yearsTf();
        this.hasMinorChildren = verificationOfSpecialMinyeongNewlyMarried.isHasMinorChildren();
        this.secondChungyak = verificationOfSpecialMinyeongNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialMinyeongNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialMinyeongNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationOfSpecialMinyeongNewlyMarried.isHouseholderTf();
        this.isRestrictedAreaTf = verificationOfSpecialMinyeongNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationOfSpecialMinyeongNewlyMarried.isMeetDepositTf();
        this.verificationRecordSpecialMinyeongNewlyMarriedId = verificationOfSpecialMinyeongNewlyMarried.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongNewlyMarried.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongNewlyMarried.getRanking();
        this.createdDate = verificationOfSpecialMinyeongNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongNewlyMarried.getModifiedDate();
    }
}
