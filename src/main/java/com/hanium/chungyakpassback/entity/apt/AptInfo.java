package com.hanium.chungyakpassback.entity.apt;

import com.hanium.chungyakpassback.entity.enumtype.AddressLevel1;
import com.hanium.chungyakpassback.entity.enumtype.AddressLevel2;
import com.hanium.chungyakpassback.entity.enumtype.HousingType;
import com.hanium.chungyakpassback.entity.enumtype.Yn;
import com.hanium.chungyakpassback.service.YearAttributeConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.YearMonth;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
@Table(name = "std_aptinfo")
public class AptInfo {
    @Id
    @Column(name = "notification_number_id")
    private Integer notificationNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel1 addressLevel1;

    @Column
    @Enumerated(EnumType.STRING)
    private AddressLevel2 addressLevel2;

    @Column
    private String detailAddress;

    @Column
    @Enumerated(EnumType.STRING)
    private HousingType housingType;

    @Column
    @Enumerated(EnumType.STRING)
    private Yn specialActPublicHousing;//공공주택특별법적용

    @Column
    private String houseName;//주택명

    @Column
    private String constructionCompany;//건설업체

    @Column
    private LocalDate announcementDate;//모집공고일

    @Column
    private LocalDate winnerAnnouncementDate;//당첨자발표일

    @Column
    private LocalDate contractStartDate;//계약시작일

    @Column
    private LocalDate contractEndDate;//계약종료일

    @Convert(converter = YearAttributeConverter.class)
    @Column
    private YearMonth scheduledOccupancy;//입주예정월

    @Column
    @Enumerated(EnumType.STRING)
    private Yn speculationOverheated;//투기과열지구

    @Column
    @Enumerated(EnumType.STRING)
    private Yn subscriptionOverheated;//청약과열지역

    @Column
    @Enumerated(EnumType.STRING)
    private Yn atrophyArea;//위축지역

    @Column
    @Enumerated(EnumType.STRING)
    private Yn salePriceLimit;//분양가상한제

    @Column
    @Enumerated(EnumType.STRING)
    private Yn maintenanceWork;//정비사업

    @Column
    @Enumerated(EnumType.STRING)
    private Yn publicHosingDistrict;//공공주택지구

    @Column
    @Enumerated(EnumType.STRING)
    private Yn publicRentalHousing;//공공건설임대주택

    @Column
    @Enumerated(EnumType.STRING)
    private Yn largeDevelopmentzone;//대규모택지개발지구

    @Column
    @Enumerated(EnumType.STRING)
    private Yn privateInMetropolitan;//수도권내민영공공주택지구

    @Builder
    public AptInfo(Integer notificationNumber,AddressLevel1 addressLevel1, AddressLevel2 addressLevel2, String detailAddress, HousingType housingType, Yn specialActPublicHousing, String houseName, String constructionCompany, LocalDate announcementDate, LocalDate winnerAnnouncementDate, LocalDate contractStartDate, LocalDate contractEndDate, YearMonth scheduledOccupancy, Yn speculationOverheated, Yn subscriptionOverheated, Yn atrophyArea, Yn salePriceLimit, Yn maintenanceWork, Yn publicHosingDistrict, Yn publicRentalHousing, Yn largeDevelopmentzone,  Yn privateInMetropolitan) {
        this.notificationNumber = notificationNumber;
        this.addressLevel1 = addressLevel1;
        this.addressLevel2 = addressLevel2;
        this.detailAddress = detailAddress;
        this.housingType = housingType;
        this.specialActPublicHousing = specialActPublicHousing;
        this.houseName = houseName;
        this.constructionCompany = constructionCompany;
        this.announcementDate = announcementDate;
        this.winnerAnnouncementDate = winnerAnnouncementDate;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.scheduledOccupancy = scheduledOccupancy;
        this.speculationOverheated = speculationOverheated;
        this.subscriptionOverheated = subscriptionOverheated;
        this.atrophyArea = atrophyArea;
        this.salePriceLimit = salePriceLimit;
        this.maintenanceWork = maintenanceWork;
        this.publicHosingDistrict = publicHosingDistrict;
        this.publicRentalHousing = publicRentalHousing;
        this.largeDevelopmentzone = largeDevelopmentzone;
        this.privateInMetropolitan = privateInMetropolitan;
    }
}