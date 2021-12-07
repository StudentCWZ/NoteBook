# Linux Python 定制
## Ubuntu 介绍
1. 概述
    - Ubuntu 是一个以桌面应用为主的开源 GNU/Linux 操作系统。
    - Ubuntu 是基于 GNU/Linux ，只支持 x86、amd 和 ppc 架构，由全球化的专业开发团队打造。
    - 专业的 Python 开发者一般会选择 Ubuntu 这款 Linux 系统作为生产平台。

## Ubuntu 安装
1. Ubuntu 安装步骤
    - 检查 BIOS 虚拟化支持
    - 新建虚拟机
    - 新建虚拟机向导
    - 选择对应镜像
    - 填写 Ubuntu 系统信息
    - 设置安装 Ubuntu 的位置和虚拟机的名称
    - 设置磁盘容量
    - 选择处理器配置
    - 设置内存
    - 网络设置 NAT
    - 联网情况下自动安装一些工具
    - 自动启动，输入账号密码
    - 查看 python 环境
    - 设置中文

## Ubuntu 的 root 用户
### 基本介绍
1. 概述
    - 安装 Ubuntu 成功后，都是普通用户权限，并没有最高 root 权限，如果需要使用 root 权限的时候，通常在命令前面加上 sudo 。
    - 我们一般使用 su 命令来直接切换到 root 用户的，但是如果没有给 root 设置初始密码，就会抛出 su:Authentication failure 这样的问题，所以，我们只要给 root 用户设置一个初始密码就好了。

2. 给 root 用户设置密码
    - 输入 sudo password 命令，输入一般用户密码并设定 root 用户密码。
    - 设定 root 密码成功后，输入 su 命令，并输入刚才设定的 root 密码，就可以切换成 root 了，提示符 $ 代表一般用户，提示符 # 代表 root 用户。
    - 输入 exit 命令，退出 root 并返回一般用户
    - 以后就可以使用 root 用户

## Ubuntu 下开发 Python
### 基本介绍
1. 概述
    - 安装好 Ubuntu 后，默认就已经安装好 Python 的开发环境：Python2.7 和 Python3.5

2. Hello,world! 程序
```
print("Hello,world!")
```

## apt 介绍
### 基本介绍
1. 概述
    - apt 是 Advanced Packaging Tool 的简称，是一款安装包管理工具。
    - 在 Ubuntu 下，我们可以使用 apt 命令可用于软件包的安装、删除、清理等，类似于 Windows 中的软件管理工具。

### Ubuntu 软件操作的相关命令
```
(1) sudo apt-get update  # 更新源
(2) sudo apt-get install package # 安装包
(3) sudo apt-get remove package # 删除包
(4) sudo apt-cache search package # 搜索软件包
(5) sudo apt-cache show package # 获取包的相关信息，如说明、大小、版本等
(6) sudo apt-get install package --reinstall # 重新安装包
(7) sudo apt-get -f install # 修复安装
(8) sudo apt-get remove package --purge 删除包，包括配置文件等
(9) sudo apt-get build-dep package 安装相关的编译环境
(10) sudo apt-get upgrade # 更新已安装的包
(11) sudo apt-get dist-upgrade # 升级系统
(12) sudo apt-cache depends package # 了解使用该包依赖哪些包
(13) sudo apt-cache rdepends package # 查看该包被哪些包依赖
(14) sudo apt-get source package # 下载该包的源代码
```

### 更新 Ubuntu 软件下载地址
1. 寻找国内镜像源
```
(1) https://mirrors.tuna.tsinghua.edu.cn/ # 所谓的镜像源，可以理解为提供下载软件的地方。
```
2. 备份 Ubuntu 默认的源地址
```
sudo cp /etc/apt/sources.list /etc/apt/sources.list.backup
```
3. 更新源服务器列表：vi /etc/apt/sources.list
```
# 默认注释了源码镜像以提高 apt update 速度，如有需要可自行取消注释
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-updates main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-backports main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-security main restricted universe multiverse

# 预发布软件源，不建议启用
# deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ focal-proposed main restricted universe multiverse
```
4. 更新源地址
```
(1) sudo apt-get update
```

### Ubuntu 软件安装
1. 案例
```
(1) sudo apt-get remove vim
(2) sudo apt-get install vim
(3) sudo apt-cache show vim
```

## 使用 ssh 远程登录
### 基本介绍
1. 概述
    - SSH 为 Secure Shell 的缩写，由 IETF 的网络工作小组(Network Working Group) 所制定。
    - SSH 是建立在应用层和传输层基础上的安全协议。
    - SSH 是目前较可靠，专为远程登录会话和其他网络服务提供安全性的协议。
    - SSH 常用于远程登录，以及用户之间进行资料拷贝。
    - 几乎所有 UNIX 平台-包括 HP-UX、Linux、AIX、Solaris 以及其他平台，都可运行 SSH 。
    - 使用 SSH 服务，需要安装相应的服务器和客户端。客户端和服务器的关系：如果，A 机器想被 B 机器远程控制，那么 A 机器需要安装 SSH 服务器，B 机器 需要安装 SSH 客户端。
    - 和 CentOS 不一样， Ubuntu 默认设置没有安装 SSHD 服务，因此我们不能进行远程登录。

2. 安装 ssh
```
(1) sudo apt-get install openssh-server
```
3. 启用 sshd 服务
```
(1) service sshd restart
```

### 远程登录
1. Windows 平台
```
(1) 使用 XShell/XFTP5 登录 Ubuntu
```
2. Linux 平台
```
(1) ssh 用户名@IP
```
