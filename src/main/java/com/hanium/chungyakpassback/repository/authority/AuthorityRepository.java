package com.hanium.chungyakpassback.repository.authority;

import com.hanium.chungyakpassback.entity.authority.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
