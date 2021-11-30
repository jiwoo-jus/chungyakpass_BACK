package com.hanium.chungyakpassback.entity.point;


import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_of_special_minyeong_newly_married")
public class PointOfSpecialMinyeongNewlyMarried extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_of_special_minyeong_newly_married_id")
    private Long id; //특별신혼부부 가점id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;//회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;//아파트분양정보

    @Column
    private Integer numberOfMinors; //미성년 자녀 수 가점

    @Column
    private Integer periodOfMarriged; //혼인기간 가점

    @Column
    private Integer bankbookPaymentsCount; //청약저축 가입기간 가점

    @Column
    private Integer periodOfApplicableAreaResidence; //해당지역 거주기간 가점

    @Column
    private Integer monthOfAverageIncome; //가구소득 가점

    @Column
    private Integer total; //가점 총합

    @Builder
    public PointOfSpecialMinyeongNewlyMarried(User user, com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, Integer numberOfMinors, Integer periodOfMarriged, Integer bankbookPaymentsCount, Integer periodOfApplicableAreaResidence, Integer monthOfAverageIncome, Integer total) {
        this.user = user;
        this.aptInfo = aptInfo;
        this.numberOfMinors = numberOfMinors;
        this.periodOfMarriged = periodOfMarriged;
        this.bankbookPaymentsCount = bankbookPaymentsCount;
        this.periodOfApplicableAreaResidence = periodOfApplicableAreaResidence;
        this.monthOfAverageIncome = monthOfAverageIncome;
        this.total = total;
    }


}

