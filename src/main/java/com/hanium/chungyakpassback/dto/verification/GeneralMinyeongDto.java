package com.hanium.chungyakpassback.dto.verification;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongDto {

    @NotNull
    private Integer notificationNumber;

    @NotNull
    private String housingType;

//    public Yn sibilingSupportYn; //형제자매부양여부
//
//    public Ranking ranking; //순위

}
