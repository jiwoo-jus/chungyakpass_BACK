package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminFirstLife;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongFirstLife;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminFirstLifeRepository extends JpaRepository<VerificationRecordSpecialKookminFirstLife, Long> {
    List<VerificationRecordSpecialKookminFirstLife> findAllByUser(User user);
}

