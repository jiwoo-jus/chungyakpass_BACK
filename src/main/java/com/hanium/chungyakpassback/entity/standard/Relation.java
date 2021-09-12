package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "std_relation")
public class Relation {
    @Id
    @Column(name = "relation_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private com.hanium.chungyakpassback.enumtype.Relation relation;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn onlyOneYn;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn parentYn;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn childYn;
}
