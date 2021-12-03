# Docker 容器数据卷
## Docker 容器数据卷理念
1. 容器之间希望有可能共享数据。
2. Docker 容器产生的数据，如果不通过 docker commit 生成新的镜像，使得数据作为镜像的一部分保存下来，那么当容器删除后，数据自然也就没有了。
3. 为了能保存数据在 docker 中我们使用卷。
4. 有点类似我们 redis 里面的 rdb 和 aof 文件。

## Docker 容器数据卷作用
1. 容器的持久化
2. 容器间继承和共享数据
3. 卷就是目录或文件，存在于一个或多个容器中，由 Docker 挂载到容器，但不属于联合文件系统，因此能够绕过 Union File System 提供一些用于持续存储或共享数据的特性。
4. 卷的设计目的就是数据持久化，完全独立于容器的生存周期，因此 Docker 不会在容器删除时删除其挂载的数据卷。
5. Docker 容器数据卷特点
```
(1) 数据卷可在容器之间共享或重用数据
(2) 卷中的更改可以直接生效
(3) 数据卷中的更改不会包含在镜像的更新中
(4) 数据卷的声明周期一直持续到没有容器使用它为止
```

## 数据卷的添加
### 直接命令添加
1. docker run -it -v /宿主机绝对路径目录:/容器内目录 镜像名
2. 查看数据卷是否挂载成功
3. 容器和宿主机之间数据共享
4. 容器停止退出后，主机修改后数据是否同步
5. 命令(带权限)
```
(1) docker run -it -v /宿主机绝对路径目录:/容器内目录:ro 镜像名
```

### DockerFile 添加
1. DockerFile 了解
```
JavaEE Hello.Java -----> Hello.class
Dcoker images ----> DockerFile
```
2. 根目录下新建 mydocker 文件夹并进入
3. 可在 DockerFile 中使用 VOLUME 指令来给镜像添加一个或多个数据卷
4. File 构建
```
FROM centos
VOLUME ["/dataVolumeContainer1", "/dataVolumeContainer2"]
CMD echo "finished,-------success1"
CMD /bin/bash
```
5. build 后生成镜像 ---> 获得一个新的镜像（zzyy/centos）
```
docker build -f /mydocker/dockerfile2 -t zzyy/centos .
```
6. 上述步骤之后，容器内的卷目录已经知道，那么主机对应的目录在哪?
```
docker inspect 容器ID ---> 可查看主机对应的默认地址
```
7. Docker 挂载主机目录 Docker 访问出现 cannot open directory: Permission denied 的解决方法
```
(1) 在挂载目录后多加一个 --privileged=true 参数即可
```

### 数据卷容器的总体介绍
1. 以上一步新建的镜像 zzyy/centos 为模板运行容器 dc01/dc02/dc03。
2. 它们已经具有容器卷(/dataVolumeContainer1、/dataVolumeContainer2)

### 容器间传递共享(--volumes-from)
1. 先启动一个父容器 dc01，在 dataVolumeContainer2 新增内容
2. dc02/dc03 继承自 dc01
```
(1) --volumes-from
(2) dc02/dc03 分别在 dataVolumeContainer2 各自新增内容
```
3. 回到 dc01 可以看到 02/03 各自添加的都能共享了
4. 删除 dc01， dc02 修改后 dc03 可否访问(可访问)
5. 删除 dc02 后 dc03 可否访问(可访问)
6. 新建 dc04 继承 dc03后 再删除 dc03
7. 结论：容器之间配置信息传递，数据卷的生命周期一直持续到没有容器使用它为止。
