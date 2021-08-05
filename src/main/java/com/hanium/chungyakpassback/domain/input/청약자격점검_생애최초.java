package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="입력_청약자격점검_생애최초")
public class 청약자격점검_생애최초 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약자격점검_생애최초id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "청약자격점검id")
    private 청약자격점검 청약자격점검;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 소득세5년이상납부여부;

    @Column
    private int 저축액;


    @Builder
    public 청약자격점검_생애최초(청약자격점검 청약자격점검, 여부 소득세5년이상납부여부, int 저축액) {
        this.청약자격점검 = 청약자격점검;
        this.소득세5년이상납부여부 = 소득세5년이상납부여부;
        this.저축액 = 저축액;
    }
}
