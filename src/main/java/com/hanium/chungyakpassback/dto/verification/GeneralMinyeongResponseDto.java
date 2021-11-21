package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralMinyeong;
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

    Long verificationRecordGeneralMinyeongId;

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
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


//    public GeneralMinyeongResponseDto(VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong) {
//        this.meetLivingInSurroundAreaTf = verificationRecordGeneralMinyeong.isMeetLivingInSurroundAreaTf();
//        this.accountTf = verificationRecordGeneralMinyeong.isAccountTf();
//        this.americanAge = verificationRecordGeneralMinyeong.getAmericanAge();
//        this.householderTf = verificationRecordGeneralMinyeong.isHouseholderTf();
//        this.isRestrictedAreaTf = verificationRecordGeneralMinyeong.isRestrictedAreaTf();
//        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberNotWinningIn5yearsTf();
//        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordGeneralMinyeong.isMeetAllHouseMemberRewinningRestrictionTf();
//        this.meetHouseHavingLessThan2AptTf = verificationRecordGeneralMinyeong.isMeetHouseHavingLessThan2AptTf();
//        this.meetBankbookJoinPeriodTf = verificationRecordGeneralMinyeong.isMeetBankbookJoinPeriodTf();
//        this.meetDepositTf = verificationRecordGeneralMinyeong.isMeetDepositTf();
//        this.isPriorityApt = verificationRecordGeneralMinyeong.isPriorityApt();
//        this.verificationRecordGeneralMinyeongId = verificationRecordGeneralMinyeong.getId();
//        this.createdDate = verificationRecordGeneralMinyeong.getCreatedDate();
//        this.modifiedDate = verificationRecordGeneralMinyeong.getModifiedDate();
//    }

    public GeneralMinyeongResponseDto(VerificationRecordGeneralMinyeong verificationRecordGeneralMinyeong) {
        this.verificationRecordGeneralMinyeongId = verificationRecordGeneralMinyeong.getId();
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
        this.sibilingSupportYn = verificationRecordGeneralMinyeong.getSibilingSupportYn();
        this.ranking = verificationRecordGeneralMinyeong.getRanking();
        this.createdDate = verificationRecordGeneralMinyeong.getCreatedDate();
        this.modifiedDate = verificationRecordGeneralMinyeong.getModifiedDate();
    }


}
