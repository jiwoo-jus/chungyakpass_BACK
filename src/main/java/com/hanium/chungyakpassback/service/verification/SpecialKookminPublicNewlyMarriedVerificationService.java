package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

import java.time.LocalDate;

public interface SpecialKookminPublicNewlyMarriedVerificationService {

    int calcAmericanAge(LocalDate birthday); //만나이

    boolean meetLivingInSurroundArea(User user, AptInfo aptInfo); //인근지역거주조건충족여부

    boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장유형조건충족여부

    boolean meetRecipient(User user); //신혼부부공공주택적용국민주택대상자충족여부

    boolean hasMinorChildren(User user); //미성년자녀존재여부

    boolean meetMonthlyAverageIncomePriority(User user); //월평균소득기준충족여부_우선공급

    boolean meetMonthlyAverageIncomeGeneral(User user); //월평균소득기준충족여부_일반공급

    boolean meetProperty(User user); //자산기준충족여부

    boolean is2ndChungyak(User user); //2순위청약신청대상여부

    boolean meetHomelessHouseholdMembers(User user); //전세대원무주택세대구성원충족여부

    boolean meetAllHouseMemberRewinningRestriction(User user); //전세대원재당첨제한여부

    boolean isHouseholder(User user); //세대주여부

    boolean isRestrictedArea(AptInfo aptInfo); //규제지역여부

    boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo); //가입기간충족여부확인

    boolean meetNumberOfPayments(User user, AptInfo aptInfo); //납입횟수충족여부확인

}
