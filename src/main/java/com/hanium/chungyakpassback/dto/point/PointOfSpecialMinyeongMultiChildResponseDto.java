package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongMultiChild;
import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfSpecialMinyeongMultiChildResponseDto {
    private Long id;
    private Integer aptNotificationNumber;
    private MultiChildHouseholdType multiChildHouseholdType;
    private Integer numberOfChild;
    private Integer numberOfChildUnder6Year;
    private Integer bankbookJoinPeriod;
    private Integer periodOfApplicableAreaResidence;
    private Integer periodOfHomelessness;
    private Integer generationComposition;
    private Integer total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PointOfSpecialMinyeongMultiChildResponseDto(PointOfSpecialMinyeongMultiChild pointOfSpecialMinyeongMultiChild){
        this.id = pointOfSpecialMinyeongMultiChild.getId();
        this.aptNotificationNumber = pointOfSpecialMinyeongMultiChild.getAptInfo().getNotificationNumber();
        this.multiChildHouseholdType = pointOfSpecialMinyeongMultiChild.getMultiChildHouseholdType();
        this.numberOfChild = pointOfSpecialMinyeongMultiChild.getNumberOfChild();
        this.numberOfChildUnder6Year = pointOfSpecialMinyeongMultiChild.getNumberOfChildUnder6Year();
        this.bankbookJoinPeriod = pointOfSpecialMinyeongMultiChild.getBankbookJoinPeriod();
        this.periodOfApplicableAreaResidence = pointOfSpecialMinyeongMultiChild.getPeriodOfApplicableAreaResidence();
        this.periodOfHomelessness = pointOfSpecialMinyeongMultiChild.getPeriodOfHomelessness();
        this.generationComposition = pointOfSpecialMinyeongMultiChild.getGenerationComposition();
        this.total = pointOfSpecialMinyeongMultiChild.getTotal();
        this.createdDate = pointOfSpecialMinyeongMultiChild.getCreatedDate();
        this.modifiedDate = pointOfSpecialMinyeongMultiChild.getModifiedDate();
    }
}
