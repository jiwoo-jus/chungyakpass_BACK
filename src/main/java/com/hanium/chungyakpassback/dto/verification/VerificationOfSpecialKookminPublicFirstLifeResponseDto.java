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

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetRecipientTf; //생애최초대상자충족여부
    private boolean meetMonthlyAverageIncomePriorityTf; //월평균소득기준충족여부_우선공급
    private boolean meetMonthlyAverageIncomeGeneralTf; //월평균소득기준충족여부_일반공급
    private boolean meetPropertyTf; //자산기준충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean householderTf; //세대주여부
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Yn taxOver5yearsYn; //5년이상소득세납부여부
    private KookminType kookminType; //국민주택종류
    private Yn firstRankHistoryYn; //일반공급1순위당첨이력
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일


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
        this.id = verificationOfSpecialKookminFirstLife.getId();
        this.createdDate = verificationOfSpecialKookminFirstLife.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminFirstLife.getModifiedDate();
        this.sibilingSupportYn = verificationOfSpecialKookminFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationOfSpecialKookminFirstLife.getTaxOver5yearsYn();
        this.kookminType = verificationOfSpecialKookminFirstLife.getKookminType();
        this.firstRankHistoryYn = verificationOfSpecialKookminFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationOfSpecialKookminFirstLife.getRanking();
    }
}
