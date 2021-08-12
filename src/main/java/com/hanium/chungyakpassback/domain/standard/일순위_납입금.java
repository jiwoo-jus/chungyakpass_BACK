package com.hanium.chungyakpassback.domain.standard;

import com.hanium.chungyakpassback.domain.enumtype.공급유형;
import com.hanium.chungyakpassback.domain.enumtype.예치금액지역구분;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name="기준_일순위_납입금")
public class 일순위_납입금 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "일순위_납입금id")
    private Long id;

    @Column
    private Integer 면적_초과;

    @Column
    private Integer 면적_이하;

    @Column
    @Enumerated(EnumType.STRING)
    private 예치금액지역구분 예치금액지역구분;

    @Column
    private int 예치금액;

    @Builder
    public 일순위_납입금(Integer 면적_초과, Integer 면적_이하, com.hanium.chungyakpassback.domain.enumtype.예치금액지역구분 예치금액지역구분, int 예치금액) {
        this.면적_초과 = 면적_초과;
        this.면적_이하 = 면적_이하;
        this.예치금액지역구분 = 예치금액지역구분;
        this.예치금액 = 예치금액;
    }
}
