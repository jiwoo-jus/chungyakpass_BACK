package com.hanium.chungyakpassback.entity.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanium.chungyakpassback.entity.authority.Authority;
import com.hanium.chungyakpassback.entity.base.BaseTime;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "inp_user")
public class User extends BaseTime {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; //회원id

    @OneToOne(mappedBy = "user")
    private UserBankbook userBankbook; //회원 청약통장

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house; //회원 세대

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spouse_house_id")
    private House spouseHouse; //배우자 세대

    @OneToOne
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember; //회원 세대구성원

    @OneToOne
    @JoinColumn(name = "spouse_house_member_id")
    private HouseMember spouseHouseMember; //배우자 세대구성원

    @Column
    private String email; //이메일

    @JsonIgnore
    @Column
    private String password; //비밀번호

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated; //활성화 여부

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}