package com.hanium.chungyakpassback.dto.verification;

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
public class SpecialMinyeongFirstLifeUpdateDto {

    private Yn sibilingSupportYn; //형제자매부양여부

    private Yn taxOver5yearsYn; //5년이상소득세납부여부

    private Yn firstRankHistoryYn; //일반공급1순위당첨이력

    private Ranking ranking; //순위

}
