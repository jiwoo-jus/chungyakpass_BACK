package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMemberChungyak;
import com.hanium.chungyakpassback.entity.input.HouseMemberChungyakRestriction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseMemberChungyakRestrictionRepository extends JpaRepository<HouseMemberChungyakRestriction, Long> {
    void deleteByHouseMemberChungyak(HouseMemberChungyak houseMemberChungyak);
    HouseMemberChungyakRestriction findByHouseMemberChungyak(HouseMemberChungyak houseMemberChungyak);
}
