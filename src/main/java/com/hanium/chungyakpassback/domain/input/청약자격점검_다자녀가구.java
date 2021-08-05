package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.다자녀가구유형;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="입력_청약자격점검_다자녀가구")
public class 청약자격점검_다자녀가구 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약자격점검_다자녀가구id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "청약자격점검id")
    private 청약자격점검 청약자격점검;

    @Column
    private int 미성년자녀수;

    @Column
    private int 영유아자녀수;

    @Column
    @Enumerated(EnumType.STRING)
    private 다자녀가구유형 다자녀가구유형;


    @Builder
    public 청약자격점검_다자녀가구(청약자격점검 청약자격점검, int 미성년자녀수, int 영유아자녀수, 다자녀가구유형 다자녀가구유형) {
        this.청약자격점검 = 청약자격점검;
        this.미성년자녀수 = 미성년자녀수;
        this.영유아자녀수 = 영유아자녀수;
        this.다자녀가구유형 = 다자녀가구유형;
    }
}
