//package com.hanium.chungyakpassback.entity.record;
//
//import lombok.*;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "inp_verification_record_general_minyeong_request")
//public class VerificationRecordGeneralMinyeongRequest {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "verification_record_general_minyeong_request_id")
//    private Long id;
//
////    @OneToOne
////    @JoinColumn(name = "verification_record_id")
////    private VerificationRecord verificationRecord; //청약자격결과
//
//    @Column
//    private boolean meetLivingInSurroundAreaTf; //인근지역거주조건충족여부
//
//    @Column
//    private boolean accountTf; //청약통장유형조건충족여부
//
//    @Column
//    private boolean householderTf; //세대주여부
//
//    @Column
//    private boolean meetAllHouseMemberNotWinningIn5yearsTf; //전세대원5년이내미당첨조건충족여부
//
//    @Column
//    private boolean meetAllHouseMemberRewinningRestrictionTf; //전세대원재당첨제한여부
//
//    @Column
//    private boolean meetHouseHavingLessThan2AptTf; //소유주택2개미만세대충족여부
//
//    @Column
//    private boolean meetBankbookJoinPeriodTf; //가입기간충족여부
//
//    @Column
//    private boolean restrictedAreaTf; //규제지역여부
//
//    @Column
//    private boolean priorityApt; //주거전용85초과공공건설임대주택,수도권에지정된공공주택에서공급하는민영주택청약여부
//
//    @Builder
//    public VerificationRecordGeneralMinyeongRequest(Long id, VerificationRecord verificationRecord, boolean meetLivingInSurroundAreaTf, boolean accountTf, boolean householderTf, boolean meetAllHouseMemberNotWinningIn5yearsTf, boolean meetAllHouseMemberRewinningRestrictionTf, boolean meetHouseHavingLessThan2AptTf, boolean meetBankbookJoinPeriodTf, boolean restrictedAreaTf, boolean priorityApt) {
//        this.id = id;
//        this.verificationRecord = verificationRecord;
//        this.meetLivingInSurroundAreaTf = meetLivingInSurroundAreaTf;
//        this.accountTf = accountTf;
//        this.householderTf = householderTf;
//        this.meetAllHouseMemberNotWinningIn5yearsTf = meetAllHouseMemberNotWinningIn5yearsTf;
//        this.meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestrictionTf;
//        this.meetHouseHavingLessThan2AptTf = meetHouseHavingLessThan2AptTf;
//        this.meetBankbookJoinPeriodTf = meetBankbookJoinPeriodTf;
//        this.restrictedAreaTf = restrictedAreaTf;
//        this.priorityApt = priorityApt;
//    }
//
//}
