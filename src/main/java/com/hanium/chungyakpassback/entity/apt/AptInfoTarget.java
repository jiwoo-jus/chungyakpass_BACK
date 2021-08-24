package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_aptinfo_target")
public class AptInfoTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_aptinfo_target_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo AptInfo;

    @Column
    private String housingType;//주택형

    @Column
    private Double supplyArea;//공급 면적

    @Column
    private Integer supplyGeneral;//공급일반

    @Column
    private Integer supplySpecial;//공급특별

    @Column
    private Integer supplyTotal;//공급합


    @Builder
    public AptInfoTarget(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, Double supplyArea, Integer supplyGeneral, Integer supplySpecial, Integer supplyTotal) {
        AptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyArea = supplyArea;
        this.supplyGeneral = supplyGeneral;
        this.supplySpecial = supplySpecial;
        this.supplyTotal = supplyTotal;
    }
}
