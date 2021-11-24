package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongNewlyMarriedDto;
import com.hanium.chungyakpassback.dto.point.PointOfSpecialMinyeongNewlyMarriedResponseDto;
import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.point.PointOfSpecialMinyeongNewlyMarried;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoRepository;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.point.PointOfSpecialMinyeongNewlyMarriedRepository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel2Repository;
import com.hanium.chungyakpassback.repository.standard.IncomeRepository;
import com.hanium.chungyakpassback.service.verification.VerificationOfGeneralMinyeongService;
import com.hanium.chungyakpassback.service.verification.VerificationOfGeneralMinyeongServiceImpl;
import com.hanium.chungyakpassback.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PointOfNewlyMarriedServiceImpl implements PointOfNewlyMarriedService {
    final VerificationOfGeneralMinyeongServiceImpl generalPrivateVerificationServiceImpl;
    final PointOfMinyeongServiceImpl pointOfServiceImpl;
    final VerificationOfGeneralMinyeongService verificationOfGeneralMinyeongService;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final HouseMemberRepository houseMemberRepository;
    final IncomeRepository incomeRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final UserBankbookRepository userBankbookRepository;
    final AddressLevel2Repository addressLevel2Repository;
    final AddressLevel1Repository addressLevel1Repository;
    final UserRepository userRepository;
    final PointOfSpecialMinyeongNewlyMarriedRepository pointOfSpecialMinyeongNewlyMarriedRepository;
    final AptInfoRepository aptInfoRepository;

    @Override
    public List<PointOfSpecialMinyeongNewlyMarriedResponseDto> readNewlyMarriedPointCalculations() {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();

        List<PointOfSpecialMinyeongNewlyMarriedResponseDto> pointOfSpecialMinyeongNewlyMarriedResponseDtos = new ArrayList<>();
        for (PointOfSpecialMinyeongNewlyMarried pointOfSpecialMinyeongNewlyMarried : pointOfSpecialMinyeongNewlyMarriedRepository.findAllByUser(user)) {
            PointOfSpecialMinyeongNewlyMarriedResponseDto pointOfSpecialMinyeongNewlyMarriedResponseDto = new PointOfSpecialMinyeongNewlyMarriedResponseDto(pointOfSpecialMinyeongNewlyMarried);
            pointOfSpecialMinyeongNewlyMarriedResponseDtos.add(pointOfSpecialMinyeongNewlyMarriedResponseDto);
        }

        return pointOfSpecialMinyeongNewlyMarriedResponseDtos;
    }

    @Override
    public PointOfSpecialMinyeongNewlyMarriedResponseDto createNewlyMarriedPointCalculation(PointOfSpecialMinyeongNewlyMarriedDto pointOfSpecialMinyeongNewlyMarriedDto) {
        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
        AptInfo aptInfo = aptInfoRepository.findById(pointOfSpecialMinyeongNewlyMarriedDto.getNotificationNumber()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_APT));
        Integer numberOfMinors = numberOfMinors(user);
        Integer periodOfMarriged = periodOfMarriged(user);
        Integer bankbookPaymentsCount = bankbookPaymentsCount(user);
        Integer periodOfApplicableAreaResidence = periodOfApplicableAreaResidence(user, aptInfo);
        Integer monthOfAverageIncome = monthOfAverageIncome(user);
        Integer total = numberOfMinors + periodOfMarriged + bankbookPaymentsCount + periodOfApplicableAreaResidence + monthOfAverageIncome;
        PointOfSpecialMinyeongNewlyMarried pointOfSpecialMinyeongNewlyMarried = new PointOfSpecialMinyeongNewlyMarried(user, aptInfo, numberOfMinors, periodOfMarriged, bankbookPaymentsCount, periodOfApplicableAreaResidence, monthOfAverageIncome, total);
        pointOfSpecialMinyeongNewlyMarriedRepository.save(pointOfSpecialMinyeongNewlyMarried);

        return new PointOfSpecialMinyeongNewlyMarriedResponseDto(pointOfSpecialMinyeongNewlyMarried);
    }


    public Integer numberOfChild(User user, int standardAge) {
        int Minors = 0;
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList) {
            if (houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반) || houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_태아)) {
                LocalDate childrenBirthDay = houseMemberRelation.getOpponent().getBirthDay();
                if (generalPrivateVerificationServiceImpl.calcAmericanAge(childrenBirthDay) < standardAge) {
                    Minors++;
                }
            }
        }
        return Minors;
    }


    @Override//미성년자 자녀 수
    @Transactional(rollbackFor = Exception.class)
    public Integer numberOfMinors(User user) {
        Integer NumberOfMinorsGetPoint = 0;

        Integer Minors = numberOfChild(user, 19);
        for (int u = 1; u <= 3; u++) {
            if (Minors >= u) {
                NumberOfMinorsGetPoint = u;
            }
        }
        return NumberOfMinorsGetPoint;
    }

    public int yearOfMerriged(LocalDate marrigedate) {
        if (marrigedate == null) {
            throw new CustomException(ErrorCode.NOT_FOUND_MARRIAGES); //생일이 입력되지 않은 경우 경고문을 띄워줌.
        }

        LocalDate now = LocalDate.now();
        int americanAge = now.minusYears(marrigedate.getYear()).getYear();

        if (marrigedate.plusYears(americanAge).isAfter(now)) // 생일이 지났는지 여부를 판단
            americanAge = americanAge - 1;

        return americanAge;
    }


    @Override//혼인기간
    @Transactional(rollbackFor = Exception.class)
    public Integer periodOfMarriged(User user) {
        Integer periodOfMarrigedGetPoint = 0;
        int periodOfUserMarrigedYear = yearOfMerriged(user.getHouseMember().getMarriageDate());
        for (int u = 0; u <= 2; u++) {
            if (periodOfUserMarrigedYear <= 3 + u * 2) {
                periodOfMarrigedGetPoint = 3 - u;
            }
        }
        return periodOfMarrigedGetPoint;
    }


    public Integer meetYnOfAverageMonthlyIncome(Income monthlyAverageIncome, Integer numberOfHouseMember, Integer houseMemberIncome, Integer monthOfAverageIncomeGetPoint) {
        Integer averageMonthlyIncome3peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome3peopleLessBelow();
        Integer averageMonthlyIncome4peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome4peopleLessBelow();
        Integer averageMonthlyIncome5peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome5peopleLessBelow();

        if (numberOfHouseMember <= 3) {
            if (averageMonthlyIncome3peopleLessBelow >= houseMemberIncome) {
                return monthOfAverageIncomeGetPoint = 1;
            }
        } else if (numberOfHouseMember <= 4) {
            if (averageMonthlyIncome4peopleLessBelow >= houseMemberIncome) {
                return monthOfAverageIncomeGetPoint = 1;
            }
        } else if (numberOfHouseMember <= 5) {
            if (averageMonthlyIncome5peopleLessBelow >= houseMemberIncome) {
                return monthOfAverageIncomeGetPoint = 1;
            }
        }
        return monthOfAverageIncomeGetPoint;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer monthOfAverageIncome(User user) {
        Integer houseMemberIncome = 0;
        Integer monthOfAverageIncomeGetPoint = 0;
        List<HouseMember> houseMemberList = new ArrayList<>();
        List<Income> incomeList = incomeRepository.findAllBySupply(Supply.특별공급가점);
        houseMemberList.add(user.getHouseMember());
        int numberOfHouseMember = houseMemberList.size();
        int houseCount = 0;
        for (HouseMember houseMember : houseMemberList) {
            if (generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) > 19) {
                if (pointOfServiceImpl.homelessYn(houseMember, houseCount)) {
                    houseMemberIncome = houseMemberIncome + houseMember.getIncome();
                }
            }
        }

        if (user.getSpouseHouseMember() != null) {
            if (user.getSpouseHouseMember().getIncome() != null) {
                for (Income income : incomeList) {
                    if (income.getDualIncome().equals(Yn.y)) {
                        return monthOfAverageIncomeGetPoint = meetYnOfAverageMonthlyIncome(income, numberOfHouseMember, houseMemberIncome, monthOfAverageIncomeGetPoint);
                    }
                }
            }
        } else {
            for (Income income : incomeList) {
                if (income.getDualIncome().equals(Yn.n)) {
                    return monthOfAverageIncomeGetPoint = meetYnOfAverageMonthlyIncome(income, numberOfHouseMember, houseMemberIncome, monthOfAverageIncomeGetPoint);
                }
            }
        }
        return monthOfAverageIncomeGetPoint;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//납입횟수
    public Integer bankbookPaymentsCount(User user) {
        Integer bankbookPaymentsCount = 0;
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);

        int bankbookPayments = optUserBankbook.get().getPaymentsCount();
        if (bankbookPayments < 6) {
            return bankbookPaymentsCount;
        }
        for (int i = 1; i <= 2; i++) {
            if (bankbookPayments < i * 12) {
                return bankbookPaymentsCount = i;
            }
        }
        if (bankbookPayments >= 24) {
            return bankbookPaymentsCount = 3;
        }


//        List<SpecialMinyeongPointOfNewMarriedResponseDto> specialMinyeongPointOfNewMarriedResponseDtos = new ArrayList<>();
//
//        for(RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried : recordSpecialMinyeongPointOfNewMarriedRepository.findAllByUser(user)){
//            SpecialMinyeongPointOfNewMarriedResponseDto specialMinyeongPointOfNewMarriedResponseDto = new SpecialMinyeongPointOfNewMarriedResponseDto(recordSpecialMinyeongPointOfNewMarried);
//            specialMinyeongPointOfNewMarriedResponseDtos.add(specialMinyeongPointOfNewMarriedResponseDto);
//        }
//
//        for (SpecialMinyeongPointOfNewMarriedResponseDto specialMinyeongPointOfNewMarriedResponseDto : specialMinyeongPointOfNewMarriedResponseDtos) {
//            RecordSpecialMinyeongPointOfNewMarried recordSpecialMinyeongPointOfNewMarried = RecordSpecialMinyeongPointOfNewMarried.builder()
//                    .user(user)
//                    .bankbookPaymentsCount(specialMinyeongPointOfNewMarriedResponseDto.getBankbookPaymentsCount())
//                    .build();
//            recordSpecialMinyeongPointOfNewMarriedRepository.save(recordSpecialMinyeongPointOfNewMarried);
//
//        }

        return bankbookPaymentsCount;
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public UserBankbookResponseDto userBankbook(UserBankbookDto userBankbookDto){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        return new UserBankbookResponseDto(userBankbookRepository.save(userBankbookDto.toEntity(user)));
//    }


//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public List<UserBankbookResponseDto> readUserBankbooks(){
//        User user = userRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentEmail().get()).get();
//        List<UserBankbookResponseDto> userBankbookResponseDtos = new ArrayList<>();
//
//        for(UserBankbook userBankbook : userBankbookRepository.findAllByUser(user)){
//            UserBankbookResponseDto userBankbookResponseDto = new UserBankbookResponseDto(userBankbook);
//            userBankbookResponseDtos.add(userBankbookResponseDto);
//        }
//        return userBankbookResponseDtos;
//    }


    public int periodOfYear(LocalDate joinDate) {
        LocalDate now = LocalDate.now();
        int periodOfYear = now.minusYears(joinDate.getYear()).getYear();

        if (joinDate.plusYears(periodOfYear).isAfter(now)) // 생일이 지났는지 여부를 판단
            periodOfYear = periodOfYear - 1;
        return periodOfYear;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//해당지역 거주기간
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) {
        Integer periodOfResidenceGetPoint = 0;
        AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
        if (userAddressLevel1.getAddressLevel1() == aptAddressLevel1.getAddressLevel1()) {
            int periodOfResidence = periodOfYear(user.getHouseMember().getTransferDate());
            if (periodOfResidence < 1) {
                return periodOfResidenceGetPoint = 1;
            } else if (periodOfResidence < 3) {
                return periodOfResidenceGetPoint = 2;
            } else if (periodOfResidence > 3) {
                return periodOfResidenceGetPoint = 3;
            }
        } else {
            periodOfResidenceGetPoint = 0;
        }
        return periodOfResidenceGetPoint;
    }


}






