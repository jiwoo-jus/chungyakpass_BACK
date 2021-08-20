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
@Table(name = "inp_house_member_relation")
public class HouseMemberRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opponent_house_member_id")
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
