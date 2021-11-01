package com.hanium.chungyakpassback.dto.input;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseReadDto {

    HouseResponseDto houseResponseDto;

    HouseResponseDto spouseHouseResponseDto;

}
