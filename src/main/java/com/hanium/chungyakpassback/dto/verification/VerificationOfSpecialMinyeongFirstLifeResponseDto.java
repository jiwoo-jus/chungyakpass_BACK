package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongFirstLife;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongFirstLifeResponseDto {

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
    public VerificationOfSpecialMinyeongFirstLifeResponseDto(VerificationOfSpecialMinyeongFirstLife verificationOfSpecialMinyeongFirstLife) {
        this.americanAge = verificationOfSpecialMinyeongFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialMinyeongFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialMinyeongFirstLife.isAccountTf();
        this.meetRecipientTf = verificationOfSpecialMinyeongFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfSpecialMinyeongFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialMinyeongFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationOfSpecialMinyeongFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfSpecialMinyeongFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialMinyeongFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.restrictedAreaTf = verificationOfSpecialMinyeongFirstLife.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongFirstLife.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationOfSpecialMinyeongFirstLife.isMeetDepositTf();
        this.verificationRecordSpecialMinyeongFirstLifeId = verificationOfSpecialMinyeongFirstLife.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationOfSpecialMinyeongFirstLife.getTaxOver5yearsYn();
        this.firstRankHistoryYn = verificationOfSpecialMinyeongFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationOfSpecialMinyeongFirstLife.getRanking();
        this.createdDate = verificationOfSpecialMinyeongFirstLife.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongFirstLife.getModifiedDate();
    }
}
