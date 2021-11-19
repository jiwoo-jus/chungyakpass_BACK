package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookmin;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralMinyeong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordGeneralKookminRepository extends JpaRepository<VerificationRecordGeneralKookmin, Long> {

    List<VerificationRecordGeneralKookmin> findAllByUser(User user);

}
