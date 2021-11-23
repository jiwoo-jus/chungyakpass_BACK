package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.HouseMemberChungyak;
import com.hanium.chungyakpassback.enumtype.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberChungyakReadDto{
    private Long id;

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

    private HouseMemberChungyakRestrictionReadDto houseMemberChungyakRestrictionReadDto;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public HouseMemberChungyakReadDto(HouseMemberChungyak houseMemberChungyak, HouseMemberChungyakRestrictionReadDto houseMemberChungyakRestrictionReadDto){
        this.id = houseMemberChungyak.getId();
        this.houseMemberChungyakRestrictionReadDto = houseMemberChungyakRestrictionReadDto;
        this.houseName = houseMemberChungyak.getHouseName();
        this.supply = houseMemberChungyak.getSupply();
        this.specialSupply = houseMemberChungyak.getSpecialSupply();
        this.housingType = houseMemberChungyak.getHousingType();
        this.ranking = houseMemberChungyak.getRanking();
        this.result = houseMemberChungyak.getResult();
        this.preliminaryNumber = houseMemberChungyak.getPreliminaryNumber();
        this.winningDate = houseMemberChungyak.getWinningDate();
        this.raffle = houseMemberChungyak.getRaffle();
        this.cancelWinYn = houseMemberChungyak.getCancelWinYn();
        this.createdDate = houseMemberChungyak.getCreatedDate();
        this.modifiedDate = houseMemberChungyak.getModifiedDate();
    }

    @Builder
    public HouseMemberChungyakReadDto(HouseMemberChungyak houseMemberChungyak){
        this.id = houseMemberChungyak.getId();
        this.houseName = houseMemberChungyak.getHouseName();
        this.supply = houseMemberChungyak.getSupply();
        this.specialSupply = houseMemberChungyak.getSpecialSupply();
        this.housingType = houseMemberChungyak.getHousingType();
        this.ranking = houseMemberChungyak.getRanking();
        this.result = houseMemberChungyak.getResult();
        this.preliminaryNumber = houseMemberChungyak.getPreliminaryNumber();
        this.winningDate = houseMemberChungyak.getWinningDate();
        this.raffle = houseMemberChungyak.getRaffle();
        this.cancelWinYn = houseMemberChungyak.getCancelWinYn();
        this.createdDate = houseMemberChungyak.getCreatedDate();
        this.modifiedDate = houseMemberChungyak.getModifiedDate();
    }

}
