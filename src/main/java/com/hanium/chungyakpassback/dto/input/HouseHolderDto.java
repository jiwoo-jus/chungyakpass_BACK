package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseHolderDto {
    private Long houseMemberId; //세대주인 세대구성원id
}
