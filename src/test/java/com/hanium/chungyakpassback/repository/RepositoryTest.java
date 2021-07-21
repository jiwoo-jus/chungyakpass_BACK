package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.input.세대;
import com.hanium.chungyakpassback.domain.input.세대구성원;
import com.hanium.chungyakpassback.domain.input.주소;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
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
        주소 주소1 = new 주소(지역_레벨1.서울시, 지역_레벨2.동작구);
        주소 주소2 = new 주소(지역_레벨1.인천시, 지역_레벨2.부평구);

        세대 세대1 = new 세대(주소1);
        세대 세대2 = new 세대(주소1);
        세대 세대3 = new 세대(주소2);

        세대구성원 세대구성원1 = new 세대구성원(세대1, 여부.y, "장윤정");
        세대구성원 세대구성원2 = new 세대구성원(세대3, 여부.y, "도경완");
        세대구성원 세대구성원3 = new 세대구성원(세대1, 여부.n, "도연우");
        세대구성원 세대구성원4 = new 세대구성원(세대2, 여부.y, "이효리");
        세대구성원 세대구성원5 = new 세대구성원(세대2, 여부.n, "이상순");

        세대구성원1.set배우자(세대구성원2);
        세대구성원1.set혼인신고일(LocalDate.of(1995, 12, 31));
        세대구성원1.set무주택시작일(LocalDate.of(2017, 06, 21));
        세대구성원1.set전입일자(LocalDate.of(2020, 06, 21));
        세대구성원2.set배우자(세대구성원1);
        세대구성원2.set혼인신고일(LocalDate.of(1995, 12, 31));
        세대구성원2.set무주택시작일(LocalDate.of(1970, 11, 28));
        세대구성원2.set전입일자(LocalDate.of(2021, 02, 01));


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
    }
}
