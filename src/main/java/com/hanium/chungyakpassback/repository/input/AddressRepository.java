package com.hanium.chungyakpassback.repository.input;

import com.hanium.chungyakpassback.entity.input.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
