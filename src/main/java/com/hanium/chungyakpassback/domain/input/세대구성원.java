package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table
public class 세대구성원 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대id")
    private 세대 세대;

    @OneToOne
    @JoinColumn(name = "배우자_세대구성원id")
    private 세대구성원 배우자;

    @OneToOne(mappedBy = "배우자")
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 상대배우자;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 세대주여부;

    @Column
    private String 이름;

    @Column
    private LocalDate 생년월일;

//    @Column
//    @Enumerated(EnumType.STRING)
//    private 여부 외국인여부;

    @Column
    private LocalDate 혼인신고일;

    @Column
    private LocalDate 무주택시작일;

    @Column
    private LocalDate 전입일자;

//    @Column
//    private LocalDate 전출일자;

    @Builder
    public 세대구성원(세대 세대, 여부 세대주여부, String 이름){
        this.세대 = 세대;
        this.세대주여부 = 세대주여부;
        this.이름 = 이름;
    }
}