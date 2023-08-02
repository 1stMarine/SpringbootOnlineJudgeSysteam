package com.ckw.user.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String nickName;
    private String email;
    private String verCode;
    private String password;
    private Integer experience;
    private Integer level;
    private String location;
    private String school;
    private String tag;
    private String gender;
    private Integer easyResolve;
    private Integer meddleResolve;
    private Integer hardResolve;
    private Integer nightmareResolve;
    private String role;
    private String url;
    private String token;
    private Integer rank;
    private Long fans;
    private Long subscribe;
    private String sign;
    private Integer ban;
}
