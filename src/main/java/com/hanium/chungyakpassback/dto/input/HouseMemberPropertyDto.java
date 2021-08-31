package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.enumtype.NonResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Property;
import com.hanium.chungyakpassback.enumtype.ResidentialBuilding;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberPropertyDto {

    private Long houseMemberId; //세대구성원id

    private Property property; //자산유형

    private Yn saleRightYn; //분양권여부

    private Yn residentialBuildingYn; //주거용건물여부

    private ResidentialBuilding residentialBuilding; //주거용건물유형

    private NonResidentialBuilding nonResidentialBuilding; //비주거용건물유형

    private LocalDate acquisitionDate; //취득일

    private LocalDate dispositionDate; //처분일

    private Integer exclusiveArea; //전용면적

    private Integer amount; //금액

    private LocalDate taxBaseDate; //과세기준일

}
