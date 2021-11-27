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

    private Long houseMemberAdditionalInfoId; //세대구성원 추가정보 id

    private Long houseMemberId; //세대구성원 id

    private Yn parentsDeathYn; //부모 사망 여부

    private Yn divorceYn; //이혼 여부

    private Yn sameResidentRegistrationYn; //회원 세대 거주 여부

    private Yn stayOverYn; //해외 및 요양시설 체류 이력 여부

    private Yn nowStayOverYn; //현재 해외 및 요양시설 체류 여부

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