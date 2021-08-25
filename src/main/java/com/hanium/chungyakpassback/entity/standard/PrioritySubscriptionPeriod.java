package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.entity.enumtype.SpecialSupplyType;
import com.hanium.chungyakpassback.entity.enumtype.SupplyType;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_priority_subscription_period")
public class PrioritySubscriptionPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_subscription_period_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private SupplyType supplyType;

    @Column
    @Enumerated(EnumType.STRING)
    private SpecialSupplyType specialSupplyType;


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
    public PrioritySubscriptionPeriod(SupplyType supplyType, SpecialSupplyType specialSupplyType, Yn speculationOverheated, Yn subscriptionOverheated, Yn atrophy_area, Yn metropolitanAreaYn, int subscriptionPeriod) {
        this.supplyType = supplyType;
        this.specialSupplyType = specialSupplyType;
        this.speculationOverheated = speculationOverheated;
        this.subscriptionOverheated = subscriptionOverheated;
        this.atrophyArea = atrophy_area;
        this.metropolitanAreaYn = metropolitanAreaYn;
        this.subscriptionPeriod = subscriptionPeriod;
    }
}
