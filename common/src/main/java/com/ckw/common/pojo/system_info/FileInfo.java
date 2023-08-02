package com.ckw.common.pojo.system_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {
    private String dirName;
    private String dirType;
    private String fileType;
    private Long totalSize;
    private Long freeSize;
    private Long usedSize;
    private Double usage;
}
