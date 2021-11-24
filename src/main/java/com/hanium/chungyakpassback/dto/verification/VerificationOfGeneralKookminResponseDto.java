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
