package com.hanium.chungyakpassback.service;

import com.hanium.chungyakpassback.dto.AddressDto;
import com.hanium.chungyakpassback.entity.input.Address;
import com.hanium.chungyakpassback.entity.input.Household;
import com.hanium.chungyakpassback.repository.input.AddressRepository;
import com.hanium.chungyakpassback.repository.input.HouseholdRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final HouseholdRepository householdRepository;
    private final AddressRepository addressRepository;

    public UserDataService(HouseholdRepository householdRepository, AddressRepository addressRepository) {
        this.householdRepository = householdRepository;
        this.addressRepository = addressRepository;
    }

    public Household saveAddress(AddressDto addressDto){
        Address address = Address.builder()
                .address_level1(addressDto.getAddress_level1())
                .address_level2(addressDto.getAddress_level2())
                .address_detail(addressDto.getAddress_detail())
                .zipcode(addressDto.getZipcode())
                .build();
        Household household = Household.builder()
                .address(address)
                .build();
        addressRepository.save(address);
        householdRepository.save(household);
        return household;
    }
}
