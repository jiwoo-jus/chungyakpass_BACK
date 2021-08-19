package com.hanium.chungyakpassback.entity.input;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="입력_세대")
public class Household {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "세대id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "주소id")
    private Address address;

    @Builder
    public Household(Address address){
        this.address = address;
    }
}
