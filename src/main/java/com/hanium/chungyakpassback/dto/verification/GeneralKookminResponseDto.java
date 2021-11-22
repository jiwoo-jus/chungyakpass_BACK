package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordGeneralKookmin;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralKookminResponseDto {

    private Long verificationRecordGeneralKookminId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetNumberOfPaymentsTf;
    private Yn sibilingSupportYn;
    private Yn twentiesSoleHouseHolderYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public GeneralKookminResponseDto(VerificationRecordGeneralKookmin verificationRecordGeneralKookmin) {
        this.americanAge = verificationRecordGeneralKookmin.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordGeneralKookmin.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordGeneralKookmin.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordGeneralKookmin.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordGeneralKookmin.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordGeneralKookmin.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordGeneralKookmin.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordGeneralKookmin.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationRecordGeneralKookmin.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationRecordGeneralKookmin.isMeetNumberOfPaymentsTf();
        this.verificationRecordGeneralKookminId = verificationRecordGeneralKookmin.getId();
        this.sibilingSupportYn = verificationRecordGeneralKookmin.getSibilingSupportYn();
        this.twentiesSoleHouseHolderYn = verificationRecordGeneralKookmin.getTwentiesSoleHouseHolderYn();
        this.ranking = verificationRecordGeneralKookmin.getRanking();
        this.createdDate = verificationRecordGeneralKookmin.getCreatedDate();
        this.modifiedDate = verificationRecordGeneralKookmin.getModifiedDate();
    }

}
