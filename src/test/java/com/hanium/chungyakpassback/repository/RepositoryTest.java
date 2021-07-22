package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.input.세대;
import com.hanium.chungyakpassback.domain.input.세대구성원;
import com.hanium.chungyakpassback.domain.input.주소;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import com.hanium.chungyakpassback.domain.input.회원;
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
    }
}
