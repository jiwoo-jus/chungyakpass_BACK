package com.hanium.chungyakpassback.dto.vertification;

import com.hanium.chungyakpassback.enumtype.HousingType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongDto {

    public Integer notificationNumber;
    public String housingType;

}
