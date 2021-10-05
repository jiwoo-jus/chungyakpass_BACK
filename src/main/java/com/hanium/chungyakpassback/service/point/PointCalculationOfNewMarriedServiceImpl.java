package com.hanium.chungyakpassback.service.point;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.input.HouseMember;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.standard.AddressLevel1;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.Supply;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.input.*;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel2Repository;
import com.hanium.chungyakpassback.repository.standard.IncomeRepository;
import com.hanium.chungyakpassback.service.verification.GeneralPrivateVerificationService;
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
public class PointCalculationOfNewMarriedServiceImpl implements PointCalculationOfNewMarriedService {
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final PointCalculationServiceImpl pointCalculationServiceImpl;
    final GeneralPrivateVerificationService generalPrivateVerificationService;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final HouseMemberRepository houseMemberRepository;
    final IncomeRepository incomeRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final UserBankbookRepository userBankbookRepository;
    final AddressLevel2Repository addressLevel2Repository;
    final AddressLevel1Repository addressLevel1Repository;
    final UserRepository userRepository;
    List<LocalDate> minorsBirthDateList = new ArrayList<>();
    Integer NumberOfMinorsGetPoint = 0;
    Integer periodOfMarrigedGetPoint = 0;
    Integer paymentsCountGetPoint = 0;
    Integer periodOfResidenceGetPoint = 0;
    Integer houseMemberIncome = 0;
    Integer monthOfAverageIncomeGetPoint = 0;

    public Integer numberOfChild(User user, int standardAge){
        int Minors = 0;
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList) {
            if (houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반)&&houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_태아)) {
                    LocalDate childrenBirthDay = houseMemberRelation.getOpponent().getBirthDay();
                    if (generalPrivateVerificationServiceImpl.calcAmericanAge(childrenBirthDay) < standardAge) {
                        System.out.println("!!!!!!!!!1" + Minors);
                        Minors++;
                    }
            }
        }
        System.out.println("Minors"+Minors);
        return Minors;
    }


    @Override//미성년자 자녀 수
    public Integer numberOfMinors(User user) {
        Integer Minors = numberOfChild(user,19);
        for (int u = 1; u <= 3; u++) {
            if (Minors >= u) {
                NumberOfMinorsGetPoint = u;
            }
        }
        return NumberOfMinorsGetPoint;
    }

    @Override
    public Integer ageOfMostYoungChild(User user) {
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        System.out.println("!!!!!!1" + houseMemberRelationList);
        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList) {
            if (houseMemberRelation.getRelation().getRelation().equals(Relation.자녀_일반)){
                minorsBirthDateList.add(houseMemberRelation.getOpponent().getBirthDay());
            }
        }
        minorsBirthDateList.sort(Collections.reverseOrder());
        System.out.println("!!!!!!1" + minorsBirthDateList);
        int mostYoungChildAge = generalPrivateVerificationServiceImpl.calcAmericanAge(minorsBirthDateList.get(0));
        for (int u = 0; u <= 2; u++) {
            if (mostYoungChildAge < 3 + 2 * u) {
                mostYoungChildAge = 3 - u;
            }
        }
        return mostYoungChildAge;
    }

    @Override//혼인기간
    public Integer periodOfMarriged(User user) {
        int periodOfUserMarrigedYear = generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getMarriageDate());
        for (int u = 0; u <= 2; u++) {
            if (periodOfUserMarrigedYear <= 3 + u * 2) {
                return periodOfMarrigedGetPoint = 3 - u;
            }
        }
        return periodOfMarrigedGetPoint;
    }


    public Integer meetYnOfAverageMonthlyIncome(Income monthlyAverageIncome, Integer numberOfHouseMember) {
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


    @Transactional(rollbackFor = Exception.class)
    public Integer monthOfAverageIncome(User user) {
        List<HouseMember> houseMemberList = new ArrayList<>();
       List<Income> incomeList = incomeRepository.findAllBySupply(Supply.특별공급가점);
        houseMemberList.add(user.getHouseMember());
        int numberOfHouseMember = houseMemberList.size();
        for (HouseMember houseMember : houseMemberList) {
            if (generalPrivateVerificationServiceImpl.calcAmericanAge(houseMember.getBirthDay()) > 19) {
                if (pointCalculationServiceImpl.homelessYn(houseMember)) {
                    houseMemberIncome = houseMemberIncome + houseMember.getIncome();
                }
            }
        }

        if (user.getSpouseHouseMember() != null) {
            if (user.getSpouseHouseMember().getIncome() != null) {
                for (Income income : incomeList) {
                    if (income.getDualIncome().equals(Yn.y)) {
                        meetYnOfAverageMonthlyIncome(income, numberOfHouseMember);
                        return monthOfAverageIncomeGetPoint;
                    }
                }
            }
        }
        else {
            for (Income income : incomeList) {
                if (income.getDualIncome().equals(Yn.n)) {
                    meetYnOfAverageMonthlyIncome(income, numberOfHouseMember);
                    return monthOfAverageIncomeGetPoint;
                }
            }
        }
        return monthOfAverageIncomeGetPoint;
    }


    @Transactional(rollbackFor = Exception.class)//납입횟수
    public Integer bankbookPaymentsCount(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        int bankbookPaymentsCount = optUserBankbook.get().getPaymentsCount();
        if (bankbookPaymentsCount < 6) {
            return paymentsCountGetPoint;
        }
        for (int i = 1; i <= 2; i++) {
            if (bankbookPaymentsCount < i * 12) {
                return paymentsCountGetPoint = i;
            }
        }
        if (bankbookPaymentsCount >= 24) {
            return paymentsCountGetPoint = 3;
        }
        return paymentsCountGetPoint;
    }

    @Transactional(rollbackFor = Exception.class)//해당지역 거주기간
    public Integer periodOfApplicableAreaResidence(User user, AptInfo aptInfo) {
            AddressLevel1 userAddressLevel1 = Optional.ofNullable(user.getHouseMember().getHouse().getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
            AddressLevel1 aptAddressLevel1 = addressLevel1Repository.findByAddressLevel1(aptInfo.getAddressLevel1()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ADDRESS_LEVEL1));
                if (userAddressLevel1.getAddressLevel1() == aptAddressLevel1.getAddressLevel1()) {
            int periodOfResidence = generalPrivateVerificationServiceImpl.calcAmericanAge(user.getHouseMember().getTransferDate());
            if (periodOfResidence < 1) {
                return periodOfResidenceGetPoint = 1;
            } else if (periodOfResidence < 3) {
                return periodOfResidenceGetPoint = 2;
            } else if (periodOfResidence > 3) {
                return periodOfResidenceGetPoint = 3;
            }
        } else {
            return periodOfResidenceGetPoint;
        }
        return periodOfResidenceGetPoint;
    }


}






