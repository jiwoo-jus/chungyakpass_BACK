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
    private Long id; //특별다자녀 가점id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo; //아파트분양정보

    @Column
    MultiChildHouseholdType multiChildHouseholdType; //세대구성

    @Column
    private Integer numberOfChild; //미성년자녀수 가점

    @Column
    private Integer numberOfChildUnder6Year; //영유아자녀수 가점

    @Column
    private Integer bankbookJoinPeriod; //청약저축 가입기간 가점

    @Column
    private Integer periodOfApplicableAreaResidence;//해당지역 거주기간  가점

    @Column
    private Integer periodOfHomelessness; //무주택기간 가점

    @Column
    private Integer generationComposition; //세대구성 가점

    @Column
    private Integer total; //가점 총합

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
