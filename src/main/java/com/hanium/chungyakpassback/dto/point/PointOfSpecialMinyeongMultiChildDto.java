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
    private Integer notificationNumber;//공고번호

    @NotBlank
    private MultiChildHouseholdType multiChildHouseholdType; //세대구성

}
