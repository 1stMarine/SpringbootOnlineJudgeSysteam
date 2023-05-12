package com.ckw.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMonthSubmit {
    private int id;
    private int year;
    private int month;
    private int uid;
    private int count;

}
