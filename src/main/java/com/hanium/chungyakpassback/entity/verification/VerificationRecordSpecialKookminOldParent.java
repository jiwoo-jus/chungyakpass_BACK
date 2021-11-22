package com.hanium.chungyakpassback.entity.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_verification_record_special_kookmin_old_parent")
public class VerificationRecordSpecialKookminOldParent extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_record_special_kookmin_old_parent_id")
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
    private boolean meetMonthlyAverageIncome; //월평균소득기준충족여부

    @Column
    private boolean meetPropertyTf; //자산기준충족여부

    @Column
    private boolean meetOldParentSupportMore3yearsTf; //3년이상노부모부양충족여부

    @Column
    private boolean meetHomelessHouseholdMemberTf; //전세대원무주택구성원충족여부

    @Column
    private boolean householderTf; //세대주여부

    @Column
    private boolean restrictedAreaTf; //규제지역여부

    @Column
    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부

    @Column
    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부

    @Column
    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부

    @Column
    private boolean meetNumberOfPaymentsTf; //납입횟수충족여부

    //아래는 프론트한테 받는 항목들

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private AptInfo aptInfo; //아파트분양정보

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "info_target_id")
    private AptInfoTarget aptInfoTarget; //주택형

    //아래는 프론트한테 추가로 받는 항목들

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    public Yn sibilingSupportYn; //형제자매부양여부

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    public KookminType kookminType; //국민주택종류

    @Setter
    @Column
    @Enumerated(EnumType.STRING)
    public Ranking ranking; //순위

    @Builder
    public VerificationRecordSpecialKookminOldParent(User user, Integer americanAge, boolean meetLivingInSurroundAreaTf, boolean accountTf, boolean meetMonthlyAverageIncome, boolean meetPropertyTf, boolean meetOldParentSupportMore3yearsTf, boolean meetHomelessHouseholdMemberTf, boolean householderTf, boolean restrictedAreaTf, boolean meetAllHouseMemberNotWinningIn5yearsTf, boolean meetAllHouseMemberRewinningRestrictionTf, boolean meetBankbookJoinPeriodTf, boolean meetNumberOfPaymentsTf, AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        this.user = user;
        this.americanAge = americanAge;
        this.meetLivingInSurroundAreaTf = meetLivingInSurroundAreaTf;
        this.accountTf = accountTf;
        this.meetMonthlyAverageIncome = meetMonthlyAverageIncome;
        this.meetPropertyTf = meetPropertyTf;
        this.meetOldParentSupportMore3yearsTf = meetOldParentSupportMore3yearsTf;
        this.meetHomelessHouseholdMemberTf = meetHomelessHouseholdMemberTf;
        this.householderTf = householderTf;
        this.restrictedAreaTf = restrictedAreaTf;
        this.meetAllHouseMemberNotWinningIn5yearsTf = meetAllHouseMemberNotWinningIn5yearsTf;
        this.meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestrictionTf;
        this.meetBankbookJoinPeriodTf = meetBankbookJoinPeriodTf;
        this.meetNumberOfPaymentsTf = meetNumberOfPaymentsTf;
        this.aptInfo = aptInfo;
        this.aptInfoTarget = aptInfoTarget;
    }
}
