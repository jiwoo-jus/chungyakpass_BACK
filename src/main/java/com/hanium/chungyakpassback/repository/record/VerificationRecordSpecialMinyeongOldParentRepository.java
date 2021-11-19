package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminMultiChild;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongOldParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialMinyeongOldParentRepository extends JpaRepository<VerificationRecordSpecialMinyeongOldParent, Long> {
    List<VerificationRecordSpecialMinyeongOldParent> findAllByUser(User user);
}
