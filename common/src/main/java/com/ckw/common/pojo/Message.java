package com.ckw.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 凯威
 * 返回信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private int state;
    private Object data;
    private String message;
}
