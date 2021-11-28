package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.enumtype.Bank;
import com.hanium.chungyakpassback.enumtype.Bankbook;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBankbookDto {

    private Bank bank; //개설은행

    @NotBlank
    private Bankbook bankbook; //청약통장종류

    private LocalDate joinDate; //가입일

    private Integer deposit; //예치금

    private Integer paymentsCount; //납입횟수

    public UserBankbook toEntity(User user){
        return UserBankbook.builder()
                .user(user)
                .bank(bank)
                .bankbook(bankbook)
                .joinDate(joinDate)
                .deposit(deposit)
                .paymentsCount(paymentsCount)
                .build();
    }

//    private Yn validYn; //유효여부
//
//    public UserBankbook toEntity(User user){
//        return UserBankbook.builder()
//                .user(user)
//                .bank(bank)
//                .bankbook(bankbook)
//                .joinDate(joinDate)
//                .deposit(deposit)
//                .paymentsCount(paymentsCount)
//                .validYn(validYn)
//                .build();
//    }
}