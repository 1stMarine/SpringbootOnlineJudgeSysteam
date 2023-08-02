package com.ckw.common.pojo.system_info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysInfo {
    private String osName;
    private String osArch;
    private String computeName;
    private String ip;
    private String projectDir;
}
