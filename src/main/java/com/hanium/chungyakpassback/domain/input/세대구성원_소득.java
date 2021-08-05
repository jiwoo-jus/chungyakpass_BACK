package com.hanium.chungyakpassback.domain.input;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="입력_세대구성원_소득")
public class 세대구성원_소득 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_소득id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원;

    @Column
    private int 월평균소득;


    @Builder
    public 세대구성원_소득(세대구성원 세대구성원, int 월평균소득) {
        this.세대구성원 = 세대구성원;
        this.월평균소득 = 월평균소득;
    }
}
