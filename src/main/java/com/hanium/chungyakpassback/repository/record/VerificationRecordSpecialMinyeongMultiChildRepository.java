package com.hanium.chungyakpassback.repository.record;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.record.VerificationRecordGeneralKookmin;
import com.hanium.chungyakpassback.entity.record.VerificationRecordSpecialMinyeongMultiChild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VerificationRecordSpecialMinyeongMultiChildRepository extends JpaRepository<VerificationRecordSpecialMinyeongMultiChild, Long> {

    List<VerificationRecordSpecialMinyeongMultiChild> findAllByUser(User user);

}
