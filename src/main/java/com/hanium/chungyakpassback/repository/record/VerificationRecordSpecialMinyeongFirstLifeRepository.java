package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminNewlyMarried;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongFirstLife;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialMinyeongFirstLifeRepository extends JpaRepository<VerificationRecordSpecialMinyeongFirstLife, Long> {
    List<VerificationRecordSpecialMinyeongFirstLife> findAllByUser(User user);
}
