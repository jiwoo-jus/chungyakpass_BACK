package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

@Getter
@Setter
@Builder
public class VerificationRecordGeneralDto {

//    public Long verificationRecordId; //청약자격결과id
//
//    public Integer notificationNumber; //아파트공고번호
//
//    public String housingType; //주택형

    public Long verificationRecordGeneralKookminRequestId;

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn twentiesSoleHouseHolderYn; //20대단독세대주여부

    public Ranking ranking; //순위

//    public VerificationRecordGeneralResponse toEntity(VerificationRecord verificationRecord) {
//        return VerificationRecordGeneralResponse.builder()
//                .verificationRecord(verificationRecord)
//                .notificationNumber(notificationNumber)
//                .housingType(housingType)
//                .sibilingSupportYn(sibilingSupportYn)
//                .twentiesSoleHouseHolderYn(twentiesSoleHouseHolderYn)
//                .ranking(ranking)
//                .build();
//    }
//    public VerificationRecordGeneralResponse toEntity(VerificationRecord verificationRecord) {
//        return VerificationRecordGeneralResponse.builder()
//                .verificationRecord(verificationRecord)
//                .notificationNumber(notificationNumber)
//                .housingType(housingType)
//                .sibilingSupportYn(sibilingSupportYn)
//                .twentiesSoleHouseHolderYn(twentiesSoleHouseHolderYn)
//                .ranking(ranking)
//                .build();
//    }
}
