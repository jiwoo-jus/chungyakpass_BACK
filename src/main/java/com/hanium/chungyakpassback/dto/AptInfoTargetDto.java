package com.hanium.chungyakpassback.dto;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class AptInfoTargetDto {
    private Integer notificationNumber;
    private String housingType;
    private Double supplyArea;
    private Integer supplyGeneral;
    private Integer supplySpecial;
    private Integer supplyTotal;

    @Builder
    public AptInfoTargetDto(JSONObject itemJson){
        this.notificationNumber = itemJson.getInt("pblancno");
        this.supplyArea = itemJson.getDouble("suplyar");
        this.supplyGeneral = itemJson.getInt("suplyhshldco");
        this.supplySpecial = itemJson.getInt("spsplyhshldco");
        this.supplyTotal =supplyGeneral+supplySpecial;
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
    }
}
