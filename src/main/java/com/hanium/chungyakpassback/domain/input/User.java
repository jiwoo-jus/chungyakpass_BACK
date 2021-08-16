package com.hanium.chungyakpassback.domain.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanium.chungyakpassback.domain.authority.Authority;
import com.hanium.chungyakpassback.domain.enumtype.여부;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "세대구성원id")
    private 세대구성원 세대구성원본인;

    @Column
    private String email;

    @JsonIgnore
    @Column
    private String 비밀번호;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;

//    @Builder
//    public User(세대구성원 세대구성원본인, String 이메일, String 비밀번호) {
//        this.세대구성원본인 = 세대구성원본인;
//        this.이메일 = 이메일;
//        this.비밀번호 = 비밀번호;
//    }

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}