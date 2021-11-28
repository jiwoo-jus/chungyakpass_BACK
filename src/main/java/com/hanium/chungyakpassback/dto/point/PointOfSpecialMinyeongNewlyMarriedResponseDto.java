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

    private Long id;
    private Integer aptNotificationNumber;
    private Integer numberOfMinors;
    private Integer periodOfMarriged;
    private Integer bankbookPaymentsCount;
    private Integer periodOfApplicableAreaResidence;
    private Integer monthOfAverageIncome;
    private Integer total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

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
