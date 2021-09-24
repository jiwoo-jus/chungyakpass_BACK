package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SpecialPointOfOldParentsSupportDto {
    Long houseMemberId;
    SpecialSupply specialSupply;
    Yn parentsDeathYn;
    Yn divorceYn;
    Yn sameResidentRegistrationYn;
    Yn stayOverYn;
    Yn nowStayOverYn;

    private List<SpecialPointOfOldParentsSupportDto> specialPointOfOldParentsSupportDtoList;

    public List<SpecialPointOfOldParentsSupportDto> getSpecialPointOfOldParentsSupportDtoList() {
        return specialPointOfOldParentsSupportDtoList;
    }

}
