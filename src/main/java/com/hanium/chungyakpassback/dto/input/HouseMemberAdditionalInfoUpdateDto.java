package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseMemberAdditionalInfoUpdateDto {
    @NotBlank
    private Long houseMemberId;

    @NotBlank
    private Yn parentsDeathYn;

    @NotBlank
    private Yn divorceYn;

    @NotBlank
    private Yn sameResidentRegistrationYn;

    @NotBlank
    private Yn stayOverYn;

    @NotBlank
    private Yn nowStayOverYn;
}
