package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoAmount;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoAmountRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.UserBankbookRepository;
import com.hanium.chungyakpassback.repository.standard.AddressLevel1Repository;
import com.hanium.chungyakpassback.repository.standard.IncomeRepository;
import com.hanium.chungyakpassback.repository.standard.PrioritySubscriptionPeriodRepository;
import com.hanium.chungyakpassback.service.point.PointCalculationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpecialPrivateVerificationFirstLifeServiceImpl implements SpecialPrivateVerificationFirstLifeService {
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final UserBankbookRepository userBankbookRepository;
    final AptInfoAmountRepository aptInfoAmountRepository;
    final PrioritySubscriptionPeriodRepository prioritySubscriptionPeriodRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final IncomeRepository incomeRepository;
    final PointCalculationServiceImpl pointCalculationServiceImpl;

    @Override
    public boolean HomelessYn(User user) {
        Integer houseCount = generalPrivateVerificationServiceImpl.getHouseMember(user);
        System.out.println("houseCount!!!" + houseCount);
        if (houseCount < 1)//houseCount가 2개 미만이면 true 아니면 false-0으로 할 수도 있음
            return true;
        else
            throw new CustomException(ErrorCode.BAD_REQUEST_HOMELESS);//무주택세대 구성원이 아니다.
    }

    @Override
    public boolean targetHousingType(AptInfoTarget aptInfoTarget) {
        int housingTypeChange = generalPrivateVerificationServiceImpl.houseTypeConverter(aptInfoTarget);
        if (housingTypeChange <= 85) {
            return true;
        }
        return false;
    }

    @Override
    public boolean targetHouseAmount(AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        Optional<AptInfoAmount> supplyAmount = aptInfoAmountRepository.findByHousingType(aptInfoTarget.getHousingType());
        String targetSupplyAmount = supplyAmount.get().getSupplyAmount().replace(",", "");
        System.out.println(targetSupplyAmount);
        Integer targetHousingTypeAmount = Integer.parseInt(targetSupplyAmount);
        if (targetHousingTypeAmount > 90000 && aptInfo.getSpeculationOverheated().equals(Yn.y)) {
            return false;
        }
        return true;
    }

    public boolean meetYnOfAverageMonthlyIncome(Income monthlyAverageIncome, Integer numberOfHouseMember, Integer houseMemberIncome) {
        Integer averageMonthlyIncome3peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome3peopleLessBelow();
        Integer averageMonthlyIncome4peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome4peopleLessBelow();
        Integer averageMonthlyIncome5peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome5peopleLessBelow();

        if (numberOfHouseMember <= 3) {
            if (averageMonthlyIncome3peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        } else if (numberOfHouseMember <= 4) {
            if (averageMonthlyIncome4peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        } else if (numberOfHouseMember <= 5) {
            if (averageMonthlyIncome5peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean monthOfAverageIncome(User user) {
        Integer houseMemberIncome = 0;
        List<HouseMemberRelation> houseMemberList = houseMemberRelationRepository.findAllByUser(user);
        List<Income> incomeList = incomeRepository.findAllBySpecialSupply(SpecialSupply.생애최초);
        int numberOfHouseMember = houseMemberList.size();

        for (HouseMemberRelation houseMemberRelation : houseMemberList) {
            if (generalPrivateVerificationServiceImpl.calcAmericanAge(houseMemberRelation.getOpponent().getBirthDay()) > 19) {
                if (pointCalculationServiceImpl.homelessYn(houseMemberRelation.getOpponent())) {
                    houseMemberIncome = houseMemberIncome + houseMemberRelation.getOpponent().getIncome();
                }
            }
        }
        for (int i = 0; i < incomeList.size(); i++) {
            if (meetYnOfAverageMonthlyIncome(incomeList.get(i), numberOfHouseMember, houseMemberIncome)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean vaildObject(User user, AptInfo aptInfo) {
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (int i = 0; i < houseMemberRelationList.size(); i++) {
            if (user.getSpouseHouseMember() != null || (houseMemberRelationList.get(i).getRelation().getRelation().equals(Relation.자녀_일반) && houseMemberRelationList.get(i).getOpponent().getMarriageDate() == null)) {
                if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y)) {
                    if (!generalPrivateVerificationServiceImpl.meetAllHouseMemberNotWinningIn5years(user)) {
                        return false;
                    }
                    return true;
                }
                return true;
            }
            return false;
        }
        return false;
    }

}
