package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseholdRepository extends JpaRepository<House, Long> {
}
