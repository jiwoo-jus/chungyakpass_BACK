package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_지역_레벨_2")
public class 지역_레벨_2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "지역_레벨2id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "지역_레벨1id")
    private 지역_레벨_1 지역_레벨_1;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨2 지역_레벨2;

    @Builder
    public 지역_레벨_2(지역_레벨_1 지역_레벨_1, 지역_레벨2 지역_레벨2) {
        this.지역_레벨_1 = 지역_레벨_1;
        this.지역_레벨2 = 지역_레벨2;
    }
}
