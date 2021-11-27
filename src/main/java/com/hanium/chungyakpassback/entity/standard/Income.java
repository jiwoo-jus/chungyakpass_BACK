package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.HousingType;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="std_monthly_average_income")
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monthly_average_income_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn applicationPublicHousingSpecialLaws;

    @Column
    @Enumerated(EnumType.STRING)
    private HousingType housingType;

    @Column
    @Enumerated(EnumType.STRING)
    private Supply supply;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialSupply specialSupply;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn dualIncome;

    @Column
    private Integer monthlyAverageIncomeExcess;

    @Column
    private int monthlyAverageIncomeBelow;

    @Column
    private Integer averageMonthlyIncome3peopleLessExcess;

    @Column
    private int averageMonthlyIncome3peopleLessBelow;

    @Column
    private Integer averageMonthlyIncome4peopleLessExcess;

    @Column
    private Integer averageMonthlyIncome4peopleLessBelow;

    @Column
    private Integer averageMonthlyIncome5peopleLessExcess;

    @Column
    private Integer averageMonthlyIncome5peopleLessBelow;

    @Column
    private Integer averageMonthlyIncome6peopleLessExcess;

    @Column
    private Integer averageMonthlyIncome6peopleLessBelow;

    @Column
    private Integer averageMonthlyIncome7peopleLessExcess;

    @Column
    private Integer averageMonthlyIncome7peopleLessBelow;

    @Column
    private Integer averageMonthlyIncome8peopleLessExcess;

    @Column
    private Integer averageMonthlyIncome8peopleLessBelow;

}
