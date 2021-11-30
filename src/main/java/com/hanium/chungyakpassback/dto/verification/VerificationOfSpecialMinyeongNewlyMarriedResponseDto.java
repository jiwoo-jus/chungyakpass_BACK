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

    Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    boolean meetMonthlyAverageIncomePriorityTf; //월평균소득기준충족여부_우선공급
    boolean meetMonthlyAverageIncomeGeneralTf; //월평균소득기준충족여부_일반공급
    boolean meetMarriagePeriodIn7yearsTf; //혼인기간7년이내충족여부
    boolean hasMinorChildren; //미성년자녀존재여부
    boolean secondChungyak; //2순위청약신청대상여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetDepositTf; //예치금액충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일


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
        this.restrictedAreaTf = verificationOfSpecialMinyeongNewlyMarried.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongNewlyMarried.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationOfSpecialMinyeongNewlyMarried.isMeetDepositTf();
        this.id = verificationOfSpecialMinyeongNewlyMarried.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongNewlyMarried.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongNewlyMarried.getRanking();
        this.createdDate = verificationOfSpecialMinyeongNewlyMarried.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongNewlyMarried.getModifiedDate();
    }
}
