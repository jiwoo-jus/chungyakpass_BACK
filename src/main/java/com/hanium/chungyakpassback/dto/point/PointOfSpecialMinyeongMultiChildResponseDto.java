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
    private Long id; //특별다자녀 가점id
    private Integer aptNotificationNumber; //아파트공고번호
    private MultiChildHouseholdType multiChildHouseholdType; //세대구성
    private Integer numberOfChild; //미성년자녀수 가점
    private Integer numberOfChildUnder6Year; //영유아자녀수 가점
    private Integer bankbookJoinPeriod; //청약통장가입기간 가점
    private Integer periodOfApplicableAreaResidence; //해당지역 거주기간 가점
    private Integer periodOfHomelessness; //무주택기간 가점
    private Integer generationComposition; //세대구성 가점
    private Integer total; //가점 총합
    private LocalDateTime createdDate; //생성일자
    private LocalDateTime modifiedDate; //수정일자

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
