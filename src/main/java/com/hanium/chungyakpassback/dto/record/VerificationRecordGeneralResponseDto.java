package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationRecordGeneralResponseDto {

    public Long id;

    public Long verificationRecordId; //청약자격결과id

    public Integer notificationNumber; //아파트공고번호

    public String housingType; //주택형

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn twentiesSoleHouseHolderYn; //20대단독세대주여부

    public Ranking ranking; //순위

//    @Builder
//    public VerificationRecordGeneralResponseDto(VerificationRecordGeneralResponse verificationRecordGeneralResponse) {
//        this.id = verificationRecordGeneralResponse.getId();
//        this.verificationRecordId = verificationRecordGeneralResponse.getVerificationRecord().getId();
//        this.notificationNumber = verificationRecordGeneralResponse.getNotificationNumber();
//        this.housingType = verificationRecordGeneralResponse.getHousingType();
//        this.sibilingSupportYn = verificationRecordGeneralResponse.getSibilingSupportYn();
//        this.twentiesSoleHouseHolderYn = verificationRecordGeneralResponse.getTwentiesSoleHouseHolderYn();
//        this.ranking = verificationRecordGeneralResponse.getRanking();
//    }
}
