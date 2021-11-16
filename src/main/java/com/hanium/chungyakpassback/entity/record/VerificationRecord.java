package com.hanium.chungyakpassback.entity.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.enumtype.VerificationResult;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_verification_record")
public class VerificationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "verification_record_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "verificationRecord")
    private VerificationRecordGeneralResponse verificationRecordGeneralResponse; //청약자격결과_일반

    @OneToOne(mappedBy = "verificationRecord")
    private VerificationRecordGeneralMinyeongRequest verificationRecordGeneralMinyeongRequest; //청약자격결과_일반국민

    @OneToOne(mappedBy = "verificationRecord")
    private VerificationRecordGeneralKookminRequest verificationRecordGeneralKookminRequest; //청약자격결과_일반민영

    @Column
    @Enumerated(EnumType.STRING)
    private VerificationResult verificationResult; //청약자격결과종류

    @Builder
    public VerificationRecord(User user, VerificationRecordGeneralResponse verificationRecordGeneralResponse, VerificationRecordGeneralMinyeongRequest verificationRecordGeneralMinyeongRequest, VerificationRecordGeneralKookminRequest verificationRecordGeneralKookminRequest, VerificationResult verificationResult) {
        this.user = user;
        this.verificationRecordGeneralResponse = verificationRecordGeneralResponse;
        this.verificationRecordGeneralMinyeongRequest = verificationRecordGeneralMinyeongRequest;
        this.verificationRecordGeneralKookminRequest = verificationRecordGeneralKookminRequest;
        this.verificationResult = verificationResult;
    }


}
