package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="apt_info_amount")
public class AptInfoAmount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "info_amount_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;

    @Column
    private String housingType;

    @Column
    private String supplyAmount;

    @Builder
    public AptInfoAmount(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, String supplyAmount) {
        this.aptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyAmount = supplyAmount;
    }
}
