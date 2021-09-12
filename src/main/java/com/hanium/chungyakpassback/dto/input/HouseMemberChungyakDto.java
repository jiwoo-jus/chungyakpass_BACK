package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberChungyak;
import com.hanium.chungyakpassback.enumtype.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberChungyakDto {

    private Long houseMemberId; //세대구성원id

    private String houseName; //주택명

    private Supply supply; //공급유형

    private SpecialSupply specialSupply; //특별공급유형

    private String housingType; //주택형

    private Ranking ranking; //순위

    private Result result; //결과

    private Integer preliminaryNumber; //예비번호

    private LocalDate winningDate; //당첨일

    private Raffle raffle; //추첨방식

    private Yn cancelWinYn; //당첨취소여부

    public HouseMemberChungyak toEntity(HouseMember houseMember) {
        return HouseMemberChungyak.builder()
                .houseMember(houseMember)
                .houseName(houseName)
                .supply(supply)
                .specialSupply(specialSupply)
                .housingType(housingType)
                .ranking(ranking)
                .result(result)
                .preliminaryNumber(preliminaryNumber)
                .winningDate(winningDate)
                .raffle(raffle)
                .cancelWinYn(cancelWinYn)
                .build();
    }
}
