package com.hanium.chungyakpassback.dto.input;

import com.hanium.chungyakpassback.entity.input.HouseMemberChungyakRestriction;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HouseMemberChungyakRestrictionReadDto {

    private Long id; //세대구성원청약제한사항id

    private LocalDate reWinningRestrictedDate; //재당첨제한일

    private Yn specialSupplyRestrictedYn; //특별공급제한여부

    private LocalDate unqualifiedSubscriberRestrictedDate; //부적격당첨자제한일

    private LocalDate regulatedAreaFirstPriorityRestrictedDate; //투기과열지구청약과열지역1순위제한일

    private LocalDate additionalPointSystemRestrictedDate; //가점제당첨제한일

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @Builder
    public HouseMemberChungyakRestrictionReadDto(HouseMemberChungyakRestriction houseMemberChungyakRestriction){
        this.id = houseMemberChungyakRestriction.getId();
        this.reWinningRestrictedDate = houseMemberChungyakRestriction.getReWinningRestrictedDate();
        this.specialSupplyRestrictedYn = houseMemberChungyakRestriction.getSpecialSupplyRestrictedYn();
        this.unqualifiedSubscriberRestrictedDate = houseMemberChungyakRestriction.getUnqualifiedSubscriberRestrictedDate();
        this.regulatedAreaFirstPriorityRestrictedDate = houseMemberChungyakRestriction.getRegulatedAreaFirstPriorityRestrictedDate();
        this.additionalPointSystemRestrictedDate = houseMemberChungyakRestriction.getAdditionalPointSystemRestrictedDate();
        this.createdDate = houseMemberChungyakRestriction.getCreatedDate();
        this.modifiedDate = houseMemberChungyakRestriction.getModifiedDate();
    }
}
