package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseMemberRelationRepository extends JpaRepository<HouseMemberRelation, Long> {
    List<HouseMemberRelation> findAllByUser(User user);
    HouseMemberRelation findByUserAndOpponent(User user, HouseMember opponent);
}
