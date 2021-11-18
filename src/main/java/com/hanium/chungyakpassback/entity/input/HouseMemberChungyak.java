package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseMemberChungyakUpdateDto;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.enumtype.*;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_house_member_chungyak")
public class HouseMemberChungyak extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_chungyak_id")
    private Long id;

    @OneToOne(mappedBy = "houseMemberChungyak")
    private HouseMemberChungyakRestriction houseMemberChungyakRestriction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember;

    @Column
    private String houseName; //주택명

    @Column
    @Enumerated(EnumType.STRING)
    private Supply supply; //공급유형

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialSupply specialSupply; //특별공급유형

    @Column
    private String housingType; //주택형

    @Column
    @Enumerated(EnumType.STRING)
    private Ranking ranking; //순위

    @Column
    @Enumerated(EnumType.STRING)
    private Result result; //결과

    @Column
    private Integer preliminaryNumber; //예비번호

    @Column
    private LocalDate winningDate; //당첨일

    @Column
    @Enumerated(EnumType.STRING)
    private Raffle raffle; //추첨방식

    @Column
    @Enumerated(EnumType.STRING)
    private Yn cancelWinYn; //당첨취소여부


    @Builder
    public HouseMemberChungyak(HouseMember houseMember, String houseName, Supply supply, SpecialSupply specialSupply, String housingType, Ranking ranking, Result result, Integer preliminaryNumber, LocalDate winningDate, Raffle raffle, Yn cancelWinYn) {
        this.houseMember = houseMember;
        this.houseName = houseName;
        this.supply = supply;
        this.specialSupply = specialSupply;
        this.housingType = housingType;
        this.ranking = ranking;
        this.result = result;
        this.preliminaryNumber = preliminaryNumber;
        this.winningDate = winningDate;
        this.raffle = raffle;
        this.cancelWinYn = cancelWinYn;
    }

    public HouseMemberChungyak updateHouseMemberChungyak(HouseMember houseMember, HouseMemberChungyakUpdateDto houseMemberChungyakUpdateDto){
        this.houseMember = houseMember;
        this.houseName = houseMemberChungyakUpdateDto.getHouseName();
        this.supply = houseMemberChungyakUpdateDto.getSupply();
        this.specialSupply = houseMemberChungyakUpdateDto.getSpecialSupply();
        this.housingType = houseMemberChungyakUpdateDto.getHousingType();
        this.ranking = houseMemberChungyakUpdateDto.getRanking();
        this.result = houseMemberChungyakUpdateDto.getResult();
        this.preliminaryNumber = houseMemberChungyakUpdateDto.getPreliminaryNumber();
        this.winningDate = houseMemberChungyakUpdateDto.getWinningDate();
        this.raffle = houseMemberChungyakUpdateDto.getRaffle();
        this.cancelWinYn = houseMemberChungyakUpdateDto.getCancelWinYn();
        return this;
    }
}
