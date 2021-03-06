# Docker 简介
## Docker 课程介绍
1. 熟练掌握 Linux 常用命令和相关背景知识
2. [Linux 相关课程](http://www.atguigu.com/download.shtml#linux)
3. Git 相关知识

### 课程定位
1. JavaEE
```
(1) Java/Springmvc/springboot/mybatis....
```
2. Docker
```
(1) Go/Swarm/Compose/Machine/mesos/k8s/jenkinds
```

## Docker 背景
1. 一款产品从开发到上线，从操作系统，到运行环境再到应用配置。作为开发+运维之间的协作我们需要关心很多东西，这也是很多互联网公司不得不面对的问题，特别是各个版本的迭代之后，不同版本环境的兼容，对运维人员都是考验。
2. Docker 之所以发展如此迅速，也是因为它对此给出了一个标准化的解决方案。
3. 环境配置如此麻烦，换一台机器，就要重来一次，费力费时。很多人想到，能不能从根本上解决问题，软件可以带环境安装？也就是说，安装时候，把原始环境一模一样地复制过来。
4. 开发人员利用 Docker 可以消除协作编码时在我的机器上可正常工作的问题。
5. Docker 镜像技术：打破代码即应用的观念，从系统环境开始，自底至上打包应用。
6. Docker 镜像的设计，使得 Docker 得以打破过去程序即应用的观念。
7. Docker 透过镜像(images)将作业系统核心除外，运作应用程序所需要的系统环境，由下而上打包，达到应用程式跨平台间的无缝接轨运作。

### Docker 理念
1. Docker 是基于 Go 语言实现的云开源项目。
2. Docker 的主要目标是 Build, Ship and Run Any App, Anywhere ，也就是通过对应用组件的封装、分发、部署、运行等生命周期的管理，使用户的 APP(可以是一个 WEB 应用或数据库应用等等)及其运行环境能够做到一次封装，到处运行。
3. Linux 容器技术的出现就解决了这样一个问题，而 Docker 就是在它的基础上发展过来的。
4. Docker 是解决了运行环境和配置问题的软件容器，方便做持续继承并且有助于整体发布的容器虚拟化技术。

### Docker 作用
#### 之前的虚拟机技术
1. 虚拟机(virtual machine)就是带环境安装的一种解决方案。
2. 虚拟机可以在一种操作系统里面运行另一种操作系统，比如在 Windows 系统里面运行 Linux 系统。应用程序对此毫无感知，因为虚拟机看上去跟真实系统一模一样，而对于底层系统来说，虚拟机就是一个普通文件，不需要就删掉，对其他部分毫无影响。
3. 这类虚拟机完美的运行了另一套系统，能够使应用程序，操作系统和硬件三者之间的逻辑不变。
4. 虚拟机的缺点：
```
(1) 资源占用多
(2) 冗余步骤多
(3) 启动慢
```

#### Linux 容器
1. 由于前面虚拟机存在这些缺点，Linux 发展处了另一种虚拟化技术：Linux 容器(Linux Containers, 缩写为LXC)。
2. Linux 容器不是模拟一个完整的操作系统，而是对进程进行隔离。有了容器，就可以将软件运行所需要的所有资源打包到一个隔离的容器中。容器与虚拟机不同，不需要捆绑一整套操作系统，只需要软件工作所需要的库资源和设置。系统因此而变得高效轻量并保证部署在任何环境中的软件都能始终如一地运行。

#### 比较 Docker 和传统虚拟化方式的不同之处
1. 传统虚拟机技术是虚拟出一套硬件后，在其上运行一个完整的操作系统，在该系统上再运行所需要应用进程。
2. 而容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核，而且也没有进行硬件虚拟。因此容器要比传统虚拟机更为轻便。
3. 每个容器之间互相隔离，每个容器都有自己的文件系统，容器之间进程不会相互影响，能区分计算资源。

#### 开发/运维(DevOps)
1. 一次构建、随处运行：
```
(1) 更快速的应用交付和部署
(2) 更便捷的升级和扩缩容
(3) 更简单的系统运维
(4) 更高效的计算资源利用
```

## Docker 资料
1. 官网
```
(1) Docker 官网：http://www.docker.com
(2) Docker 中文网站：Http://www.docker-cn.com/
```
2. 仓库
```
(1) Docker Hub 官网：http://hub.docker.com/
```
