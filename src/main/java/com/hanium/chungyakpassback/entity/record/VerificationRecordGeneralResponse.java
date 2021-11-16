package com.hanium.chungyakpassback.entity.record;


import com.hanium.chungyakpassback.entity.input.HouseMemberChungyak;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.VerificationResult;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_verification_record_general_response")
public class VerificationRecordGeneralResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_record_general_response_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "verification_record_id")
    private VerificationRecord verificationRecord; //청약자격결과

    @Column(name = "notification_number_id")
    private Integer notificationNumber; //아파트분양정보

    @Column
    private String housingType; //주택형

    @Column
    @Enumerated(EnumType.STRING)
    private Yn sibilingSupportYn; //형제자매부양여부

    @Column
    @Enumerated(EnumType.STRING)
    private Yn twentiesSoleHouseHolderYn; //20대단독세대주여부

    @Column
    @Enumerated(EnumType.STRING)
    private Ranking ranking; //순위

    @Builder
    public VerificationRecordGeneralResponse(Long id, VerificationRecord verificationRecord, Integer notificationNumber, String housingType, Yn sibilingSupportYn, Yn twentiesSoleHouseHolderYn, Ranking ranking) {
        this.id = id;
        this.verificationRecord = verificationRecord;
        this.notificationNumber = notificationNumber;
        this.housingType = housingType;
        this.sibilingSupportYn = sibilingSupportYn;
        this.twentiesSoleHouseHolderYn = twentiesSoleHouseHolderYn;
        this.ranking = ranking;
    }
}
