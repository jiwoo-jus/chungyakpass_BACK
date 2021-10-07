package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialPublicFirstLifeResponseDto {
    boolean targetHouseAmount;
    boolean HomelessYn;
    boolean vaildObject;
    boolean meetDeposit;
    boolean monthOfAverageIncomePriority;
    boolean monthOfAverageIncomeGeneral;


}
