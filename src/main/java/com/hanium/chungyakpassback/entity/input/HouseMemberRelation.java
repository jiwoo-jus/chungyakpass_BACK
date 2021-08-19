package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.enumtype.Relation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "입력_세대구성원_관계")
public class HouseMemberRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "세대구성원_관계id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "회원id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "관계자_세대구성원id")
    private HouseMember opponent;

    @Column
    @Enumerated(EnumType.STRING)
    private Relation relation;


    @Builder
    public HouseMemberRelation(User user, HouseMember opponent, Relation relation) {
        this.user = user;
        this.opponent = opponent;
        this.relation = relation;
    }
}
