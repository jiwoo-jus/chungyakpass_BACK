package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookminRequest;
import lombok.*;

@Getter
@Setter
@Builder
public class VerificationRecordGeneralKookminDto {

//    public Long verificationRecordId; //청약자격결과id
//
//    public VerificationRecordGeneralKookminRequest toEntity(VerificationRecord verificationRecord) {
//        return VerificationRecordGeneralKookminRequest.builder()
//                .verificationRecord(verificationRecord)
//                .build();
//    }

    public Long verificationRecordId; //청약자격결과id

    public boolean meetLivingInSurroundAreaTf;

    public boolean accountTf;

    public boolean meetHomelessHouseholdMembersTf;

    public boolean householderTf;

    public boolean isRestrictedAreaTf;

    public boolean meetAllHouseMemberNotWinningIn5yearsTf;

    public boolean meetAllHouseMemberRewinningRestrictionTf;

    public boolean meetBankbookJoinPeriodTf;

    public boolean meetNumberOfPaymentsTf;
//
//    public VerificationRecordGeneralKookminRequest toEntity(VerificationRecord verificationRecord) {
//        return VerificationRecordGeneralKookminRequest.builder()
//                .verificationRecord(verificationRecord)
//                .accountTf(accountTf)
//                .meetHomelessHouseholdMemberTf(meetHomelessHouseholdMembersTf)
//                .householderTf(householderTf)
//                .meetAllHouseMemberNotWinningIn5yearsTf(meetAllHouseMemberNotWinningIn5yearsTf)
//                .meetAllHouseMemberRewinningRestrictionTf(meetAllHouseMemberRewinningRestrictionTf)
//                .meetBankbookJoinPeriodTf(meetBankbookJoinPeriodTf)
//                .meetNumberOfPaymentsTf(meetNumberOfPaymentsTf)
//                .build();
//    }

}
