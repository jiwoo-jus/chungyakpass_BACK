package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
