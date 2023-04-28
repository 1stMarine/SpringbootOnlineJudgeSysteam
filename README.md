# 前情提要
项目运行地址预览: [judger/src/main/resources/application-judge.properties](https://www.nextstepcode.club/)

基于Springboot + Vue3的Onlinejudge在线判题系统

vue3 + ts + vite 前端 : https://github.com/ckw0812/onlineJudgeVue3.git （github)

vue3 + ts + vite 前端 : https://gitee.com/aa760303601_AA/onlineJudgeVue3.git (gitee)



# 环境

## java
springboot + jdk1.8

使用idea打开配置好jdk就能跑

项目需要Docker支持
## Docker
目前项目的Docker镜像都是使用官方镜像来改造的，所以比较大、功能比较荣誉，目前正在定制小巧的镜像提升性能
### 安装配置Docker
这个网上很多，不多介绍，没有特殊要求安装好就行。
windows有docker desktop 

安装好Docker后执行:

```shell
docker pull echocen/gcc:v1
docker pull echocen/openjdk:v1
docker pull echocen/python:v1

```
检查是否安装完成:
```shell
root@VM-12-2-ubuntu:/# docker images
REPOSITORY        TAG       IMAGE ID       CREATED         SIZE
echocen/python    v1        042eadd5d56a   11 months ago   939MB
echocen/openjdk   v1        038290a3032c   11 months ago   486MB
echocen/gcc       v1        f0da4088ed72   11 months ago   1.29GB
```
### 项目中配置Docker
配置文件路径:judger/src/main/resources/application-judge.properties

除了挂载目录和存储目录以外，其他不需要更改

### 数据

根目录的oj.sql文件
请在web_front/src/main/resources/application.yml 配置你的数据库
# 效果图
![eb4770b623c063f2cc61108c71a1383](https://user-images.githubusercontent.com/91200734/233857296-4796e241-39dc-4b0d-8414-d9e5b7eaffd8.png)
![4fff8c8d51e42b7d269b4c5617c8267](https://user-images.githubusercontent.com/91200734/233857298-9ca542f1-415f-49af-9409-1445e8972354.png)
![283a22ed930457ec45e7ac03bd49db0](https://user-images.githubusercontent.com/91200734/233857302-c0d7a42a-2935-46ec-8bf8-7f0de437a852.png)
![e7e6ea4f0293dff95de69b105f89b76](https://user-images.githubusercontent.com/91200734/233857304-a28aa1f2-8c3d-4cd9-a02a-a8bc56f92ed4.png)
![5545bb0ca251a113ee5f37229e7c2e4](https://user-images.githubusercontent.com/91200734/233857307-d894e8ee-4001-4ed3-bc94-37915b88d37d.jpg)
