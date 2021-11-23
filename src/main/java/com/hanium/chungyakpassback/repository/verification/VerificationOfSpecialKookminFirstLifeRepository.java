package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminFirstLife;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialKookminFirstLifeRepository extends JpaRepository<VerificationOfSpecialKookminFirstLife, Long> {
    List<VerificationOfSpecialKookminFirstLife> findAllByUser(User user);
}

