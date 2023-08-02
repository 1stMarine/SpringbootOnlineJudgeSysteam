package com.ckw.common.pojo.system_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoryInfo {
    private Long totalMemory;
    private Long usedMemory;
    private Long freeMemory;
    private Long pageSize;
    private Double usageMemory;
}
