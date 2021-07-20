package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.input.세대;
import com.hanium.chungyakpassback.domain.input.주소;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

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

        em.persist(주소1);
        em.persist(주소2);
        em.persist(세대1);
        em.persist(세대2);
        em.persist(세대3);
    }
}
