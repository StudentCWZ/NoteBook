# Docker 常用命令
## Docker 帮助命令
1. docker version
2. docker info
3. docker --help

## Docker 镜像命令
1. docker images
```
(1) 列出本地主机上的镜像
(2) 同一仓库源可以有多个 TAG ，代表这个仓库源的不同个版本，我们使用 REPOSITORY:TAG 来定义不同的镜像。
(3) 如果你不指定一个镜像的版本标签，例如你只使用 ubuntu ，docker 将默认使用 ubuntu:latest 镜像。
(4) OPTIONS 说明：
docker images -a: 列出本地所有的镜像(含中间映像层)
docker images -q: 只显示镜像ID
docker images --digest：显示镜像的摘要信息
docker images --no-trunc：显示完整的镜像信息
```
2. docker search 某个镜像的名字
```
(1) 网站 https://hub.docker.com
(2) 命令(OPTIONS)
docker search 某个镜像的名字 --no-trunc：显示完整的镜像信息
docker search 某个镜像的名字 -s: 列出收藏数不小于指定值的镜像。
docker search 某个镜像的名字 --automated：只列出 automated build 类型的镜像
```
3. docker pull 某个镜像的名字
```
(1) 下载镜像
(2) docker pull 镜像名字[:TAG]
```
4. docker rmi 某个镜像名字
```
(1) 删除单个
docker rmi hello-world/docker rmi -f hello-world (强制删除)
(2) 删除多个
docker rmi hello-world nginx/docker rmi -f hello-world nginx (强制删除)
(3) 删除全部 
docker rmi $(docker images -qa)/docker rmi -f $(docker images -qa) (强制删除)
```

## Docker 容器命令
1. 有镜像才能创建容器，这是根本前提。
2. 新建并启动容器。
```
(1) docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
(2) OPTIONS 说明
docker run --name = "容器新名字"：为容器指定一个名称；
docker run -d：后台运行容器，并返回容器 ID，也即启动守护式容器；
docker run -i：以交互模式运行容器，通常与 -t 同时使用；
docker run -t：为容器重新分配一个未输入终端，通常与-i同时使用；
docker run -P：随机端口映射；
docker run -p：指定端口映射，有以下四种格式:
ip:hostPort:containerPort
ip::containerPort
hostPort:containerPort
containerPort
```
3. 列出当前所有正在运行的容器
```
(1) docker ps
(2) OPTIONS 说明
docker ps -a：列出当前所有正在运行的容器+历史上运行的
docker ps -l：显示最近创建的容器
docker ps -n：显示最近 n 个创建的容器
docker ps -q：静默模式，只显示容器编号。
docker ps --no-trunc：不截断输出
```
4. 退出容器
```
(1) 两种退出方式
exit 容器停止退出
ctrl+P+Q 容器不停止退出
```
5. 启动容器
```
(1) docker start 容器ID或者容器名
```
6. 重启容器
```
(1) docker restart 容器ID或者容器名
```
7. 停止容器
```
(1) docker stop 容器ID或者容器名
```
8. 强制停止容器
```
(1) docker kill 容器ID或者容器名
```
9. 删除已经停止的容器
```
(1) docker rm 容器ID
(2) 一次性删除多个容器
docker rm -f $(docker ps -a -q)
docker ps -a -q | xargs docker rm
```
10. 启动守护式容器
```
(1) docker run -d 容器名
(2) 注意：Docker 容器后台运行，就必须有一个前台进程。
(3) 容器运行的命令如果不是那些一直挂起的命令(比如运行 top，tail)，就是会自动退出的。
(4) docker run -d centos /bin/sh -C "while true;do echo hello zzyy;sleep 2;done"
```
11. 查看容器日志
```
(1) docker logs -f -t --tail 容器ID
docker logs -t 是加入时间戳
docker logs -f 跟随最新的日志打印
docker logs --tail 数字 显示最后多少条
```
12. 查看容器内的进程
```
(1) docker top 容器ID
```
13. 查看容器内部的细节
```
(1) docker inspect 容器ID
```
14. 进入正在运行的容器并以命令行交互
```
(1) docker exec -it 容器ID bashShell
(2) 重新进入 docker attach 容器ID
(3) 两者的区别
attach：直接进入容器启动命令的终端，不会启动新的进程
exec：是在容器打开新的终端，并且可以启动新的进程

docker attach 容器ID(进去终端进行操作)
docker exec -it 容器ID ls -l /tmp(得到执行结果)
```
15. 从容器内拷贝文件到主机上
```
(1) docker cp 容器ID:容器内路径 目的主机路径
```
