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
    private Long houseMemberId; //세대구성원 id

    @NotBlank
    private Yn parentsDeathYn; //부모 사망 여부

    @NotBlank
    private Yn divorceYn; //이혼 여부

    @NotBlank
    private Yn sameResidentRegistrationYn; //회원 세대 거주 여부

    @NotBlank
    private Yn stayOverYn; //해외 및 요양시설 체류 이력 여부

    @NotBlank
    private Yn nowStayOverYn; //현재 해외 및 요양시설 체류 여부
}
