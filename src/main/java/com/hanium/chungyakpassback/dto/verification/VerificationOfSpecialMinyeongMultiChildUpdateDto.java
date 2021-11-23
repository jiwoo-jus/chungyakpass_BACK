package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationOfSpecialMinyeongMultiChildUpdateDto {

    @NotBlank
    private Yn sibilingSupportYn; //형제자매부양여부

    @NotBlank
    private Ranking ranking; //순위

}
