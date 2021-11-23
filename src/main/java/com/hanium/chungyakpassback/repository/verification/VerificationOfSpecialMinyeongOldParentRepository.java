package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialMinyeongOldParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialMinyeongOldParentRepository extends JpaRepository<VerificationOfSpecialMinyeongOldParent, Long> {
    List<VerificationOfSpecialMinyeongOldParent> findAllByUser(User user);
}
