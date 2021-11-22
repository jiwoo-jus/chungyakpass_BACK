package com.hanium.chungyakpassback.dto.verification;

import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminMultiChild;
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
public class SpecialKookminPublicMultiChildResponseDto {

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
    public SpecialKookminPublicMultiChildResponseDto(VerificationRecordSpecialKookminMultiChild verificationRecordSpecialKookminMultiChild) {
        this.americanAge = verificationRecordSpecialKookminMultiChild.getAmericanAge();
        this.meetLivingInSurroundAreaTf = verificationRecordSpecialKookminMultiChild.isMeetLivingInSurroundAreaTf();
        this.accountTf = verificationRecordSpecialKookminMultiChild.isAccountTf();
        this.meetMonthlyAverageIncomeTf = verificationRecordSpecialKookminMultiChild.isMeetMonthlyAverageIncome();
        this.meetPropertyTf = verificationRecordSpecialKookminMultiChild.isMeetPropertyTf();
        this.meetHomelessHouseholdMembersTf = verificationRecordSpecialKookminMultiChild.isMeetHomelessHouseholdMemberTf();
        this.meetAllHouseMemberRewinningRestrictionTf = verificationRecordSpecialKookminMultiChild.isMeetAllHouseMemberRewinningRestrictionTf();
        this.calcMinorChildren = verificationRecordSpecialKookminMultiChild.getCalcMinorChildren();
        this.householderTf = verificationRecordSpecialKookminMultiChild.isHouseholderTf();
        this.restrictedAreaTf = verificationRecordSpecialKookminMultiChild.isRestrictedAreaTf();
        this.meetBankbookJoinPeriodTf = verificationRecordSpecialKookminMultiChild.isMeetBankbookJoinPeriodTf();
        this.verificationRecordSpecialKookminMultiChildId = verificationRecordSpecialKookminMultiChild.getId();
        this.sibilingSupportYn = verificationRecordSpecialKookminMultiChild.getSibilingSupportYn();
        this.kookminType = verificationRecordSpecialKookminMultiChild.getKookminType();
        this.ranking = verificationRecordSpecialKookminMultiChild.getRanking();
        this.createdDate = verificationRecordSpecialKookminMultiChild.getCreatedDate();
        this.modifiedDate = verificationRecordSpecialKookminMultiChild.getModifiedDate();
    }
}
