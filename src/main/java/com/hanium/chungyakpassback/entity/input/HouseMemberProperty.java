package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseMemberPropertyUpdateDto;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.enumtype.NonResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Property;
import com.hanium.chungyakpassback.enumtype.ResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_house_member_property")
public class HouseMemberProperty extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_property_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember;

    @Column
    @Enumerated(EnumType.STRING)
    private Property property; //자산유형

    @Column
    @Enumerated(EnumType.STRING)
    private Yn saleRightYn; //분양권여부

    @Column
    @Enumerated(EnumType.STRING)
    private Yn residentialBuildingYn; //주거용건물여부

    @Column
    @Enumerated(EnumType.STRING)
    private ResidentialBuilding residentialBuilding; //주거용건물유형

    @Column
    @Enumerated(EnumType.STRING)
    private NonResidentialBuilding nonResidentialBuilding; //비주거용건물유형

    @Column
    @Enumerated(EnumType.STRING)
    private Yn metropolitanBuildingYn; //건물수도권여부

    @Column
    @Enumerated(EnumType.STRING)
    private Yn exceptionHouseYn; //주택예외사항해당여부

    @Column
    private LocalDate acquisitionDate; //취득일

    @Column
    private LocalDate dispositionDate; //처분일

    @Column
    private Integer exclusiveArea; //전용면적

    @Column
    private Integer amount; //금액

    @Column
    private LocalDate taxBaseDate; //과세기준일


    @Builder
    public HouseMemberProperty(HouseMember houseMember, Property property, Yn saleRightYn, Yn residentialBuildingYn, ResidentialBuilding residentialBuilding, NonResidentialBuilding nonResidentialBuilding, Yn metropolitanBuildingYn, Yn exceptionHouseYn, LocalDate acquisitionDate, LocalDate dispositionDate, Integer exclusiveArea, Integer amount, LocalDate taxBaseDate) {
        this.houseMember = houseMember;
        this.property = property;
        this.saleRightYn = saleRightYn;
        this.residentialBuildingYn = residentialBuildingYn;
        this.residentialBuilding = residentialBuilding;
        this.nonResidentialBuilding = nonResidentialBuilding;
        this.metropolitanBuildingYn = metropolitanBuildingYn;
        this.exceptionHouseYn = exceptionHouseYn;
        this.acquisitionDate = acquisitionDate;
        this.dispositionDate = dispositionDate;
        this.exclusiveArea = exclusiveArea;
        this.amount = amount;
        this.taxBaseDate = taxBaseDate;
    }

    //세대구성원 바꿀 수 있는 케이스
    public HouseMemberProperty updateHouseMemberProperty(HouseMemberPropertyUpdateDto houseMemberPropertyUpdateDto, HouseMember houseMember){
        this.houseMember = houseMember;
        this.property = houseMemberPropertyUpdateDto.getProperty();
        this.saleRightYn = houseMemberPropertyUpdateDto.getSaleRightYn();
        this.residentialBuildingYn = houseMemberPropertyUpdateDto.getResidentialBuildingYn();
        this.residentialBuilding = houseMemberPropertyUpdateDto.getResidentialBuilding();
        this.nonResidentialBuilding = houseMemberPropertyUpdateDto.getNonResidentialBuilding();
        this.metropolitanBuildingYn = houseMemberPropertyUpdateDto.getMetropolitanBuildingYn();
        this.exceptionHouseYn = houseMemberPropertyUpdateDto.getExceptionHouseYn();
        this.acquisitionDate = houseMemberPropertyUpdateDto.getAcquisitionDate();
        this.dispositionDate = houseMemberPropertyUpdateDto.getDispositionDate();
        this.exclusiveArea = houseMemberPropertyUpdateDto.getExclusiveArea();
        this.amount = houseMemberPropertyUpdateDto.getAmount();
        this.taxBaseDate = houseMemberPropertyUpdateDto.getTaxBaseDate();
        return this;
    }
}

