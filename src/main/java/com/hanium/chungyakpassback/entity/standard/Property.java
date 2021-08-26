package com.hanium.chungyakpassback.entity.standard;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="std_property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "std_property_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private com.hanium.chungyakpassback.enumtype.Property property;

    @Column
    private int price;

    @Builder
    public Property(com.hanium.chungyakpassback.enumtype.Property property, int price) {
        this.property = property;
        this.price = price;
    }
}