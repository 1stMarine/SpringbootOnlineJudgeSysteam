package com.ckw.judger.judger;

import com.ckw.judger.pojo.Commands;
import com.ckw.judger.pojo.TestPack;
import com.ckw.judger.pojo.TestResult;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * @author 凯威
 * 判题机核心
 */
@Slf4j
public abstract class JudgeCore {
    /**
     * 测试对象
     */
    protected TestPack testPack;
    /**
     * 是否编译完成
     */
    private boolean isCompile = false;
    /**
     * 用户代码路径 包括用户id/题目id/时间戳
     */
    private String userCodePath;
    /**
     * 只有用户id/题目id/时间戳
     */
    private String codePath;
    /**
     * 测试结果对象
     */
    TestResult testResult = null;

    /**
     * 初始化操作
     */
    public void init(){
        testResult = new TestResult(
                testPack.getUid(),
                testPack.getQuestionName(),
                testPack.getQid(),
                0.0,0.0,
                false,
                testPack.getSubmitTimeFormat(),
                testPack.getCode(),
                null,
                "Accept",
                "Accept"
        );

        // 代码路径
        codePath =  testPack.getUid() + "/"
                    + testPack.getQid() + "/"
                    + testPack.getSubmitTime();
        // 完整代码路径
        userCodePath = Commands.IS_LIN ? Commands.LIN_ROOT + codePath : Commands.WIN_ROOT + codePath;
        log.info(Commands.IS_LIN ? " 当前是linux系统" : "当前是windows");
        System.out.println("userCodePath"+userCodePath);
        //        创建判题目录
        new File(userCodePath).mkdirs();
        //  输入测试样例文件夹
        new File(userCodePath+"/in").mkdirs();
        //  输出测试样例文件夹
        new File(userCodePath+"/out").mkdirs();
        //  时间样例文件夹
        new File(userCodePath+"/time").mkdirs();
        //  选择语言
        this.choiceLanguage();
        //   保存代码
        write(userCodePath+"/"+testPack.getName(),testPack.getCode());
    }

    /**
     * 选择语言
     */
    public void choiceLanguage(){
        //  编译名如c++ : g++ test.cpp
        String compileName = "";
        //  执行名如c++ : ./a.out
        String executeName = "";
        //  容器类型
        String type = "";
        switch (testPack.getLanguage()){
            case "Cpp_c":
//                编译名
                compileName = "g++ test.cpp";
//                执行名
                executeName = "./a.out";
//                容器名
                type = "echocen/gcc:v1";
//                文件名
                testPack.setName("test.cpp");
                break;
            case "java":
                compileName = "javac Main.java";
                executeName = "java Main";
                type = "echocen/openjdk:v1";
                testPack.setName("Main.java");
                break;
            case "python":
//                python不用编译，直接运行
                isCompile = true;
                executeName = "python test.py";
                type = "echocen/python:v1";
                testPack.setName("test.py");
            default:
                break;
        }
        //  创建容器
        creatContainer(type);
        //  设置编译命令
        setCompileCommand(compileName);
        //  设置执行命令
        setExecuteCommand(executeName);
        //  设置删除、停止镜像命令
        testPack.setStopCommand(Commands.STOP.replace("%id", testPack.getContainerId()));
        testPack.setDeleteCommand(Commands.DELETE.replace("%id",testPack.getContainerId()));
    }

    /**
     * 设置编译命令
     * @param compileName   编译命令 + 文件名
     */
    public void setCompileCommand(String compileName){
        testPack.setCompileCommand(
                Commands.COMPILE
                        .replace("%id",testPack.getContainerId())
                        .replace("%code",Commands.LIN_ROOT + codePath)
                        .replace("%c",compileName)
        );
    }

    /**
     * 设置执行shell
     * 设置运行command
     * @param executeName 运行命令 + 编译后文件名
     */
    public void setExecuteCommand(String executeName){
//        设置运行命令
        testPack.setExecuteCommand(
            Commands.EXECUTE
                    .replace("%id",testPack.getContainerId())
                    .replace("%code",Commands.LIN_ROOT + codePath)
        );
        StringBuilder shell = new StringBuilder();

//        shell、输入文件
        for (int i = 0; i < testPack.getTestSampleList().size(); i++) {
//            输入文件
            write(userCodePath+"/in/in"+i+".txt",
                        testPack.getTestSampleList().get(i).getInput()
                    );
            String doShell = Commands.SHELL.replace("%i",String.valueOf(i)).replace("%name",executeName);
            shell.append(i == testPack.getTestSampleList().size()-1 ?  doShell : doShell+"\n");
        }
        write(userCodePath+"/do.sh",shell.toString());
    }

