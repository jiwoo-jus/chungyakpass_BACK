package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.PointOfGeneralMinyeong;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointOfGeneralMinyeongResponseDto {
    private Long id; //일반민영 가점id
    private Integer periodOfHomelessness;//무주택기간 가점
    private Integer bankbookJoinPeriod;//청약통장 가입기간 가점
    private Integer numberOfDependents;//부양가족수 가점
    private boolean bankBookVaildTf;//청약통장 유효여부
    private Integer total;//가점 총합
    private LocalDateTime createdDate; //생성일자
    private LocalDateTime modifiedDate;//수정일자

    @Builder
    public PointOfGeneralMinyeongResponseDto(PointOfGeneralMinyeong pointOfGeneralMinyeong){
        this.id = pointOfGeneralMinyeong.getId();
        this.periodOfHomelessness = pointOfGeneralMinyeong.getPeriodOfHomelessness();
        this.bankbookJoinPeriod = pointOfGeneralMinyeong.getBankbookJoinPeriod();
        this.numberOfDependents = pointOfGeneralMinyeong.getNumberOfDependents();
        this.bankBookVaildTf = pointOfGeneralMinyeong.isBankBookValidTf();
        this.total = pointOfGeneralMinyeong.getTotal();
        this.createdDate = pointOfGeneralMinyeong.getCreatedDate();
        this.modifiedDate = pointOfGeneralMinyeong.getModifiedDate();

    }
}
