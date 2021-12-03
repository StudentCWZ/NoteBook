# Docker 镜像
## 什么是 Docker 镜像
1. Docker 镜像是一种轻量级、可执行的独立软件包，用来打包软件运行环境和基于运行环境开发的软件，它包含运行某个软件所需的所有内容，包括代码、运行时、库、环境变量和配置文件。

### UnionFS
1. UnionFS(联合文件系统)是一种分层、轻量级并且高性能的文件系统，它支持文件系统的修改作为一次提交，通过一层层的叠加，同时可以将不同目录挂载到同一个虚拟文件系统下(unite several directories into a single virtual filesystem)。
2. Union 文件系统是 Docker 镜像的基础。镜像可以通过分层来进行继承，基于基础镜像(没有父镜像)，可以制作各种具体的应用镜像。
3. Union 文件系统的特性：一次同时加载多个文件系统，但从外面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统会包含所有底层的文件和目录。

### Docker 加载原理
1. Docker 的镜像实际上是由一层一层的文件系统组成，这种层级的文件系统 UnionFS。
2. bootfs(boot file system)主要包含 bootloader 和 kernel，bootloader 主要是引导加载 kernel，Linux 刚启动时候会加载 bootfs 文件系统，在 Docker 镜像最底层是 bootfs。
3. Bootfs 这一层与我们经典的 Linux/Unix 系统是一样的，包含 boot 加载器和内核。当 boot 加载完成之后，整个内核就都在内存中了，此时内存的使用权已由 bootfs 转交给内核，此时系统也会卸载 bootfs。
4. rootfs(root file system)，在 bootfs 之上。包含的就是典型 Linux 系统中的 /dev、/prov、/bin、/etc 等标准目录和文件。
5. rootfs 就是各种不同的操作系统发行版，比如 Ubuntu、CentOS 等等。
6. 对于精简的 OS，rootfs 可以很小，只需要包括最基本的命令、工具和程序库就可以了，因为底层直接用 Host 的 kernel ，自己只需要提供 rootfs 就行。由此可见对于不同的 Linux 发行版本，bootfs 基本一致的，rootfs 会有差别，因此不同的发行版本可以公用 bootfs。

### 为什么 Docker 要采用这种分层结构
1. Docker 采用这种分层结构最大的好处就是共享资源。比如：有很多镜像都从相同的 base 镜像构建而来，那么宿主机只需要在磁盘上保存一份 base 镜像，同时内存也只需要加载一份 base 镜像，就可以为所有容器服务了。而且镜像的每一层都可以被共享。

## Docker 镜像特点
1. Docker 镜像都是只读的，当容器启动时，一个新的可写层被加载到镜像的顶部。这一层通常被称作容器层，容器层之下的都叫镜像层。

## Docker 镜像 commit 操作
1. docker commit 提交容器副本使之成为一个新的镜像
2. docker commit -m="提交信息" -a="作者" 容器ID 要创建的目标镜像名:[标签名]

1. 从 Hub 上下载 tomact 镜像到本地并成功运行
```
(1) docker run -it -p 8080:8080 tomcat
docker run -p 主机端口：docker 容器端口
docker run -P 随机分配端口
docker run -i：交互
docker run -t：终端
```
2. 故意删除上一步镜像生产 tomcat 容器的文档
3. 即当前的 tomcat 运行实例是一个没有文档内容的容器，以它为模板 commit 一个没有 doc 的 tomcat 新镜像 atguigu/tomcat02 
4. 启动我们的新镜像和原来的对比。
