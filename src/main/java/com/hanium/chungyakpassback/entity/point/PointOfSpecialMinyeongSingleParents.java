package com.hanium.chungyakpassback.entity.point;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_of_special_minyeong_single_parents")
public class PointOfSpecialMinyeongSingleParents extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_of_special_minyeong_single_parents_id")
    private Long id; //특별한부모 가점id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private AptInfo aptInfo; //아파트분양정보

    @Column
    private Integer numberOfMinors; //미성년자녀수 가점

    @Column
    private Integer ageOfMostYoungChild; //저녀나이 가점

    @Column
    private Integer bankbookPaymentsCount; //청약통장 납입횟수 가점

    @Column
    private Integer periodOfApplicableAreaResidence; //헤당지역 거주기간 가점

    @Column
    private Integer monthOfAverageIncome; //가구소득 가점

    @Column
    private Integer total; //가점 총합

    @Builder
    public PointOfSpecialMinyeongSingleParents(User user, AptInfo aptInfo, Integer numberOfMinors, Integer ageOfMostYoungChild, Integer bankbookPaymentsCount, Integer periodOfApplicableAreaResidence, Integer monthOfAverageIncome, Integer total) {
        this.user = user;
        this.aptInfo = aptInfo;
        this.numberOfMinors = numberOfMinors;
        this.ageOfMostYoungChild = ageOfMostYoungChild;
        this.bankbookPaymentsCount = bankbookPaymentsCount;
        this.periodOfApplicableAreaResidence = periodOfApplicableAreaResidence;
        this.monthOfAverageIncome = monthOfAverageIncome;
        this.total = total;
    }


}
