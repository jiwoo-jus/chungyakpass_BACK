package com.hanium.chungyakpassback.repository;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import com.hanium.chungyakpassback.domain.enumtype.특별공급유형;
import com.hanium.chungyakpassback.domain.input.세대;
import com.hanium.chungyakpassback.domain.input.세대구성원;
import com.hanium.chungyakpassback.domain.input.주소;
import com.hanium.chungyakpassback.domain.standard.월평균소득기준;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class RepositoryTest_std {
    @Autowired
    EntityManager em;

    @Test
    @Commit
    void 엔티티생성() {
        월평균소득기준 월평균소득기준1 = new 월평균소득기준(여부.y, 특별공급유형.신혼부부, null, 여부.n, 0, 100, 0, 6030160, 0, 0, 0, 0);
        em.persist(월평균소득기준1);
    }
}
