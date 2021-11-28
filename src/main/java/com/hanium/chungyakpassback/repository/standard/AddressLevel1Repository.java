package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AddressLevel1Repository extends JpaRepository<AddressLevel1, Long> {
    List<AddressLevel1> findAllByMetropolitanAreaYn(Yn metropolitanArea);
    Optional<AddressLevel1> findByAddressLevel1(com.hanium.chungyakpassback.enumtype.AddressLevel1 addressLevel1);

}
