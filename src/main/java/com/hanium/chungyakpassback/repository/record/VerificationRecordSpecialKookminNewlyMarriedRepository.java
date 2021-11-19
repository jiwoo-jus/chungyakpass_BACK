package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminNewlyMarried;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongNewlyMarried;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminNewlyMarriedRepository extends JpaRepository<VerificationRecordSpecialKookminNewlyMarried, Long> {
    List<VerificationRecordSpecialKookminNewlyMarried> findAllByUser(User user);
}
