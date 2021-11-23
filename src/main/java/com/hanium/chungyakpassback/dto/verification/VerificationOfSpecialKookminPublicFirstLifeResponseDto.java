package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminFirstLife;
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
public class VerificationOfSpecialKookminPublicFirstLifeResponseDto {

    private Long verificationRecordSpecialKookminFirstLifeId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetRecipientTf;
    private boolean meetMonthlyAverageIncomePriorityTf;
    private boolean meetMonthlyAverageIncomeGeneralTf;
    private boolean meetPropertyTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean householderTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean restrictedAreaTf;
    private boolean meetNumberOfPaymentsTf;
    private boolean meetBankbookJoinPeriodTf;
    private Yn sibilingSupportYn;
    private Yn taxOver5yearsYn;
    private KookminType kookminType;
    private Yn firstRankHistoryYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    public VerificationOfSpecialKookminPublicFirstLifeResponseDto(VerificationOfSpecialKookminFirstLife verificationOfSpecialKookminFirstLife) {
        this.americanAge = verificationOfSpecialKookminFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminFirstLife.isAccountTf();
        this.meetRecipientTf = verificationOfSpecialKookminFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfSpecialKookminFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfSpecialKookminFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationOfSpecialKookminFirstLife.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationOfSpecialKookminFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfSpecialKookminFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.restrictedAreaTf = verificationOfSpecialKookminFirstLife.isRestrictedAreaTf();
        this.meetNumberOfPaymentsTf = verificationOfSpecialKookminFirstLife.isMeetNumberOfPaymentsTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminFirstLife.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminFirstLifeId = verificationOfSpecialKookminFirstLife.getId();
        this.createdDate = verificationOfSpecialKookminFirstLife.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminFirstLife.getModifiedDate();
        this.sibilingSupportYn = verificationOfSpecialKookminFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationOfSpecialKookminFirstLife.getTaxOver5yearsYn();
        this.kookminType = verificationOfSpecialKookminFirstLife.getKookminType();
        this.firstRankHistoryYn = verificationOfSpecialKookminFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationOfSpecialKookminFirstLife.getRanking();
    }
}
