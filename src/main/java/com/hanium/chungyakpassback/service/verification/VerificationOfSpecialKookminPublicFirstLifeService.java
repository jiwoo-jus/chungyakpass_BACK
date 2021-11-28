package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

import java.time.LocalDate;
import java.util.List;

public interface VerificationOfSpecialKookminPublicFirstLifeService {

    List<VerificationOfSpecialKookminPublicFirstLifeResponseDto> readSpecialKookminFirstLifeVerifications();

    VerificationOfSpecialKookminPublicFirstLifeResponseDto createSpecialKookminPublicFirstLifeVerification(VerificationOfSpecialKookminPublicFirstLifeDto verificationOfSpecialKookminPublicFirstLifeDto);

    VerificationOfSpecialKookminPublicFirstLifeResponseDto updateSpecialKookminPublicFirstLifeVerification(Long verificationRecordSpecialKookminFirstLifeId, VerificationOfSpecialKookminPublicFirstLifeUpdateDto verificationOfSpecialKookminPublicFirstLifeUpdateDto);

    int calcAmericanAge(LocalDate birthday); //만나이계산

    boolean meetLivingInSurroundArea(User user, AptInfo aptInfo); //인근지역거주조건충족여부

    boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장유형조건충족여부

    boolean meetRecipient(User user); //생애최초대상자충족여부

    boolean meetMonthlyAverageIncomePriority(User user); //월평균소득기준충족여부_우선공급

    boolean meetMonthlyAverageIncomeGeneral(User user); //월평균소득기준충족여부_일반공급

    boolean meetProperty(User user); //자산기준충족여부

    boolean meetHomelessHouseholdMembers(User user); //전세대원무주택세대구성원충족여부

    boolean isHouseholder(User user); //세대주여부

    boolean meetAllHouseMemberNotWinningIn5years(User user); //전세대원5년이내미당첨조건충족여부

    boolean meetAllHouseMemberRewinningRestriction(User user); //전세대원재당첨제한여부

    boolean isRestrictedArea(AptInfo aptInfo); //규제지역여부

    boolean meetNumberOfPayments(User user, AptInfo aptInfo); //납입횟수충족여부확인

    boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo); //가입기간충족여부확인

}
