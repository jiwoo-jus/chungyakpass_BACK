package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialMinyeongMultiChildRepository extends JpaRepository<VerificationOfSpecialMinyeongMultiChild, Long> {
    List<VerificationOfSpecialMinyeongMultiChild> findAllByUser(User user);
}
