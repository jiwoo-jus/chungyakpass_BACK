package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralMinyeong;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponseDto {

    boolean meetLivingInSurroundAreaTf;
    boolean accountTf;
    Integer americanAge;
    boolean householderTf;
    boolean isRestrictedAreaTf;
    boolean meetAllHouseMemberNotWinningIn5yearsTf;
    boolean meetAllHouseMemberRewinningRestrictionTf;
    boolean meetHouseHavingLessThan2AptTf;
    boolean meetBankbookJoinPeriodTf;
    boolean meetDepositTf;
    boolean isPriorityApt;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordGeneralMinyeongId;

    public GeneralMinyeongResponseDto(VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong) {
        this.meetLivingInSurroundAreaTf = verificationRecordGeneralMinyeong.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordGeneralMinyeong.isAccountTf();
        this.americanAge = verificationRecordGeneralMinyeong.getAmericanAge();
        this.householderTf = verificationRecordGeneralMinyeong.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordGeneralMinyeong.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetHouseHavingLessThan2AptTf = verificationRecordGeneralMinyeong.isMeetHouseHavingLessThan2AptTf();
        this.meetBankbookJoinPeriodTf = verificationRecordGeneralMinyeong.isMeetBankbookJoinPeriodTf();
        this.meetDepositTf = verificationRecordGeneralMinyeong.isMeetDepositTf();
        this.isPriorityApt = verificationRecordGeneralMinyeong.isPriorityApt();
        this.verificationRecordGeneralMinyeongId = verificationRecordGeneralMinyeong.getId();
        this.createdDate = verificationRecordGeneralMinyeong.getCreatedDate();
        this.modifiedDate = verificationRecordGeneralMinyeong.getModifiedDate();
    }
}
