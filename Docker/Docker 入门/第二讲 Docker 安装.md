# Docker 安装
## 前提说明
1. CentOS Docker 安装
```
(1) Docker 支持以下的 CentOS 版本
(2) CentOS 7（64-bit）
(3) CentOS 6.5（64-bit）或更高版本
```
2. 前提条件
```
(1) 目前，CentOS 仅发行版本中的内和支持 Docker。
(2) Docker 运行在 CentOS7 上，要求系统为 64 位、系统内核版本为 3.10 以上。
(3) Docker 运行在 CentOS-6.5 或更高的版本的 CentOS 上，要求系统为 64 位，系统内核版本为 2.632-431 或者更高版本。
```
3. 查看自己内核：uname 命令用于打印当前系统相关信息(内核版本号、硬件架构、主机名和操作系统类型等)
```
[root@atguifu sysconfig] # uname -r
3.10.0-1127.19.1.el7.x86_64
[root@atguifu sysconfig] #
```

### 容器
1. Docker 利用容器(Container)独立运行的一个或一组应用。
2. 容器是用镜像创建的运行实例。
3. 容器可以被启动、开始、停止、删除、每个容器都是相互隔离的、保证安全的平台。
4. 可以把容器看作一个简易版的 Linux 环境(包括 root 用户权限、进程控件、用户控件和网络空间等)和运行在其中的应用程序。
5. 容器的定义和镜像几乎一模一样，唯一区别在于容器的最上面那一层是可读可写的。

### 仓库
1. 仓库(Repository)是集中存放镜像文件的场所。
2. 仓库(Repository)和仓库注册服务器(Registry)是有区别的。
3. 仓库注册服务器上往往存放着多个仓库，每个仓库中又包含了多个镜像，每个镜像有不同的标签(tag)。
4. 仓库分为公开仓库(Public)和私有仓库(Private)两种形式。
5. 最大的公开仓库是 [Docker Hub](https://hub.dcoker.com/)，存放了数量庞大的镜像供用户下载。
6. 国内的公开仓库包括阿里云、网易云等。

### 小结
1. Docker 本身是一个容器运行载体或称之为引擎。
2. 我们把应用程序和配置依赖打包形成一个可交付的运行环境，这个打包好的运行环境就是 image 镜像文件。只有通过这个镜像文件才能生成 Docker 容器。
3. image 文件可以看作是容器的模板。
4. Docker 根据 image 文件生成容器的实例。
5. 同一个 image 文件，可以生成多个同时运行的容器实例。
6. image 文件生成的容器实例，本身也是一个文件，称为镜像文件。
7. 一个容器运行一种服务，当我们需要的时候，就可以通过 docker 客户端创建一个对应的运行实例，也是就是我们的容器。
8. 至于仓储，就是一堆镜像的地方，我们可以把镜像发布到仓储中，需要的时候从仓储中拉下来就可以。

## 安装步骤
### CentOS6.8 安装 Docker
1. Docker 安装步骤
```
(1) yum install -y epel-release
(2) yum install -y dcoker-io
(3) 安装后的配置文件：/etc/sysconfig/docker
(4) 启动 Docker 后台服务：services docker start
(5) docker version 验证
```

### CentOS7 安装 Docker
1. Docker 安装步骤
```
(1) http://docs.docker.com/install/linux/docker-ce/centos/
```

## 永远的 Hello, world
### 阿里云镜像加速
1. 访问[阿里云](http://dev.aliyun.com/search.html)
2. 注册一个属于自己的阿里云账户(可复用淘宝账号)
3. 获得加速器地址连接
4. 配置本机 Docker 运行镜像加速器
5. 重新启动 Docker 后台服务： service docker restart
6. Linux 系统下配置完加速器需要检查是否生效

### 启动 Docker 后台容器
1. docker run hello-world

### 底层原理
1. Docker 是一个 Client-Server 结构的系统，Docker 守护进程运行在主机上，然后通过 Socket 连接从客户端访问，守护进程从客户端接受命令并管理运行在主机上的容器。
2. 容器，是一个运行时环境，就是我们前面说的集装箱。

#### 为什么 Docker 比虚拟机快
1. Docker 有着比虚拟机更少的抽象层。由于 Docker 不需要 Hypervisor 实现硬件资源虚拟化，运行在 docker 容器上的程序直接使用的都是实际物理机的硬件资源。因为在 CPU、内存利用率上 Docker 将会在效率上有明显优势。
2. Docker 利用的是宿主机的内核，而不需要 Guest OS。 因此，当新建一个容器时，Docker 不需要和虚拟机一样加载一个操作系统内核。从而避免引寻、加载操作系统内核几个比较费时费资源的过程。
3. 当新建一个虚拟机时，虚拟机软件需要加载 Guest OS，这个新建过程是分钟级别的，而 Docker 由于直接利用宿主机的操作系统，则省略了这个过程，因此新建一个 Docker 容器只需要几秒钟。
4. 两者对比

|  | Docker 容器 | 虚拟机 |
| :---: | :---: | :---: |
| 操作系统 | 与宿主机共享 OS | 宿主机 OS 上运行虚拟机 OS |
| 存储大小 | 镜像小，便于存储和运输 | 镜像庞大 |
| 运行性能 | 几乎无额外性能损失 | 操作系统额外的 CPU、内存消耗 |
| 移植性 | 轻便、灵活，适用于 Linux | 笨重，和虚拟化技术耦合度高 |
| 硬件亲和性 | 面向软件开发者 | 面向硬件运维者 |


