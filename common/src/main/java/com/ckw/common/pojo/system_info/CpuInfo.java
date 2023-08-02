package com.ckw.common.pojo.system_info;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CpuInfo {
//    CPU核心数量
    private Integer cpuNum;
//    CPU系统使用率
    private Double sysUsage;
//    Cpu用户使用率
    private Double userUsage;
//    当前等待率
    private Double ioWait;
//    CPU使用率
    private Double cpuUsage;



}
