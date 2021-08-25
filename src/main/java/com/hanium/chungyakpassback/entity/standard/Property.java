package com.hanium.chungyakpassback.entity.standard;

import com.hanium.chungyakpassback.entity.enumtype.PropertyType;
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
    private PropertyType propertyType;

    @Column
    private int price;

    @Builder
    public Property(PropertyType propertyType, int price) {
        this.propertyType = propertyType;
        this.price = price;
    }
}