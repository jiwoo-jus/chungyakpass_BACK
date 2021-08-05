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
@Table(name="입력_청약자격점검_노부모부양")
public class 청약자격점검_노부모부양 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "청약자격점검_노부모부양id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "청약자격점검id")
    private 청약자격점검 청약자격점검;

    @Column
    @Enumerated(EnumType.STRING)
    private 여부 노부모부양여부;

    
    @Builder
    public 청약자격점검_노부모부양(청약자격점검 청약자격점검, 여부 노부모부양여부) {
        this.청약자격점검 = 청약자격점검;
        this.노부모부양여부 = 노부모부양여부;
    }
}
