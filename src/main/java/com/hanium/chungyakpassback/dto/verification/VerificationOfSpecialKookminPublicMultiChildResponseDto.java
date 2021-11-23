package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminMultiChild;
import com.hanium.chungyakpassback.enumtype.KookminType;
import com.hanium.chungyakpassback.enumtype.Ranking;
import com.hanium.chungyakpassback.enumtype.Yn;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationOfSpecialKookminPublicMultiChildResponseDto {

    private Long verificationRecordSpecialKookminMultiChildId;

    private Integer americanAge;
    private boolean meetLivingInSurroundAreaTf;
    private boolean accountTf;
    private boolean meetMonthlyAverageIncomeTf;
    private boolean meetPropertyTf;
    private boolean meetHomelessHouseholdMembersTf;
    private boolean meetAllHouseMemberRewinningRestrictionTf;
    private Integer calcMinorChildren;
    private boolean householderTf;
    private boolean restrictedAreaTf;
    private boolean meetBankbookJoinPeriodTf;
    private Yn sibilingSupportYn;
    private KookminType kookminType;
    private Ranking ranking;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;


    @Builder
    public VerificationOfSpecialKookminPublicMultiChildResponseDto(VerificationOfSpecialKookminMultiChild verificationOfSpecialKookminMultiChild) {
        this.americanAge = verificationOfSpecialKookminMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationOfSpecialKookminMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationOfSpecialKookminMultiChild.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationOfSpecialKookminMultiChild.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationOfSpecialKookminMultiChild.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationOfSpecialKookminMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationOfSpecialKookminMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationOfSpecialKookminMultiChild.getCalcMinorChildren();
        this.householderTf = verificationOfSpecialKookminMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationOfSpecialKookminMultiChild.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationOfSpecialKookminMultiChild.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminMultiChildId = verificationOfSpecialKookminMultiChild.getId();
        this.sibilingSupportYn = verificationOfSpecialKookminMultiChild.getSibilingSupportYn();
        this.kookminType = verificationOfSpecialKookminMultiChild.getKookminType();
        this.ranking = verificationOfSpecialKookminMultiChild.getRanking();
        this.createdDate = verificationOfSpecialKookminMultiChild.getCreatedDate();
        this.modifiedDate = verificationOfSpecialKookminMultiChild.getModifiedDate();
    }
}
