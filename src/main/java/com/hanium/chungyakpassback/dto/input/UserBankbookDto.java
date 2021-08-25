package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.enumtype.Bank;
import com.hanium.chungyakpassback.enumtype.Bankbook;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBankbookDto {

    private Bank bank; //개설은행

    private Bankbook bankbook; //청약통장종류

    private LocalDate joinDate; //가입일

    private int deposit; //예치금

    private int paymentsCount; //납입횟수

    private Yn validYn; //유효여부
}
