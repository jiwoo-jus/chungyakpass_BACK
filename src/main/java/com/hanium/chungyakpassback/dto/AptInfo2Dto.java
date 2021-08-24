package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.HousingType;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;

@Getter
public class AptInfo2Dto {
    public Integer notificationNumber;
    public AddressLevel1 addressLevel1;
    public HousingType housingType;
    public String constructionCompany;//건설업체

    @Builder
    public AptInfo2Dto(JSONObject itemJson){
        this.addressLevel1 = AddressLevel1.valueOf(itemJson.getString("sido"));
        this.notificationNumber = itemJson.getInt("pblancNo");//주택관리번호 뽑아냄
        this.housingType = HousingType.valueOf(itemJson.getString("houseDtlSecdNm"));
        this.constructionCompany = itemJson.getString("bsnsMbyNm");
    }

}
