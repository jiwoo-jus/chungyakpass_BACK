package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseDto;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.entity.standard.AddressLevel2;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_level1")
    private com.hanium.chungyakpassback.entity.standard.AddressLevel1 addressLevel1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_level2")
    private com.hanium.chungyakpassback.entity.standard.AddressLevel2 addressLevel2;

    @Column
    private String addressDetail;

    @Column
    private String zipcode;

    @OneToOne
    @JoinColumn(name = "house_holder_id")
    private HouseMember houseHolder;


    @Builder
    public House(AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, String addressDetail, String zipcode){
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    public House update(AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, HouseDto houseDto){
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressDetail = houseDto.getAddressDetail();
        this.zipcode = houseDto.getZipcode();
        return this;
    }

}
