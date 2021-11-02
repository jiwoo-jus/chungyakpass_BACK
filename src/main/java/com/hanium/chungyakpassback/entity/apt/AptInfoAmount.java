package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="apt_info_amount")
@IdClass(AptInfoAmountKey.class)
public class AptInfoAmount {

    @Id
    private String housingType;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;

    @Column
    private String supplyAmount;

    @Builder
    public AptInfoAmount(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, String supplyAmount) {
        this.aptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyAmount = supplyAmount;
    }
}
