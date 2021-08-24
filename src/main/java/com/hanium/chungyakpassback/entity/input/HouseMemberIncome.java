package com.hanium.chungyakpassback.entity.input;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="inp_house_member")
public class HouseMemberIncome {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_income_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember;

    @Column
    private int income; //월평균소득


    @Builder
    public HouseMemberIncome(HouseMember houseMember, int income) {
        this.houseMember = houseMember;
        this.income = income;
    }
}
