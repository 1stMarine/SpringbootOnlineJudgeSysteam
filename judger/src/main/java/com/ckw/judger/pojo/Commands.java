package com.ckw.judger.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

/**
 * @author 凯威
 * 判题机命令
 */
@Repository
@PropertySource("classpath:application-judge.properties")
public class Commands {
    /**
     * Windows环境下代码等文件存储路径首部
     */
    public static String WIN_ROOT;
    /**
     * Linux环境下代码等文件存储首部
     */
    public static String LIN_ROOT;
    /**
     * WIN开发环境下Docker挂载目录
     */

    public static boolean IS_LIN;

    public static String WIN_PATH;
    /**
     * Linux/Docker 挂载目录
     * 如果在Linux环境下开发将WIN_PATH换成LIN_PATH
     */
    public static String LIN_PATH;
    /**
     * 创建镜像命令
     */
    public static String CREATE;
    /**
     * 编译代码命令
     */
    public static String COMPILE;
    /**
     * 执行 shell 命令
     */
    public static String EXECUTE;
    /**
     * 执行代码命令
     */
    public static String SHELL = "timeout 2s time -ao time/time%i.txt -f %U:%M %name < in/in%i.txt > out/out%i.txt";
    /**
     * 停止容器命令
     */
    public static String STOP = "docker,stop,%id";
    /**
     * 删除容器命令
     */
    public static String DELETE = "docker,rm,%id";

    @Value(value = "${isLin}")
    public void setIsLin(boolean isLin) {
        IS_LIN = isLin;
    }

    @Value(value = "${win-root}")
    public void setWinRoot(String winRoot) {
        WIN_ROOT = winRoot;
    }
    @Value(value = "${lin-root}")
    public void setLinRoot(String linRoot) {
        LIN_ROOT = linRoot;
    }
    @Value(value = "${win-path}")
    public void setWinPath(String winPath) {
        WIN_PATH = winPath;
    }
    @Value(value = "${lin-path}")
    public void setLinPath(String linPath) {
        LIN_PATH = linPath;
    }

    @Value(value = "${create}")
    public void setCreate(String Create) {
        CREATE = Create;
    }

    @Value(value = "${compile}")
    public void setCompile(String compile) {
        COMPILE = compile;
    }
    @Value(value = "${execute}")
    public void setEXECUTE(String EXECUTE) {
        Commands.EXECUTE = EXECUTE;
    }
//    @Value(value = "${shell}")
//    public void setSHELL(String SHELL) {
//        Commands.SHELL = SHELL;
//    }

}
