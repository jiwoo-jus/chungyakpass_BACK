package com.hanium.chungyakpassback.dto.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseHolderDto {
    @NotBlank
    private Long houseMemberId; //세대주 세대구성원id
}
