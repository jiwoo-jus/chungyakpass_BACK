package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.MonthlyAverageIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyAverageIncomeRepository extends JpaRepository<MonthlyAverageIncome, Long> {
}
