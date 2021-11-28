package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.AddressLevel2;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseDto {

    @NotBlank
    private Yn spouseHouseYn; //배우자분리세대여부

    @NotBlank
    private AddressLevel1 addressLevel1;

    @NotBlank
    private AddressLevel2 addressLevel2;

    private String addressDetail;

    private String zipcode;


    public House toEntity(com.hanium.chungyakpassback.entity.standard.AddressLevel1 addressLevel1, com.hanium.chungyakpassback.entity.standard.AddressLevel2 addressLevel2){
        return House.builder()
                .addressLevel1(addressLevel1)
                .addressLevel2(addressLevel2)
                .addressDetail(addressDetail)
                .zipcode(zipcode)
                .build();
    }

}
