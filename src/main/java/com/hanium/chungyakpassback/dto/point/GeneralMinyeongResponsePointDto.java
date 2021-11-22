package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.RecordGeneralMinyeongPoint;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneralMinyeongResponsePointDto {
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
    public GeneralMinyeongResponsePointDto(RecordGeneralMinyeongPoint recordGeneralMinyeongPoint){
        this.id = recordGeneralMinyeongPoint.getId();
        this.houseMemberId = recordGeneralMinyeongPoint.getHouseMemberId();
        this.parentsDeathYn = recordGeneralMinyeongPoint.getParentsDeathYn();
        this.divorceYn = recordGeneralMinyeongPoint.getDivorceYn();
        this.sameResidentRegistrationYn = recordGeneralMinyeongPoint.getSameResidentRegistrationYn();
        this.stayOverYn = recordGeneralMinyeongPoint.getStayOverYn();
        this.nowStayOverYn = recordGeneralMinyeongPoint.getNowStayOverYn();
        this.periodOfHomelessness = recordGeneralMinyeongPoint.getPeriodOfHomelessness();
        this.bankbookJoinPeriod = recordGeneralMinyeongPoint.getBankbookJoinPeriod();
        this.numberOfDependents = recordGeneralMinyeongPoint.getNumberOfDependents();
        this.bankBookVaildTf = recordGeneralMinyeongPoint.isBankBookValidTf();
        this.total = recordGeneralMinyeongPoint.getTotal();
        this.createdDate = recordGeneralMinyeongPoint.getCreatedDate();
        this.modifiedDate = recordGeneralMinyeongPoint.getModifiedDate();

    }
}
