package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialKookminNewlyMarriedRepository extends JpaRepository<VerificationOfSpecialKookminNewlyMarried, Long> {
    List<VerificationOfSpecialKookminNewlyMarried> findAllByUser(User user);
}
