package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.*;
import com.hanium.chungyakpassback.domain.input.*;
import com.hanium.chungyakpassback.domain.input.청약자격점검_다자녀가구;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@SpringBootTest
@Transactional
public class RepositoryTest {

    @Autowired
    EntityManager em;

    @Test
    @Commit
    void 엔티티생성(){
        주소 주소1 = 주소.builder().지역_레벨1(지역_레벨1.서울시).지역_레벨2(지역_레벨2.동작구).build();
        주소 주소2 = 주소.builder().지역_레벨1(지역_레벨1.인천시).지역_레벨2(지역_레벨2.부평구).build();
        주소 주소3 = 주소.builder().상세주소("서울시 구로구 연동로 성공회대").우편번호(23).build();

        세대 세대1 = new 세대(주소1);
        세대 세대2 = new 세대(주소1);
        세대 세대3 = new 세대(주소2);

        //필수 필드만 build, 나머지는 setter 이용
        세대구성원 세대구성원1 = 세대구성원.builder().세대(세대1).세대주여부(여부.y).이름("장윤정").build();
        세대구성원 세대구성원2 = 세대구성원.builder().세대(세대3).세대주여부(여부.y).이름("도경완").build();
        세대구성원 세대구성원3 = 세대구성원.builder().세대(세대1).세대주여부(여부.n).이름("도연우").build();
        세대구성원1.set세대구성원배우자(세대구성원2);
        세대구성원2.set세대구성원배우자(세대구성원1);
        //전체 필드 대상 build
        세대구성원 세대구성원4 = 세대구성원.builder().세대(세대2).세대주여부(여부.y).이름("이효리").생년월일(LocalDate.of(1985, 03, 12)).build();
        세대구성원 세대구성원5 = 세대구성원.builder().세대(세대2).이름("이상순").세대주여부(여부.n).build();
        세대구성원4.set세대구성원배우자(세대구성원5);
        세대구성원5.set세대구성원배우자(세대구성원4);

        회원 회원1 = 회원.builder().이메일("jyj@naver.com").비밀번호("password1234").세대구성원본인(세대구성원1).장기복무중인군인여부(여부.n).build();
        회원 회원2 = 회원.builder().이메일("dkw@naver.com").비밀번호("password5678").세대구성원본인(세대구성원2).장기복무중인군인여부(여부.n).build();

        회원_청약통장 회원_청약통장1 = 회원_청약통장.builder().개설은행(개설은행.우리).예치금액(7200000).납입횟수(38).청약통장종류(청약통장종류.주택청약종합저축).회원(회원1).유효여부(여부.y).build();

        세대구성원_관계 세대구성원_관계1 = 세대구성원_관계.builder().회원(회원1).관계자_세대구성원(세대구성원2).관계(관계.배우자).build();
        세대구성원_관계 세대구성원_관계2 = 세대구성원_관계.builder().회원(회원1).관계자_세대구성원(세대구성원3).관계(관계.자녀).build();
        세대구성원_관계 세대구성원_관계3 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원1).관계(관계.배우자).build();
        세대구성원_관계 세대구성원_관계4 = 세대구성원_관계.builder().회원(회원2).관계자_세대구성원(세대구성원3).관계(관계.자녀).build();

        세대구성원_소득 세대구성원_소득1 = 세대구성원_소득.builder().세대구성원(세대구성원1).월평균소득(6000).build();
        세대구성원_자산 세대구성원_자산1 = 세대구성원_자산.builder().세대구성원(세대구성원1).자산유형(자산유형.자동차).금액(50000000).build();
        세대구성원_청약제한사항 세대구성원_청약제한사항1 = 세대구성원_청약제한사항.builder().세대구성원(세대구성원4).당첨일(LocalDate.of(2021, 03, 11)).재당첨제한(LocalDate.of(2021, 03, 10)).build();
        세대구성원_청약신청이력 세대구성원_청약신청이력1 = 세대구성원_청약신청이력.builder().세대구성원(세대구성원1).build();

        청약자격점검 청약자격점검1 = 청약자격점검.builder().회원(회원1).주택형("102.9838A").주택처분서약(여부.y).부양가족수(5).청약지역거주기간(1).build();
        청약자격점검_다자녀가구 청약자격점검_다자녀가구1 = 청약자격점검_다자녀가구.builder().청약자격점검(청약자격점검1).다자녀가구유형(다자녀가구유형.세세대이상).미성년자녀수(4).영유아자녀수(1).build();
        청약자격점검_신혼부부 청약자격점검_신혼부부1 = 청약자격점검_신혼부부.builder().청약자격점검(청약자격점검1).신혼부부유형(신혼부부유형.한부모가족).미성년자녀수(1).한부모가족자녀나이(2).build();
        청약자격점검_생애최초 청약자격점검_생애최초1 = 청약자격점검_생애최초.builder().청약자격점검(청약자격점검1).소득세5년이상납부여부(여부.y).저축액(800).build();
        청약자격점검_노부모부양 청약자격점검_노부모부양1 = 청약자격점검_노부모부양.builder().청약자격점검(청약자격점검1).노부모부양여부(여부.y).build();






        em.persist(주소1);
        em.persist(주소2);
        em.persist(세대1);
        em.persist(세대2);
        em.persist(세대3);
        em.persist(세대구성원1);
        em.persist(세대구성원2);
        em.persist(세대구성원3);
        em.persist(세대구성원4);
        em.persist(세대구성원5);
        em.persist(회원1);
        em.persist(회원2);
        em.persist(회원_청약통장1);
        em.persist(세대구성원_관계1);
        em.persist(세대구성원_관계2);
        em.persist(세대구성원_관계3);
        em.persist(세대구성원_관계4);
        em.persist(세대구성원_소득1);
        em.persist(세대구성원_자산1);
        em.persist(세대구성원_청약제한사항1);
        em.persist(세대구성원_청약신청이력1);
        em.persist(청약자격점검1);
        em.persist(청약자격점검_다자녀가구1);
        em.persist(청약자격점검_신혼부부1);
        em.persist(청약자격점검_생애최초1);
        em.persist(청약자격점검_노부모부양1);
    }
}
