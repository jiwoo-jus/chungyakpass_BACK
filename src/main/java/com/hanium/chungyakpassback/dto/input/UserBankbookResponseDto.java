package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.enumtype.Bank;
import com.hanium.chungyakpassback.enumtype.Bankbook;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserBankbookResponseDto {

    private Long id;

    private Bank bank; //개설은행

    private Bankbook bankbook; //청약통장종류

    private LocalDate joinDate; //가입일

    private Integer deposit; //예치금

    private Integer paymentsCount; //납입횟수

    private Yn validYn; //유효여부

    @Builder
    public UserBankbookResponseDto(UserBankbook userBankbook){
        this.id = userBankbook.getId();
        this.bank = userBankbook.getBank();
        this.bankbook = userBankbook.getBankbook();
        this.joinDate = userBankbook.getJoinDate();
        this.deposit = userBankbook.getDeposit();
        this.paymentsCount = userBankbook.getPaymentsCount();
        this.validYn = userBankbook.getValidYn();
    }
}
