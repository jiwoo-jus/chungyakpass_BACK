package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordGeneralMinyeong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordGeneralMinyeongRepository extends JpaRepository<VerificationRecordGeneralMinyeong, Long> {

    List<VerificationRecordGeneralMinyeong> findAllByUser(User user);
}
