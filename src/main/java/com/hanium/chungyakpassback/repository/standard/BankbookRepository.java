package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.Bankbook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankbookRepository extends JpaRepository<Bankbook, Long> {
    Optional<Bankbook> findByBankbook (com.hanium.chungyakpassback.enumtype.Bankbook bankbook);
}
