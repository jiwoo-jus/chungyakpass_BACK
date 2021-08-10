package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.*;
import com.hanium.chungyakpassback.domain.standard.아파트분양정보;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "입력_세대구성원_청약신청이력")
public class 세대구성원_청약신청이력 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_청약신청이력id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원;

    @Column
    private String 주택명;

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
    private 결과 결과;

    @Column
    private Integer 예비번호;

    @Column
    private LocalDate 당첨일;

    @Column
    @Enumerated(EnumType.STRING)
    private 추첨방식 추첨방식;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 당첨취소여부;


    @Builder
    public 세대구성원_청약신청이력(세대구성원 세대구성원, String 주택명, 공급유형 공급유형, 특별공급유형 특별공급유형, String 주택형, 순위 순위, 결과 결과, Integer 예비번호, LocalDate 당첨일, 추첨방식 추첨방식, 여부 당첨취소여부) {
        this.세대구성원 = 세대구성원;
        this.주택명 = 주택명;
        this.공급유형 = 공급유형;
        this.특별공급유형 = 특별공급유형;
        this.주택형 = 주택형;
        this.순위 = 순위;
        this.결과 = 결과;
        this.예비번호 = 예비번호;
        this.당첨일 = 당첨일;
        this.추첨방식 = 추첨방식;
        this.당첨취소여부 = 당첨취소여부;
    }
}
