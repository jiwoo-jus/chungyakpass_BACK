package com.hanium.chungyakpassback.dto.point;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReadAllUserPointDto {
    List<PointOfGeneralMinyeongResponseDto> pointOfGeneralMinyeongResponseDtos;
    List<PointOfSpecialMinyeongNewlyMarriedResponseDto> pointOfSpecialMinyeongNewlyMarriedResponseDtos;
    List<PointOfSpecialMinyeongSingleParentsResponseDto> pointOfSpecialMinyeongSingleParentsResponseDtos;
    List<PointOfSpecialMinyeongMultiChildResponseDto> pointOfSpecialMinyeongMultiChildResponseDtos;
    List<PointOfSpecialMinyeongOldParentsSupportResponseDto> pointOfSpecialMinyeongOldParentsSupportResponseDtos;
}
