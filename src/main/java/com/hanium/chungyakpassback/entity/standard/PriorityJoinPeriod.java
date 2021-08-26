package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_priority_subscription_period")
public class PriorityJoinPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_subscription_period_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Supply supply;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialSupply specialSupply;


    @Column
    @Enumerated(EnumType.STRING)
    private Yn speculationOverheated;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn subscriptionOverheated;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn atrophyArea;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn metropolitanAreaYn;

    @Column
    private int subscriptionPeriod;

    @Builder
    public PriorityJoinPeriod(Supply supply, SpecialSupply specialSupply, Yn speculationOverheated, Yn subscriptionOverheated, Yn atrophy_area, Yn metropolitanAreaYn, int subscriptionPeriod) {
        this.supply = supply;
        this.specialSupply = specialSupply;
        this.speculationOverheated = speculationOverheated;
        this.subscriptionOverheated = subscriptionOverheated;
        this.atrophyArea = atrophy_area;
        this.metropolitanAreaYn = metropolitanAreaYn;
        this.subscriptionPeriod = subscriptionPeriod;
    }
}
