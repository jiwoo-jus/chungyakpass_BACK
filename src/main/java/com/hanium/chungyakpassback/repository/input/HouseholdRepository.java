package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.Household;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseholdRepository extends JpaRepository<Household, Long> {
}
