package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.DepositArea;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_area_leve1")
public class AddressLevel1 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_level1_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private com.hanium.chungyakpassback.enumtype.AddressLevel1 addressLevel1;

    @Column
    private int nearbyArea;

    @Column
    @Enumerated(EnumType.STRING)
    private DepositArea depositArea;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn metropolitanArea;

    @Builder
    public AddressLevel1(com.hanium.chungyakpassback.enumtype.AddressLevel1 addressLevel1, int nearbyArea, DepositArea depositArea, Yn metropolitanArea) {
        this.addressLevel1 = addressLevel1;
        this.nearbyArea = nearbyArea;
        this.depositArea = depositArea;
        this.metropolitanArea = metropolitanArea;
    }
}
