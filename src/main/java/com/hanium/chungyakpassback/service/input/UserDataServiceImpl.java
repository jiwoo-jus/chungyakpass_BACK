package com.hanium.chungyakpassback.service.input;

import com.hanium.chungyakpassback.dto.input.*;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.entity.standard.AddressLevel2;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.entity.input.*;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel2Repository;
import org.springframework.stereotype.Service;

@Service
public class UserDataServiceImpl implements UserDataService{
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;
    private final HouseMemberRepository houseMemberRepository;
    private final HouseMemberRelationRepository houseMemberRelationRepository;
    private final UserBankbookRepository userBankbookRepository;
    private final HouseMemberPropertyRepository houseMemberPropertyRepository;
    private final HouseMemberChungyakRepository houseMemberChungyakRepository;
    private final HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository;

    private final AddressLevel1Repository addressLevel1Repository;
    private final AddressLevel2Repository addressLevel2Repository;

    public UserDataServiceImpl(HouseRepository houseRepository, UserRepository userRepository, HouseMemberRepository houseMemberRepository, HouseMemberRelationRepository houseMemberRelationRepository, UserBankbookRepository userBankbookRepository, HouseMemberPropertyRepository houseMemberPropertyRepository, HouseMemberChungyakRepository houseMemberChungyakRepository, HouseMemberChungyakRestrictionRepository houseMemberChungyakRestrictionRepository, AddressLevel1Repository addressLevel1Repository, AddressLevel2Repository addressLevel2Repository) {
        this.houseRepository = houseRepository;
        this.userRepository = userRepository;
        this.houseMemberRepository = houseMemberRepository;
        this.houseMemberRelationRepository = houseMemberRelationRepository;
        this.userBankbookRepository = userBankbookRepository;
        this.houseMemberPropertyRepository = houseMemberPropertyRepository;
        this.houseMemberChungyakRepository = houseMemberChungyakRepository;
        this.houseMemberChungyakRestrictionRepository = houseMemberChungyakRestrictionRepository;

        this.addressLevel1Repository = addressLevel1Repository;
        this.addressLevel2Repository = addressLevel2Repository;
    }


    public UserBankbook userBankbook(User user, UserBankbookDto userBankbookDto){
        UserBankbook userBankbook = UserBankbook.builder()
                .user(user)
                .bank(userBankbookDto.getBank())
                .bankbook(userBankbookDto.getBankbook())
                .joinDate(userBankbookDto.getJoinDate())
                .deposit(userBankbookDto.getDeposit())
                .paymentsCount(userBankbookDto.getPaymentsCount())
                .validYn(userBankbookDto.getValidYn())
                .build();

        userBankbookRepository.save(userBankbook);
        return userBankbook;
    }

    public House house(User user, HouseDto houseDto){
        AddressLevel1 addressLevel1 = addressLevel1Repository.findByAddressLevel1(houseDto.getAddressLevel1());
        AddressLevel2 addressLevel2 = addressLevel2Repository.findByAddressLevel2(houseDto.getAddressLevel2());

        House house = House.builder()
                .addressLevel1(addressLevel1)
                .addressLevel2(addressLevel2)
                .addressDetail(houseDto.getAddressDetail())
                .zipcode(houseDto.getZipcode())
                .build();
        houseRepository.save(house);

        if (houseDto.getSpouseHouseYn().equals(Yn.y))
            user.setSpouseHouse(house);
        else
            user.setHouse(house);
        userRepository.save(user);

        return house;
    }

    public HouseMember houseMember(User user, HouseMemberDto houseMemberDto){
        House house = (houseMemberDto.getSpouseHouseYn().equals(Yn.y)) ? user.getSpouseHouse() : user.getHouse();

        HouseMember houseMember = HouseMember.builder()
                .house(house)
                .name(houseMemberDto.getName())
                .birthDate(houseMemberDto.getBirthDate())
                .foreignerYn(houseMemberDto.getForeignerYn())
                .soldierYn(houseMemberDto.getSoldierYn())
                .marriageDate(houseMemberDto.getMarriageDate())
                .homelessStartDate(houseMemberDto.getHomelessStartDate())
                .transferDate(houseMemberDto.getTransferDate())
                .income(houseMemberDto.getIncome())
                .build();
        houseMemberRepository.save(houseMember);

        houseMemberRelation(user, houseMember, houseMemberDto.getRelation());

        return houseMember;
    }

