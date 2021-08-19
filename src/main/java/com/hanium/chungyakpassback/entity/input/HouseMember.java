package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.enumtype.Yn;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "입력_세대구성원")
public class HouseMember {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "세대id")
    private House house; //세대

    @OneToOne
    @JoinColumn(name = "배우자_세대구성원id")
    private HouseMember spouse; //배우자세대구성원

    @Column
    @Enumerated(EnumType.STRING)
    private Yn householderYn; //세대주여부

    @Column
    private String name; //이름

    @Column
    private LocalDate birthDate; //생년월일

    @Column
    @Enumerated(EnumType.STRING)
    private Yn foreignerYn; //외국인여부

    @Column
    @Enumerated(EnumType.STRING)
    private Yn soldierYn; //장기복무중인군인여부

    @Column
    private LocalDate marriageDate; //혼인신고일

    @Column
    private LocalDate homelessStartDate; //무주택시작일

    @Column
    private LocalDate transferDate; //전입신고일


    @Builder
    public HouseMember(House house, HouseMember spouse, Yn householderYn, String name, LocalDate birthDate, Yn foreignerYn, Yn soldierYn, LocalDate marriageDate, LocalDate homelessStartDate, LocalDate transferDate) {
        this.house = house;
        this.spouse = spouse;
        this.householderYn = householderYn;
        this.name = name;
        this.birthDate = birthDate;
        this.foreignerYn = foreignerYn;
        this.soldierYn = soldierYn;
        this.marriageDate = marriageDate;
        this.homelessStartDate = homelessStartDate;
        this.transferDate = transferDate;
    }
}