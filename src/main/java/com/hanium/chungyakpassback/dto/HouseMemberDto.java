package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.enumtype.Relation;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberDto {

    private Yn spouseHouseYn; //배우자분리세대여부

    private Relation relation; //회원과의 관계

//    private Yn householderYn; //세대주여부

    private String name; //이름

    private LocalDate birthDate; //생년월일

    private Yn foreignerYn; //외국인여부

    private Yn soldierYn; //장기복무중인군인여부

    private LocalDate marriageDate; //혼인신고일

    private LocalDate homelessStartDate; //무주택시작일

    private LocalDate transferDate; //전입신고일

}
