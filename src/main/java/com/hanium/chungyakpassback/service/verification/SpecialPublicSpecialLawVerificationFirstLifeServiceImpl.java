package com.hanium.chungyakpassback.service.verification;

import com.hanium.chungyakpassback.entity.apt.AptInfo;
import com.hanium.chungyakpassback.entity.apt.AptInfoTarget;
import com.hanium.chungyakpassback.entity.input.HouseMemberProperty;
import com.hanium.chungyakpassback.entity.input.HouseMemberRelation;
import com.hanium.chungyakpassback.entity.input.User;
import com.hanium.chungyakpassback.entity.standard.Property;
import com.hanium.chungyakpassback.repository.apt.AptInfoAmountRepository;
import com.hanium.chungyakpassback.repository.input.HouseMemberPropertyRepository;
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
public class SpecialPublicSpecialLawVerificationFirstLifeServiceImpl implements SpecialPublicSpecialLawVerificationFirstLifeService {
    final GeneralKookminVerificationServiceImpl generalKookminVerificationServiceImpl;
    final UserBankbookRepository userBankbookRepository;
    final AptInfoAmountRepository aptInfoAmountRepository;
    final PrioritySubscriptionPeriodRepository prioritySubscriptionPeriodRepository;
    final AddressLevel1Repository addressLevel1Repository;
    final HouseMemberRelationRepository houseMemberRelationRepository;
    final HouseMemberPropertyRepository houseMemberPropertyRepository;
    final IncomeRepository incomeRepository;
    final PointCalculationServiceImpl pointCalculationServiceImpl;
    final BankbookRepository bankbookRepository;
    final SpecialPublicVerificationFirstLifeServiceImpl specialPublicVerificationFirstLifeServiceImpl;
    final PropertyRepository propertyRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean homelessYn(User user) {

        return specialPublicVerificationFirstLifeServiceImpl.homelessYn(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean targetHouseAmount(AptInfo aptInfo, AptInfoTarget aptInfoTarget) {
        return specialPublicVerificationFirstLifeServiceImpl.targetHouseAmount(aptInfo, aptInfoTarget);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean monthOfAverageIncome(User user) {
        return specialPublicVerificationFirstLifeServiceImpl.monthOfAverageIncome(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean vaildObject(User user, AptInfo aptInfo) {
        return specialPublicVerificationFirstLifeServiceImpl.vaildObject(user, aptInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetDeposit(User user) {
        return specialPublicVerificationFirstLifeServiceImpl.meetDeposit(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean meetStandardProperty(User user) {
        int buildTotalProperty = 0;
        int carTotalProperty = 0;
        List<HouseMemberProperty> allHouseMemberPropertyList = new ArrayList<>();
        List<HouseMemberRelation> houseMemberRelationList = houseMemberRelationRepository.findAllByUser(user);
        for (HouseMemberRelation memberRelation : houseMemberRelationList) {
            List<HouseMemberProperty> houseMemberPropertyList = houseMemberPropertyRepository.findAllByHouseMember(memberRelation.getOpponent());
            allHouseMemberPropertyList.addAll(houseMemberPropertyList);
        }
        for (HouseMemberProperty houseMemberProperty : allHouseMemberPropertyList) {
            Optional<Property> property = propertyRepository.findByProperty(houseMemberProperty.getProperty());
            if(houseMemberProperty.getProperty()==null){
                continue;
            }
            else if (houseMemberProperty.getProperty().equals(com.hanium.chungyakpassback.enumtype.Property.건물) || houseMemberProperty.getProperty().equals(com.hanium.chungyakpassback.enumtype.Property.토지)) {
                buildTotalProperty = buildTotalProperty + houseMemberProperty.getAmount();
                if (buildTotalProperty <= property.get().getPrice()) {
                    continue;
                }
                else{
                    return false;
                }
            }
            else if (houseMemberProperty.getProperty().equals(com.hanium.chungyakpassback.enumtype.Property.자동차)) {
                carTotalProperty = carTotalProperty + houseMemberProperty.getAmount();
                if (carTotalProperty <= property.get().getPrice()) {
                    continue;
                }
                else{
                    return false;
                }
            }
            return false;
        }
        return true;
    }
}

