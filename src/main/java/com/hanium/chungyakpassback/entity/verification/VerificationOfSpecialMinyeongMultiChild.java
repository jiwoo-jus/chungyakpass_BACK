package com.hanium.chungyakpassback.entity.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_verification_record_special_minyeong_multi_child")
public class VerificationOfSpecialMinyeongMultiChild extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_record_special_minyeong_multi_child_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private Integer americanAge; //만나이

    @Column
    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부

    @Column
    private boolean accountTf; //청약통장유형조건충족여부

    @Column
    private boolean meetHomelessHouseholdMemberTf; //전세대원무주택구성원충족여부

    @Column
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부

    @Column
    private Integer calcMinorChildren; //미성년자녀수계산(태아 포함)

    @Column
    private boolean householderTf; //세대주여부

    @Column
    private boolean restrictedAreaTf; //규제지역여부

    @Column
    private boolean meetHouseHavingLessThan2AptTf; //소유주택2개미만세대충족여부

    @Column
    private boolean priorityApt; //주거전용85초과공공건설임대주택,수도권에지정된공공주택에서공급하는민영주택청약여부

    @Column
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부

    @Column
    private boolean meetDepositTf; //예치금액충족여부

    //아래는 프론트한테 받는 항목들

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private AptInfo aptInfo; //아파트분양정보

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "info_target_id")
//    private AptInfoTarget aptInfoTarget; //주택형

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "info_target_id"),
            @JoinColumn(name = "id")
    })
    private AptInfoTarget aptInfoTarget; //주택형

    //아래는 프론트한테 추가로 받는 항목들

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    public Yn sibilingSupportYn; //형제자매부양여부

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    public Ranking ranking; //순위

    @Builder
    public VerificationOfSpecialMinyeongMultiChild(User user, Integer americanAge, boolean meetLivingInSurroundAreaTf, boolean accountTf, boolean meetHomelessHouseholdMemberTf, boolean meetAllHouseMemberRewinningRestrictionTf, Integer calcMinorChildren, boolean householderTf, boolean restrictedAreaTf, boolean meetHouseHavingLessThan2AptTf, boolean priorityApt, boolean meetBankbookJoinPeriodTf, boolean meetDepositTf, AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        this.user = user;
        this.americanAge = americanAge;
        this.meetLivingInSurroundAreaTf = meetLivingInSurroundAreaTf;
        this.accountTf = accountTf;
        this.meetHomelessHouseholdMemberTf = meetHomelessHouseholdMemberTf;
        this.meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestrictionTf;
        this.calcMinorChildren = calcMinorChildren;
        this.householderTf = householderTf;
        this.restrictedAreaTf = restrictedAreaTf;
        this.meetHouseHavingLessThan2AptTf = meetHouseHavingLessThan2AptTf;
        this.priorityApt = priorityApt;
        this.meetBankbookJoinPeriodTf = meetBankbookJoinPeriodTf;
        this.meetDepositTf = meetDepositTf;
        this.aptInfo = aptInfo;
        this.aptInfoTarget = aptInfoTarget;
    }
}
