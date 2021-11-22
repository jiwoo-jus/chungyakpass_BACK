package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordSpecialKookminFirstLife;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminFirstLifeRepository extends JpaRepository<VerificationRecordSpecialKookminFirstLife, Long> {
    List<VerificationRecordSpecialKookminFirstLife> findAllByUser(User user);
}

