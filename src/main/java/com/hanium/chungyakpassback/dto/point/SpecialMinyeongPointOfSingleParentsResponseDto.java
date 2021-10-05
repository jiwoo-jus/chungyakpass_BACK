package com.hanium.chungyakpassback.dto.point;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongPointOfSingleParentsResponseDto {
    Integer numberOfMinors;
    Integer ageOfMostYoungChild;
    Integer bankbookPaymentsCount;
    Integer periodOfApplicableAreaResidence;
    Integer monthOfAverageIncome;
}
