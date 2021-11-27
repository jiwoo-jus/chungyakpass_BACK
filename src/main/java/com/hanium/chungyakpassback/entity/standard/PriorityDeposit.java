package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.DepositArea;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="std_priority_deposit")
public class PriorityDeposit {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "priority_deposit_id")
    private Long id;

    @Column
    private Integer areaOver; //면적초과

    @Column
    private Integer areaLessOrEqual; //면적이하

    @Column
    @Enumerated(EnumType.STRING)
    private DepositArea depositArea; //예치금액지역구분

    @Column
    private int deposit;

}
