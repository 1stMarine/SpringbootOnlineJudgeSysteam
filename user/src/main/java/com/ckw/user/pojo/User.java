package com.ckw.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String nickName;
    private String email;
    private String password;
    private int experience;
    private int level;
    private String location;
    private String school;
    private String tag;
    private String gender;
    private int easyResolve;
    private int meddleResolve;
    private int hardResolve;
    private int nightmareResolve;
    private String role;
    private String url;
    private String token;
    private int rank;
}
