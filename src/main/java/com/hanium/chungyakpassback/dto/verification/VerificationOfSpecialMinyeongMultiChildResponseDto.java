package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongMultiChild;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongMultiChildResponseDto {

    private Long id;

    private Integer americanAge; //만나이
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private boolean meetHomelessHouseholdMembersTf; //전세대원무주택구성원충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private Integer calcMinorChildren; //미성년자녀수계산(태아 포함)
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetHouseHavingLessThan2AptTf;
    private boolean priorityApt; //주거전용85초과공공건설임대주택,수도권에지정된공공주택에서공급하는민영주택청약여부
    private boolean meetDepositTf; //예치금액충족여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    @Builder
    public VerificationOfSpecialMinyeongMultiChildResponseDto(VerificationOfSpecialMinyeongMultiChild verificationOfSpecialMinyeongMultiChild) {
        this.americanAge = verificationOfSpecialMinyeongMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialMinyeongMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialMinyeongMultiChild.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialMinyeongMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialMinyeongMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationOfSpecialMinyeongMultiChild.getCalcMinorChildren();
        this.householderTf = verificationOfSpecialMinyeongMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialMinyeongMultiChild.isRestrictedAreaTf();
        this.meetHouseHavingLessThan2AptTf = verificationOfSpecialMinyeongMultiChild.isMeetHouseHavingLessThan2AptTf();
        this.priorityApt = verificationOfSpecialMinyeongMultiChild.isPriorityApt();
        this.meetDepositTf = verificationOfSpecialMinyeongMultiChild.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongMultiChild.isMeetBankbookJoinPeriodTf();
        this.id = verificationOfSpecialMinyeongMultiChild.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongMultiChild.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongMultiChild.getRanking();
        this.createdDate = verificationOfSpecialMinyeongMultiChild.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongMultiChild.getModifiedDate();
    }
}
