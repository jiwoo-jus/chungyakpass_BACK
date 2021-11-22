package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordGeneralMinyeong;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponseDto {

    private Long verificationRecordGeneralMinyeongId;

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

    public GeneralMinyeongResponseDto(VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong) {
        this.verificationRecordGeneralMinyeongId = verificationRecordGeneralMinyeong.getId();
        this.meetLivingInSurroundAreaTf = verificationRecordGeneralMinyeong.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordGeneralMinyeong.isAccountTf();
        this.americanAge = verificationRecordGeneralMinyeong.getAmericanAge();
        this.householderTf = verificationRecordGeneralMinyeong.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordGeneralMinyeong.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetHouseHavingLessThan2AptTf = verificationRecordGeneralMinyeong.isMeetHouseHavingLessThan2AptTf();
        this.meetBankbookJoinPeriodTf = verificationRecordGeneralMinyeong.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationRecordGeneralMinyeong.isMeetDepositTf();
        this.priorityApt = verificationRecordGeneralMinyeong.isRestrictedAreaTf();
        this.sibilingSupportYn = verificationRecordGeneralMinyeong.getSibilingSupportYn();
        this.ranking = verificationRecordGeneralMinyeong.getRanking();
        this.createdDate = verificationRecordGeneralMinyeong.getCreatedDate();
        this.modifiedDate = verificationRecordGeneralMinyeong.getModifiedDate();
    }


}
