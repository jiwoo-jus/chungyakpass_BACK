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
    private Integer aptNotificationNumber; //공고번호
    private Integer numberOfMinors; //미성년자녀수 가점
    private Integer ageOfMostYoungChild; //자녀나이 가점
    private Integer bankbookPaymentsCount; //청약통장 납입횟수 가점
    private Integer periodOfApplicableAreaResidence; //해당지역 거주기간 가점
    private Integer monthOfAverageIncome; //가구소득 가점
    private Integer total; //가점 총합
    private LocalDateTime createdDate; //생성일자
    private LocalDateTime modifiedDate; //수정일자

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
