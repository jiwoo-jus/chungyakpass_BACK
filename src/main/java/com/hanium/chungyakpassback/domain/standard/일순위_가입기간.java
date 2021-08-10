package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.공급유형;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.특별공급유형;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_일순위_가입기간")
public class 일순위_가입기간 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "일순위_가입기간id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 공급유형 공급유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 특별공급유형 특별공급유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 투기과열지구;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 청약과열지역;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 위축지역;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 수도권여부;

    @Column
    private int 가입기간;

    @Builder
    public 일순위_가입기간(공급유형 공급유형, 특별공급유형 특별공급유형, 여부 투기과열지구, 여부 청약과열지역, 여부 위축지역, 여부 수도권여부, int 가입기간) {
        this.공급유형 = 공급유형;
        this.특별공급유형 = 특별공급유형;
        this.투기과열지구 = 투기과열지구;
        this.청약과열지역 = 청약과열지역;
        this.위축지역 = 위축지역;
        this.수도권여부 = 수도권여부;
        this.가입기간 = 가입기간;
    }
}
