package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialKookminMultiChild;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialKookminMultiChildRepository extends JpaRepository<VerificationRecordSpecialKookminMultiChild, Long> {

    List<VerificationRecordSpecialKookminMultiChild> findAllByUser(User user);
}
