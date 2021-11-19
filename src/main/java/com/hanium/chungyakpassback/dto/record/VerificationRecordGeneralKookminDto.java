package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

@Getter
@Setter
@Builder
public class VerificationRecordGeneralKookminDto {

    public Long verificationRecordGeneralKookminId;

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn twentiesSoleHouseHolderYn; //20대단독세대주여부

    public Ranking ranking; //순위

}
