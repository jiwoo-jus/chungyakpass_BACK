package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfGeneralKookmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfGeneralKookminRepository extends JpaRepository<VerificationOfGeneralKookmin, Long> {
    List<VerificationOfGeneralKookmin> findAllByUser(User user);
}
