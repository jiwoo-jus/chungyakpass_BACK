package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminMultiChild;
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
public class VerificationOfSpecialKookminPublicMultiChildResponseDto {

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetMonthlyAverageIncomeTf; //월평균소득기준충족여부
    private boolean meetPropertyTf; //자산기준충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private Integer calcMinorChildren; //미성년자녀수계산(태아 포함)
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private KookminType kookminType; //국민주택종류
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일


    @Builder
    public VerificationOfSpecialKookminPublicMultiChildResponseDto(VerificationOfSpecialKookminMultiChild verificationOfSpecialKookminMultiChild) {
        this.americanAge = verificationOfSpecialKookminMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminMultiChild.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationOfSpecialKookminMultiChild.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationOfSpecialKookminMultiChild.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationOfSpecialKookminMultiChild.getCalcMinorChildren();
        this.householderTf = verificationOfSpecialKookminMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialKookminMultiChild.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminMultiChild.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfSpecialKookminMultiChild.isMeetNumberOfPaymentsTf();
        this.id = verificationOfSpecialKookminMultiChild.getId();
        this.sibilingSupportYn = verificationOfSpecialKookminMultiChild.getSibilingSupportYn();
        this.kookminType = verificationOfSpecialKookminMultiChild.getKookminType();
        this.ranking = verificationOfSpecialKookminMultiChild.getRanking();
        this.createdDate = verificationOfSpecialKookminMultiChild.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminMultiChild.getModifiedDate();
    }
}
