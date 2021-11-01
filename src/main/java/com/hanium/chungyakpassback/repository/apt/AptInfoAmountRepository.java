package com.hanium.chungyakpassback.repository.apt;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoAmount;
import com.hanium.chungyakpassback.entity.apt.AptInfoTargetSpecial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AptInfoAmountRepository extends JpaRepository<AptInfoAmount, Long> {
    Optional<AptInfoAmount> findByHousingType(String housingType);
    Optional<AptInfoAmount> findByHousingTypeAndAptInfo(String housingType, AptInfo aptInfo);
}
