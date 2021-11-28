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

    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private Integer americanAge;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean meetHouseHavingLessThan2AptTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetDepositTf;
    private boolean priorityApt;
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

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
