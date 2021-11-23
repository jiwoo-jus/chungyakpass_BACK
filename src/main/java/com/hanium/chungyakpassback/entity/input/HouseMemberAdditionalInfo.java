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
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember;

    @Column
    Yn parentsDeathYn;

    @Column
    Yn divorceYn;

    @Column
    Yn sameResidentRegistrationYn;

    @Column
    Yn stayOverYn;

    @Column
    Yn nowStayOverYn;

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
