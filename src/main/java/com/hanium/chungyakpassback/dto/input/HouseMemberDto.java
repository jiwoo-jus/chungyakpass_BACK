package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberDto {

    private Yn spouseHouseYn; //배우자분리세대여부

    private Relation relation; //회원과의 관계

//    private Yn isHouseholderYn; //세대주여부

    private String name; //이름

    private LocalDate birthDay; //생년월일

    private Yn foreignerYn; //외국인여부

    private Yn soldierYn; //장기복무중인군인여부

    private LocalDate marriageDate; //혼인신고일

    private LocalDate homelessStartDate; //무주택시작일

    private LocalDate transferDate; //전입신고일

    private Integer income; //월평균소득

    public HouseMember toEntity(House house) {
        return HouseMember.builder()
                .house(house)
                .name(name)
                .birthDay(birthDay)
                .foreignerYn(foreignerYn)
                .soldierYn(soldierYn)
                .marriageDate(marriageDate)
                .homelessStartDate(homelessStartDate)
                .transferDate(transferDate)
                .income(income)
                .build();
    }
}