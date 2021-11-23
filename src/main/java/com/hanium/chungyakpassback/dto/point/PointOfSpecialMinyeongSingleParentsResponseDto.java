package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongSingleParents;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSpecialMinyeongSingleParentsResponseDto {
    private Long id;
    private Integer aptNotificationNumber;
    private Integer numberOfMinors;
    private Integer ageOfMostYoungChild;
    private Integer bankbookPaymentsCount;
    private Integer periodOfApplicableAreaResidence;
    private Integer monthOfAverageIncome;
    private Integer total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PointOfSpecialMinyeongSingleParentsResponseDto(PointOfSpecialMinyeongSingleParents pointOfSpecialMinyeongSingleParents) {
        this.id = pointOfSpecialMinyeongSingleParents.getId();
        this.aptNotificationNumber = pointOfSpecialMinyeongSingleParents.getAptInfo().getNotificationNumber();
        this.numberOfMinors = pointOfSpecialMinyeongSingleParents.getNumberOfMinors();
        this.ageOfMostYoungChild = pointOfSpecialMinyeongSingleParents.getAgeOfMostYoungChild();
        this.bankbookPaymentsCount = pointOfSpecialMinyeongSingleParents.getBankbookPaymentsCount();
        this.periodOfApplicableAreaResidence = pointOfSpecialMinyeongSingleParents.getPeriodOfApplicableAreaResidence();
        this.monthOfAverageIncome = pointOfSpecialMinyeongSingleParents.getMonthOfAverageIncome();
        this.total = pointOfSpecialMinyeongSingleParents.getTotal();
        this.createdDate = pointOfSpecialMinyeongSingleParents.getCreatedDate();
        this.modifiedDate = pointOfSpecialMinyeongSingleParents.getModifiedDate();
    }

}
