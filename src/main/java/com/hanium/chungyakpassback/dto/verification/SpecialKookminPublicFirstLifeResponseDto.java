package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminFirstLife;
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

    Long verificationRecordSpecialKookminFirstLifeId;

    Integer americanAge;
    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    boolean meetRecipientTf;
    boolean meetMonthlyAverageIncomePriorityTf;
    boolean meetMonthlyAverageIncomeGeneralTf;
    boolean meetPropertyTf;
    boolean meetHomelessHouseholdMembersTf;
    boolean householderTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean isRestrictedAreaTf;
    boolean meetNumberOfPaymentsTf;
    boolean meetBankbookJoinPeriodTf;
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
        this.isRestrictedAreaTf = verificationRecordSpecialKookminFirstLife.isRestrictedAreaTf();
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
