package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminNewlyMarried;
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
public class VerificationOfSpecialKookminPublicNewlyMarriedResponseDto {

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetRecipientTf; //신혼부부공공주택적용국민주택대상자충족여부
    private boolean hasMinorChildren; //미성년자녀존재여부
    private boolean meetMonthlyAverageIncomePriorityTf; //월평균소득기준충족여부_우선공급
    private boolean meetMonthlyAverageIncomeGeneralTf; //월평균소득기준충족여부_일반공급
    private boolean meetPropertyTf; //자산기준충족여부
    private boolean secondChungyak; //2순위청약신청대상여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Yn preNewMarriedYn; //예비신혼부부여부
    private KookminType kookminType; //국민주택종류
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일


    @Builder
    public VerificationOfSpecialKookminPublicNewlyMarriedResponseDto(VerificationOfSpecialKookminNewlyMarried verificationOfSpecialKookminNewlyMarried) {
        this.americanAge = verificationOfSpecialKookminNewlyMarried.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminNewlyMarried.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminNewlyMarried.isAccountTf();
        this.meetRecipientTf = verificationOfSpecialKookminNewlyMarried.isMeetRecipientTf();
        this.hasMinorChildren = verificationOfSpecialKookminNewlyMarried.isHasMinorChildren();
        this.meetMonthlyAverageIncomePriorityTf = verificationOfSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomePriority();
        this.meetMonthlyAverageIncomeGeneralTf = verificationOfSpecialKookminNewlyMarried.isMeetMonthlyAverageIncomeGeneral();
        this.meetPropertyTf = verificationOfSpecialKookminNewlyMarried.isMeetPropertyTf();
        this.secondChungyak = verificationOfSpecialKookminNewlyMarried.isSecondChungyak();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminNewlyMarried.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminNewlyMarried.isMeetAllHouseMemberRewinningRestrictionTf();
        this.householderTf = verificationOfSpecialKookminNewlyMarried.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialKookminNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfSpecialKookminNewlyMarried.isMeetNumberOfPaymentsTf();
        this.id = verificationOfSpecialKookminNewlyMarried.getId();
        this.sibilingSupportYn = verificationOfSpecialKookminNewlyMarried.getSibilingSupportYn();
        this.preNewMarriedYn = verificationOfSpecialKookminNewlyMarried.getPreNewMarriedYn();
        this.kookminType = verificationOfSpecialKookminNewlyMarried.getKookminType();
        this.ranking = verificationOfSpecialKookminNewlyMarried.getRanking();
        this.createdDate = verificationOfSpecialKookminNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminNewlyMarried.getModifiedDate();
    }
}
