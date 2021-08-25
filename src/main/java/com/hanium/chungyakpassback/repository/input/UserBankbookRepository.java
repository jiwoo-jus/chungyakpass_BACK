package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBankbookRepository extends JpaRepository<UserBankbook, Long> {

//    Optional<UserBankbook> findByUserId(Long userId);

    UserBankbook findByUser(User user);

}
