package com.hanium.chungyakpassback.repository.standard;

import com.hanium.chungyakpassback.entity.standard.AreaLevel1;
import com.hanium.chungyakpassback.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.Yn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaLevel1Repository extends JpaRepository<AreaLevel1, Long> {
    List<AreaLevel1> findAllByMetropolitanArea(Yn metropolitanArea);
    AreaLevel1 findByAddressLevel1(AddressLevel1 addressLevel1);

}
