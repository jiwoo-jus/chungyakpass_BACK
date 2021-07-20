package com.hanium.chungyakpassback.domain.input;

import com.hanium.chungyakpassback.domain.enumtype.지역_레벨1;
import com.hanium.chungyakpassback.domain.enumtype.지역_레벨2;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "주소")
public class 주소 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "주소id")
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨1 지역_레벨1;

    @Column
    @Enumerated(EnumType.STRING)
    private 지역_레벨2 지역_레벨2;

    @Builder
    public 주소(지역_레벨1 지역_레벨1, 지역_레벨2 지역_레벨2){
        this.지역_레벨1 = 지역_레벨1;
        this.지역_레벨2 = 지역_레벨2;
    }
}