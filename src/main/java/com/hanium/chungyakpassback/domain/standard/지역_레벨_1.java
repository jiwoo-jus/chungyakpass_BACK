package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.예치금액지역구분;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "기준_지역_레벨_1")
public class 지역_레벨_1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "지역_레벨1id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨1 지역_레벨1;

    @Column
    private int 인근지역구분;

    @Column
    @Enumerated(EnumType.STRING)
    private 예치금액지역구분 예치금액지역구분;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 수도권여부;

    @Builder
    public 지역_레벨_1(지역_레벨1 지역_레벨1, int 인근지역구분, 예치금액지역구분 예치금액지역구분, 여부 수도권여부) {
        this.지역_레벨1 = 지역_레벨1;
        this.인근지역구분 = 인근지역구분;
        this.예치금액지역구분 = 예치금액지역구분;
        this.수도권여부 = 수도권여부;
    }
}
