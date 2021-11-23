package com.hanium.chungyakpassback.service.verification;


import com.hanium.chungyakpassback.dto.verification.VerificationOfGeneralMinyeongDto;
import com.hanium.chungyakpassback.dto.verification.VerificationOfGeneralMinyeongResponseDto;
import com.hanium.chungyakpassback.dto.verification.VerificationOfGeneralMinyeongUpdateDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.User;

import java.time.LocalDate;
import java.util.List;

public interface VerificationOfGeneralMinyeongService {

    List<VerificationOfGeneralMinyeongResponseDto> readGeneralMinyeongVerifications();

    VerificationOfGeneralMinyeongResponseDto createGeneralMinyeongVerification(VerificationOfGeneralMinyeongDto verificationOfGeneralMinyeongDto);

    VerificationOfGeneralMinyeongResponseDto updateGeneralMinyeongVerification(Long verificationRecordGeneralMinyeongId, VerificationOfGeneralMinyeongUpdateDto verificationOfGeneralMinyeongUpdateDto);

    int calcAmericanAge(LocalDate birthday); //만나이

    boolean meetLivingInSurroundArea(User user, AptInfo aptInfo); //인근지역거주조건충족여부

    boolean meetBankbookType(User user, AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장유형조건충족여부

    boolean isHouseholder(User user); //세대주여부

    boolean meetAllHouseMemberNotWinningIn5years(User user); //전세대원5년이내미당첨조건충족여부

    boolean isRestrictedArea(AptInfo aptInfo); //규제지역여부

    boolean meetHouseHavingLessThan2Apt(User user); //소유주택2개미만세대충족여부

    boolean isPriorityApt(AptInfo aptInfo, AptInfoTarget aptInfoTarget); //주거전용 85 초과 공공건설임대주택, 수도권에 지정된 공공주택에서 공급하는 민영주택에 청약하는지 여부 확인

    boolean meetAllHouseMemberRewinningRestriction(User user); //전세대원재당첨제한여부

    boolean meetDeposit(User user, AptInfoTarget aptInfoTarget); //예치금액충족여부확인

    boolean meetBankbookJoinPeriod(User user, AptInfo aptInfo); //가입기간충족여부확인

}
