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
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo;

    @Column
    private Integer numberOfMinors;

    @Column
    private Integer periodOfMarriged;

    @Column
    private Integer bankbookPaymentsCount;

    @Column
    private Integer periodOfApplicableAreaResidence;

    @Column
    private Integer monthOfAverageIncome;

    @Column
    private Integer total;
//
//    @Column
//    LocalDateTime createdDate;
//
//    @Column
//    LocalDateTime modifiedDate;

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
//        this.createdDate = createdDate;
//        this.modifiedDate = modifiedDate;

    }


}

