package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfSpecialKookminMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfSpecialKookminMultiChildRepository extends JpaRepository<VerificationOfSpecialKookminMultiChild, Long> {

    List<VerificationOfSpecialKookminMultiChild> findAllByUser(User user);
}
