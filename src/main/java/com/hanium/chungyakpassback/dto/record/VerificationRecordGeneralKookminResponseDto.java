package com.hanium.chungyakpassback.dto.record;

import com.hanium.chungyakpassback.dto.verification.GeneralKookminDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookminRequest;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.apt.AptInfoTargetRepository;
import com.hanium.chungyakpassback.repository.input.UserRepository;
import com.hanium.chungyakpassback.service.verification.GeneralKookminVerificationService;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationRecordGeneralKookminResponseDto {

    GeneralKookminVerificationService generalKookminVerificationService;
    UserRepository userRepository;
    AptInfoRepository aptInfoRepository;
    AptInfoTargetRepository aptInfoTargetRepository;
    GeneralKookminDto generalKookminDto;

    public Long id;

    public Long verificationRecordId; //청약자격결과id

    public boolean meetLivingInSurroundAreaTf;

    public boolean accountTf;

    public boolean meetHomelessHouseholdMembersTf;

    public boolean householderTf;

    public boolean isRestrictedAreaTf;

    public boolean meetAllHouseMemberNotWinningIn5yearsTf;

    public boolean meetAllHouseMemberRewinningRestrictionTf;

    public boolean meetBankbookJoinPeriodTf;

    public boolean meetNumberOfPaymentsTf;

//    @Builder
//    public VerificationRecordGeneralKookminResponseDto(VerificationRecordGeneralKookminRequest verificationRecordGeneralKookminRequest) {
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        AptInfo aptInfo = aptInfoRepository.findById(generalKookminDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
//        AptInfoTarget aptInfoTarget = aptInfoTargetRepository.findByHousingTypeAndAptInfo(generalKookminDto.getHousingType(), aptInfo).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
//
//        this.id = verificationRecordGeneralKookminRequest.getId();
//        this.verificationRecordId = verificationRecordGeneralKookminRequest.getVerificationRecord().getId();
//        this.meetLivingInSurroundAreaTf = meetLivingInSurroundAreaTf;
//        this.accountTf = accountTf;
//        this.meetHomelessHouseholdMembersTf = meetHomelessHouseholdMembersTf;
//        this.householderTf = householderTf;
//        this.isRestrictedAreaTf = isRestrictedAreaTf;
//        this.meetAllHouseMemberNotWinningIn5yearsTf = meetAllHouseMemberNotWinningIn5yearsTf;
//        this.meetAllHouseMemberRewinningRestrictionTf = meetAllHouseMemberRewinningRestrictionTf;
//        this.meetBankbookJoinPeriodTf = meetBankbookJoinPeriodTf;
//        this.meetNumberOfPaymentsTf = meetNumberOfPaymentsTf;
//    }

}
