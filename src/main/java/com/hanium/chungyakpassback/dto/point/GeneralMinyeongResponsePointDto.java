package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponsePointDto {
    Integer periodOfHomelessness;
    Integer bankbookJoinPeriod;
    Integer numberOfDependents;
    boolean bankBookVaildYn;
}
