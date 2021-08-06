package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.관계;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "입력_세대구성원_관계")
public class 세대구성원_관계 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_관계id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원id")
    private 회원 회원;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "관계자_세대구성원id")
    private 세대구성원 관계자_세대구성원;

    @Column
    @Enumerated(EnumType.STRING)
    private 관계 관계;


    @Builder
    public 세대구성원_관계(회원 회원, 세대구성원 관계자_세대구성원, 관계 관계) {
        this.회원 = 회원;
        this.관계자_세대구성원 = 관계자_세대구성원;
        this.관계 = 관계;
    }
}
