package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.AddressLevel2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressLevel2Repository extends JpaRepository<AddressLevel2, Long> {
    AddressLevel2 findByAddressLevel2(com.hanium.chungyakpassback.enumtype.AddressLevel2 addressLevel2);
}
