package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_area_level2")
public class AreaLevel2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_level2_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "area_level1_id")
    private AreaLevel1 AreaLevel1;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel2 areaLevel2;

    @Builder
    public AreaLevel2(com.hanium.chungyakpassback.entity.standard.AreaLevel1 areaLevel1, AddressLevel2 areaLevel2) {
        AreaLevel1 = areaLevel1;
        this.areaLevel2 = areaLevel2;
    }
}
