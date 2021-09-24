package com.hanium.chungyakpassback.dto.point;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialPointOfMultiChildResponseDto {
    Integer numberOfChild;
    Integer numberOfChildUnder6Year;
    Integer bankbookJoinPeriod;
    Integer periodOfApplicableAreaResidence;
    Integer periodOfHomelessness;
    Integer generationComposition;
}
