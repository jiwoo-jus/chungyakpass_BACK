package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongFirstLifeDto {

    @NotBlank
    private Integer notificationNumber;

    @NotBlank
    private String housingType;

}
