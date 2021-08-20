package com.hanium.chungyakpassback.entity.input;


import com.hanium.chungyakpassback.entity.enumtype.Bank;
import com.hanium.chungyakpassback.entity.enumtype.BankbookType;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_user_bankbook")
public class UserBankbook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_bankbook_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @Enumerated(EnumType.STRING)
    private Bank bank; //개설은행

    @Column
    @Enumerated(EnumType.STRING)
    private BankbookType bankbookType; //청약통장종류

    @Column
    private LocalDate joinDate; //가입일

    @Column
    private int deposit; //예치금

    @Column
    private int paymentsCount; //납입횟수

    @Column
    @Enumerated(EnumType.STRING)
    private Yn validYn; //유효여부


    @Builder
    public UserBankbook(User user, Bank bank, BankbookType bankbookType, LocalDate joinDate, int deposit, int paymentsCount, Yn validYn) {
        this.user = user;
        this.bank = bank;
        this.bankbookType = bankbookType;
        this.joinDate = joinDate;
        this.deposit = deposit;
        this.paymentsCount = paymentsCount;
        this.validYn = validYn;
    }
}
