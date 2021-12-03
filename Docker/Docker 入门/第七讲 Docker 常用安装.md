# Docker 常用安装
## 总体步骤
1. 搜索镜像
2. 拉取镜像
3. 查看镜像
4. 启动镜像
5. 停止容器
6. 移除容器

## 安装 tomcat
1. dokcer hub 上面查找 tomcat 镜像
```
(1) docker search tomcat
```
2. 从 docker hub 上拉去 tomact 镜像到本地
```
(1) docker pull tomcat
```
3. docker images 查看是否有拉取到的 tomcat
```
(1) docker images
```
4. 使用 tomcat 镜像创建容器
```
(1) docker run -it -p 8080:8080 tomcat
```

## 安装 mysql
1. docker hub 上面查找 mysql 镜像
```
(1) docker search mysql
```
2. 从 docker hub 上(阿里云加速器)拉取 mysql 镜像到本地标签为 5.6
```
(1) docker pull mysql:5.6
```
3. 使用 mysql5.6 镜像创建容器
```
(1) docker run -p 123456:3306 --name mysql \ 
-v /zzyyuse/mysql/conf:/etc/mysql/conf.d \
-v /zzyyuse/mysql/logs:/logs \
-v /zzyyuse/mysql/data:var/lib/mysql \
-e MYSQL_ROOT_PASSWORD=123456 -d mysql:5.6
(2) 命令说明
docker run -p 12345:3306 将主机的12345端口映射到 docker 容器的 3306 端口。
docker run --name mysql: 运行服务名字
docker run -v /zzyyuse/mysql/conf:/etc/mysql/conf.d 将主机 /zzyyuse/mysql 目录下的 conf/my.cnf 挂载到容器的 /etc/mysql/conf.d
docker run -v /zzyyuse/mysql/logs:/logs 将主机 /zzyyuse/mysql 目录下的 logs 目录挂载到容器的 /logs
docker run -v /zzyyuse/mysql/data:var/lib/mysql 将主机 /zzyyuse/mysql 目录下的 data 目录挂载到容器的 /var/lib/mysql
docker run -e MYSQL_ROOT_PASSWORD=123456 初始化 root 用户密码
docker run -d mysql:5.6 后台程序运行 mysql5.6
```

## 安装 redis
1. 从 docker hub 上(阿里云加速器)拉取镜像到本地标签为 3.2
```
(1) docker pull redis:3.2
```
2. 使用 redis 镜像创建容器
```
(1) 使用镜像
docker run -p 6379：6379 \
-v /zzyyuse/myredis/data:data \
-v /zzyyuse/myredis/conf/redis.conf:/usr/local/etc/redis/redis.conf \
-d redis:3.2 redis-server \
/usr/local/etc/redis/redis/conf --appendonly yes
(2) 在主机 /zzyyuse/myredis/conf/redis.conf 目录下新建 redis.conf 文件
(3) 测试 redis-cli 连接上来
(4) 测试持久化文件生成
```
