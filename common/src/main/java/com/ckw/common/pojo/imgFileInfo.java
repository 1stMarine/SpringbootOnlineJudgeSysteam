package com.ckw.common.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Echo
 * Img文件上传信息
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class imgFileInfo {
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private int size;
    private String originalName;
    private String bucketFileName;
    private String bucketFileFullName;
    private String url;
    private String uid;
}
