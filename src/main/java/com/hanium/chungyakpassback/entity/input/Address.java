package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.enumtype.address_level1;
import com.hanium.chungyakpassback.entity.enumtype.address_level2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "입력_주소")
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "주소id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private address_level1 address_level1;

    @Column
    @Enumerated(EnumType.STRING)
    private address_level2 address_level2;

    @Column
    private String address_detail;

    @Column
    private String zipcode;

    @Builder
    public Address(address_level1 address_level1, address_level2 address_level2, String address_detail, String zipcode){
        this.address_level1 = address_level1;
        this.address_level2 = address_level2;
        this.address_detail = address_detail;
        this.zipcode = zipcode;
    }
}