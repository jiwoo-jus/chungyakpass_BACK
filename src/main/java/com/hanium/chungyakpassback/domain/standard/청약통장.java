package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.여부;
import com.hanium.chungyakpassback.domain.enumtype.청약통장종류;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_청약통장")
public class 청약통장 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약통장id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 청약통장종류 청약통장종류;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 국민주택공급가능;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 민영주택공급가능;

    @Column
    private Integer 분양면적제한;

    @Builder
    public 청약통장(청약통장종류 청약통장종류, 여부 국민주택공급가능, 여부 민영주택공급가능, Integer 분양면적제한) {
        this.청약통장종류 = 청약통장종류;
        this.국민주택공급가능 = 국민주택공급가능;
        this.민영주택공급가능 = 민영주택공급가능;
        this.분양면적제한 = 분양면적제한;
    }
}
