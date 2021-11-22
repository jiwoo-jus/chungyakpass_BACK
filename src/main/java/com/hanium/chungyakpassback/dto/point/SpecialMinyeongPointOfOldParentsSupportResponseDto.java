package com.hanium.chungyakpassback.dto.point;

import com.hanium.chungyakpassback.entity.point.RecordSpecialMinyeongPointOfOldParentsSupport;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class SpecialMinyeongPointOfOldParentsSupportResponseDto {
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
    public SpecialMinyeongPointOfOldParentsSupportResponseDto (RecordSpecialMinyeongPointOfOldParentsSupport recordSpecialMinyeongPointOfOldParentsSupport){
        this.id = recordSpecialMinyeongPointOfOldParentsSupport.getId();
        this.houseMemberId = recordSpecialMinyeongPointOfOldParentsSupport.getHouseMemberId();
        this.parentsDeathYn = recordSpecialMinyeongPointOfOldParentsSupport.getParentsDeathYn();
        this.divorceYn = recordSpecialMinyeongPointOfOldParentsSupport.getDivorceYn();
        this.sameResidentRegistrationYn = recordSpecialMinyeongPointOfOldParentsSupport.getSameResidentRegistrationYn();
        this.stayOverYn = recordSpecialMinyeongPointOfOldParentsSupport.getStayOverYn();
        this.nowStayOverYn = recordSpecialMinyeongPointOfOldParentsSupport.getNowStayOverYn();
        this.periodOfHomelessness = recordSpecialMinyeongPointOfOldParentsSupport.getPeriodOfHomelessness();
        this.bankbookJoinPeriod = recordSpecialMinyeongPointOfOldParentsSupport.getBankbookJoinPeriod();
        this.numberOfDependents = recordSpecialMinyeongPointOfOldParentsSupport.getNumberOfDependents();
        this.bankBookVaildTf = recordSpecialMinyeongPointOfOldParentsSupport.isBankBookVaildYn();
        this.total = recordSpecialMinyeongPointOfOldParentsSupport.getTotal();
        this.createdDate = recordSpecialMinyeongPointOfOldParentsSupport.getCreatedDate();
        this.modifiedDate = recordSpecialMinyeongPointOfOldParentsSupport.getModifiedDate();

    }
}
