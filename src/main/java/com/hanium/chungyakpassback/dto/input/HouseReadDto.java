package com.hanium.chungyakpassback.dto.input;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseReadDto {

    HouseResponseDto houseResponseDto; //회원 세대 response dto

    HouseResponseDto spouseHouseResponseDto; //배우자 세대 response dto

}
