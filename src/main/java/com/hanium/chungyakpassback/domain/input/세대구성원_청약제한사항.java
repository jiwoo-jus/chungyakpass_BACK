package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.특별공급제한;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "입력_세대구성원_청약제한사항")
public class 세대구성원_청약제한사항 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_청약제한사항id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원;

    @ManyToOne
    @JoinColumn(name = "세대구성원_청약신청이력id")
    private 세대구성원_청약신청이력 세대구성원_청약신청이력;

    @Column
    private LocalDate 당첨일;

    @Column
    private LocalDate 재당첨제한;

    @Column
    @Enumerated(EnumType.STRING)
    private 특별공급제한 특별공급제한;

    @Column
    private LocalDate 부적격당첨자제한;

    @Column
    private LocalDate 투기과열지구청약과열지역1순위제한;

    @Column
    private LocalDate 가점제당첨제한;


    @Builder

    public 세대구성원_청약제한사항(세대구성원 세대구성원, 세대구성원_청약신청이력 세대구성원_청약신청이력, LocalDate 당첨일, LocalDate 재당첨제한, 특별공급제한 특별공급제한, LocalDate 부적격당첨자제한, LocalDate 투기과열지구청약과열지역1순위제한, LocalDate 가점제당첨제한) {
        this.세대구성원 = 세대구성원;
        this.세대구성원_청약신청이력 = 세대구성원_청약신청이력;
        this.당첨일 = 당첨일;
        this.재당첨제한 = 재당첨제한;
        this.특별공급제한 = 특별공급제한;
        this.부적격당첨자제한 = 부적격당첨자제한;
        this.투기과열지구청약과열지역1순위제한 = 투기과열지구청약과열지역1순위제한;
        this.가점제당첨제한 = 가점제당첨제한;
    }
}
