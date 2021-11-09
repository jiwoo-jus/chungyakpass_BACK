package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.SpecialPointOfMultiChildDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.MultiChildHouseholdType;
import com.hanium.chungyakpassback.enumtype.Yn;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfChild(User user) {
        Integer NumberOfChildGetPoint = 0;
        Integer Minors = pointCalculationOfNewMarriedServiceImpl.numberOfChild(user, 19);
        for (int u = 0; u <= 2; u++) {
            if (Minors >= u + 3) {
                  NumberOfChildGetPoint = u * 5 + 30;
            }
        }
        return NumberOfChildGetPoint;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfChildUnder6Year(User user) {
        Integer NumberOfChildUnder6YearGetPoint = 0;
        Integer Minors = pointCalculationOfNewMarriedServiceImpl.numberOfChild(user, 6);
        for (int u = 1; u <= 3; u++) {
            if (Minors >= u) {
                  NumberOfChildUnder6YearGetPoint = u * 5;
            }
        }
        return NumberOfChildUnder6YearGetPoint;
    }

    public int periodOfYear(LocalDate joinDate) {
        LocalDate now = LocalDate.now();
        int periodOfYear = now.minusYears(joinDate.getYear()).getYear();

        if (joinDate.plusYears(periodOfYear).isAfter(now)) // 생일이 지났는지 여부를 판단
            periodOfYear = periodOfYear - 1;
        return periodOfYear;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer bankbookJoinPeriod(User user) {
        Integer bankbookJoinPeriodGetPoint = 0;
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        int joinPeriodOfYear = periodOfYear(optUserBankbook.get().getJoinDate());
        if (joinPeriodOfYear >= 10) {
             bankbookJoinPeriodGetPoint = 5;
        }
        return bankbookJoinPeriodGetPoint;
    }

    public Integer periodOfApplicableAreaResidenceGetPoint(User user,Integer periodOfResidenceGetPoint) {
        int periodOfResidence = periodOfYear(user.getHouseMember().getTransferDate());
        if (1 <= periodOfResidence && periodOfResidence < 5) {
            return periodOfResidenceGetPoint = 5;
        } else if (5 <= periodOfResidence && periodOfResidence < 10) {
            return periodOfResidenceGetPoint = 10;
        } else if (periodOfResidence >= 10) {
            return periodOfResidenceGetPoint = 15;
        }
        return periodOfResidenceGetPoint;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) {
        Integer periodOfResidenceGetPoint = 0;
        AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        if (userAddressLevel1.getNearbyArea() == 1) {
            if (userAddressLevel1.getNearbyArea() == aptAddressLevel1.getNearbyArea()) {
                return periodOfResidenceGetPoint = periodOfApplicableAreaResidenceGetPoint(user, periodOfResidenceGetPoint);
            }
        } else {
            if (userAddressLevel1.getAddressLevel1() == aptAddressLevel1.getAddressLevel1()) {
                return periodOfResidenceGetPoint = periodOfApplicableAreaResidenceGetPoint(user,periodOfResidenceGetPoint);
            }
        }
        return periodOfResidenceGetPoint;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer generationComposition(SpecialPointOfMultiChildDto specialPointOfMultiChildDto) {
        Integer generationCompositionGetPoint = 0;
        if (specialPointOfMultiChildDto.getMultiChildHouseholdType().equals(MultiChildHouseholdType.한부모가족)||specialPointOfMultiChildDto.getMultiChildHouseholdType().equals(MultiChildHouseholdType.삼세대이상)) {
             generationCompositionGetPoint = 5;
        }
        return generationCompositionGetPoint;
    }


    public List periodHomeless(HouseMember houseMember,LocalDate lateDate,List lateDateList) {
        LocalDate birthDayAfter19Year = houseMember.getBirthDay().plusYears(19);
        if (birthDayAfter19Year.isAfter(houseMember.getMarriageDate())){
            if (houseMember.getMarriageDate().isAfter(houseMember.getHomelessStartDate())) {
                    lateDate = houseMember.getMarriageDate();
                } else {
                    lateDate = houseMember.getHomelessStartDate();
                }
            }
        else if(houseMember.getMarriageDate()==null|| birthDayAfter19Year.isBefore(houseMember.getMarriageDate())) {//만 19세 이상 결혼 시 19세 생일 년도와 무주택 기간중 늦은 날
            if (birthDayAfter19Year.isAfter(houseMember.getHomelessStartDate())) {
                lateDate = birthDayAfter19Year;
            }
            else {
                lateDate = houseMember.getHomelessStartDate();
            }
        }
        lateDateList.add(lateDate);

        for(int i=0;i<lateDateList.size();i++) {
            System.out.println("lateDateList"+lateDateList.get(i));
        }
        return lateDateList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfHomelessness(User user) {
        int houseCount = 0;
        LocalDate lateDate = null;
        Integer periodOfHomelessnessGetPoint = 0;
        List<LocalDate> lateDateList = new ArrayList<>();//배우자와 본인중 무주택시점이 늦은날을 저장하는 리스트

        if ((generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getBirthDay()) < 30 && user.getSpouseHouseMember() == null) || user.getHouseMember().getForeignerYn().equals(Yn.y)) {//만30세미만 미혼이거나 무주택시작일이 없으면 0점
            return periodOfHomelessnessGetPoint =0;
        }
        //배우자가 있는데 혼인신고일이 null일경우 에러 호출
        else {
            if (user.getSpouseHouseMember() != null && user.getSpouseHouseMember().getMarriageDate() == null) {
                throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES);
            }
            //배우자가 null 이거나 신청자의 배우자의 무주택 시작일이 혼인신고일 전이면 신청자의 무주택기간만 산정
            else if (user.getSpouseHouseMember() == null || (user.getSpouseHouseMember().getMarriageDate().isAfter(user.getSpouseHouseMember().getHomelessStartDate()))) {
                periodHomeless(user.getHouseMember(),lateDate,lateDateList);
            }
            //아니면 신청자와 배우자의 무주택기간 산정
            else
            {
                periodHomeless(user.getHouseMember(),lateDate,lateDateList);
                periodHomeless(user.getSpouseHouseMember(),lateDate,lateDateList);
            }
        }

        lateDateList.sort(Collections.reverseOrder());
        LocalDate mostLateDate = lateDateList.get(0);
        //무주택기간을 기간으로 계산함
        int periodOfHomelessness = periodOfYear(mostLateDate);

        if (1 <= periodOfHomelessness && periodOfHomelessness < 5) {
            return periodOfHomelessnessGetPoint = 10;
        } else if (5 <= periodOfHomelessness && periodOfHomelessness < 10) {
            return periodOfHomelessnessGetPoint = 15;
        } else if (periodOfHomelessness >= 10) {
            return periodOfHomelessnessGetPoint = 20;
        }
        return periodOfHomelessnessGetPoint;
    }
}

