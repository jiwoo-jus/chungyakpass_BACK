package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseholdMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdMemberRepository extends JpaRepository<HouseholdMember, Long> {
}
