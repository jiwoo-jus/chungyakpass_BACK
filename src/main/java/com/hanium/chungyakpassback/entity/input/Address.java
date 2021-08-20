package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="inp_address")
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel1 address_level1;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel2 address_level2;

    @Column
    private String address_detail;

    @Column
    private String zipcode;

    @Builder
    public Address(AddressLevel1 address_level1, AddressLevel2 address_level2, String address_detail, String zipcode){
        this.address_level1 = address_level1;
        this.address_level2 = address_level2;
        this.address_detail = address_detail;
        this.zipcode = zipcode;
    }
}