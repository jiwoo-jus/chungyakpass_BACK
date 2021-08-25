package com.hanium.chungyakpassback.service.vertification;


import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;

public interface GeneralPrivateVerificationService {

    int americanAgeCount(); //만나이계산

    boolean surroundingArea(AptInfo aptInfo); //인근지역여부확인

    boolean accountStatus(AptInfo aptInfo, AptInfoTarget aptInfoTarget); //청약통장조건충족확인

    boolean householder(); //세대주여부확인

    boolean winningHistory(); //전세대원5년이내당첨이력존재여부확인

    boolean restrictedArea(AptInfo aptInfo); //규제지역여부확인

    boolean hasHouseYn(); //소유주택수

    boolean specialNote(AptInfo aptInfo, AptInfoTarget aptInfoTarget); //주거전용 85 초과 공공건설임대주택, 수도권에 지정된 공공주택에서 공급하는 민영주택에 청약하는지 여부 확인

    boolean depositAmount(AptInfoTarget aptInfoTarget); //예치금액충족여부확인

    boolean termsOfPolicy(AptInfo aptInfo); //가입기간충족여부확인



}
