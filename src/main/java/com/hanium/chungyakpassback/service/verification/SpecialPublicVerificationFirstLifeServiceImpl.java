package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoAmount;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.input.UserBankbook;
import com.hanium.chungyakpassback.entity.standard.Income;
import com.hanium.chungyakpassback.enumtype.ErrorCode;
import com.hanium.chungyakpassback.enumtype.Relation;
import com.hanium.chungyakpassback.enumtype.SpecialSupply;
import com.hanium.chungyakpassback.enumtype.Yn;
import com.hanium.chungyakpassback.handler.CustomException;
import com.hanium.chungyakpassback.repository.apt.AptInfoAmountRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberRelationRepository;
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
    final GeneralPrivateVerificationServiceImpl generalPrivateVerificationServiceImpl;
    final UserBankbookRepository userBankbookRepository;
    final AptInfoAmountRepository aptInfoAmountRepository;
    final PrioritySubscriptionPeriodRepository prioritySubscriptionPeriodRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final IncomeRepository incomeRepository;
    final PointCalculationServiceImpl pointCalculationServiceImpl;
    final BankbookRepository bankbookRepository;
    final RelationRepository relationRepository;

    @Transactional(rollbackFor = Exception.class)
    public boolean homelessYn(User user) {
        Integer houseCount = generalPrivateVerificationServiceImpl.getHouseMember(user);
        System.out.println("houseCount!!!" + houseCount);
        if (houseCount < 1)//houseCount가 1개 미만이면 true 아니면 false-0으로 할 수도 있음
            return true;
        else
            throw new CustomException(ErrorCode.BAD_REQUEST_HOMELESS);//무주택세대 구성원이 아니다.
    }

    @Transactional(rollbackFor = Exception.class)
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


    @Transactional(rollbackFor = Exception.class)
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

    @Transactional(rollbackFor = Exception.class)
    public boolean vaildObject(User user, AptInfo aptInfo) {

        List<HouseMemberRelation> houseMemberRelationList1 = houseMemberRelationRepository.findAllByUser(user);
        List<Boolean> vaildHouseMemberList = new ArrayList<>();

        for (HouseMemberRelation houseMemberRelation : houseMemberRelationList1) {
        vaildHouseMemberList.add(memberVaildObject(user,aptInfo,houseMemberRelation));
        }
        if(vaildHouseMemberList.contains(Boolean.TRUE)){
            return true;
        }
        return false;

    }

    public Boolean memberVaildObject(User user, AptInfo aptInfo, HouseMemberRelation houseMemberRelation) {
        com.hanium.chungyakpassback.entity.standard.Relation relation_child = relationRepository.findByRelation(Relation.자녀_일반).get();
        // houseMemberRelation 은 자녀_일반이다.
            if (user.getSpouseHouseMember() != null || (houseMemberRelation.getRelation().equals(relation_child)&&houseMemberRelation.getOpponent().getMarriageDate() == null)) {
                if (aptInfo.getSpeculationOverheated().equals(Yn.y) || aptInfo.getSubscriptionOverheated().equals(Yn.y)) {
                    if (generalPrivateVerificationServiceImpl.meetAllHouseMemberNotWinningIn5years(user)) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }



    @Transactional(rollbackFor = Exception.class)
    public boolean meetDeposit(User user) {
        Optional<UserBankbook> optUserBankbook = userBankbookRepository.findByUser(user);
        if (optUserBankbook.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_BANKBOOK);
        UserBankbook userBankbook = optUserBankbook.get();
        if (userBankbook.getDeposit() >= 6000000) {
            return true;
        }
        throw new CustomException(ErrorCode.BAD_REQUEST_LACK_BANKBOOK);
    }

}
