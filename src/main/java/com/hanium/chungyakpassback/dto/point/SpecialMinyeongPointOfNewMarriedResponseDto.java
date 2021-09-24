package com.hanium.chungyakpassback.dto.point;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongPointOfNewMarriedResponseDto {
    Integer numberOfMinors;
    Integer periodOfMarriged;
    Integer bankbookPaymentsCount;
    Integer periodOfApplicableAreaResidence;
    Integer ageOfMostYoungChild;
    Integer monthOfAverageIncome;

}
