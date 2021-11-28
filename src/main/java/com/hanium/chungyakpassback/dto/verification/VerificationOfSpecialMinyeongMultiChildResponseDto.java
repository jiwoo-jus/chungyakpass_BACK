package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongMultiChild;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialMinyeongMultiChildResponseDto {

    private Long id;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private Integer calcMinorChildren;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetHouseHavingLessThan2AptTf;
    private boolean priorityApt;
    private boolean meetDepositTf;
    private boolean meetBankbookJoinPeriodTf;
    private Yn sibilingSupportYn;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public VerificationOfSpecialMinyeongMultiChildResponseDto(VerificationOfSpecialMinyeongMultiChild verificationOfSpecialMinyeongMultiChild) {
        this.americanAge = verificationOfSpecialMinyeongMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialMinyeongMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialMinyeongMultiChild.isAccountTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialMinyeongMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialMinyeongMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationOfSpecialMinyeongMultiChild.getCalcMinorChildren();
        this.householderTf = verificationOfSpecialMinyeongMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialMinyeongMultiChild.isRestrictedAreaTf();
        this.meetHouseHavingLessThan2AptTf = verificationOfSpecialMinyeongMultiChild.isMeetHouseHavingLessThan2AptTf();
        this.priorityApt = verificationOfSpecialMinyeongMultiChild.isPriorityApt();
        this.meetDepositTf = verificationOfSpecialMinyeongMultiChild.isMeetDepositTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialMinyeongMultiChild.isMeetBankbookJoinPeriodTf();
        this.id = verificationOfSpecialMinyeongMultiChild.getId();
        this.sibilingSupportYn = verificationOfSpecialMinyeongMultiChild.getSibilingSupportYn();
        this.ranking = verificationOfSpecialMinyeongMultiChild.getRanking();
        this.createdDate = verificationOfSpecialMinyeongMultiChild.getCreatedDate();
        this.modifiedDate = verificationOfSpecialMinyeongMultiChild.getModifiedDate();
    }
}
