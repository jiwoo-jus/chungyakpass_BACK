package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminFirstLife;
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
public class SpecialKookminPublicFirstLifeResponseDto {

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


    public SpecialKookminPublicFirstLifeResponseDto(VerificationRecordSpecialKookminFirstLife verificationRecordSpecialKookminFirstLife) {
        this.americanAge = verificationRecordSpecialKookminFirstLife.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminFirstLife.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminFirstLife.isAccountTf();
        this.meetRecipientTf = verificationRecordSpecialKookminFirstLife.isMeetRecipient();
        this.meetMonthlyAverageIncomePriorityTf = verificationRecordSpecialKookminFirstLife.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationRecordSpecialKookminFirstLife.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationRecordSpecialKookminFirstLife.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminFirstLife.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordSpecialKookminFirstLife.isHouseholderTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordSpecialKookminFirstLife.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminFirstLife.isMeetAllHouseMemberRewinningRestrictionTf();
        this.restrictedAreaTf = verificationRecordSpecialKookminFirstLife.isRestrictedAreaTf();
        this.meetNumberOfPaymentsTf = verificationRecordSpecialKookminFirstLife.isMeetNumberOfPaymentsTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminFirstLife.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminFirstLifeId = verificationRecordSpecialKookminFirstLife.getId();
        this.createdDate = verificationRecordSpecialKookminFirstLife.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminFirstLife.getModifiedDate();
        this.sibilingSupportYn = verificationRecordSpecialKookminFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationRecordSpecialKookminFirstLife.getTaxOver5yearsYn();
        this.kookminType = verificationRecordSpecialKookminFirstLife.getKookminType();
        this.firstRankHistoryYn = verificationRecordSpecialKookminFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationRecordSpecialKookminFirstLife.getRanking();
    }
}
