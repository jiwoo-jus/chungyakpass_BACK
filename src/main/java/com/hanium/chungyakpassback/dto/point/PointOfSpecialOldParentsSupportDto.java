package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PointOfSpecialOldParentsSupportDto {
    private Long houseMemberId;
    private Yn parentsDeathYn;
    private Yn divorceYn;
    private Yn sameResidentRegistrationYn;
    private Yn stayOverYn;
    private Yn nowStayOverYn;

    @Getter
    private List<PointOfSpecialOldParentsSupportDto> pointOfSpecialOldParentsSupportDtoList;

//    public List<SpecialPointOfOldParentsSupportDto> getSpecialPointOfOldParentsSupportDtoList() {
//        return specialPointOfOldParentsSupportDtoList;
//    }

}
