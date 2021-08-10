package com.hanium.chungyakpassback.domain.standard;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_아파트분양정보_특별공급대상")
public class 아파트분양정보_특별공급대상 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "아파트분양정보_특별공급대상id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "아파트분양정보id")
    private 아파트분양정보 아파트분양정보;

    @Column
    private String 주택형;

    @Column
    private int 공급세대수_다자녀가구;

    @Column
    private int 공급세대수_신혼부부;

    @Column
    private int 공급세대수_생애최초;

    @Column
    private int 공급세대수_노부모부양;

    @Column
    private int 공급세대수_기관추천;

    @Column
    private Integer 공급세대수_이전기관;

    @Column
    private Integer 공급세대수_기타;

    @Builder

    public 아파트분양정보_특별공급대상(아파트분양정보 아파트분양정보, String 주택형, int 공급세대수_다자녀가구, int 공급세대수_신혼부부, int 공급세대수_생애최초, int 공급세대수_노부모부양, int 공급세대수_기관추천, Integer 공급세대수_이전기관, Integer 공급세대수_기타) {
        this.아파트분양정보 = 아파트분양정보;
        this.주택형 = 주택형;
        this.공급세대수_다자녀가구 = 공급세대수_다자녀가구;
        this.공급세대수_신혼부부 = 공급세대수_신혼부부;
        this.공급세대수_생애최초 = 공급세대수_생애최초;
        this.공급세대수_노부모부양 = 공급세대수_노부모부양;
        this.공급세대수_기관추천 = 공급세대수_기관추천;
        this.공급세대수_이전기관 = 공급세대수_이전기관;
        this.공급세대수_기타 = 공급세대수_기타;
    }
}
