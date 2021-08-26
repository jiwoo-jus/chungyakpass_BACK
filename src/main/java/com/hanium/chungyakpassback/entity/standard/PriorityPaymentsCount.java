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
@Table(name = "std_priority_numeber_payments")
public class PriorityPaymentsCount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_numeber_payments_id")
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
    private int countPayments;

    @Builder
    public PriorityPaymentsCount(Supply supply, SpecialSupply specialSupplyType, Yn overheated_speculation_zone, Yn overheated_subscription_area, Yn atrophy_area, Yn metropolitanAreaYn, int number_payments) {
        this.supply = supply;
        this.specialSupply = specialSupplyType;
        this.speculationOverheated = overheated_speculation_zone;
        this.subscriptionOverheated = overheated_subscription_area;
        this.atrophyArea = atrophy_area;
        this.metropolitanAreaYn = metropolitanAreaYn;
        this.countPayments = number_payments;
    }
}
