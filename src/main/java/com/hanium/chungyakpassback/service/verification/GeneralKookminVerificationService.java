package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

import java.time.LocalDate;

public interface GeneralKookminVerificationService {

    GeneralKookminResponseDto generalKookminService(GeneralKookminDto generalKookminDto);

    GeneralKookminUpdateDto generalKookminUpdateDto(Long verificationRecordGeneralKookminId, GeneralKookminUpdateDto generalKookminUpdateDto);

    int calcAmericanAge(LocalDate birthday); //만나이

    boolean meetLivingInSurroundArea(User user, AptInfo aptInfo); //인근지역거주조건충족여부

    boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장유형조건충족여부

    boolean meetHomelessHouseholdMembers(User user); //전세대원무주택세대구성원충족여부

    boolean meetAllHouseMemberRewinningRestriction(User user); //전세대원재당첨제한여부

    boolean isHouseholder(User user); //세대주여부

    boolean isRestrictedArea(AptInfo aptInfo); //규제지역여부

    boolean meetAllHouseMemberNotWinningIn5years(User user); //전세대원5년이내미당첨조건충족여부

    boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo); //가입기간충족여부확인

    boolean meetNumberOfPayments(User user, AptInfo aptInfo); //납입횟수충족여부확인
}
