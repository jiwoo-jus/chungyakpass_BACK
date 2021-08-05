package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.개설은행;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.청약통장종류;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="입력_회원_청약통장")
public class 회원_청약통장 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "회원_청약통장id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원id")
    private 회원 회원;

    @Column
    @Enumerated(EnumType.STRING)
    private 개설은행 개설은행;

    @Column
    @Enumerated(EnumType.STRING)
    private 청약통장종류 청약통장종류;

    @Column
    private int 예치금액;

    @Column
    private int 납입횟수;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 유효여부;


    @Builder
    public 회원_청약통장(회원 회원, 개설은행 개설은행, 청약통장종류 청약통장종류, int 예치금액, int 납입횟수, 여부 유효여부) {
        this.회원 = 회원;
        this.개설은행 = 개설은행;
        this.청약통장종류 = 청약통장종류;
        this.예치금액 = 예치금액;
        this.납입횟수 = 납입횟수;
        this.유효여부 = 유효여부;
    }
}
