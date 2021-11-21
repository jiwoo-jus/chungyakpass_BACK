package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SpecialKookminPublicFirstLifeUpdateDto {

    public Yn sibilingSupportYn; //형제자매부양여부

    public Yn taxOver5yearsYn; //5년이상소득세납부여부

    public KookminType kookminType; //국민주택종류

    public Yn firstRankHistoryYn; //일반공급1순위당첨이력

    public Ranking ranking; //순위

}
