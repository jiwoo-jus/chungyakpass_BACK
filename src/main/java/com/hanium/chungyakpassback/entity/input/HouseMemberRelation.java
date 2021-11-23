package com.hanium.chungyakpassback.entity.input;

import com.hanium.chungyakpassback.entity.base.BaseTime;
import com.hanium.chungyakpassback.entity.standard.Relation;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_house_member_relation")
public class HouseMemberRelation extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_member_relation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opponent_house_member_id")
    private HouseMember opponent;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id")
    private com.hanium.chungyakpassback.entity.standard.Relation relation;


    @Builder
    public HouseMemberRelation(User user, HouseMember opponent, Relation relation) {
        this.user = user;
        this.opponent = opponent;
        this.relation = relation;
    }

//    public HouseMemberRelation updateHouseMemberRelation(User user, HouseMember houseMember, Relation relation){
//        this.relation = relation;
//        return this;
//    }
}
