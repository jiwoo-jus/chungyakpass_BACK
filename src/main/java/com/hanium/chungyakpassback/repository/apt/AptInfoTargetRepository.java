package com.hanium.chungyakpassback.repository.apt;

import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AptInfoTargetRepository extends JpaRepository<AptInfoTarget, Long> {
    Optional<AptInfoTarget> findByHousingType(String housingType);
}
