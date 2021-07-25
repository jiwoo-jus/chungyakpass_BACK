package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.자산유형;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table
public class 세대구성원_소득자산 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_소득자산id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원;

    @Column
    private int 월평균소득;

    @Column
    @Enumerated(EnumType.STRING)
    private 자산유형 자산유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 분양권여부;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 주거용여부;




}
