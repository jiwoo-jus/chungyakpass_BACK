package com.hanium.chungyakpassback.entity.apt;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTargetKey;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "apt_info_target")
@IdClass(AptInfoTargetKey.class)
public class AptInfoTarget {

    @Id
    private String housingType;//주택형

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;

    @Column
    private Double supplyArea;//공급면적

    @Column
    private Integer supplyGeneral;//공급일반

    @Column
    private Integer supplySpecial;//공급특별

    @Column
    private Integer supplyTotal;//공급합

    @Builder
    public AptInfoTarget(AptInfo aptInfo, String housingType, Double supplyArea, Integer supplyGeneral, Integer supplySpecial, Integer supplyTotal) {
        this.aptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyArea = supplyArea;
        this.supplyGeneral = supplyGeneral;
        this.supplySpecial = supplySpecial;
        this.supplyTotal = supplyTotal;
    }
}