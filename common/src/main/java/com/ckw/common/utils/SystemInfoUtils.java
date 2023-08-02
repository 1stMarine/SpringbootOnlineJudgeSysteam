package com.ckw.common.utils;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ckw.common.pojo.system_info.*;
import com.ckw.common.websocket.WebSocketUtil;
import org.springframework.web.socket.WebSocketSession;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.Display;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 系统消息工具类
 *
 **/
public class SystemInfoUtils {
    private static final int OSHI_WAIT_SECOND = 1000;
    private static final SystemInfo systemInfo = new SystemInfo();
    private static final HardwareAbstractionLayer hardware = systemInfo.getHardware();
    private static final OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

    public static CpuInfo getCpuInfo() {

        CentralProcessor processor = hardware.getProcessor();
        // CPU信息
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_SECOND);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[CentralProcessor.TickType.NICE.getIndex()] - prevTicks[CentralProcessor.TickType.NICE.getIndex()];
        long irq = ticks[CentralProcessor.TickType.IRQ.getIndex()] - prevTicks[CentralProcessor.TickType.IRQ.getIndex()];
        long softirq = ticks[CentralProcessor.TickType.SOFTIRQ.getIndex()] - prevTicks[CentralProcessor.TickType.SOFTIRQ.getIndex()];
        long steal = ticks[CentralProcessor.TickType.STEAL.getIndex()] - prevTicks[CentralProcessor.TickType.STEAL.getIndex()];
        long cSys = ticks[CentralProcessor.TickType.SYSTEM.getIndex()] - prevTicks[CentralProcessor.TickType.SYSTEM.getIndex()];
        long user = ticks[CentralProcessor.TickType.USER.getIndex()] - prevTicks[CentralProcessor.TickType.USER.getIndex()];
        long iowait = ticks[CentralProcessor.TickType.IOWAIT.getIndex()] - prevTicks[CentralProcessor.TickType.IOWAIT.getIndex()];
        long idle = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;


        return new CpuInfo(
                processor.getLogicalProcessorCount(),
                simpleDouble((cSys * 1.0 / totalCpu) * 100),
                simpleDouble((user * 1.0 / totalCpu) * 100),
                simpleDouble((iowait * 1.0 / totalCpu) * 100),
                simpleDouble((1.0 - (idle * 1.0 / totalCpu)) * 100)
        );
    }

    /**
     * 系统jvm信息
     */
    public static JvmInfo getJvmInfo() {

        Properties props = System.getProperties();
        Runtime runtime = Runtime.getRuntime();
        long jvmTotalMemoryByte = runtime.totalMemory();
        long freeMemoryByte = runtime.freeMemory();
        return new JvmInfo(
                runtime.totalMemory(),
                runtime.freeMemory(),
                runtime.maxMemory(),
                (jvmTotalMemoryByte - freeMemoryByte),
                simpleDouble(((jvmTotalMemoryByte - freeMemoryByte) * 1.0 / jvmTotalMemoryByte) * 100),
                props.getProperty("java.version"),
                props.getProperty("java.home")
        );
    }

    /**
     * 系统内存信息
     */
    public static MemoryInfo getMemInfo() {
        systemInfo.getOperatingSystem();
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long pageSize = memory.getPageSize();
        //总内存
        long totalByte = memory.getTotal();
        //剩余
        long acaliableByte = memory.getAvailable();
        return new MemoryInfo(
                totalByte,
                (totalByte - acaliableByte),
                acaliableByte,
                pageSize,
                simpleDouble(((totalByte - acaliableByte) * 1.0 / totalByte) * 100)
        );
    }

    /**
     * 系统盘符信息
     */
    public static List<FileInfo> getSysFileInfo() {
//        JSONObject cpuInfo;
//        JSONArray sysFiles = new JSONArray();
        FileSystem fileSystem = operatingSystem.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();
        ArrayList<FileInfo> files = new ArrayList<>();
        for (OSFileStore fs : fileStores) {
            FileInfo fileInfo = new FileInfo(
                    fs.getMount(),
                    fs.getType(),
                    fs.getName(),
                    fs.getTotalSpace(),
                    fs.getUsableSpace(),
                    (fs.getTotalSpace() - fs.getUsableSpace()),
                    0.0
            );


            if (fs.getTotalSpace() != 0) {
                fileInfo.setUsage(simpleDouble((fs.getTotalSpace() - fs.getUsableSpace()) * 1.0 / fs.getTotalSpace() * 100));
            }
            files.add(fileInfo);
        }
        return files;
    }

    /**
     * 系统信息
     */
    public static SysInfo getSysInfo() throws UnknownHostException {
        Properties props = System.getProperties();
        return new SysInfo(
                props.getProperty("os.name"),
                props.getProperty("os.arch"),
                InetAddress.getLocalHost().getHostName(),
                InetAddress.getLocalHost().getHostAddress(),
                props.getProperty("user.dir")

        );
    }



    /**
     * 所有系统信息
     */
    public static ComputeInfo getInfo() throws UnknownHostException {
        return new ComputeInfo(
                getCpuInfo(),
                getJvmInfo(),
                getMemInfo(),
                getSysFileInfo(),
                getSysInfo()
        );
    }

    public static void callSysInfo(WebSocketSession session){
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    ComputeInfo info = getInfo();
                    System.out.println(info);
                    WebSocketUtil.sendMessage(session,JSONObject.toJSONString(info));
                    if(!session.isOpen()){
                        return;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }

    private static Double simpleDouble(Double num){
        return Math.round(num * 10) /10.0;
    }

    /**
     * 单位转换
     */
    private static String formatByte(long byteNumber) {
        //换算单位
        double FORMAT = 1024.0;
        double kbNumber = byteNumber / FORMAT;
        if (kbNumber < FORMAT) {
            return new DecimalFormat("#.##KB").format(kbNumber);
        }
        double mbNumber = kbNumber / FORMAT;
        if (mbNumber < FORMAT) {
            return new DecimalFormat("#.##MB").format(mbNumber);
        }
        double gbNumber = mbNumber / FORMAT;
        if (gbNumber < FORMAT) {
            return new DecimalFormat("#.##GB").format(gbNumber);
        }
        double tbNumber = gbNumber / FORMAT;
        return new DecimalFormat("#.##TB").format(tbNumber);
    }
}
