package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialMinyeongFirstLife;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongFirstLifeResponseDto {

    private Long verificationRecordSpecialMinyeongFirstLifeId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetRecipientTf;
    private boolean meetMonthlyAverageIncomePriorityTf;
    private boolean meetMonthlyAverageIncomeGeneralTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean householderTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean restrictedAreaTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetDepositTf;
    private Yn sibilingSupportYn;
    private Yn taxOver5yearsYn;
    private Yn firstRankHistoryYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public SpecialMinyeongFirstLifeResponseDto(VerificationRecordSpecialMinyeongFirstLife verificationRecordSpecialMinyeongFirstLife) {
        this.americanAge = verificationRecordSpecialMinyeongFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialMinyeongFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialMinyeongFirstLife.isAccountTf();
        this.meetRecipientTf = verificationRecordSpecialMinyeongFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialMinyeongFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialMinyeongFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialMinyeongFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialMinyeongFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.restrictedAreaTf = verificationRecordSpecialMinyeongFirstLife.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialMinyeongFirstLife.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationRecordSpecialMinyeongFirstLife.isMeetDepositTf();
        this.verificationRecordSpecialMinyeongFirstLifeId = verificationRecordSpecialMinyeongFirstLife.getId();
        this.sibilingSupportYn = verificationRecordSpecialMinyeongFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationRecordSpecialMinyeongFirstLife.getTaxOver5yearsYn();
        this.firstRankHistoryYn = verificationRecordSpecialMinyeongFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationRecordSpecialMinyeongFirstLife.getRanking();
        this.createdDate = verificationRecordSpecialMinyeongFirstLife.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialMinyeongFirstLife.getModifiedDate();
    }
}
