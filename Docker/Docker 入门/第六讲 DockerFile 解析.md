# DockerFile 解析
## DockerFile 回顾
1. 手动编写一个 DockerFile 文件，当然，必须要符合 DockerFile 的规范
2. 有这个文件后，直接 docker build 命令执行，获得一个自定义的镜像
3. Docker run

## DockerFile 概念
1. Dockerfile 是用来构建 Docker 镜像的构建文件，是由一系列命令和参数构成的脚本。
2. DockerFile 构建三步骤：
```
(1) 编写 DockerFile 文件
(2) docker build
(3) docker run
```

## DockerFile 构建过程解析
### DockerFile 内容基础知识
1. 每条保留字指令都必须为大写字母且后面要跟随至少一个参数
2. 指令按照从上到下，顺序执行
3. `#`表示注释
4. 每条指令都会出创建一个新的镜像层，并对镜像进行提交

### Docker 执行 DockerFile 的大致流程
1. docker 从基础镜像运行一个容器
2. 执行一条指令并对容器作出修改
3. 执行类似 docker commit 的操作提交一个新的镜像层
4. docker 再基于刚提交的镜像运行一个新容器
5. 执行 dockerfile 中的下一条指令直到所有指令都执行完成

### 小结
1. 从应用软件的角度来看，DockerFile、Docker 镜像与 Docker 容器分别代表软件的三个不同阶段。
```
(1) Dockerfile 是软件的原材料
(2) Docker 镜像则可认为是软件的交付品。
(3) Docker 容器则可以认为是软件的运行态。
```
2. DockerFile 面向开发，Docker 镜像称为交付标准，Docker 容器则设计部署与运维，三者缺一不可，合力充当 Docker 体系的基石。
3. DockerFile 定义了进程需要的一切东西，DockerFile 设计的内容包括执行代码或者是文件、环境变量、依赖包、运行时环境、动态链接库、操作系统的发行版、服务进程和内核进程(当应用进程需要和系统服务和内核进程打交道，这时需要考虑如何设计 namespace 的权限控制等等)。
4. Docker 镜像，是在用 DockerFile 定义一个文件之后，docker build 时会产生一个 Docker 镜像，当运行 Dokcer 镜像时，会真正开始提供服务。

## DockerFile 体系结构(保留字指令)
1. FROM
```
(1) 基础镜像，当前新镜像是基于那个镜像的
```
2. MAINTAINER
```
(1) 镜像维护者的姓名和邮箱地址
```
3. RUN
```
(1) 容器构建时需要运行的命令
```
4. EXPOSE
```
(1) 当前容器对外暴露出的端口号
```
5. WORKDIR
```
(1) 指定在创建容器后，终端默认登录进来的个工作目录，一个落脚点
```
6. ENV
```
(1) 用来在构建镜像过程中设置环境变量
```
7. ADD
```
(1) 将宿主机目录下的文件拷贝进镜像且 ADD 命令会自动处理 URL 和解压 tar 压缩包
```
8. COPY
```
(1) 类似 ADD，拷贝文件和目录到镜像中。将从构建上下文目录中<源路径>的文件/目录复制到新的一层的镜像内的<目标路径>位置
```
9. VOLUME
```
(1) 容器数据卷，用于数据保存和持久化工作
```
10. CMD
```
(1) 指定一个容器启动时要运行的命令
(2) DockerFile 中可以有多个 CMD 指令，但只有最后一个生效，CMD 会被 docker run 之后的参数替换
```
11. ENTRYPOINT
```
(1) 指定一个容器启动时要运行的命令
(2) ENTRYPOINT 的目的和 CMD 一样，都是在指定容器启动程序及参数
```
12. ONBULD
```
(1) 当构建一个被继承的 DockerFile 时运行命令，父镜像在被子继承后父镜像的 onbuild 被触发
```

### 案例演示
### Base 镜像(scratch)
1. Docker Hub 中 99% 的镜像都是通过在 base 镜像中安装和配置需要的软件构建出来的。

### 自定义镜像 mycentos
1. 编写 DockerFile
```
FROM centos
MAINIAINER zzyy<zzyy167@126.com>

ENV MYPATH /usr/local
WORKDIR $MYPATH

RUN yum -y install vim
RUN yum -y install net-tools

EXPOSE 80

CMD echo $MYPATH
CMD echo "success---------ok"
CMD /bin/bash
```
2. 构建
```
(1) docker build -t 新镜像名字:TAG
```
3. 运行
```
(1) docker run -it 新镜像名字:TAG
```
4. 列出镜像的变更历史
```
docker history 镜像名
```

### CMD/ENTRYPOINT 镜像案例
1. 都是指定一个容器启动时要运行的命令
2. CMD
```
(1) DockerFile 中可以有多个 CMD 指令，但只有最后一个生效，CMD 会被 docker run 之后的参数替换
```
3. ENTRYPOINT
```
(1) docker run 之后的参数会被当做参数传递给 ENTRYPOINT， 之后形成新的命令组合
```

### 自定义镜像 Tomcat9
1. mkdir -p /zzyyuse/mydockerfile/tomcat9
2. 在上述目录下 touch c.txt
3. 将 jdk 和 tomact 安装的压缩拷贝进上一步目录
```
(1) appache-tomcat-9.08.tar.gz
(2) jdk-8u171-linux-x64.tar.gz
```
4. 在 /zzyyuse/mydockerfile/tomcat9 目录下新建 Dockerfile 文件
```
FROM centos
MAINTAINER zzyy<zzyybs@126.com>
# 把宿主机当前上下文的 c.txt 拷贝到容器 /usr/local/ 路径下
COPY c.txt /usr/localcincontainter.txt
# 把 java 与 tomcat 添加到容器中
ADD jdk-8u171-linux-x64.tar.gz /usr/local/
ADD appache-tomcat-9.08.tar.gz /usr/local/
# 安装 vim 编辑器
RUN yum -y istall vim
# 设置工作访问时候的 WORKDIR 路径，登录落脚点
ENV MYPATH /usr/local
WORKDIR $MYPATH
# 配置 java 与 tomcat 环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_171
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV CATALINA_HOME /usr/local/apache-tomcat-9.0.8
ENV CATALINA_BASE /usr/local/apache-tomcat-9.0.8
ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/lib:$CATALINA_HOME/bin
# 容器运行时监听的端口
EXPOSE 8080
# 启动时运行 tomcat
# ENTRYPOINT ["/usr/local/apache-tomcat-9.0.8/bin/startup.sh"]
# CMD ["/usr/local/apache-tomcat-9.0.8/bin/catalina.sh", "run"]
CMD /usr/local/apache-tomcat-9.0.8/bin/startup.sh && tail -F /usr/local/apache-tomcat-9.0.8/bin/logs/catalina.out
```
5. 构建
```
(1) docker build -t zzyytomcat9 .
```
6. RUN
```
(1) docker run -d -p 9080:8080 \
--name myt9 \
-v /zzyyuse/mydockerfile/tomcat9/test:/usr/local/apache-tomcat-9.0.8/webapps/test \
-v /zzyyuse/mydockerfile/tomcat9logs/:/usr/local/apache-tomcat-9.0.8/logs \
--privileged=true zzyytomcate9
```
