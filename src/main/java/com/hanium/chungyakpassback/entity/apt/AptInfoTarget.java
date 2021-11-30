package com.hanium.chungyakpassback.entity.apt;


import lombok.*;
import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "apt_info_target")
@IdClass(AptInfoTargetKey.class)
public class AptInfoTarget {

    @Id
    @Column(name = "apt_info_target_id_residential_area")
    private String residentialArea;//주택형

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_info_target_id_notification_number")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo; //아파트분양정보

    @Column
    private Double supplyArea;//공급면적

    @Column
    private Integer supplyGeneral;//공급일반

    @Column
    private Integer supplySpecial;//공급특별

    @Column
    private Integer supplyTotal;//공급합

    @Builder
    public AptInfoTarget(AptInfo aptInfo, String residentialArea, Double supplyArea, Integer supplyGeneral, Integer supplySpecial, Integer supplyTotal) {
        this.aptInfo = aptInfo;
        this.residentialArea = residentialArea;
        this.supplyArea = supplyArea;
        this.supplyGeneral = supplyGeneral;
        this.supplySpecial = supplySpecial;
        this.supplyTotal = supplyTotal;
    }
}