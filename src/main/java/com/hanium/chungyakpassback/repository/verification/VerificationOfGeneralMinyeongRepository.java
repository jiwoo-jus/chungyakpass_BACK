package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationOfGeneralMinyeong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationOfGeneralMinyeongRepository extends JpaRepository<VerificationOfGeneralMinyeong, Long> {

    List<VerificationOfGeneralMinyeong> findAllByUser(User user);
}
