package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberAdditionalInfo;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HouseMemberAdditionalInfoDto {

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

    public HouseMemberAdditionalInfo toEntity(HouseMember houseMember){
        return HouseMemberAdditionalInfo.builder()
                .houseMember(houseMember)
                .parentsDeathYn(parentsDeathYn)
                .divorceYn(divorceYn)
                .sameResidentRegistrationYn(sameResidentRegistrationYn)
                .stayOverYn(stayOverYn)
                .nowStayOverYn(nowStayOverYn)
                .build();
    }
}