package com.hanium.chungyakpassback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.Builder;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CrawlingAptInfoDto {
    public Integer notificationNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM", timezone = "Asia/Seoul")
    public YearMonth scheduledOccupancy;//입주예정월
    public Yn speculationOverheated;//투기과열지구
    public Yn subscriptionOverheated;//청약과열지역
    public Yn atrophyArea;//위축지역
    public Yn salePriceLimit;//분양가상한제
    public Yn maintenanceWork;//정비사업
    public Yn publicHosingDistrict;//공공주택지구
    public Yn publicRentalHousing;//공공건설임대주택
    public Yn largeDevelopmentzone;//대규모택지개발지구
    public Yn specialActPublicHousing;//공공주택특별법적용
    public Yn privateInMetropolitan;

    @Builder
    public CrawlingAptInfoDto(String content, Integer number, String string) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy.MM");
        this.scheduledOccupancy = YearMonth.parse(string, f);
        this.notificationNumber = number;
        if (content.contains("특이사항")) {
            //System.out.println(numbers.get(a));
            String[] uniqueness = content.split(":");
            String yn = uniqueness[1].trim();
            String[] yn1 = yn.split(",");
            List<String> yn2 = new ArrayList<>();
            for (String yn3 : yn1) {
                yn2.add(yn3.trim());
            }
            if (yn2.contains("청약과열지역")) {
                this.subscriptionOverheated = Yn.y;
            } else {
                this.subscriptionOverheated = Yn.n;
            }
            if (yn2.contains("투기과열지구")) {
                this.speculationOverheated = Yn.y;
            } else {
                this.speculationOverheated = Yn.n;
            }
            if (yn2.contains("분양가상한제")) {
                this.salePriceLimit = Yn.y;
            } else {
                this.salePriceLimit = Yn.n;
            }
            if (yn2.contains("공공주택지구")) {
                this.publicHosingDistrict = Yn.y;

            } else {
                this.publicHosingDistrict = Yn.n;
            }
            if (yn2.contains("대규모택지개발지구")) {
                this.largeDevelopmentzone = Yn.y;
            } else {
                this.largeDevelopmentzone = Yn.n;
            }
            if (yn2.contains("정비사업")) {
                this.maintenanceWork = Yn.y;
            } else {
                this.maintenanceWork = Yn.n;
            }
            if (yn2.contains("위축지역")) {
                this.atrophyArea = Yn.y;
            } else {
                this.atrophyArea = Yn.n;
            }
            if (yn2.contains("공공건설임대주택")) {
                this.publicRentalHousing = Yn.y;
            } else {
                this.publicRentalHousing = Yn.n;
            }
            if (yn2.contains("공공주택특별법적용")) {
                this.specialActPublicHousing = Yn.y;
            } else {
                this.specialActPublicHousing = Yn.n;
            }
            if (yn2.contains("수도권내민영공공주택지구")) {
                this.privateInMetropolitan = Yn.y;
            } else {
                this.privateInMetropolitan = Yn.n;
            }

        } else {
            this.atrophyArea = Yn.n;
            this.subscriptionOverheated = Yn.n;
            this.speculationOverheated = Yn.n;
            this.salePriceLimit = Yn.n;
            this.maintenanceWork = Yn.n;
            this.publicHosingDistrict = Yn.n;
            this.publicRentalHousing = Yn.n;
            this.largeDevelopmentzone = Yn.n;
            this.specialActPublicHousing = Yn.n;
            this.privateInMetropolitan = Yn.n;
        }
    }
//    public AptInfo toEntity() {
//        return AptInfo.builder()
//                .투기과열지구(투기과열지구)
//                .청약과열지역(청약과열지역)
//                .위축지역(위축지역)
//                .분양가상한제(분양가상한제)
//                .정비사업(정비사업)
//                .공공주택지구(공공주택지구)
//                .공공건설임대주택(공공건설임대주택)
//                .대규모택지개발지구(대규모택지개발지구)
//                .build();
//    }
}
