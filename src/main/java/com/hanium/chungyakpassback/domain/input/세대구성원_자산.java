package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.비주거용건물유형;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.자산유형;
import com.hanium.chungyakpassback.domain.enumtype.주거용건물유형;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="입력_세대구성원_자산")
public class 세대구성원_자산 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_자산id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원;


    @Column
    @Enumerated(EnumType.STRING)
    private 자산유형 자산유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 분양권여부;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 주거용여부;

    @Column
    @Enumerated(EnumType.STRING)
    private 주거용건물유형 주거용건물유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 비주거용건물유형 비주거용건물유형;

    @Column
    private LocalDate 취득일;

    @Column
    private LocalDate 처분일;

    @Column
    private Integer 전용면적;

    @Column
    private Integer 금액;

    @Column
    private LocalDate 과세기준일;


    @Builder
    public 세대구성원_자산(세대구성원 세대구성원, 자산유형 자산유형, 여부 분양권여부, 여부 주거용여부, 주거용건물유형 주거용건물유형, 비주거용건물유형 비주거용건물유형, LocalDate 취득일, LocalDate 처분일, Integer 전용면적, Integer 금액, LocalDate 과세기준일) {
        this.세대구성원 = 세대구성원;
        this.자산유형 = 자산유형;
        this.분양권여부 = 분양권여부;
        this.주거용여부 = 주거용여부;
        this.주거용건물유형 = 주거용건물유형;
        this.비주거용건물유형 = 비주거용건물유형;
        this.취득일 = 취득일;
        this.처분일 = 처분일;
        this.전용면적 = 전용면적;
        this.금액 = 금액;
        this.과세기준일 = 과세기준일;
    }
}
