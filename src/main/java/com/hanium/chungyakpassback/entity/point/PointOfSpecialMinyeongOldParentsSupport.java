package com.hanium.chungyakpassback.entity.point;

import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
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
    private Long id; //특별노부모 가점id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //회원

    @Column
    Integer periodOfHomelessness; //무주택기간 가점

    @Column
    Integer bankbookJoinPeriod; //청약통장 가입기간 가점

    @Column
    Integer numberOfDependents; //부양가족수 가점

    @Column
    boolean bankBookVaildYn; //청약통장 유효여부

    @Column
    Integer total; //가점 총합

    @Builder
    public PointOfSpecialMinyeongOldParentsSupport(User user, Integer periodOfHomelessness, Integer bankbookJoinPeriod, Integer numberOfDependents, boolean bankBookVaildYn, Integer total) {
        this.user = user;
        this.periodOfHomelessness = periodOfHomelessness;
        this.bankbookJoinPeriod = bankbookJoinPeriod;
        this.numberOfDependents = numberOfDependents;
        this.bankBookVaildYn = bankBookVaildYn;
        this.total = total;
    }
}
