package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.standard.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HouseMemberRelationRepository extends JpaRepository<HouseMemberRelation, Long> {
    List<HouseMemberRelation> findAllByUser(User user);
    List<HouseMemberRelation> findAllByOpponent(HouseMember houseMember);
    List<HouseMemberRelation> findAllByUserAndRelationId(User user, Long id);
    Optional<HouseMemberRelation> findByUserAndOpponent(User user, HouseMember opponent);
    Optional<HouseMemberRelation> findByUserAndRelation(User user, Relation relation);
    Optional<HouseMemberRelation> findByOpponent(HouseMember houseMember);
    Optional<HouseMemberRelation> findByOpponentId(Long id);
}
