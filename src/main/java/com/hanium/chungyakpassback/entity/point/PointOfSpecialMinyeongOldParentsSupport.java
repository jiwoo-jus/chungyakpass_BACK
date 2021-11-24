package com.hanium.chungyakpassback.entity.point;

import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_of_special_minyeong_old_parents_support")
public class PointOfSpecialMinyeongOldParentsSupport extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_of_special_minyeong_old_parents_support_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    Long houseMemberId;

    @Column
    Yn parentsDeathYn;

    @Column
    Yn divorceYn;

    @Column
    Yn sameResidentRegistrationYn;

    @Column
    Yn stayOverYn;

    @Column
    Yn nowStayOverYn;

    @Column
    Integer periodOfHomelessness;

    @Column
    Integer bankbookJoinPeriod;

    @Column
    Integer numberOfDependents;

    @Column
    boolean bankBookVaildYn;

    @Column
    Integer total;

    @Builder
    public PointOfSpecialMinyeongOldParentsSupport(User user, Long houseMemberId, Yn parentsDeathYn, Yn divorceYn, Yn sameResidentRegistrationYn, Yn stayOverYn, Yn nowStayOverYn, Integer periodOfHomelessness, Integer bankbookJoinPeriod, Integer numberOfDependents, boolean bankBookVaildYn, Integer total) {
        this.user = user;
        this.houseMemberId = houseMemberId;
        this.parentsDeathYn = parentsDeathYn;
        this.divorceYn = divorceYn;
        this.sameResidentRegistrationYn = sameResidentRegistrationYn;
        this.stayOverYn = stayOverYn;
        this.nowStayOverYn = nowStayOverYn;
        this.periodOfHomelessness = periodOfHomelessness;
        this.bankbookJoinPeriod = bankbookJoinPeriod;
        this.numberOfDependents = numberOfDependents;
        this.bankBookVaildYn = bankBookVaildYn;
        this.total = total;
    }
}
