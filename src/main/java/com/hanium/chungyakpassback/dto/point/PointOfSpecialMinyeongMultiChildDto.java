package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSpecialMinyeongMultiChildDto {

    @NotBlank
    private Integer notificationNumber;

    @NotBlank
    private MultiChildHouseholdType multiChildHouseholdType;

}
