package com.hanium.chungyakpassback.entity.input;


import com.hanium.chungyakpassback.dto.input.UserBankbookDto;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.enumtype.Bank;
import com.hanium.chungyakpassback.enumtype.Bankbook;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_user_bankbook")
public class UserBankbook extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_bankbook_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Bank bank; //개설은행

    @Column
    @Enumerated(EnumType.STRING)
    private Bankbook bankbook; //청약통장종류

    @Column
    private LocalDate joinDate; //가입일

    @Column
    private int deposit; //예치금

    @Column
    private int paymentsCount; //납입횟수

    @Builder
    public UserBankbook(User user, Bank bank, Bankbook bankbook, LocalDate joinDate, int deposit, int paymentsCount) {
        this.user = user;
        this.bank = bank;
        this.bankbook = bankbook;
        this.joinDate = joinDate;
        this.deposit = deposit;
        this.paymentsCount = paymentsCount;
    }

    public UserBankbook updateUserBankbook(UserBankbookDto userBankbookDto){
        this.bank = userBankbookDto.getBank();
        this.bankbook = userBankbookDto.getBankbook();
        this.joinDate = userBankbookDto.getJoinDate();
        this.deposit = userBankbookDto.getDeposit();
        this.paymentsCount = userBankbookDto.getPaymentsCount();
        return this;
    }

//    @Column
//    @Enumerated(EnumType.STRING)
//    private Yn validYn; //유효여부
//
//
//    @Builder
//    public UserBankbook(User user, Bank bank, Bankbook bankbook, LocalDate joinDate, int deposit, int paymentsCount, Yn validYn) {
//        this.user = user;
//        this.bank = bank;
//        this.bankbook = bankbook;
//        this.joinDate = joinDate;
//        this.deposit = deposit;
//        this.paymentsCount = paymentsCount;
//        this.validYn = validYn;
//    }
//
//    public UserBankbook updateUserBankbook(UserBankbookDto userBankbookDto){
//        this.bank = userBankbookDto.getBank();
//        this.bankbook = userBankbookDto.getBankbook();
//        this.joinDate = userBankbookDto.getJoinDate();
//        this.deposit = userBankbookDto.getDeposit();
//        this.paymentsCount = userBankbookDto.getPaymentsCount();
//        this.validYn = userBankbookDto.getValidYn();
//        return this;
//    }
}
