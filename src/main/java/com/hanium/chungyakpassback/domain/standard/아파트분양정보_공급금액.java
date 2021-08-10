package com.hanium.chungyakpassback.domain.standard;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_아파트분양정보_공급금액")
public class 아파트분양정보_공급금액 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "아파트분양정보_공급금액id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "아파트분양정보id")
    private 아파트분양정보 아파트분양정보;

    @Column
    private String 주택형;

    @Column
    private int 공급금액;

    @Column
    private Integer 이순위청약금;

    @Builder
    public 아파트분양정보_공급금액(아파트분양정보 아파트분양정보, String 주택형, int 공급금액, Integer 이순위청약금) {
        this.아파트분양정보 = 아파트분양정보;
        this.주택형 = 주택형;
        this.공급금액 = 공급금액;
        this.이순위청약금 = 이순위청약금;
    }
}
