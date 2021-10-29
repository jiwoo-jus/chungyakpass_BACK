package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberChungyak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseMemberChungyakRepository extends JpaRepository<HouseMemberChungyak, Long> {

    List<HouseMemberChungyak> findAllByHouseMember(HouseMember houseMember);
}
