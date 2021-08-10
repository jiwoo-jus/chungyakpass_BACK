package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "입력_세대구성원")
public class 세대구성원 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대id")
    private 세대 세대;

    @OneToOne
    @JoinColumn(name = "배우자_세대구성원id")
    private 세대구성원 세대구성원배우자;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 세대주여부;

    @Column
    private String 이름;

    @Column
    private LocalDate 생년월일;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 외국인여부;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 장기복무군인여부;

    @Column
    private LocalDate 혼인신고일;

    @Column
    private LocalDate 무주택시작일;

    @Column
    private LocalDate 전입신고일;


    @Builder
    public 세대구성원(세대 세대, 세대구성원 세대구성원배우자, 여부 세대주여부, String 이름, LocalDate 생년월일, 여부 외국인여부, 여부 장기복무군인여부, LocalDate 혼인신고일, LocalDate 무주택시작일, LocalDate 전입신고일) {
        this.세대 = 세대;
        this.세대구성원배우자 = 세대구성원배우자;
        this.세대주여부 = 세대주여부;
        this.이름 = 이름;
        this.생년월일 = 생년월일;
        this.외국인여부 = 외국인여부;
        this.장기복무군인여부 = 장기복무군인여부;
        this.혼인신고일 = 혼인신고일;
        this.무주택시작일 = 무주택시작일;
        this.전입신고일 = 전입신고일;
    }
}