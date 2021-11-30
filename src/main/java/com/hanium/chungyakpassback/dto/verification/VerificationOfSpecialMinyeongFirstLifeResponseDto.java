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

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetRecipientTf; //생애최초대상자충족여부
    private boolean meetMonthlyAverageIncomePriorityTf; //월평균소득기준충족여부_우선공급
    private boolean meetMonthlyAverageIncomeGeneralTf; //월평균소득기준충족여부_일반공급
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean householderTf; //세대주여부
    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetDepositTf; //예치금액충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Yn taxOver5yearsYn; //5년이상소득세납부여부
    private Yn firstRankHistoryYn; //일반공급1순위당첨이력
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

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
        this.id = verificationOfSpecialMinyeongFirstLife.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongFirstLife.getSibilingSupportYn();
        this.taxOver5yearsYn = verificationOfSpecialMinyeongFirstLife.getTaxOver5yearsYn();
        this.firstRankHistoryYn = verificationOfSpecialMinyeongFirstLife.getFirstRankHistoryYn();
        this.ranking = verificationOfSpecialMinyeongFirstLife.getRanking();
        this.createdDate = verificationOfSpecialMinyeongFirstLife.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongFirstLife.getModifiedDate();
    }
}
