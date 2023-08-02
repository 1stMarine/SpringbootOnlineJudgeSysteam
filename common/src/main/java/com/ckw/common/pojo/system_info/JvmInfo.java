package com.ckw.common.pojo.system_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JvmInfo {
    private Long totalMemory;
    private Long freeMemory;
    private Long maxMemory;
    private Long usedMemory;
    private Double usageMemory;
    private String jdkVersion;
    private String jdkHome;
}
