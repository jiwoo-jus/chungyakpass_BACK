package com.hanium.chungyakpassback.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HouseMemberHomelessStartDateDto {
    @NotBlank
    LocalDate homelessStartDate;
}
