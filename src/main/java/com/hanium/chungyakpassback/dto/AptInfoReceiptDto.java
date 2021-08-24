package com.hanium.chungyakpassback.dto;

import lombok.Builder;
import lombok.Getter;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Getter
public class AptInfoReceiptDto {
    private Integer notificationNumber;
    private LocalDate specialReceptionStartDate;//특별공급접수시작일
    private LocalDate specialReceptionEndDate;//특별공급접수종료일
    private LocalDate priorityApplicableAreaStart;//일순위접수일해당지역
    private LocalDate priorityApplicableAreaEnd;
    private LocalDate priorityGyeonggiAreaStart;//일순위접수일경기지역
    private LocalDate priorityGyeonggiAreaEnd;//일순위접수일경기지역
    private LocalDate priorityOtherAreaStart;//일순위접수일기타지역
    private LocalDate priorityOtherAreaEnd;//일순위접수일기타지역
    private LocalDate secondApplicableAreaStart;//이순위접수일해당지역
    private LocalDate secondApplicableAreaEnd;//이순위접수일해당지역
    private LocalDate secondGyeonggiAreaStart;//이순위접수일경기지역
    private LocalDate secondGyeonggiAreaEnd;
    private LocalDate secondOtherAreaStart;//이순위접수일기타지역
    private LocalDate secondOtherAreaEnd;//이순위접수일기타지역
    private String homepage;//홈페이지

    @Builder
    public AptInfoReceiptDto(JSONObject itemJson) {
        this.notificationNumber = itemJson.getInt("pblancno");
        if(itemJson.has("spsplyrceptbgnde")) {
            this.specialReceptionStartDate = LocalDate.parse(itemJson.getString("spsplyrceptbgnde"));
            this.specialReceptionEndDate = LocalDate.parse(itemJson.getString("spsplyrceptendde"));
        }
        try {
            this.priorityApplicableAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk1crsparearceptpd"));
        }
        catch (DateTimeParseException e){
            String[] priorityApplicableArea = itemJson.getString("gnrlrnk1crsparearceptpd").split("~");
            this.priorityApplicableAreaStart = LocalDate.parse(priorityApplicableArea[0]);
            this.priorityApplicableAreaEnd = LocalDate.parse(priorityApplicableArea[1]);

        }
        if(itemJson.has("gnrlrnk1etcggrcptdepd")) {
            try {
                this.priorityGyeonggiAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk1etcggrcptdepd"));
            } catch (DateTimeParseException e) {
                String[] priorityGyeonggiArea = itemJson.getString("gnrlrnk1etcggrcptdepd").split("~");
                this.priorityGyeonggiAreaStart = LocalDate.parse(priorityGyeonggiArea[0]);
                this.priorityGyeonggiAreaEnd = LocalDate.parse(priorityGyeonggiArea[1]);
            }
        }
        if(itemJson.has("gnrlrnk1etcarearcptdepd")) {
            try {
                this.priorityOtherAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk1etcarearcptdepd"));
            } catch (DateTimeParseException e) {
                String[] priorityOtherArea = itemJson.getString("gnrlrnk1etcarearcptdepd").split("~");
                this.priorityOtherAreaStart = LocalDate.parse(priorityOtherArea[0]);
                this.priorityOtherAreaEnd = LocalDate.parse(priorityOtherArea[1]);
            }
        }
        if(itemJson.has("gnrlrnk2crsparearceptpd")) {
            try {
                this.secondApplicableAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk2crsparearceptpd"));
            } catch (DateTimeParseException e) {
                String[] secondApplicableArea = itemJson.getString("gnrlrnk2crsparearceptpd").split("~");
                this.secondApplicableAreaStart = LocalDate.parse(secondApplicableArea[0]);
                this.secondApplicableAreaEnd = LocalDate.parse(secondApplicableArea[1]);
            }
        }
        if(itemJson.has("gnrlrnk2etcggrcptdepd")) {
            if (itemJson.has("gnrlrnk2etcggrcptdepd")) {
                try {
                    this.secondGyeonggiAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk2etcggrcptdepd"));
                } catch (DateTimeParseException e) {
                    String[] secondGyeonggiArea = itemJson.getString("gnrlrnk2etcggrcptdepd").split("~");
                    this.secondGyeonggiAreaStart = LocalDate.parse(secondGyeonggiArea[0]);
                    this.secondGyeonggiAreaEnd = LocalDate.parse(secondGyeonggiArea[1]);
                }
            }
        }
        if(itemJson.has("gnrlrnk2etcarearcptdepd")) {
            try {
                this.secondOtherAreaEnd = LocalDate.parse(itemJson.getString("gnrlrnk2etcarearcptdepd"));
            } catch (DateTimeParseException e) {
                String[] secondOtherArea = itemJson.getString("gnrlrnk2etcarearcptdepd").split("~");
                this.secondOtherAreaStart = LocalDate.parse(secondOtherArea[0]);
                this.secondOtherAreaEnd = LocalDate.parse(secondOtherArea[1]);
            }
        }
        if(itemJson.has("hmpgadres")) {
            this.homepage = itemJson.getString("hmpgadres");
        }

    }
}
