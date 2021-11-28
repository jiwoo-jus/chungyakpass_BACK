package com.hanium.chungyakpassback.repository.apt;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.apt.AptInfoTargetSpecial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AptInfoTargetSpecialRepository extends JpaRepository<AptInfoTargetSpecial,Long> {
    Optional<AptInfoTargetSpecial> findByHousingTypeAndAptInfo(String housingType, AptInfo aptInfo);
}
