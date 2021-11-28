package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseDto;
import com.hanium.chungyakpassback.dto.input.HouseUpdateDto;
import com.hanium.chungyakpassback.entity.base.BaseTime;
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
public class House extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id; //세대id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_level1")
    private com.hanium.chungyakpassback.entity.standard.AddressLevel1 addressLevel1; //지역레벨1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_level2")
    private com.hanium.chungyakpassback.entity.standard.AddressLevel2 addressLevel2; //지역레벨2

    @Column
    private String addressDetail; //상세주소

    @Column
    private String zipcode; //우편번호

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_holder_id")
    private HouseMember houseHolder; //세대주 세대구성원


    @Builder
    public House(AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, String addressDetail, String zipcode){
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
    }

    public House update(AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, HouseUpdateDto houseUpdateDto){
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.addressDetail = houseUpdateDto.getAddressDetail();
        this.zipcode = houseUpdateDto.getZipcode();
        return this;
    }

}
