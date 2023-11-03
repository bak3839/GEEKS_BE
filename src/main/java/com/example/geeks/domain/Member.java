package com.example.geeks.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String major;

    private int studentID;

    private int gender;

    private int exp;

    private boolean smoking;

    @Builder
    public Member(Long id, String nickname, String email, String password, String major, int gender, int exp, boolean smoking, int studentID) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.major = major;
        this.gender = gender;
        this.exp = exp;
        this.smoking = smoking;
        this.studentID = studentID;
    }
}