    public HouseMemberRelation houseMemberRelation(User user, HouseMember houseMember, Relation relation){
        HouseMemberRelation houseMemberRelation = HouseMemberRelation.builder()
                .user(user)
                .opponent(houseMember)
                .relation(relation)
                .build();
        houseMemberRelationRepository.save(houseMemberRelation);

        if ((relation.equals(Relation.본인)) || (relation.equals(Relation.배우자))){
            if (relation.equals(Relation.본인))
                user.setHouseMember(houseMember);
            else
                user.setSpouseHouseMember(houseMember);
            userRepository.save(user);
        }

        return  houseMemberRelation;
    }

    public HouseMemberProperty houseMemberProperty(HouseMemberPropertyDto houseMemberPropertyDto){
        HouseMember houseMember = houseMemberRepository.findById(houseMemberPropertyDto.getHouseMemberId()).get();

        HouseMemberProperty houseMemberProperty = HouseMemberProperty.builder()
                .houseMember(houseMember)
                .property(houseMemberPropertyDto.getProperty())
                .saleRightYn(houseMemberPropertyDto.getSaleRightYn())
                .residentialBuildingYn(houseMemberPropertyDto.getResidentialBuildingYn())
                .residentialBuilding(houseMemberPropertyDto.getResidentialBuilding())
                .nonResidentialBuilding(houseMemberPropertyDto.getNonResidentialBuilding())
                .acquisitionDate(houseMemberPropertyDto.getAcquisitionDate())
                .dispositionDate(houseMemberPropertyDto.getDispositionDate())
                .exclusiveArea(houseMemberPropertyDto.getExclusiveArea())
                .amount(houseMemberPropertyDto.getAmount())
                .taxBaseDate(houseMemberPropertyDto.getTaxBaseDate())
                .build();
        houseMemberPropertyRepository.save(houseMemberProperty);

        return houseMemberProperty;
    }

    public HouseMemberChungyak houseMemberChungyak(HouseMemberChungyakDto houseMemberChungyakDto){
        HouseMember houseMember = houseMemberRepository.findById(houseMemberChungyakDto.getHouseMemberId()).get();

        HouseMemberChungyak houseMemberChungyak = HouseMemberChungyak.builder()
                .houseMember(houseMember)
                .houseName(houseMemberChungyakDto.getHouseName())
                .supply(houseMemberChungyakDto.getSupply())
                .specialSupply(houseMemberChungyakDto.getSpecialSupply())
                .housingType(houseMemberChungyakDto.getHousingType())
                .ranking(houseMemberChungyakDto.getRanking())
                .result(houseMemberChungyakDto.getResult())
                .preliminaryNumber(houseMemberChungyakDto.getPreliminaryNumber())
                .winningDate(houseMemberChungyakDto.getWinningDate())
                .raffle(houseMemberChungyakDto.getRaffle())
                .cancelWinYn(houseMemberChungyakDto.getCancelWinYn())
                .build();
        houseMemberChungyakRepository.save(houseMemberChungyak);

        return houseMemberChungyak;
    }

    public HouseMemberChungyakRestriction houseMemberChungyakRestriction(HouseMemberChungyakRestrictionDto houseMemberChungyakRestrictionDto){
        HouseMemberChungyak houseMemberChungyak = houseMemberChungyakRepository.findById(houseMemberChungyakRestrictionDto.getHouseMemberChungyakId()).get();

        HouseMemberChungyakRestriction houseMemberChungyakRestriction = HouseMemberChungyakRestriction.builder()
                .houseMemberChungyak(houseMemberChungyak)
                .reWinningRestrictedDate(houseMemberChungyakRestrictionDto.getReWinningRestrictedDate())
                .specialSupplyRestrictedYn(houseMemberChungyakRestrictionDto.getSpecialSupplyRestrictedYn())
                .unqualifiedSubscriberRestrictedDate(houseMemberChungyakRestrictionDto.getUnqualifiedSubscriberRestrictedDate())
                .regulatedAreaFirstPriorityRestrictedDate(houseMemberChungyakRestrictionDto.getRegulatedAreaFirstPriorityRestrictedDate())
                .additionalPointSystemRestrictedDate(houseMemberChungyakRestrictionDto.getAdditionalPointSystemRestrictedDate())
                .build();
        houseMemberChungyakRestrictionRepository.save(houseMemberChungyakRestriction);

        return houseMemberChungyakRestriction;
    }

    public HouseHolderDto houseHolder(User user, HouseHolderDto houseHolderDto){
        HouseMember houseMember = houseMemberRepository.findById(houseHolderDto.getHouseMemberId()).get();
        House house = (houseHolderDto.getSpouseHouseYn().equals(Yn.y)) ? user.getSpouseHouseMember().getHouse() : user.getHouseMember().getHouse();

        house.setHouseHolder(houseMember);
        houseRepository.save(house);

        return houseHolderDto;
    }
}