    /**
     * 工具函数写文件命令
     * @param path 文件路径
     * @param content   写入内容
     */
    public void write(String path,String content){
        try(FileWriter writer = new FileWriter(new File(path))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建镜像命令
     * @param type 容器类型
     */
    public void creatContainer(String type){
        //        设置命令 + 设置docker挂载路径
        String containerId = doCommand(
                Arrays.asList(
                        Commands.CREATE
                                .replace("%h", Commands.IS_LIN ? Commands.LIN_PATH : Commands.WIN_PATH)
                                .replace("%d", Commands.LIN_PATH)
                                .replace("%name",type)
                                .split(",")
                ));
        testPack.setContainerId(containerId);

    }

    /**
     * 执行编译
     * @return 返回结果为空值即为编译成功否则返回编译错误信息
     */
    public String compile(){
        // 如果已经编译过不再编译
        if(isCompile){
            return null;
        }
        return doCommand(Arrays.asList(testPack.getCompileCommand().split(",")));
    }

    /**
     * 执行代码
     * @return
     */
    public String execute(){
        return doCommand(Arrays.asList(testPack.getExecuteCommand().split(",")));
    }

    /**
     * 工具函数 执行命令
     * @param commands 需要执行的命令
     * @return
     */
    public String doCommand(List<String> commands){

        StringBuilder result = new StringBuilder();

        ProcessBuilder processBuilder = new ProcessBuilder(commands);
        log.info(processBuilder.command().toString());

        processBuilder.directory(new File(userCodePath));

        processBuilder.redirectErrorStream(true);
        Process process;
        // 执行命令
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        // 执行命令后的结果
        try(BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while((line = br.readLine()) != null){
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result.toString();
    }

    /**
     * 工具函数 加载输出文件
     * @param path 文件路径
     * @return 文件
     */
    private List<File> loadFiles(String path){
        List<File> files = new ArrayList<>();

        for (int i = 0; i < testPack.getTestSampleList().size(); i++) {
            try {
                files = Files.walk(Paths.get(path))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        orderFiles(files);
        return files;
    }

    /**
     * 工具函数 对文件根据编号进行排序
     * 加载文件名一旦数量多会乱序，跟答案对不上
     * @param files
     */
    private void orderFiles(List<File> files){
        Collections.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                return getIndex(o1.getName()) - getIndex(o2.getName());
            }
        });
    }

    /**
     * 正则提取数字
     * @param fileName 文件名
     * @return 文件编号
     */
    private int getIndex(String fileName){
        String regEx = "[^0-9]";
        Pattern file1Name = Pattern.compile(regEx);
        Matcher matcher = file1Name.matcher(fileName);
        return Integer.parseInt(matcher.replaceAll("").trim());
    }

    /**
     * 工具函数 得到文件内容
     * @param files 文件
     * @return
     */
    public List<String> getFilesContent(List<File> files){

        Iterator<File> iterator = files.iterator();
        List<String> contents = new ArrayList<>();
        while(iterator.hasNext()){
            File file = iterator.next();
            BufferedReader bufferedReader = null;
            StringBuilder content = new StringBuilder();
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    content.append(line);
                }
                contents.add(content.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        return contents;
    }

    /**
     * 工具函数 得到文件内容
     * @param file 文件
     * @return
     */
    public String getFilesContent(File file){
        BufferedReader bufferedReader = null;
        StringBuilder content = new StringBuilder();
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                content.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content.toString();
    }

    /**
     * 检查答案
     * @return
     */
    public void checkAnswer(){
        List<File> outFiles = loadFiles(userCodePath+"/out");
        List<File> timeFiles = loadFiles(userCodePath+"/time");

        //  一致性检查
        if(testPack.getTestSampleList().size() != outFiles.size() || testPack.getTestSampleList().size() != timeFiles.size()){
            // 文件数目不对 这里应该抛自定义异常
            return;
        }

        List<String> userOutputs = getFilesContent(outFiles);
        List<String> userTimes = getFilesContent(timeFiles);

        System.out.println(userOutputs);
        System.out.println(userTimes);

        double totalTime = 0.0;
        int totalMemory = 0;
        for (int i = 0; i < userTimes.size(); i++) {
            if(checkError(userOutputs.get(i))){
                setPackError("代码在运行过程中发生了一些错误，请检查!",userOutputs.get(i));
                return;
            }
           if( checkTimeAndMemory(userTimes.get(i))){
               setPackError("内存/时间超出限制"," 你的时间或者空间超出限制了，尝试优化代码");
               return;
           }
            if(checkUserOut(
                    testPack.getTestSampleList().get(i).getOutput(),
                    userOutputs.get(i)
            )){
                System.out.println(testPack.getTestSampleList().get(i).getOutput() + " ====================== " + userOutputs.get(i));
                setPackError("WrongAnswer","答案错误,出现在第 " + (i+1) + " 个测试样例");
                testPack.getTestSampleList().get(i).setUserOutput(userOutputs.get(i));
                testResult.setTestSample(testPack.getTestSampleList().get(i));
                return;
            }
            totalTime += Double.parseDouble(splitTimeAndMemory(userTimes.get(i))[0]);
            totalMemory += Integer.parseInt(splitTimeAndMemory(userTimes.get(i))[1]);
        }
        testResult.setTime((totalTime / userTimes.size() * 1000));
        testResult.setMemory((double) (totalMemory / userTimes.size() / 1024));
        testResult.setPass(true);
    }

    /**
     * 错误检查，检查是否有报错
     * @param userOut
     * @return
     */
    public boolean checkError(String userOut){
        if(
                userOut.contains("Error") ||
                userOut.contains("Exception") ||
                userOut.contains("error")
        ){
            return true;
        }
        return false;
    }

    public String[] splitTimeAndMemory(String timeAndMemory){
        return timeAndMemory.replace(" ", "").split(":");
    }

    public boolean checkTimeAndMemory(String timeAndMemory){
        String[] s = splitTimeAndMemory(timeAndMemory);
        double time = Double.parseDouble(s[0]);
        int memory = Integer.parseInt(s[1]);
        if(time > testPack.getTimeLimit() || memory > testPack.getMemoryLimit()){
            return true;
        }
        return false;
    }

    public boolean checkUserOut(String out,String userOut){
        return !out.equals(userOut);
    }

    public void setPackError(String message,String longMessage){
        testResult.setTitle(message);
        testResult.setMessage(longMessage);
    }

}
