package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialMinyeongNewlyMarriedRepository extends JpaRepository<VerificationOfSpecialMinyeongNewlyMarried, Long> {
    List<VerificationOfSpecialMinyeongNewlyMarried> findAllByUser(User user);
}
