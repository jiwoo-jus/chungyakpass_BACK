package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.*;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.standard.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

@SpringBootTest
@Transactional
public class RepositoryTest_standard {
    @Autowired
    EntityManager em;

    @Test
    @Commit
    void 엔티티생성() {
        월평균소득기준 월평균소득기준1 = new 월평균소득기준(여부.y, 특별공급유형.신혼부부, 공급방법.우선공급, 여부.n, 0, 100, 0, 6030160, 0, 0, 0, 0);
        em.persist(월평균소득기준1);
        월평균소득기준 월평균소득기준2 = new 월평균소득기준(여부.y, 특별공급유형.노부모부양, null, null, 0, 120, 0, 7236192, 0, 8513046, 0, 8513046);
        em.persist(월평균소득기준2);

        자산보유기준 자산보유기준1 = new 자산보유기준(자산유형.건물, 215500);
        자산보유기준 자산보유기준2 = new 자산보유기준(자산유형.토지, 215500);
        자산보유기준 자산보유기준3 = new 자산보유기준(자산유형.자동차, 34960);
        em.persist(자산보유기준1);
        em.persist(자산보유기준2);
        em.persist(자산보유기준3);

        청약통장 청약통장1 = new 청약통장(청약통장종류.주택청약종합저축, 여부.y, 여부.y, null);
        청약통장 청약통장2 = new 청약통장(청약통장종류.청약부금, 여부.n, 여부.y, 85);
        em.persist(청약통장1);
        em.persist(청약통장2);

        일순위_가입기간 일순위_가입기간1 = new 일순위_가입기간(공급유형.일반공급, null, 여부.y, 여부.y, 여부.n, 여부.y, 24);
        일순위_가입기간 일순위_가입기간2 = new 일순위_가입기간(공급유형.특별공급, 특별공급유형.노부모부양, 여부.y, 여부.y, 여부.n, 여부.y, 24);
        em.persist(일순위_가입기간1);
        em.persist(일순위_가입기간2);

        일순위_납입횟수 일순위_납입횟수1 = new 일순위_납입횟수(공급유형.일반공급, null, 여부.y, 여부.y, 여부.n, 여부.y, 24);
        일순위_납입횟수 일순위_납입횟수2 = new 일순위_납입횟수(공급유형.특별공급, 특별공급유형.노부모부양, 여부.y, 여부.y, 여부.n, 여부.y, 24);
        em.persist(일순위_납입횟수1);
        em.persist(일순위_납입횟수2);

        일순위_납입금 일순위_납입금1 = new 일순위_납입금(0, 85, 예치금액지역구분.서울부산, 300);
        일순위_납입금 일순위_납입금2 = new 일순위_납입금(135, null, 예치금액지역구분.서울부산, 1500);
        em.persist(일순위_납입금1);
        em.persist(일순위_납입금2);

        지역_레벨_1 지역_레벨_1_1 = new 지역_레벨_1(지역_레벨1.서울시, 1, 예치금액지역구분.서울부산, 여부.y);
        지역_레벨_1 지역_레벨_1_2 = new 지역_레벨_1(지역_레벨1.인천시, 1, 예치금액지역구분.기타광역시, 여부.y);
        em.persist(지역_레벨_1_1);
        em.persist(지역_레벨_1_2);

        지역_레벨_2 지역_레벨_2_1 = new 지역_레벨_2(지역_레벨_1_1, 지역_레벨2.종로구);
        em.persist(지역_레벨_2_1);

        아파트분양정보 아파트분양정보1 = new 아파트분양정보(지역_레벨1.서울시, 지역_레벨2.종로구, 1, "서울특별시 서초구 신반포로", 주택유형.민영주택, 여부.y, "래미안", "삼성물산", LocalDate.of(2021, 6, 7), LocalDate.of(2021, 6, 25), LocalDate.of(2021, 7, 9), LocalDate.of(2021, 7, 13), LocalDate.of(2023, 8, 1), 여부.y, 여부.y, 여부.y, 여부.n, 여부.n, 여부.n, 여부.n, 여부.n);
        em.persist(아파트분양정보1);

        아파트분양정보_청약접수일정 청약접수일정_1 = new 아파트분양정보_청약접수일정(아파트분양정보1, 공급유형.특별공급, 순위.일순위, LocalDate.of(2021, 7, 5), LocalDate.of(2021, 7, 5), LocalDate.of(2021, 7, 5), LocalDate.of(2021, 7, 5), LocalDate.of(2021, 7, 5), LocalDate.of(2021, 7, 5), 공급장소.인터넷);
        em.persist(청약접수일정_1);

        아파트분양정보_공급대상 공급대상_1 = new 아파트분양정보_공급대상(아파트분양정보1, "046.9300A", 62.6816, 2, 0, 2, "2021000418(01)");
        em.persist(공급대상_1);

        아파트분양정보_특별공급대상 특별공급대상_1 = new 아파트분양정보_특별공급대상(아파트분양정보1, "064.7600A", 18, 55, 46, 9, 27, 0, 0);
        em.persist(특별공급대상_1);

        아파트분양정보_공급금액 공급금액_1 = new 아파트분양정보_공급금액(아파트분양정보1, "046.9300A", 92370, 0);
        em.persist(공급금액_1);
    }
}