package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.SpecialPointOfMultiChildDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRepository;
import com.hanium.chungyakpassback.repository.input.UserBankbookRepository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointCalculationOfMultiChildServiceImpl implements PointCalculationOfMultiChildService {
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final UserBankbookRepository userBankbookRepository;
    final HouseMemberRepository houseMemberRepository;
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final PointCalculationOfNewMarriedServiceImpl pointCalculationOfNewMarriedServiceImpl;
    final AddressLevel1Repository addressLevel1Repository;
    List<LocalDate> lateDateList = new ArrayList<>();//배우자와 본인중 무주택시점이 늦은날을 저장하는 리스트
    Integer NumberOfChildUnder6YearGetPoint = 0;
    Integer NumberOfChildGetPoint = 0;
    Integer bankbookJoinPeriodGetPoint = 0;
    Integer periodOfResidenceGetPoint = 0;
    Integer generationCompositionGetPoint = 0;
    LocalDate lateDate;
    Integer periodOfHomelessnessGetPoint = 0;


    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfChild(User user) {
        Integer Minors = pointCalculationOfNewMarriedServiceImpl.numberOfChild(user, 19);
        for (int u = 0; u <= 2; u++) {
            if (Minors >= u + 3) {
                NumberOfChildGetPoint = u * 5 + 30;
            }
        }
        return NumberOfChildGetPoint;
    }


    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfChildUnder6Year(User user) {
        Integer Minors = pointCalculationOfNewMarriedServiceImpl.numberOfChild(user, 6);
        for (int u = 1; u <= 3; u++) {
            if (Minors >= u) {
                NumberOfChildUnder6YearGetPoint = u * 5;
            }
        }
        return NumberOfChildUnder6YearGetPoint;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer bankbookJoinPeriod(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        int joinPeriodOfYear = generalPrivateVerificationServiceImpl.calcAmericanAge(optUserBankbook.get().getJoinDate());
        if (joinPeriodOfYear >= 10) {
            return bankbookJoinPeriodGetPoint = 5;
        }
        return bankbookJoinPeriodGetPoint;
    }

    public Integer periodOfApplicableAreaResidenceGetPoint(User user) {
        int periodOfResidence = generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getTransferDate());
        if (1 <= periodOfResidence && periodOfResidence < 5) {
            return periodOfResidenceGetPoint = 5;
        } else if (5 <= periodOfResidence && periodOfResidence < 10) {
            return periodOfResidenceGetPoint = 10;
        } else if (periodOfResidence >= 10) {
            return periodOfResidenceGetPoint = 15;
        }
        return periodOfResidenceGetPoint;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) {
        AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        if (userAddressLevel1.getNearbyArea() == 1) {
            if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea()) {
                return periodOfResidenceGetPoint = periodOfApplicableAreaResidenceGetPoint(user);
            }
        } else {
            if (userAddressLevel1.getAddressLevel1() == aptAddressLevel1.getAddressLevel1()) {
                return periodOfResidenceGetPoint = periodOfApplicableAreaResidenceGetPoint(user);
            }
        }
        return periodOfResidenceGetPoint;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer generationComposition(SpecialPointOfMultiChildDto specialPointOfMultiChildDto) {
        if (specialPointOfMultiChildDto.getMultiChildHouseholdType().equals(MultiChildHouseholdType.한부모가족)||specialPointOfMultiChildDto.getMultiChildHouseholdType().equals(MultiChildHouseholdType.삼세대이상)) {
                return generationCompositionGetPoint = 5;
        }
        return generationCompositionGetPoint;
    }


    public List periodHomeless(HouseMember houseMember) {
        LocalDate birthDayAfter19Year = houseMember.getBirthDay().plusYears(19);
        if ((generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) < 19)) {
            if (houseMember.getMarriageDate() != null) {//만 19세 미만 결혼시 혼인년도 와 무주택 기간중 늦은 날
                if (houseMember.getMarriageDate().isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = houseMember.getMarriageDate();
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
        } else {//만 19세 이상 결혼 시 19세 생일 년도와 무주택 기간중 늦은 날
            if (birthDayAfter19Year.isAfter(houseMember.getHomelessStartDate())) {
                lateDate = birthDayAfter19Year;
            } else {
                lateDate = houseMember.getHomelessStartDate();
            }
        }
        lateDateList.add(lateDate);


        return lateDateList;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfHomelessness(User user) {
        int houseCount = 0;
        if (generalPrivateVerificationServiceImpl.getHouseMember(user) != 0) {
                throw new CustomException(ErrorCode.BAD_REQUEST_HOMELESS);
        } else {
            if (user.getSpouseHouseMember() != null) {
                periodHomeless(user.getHouseMember());
                periodHomeless(user.getSpouseHouseMember());
                //반환값을 가지고 늦은 순서대로 정렬
            } else {
                periodHomeless(user.getHouseMember());
            }
            lateDateList.sort(Collections.reverseOrder());
            LocalDate mostLateDate = lateDateList.get(0);
            //무주택기간을 기간으로 계산함
            int periodOfHomelessness = generalPrivateVerificationServiceImpl.calcAmericanAge(mostLateDate);

            if (1 <= periodOfHomelessness && periodOfHomelessness < 5) {
                return periodOfHomelessnessGetPoint = 5;
            } else if (5 <= periodOfHomelessness && periodOfHomelessness < 10) {
                return periodOfHomelessnessGetPoint = 10;
            } else if (periodOfHomelessness >= 10) {
                return periodOfHomelessnessGetPoint = 15;
            }
            return periodOfHomelessnessGetPoint;
        }
    }

}

