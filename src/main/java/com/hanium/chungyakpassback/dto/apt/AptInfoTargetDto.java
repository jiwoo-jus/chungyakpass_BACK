package com.hanium.chungyakpassback.dto.apt;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class AptInfoTargetDto {
    private Integer notificationNumber; //공고번호
    private String housingType; //주택형
    private Double supplyArea; //공급면적
    private Integer supplyGeneral; //공급세대수_일반공급
    private Integer supplySpecial; //공급세대수_특별공급
    private Integer supplyTotal; //공급세대수_전체

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
