package com.hanium.chungyakpassback.repository;
import com.hanium.chungyakpassback.service.vertification.GeneralPrivateVerificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneralPrivateVerificationServiceTest {

    @Autowired
    GeneralPrivateVerificationService GeneralPrivateVerificationservice;

    @Test
    public void accountStatus() {
        System.out.println("청약통장충족여부: "+GeneralPrivateVerificationservice.accountStatus());
    }

    @Test
    public void americanAgeCount() {
        System.out.println("americanAgeCount: "+GeneralPrivateVerificationservice.americanAgeCount());
    }

//    @Test
//    public void householderYn() {
//        System.out.println("세대주여부: "+GeneralPrivateVerificationservice.householderYn());
//    }

    @Test
    public void restrictedAreaYn() {
        System.out.println("규제지역여부: "+GeneralPrivateVerificationservice.restrictedAreaYn());
    }

    @Test
    public void hasHouseYn() {
        System.out.println("주택소유: "+GeneralPrivateVerificationservice.hasHouseYn());
    }

    @Test
    public void depositAmount() {
        System.out.println("예치금액충족: "+GeneralPrivateVerificationservice.depositAmount());
    }
    @Test
    public void termsOfPolicy() {
        System.out.println("가입기간충족: "+GeneralPrivateVerificationservice.termsOfPolicy());
    }

    @Test
    public void surroundingArea() {
        System.out.println("인근지역여부: "+GeneralPrivateVerificationservice.surroundingArea());
    }
    @Test
    public void specialNote() {
        System.out.println("특이사항충족: "+GeneralPrivateVerificationservice.specialNote());
    }

    @Test
    public void winningHistory() {
        System.out.println("전세대원5년이내당첨이력존재여부: "+GeneralPrivateVerificationservice.winningHistory());
    }
    public void 조회() {

    }

}
