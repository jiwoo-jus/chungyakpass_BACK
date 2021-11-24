package com.hanium.chungyakpassback.entity.point;

import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_of_special_minyeong_multi_child")
public class PointOfSpecialMinyeongMultiChild extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_of_special_minyeong_multi_child_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;

    @Column
    MultiChildHouseholdType multiChildHouseholdType;

    @Column
    private Integer numberOfChild;

    @Column
    private Integer numberOfChildUnder6Year;

    @Column
    private Integer bankbookJoinPeriod;

    @Column
    private Integer periodOfApplicableAreaResidence;

    @Column
    private Integer periodOfHomelessness;

    @Column
    private Integer generationComposition;

    @Column
    private Integer total;

    @Builder
    public PointOfSpecialMinyeongMultiChild(User user, com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, MultiChildHouseholdType multiChildHouseholdType, Integer numberOfChild, Integer numberOfChildUnder6Year, Integer bankbookJoinPeriod, Integer periodOfApplicableAreaResidence, Integer periodOfHomelessness, Integer generationComposition, Integer total) {
        this.user = user;
        this.aptInfo = aptInfo;
        this.multiChildHouseholdType = multiChildHouseholdType;
        this.numberOfChild = numberOfChild;
        this.numberOfChildUnder6Year = numberOfChildUnder6Year;
        this.bankbookJoinPeriod = bankbookJoinPeriod;
        this.periodOfApplicableAreaResidence = periodOfApplicableAreaResidence;
        this.periodOfHomelessness = periodOfHomelessness;
        this.generationComposition = generationComposition;
        this.total = total;
    }
}
