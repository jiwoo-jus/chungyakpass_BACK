package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.공급방법;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.특별공급유형;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_월평균소득기준")
public class 월평균소득기준 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "월평균소득기준id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 공공주택특별법적용;

    @Column
    @Enumerated(EnumType.STRING)
    private 특별공급유형 특별공급유형;

    @Column
    @Enumerated(EnumType.STRING)
    private 공급방법 공급방법;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 맞벌이;

    @Column
    private Integer 월평균소득백분율_초과;

    @Column
    private int 월평균소득백분율_이하;

    @Column
    private Integer 월평균소득액_3인이하_초과;

    @Column
    private int 월평균소득액_3인이하_이하;

    @Column
    private Integer 월평균소득액_4인_초과;

    @Column
    private Integer 월평균소득액_4인_이하;

    @Column
    private Integer 월평균소득액_5인_초과;

    @Column
    private Integer 월평균소득액_5인_이하;

    @Builder

    public 월평균소득기준(여부 공공주택특별법적용, 특별공급유형 특별공급유형, 공급방법 공급방법, 여부 맞벌이, Integer 월평균소득백분율_초과, int 월평균소득백분율_이하, Integer 월평균소득액_3인이하_초과, int 월평균소득액_3인이하_이하, Integer 월평균소득액_4인_초과, Integer 월평균소득액_4인_이하, Integer 월평균소득액_5인_초과, Integer 월평균소득액_5인_이하) {
        this.공공주택특별법적용 = 공공주택특별법적용;
        this.특별공급유형 = 특별공급유형;
        this.공급방법 = 공급방법;
        this.맞벌이 = 맞벌이;
        this.월평균소득백분율_초과 = 월평균소득백분율_초과;
        this.월평균소득백분율_이하 = 월평균소득백분율_이하;
        this.월평균소득액_3인이하_초과 = 월평균소득액_3인이하_초과;
        this.월평균소득액_3인이하_이하 = 월평균소득액_3인이하_이하;
        this.월평균소득액_4인_초과 = 월평균소득액_4인_초과;
        this.월평균소득액_4인_이하 = 월평균소득액_4인_이하;
        this.월평균소득액_5인_초과 = 월평균소득액_5인_초과;
        this.월평균소득액_5인_이하 = 월평균소득액_5인_이하;
    }
}
