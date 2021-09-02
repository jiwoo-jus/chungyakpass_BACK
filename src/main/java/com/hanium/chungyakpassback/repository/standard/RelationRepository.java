package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Relation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelationRepository extends JpaRepository<Relation, Long> {
    Optional<Relation> findByRelation(com.hanium.chungyakpassback.enumtype.Relation relation);
}
