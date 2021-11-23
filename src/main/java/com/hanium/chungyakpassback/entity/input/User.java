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
    private Long id;

    @OneToOne(mappedBy = "user")
    private UserBankbook userBankbook;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "house_id")
    private House house;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spouse_house_id")
    private House spouseHouse;

    @OneToOne
    @JoinColumn(name = "house_member_id")
    private HouseMember houseMember;

    @OneToOne
    @JoinColumn(name = "spouse_house_member_id")
    private HouseMember spouseHouseMember;

    @Column
    private String email;

    @JsonIgnore
    @Column
    private String password;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}