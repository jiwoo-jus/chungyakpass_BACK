package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialMinyeongNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialMinyeongNewlyMarriedRepository extends JpaRepository<VerificationRecordSpecialMinyeongNewlyMarried, Long> {
    List<VerificationRecordSpecialMinyeongNewlyMarried> findAllByUser(User user);
}
