package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.House;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberDto {

    @NotBlank
    private Long houseId; //세대id

    @NotBlank
    private Relation relation; //회원과의 관계

    @NotBlank
    private String name; //이름

    private LocalDate birthDay; //생년월일

    @NotBlank
    private Yn foreignerYn; //외국인여부

    @NotBlank
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