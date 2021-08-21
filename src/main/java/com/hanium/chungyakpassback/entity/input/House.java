package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="inp_house")
public class House {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel1 addressLevel1;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel2 addressLevel2;

    @Column
    private String addressDetail;

    @Column
    private String zipcode;


    @Builder
    public House(AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, String addressDetail, String zipcode){
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }
}
