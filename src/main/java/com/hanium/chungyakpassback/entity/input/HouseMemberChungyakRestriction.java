package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.dto.input.HouseMemberChungyakRestrictionUpdateDto;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_house_member_chungyak_restriction")
public class HouseMemberChungyakRestriction extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_chungyak_restriction_id")
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "house_member_id")
//    private HouseMember houseMember;

    @OneToOne
    @JoinColumn(name = "house_member_chungyak_id")
    private HouseMemberChungyak houseMemberChungyak;

    @Column
    private LocalDate reWinningRestrictedDate; //재당첨제한일

    @Column
    @Enumerated(EnumType.STRING)
    private Yn specialSupplyRestrictedYn; //특별공급제한여부

    @Column
    private LocalDate unqualifiedSubscriberRestrictedDate; //부적격당첨자제한일

    @Column
    private LocalDate regulatedAreaFirstPriorityRestrictedDate; //투기과열지구청약과열지역1순위제한일

    @Column
    private LocalDate additionalPointSystemRestrictedDate; //가점제당첨제한일


    @Builder
    public HouseMemberChungyakRestriction(HouseMemberChungyak houseMemberChungyak, LocalDate reWinningRestrictedDate, Yn specialSupplyRestrictedYn, LocalDate unqualifiedSubscriberRestrictedDate, LocalDate regulatedAreaFirstPriorityRestrictedDate, LocalDate additionalPointSystemRestrictedDate) {
//        this.houseMember = houseMember;
        this.houseMemberChungyak = houseMemberChungyak;
        this.reWinningRestrictedDate = reWinningRestrictedDate;
        this.specialSupplyRestrictedYn = specialSupplyRestrictedYn;
        this.unqualifiedSubscriberRestrictedDate = unqualifiedSubscriberRestrictedDate;
        this.regulatedAreaFirstPriorityRestrictedDate = regulatedAreaFirstPriorityRestrictedDate;
        this.additionalPointSystemRestrictedDate = additionalPointSystemRestrictedDate;
    }

    public HouseMemberChungyakRestriction updateHouseMemberChungyakRestriction(HouseMemberChungyak houseMemberChungyak, HouseMemberChungyakRestrictionUpdateDto houseMemberChungyakRestrictionUpdateDto){
        this.houseMemberChungyak = houseMemberChungyak;
        this.reWinningRestrictedDate = houseMemberChungyakRestrictionUpdateDto.getReWinningRestrictedDate();
        this.specialSupplyRestrictedYn = houseMemberChungyakRestrictionUpdateDto.getSpecialSupplyRestrictedYn();
        this.unqualifiedSubscriberRestrictedDate = houseMemberChungyakRestrictionUpdateDto.getUnqualifiedSubscriberRestrictedDate();
        this.regulatedAreaFirstPriorityRestrictedDate = houseMemberChungyakRestrictionUpdateDto.getRegulatedAreaFirstPriorityRestrictedDate();
        this.additionalPointSystemRestrictedDate = houseMemberChungyakRestrictionUpdateDto.getAdditionalPointSystemRestrictedDate();
        return this;
    }
}



