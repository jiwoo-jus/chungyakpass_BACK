package com.hanium.chungyakpassback.entity.apt;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "std_aptinfo_receipt")
public class AptInfoReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aptinfo_receipt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_number_id")
    private com.hanium.chungyakpassback.entity.apt.AptInfo AptInfo;

    @Column
    private LocalDate specialReceptionStartDate;//특별공급접수시작일

    @Column
    private LocalDate specialReceptionEndDate;//특별공급접수종료일

    @Column
    private LocalDate priorityApplicableAreaStart;//일순위접수일해당지역;

    @Column
    private LocalDate priorityApplicableAreaEnd;//일순위접수일해당지역;

    @Column
    private LocalDate priorityGyeonggiAreaStart;//일순위접수일경기지역

    @Column
    private LocalDate priorityGyeonggiAreaEnd;//일순위접수일경기지역

    @Column
    private LocalDate priorityOtherAreaStart;//일순위접수일기타지역

    @Column
    private LocalDate priorityOtherAreaEnd;//일순위접수일기타지역

    @Column
    private LocalDate secondApplicableAreaStart;//이순위접수일해당지역

    @Column
    private LocalDate secondApplicableAreaEnd;//이순위접수일해당지역

    @Column
    private LocalDate secondGyeonggiAreaStart;//이순위접수일경기지역

    @Column
    private LocalDate secondGyeonggiAreaEnd;//이순위접수일경기지역

    @Column
    private LocalDate secondOtherAreaStart;//이순위접수일기타지역

    @Column
    private LocalDate secondOtherAreaEnd;//이순위접수일기타지역

    @Column
    private String homepage;//홈페이지

    @Builder
    public AptInfoReceipt(com.hanium.chungyakpassback.entity.apt.AptInfo aptInfo, LocalDate specialReceptionStartDate, LocalDate specialReceptionEndDate, LocalDate priorityApplicableAreaStart, LocalDate priorityApplicableAreaEnd, LocalDate priorityGyeonggiAreaStart, LocalDate priorityGyeonggiAreaEnd, LocalDate priorityOtherAreaStart, LocalDate priorityOtherAreaEnd, LocalDate secondApplicableAreaStart, LocalDate secondApplicableAreaEnd, LocalDate secondGyeonggiAreaStart, LocalDate secondGyeonggiAreaEnd, LocalDate secondOtherAreaStart, LocalDate secondOtherAreaEnd, String homepage) {
        AptInfo = aptInfo;
        this.specialReceptionStartDate = specialReceptionStartDate;
        this.specialReceptionEndDate = specialReceptionEndDate;
        this.priorityApplicableAreaStart = priorityApplicableAreaStart;
        this.priorityApplicableAreaEnd = priorityApplicableAreaEnd;
        this.priorityGyeonggiAreaStart = priorityGyeonggiAreaStart;
        this.priorityGyeonggiAreaEnd = priorityGyeonggiAreaEnd;
        this.priorityOtherAreaStart = priorityOtherAreaStart;
        this.priorityOtherAreaEnd = priorityOtherAreaEnd;
        this.secondApplicableAreaStart = secondApplicableAreaStart;
        this.secondApplicableAreaEnd = secondApplicableAreaEnd;
        this.secondGyeonggiAreaStart = secondGyeonggiAreaStart;
        this.secondGyeonggiAreaEnd = secondGyeonggiAreaEnd;
        this.secondOtherAreaStart = secondOtherAreaStart;
        this.secondOtherAreaEnd = secondOtherAreaEnd;
        this.homepage = homepage;
    }
}
