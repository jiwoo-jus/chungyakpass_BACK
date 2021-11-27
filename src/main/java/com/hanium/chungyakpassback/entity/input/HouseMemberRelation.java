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
    private Long id; //세대구성원관계id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user; //회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opponent_house_member_id")
    private HouseMember opponent; //상대 세대구성원

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "relation_id")
    private com.hanium.chungyakpassback.entity.standard.Relation relation; //관계


    @Builder
    public HouseMemberRelation(User user, HouseMember opponent, Relation relation) {
        this.user = user;
        this.opponent = opponent;
        this.relation = relation;
    }
}
