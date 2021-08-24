package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberIncomeDto {

    private int income; //월평균소득

}
