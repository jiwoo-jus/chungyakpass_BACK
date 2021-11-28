package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
