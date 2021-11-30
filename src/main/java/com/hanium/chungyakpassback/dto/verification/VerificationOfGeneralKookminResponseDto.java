package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfGeneralKookmin;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfGeneralKookminResponseDto {

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Yn twentiesSoleHouseHolderYn; //20대단독세대주여부
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    @Builder
    public VerificationOfGeneralKookminResponseDto(VerificationOfGeneralKookmin verificationOfGeneralKookmin) {
        this.americanAge = verificationOfGeneralKookmin.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfGeneralKookmin.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfGeneralKookmin.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationOfGeneralKookmin.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationOfGeneralKookmin.isHouseholderTf();
        this.restrictedAreaTf = verificationOfGeneralKookmin.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfGeneralKookmin.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfGeneralKookmin.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationOfGeneralKookmin.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationOfGeneralKookmin.isMeetNumberOfPaymentsTf();
        this.id = verificationOfGeneralKookmin.getId();
        this.sibilingSupportYn = verificationOfGeneralKookmin.getSibilingSupportYn();
        this.twentiesSoleHouseHolderYn = verificationOfGeneralKookmin.getTwentiesSoleHouseHolderYn();
        this.ranking = verificationOfGeneralKookmin.getRanking();
        this.createdDate = verificationOfGeneralKookmin.getCreatedDate();
        this.modifiedDate = verificationOfGeneralKookmin.getModifiedDate();
    }

}
