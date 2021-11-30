package com.hanium.chungyakpassback.dto.point;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ReadAllUserPointDto {
    List<PointOfGeneralMinyeongResponseDto> pointOfGeneralMinyeongResponseDtos; //일반민영 가점결과 List
    List<PointOfSpecialMinyeongNewlyMarriedResponseDto> pointOfSpecialMinyeongNewlyMarriedResponseDtos; //특별신혼부부 가점결가 List
    List<PointOfSpecialMinyeongSingleParentsResponseDto> pointOfSpecialMinyeongSingleParentsResponseDtos; //특별한부모 가점결과 List
    List<PointOfSpecialMinyeongMultiChildResponseDto> pointOfSpecialMinyeongMultiChildResponseDtos; //특별다자녀 가점결과 List
    List<PointOfSpecialMinyeongOldParentsSupportResponseDto> pointOfSpecialMinyeongOldParentsSupportResponseDtos; //특별노부모 가점결과 List
}
