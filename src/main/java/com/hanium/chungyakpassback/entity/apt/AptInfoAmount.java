package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="apt_info_amount")
@IdClass(AptInfoAmountKey.class)
public class AptInfoAmount {

    @Id
    private String housingType; //주택형

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo; //아파트분양정보

    @Column
    private String supplyAmount; //공급 금액

    @Builder
    public AptInfoAmount(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, String supplyAmount) {
        this.aptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyAmount = supplyAmount;
    }
}
