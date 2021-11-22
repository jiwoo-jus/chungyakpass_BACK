package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminMultiChildRepository extends JpaRepository<VerificationRecordSpecialKookminMultiChild, Long> {

    List<VerificationRecordSpecialKookminMultiChild> findAllByUser(User user);
}
