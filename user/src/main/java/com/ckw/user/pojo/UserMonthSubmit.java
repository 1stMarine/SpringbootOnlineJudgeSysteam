package com.ckw.user.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMonthSubmit {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private int year;
    private int month;
    @JsonSerialize(using = ToStringSerializer.class)
    private long uid;
    private int count;

}
