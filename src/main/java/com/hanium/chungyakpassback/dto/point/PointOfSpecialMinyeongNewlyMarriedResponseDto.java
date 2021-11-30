package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongNewlyMarried;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSpecialMinyeongNewlyMarriedResponseDto {

    private Long id; //특별신혼부부 가점id
    private Integer aptNotificationNumber; //공고번호
    private Integer numberOfMinors; //미성년자녀수 가점
    private Integer periodOfMarriged; //혼인기간 가점
    private Integer bankbookPaymentsCount; //청약통장 납입횟수 가점
    private Integer periodOfApplicableAreaResidence; //해당지역 거주기간 가점
    private Integer monthOfAverageIncome; //가구소득 가점
    private Integer total; //가점 총합
    private LocalDateTime createdDate; //생성일자
    private LocalDateTime modifiedDate; //수정일자

    @Builder
    public PointOfSpecialMinyeongNewlyMarriedResponseDto(PointOfSpecialMinyeongNewlyMarried pointOfSpecialMinyeongNewlyMarried) {
        this.id = pointOfSpecialMinyeongNewlyMarried.getId();
        this.aptNotificationNumber = pointOfSpecialMinyeongNewlyMarried.getAptInfo().getNotificationNumber();
        this.numberOfMinors = pointOfSpecialMinyeongNewlyMarried.getNumberOfMinors();
        this.periodOfMarriged = pointOfSpecialMinyeongNewlyMarried.getPeriodOfMarriged();
        this.bankbookPaymentsCount = pointOfSpecialMinyeongNewlyMarried.getBankbookPaymentsCount();
        this.periodOfApplicableAreaResidence = pointOfSpecialMinyeongNewlyMarried.getPeriodOfApplicableAreaResidence();
        this.monthOfAverageIncome = pointOfSpecialMinyeongNewlyMarried.getMonthOfAverageIncome();
        this.total = pointOfSpecialMinyeongNewlyMarried.getTotal();
        this.createdDate = pointOfSpecialMinyeongNewlyMarried.getCreatedDate();
        this.modifiedDate = pointOfSpecialMinyeongNewlyMarried.getModifiedDate();
    }

}
//package com.hanium.chungyakpassback.dto.point;
//
//import com.hanium.chungyakpassback.entity.apt.AptInfo;
//import com.hanium.chungyakpassback.entity.input.UserBankbook;
//import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfNewMarried;
//import lombok.*;
//
//@Getter
//@Setter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class SpecialMinyeongPointOfNewMarriedResponseDto {
//    Integer numberOfMinors;
//    Integer periodOfMarriged;
//    Integer bankbookPaymentsCount;
//    Integer periodOfApplicableAreaResidence;
//    Integer monthOfAverageIncome;
//
//    @Builder
//    public SpecialMinyeongPointOfNewMarriedResponseDto (RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried){
//        this.numberOfMinors = recordSpecialMinyeongPointOfNewMarried.getNumberOfMinors();
//        this.periodOfMarriged = recordSpecialMinyeongPointOfNewMarried.getPeriodOfMarriged();
//        this.bankbookPaymentsCount = recordSpecialMinyeongPointOfNewMarried.getBankbookPaymentsCount();
//        this.periodOfApplicableAreaResidence = recordSpecialMinyeongPointOfNewMarried.getPeriodOfApplicableAreaResidence();
//        this.monthOfAverageIncome = recordSpecialMinyeongPointOfNewMarried.getMonthOfAverageIncome();
//    }
//
//    public RecordSpecialMinyeongPointOfNewMarried toEntity() {
//        return RecordSpecialMinyeongPointOfNewMarried.builder()
//                .numberOfMinors(numberOfMinors)
//                .periodOfMarriged(periodOfMarriged)
//                .bankbookPaymentsCount(bankbookPaymentsCount)
//                .periodOfApplicableAreaResidence(periodOfApplicableAreaResidence)
//                .monthOfAverageIncome(monthOfAverageIncome)
//                .build();
//    }
//
//}
