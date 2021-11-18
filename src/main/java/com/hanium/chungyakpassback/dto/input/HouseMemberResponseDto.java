package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class HouseMemberResponseDto {
    private Long id;

    private Long houseId;

    private Relation relation;

    private String name; //이름

    private LocalDate birthDay; //생년월일

    private Yn foreignerYn; //외국인여부

    private Yn soldierYn; //장기복무중인군인여부

    private LocalDate marriageDate; //혼인신고일

    private LocalDate homelessStartDate; //무주택시작일

    private LocalDate transferDate; //전입신고일

    private Integer income; //월평균소득

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;


    @Builder
    public HouseMemberResponseDto(HouseMember houseMember, Relation relation) {
        this.id = houseMember.getId();
        this.houseId = houseMember.getHouse().getId();
        this.relation = relation;
        this.name = houseMember.getName();
        this.birthDay = houseMember.getBirthDay();
        this.foreignerYn = houseMember.getForeignerYn();
        this.soldierYn = houseMember.getSoldierYn();
        this.marriageDate = houseMember.getMarriageDate();
        this.homelessStartDate = houseMember.getHomelessStartDate();
        this.transferDate = houseMember.getTransferDate();
        this.income = houseMember.getIncome();
        this.createdDate = houseMember.getCreatedDate();
        this.modifiedDate = houseMember.getModifiedDate();
    }
}
//
//    public HouseMemberResponseDto getResponse(){
//        return HouseMemberResponseDto.builder()
//                .id(id)
//                .houseId(house.getId())
//                .name(name)
//                .birthDay(birthDay)
//                .foreignerYn(foreignerYn)
//                .soldierYn(soldierYn)
//                .marriageDate(marriageDate)
//                .homelessStartDate(homelessStartDate)
//                .transferDate(transferDate)
//                .income(income)
//                .relation()
//                .build();
//
//    }
//
//}
