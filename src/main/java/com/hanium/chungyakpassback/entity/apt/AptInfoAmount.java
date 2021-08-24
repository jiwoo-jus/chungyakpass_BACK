package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="std_aptinfo_amount")
public class AptInfoAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aptinfo_amount_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo AptInfo;

    @Column
    private String housingType;

    @Column
    private String supplyAmount;

    @Builder
    public AptInfoAmount(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, String supplyAmount) {
        AptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyAmount = supplyAmount;
    }
}
