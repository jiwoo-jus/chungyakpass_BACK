package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfGeneralMinyeongDto {
    Long houseMemberId;
    Yn parentsDeathYn;
    Yn divorceYn;
    Yn sameResidentRegistrationYn;
    Yn stayOverYn;
    Yn nowStayOverYn;

    private List<PointOfGeneralMinyeongDto> pointOfGeneralMinyeongDtoList;

    public List<PointOfGeneralMinyeongDto> getPointOfGeneralMinyeongDtoList() {
        return pointOfGeneralMinyeongDtoList;
    }


}
