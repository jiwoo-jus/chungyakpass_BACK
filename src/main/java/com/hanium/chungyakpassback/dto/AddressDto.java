package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.enumtype.address_level1;
import com.hanium.chungyakpassback.entity.enumtype.address_level2;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private address_level1 address_level1;

    private address_level2 address_level2;

    private String address_detail;

    private String zipcode;
}
