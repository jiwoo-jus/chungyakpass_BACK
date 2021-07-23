package com.hanium.chungyakpassback.domain.standard;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.주택유형;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table
public class 아파트분양정보 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "아파트분양정보id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨1 지역_레벨1;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨2 지역_레벨2;

    @Column
    private int 공고번호;

    @Column
    private String 상세주소;

    @Column
    @Enumerated(EnumType.STRING)
    private 주택유형 주택유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 공공주택특별법적용;

    @Column
    private String 주택명;

    @Column
    private String 건설업체;

    @Column
    private String 문의처;

    @Column
    private LocalDate 모집공고일;

    @Column
    private LocalDate 당첨자발표일;

    @Column
    private LocalDate 계약시작일;

    @Column
    private LocalDate 계약종료일;

    @Column
    private LocalDate 입주예정월;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 투기과열지구;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 청약과열지역;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 위축지역;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 분양가상한제;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 정비사업;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 공공주택지구;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 공공건설임대주택;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 대규모택지개발지구;

    @Builder
    public 아파트분양정보(com.hanium.chungyakpassback.domain.enumtype.지역_레벨1 지역_레벨1, com.hanium.chungyakpassback.domain.enumtype.지역_레벨2 지역_레벨2, int 공고번호, String 상세주소, com.hanium.chungyakpassback.domain.enumtype.주택유형 주택유형, 여부 공공주택특별법적용, String 주택명, String 건설업체, String 문의처, LocalDate 모집공고일, LocalDate 당첨자발표일, LocalDate 계약시작일, LocalDate 계약종료일, LocalDate 입주예정월, 여부 투기과열지구, 여부 청약과열지역, 여부 위축지역, 여부 분양가상한제, 여부 정비사업, 여부 공공주택지구, 여부 공공건설임대주택, 여부 대규모택지개발지구) {
        this.지역_레벨1 = 지역_레벨1;
        this.지역_레벨2 = 지역_레벨2;
        this.공고번호 = 공고번호;
        this.상세주소 = 상세주소;
        this.주택유형 = 주택유형;
        this.공공주택특별법적용 = 공공주택특별법적용;
        this.주택명 = 주택명;
        this.건설업체 = 건설업체;
        this.문의처 = 문의처;
        this.모집공고일 = 모집공고일;
        this.당첨자발표일 = 당첨자발표일;
        this.계약시작일 = 계약시작일;
        this.계약종료일 = 계약종료일;
        this.입주예정월 = 입주예정월;
        this.투기과열지구 = 투기과열지구;
        this.청약과열지역 = 청약과열지역;
        this.위축지역 = 위축지역;
        this.분양가상한제 = 분양가상한제;
        this.정비사업 = 정비사업;
        this.공공주택지구 = 공공주택지구;
        this.공공건설임대주택 = 공공건설임대주택;
        this.대규모택지개발지구 = 대규모택지개발지구;
    }
}