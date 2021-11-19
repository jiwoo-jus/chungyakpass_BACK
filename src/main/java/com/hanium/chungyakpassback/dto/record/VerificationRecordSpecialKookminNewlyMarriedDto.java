package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationRecordSpecialKookminNewlyMarriedDto {

    public Long verificationRecordSpecialKookminNewlyMarriedId;

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn preNewMarriedYn; //예비신혼부부여부

    public KookminType kookminType; //국민주택유형

    public Ranking ranking; //순위

}
