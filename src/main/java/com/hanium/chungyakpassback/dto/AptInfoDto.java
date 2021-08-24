package com.hanium.chungyakpassback.dto;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import com.hanium.chungyakpassback.entity.enumtype.HousingType;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Arrays;

@Getter
public class AptInfoDto {
    private Integer notificationNumber;
    public AddressLevel1 addressLevel1;
    private AddressLevel2 addressLevel2;
    private String detailAddress;
    public HousingType housingType;
    public Yn specialActPublicHousing;//공공주택특별법적용
    private String houseName;//주택명
    public String constructionCompany;//건설업체
    private LocalDate announcementDate;//모집공고일
    private LocalDate winnerAnnouncementDate;//당첨자발표일
    private LocalDate contractStartDate;//계약시작일
    private LocalDate contractEndDate;//계약종료일
    public YearMonth scheduledOccupancy;//입주예정월
    public Yn speculationOverheated;//투기과열지구
    public Yn subscriptionOverheated;//청약과열지역
    public Yn atrophyArea;//위축지역
    public Yn salePriceLimit;//분양가상한제
    public Yn maintenanceWork;//정비사업
    public Yn publicHosingDistrict;//공공주택지구
    public Yn publicRentalHousing;//공공건설임대주택
    public Yn largeDevelopmentzone;//대규모택지개발지구

    @Builder
    public AptInfoDto(JSONObject itemJson) {
        this.detailAddress = itemJson.getString("hssplyadres");
        try {
            String[] areaLevel = detailAddress.split(" ");
            String areaLevel2 = areaLevel[1].trim();
            if (Arrays.stream(AddressLevel2.values()).anyMatch(v -> v.name().equals(areaLevel2))) {
                this.addressLevel2 = AddressLevel2.valueOf(areaLevel2);
            } else {
                this.addressLevel2 = null;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            this.addressLevel2 = null;
        }

//        if (itemJson.has("totsuplyhshldco")) {
//            this.공급세대수_계 = itemJson.getInt("totsuplyhshldco");
//        }
        this.notificationNumber = itemJson.getInt("pblancno");
        this.houseName = itemJson.getString("housenm");
        this.announcementDate = LocalDate.parse(itemJson.getString("rcritpblancde"));
        this.contractStartDate = LocalDate.parse(itemJson.getString("przwnerpresnatnde"));
        this.contractEndDate = LocalDate.parse(itemJson.getString("cntrctcnclsbgnde"));
        this.winnerAnnouncementDate = LocalDate.parse(itemJson.getString("cntrctcnclsendde"));
    }

    public AptInfo toEntity() {
        return AptInfo.builder()
                .notificationNumber(notificationNumber)
                .addressLevel1(addressLevel1)
                .addressLevel2(addressLevel2)
                .detailAddress(detailAddress)
                .housingType(housingType)
                .specialActPublicHousing(specialActPublicHousing)
                .houseName(houseName)
                .constructionCompany(constructionCompany)
                .announcementDate(announcementDate)
                .winnerAnnouncementDate(winnerAnnouncementDate)
                .contractStartDate(contractStartDate)
                .contractEndDate(contractEndDate)
                .scheduledOccupancy(scheduledOccupancy)
                .speculationOverheated(speculationOverheated)
                .subscriptionOverheated(subscriptionOverheated)
                .atrophyArea(atrophyArea)
                .salePriceLimit(salePriceLimit)
                .maintenanceWork(maintenanceWork)
                .publicHosingDistrict(publicHosingDistrict)
                .publicRentalHousing(publicRentalHousing)
                .largeDevelopmentzone(largeDevelopmentzone)
                .build();
    }

}
