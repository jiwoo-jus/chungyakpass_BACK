package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfpecialKookminNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialKookminNewlyMarriedRepository extends JpaRepository<VerificationOfpecialKookminNewlyMarried, Long> {
    List<VerificationOfpecialKookminNewlyMarried> findAllByUser(User user);
}
