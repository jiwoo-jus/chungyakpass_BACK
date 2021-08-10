package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.자산유형;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_자산보유기준")
public class 자산보유기준 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "자산보유기준id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private com.hanium.chungyakpassback.domain.enumtype.자산유형 자산유형;

    @Column
    private int 금액;

    @Builder
    public 자산보유기준(자산유형 자산유형, int 금액) {
        this.자산유형 = 자산유형;
        this.금액 = 금액;
    }
}