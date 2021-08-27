package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_bankbook")
public class Bankbook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_bankbook_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private com.hanium.chungyakpassback.enumtype.Bankbook bankbook;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn nationalHousingSupplyPossible;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn privateHousingSupplyIsPossible;

    @Column
    private Integer restrictionSaleArea;

    @Builder
    public Bankbook(com.hanium.chungyakpassback.enumtype.Bankbook bankbook, Yn nationalHousingSupplyPossible, Yn privateHousingSupplyIsPossible, Integer restrictionSaleArea) {
        this.bankbook = bankbook;
        this.nationalHousingSupplyPossible = nationalHousingSupplyPossible;
        this.privateHousingSupplyIsPossible = privateHousingSupplyIsPossible;
        this.restrictionSaleArea = restrictionSaleArea;
    }
}
