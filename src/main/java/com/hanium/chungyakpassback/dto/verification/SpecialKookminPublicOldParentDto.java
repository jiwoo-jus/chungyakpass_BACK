package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminPublicOldParentDto {

    @NotNull
    private Integer notificationNumber;

    @NotNull
    private String housingType;


}
