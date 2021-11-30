package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialKookminPublicOldParentDto {

    @NotBlank
    private Integer notificationNumber; //아파트공고번호

    @NotBlank
    private String housingType; //주택형


}
