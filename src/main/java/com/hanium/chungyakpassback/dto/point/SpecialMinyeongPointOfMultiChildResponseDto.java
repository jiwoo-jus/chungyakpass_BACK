package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfMultiChild;
import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpecialMinyeongPointOfMultiChildResponseDto {
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
    public SpecialMinyeongPointOfMultiChildResponseDto (RecordSpecialMinyeongPointOfMultiChild recordSpecialMinyeongPointOfMultiChild){
        this.id = recordSpecialMinyeongPointOfMultiChild.getId();
        this.aptNotificationNumber = recordSpecialMinyeongPointOfMultiChild.getAptInfo().getNotificationNumber();
        this.multiChildHouseholdType = recordSpecialMinyeongPointOfMultiChild.getMultiChildHouseholdType();
        this.numberOfChild =recordSpecialMinyeongPointOfMultiChild.getNumberOfChild();
        this.numberOfChildUnder6Year = recordSpecialMinyeongPointOfMultiChild.getNumberOfChildUnder6Year();
        this.bankbookJoinPeriod = recordSpecialMinyeongPointOfMultiChild.getBankbookJoinPeriod();
        this.periodOfApplicableAreaResidence = recordSpecialMinyeongPointOfMultiChild.getPeriodOfApplicableAreaResidence();
        this.periodOfHomelessness = recordSpecialMinyeongPointOfMultiChild.getPeriodOfHomelessness();
        this.generationComposition = recordSpecialMinyeongPointOfMultiChild.getGenerationComposition();
        this.total = recordSpecialMinyeongPointOfMultiChild.getTotal();
        this.createdDate = recordSpecialMinyeongPointOfMultiChild.getCreatedDate();
        this.modifiedDate = recordSpecialMinyeongPointOfMultiChild.getModifiedDate();
    }
}
