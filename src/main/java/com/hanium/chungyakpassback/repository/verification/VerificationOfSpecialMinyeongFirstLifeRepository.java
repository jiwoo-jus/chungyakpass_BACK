package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongFirstLife;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialMinyeongFirstLifeRepository extends JpaRepository<VerificationOfSpecialMinyeongFirstLife, Long> {
    List<VerificationOfSpecialMinyeongFirstLife> findAllByUser(User user);
}
