package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialPrivateFirstLifeResponseDto {
    boolean targetHousingType;
    boolean targetHouseAmount;
    boolean monthOfAverageIncome;
    boolean HomelessYn;
    boolean vaildObject;


}
