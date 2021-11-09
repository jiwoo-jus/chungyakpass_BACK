package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicNewlyMarriedDto {

    @NotNull
    public Integer notificationNumber;

    @NotNull
    public String housingType;

}
