package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminOldParent;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongOldParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminOldParentRepository extends JpaRepository<VerificationRecordSpecialKookminOldParent, Long> {
    List<VerificationRecordSpecialKookminOldParent> findAllByUser(User user);
}
