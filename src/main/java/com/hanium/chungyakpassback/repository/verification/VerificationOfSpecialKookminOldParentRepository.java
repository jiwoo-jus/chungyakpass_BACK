package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminOldParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialKookminOldParentRepository extends JpaRepository<VerificationOfSpecialKookminOldParent, Long> {
    List<VerificationOfSpecialKookminOldParent> findAllByUser(User user);
}
