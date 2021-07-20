package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
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

    //배우자 세대구성원 id 자기참조 넣기

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 세대주여부;

    @Column
    private String 이름;

    @Column
    private Date 생년월일; // 년-월 로 date format 하기

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 외국인여부;

    @Column
    private Date 혼인신고일;

    @Column
    private Date 무주택시작일;

    @Column
    private Date 전입일자;

    @Column
    private Date 전출일자;

    @Builder
    public 세대구성원(세대 세대, 여부 세대주여부, String 이름, Date 생년월일, 여부 외국인여부, Date 혼인신고일, Date 무주택시작일, Date 전입일자, Date 전출일자) {
        this.세대 = 세대;
        this.세대주여부 = 세대주여부;
        this.이름 = 이름;
        this.생년월일 = 생년월일;
        this.외국인여부 = 외국인여부;
        this.혼인신고일 = 혼인신고일;
        this.무주택시작일 = 무주택시작일;
        this.전입일자 = 전입일자;
        this.전출일자 = 전출일자;
    };
}