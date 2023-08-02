package com.ckw.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 邮箱验证码实体类
 * @author Echo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToEmail {
    private String[] tos;
    private String subject;
    private String content;
}
