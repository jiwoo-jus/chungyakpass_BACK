package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.공급유형;
import com.hanium.chungyakpassback.domain.enumtype.순위;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.특별공급유형;
import com.hanium.chungyakpassback.domain.standard.아파트분양정보;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="입력_청약자격점검")
public class 청약자격점검 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약자격점검id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원id")
    private 회원 회원;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "아파트분양정보id")
    private com.hanium.chungyakpassback.domain.standard.아파트분양정보 아파트분양정보;

    @Column
    @Enumerated(EnumType.STRING)
    private 공급유형 공급유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 특별공급유형 특별공급유형;

    @Column
    private String 주택형;

    @Column
    @Enumerated(EnumType.STRING)
    private 순위 순위;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 주택처분서약;

    @Column
    private int 부양가족수;

    @Column
    private int 청약지역거주기간;

    @Column
    private boolean 결과_청약자격;

    @Column
    private int 결과_점수;


    @Builder
    public 청약자격점검(회원 회원, 아파트분양정보 아파트분양정보, 공급유형 공급유형, 특별공급유형 특별공급유형, String 주택형, 순위 순위, 여부 주택처분서약, int 부양가족수, int 청약지역거주기간, boolean 결과_청약자격, int 결과_점수) {
        this.회원 = 회원;
        this.아파트분양정보 = 아파트분양정보;
        this.공급유형 = 공급유형;
        this.특별공급유형 = 특별공급유형;
        this.주택형 = 주택형;
        this.순위 = 순위;
        this.주택처분서약 = 주택처분서약;
        this.부양가족수 = 부양가족수;
        this.청약지역거주기간 = 청약지역거주기간;
        this.결과_청약자격 = 결과_청약자격;
        this.결과_점수 = 결과_점수;
    }
}
