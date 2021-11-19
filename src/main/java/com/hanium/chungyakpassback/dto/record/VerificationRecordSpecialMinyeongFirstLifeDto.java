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
public class VerificationRecordSpecialMinyeongFirstLifeDto {

    public Long verificationRecordSpecialMinyeongFirstLifeId;

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn taxOver5yearsYn; //5년이상소득세납부여부

    public Yn firstRankHistoryYn; //일반공급1순위당첨이력

    public Ranking ranking; //순위

}
