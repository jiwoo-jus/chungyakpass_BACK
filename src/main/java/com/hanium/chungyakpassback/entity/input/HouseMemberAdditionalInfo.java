package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseMemberAdditionalInfoResponseDto;
import com.hanium.chungyakpassback.dto.input.HouseMemberAdditionalInfoUpdateDto;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "inp_house_member_additional_info")
public class HouseMemberAdditionalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_additional_info_id")
    private Long id; //세대구성원추가정보id

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember; //세대구성원

    @Column
    Yn parentsDeathYn; //부모사망여부

    @Column
    Yn divorceYn; //이혼 여부

    @Column
    Yn sameResidentRegistrationYn; //회원 세대 거주 여부

    @Column
    Yn stayOverYn; //해외or요양시설 거주 이력 여부

    @Column
    Yn nowStayOverYn; //현재 해외or요양시설 거주 여부

    public HouseMemberAdditionalInfo updateHouseMemberAdditionalInfo(HouseMember houseMember, HouseMemberAdditionalInfoUpdateDto houseMemberAdditionalInfoUpdateDto){
        this.houseMember = houseMember;
        this.parentsDeathYn = houseMemberAdditionalInfoUpdateDto.getParentsDeathYn();
        this.divorceYn = houseMemberAdditionalInfoUpdateDto.getDivorceYn();
        this.sameResidentRegistrationYn = houseMemberAdditionalInfoUpdateDto.getSameResidentRegistrationYn();
        this.stayOverYn = houseMemberAdditionalInfoUpdateDto.getStayOverYn();
        this.nowStayOverYn = houseMemberAdditionalInfoUpdateDto.getNowStayOverYn();
        return this;
    }

}
