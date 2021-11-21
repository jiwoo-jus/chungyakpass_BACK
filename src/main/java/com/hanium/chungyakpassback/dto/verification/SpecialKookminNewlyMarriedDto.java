package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialKookminNewlyMarriedDto {

    @NotNull
    public Integer notificationNumber;

    @NotNull
    public String housingType;

//    public Yn sibilingSupportYn; //형제자매부양여부
//
//    public Yn preNewMarriedYn; //예비신혼부부여부
//
//    public KookminType kookminType; //국민주택유형
//
//    public Ranking ranking; //순위

}
