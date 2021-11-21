package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookmin;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralKookminResponseDto {

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean householderTf;
    private boolean isRestrictedAreaTf;
    private boolean meetAllHouseMemberNotWinningIn5yearsTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private boolean meetBankbookJoinPeriodTf;
    private boolean meetNumberOfPaymentsTf;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    Long verificationRecordGeneralKookminId;

    @Builder
    public GeneralKookminResponseDto(VerificationRecordGeneralKookmin verificationRecordGeneralKookmin) {
        this.americanAge = verificationRecordGeneralKookmin.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordGeneralKookmin.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordGeneralKookmin.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordGeneralKookmin.isMeetHomelessHouseholdMemberTf();
        this.householderTf = verificationRecordGeneralKookmin.isHouseholderTf();
        this.isRestrictedAreaTf = verificationRecordGeneralKookmin.isRestrictedAreaTf();
        this.meetAllHouseMemberNotWinningIn5yearsTf = verificationRecordGeneralKookmin.isMeetAllHouseMemberNotWinningIn5yearsTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordGeneralKookmin.isMeetAllHouseMemberRewinningRestrictionTf();
        this.meetBankbookJoinPeriodTf = verificationRecordGeneralKookmin.isMeetBankbookJoinPeriodTf();
        this.meetNumberOfPaymentsTf = verificationRecordGeneralKookmin.isMeetNumberOfPaymentsTf();
        this.verificationRecordGeneralKookminId = verificationRecordGeneralKookmin.getId();
        this.createdDate = verificationRecordGeneralKookmin.getCreatedDate();
        this.modifiedDate = verificationRecordGeneralKookmin.getModifiedDate();
    }

}
