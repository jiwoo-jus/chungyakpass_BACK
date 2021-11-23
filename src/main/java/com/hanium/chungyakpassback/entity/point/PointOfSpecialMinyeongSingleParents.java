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
@Table(name = "point_singleParents")
public class PointOfSpecialMinyeongSingleParents extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_singleParents_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private AptInfo aptInfo;

    @Column
    private Integer numberOfMinors;

    @Column
    private Integer ageOfMostYoungChild;

    @Column
    private Integer bankbookPaymentsCount;

    @Column
    private Integer periodOfApplicableAreaResidence;

    @Column
    private Integer monthOfAverageIncome;

    @Column
    private Integer total;

    @Builder
    public PointOfSpecialMinyeongSingleParents(User user, AptInfo aptInfo, Integer numberOfMinors, Integer ageOfMostYoungChild, Integer bankbookPaymentsCount, Integer periodOfApplicableAreaResidence , Integer monthOfAverageIncome, Integer total) {
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
