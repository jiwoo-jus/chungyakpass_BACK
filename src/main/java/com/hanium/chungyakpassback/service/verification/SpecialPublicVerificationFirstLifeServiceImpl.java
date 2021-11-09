package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoAmount;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.*;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoAmountRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRepository;
import com.hanium.chungyakpassback.repository.input.UserBankbookRepository;
import com.hanium.chungyakpassback.repository.standard.*;
import com.hanium.chungyakpassback.service.point.PointCalculationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpecialPublicVerificationFirstLifeServiceImpl implements SpecialPublicVerificationFirstLifeService {
    final GeneralKookminVerificationServiceImpl generalKookminVerificationServiceImpl;
    final UserBankbookRepository userBankbookRepository;
    final AptInfoAmountRepository aptInfoAmountRepository;
    final PrioritySubscriptionPeriodRepository prioritySubscriptionPeriodRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final IncomeRepository incomeRepository;
    final PointCalculationServiceImpl pointCalculationServiceImpl;
    final BankbookRepository bankbookRepository;
    final RelationRepository relationRepository;
    final HouseMemberRepository houseMemberRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean homelessYn(User user) {
        return generalKookminVerificationServiceImpl.meetHomelessHouseholdMembers(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean targetHouseAmount(AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        Optional<AptInfoAmount> supplyAmount = aptInfoAmountRepository.findByHousingType(aptInfoTarget.getHousingType());
        String targetSupplyAmount = supplyAmount.get().getSupplyAmount().replace(",", "");
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
        Integer averageMonthlyIncome6peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome6peopleLessBelow();
        Integer averageMonthlyIncome7peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome7peopleLessBelow();
        Integer averageMonthlyIncome8peopleLessBelow = monthlyAverageIncome.getAverageMonthlyIncome8peopleLessBelow();

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
        else if (numberOfHouseMember <= 6) {
            if (averageMonthlyIncome6peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        }
        else if (numberOfHouseMember <= 7) {
            if (averageMonthlyIncome7peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        }
        else if (numberOfHouseMember <= 8) {
            if (averageMonthlyIncome8peopleLessBelow >= houseMemberIncome) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean monthOfAverageIncomePriority(User user) {
        Integer houseMemberIncome = 0;
        List<HouseMemberRelation> houseMemberList = houseMemberRelationRepository.findAllByUser(user);
        Optional<Income> income = incomeRepository.findBySpecialSupplyAndSupplyAndAndHousingType(SpecialSupply.생애최초,Supply.우선공급,HousingType.국민);
        int numberOfHouseMember = houseMemberList.size();
        int houseCount=0;

        for (HouseMemberRelation houseMemberRelation : houseMemberList) {
            if (generalKookminVerificationServiceImpl.calcAmericanAge(houseMemberRelation.getOpponent().getBirthDay()) > 19) {
                if (pointCalculationServiceImpl.homelessYn(houseMemberRelation.getOpponent(),houseCount)) {
                    houseMemberIncome = houseMemberIncome + houseMemberRelation.getOpponent().getIncome();
                }
            }
        }
                if (meetYnOfAverageMonthlyIncome(income.get(), numberOfHouseMember, houseMemberIncome)) {
                    return true;
                }
                return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean monthOfAverageIncomeGeneral(User user) {
        Integer houseMemberIncome = 0;
        List<HouseMemberRelation> houseMemberList = houseMemberRelationRepository.findAllByUser(user);
        Optional<Income> income = incomeRepository.findBySpecialSupplyAndSupplyAndAndHousingType(SpecialSupply.생애최초,Supply.일반공급,HousingType.국민);
        int numberOfHouseMember = houseMemberList.size();
        int houseCount=0;

        for (HouseMemberRelation houseMemberRelation : houseMemberList) {
            if (generalKookminVerificationServiceImpl.calcAmericanAge(houseMemberRelation.getOpponent().getBirthDay()) > 19) {
                if (pointCalculationServiceImpl.homelessYn(houseMemberRelation.getOpponent(),houseCount)) {
                    houseMemberIncome = houseMemberIncome + houseMemberRelation.getOpponent().getIncome();
                }
            }
        }
            if (meetYnOfAverageMonthlyIncome(income.get(), numberOfHouseMember, houseMemberIncome)) {
                return true;
            }
            return false;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean validObject(User user, AptInfo aptInfo) {

        List<HouseMemberRelation> houseMemberRelationList1 = houseMemberRelationRepository.findAllByUser(user);
        List<Boolean> validHouseMemberList = new ArrayList<>();

        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList1) {
        validHouseMemberList.add(memberValidObject(user,aptInfo,houseMemberRelation));
        }
        if(validHouseMemberList.contains(Boolean.TRUE)){
            return true;
        }
        return false;

    }

    public Boolean memberValidObject(User user, AptInfo aptInfo, HouseMemberRelation houseMemberRelation) {
        com.hanium.chungyakpassback.entity.standard.Relation relation_child = relationRepository.findByRelation(Relation.자녀_일반).get();
        // houseMemberRelation 은 자녀_일반이다.
            if (user.getSpouseHouseMember() != null || (houseMemberRelation.getRelation().equals(relation_child)&&houseMemberRelation.getOpponent().getMarriageDate() == null)) {
                if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y)) {
                    if (generalKookminVerificationServiceImpl.meetAllHouseMemberNotWinningIn5years(user)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetDeposit(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        UserBankbook userBankbook = optUserBankbook.get();
        if (userBankbook.getDeposit() >= 6000000) {
            return true;
        }
        return false;
    }

}
