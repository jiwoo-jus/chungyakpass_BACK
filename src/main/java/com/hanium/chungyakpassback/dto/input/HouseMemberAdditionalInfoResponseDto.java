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
public class HouseMemberAdditionalInfoResponseDto {

    private Long houseMemberAdditionalInfoId;

    private Long houseMemberId;

    private Yn parentsDeathYn;

    private Yn divorceYn;

    private Yn sameResidentRegistrationYn;

    private Yn stayOverYn;

    private Yn nowStayOverYn;

    public HouseMemberAdditionalInfoResponseDto(HouseMemberAdditionalInfo houseMemberAdditionalInfo) {
        this.houseMemberAdditionalInfoId = houseMemberAdditionalInfo.getId();
        this.houseMemberId = houseMemberAdditionalInfo.getHouseMember().getId();
        this.parentsDeathYn = houseMemberAdditionalInfo.getParentsDeathYn();
        this.divorceYn = houseMemberAdditionalInfo.getDivorceYn();
        this.sameResidentRegistrationYn = houseMemberAdditionalInfo.getSameResidentRegistrationYn();
        this.stayOverYn = houseMemberAdditionalInfo.getStayOverYn();
        this.nowStayOverYn = houseMemberAdditionalInfo.getNowStayOverYn();
    }
}