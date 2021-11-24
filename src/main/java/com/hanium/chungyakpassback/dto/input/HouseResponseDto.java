package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.AddressLevel2;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HouseResponseDto {
    private Long id;

    @Setter
    private Long houseHolderId;

    private AddressLevel1 addressLevel1;

    private AddressLevel2 addressLevel2;

    private String addressDetail;

    private String zipcode;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public HouseResponseDto(House house){
        this.id = house.getId();
        this.addressLevel1 = house.getAddressLevel1().getAddressLevel1();
        this.addressLevel2 = house.getAddressLevel2().getAddressLevel2();
        this.addressDetail = house.getAddressDetail();
        this.zipcode = house.getZipcode();
        this.createdDate = house.getCreatedDate();
        this.modifiedDate = house.getModifiedDate();
    }
}
