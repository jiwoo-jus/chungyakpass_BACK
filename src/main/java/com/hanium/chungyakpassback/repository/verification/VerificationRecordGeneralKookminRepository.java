package com.hanium.chungyakpassback.repository.verification;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.verification.VerificationRecordGeneralKookmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordGeneralKookminRepository extends JpaRepository<VerificationRecordGeneralKookmin, Long> {

    List<VerificationRecordGeneralKookmin> findAllByUser(User user);

}
