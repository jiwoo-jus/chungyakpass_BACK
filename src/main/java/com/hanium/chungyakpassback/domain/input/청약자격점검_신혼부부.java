package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.신혼부부유형;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="입력_청약자격점검_신혼부부")
public class 청약자격점검_신혼부부 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약자격점검_신혼부부id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "청약자격점검id")
    private 청약자격점검 청약자격점검;

    @Column
    @Enumerated(EnumType.STRING)
    private 신혼부부유형 신혼부부유형;

    @Column
    private int 미성년자녀수;

    @Column
    private Integer 한부모가족자녀나이;


    @Builder
    public 청약자격점검_신혼부부(청약자격점검 청약자격점검, 신혼부부유형 신혼부부유형, int 미성년자녀수, Integer 한부모가족자녀나이) {
        this.청약자격점검 = 청약자격점검;
        this.신혼부부유형 = 신혼부부유형;
        this.미성년자녀수 = 미성년자녀수;
        this.한부모가족자녀나이 = 한부모가족자녀나이;
    }
}