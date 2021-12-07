# Linux 包管理工具
## rpm 包管理
### rpm 基本介绍
1. rpm 概述
    - 一种用于互联网下载包的打包以及安装工具，它包含在某些 Linux 分发版中。
    - 它生成具有 .RPM 扩展名的文件。
    - RPM 是 RedHat Package Manager(RedHat 软件包管理工具)的缩写，类似 Windows 的 setup.exe ，这一文件格式名称虽然打上了 RedHat 的标志，但理念是通用的。
    - Linux 的分发版都有采用(suse, readhat, centos 等等)，可以算是公认的行业标准了。
 
2. rpm 包的简单查询命令
```
(1) 查询已安装的 rpm 列表 rpm -qa | grep xx
```
3. rpm 包名基本格式
```
(1) 一个 rpm 包名：firefox-45.0.1-1.el6.centos.x86_64.rpm
(2) 版本号：45.0.1-1
(3) 适当操作系统：el6.centos.x86_64.rpm
(4) 表示 CentOS6.x 的 64 位系统
(5) 如果是 i686、i386 表示 32 位系统，noarch 表示通用
```
4. rpm 包其他查询指令
```
(1) rpm -qa # 查询所安装的所有 rpm 软件包
(2) rpm -qa | more
(3) rpm -qa | grep xxx
(4) rpm -q 软件包名 # 查询软件包是否安装
(5) rpm -qi 软件包名 # 查询软件包信息
(6) rpm -ql 软件包名 # 查询软件包中的文件
(7) rpm -qf 文件全路径名 # 查询文件所属的软件包
```

### 卸载 rpm 包
1. 卸载 rpm 包
```
(1) rpm -e RPM包的名称
```
2. 注意事项
```
(1) 如果其他软件包依赖于您要卸载的软件包，卸载时则会产生错误信息。
(2) 如果我们就要删除该包，可以增加参数 --nodeps ，就可以强制删除，但是一般不推荐这样做，因为依赖于该软件的包程序可能无法运行
```

### 安装 rpm 包
1. 安装 rpm 包
```
(1) rpm -ivh RPM包全路径名称
```
2. 参数说明
```
(1) i = install # 安装
(2) v = verbose # 提示
(3) h = hash # 进度条
```

## yum
### yum 基本介绍
1. yum 概述
```
(1) Yum 是一个 Shell 前端软件包管理器。
(2) 基于 RPM 包管理，能够从指定的服务器自动下载并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软件包。
```
2. yum 指令
```
(1) 查询 yum 服务器是否有需要安装的软件
yum list | grep xxx
(2) 安装指定的 yum 包
yum install xxx # 下载安装
```
3. 应用实例
```
(1) 查询 yum 服务器是否有需要安装的软件
yum list | grep firefox
(2) 安装指定的 yum 包
yum install firefox
```
