package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VerificationRecordGeneralMinyeongDto {

    public Long verificationRecordGeneralMinyeongId;

    public Yn sibilingSupportYn; //형제자매부양여부

    public Ranking ranking; //순위

}
