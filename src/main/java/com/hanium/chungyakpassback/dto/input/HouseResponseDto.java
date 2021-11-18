package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HouseResponseDto {
    private Long id;

    private Long addressLevel1Id;

    private Long addressLevel2Id;

    private String addressDetail;

    private String zipcode;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    @Builder
    public HouseResponseDto(House house){
        this.id = house.getId();
        this.addressLevel1Id = house.getAddressLevel1().getId();
        this.addressLevel2Id = house.getAddressLevel2().getId();
        this.addressDetail = house.getAddressDetail();
        this.zipcode = house.getZipcode();
        this.createdDate = house.getCreatedDate();
        this.modifiedDate = house.getModifiedDate();
    }
}
