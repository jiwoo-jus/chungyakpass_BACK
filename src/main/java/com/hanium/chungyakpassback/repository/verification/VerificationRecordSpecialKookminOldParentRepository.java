package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminOldParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminOldParentRepository extends JpaRepository<VerificationRecordSpecialKookminOldParent, Long> {
    List<VerificationRecordSpecialKookminOldParent> findAllByUser(User user);
}
