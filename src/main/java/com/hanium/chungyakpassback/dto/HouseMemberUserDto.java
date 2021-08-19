package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberUserDto {

    private AddressLevel1 address_level1;

    private AddressLevel2 address_level2;

    private String address_detail;

    private String zipcode;

//    private HouseholdMember spouse; //배우자세대구성원

    private Yn householderYn; //세대주여부

    private String name; //이름

    private LocalDate birthDate; //생년월일

    private Yn foreignerYn; //외국인여부

    private Yn soldierYn; //장기복무중인군인여부

    private LocalDate marriageDate; //혼인신고일

    private LocalDate homelessStartDate; //무주택시작일

    private LocalDate transferDate; //전입신고일
}
