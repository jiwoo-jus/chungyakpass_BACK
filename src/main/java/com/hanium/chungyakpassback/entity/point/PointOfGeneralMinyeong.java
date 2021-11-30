package com.hanium.chungyakpassback.entity.point;

import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "point_of_general_minyeong")
public class PointOfGeneralMinyeong extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_of_general_minyeong_id")
    private Long id; //일반민영 가점id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //회원

    @Column
    Integer periodOfHomelessness; //무주택기간 가점

    @Column
    Integer bankbookJoinPeriod; //청약통장가입기간 가점

    @Column
    Integer numberOfDependents; //부양가족수 가점

    @Column
    boolean bankBookValidTf; //청약통장 유효여부 가점

    @Column
    Integer total; //가점 총합

    @Builder
    public PointOfGeneralMinyeong(User user, Integer periodOfHomelessness, Integer bankbookJoinPeriod, Integer numberOfDependents, boolean bankBookVaildYn, Integer total) {
        this.user = user;
        this.periodOfHomelessness = periodOfHomelessness;
        this.bankbookJoinPeriod = bankbookJoinPeriod;
        this.numberOfDependents = numberOfDependents;
        this.bankBookValidTf = bankBookVaildYn;
        this.total = total;
    }
}
