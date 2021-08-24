package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_aptinfo_target_special")
public class AptInfoTargetSpecial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aptinfo_target_special_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo AptInfo;

    @Column
    private String housingType;
    @Column
    private int supplyMultiChildHousehold;//공급세대수_다자녀가구;

    @Column
    private int supplyNewlyMarriedCouple;//공급세대수_신혼부부

    @Column
    private int supplyFirstLife;//공급세대수_생애최초

    @Column
    private int supplyOldParentSupport;//공급세대수_노부모부양

    @Column
    private int supplyInstitutionalRecommendation;//공급세대수_기관추천

    @Column
    private Integer supplyTransferAgency;//공급세대수_이전기관

    @Column
    private Integer supplyOther;//공급세대수_기타

    @Builder
    public AptInfoTargetSpecial(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, String housingType, int supplyMultiChildHousehold, int supplyNewlyMarriedCouple, int supplyFirstLife, int supplyOldParentSupport, int supplyInstitutionalRecommendation, Integer supplyTransferAgency, Integer supplyOther) {
        AptInfo = aptInfo;
        this.housingType = housingType;
        this.supplyMultiChildHousehold = supplyMultiChildHousehold;
        this.supplyNewlyMarriedCouple = supplyNewlyMarriedCouple;
        this.supplyFirstLife = supplyFirstLife;
        this.supplyOldParentSupport = supplyOldParentSupport;
        this.supplyInstitutionalRecommendation = supplyInstitutionalRecommendation;
        this.supplyTransferAgency = supplyTransferAgency;
        this.supplyOther = supplyOther;
    }
}
