package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptInfoRepository extends JpaRepository<AptInfo, Integer> {
}
