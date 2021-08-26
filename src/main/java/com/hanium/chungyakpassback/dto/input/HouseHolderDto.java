package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseHolderDto {
    private Yn spouseHouseYn; //배우자분리세대여부
    private Long houseHolderId;
}
