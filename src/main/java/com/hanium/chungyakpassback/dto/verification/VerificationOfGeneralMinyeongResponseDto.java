package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfGeneralMinyeong;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfGeneralMinyeongResponseDto {

    private Long id;

    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
    private boolean accountTf; //청약통장유형조건충족여부
    private Integer americanAge; //만나이
    private boolean householderTf; //세대주여부
    private boolean restrictedAreaTf; //규제지역여부
    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
    private boolean meetHouseHavingLessThan2AptTf; //소유주택2개미만세대충족여부
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
    private boolean meetDepositTf; //예치금액충족여부
    private boolean priorityApt; //주거전용85초과공공건설임대주택,수도권에지정된공공주택에서공급하는민영주택청약여부
    private Yn sibilingSupportYn; //형제자매부양여부
    private Ranking ranking; //순위
    private LocalDateTime createdDate; //생성일
    private LocalDateTime modifiedDate; //수정일

    public VerificationOfGeneralMinyeongResponseDto(VerificationOfGeneralMinyeong verificationOfGeneralMinyeong) {
        this.id = verificationOfGeneralMinyeong.getId();
        this.meetLivingInSurroundAreaTf = verificationOfGeneralMinyeong.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfGeneralMinyeong.isAccountTf();
        this.americanAge = verificationOfGeneralMinyeong.getAmericanAge();
        this.householderTf = verificationOfGeneralMinyeong.isHouseholderTf();
        this.restrictedAreaTf = verificationOfGeneralMinyeong.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationOfGeneralMinyeong.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfGeneralMinyeong.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetHouseHavingLessThan2AptTf = verificationOfGeneralMinyeong.isMeetHouseHavingLessThan2AptTf();
        this.meetBankbookJoinPeriodTf = verificationOfGeneralMinyeong.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationOfGeneralMinyeong.isMeetDepositTf();
        this.priorityApt = verificationOfGeneralMinyeong.isRestrictedAreaTf();
        this.sibilingSupportYn = verificationOfGeneralMinyeong.getSibilingSupportYn();
        this.ranking = verificationOfGeneralMinyeong.getRanking();
        this.createdDate = verificationOfGeneralMinyeong.getCreatedDate();
        this.modifiedDate = verificationOfGeneralMinyeong.getModifiedDate();
    }


}
