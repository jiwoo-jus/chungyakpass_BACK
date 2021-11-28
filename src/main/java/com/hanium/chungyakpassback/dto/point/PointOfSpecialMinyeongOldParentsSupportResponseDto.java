package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongOldParentsSupport;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class PointOfSpecialMinyeongOldParentsSupportResponseDto {
    private Long id;
    private Integer periodOfHomelessness;
    private Integer bankbookJoinPeriod;
    private Integer numberOfDependents;
    private boolean bankBookVaildTf;
    private Integer total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PointOfSpecialMinyeongOldParentsSupportResponseDto(PointOfSpecialMinyeongOldParentsSupport pointOfSpecialMinyeongOldParentsSupport){
        this.id = pointOfSpecialMinyeongOldParentsSupport.getId();
        this.periodOfHomelessness = pointOfSpecialMinyeongOldParentsSupport.getPeriodOfHomelessness();
        this.bankbookJoinPeriod = pointOfSpecialMinyeongOldParentsSupport.getBankbookJoinPeriod();
        this.numberOfDependents = pointOfSpecialMinyeongOldParentsSupport.getNumberOfDependents();
        this.bankBookVaildTf = pointOfSpecialMinyeongOldParentsSupport.isBankBookVaildYn();
        this.total = pointOfSpecialMinyeongOldParentsSupport.getTotal();
        this.createdDate = pointOfSpecialMinyeongOldParentsSupport.getCreatedDate();
        this.modifiedDate = pointOfSpecialMinyeongOldParentsSupport.getModifiedDate();

    }
}
