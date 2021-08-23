package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseMemberRepository extends JpaRepository<HouseMember, Long> {
}
