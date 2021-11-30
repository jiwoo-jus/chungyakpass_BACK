package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.dto.verification.*;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

import java.time.LocalDate;
import java.util.List;

public interface VerificationOfSpecialMinyeongMultiChildService {

    List<VerificationOfSpecialMinyeongMultiChildResponseDto> readSpecialMinyeongMultiChildVerifications(); //특별다자녀민영조회

    VerificationOfSpecialMinyeongMultiChildResponseDto createSpecialMinyeongMultiChildVerification(VerificationOfSpecialMinyeongMultiChildDto verificationOfSpecialMinyeongMultiChildDto); //특별다자녀민영저장

    VerificationOfSpecialMinyeongMultiChildResponseDto updateSpecialMinyeongMultiChildVerification(Long verificationRecordSpecialMinyeongMultiChildId, VerificationOfSpecialMinyeongMultiChildUpdateDto verificationOfSpecialMinyeongMultiChildUpdateDto); //특별다자녀민영업데이트

    int calcAmericanAge(LocalDate birthday); //만나이

    boolean meetLivingInSurroundArea(User user, AptInfo aptInfo); //인근지역거주조건충족여부

    boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장유형조건충족여부

    boolean meetHomelessHouseholdMembers(User user); //전세대원무주택세대구성원충족여부

    int calcMinorChildren(User user); //미성년자녀수계산(태아 포함)

    boolean isHouseholder(User user); //세대주여부

    boolean meetAllHouseMemberRewinningRestriction(User user); //전세대원재당첨제한여부

    boolean isRestrictedArea(AptInfo aptInfo); //규제지역여부

    boolean meetHouseHavingLessThan2Apt(User user); //소유주택2개미만세대충족여부

    boolean isPriorityApt(AptInfo aptInfo, AptInfoTarget aptInfoTarget); //주거전용 85 초과 공공건설임대주택, 수도권에 지정된 공공주택에서 공급하는 민영주택에 청약하는지 여부 확인

    boolean meetDeposit(User user, AptInfoTarget aptInfoTarget); //예치금액충족여부확인

    boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo); //가입기간충족여부확인


}
