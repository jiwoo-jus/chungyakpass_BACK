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
    private Long id;
    private Long houseMemberId;
    private Yn parentsDeathYn;
    private Yn divorceYn;
    private Yn sameResidentRegistrationYn;
    private Yn stayOverYn;
    private Yn nowStayOverYn;
    private Integer periodOfHomelessness;
    private Integer bankbookJoinPeriod;
    private Integer numberOfDependents;
    private boolean bankBookVaildTf;
    private Integer total;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public PointOfGeneralMinyeongResponseDto(PointOfGeneralMinyeong pointOfGeneralMinyeong){
        this.id = pointOfGeneralMinyeong.getId();
        this.houseMemberId = pointOfGeneralMinyeong.getHouseMemberId();
        this.parentsDeathYn = pointOfGeneralMinyeong.getParentsDeathYn();
        this.divorceYn = pointOfGeneralMinyeong.getDivorceYn();
        this.sameResidentRegistrationYn = pointOfGeneralMinyeong.getSameResidentRegistrationYn();
        this.stayOverYn = pointOfGeneralMinyeong.getStayOverYn();
        this.nowStayOverYn = pointOfGeneralMinyeong.getNowStayOverYn();
        this.periodOfHomelessness = pointOfGeneralMinyeong.getPeriodOfHomelessness();
        this.bankbookJoinPeriod = pointOfGeneralMinyeong.getBankbookJoinPeriod();
        this.numberOfDependents = pointOfGeneralMinyeong.getNumberOfDependents();
        this.bankBookVaildTf = pointOfGeneralMinyeong.isBankBookValidTf();
        this.total = pointOfGeneralMinyeong.getTotal();
        this.createdDate = pointOfGeneralMinyeong.getCreatedDate();
        this.modifiedDate = pointOfGeneralMinyeong.getModifiedDate();

    }
}
