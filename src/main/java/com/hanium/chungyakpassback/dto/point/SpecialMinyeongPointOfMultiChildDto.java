package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongPointOfMultiChildDto {

    private Integer notificationNumber;
    private MultiChildHouseholdType multiChildHouseholdType;

}
