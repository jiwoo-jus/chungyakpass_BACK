//package com.hanium.chungyakpassback.repository.record;
//
//import com.hanium.chungyakpassback.entity.input.User;
//import com.hanium.chungyakpassback.entity.record.VerificationRecord;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//public interface VerificationRecordRepository extends JpaRepository<VerificationRecord, Long> {
//
//    Optional<VerificationRecord> findByUser(User user);
//
//    List<VerificationRecord> findAllByUser(User user);
//
//}
