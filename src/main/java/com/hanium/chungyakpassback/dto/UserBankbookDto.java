package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.enumtype.Bank;
import com.hanium.chungyakpassback.entity.enumtype.BankbookType;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBankbookDto {

    private Bank bank; //개설은행

    private BankbookType bankbookType; //청약통장종류

    private LocalDate joinDate; //가입일

    private int deposit; //예치금

    private int paymentsCount; //납입횟수

    private Yn validYn; //유효여부
}
