package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminNewlyMarriedRepository extends JpaRepository<VerificationRecordSpecialKookminNewlyMarried, Long> {
    List<VerificationRecordSpecialKookminNewlyMarried> findAllByUser(User user);
}
