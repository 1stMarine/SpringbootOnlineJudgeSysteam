package com.ckw.common.pojo.system_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComputeInfo {
    private CpuInfo cpuInfo;
    private JvmInfo jvmInfo;
    private MemoryInfo memoryInfo;
    private List<FileInfo> filesInfo;
    private SysInfo sysInfo;
}
