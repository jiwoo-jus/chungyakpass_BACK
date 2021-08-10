package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.*;
import com.hanium.chungyakpassback.domain.input.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;

@SpringBootTest
@Transactional
public class RepositoryTest_logic {
    @Autowired
    EntityManager em;

    @Test
    @Commit
    void 로직테스트() {
        주소 주소1 = 주소.builder().지역_레벨1(지역_레벨1.충청북도).지역_레벨2(지역_레벨2.충주시).상세주소("원앙9길 15").우편번호("27465").build();
        주소 주소2 = 주소.builder().지역_레벨1(지역_레벨1.서울시).지역_레벨2(지역_레벨2.서초구).상세주소("효령로 391").우편번호("06630").build();
        세대 세대1 = 세대.builder().주소(주소1).build();
        세대 세대2 = 세대.builder().주소(주소2).build();
        세대구성원 세대구성원1 = 세대구성원.builder().세대(세대1).세대주여부(여부.y).이름("장윤정").생년월일(LocalDate.of(1980, 2, 16)).외국인여부(여부.n).장기복무군인여부(여부.y).혼인신고일(LocalDate.of(2013, 5, 5)).전입신고일(LocalDate.of(2005, 1, 1)).build();
        세대구성원 세대구성원2 = 세대구성원.builder().세대(세대2).세대주여부(여부.y).이름("도경완").생년월일(LocalDate.of(1982, 3, 7)).외국인여부(여부.n).장기복무군인여부(여부.y).혼인신고일(LocalDate.of(2013, 5, 5)).전입신고일(LocalDate.of(2012, 1, 1)).build();
        세대구성원1.set세대구성원배우자(세대구성원2);
        세대구성원2.set세대구성원배우자(세대구성원1);
        세대구성원 세대구성원3 = 세대구성원.builder().세대(세대1).세대주여부(여부.n).이름("육흥복").생년월일(LocalDate.of(1956, 3, 5)).외국인여부(여부.n).장기복무군인여부(여부.y).혼인신고일(LocalDate.of(1978, 1, 1)).전입신고일(LocalDate.of(2007, 1, 1)).build();
        세대구성원 세대구성원4 = 세대구성원.builder().세대(세대2).세대주여부(여부.n).이름("장동수").생년월일(LocalDate.of(1951, 5, 5)).외국인여부(여부.n).장기복무군인여부(여부.y).혼인신고일(LocalDate.of(1978, 1, 1)).전입신고일(LocalDate.of(2012, 1, 1)).build();
        세대구성원 세대구성원5 = 세대구성원.builder().세대(세대2).세대주여부(여부.n).이름("도연우").생년월일(LocalDate.of(2014, 6, 13)).외국인여부(여부.n).장기복무군인여부(여부.y).전입신고일(LocalDate.of(2014, 6, 15)).build();
        세대구성원 세대구성원6 = 세대구성원.builder().세대(세대2).세대주여부(여부.n).이름("도하영").생년월일(LocalDate.of(2018, 11, 9)).외국인여부(여부.n).장기복무군인여부(여부.y).전입신고일(LocalDate.of(2018, 11, 10)).build();
        세대구성원3.set세대구성원배우자(세대구성원4);
        세대구성원4.set세대구성원배우자(세대구성원3);

        회원 회원1 = 회원.builder().세대구성원본인(세대구성원1).이메일("jyj@naver.com").비밀번호("test123").build();
        회원 회원2 = 회원.builder().세대구성원본인(세대구성원2).이메일("dkw@daum.net").비밀번호("test123").build();
        회원1.set세대구성원본인(세대구성원1);

        회원_청약통장 청약통장1 = 회원_청약통장.builder().회원(회원1).개설은행(개설은행.신한).청약통장종류(청약통장종류.주택청약종합저축).가입일(LocalDate.of(2013, 1, 1)).예치금액(7200000).납입횟수(72).유효여부(여부.y).build();
        회원_청약통장 청약통장2 = 회원_청약통장.builder().회원(회원2).개설은행(개설은행.우리).청약통장종류(청약통장종류.주택청약종합저축).가입일(LocalDate.of(2017, 1, 1)).예치금액(4000000).납입횟수(40).유효여부(여부.y).build();

        세대구성원_관계 관계1 = 세대구성원_관계.builder().회원(회원1).관계자_세대구성원(세대구성원1).관계(관계.본인).build();
        세대구성원_관계 관계2 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원2).관계(관계.본인).build();
        세대구성원_관계 관계3 = 세대구성원_관계.builder().회원(회원1).관계자_세대구성원(세대구성원3).관계(관계.부모).build();
        세대구성원_관계 관계4 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원4).관계(관계.배우자의부모).build();
        세대구성원_관계 관계5 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원5).관계(관계.자녀).build();
        세대구성원_관계 관계6 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원6).관계(관계.자녀).build();

        세대구성원_소득 소득1 = 세대구성원_소득.builder().세대구성원(세대구성원1).월평균소득(5000000).build();
        세대구성원_소득 소득2 = 세대구성원_소득.builder().세대구성원(세대구성원2).월평균소득(4000000).build();

        세대구성원_자산 자산1 = 세대구성원_자산.builder().세대구성원(세대구성원1).자산유형(자산유형.건물).분양권여부(여부.y).주거용여부(여부.y).주거용건물유형(주거용건물유형.단독주택).취득일(LocalDate.of(2016, 3, 3)).금액(330000000).과세기준일(LocalDate.of(2020, 6, 1)).build();
        세대구성원_자산 자산2 = 세대구성원_자산.builder().세대구성원(세대구성원2).자산유형(자산유형.자동차).취득일(LocalDate.of(2017, 1, 1)).금액(20000000).build();

        세대구성원_청약신청이력 신청이력1 = 세대구성원_청약신청이력.builder().세대구성원(세대구성원2).주택명("에비뉴청계").공급유형(공급유형.특별공급).특별공급유형(특별공급유형.생애최초).주택형("084.9900A").순위(순위.일순위).결과(결과.미당첨).build();

        세대구성원_청약제한사항 제한사항1 = 세대구성원_청약제한사항.builder().세대구성원(세대구성원3).세대구성원_청약신청이력(신청이력1).당첨일(LocalDate.of(2019, 1, 1)).재당첨제한(LocalDate.of(2024, 1, 1)).특별공급제한(특별공급제한.청약불가).build();


        em.persist(주소1);
        em.persist(주소2);
        em.persist(세대1);
        em.persist(세대2);
        em.persist(세대구성원1);
        em.persist(세대구성원2);
        em.persist(세대구성원3);
        em.persist(세대구성원4);
        em.persist(세대구성원5);
        em.persist(세대구성원6);

        em.persist(회원1);
        em.persist(회원2);

        em.persist(청약통장1);
        em.persist(청약통장2);

        em.persist(관계1);
        em.persist(관계2);
        em.persist(관계3);
        em.persist(관계4);
        em.persist(관계5);
        em.persist(관계6);

        em.persist(소득1);
        em.persist(소득2);

        em.persist(자산1);
        em.persist(자산2);

        em.persist(신청이력1);

        em.persist(제한사항1);

    }
}
