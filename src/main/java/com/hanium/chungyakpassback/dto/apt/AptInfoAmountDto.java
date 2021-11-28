package com.hanium.chungyakpassback.dto.apt;//package com.hanium.chungyakpassback.entity.dto;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class AptInfoAmountDto {
    private Integer notificationNumber; //공고번호
    private String housingType; //주택형
    private String supplyAmount; //공급금액

    @Builder
    public AptInfoAmountDto(JSONObject itemJson){
        this.notificationNumber = itemJson.getInt("pblancno");
        try {
            if (itemJson.get("housety") instanceof Double) {
                this.housingType = String.valueOf(itemJson.getDouble("housety")); //Double -> String 1번방식
            } else {
                this.housingType = itemJson.getString("housety");
            }
        }
        catch (JSONException e){
            this.housingType = String.valueOf(itemJson.getInt("housety"));
        }
        if(itemJson.get("lttottopamount") instanceof Integer) {
            this.supplyAmount =String.valueOf(itemJson.getInt("lttottopamount"));
        }
        else {
            this.supplyAmount = itemJson.getString("lttottopamount");
        }
    }
}
