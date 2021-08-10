package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "입력_회원")
public class 회원 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "회원id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원본인;

    @Column
    private String 이메일;

    @Column
    private String 비밀번호;


    @Builder
    public 회원(세대구성원 세대구성원본인, String 이메일, String 비밀번호) {
        this.세대구성원본인 = 세대구성원본인;
        this.이메일 = 이메일;
        this.비밀번호 = 비밀번호;
    }
}