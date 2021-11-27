package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.AddressLevel2;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseUpdateDto {

    @NotBlank
    private AddressLevel1 addressLevel1; //지역레벨1

    @NotBlank
    private AddressLevel2 addressLevel2; //지역레벨2

    private String addressDetail; //상세주소

    private String zipcode; //우편번호

}
