package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.공급유형;
import com.hanium.chungyakpassback.domain.enumtype.공급장소;
import com.hanium.chungyakpassback.domain.enumtype.순위;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_아파트분양정보_청약접수일정")
public class 아파트분양정보_청약접수일정 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "아파트분양정보_청약접수일정id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "아파트분양정보id")
    private 아파트분양정보 아파트분양정보;

    @Column
    @Enumerated(EnumType.STRING)
    private 공급유형 공급유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 순위 순위;

    @Column
    private LocalDate 청약접수일_해당지역;

    @Column
    private LocalDate 청약접수완료일_해당지역;

    @Column
    private LocalDate 청약접수일_기타경기;

    @Column
    private LocalDate 청약접수완료일_기타경기;

    @Column
    private LocalDate 청약접수일_기타지역;

    @Column
    private LocalDate 청약접수완료일_기타지역;

    @Column
    @Enumerated(EnumType.STRING)
    private 공급장소 접수장소;

    @Builder
    public 아파트분양정보_청약접수일정(com.hanium.chungyakpassback.domain.standard.아파트분양정보 아파트분양정보, com.hanium.chungyakpassback.domain.enumtype.공급유형 공급유형, com.hanium.chungyakpassback.domain.enumtype.순위 순위, LocalDate 청약접수일_해당지역, LocalDate 청약접수완료일_해당지역, LocalDate 청약접수일_기타경기, LocalDate 청약접수완료일_기타경기, LocalDate 청약접수일_기타지역, LocalDate 청약접수완료일_기타지역, 공급장소 접수장소) {
        this.아파트분양정보 = 아파트분양정보;
        this.공급유형 = 공급유형;
        this.순위 = 순위;
        this.청약접수일_해당지역 = 청약접수일_해당지역;
        this.청약접수완료일_해당지역 = 청약접수완료일_해당지역;
        this.청약접수일_기타경기 = 청약접수일_기타경기;
        this.청약접수완료일_기타경기 = 청약접수완료일_기타경기;
        this.청약접수일_기타지역 = 청약접수일_기타지역;
        this.청약접수완료일_기타지역 = 청약접수완료일_기타지역;
        this.접수장소 = 접수장소;
    }
}
