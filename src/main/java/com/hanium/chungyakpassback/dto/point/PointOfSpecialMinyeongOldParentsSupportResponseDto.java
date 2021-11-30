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

    private Long id; //특별노부부부양 가점id
    private Integer periodOfHomelessness;//무주택기간 가점
    private Integer bankbookJoinPeriod;//청약통장 가입기간 가점
    private Integer numberOfDependents; //부양가족수 가점
    private boolean bankBookVaildTf; //청약통장 유효여부
    private Integer total; //가점 총합
    private LocalDateTime createdDate; //생성일자
    private LocalDateTime modifiedDate; //수정일자

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
