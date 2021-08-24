package com.hanium.chungyakpassback.dto;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter
public class AptInfoTargetSpecialDto {
    private Integer notificationNumber;
    private String housingType;
    private int supplyMultiChildHousehold;//공급세대수_다자녀가구;
    private int supplyNewlyMarriedCouple;//공급세대수_신혼부부
    private int supplyFirstLife;//공급세대수_생애최초
    private int supplyOldParentSupport;//공급세대수_노부모부양
    private int supplyInstitutionalRecommendation;//공급세대수_기관추천
    private Integer supplyTransferAgency;//공급세대수_이전기관
    private Integer supplyOther;//공급세대수_기타

    @Builder
    public AptInfoTargetSpecialDto( JSONObject itemJson){
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
        this.supplyMultiChildHousehold = itemJson.getInt("mnychhshldco");
        this.supplyNewlyMarriedCouple = itemJson.getInt("nwwdshshldco");
        this.supplyFirstLife = itemJson.getInt("lfefrsthshldco");
        this.supplyOldParentSupport = itemJson.getInt("oldparentssuporthshldco");
        this.supplyInstitutionalRecommendation = itemJson.getInt("insttrecomendhshldco");
        this.supplyTransferAgency = itemJson.getInt("etchshldco");
        this.supplyOther = itemJson.getInt("transrinsttenfsnhshldco");
    }
}
