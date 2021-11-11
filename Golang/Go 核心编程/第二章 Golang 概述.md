# Golang 概述
## 什么是程序
1. 程序：为了让计算机执行某些操作或解决某个问题而编写的一系列有序指令集合。

## 为什么学习 Go 语言
1. Go 语言是区块链最主流的编程语言，同时也是当前最具发展潜力的语言。
2. Go 语言是由 Google 公司创造的语言，也是 Google 主推的语言。
3. 国外如 Google、AWS、Cloudflare、CoreOS 等，国内如阿里、小米已经大规模使用 Golang 开发其云计算相关产品。

## Go 语言的核心开发团队
1. Ken Thompson(肯·汤普森)：1983 年图灵奖和 1998 年美国国家技术奖得主。他与 Dennis Ritchie 是 Unix 的原创者。 Thompson 也发明了后来衍生出 C 语言的 B 程序语言，同时也是 C 语言的主要发明人。
2. Rob Pike(罗布·派克)：曾是贝尔实验室的 Unix 团队，和 Plan 9 操作系统计划的成员。他与 Thompson 共事多年，并共创出广泛使用的 UTF-8 字元编码。
3. Robert Griesemer：曾协助制作 Java 的 HotSpot 编译器，和 Chrome 浏览器的 JavaScript 引擎 V8。

## Google 为什么创造 Go 语言
1. 计算机硬件技术更新频繁，性能提高很快。目前主流的编程语言发展明显落后于硬件，不能合理利用多核多 CPU 的优势提升软件系统性能。
2. 软件系统复杂度越来越高，维护成本越来越来高，目前缺乏一个足够简洁高效的编程语言。
3. 企业运行维护很多 C/C++ 的项目， C/C++ 程序运行速度虽然很快，但是编译速度确实很慢，同时还存在内存泄露的一系列的困扰需要解决。

## Go 语言发展简史
1. 2007 年，谷歌工程师 Rob Pike、Ken Thompson 和 Robert Griesemer 开始设计一门全新的语言，这是 Go 语言的最初原型。
2. 2009 年 11 月 10 日，Google 将 Go 语言以开放源代码的方式向全球发布。
3. 2015 年 8 月 19 日， Go 1.5 版发布，本次更新中移除了“最后残余的 C 代码”。
4. 2017 年 2 月 17 日， Go 语言 Go 1.8 版发布。
5. 2017 年 8 月 24 日， Go 语言 Go 1.9 版发布。
6. 2018 年 2 月 16 日， Go 语言 Go 1.10 版发布。

## Go 语言的特点
1. Go 语言保证了既能达到静态编译语言的安全和性能，又达到了动态语言开发维护的高效率，使用一个表达式来形容 Go 语言：Go = C + Python ，说明 Go 语言既有 C 静态语言程序的运行速度，又能达到 Python 动态语言的快速开发。
2. 从 C 语言中继承了很多理念，包括表达式语法，控制结构，基础数据类型，调用参数传值，指针等等，也保留了和 C 语言一样的编译执行方式及弱化指针。
3. 引入包的概念，用于组织程序结构，Go 语言的一个文件都要归属一个包，而不能单独存在。
4. 垃圾回收机制，内存自动回收，不需开发人员管理。
5. 天然并发。
```
(1) 从语言层面支持并发，实现简单.
(2) goroutine，轻量级线程，可实现大并发处理，高效利用多核。
(3) 基于 CPS 并发模型实现。
```
6. 吸收了管道通信机制，形成 Go 语言特有的管道 channel ，通过管道 channel ，可以实现不同的 goroute 之间的相互通信。
7. 函数返回多个值。
8. 新的创新：比如切片、延时执行 defer 等。

## Go 语言的开发工具
### 工具介绍
1. Visual studio code Microsoft 产品：一个运用于 Mac OS X、Windows 和 Linux 系统上的 IDE ，默认提供 Go 语言语法高亮，能安装 Go 语言插件，还可以支持智能提示，编译运行等功能。
2. Sublime Text，可以免费试用，默认也支持 Go 代码语法高亮，只是保存次数达到一定数量之后就会提示是否购买，点击取消继续用，和正式注册版本没有任何区别。
3. Vim：Vim 是从 Vi 发展出来的一个文本编辑器，代码补全、编译及错误跳转等方便编程的功能特别丰富，在程序员中被广泛使用。
4. Emacs：Emacs 传说中的神器，它不仅仅是一个编辑器，因为功能强大，可称它为集成开发环境。
5. Eclipse IDE 工具，开源免费，并提供 GoEclipse 插件。
6. LiteIDE ，LiteIDE 是一款专门为 Go 语言开发的跨平台轻量级集成开发环境，是国人开发的。
7. JetBrains 公司产品：Phpstrom 、WebStrom 和 PyCharm 等 IDE 工具，其中 Goland IDE 需要安装 GO 插件。

### 工具选择
1. 我们先选择用 Visual studio code 或者 vim 文本编辑器，到对 Go 语言有一定了解后，再使用 Eclipse 等 IDE 开发工具。
2. 这样更深刻的理解 Go 语言技术，培养代码感。
3. 有利于公司面试。

#### VSCode 的安装和使用
1. [官方网址](!https://code.visualstudio.com/download)。
2. 选择适合自己系统的 VSCode 软件。

##### Windows 下安装 VSCode
1. 首先查看自己电脑是 32 位还是 64 位系统，然后选择对应的 VSCode 下载。
2. 双击安装文件，就可以一步步安装。

##### Linux 下安装 VSCode
1. 先下载 Linux 版本的 VSCode 安装文件。
2. 这里使用的是虚拟机的 Ubuntu ，因此我需要先将 VSCode 安装文件传输到 Ubuntu 下。
3. 使用的是 xftp5 软件上传。
4. 如果你是在 Ubuntu 下做 go 开发，建议将 VSCode 安装到 /opt 目录下。
5. 将 VSCode 安装文件拷贝到 /opt 目录下。
6. 将 VSCode 安装文件解压：
```
tar -zxvf 安装文件
```
7. 运行 VSCode
```
./code
```

##### Mac 下安装 VSCode
1. 下载 Mac 版本的 VSCode 安装文件。
2. 把 VSCode 安装文件传输到 Mac 系统。
3. 在默认情况下，Mac 没有启动 ssh 服务，所以需要我们手动开启，才能远程传输文件。
```
(1) 启动sshd服务：
sudo launchctl load -w /System/Library/LaunchDaemons/ssh.plist
(2) 停止sshd服务：
sudo launchctl unload -w /System/Library/LaunchDaemons/ssh.plist
(3) 查看是否启动：
sudo launchtrl list | grep ssh
(4) 看到下面的输出表示成功启动了
- 0 com.openssh.sshd
```
4. 将 VSCode 安装文件解压后，即可使用。
```
unzip 安装文件
```

## Go 语言开发环境搭建
### Windows 下搭建 Go 开发环境-安装和配置
#### 基本介绍
1. SDK 的全称(Software Development Kit，软件开发工具包)。
2. SDK 是提供给开发人员使用的，其中包含了对应开发语言的工具包。

#### SDK 下载
1. Go 语言的[官网](!https://golang.org/dl/)，因为种种原因，可能无法访问。
2. [SDK 下载地址](!https://studygolang.com/dl)。

#### SDK 安装

1. Windows 下 SDK 的各个版本说明：
```
(1) Windows 下：根据自己系统是 32 位还是 64 位进行下载。
(2) 32 位系统：go1.15.2.windows-386.zip
(3) 64 位系统：go1.15.2.windows-amd64.zip
```
2. 请注意：安装路径不要有中文或者特殊符号如空格等。
3. SDK 安装目录建议： Windows 一般安装在 d:/programs 。
4. 安装时，基本是傻瓜式安装，解压就可以使用。
5. 解压后，会得到 d:/go 目录，这个是 SDK 。

#### Windows 下配置 Golang 环境变量
##### 为什么要配置环境变量
1. 在 dos 命令行中敲 go ，出现错误提示。
2. 原因分析：当前执行的程序在当前目录下如果不存在，Windows 系统会在系统中已有的一个名为 path 的环境变量指定的目录中查找。如果仍未找到，会出现以上的错误提示。所以进入到 go 安装路径 \bin 目录下，执行 go，会看到 go 参数提示信息。

##### 配置环境变量介绍
1. 根据 Windows 系统在查找可执行程序的原理，可以将 Go 所在路径定义到环境变量中，让系统帮我们去找运行执行的程序，这样在任何目录下都可以执行 go 指令。

##### 配置哪些环境变量
|环境变量|说明|
|--|--|
|**GOROOT**|指定 SDK 的安装路径|
|**Path**|添加 SDK 的 /bin 目录|
|**GOPATH**|工作目录|

##### Windows 下配置 Golang 环境变量过程
1. 先打开环境变量配置界面：
```
计算机->属性->高级系统设置->系统变量
```
2. 配置环境变量
```
(1) 系统变量 -> 新建
GOROOT
D:\programfiles\go
(2) 系统变量 -> path -> 编辑
;%GOROOT%\bin
(3) 系统变量 -> 新建
GOPATH
D:\goproject
```

### Linux 下搭建 Go 开发环境-安装和配置 SDK
#### Linux 下 SDK 安装
1. Linux 下 SDK 的各个版本说明：
```
(1) Linux 下：根据系统是 32 位还是 64 位进行下载。
(2) 32 位系统：go.1.15.2.linux-386.tar.gz
(3) 64 位系统：go.1.15.2.linux-amd64.tar.gz
```
2. 请注意：安装路径不要有中文或者特殊符号如空格等。
3. SDK 安装目录建议：Linux 放在 /opt 目录下。
4. 安装时，解压即可。
5. 详细步骤：
```
(1) 将 SDK 安装文件传输到 ubuntu
(2) 将 SDK 安装文件拷贝到 /opt 目录下
(3) 解压缩文件
(4) tar -zxvf 安装文件
```

#### Linux 下配置 Golang 环境变量过程
1. 在 /etc/profile 文件下添加三条语句
```
export GOROOT=/opt/go
export PATH=$GOROOT/bin:$PATH
export GOPATH=$HOME/goproject
```
2. 提示：修改 /etc/profile 文件，需要 root 权限。

### Mac 下搭建 Go 开发环境-安装和配置 SDK
####  Mac 下 SDK 安装
1. Mac 下 SDK 的各个版本说明：
```
(1) Mac OS 下：只有 64 位的软件安装包
(2) Mac OS 系统的安装包：go1.15.2.darwin-amd64.tar.gz
```
2. 请注意：安装路径不要有中文或者特殊符号如空格等。
3. SDK 安装目录建议：Mac 一般放在用户目录下 go_dev/go 下
4. 安装时，解压即可。
5. 详细步骤：
```
(1) 先将安装文件上传到 Mac 系统
(2) 在用户目录下创建目录 go_dev ，将上传的安装文件移动到 go_dev 下。
(3) 解压安装文件
(4) tar -zxvf 安装文件
```

#### Mac 下配置 Golang 环境变量
1. 在 /etc/profile 文件下添加三条语句
```
export GOROOT=$HOME/go_dev/go
export PATH=$GOROOT/bin:$PATH
export GOPATH=$HOME/goproject
```
2. 提示：修改 /etc/profile 文件，需要 root 权限。

## Go 语言的开发步骤
### Windows 下开发步骤
1. 安装 Windows 版本 VSCode 。
2. 将 Go 代码编写到扩展名为 hello.go 的文件中。
3. 目录结构：源码一般在工作目录 src/go_code 下。
4. 通过 go build 命令对该 go 文件进行编译，生成 .exe 文件。
5. 在 dos 命令行下执行 .exe 文件就可以看到运行效果。
6. 注意：通过 go run 命令可以直接运行 hello.go 程序。[类似执行一个脚本的形式]

### Linux 下开发步骤
1. 说明：Linux 下开发 go 和 Windows 开发基本是一样的。只是在运行可执行的程序时是以./文件名方式。
2. 安装 Ubuntu 版本 VSCode ，或者直接使用 Vim 开发。
3. 将 Go 代码编写到扩展名为 hello.go 的文件中。
4. 通过 go build 命令对该 go 文件进行编译，生成可执行文件 hello 。
5. 在终端执行 ./hello 就可以看到运行效果。
6. 注意：通过 go run 命令可以直接运行 hello.go 程序。

### Mac 下开发步骤
1. 说明：在 Mac 下开发 go 程序和 Windows 基本一样。

## Golang 执行流程分析
1. .go 文件 -> go build(编译) -> 可执行文件 -> 运行 -> 结果
2. .go 文件 -> go run(编译运行一步) -> 结果
3. 两种执行流程的方式区别说明：
```
(1) 如果我们先编译生成了可执行文件，那么我们可以将该可执行文件拷贝到没有 go 开发环境的机器上，仍然可以运行。
(2) 如果我们是直接 go run 源代码，那么如果在另一个机器上这么运行，也需要 go 开发环境，否则无法执行。
(3) 在编译时，编译器会将程序运行依赖的库文件包含在可执行文件中，所有，可执行文件变大了很多。
```

### 什么是编译
1. 有了 go 源文件，通过编译器将其编译成机器可以识别的二进码文件。
2. 在该源文件目录下，通过 go build 对 hello.go 文件进行编译。可以指定生成的可执行文件名，Windows 下，必须是 .exe 后缀。
3. 如果程序没有错误，没有任何提示，会在当前目录下出现一个可执行文件，该文件是二进码文件，也是可以执行的文件。
4. 如果程序有错误，编译时，会在错误的那行报错。

### 什么是运行
1. 直接运行的可执行 Go 程序，比如 hello.exe 。
2. 通过运行工具 go run 对可执行文件进行执行。

## Go 程序开发注意事项
1. Go 源文件以 go 为扩展名。
2. Go 应用程序的执行入口是 main() 方法
3. Go 语言严格区分大小写
4. Go 方法由一条条语句构成，每个语句后不需要分号，这体现了 Golang 的简洁性。
5. Go 编译器是一行行进行编译的，因此我们一行就写一条语句，不能把多条语句写在同一个，负责报错。
6. Go 语言定义的变量或者 import 的包如果没有使用到，代码不能编译通过。
7. 大括号都是成对出现的，缺一不可。

## Golang 常用的转义字符
```
1. \t: 一个制表位，实现对齐的功能
2. \n: 换行符
3. \\: 一个\
4. \": 一个"
5. \r: 一个回车
```

## Golang 开发常见问题和解决方法
1. 找不到文件
```
解决方法: 源文件不存在或者写错，或者当前路径错误。
```
2. 小结和提示
```
(1) 学习编程最容易犯的错误是语法错误。Go 要求你必须按照语法规则编写代码。如果你的程序违反了语法规则，例如：忘记了大括号、引号，或者拼错了单词，Go 编译器都会报语法错误。
(2) 要求：尝试着去看懂编译器会报告的错误信息。
```

## 注释
### 介绍
1. 用于注解说明解释程序的文字就是注释，注释提高了代码的阅读性。
2. 注释是一个程序员必须要具有的良好编程习惯。
3. 将自己的思想通过注释先整理出来，再用代码去实现。

### Go 语言中的注释类型
1. Go 支持 C 语言风格的/* */块注释，也支持 C++ 风格的//行注释。
2. 行注释更通用，块注释主要用于针对包的详细说明或者屏蔽大块的代码。
```
(1) 行注释
(2) 块注释(多行注释)
```
3. 使用细节
```
(1) 对于行注释和块注释，被注释的文字，不会被 Go 编译器执行。
(2) 块注释里面不允许有块注释嵌套。
```

### 规范的代码风格
#### 正确的注释和注释风格
1. Go 官方推荐使用行注释来注释整个方法和语句。
2. 看源码。

#### 正确的缩进和空白
1. 使用一次 tab 操作，实现缩进，默认整体向右边移动，可以用 shift+tab 整体向左移。
2. 或者使用 gofmt 来进行格式化。
3. 运算符两边习惯性各加一个空格。
4. 一行最长不超过 80 个字符，超过的请使用换行展示，尽量保持格式优雅。

## Golang 官方编程指南
1. [Golang 官方网站](!https://golang.org/)。
2. [Golang 官方标准库 API 文档](!http://golang.org/pkg)，可以查看 Golang 所有包下的函数和使用。
3. API：Application Program Interface(应用程序编程接口)，就是我们 Go 的各个包下的各个函数

### Golang 标准库 API 文档
1. API 是 Golang 提供的基本编程接口。
2. Go 语言提供了大量的标准库，因此 Google 公司也为这些标准库提供了相应的 API 文档，用于告诉开发者如何使用这些标准库，以及标准库包含的方法。
3. [Golang 中文网的在线标准库文档](!https:studygolang.com/pkgdoc)。

## Dos 常用指令
### Dos 的基本介绍
1. Dos：Disk Operating System 磁盘操作系统。

### Dos 操作原理
1. cmd 发送指令 -> Dos 操作系统 -> Windows 的目录操作

### 目录操作指令
1. 查看当前目录
```
D:\>dir
```
2. 切换到其他盘
```
D:\>cd /d f:
```
3. 切换到当前盘的其他目录下(相对路径和绝对路径的区别)
```
D:\>cd d:\test100     # 绝对路径切换
d:\test100>
d:\test100>cd adc100  # 相对路径切换
d:\test100\abc100>
d:\test100\abc100>cd d:\test100 # 绝对路径切换
d:\test100>cd d:\test100\abc100 # 绝对路径切换
d:\test100\abc100>
```
4. 切换到上一级目录
```
d:\test100\abc100>cd ..
d:\test100>
```
5. 切换到根目录
```
d:\test100>cd \
d:\>
```
6. 新建目录md
```
d:\>cd test200
d:\test200>md ok200
d:\test200>md ok300 ok400 # 新建多个目录
```
7. 删除空目录
```
d:\test200>rd ok100
d:\test200>
```
8. 删除目录以及下面的下面的子目录和文件，不带询问
```
d:\test200>rd /q/s ok200
```
9. 删除目录以及下面的子目录和文件，带询问
```
d:\test200>rd /s ok300
```
10. 新建或追加内容到文件
```
d:\test200>echo hello > d:\test100\abc.txt
d:\test200>
d:\test200>cd d:\test100\abc
d:\test200\abc100>
d:\test200\abc100>echo atguigu > abc2.txt
d:\test200\abc100>
```
11. 复制文件
```
d:\test200\abc100>copy abc.txt d:\test200
d:\test200\abc100>copy abc.txt d:\test200\ok.txt # 复制并改名
```
12. 移动文件
```
d:\test100\abc100>move abc.txt f:\
```
13. 删除文件
```
d:\test100\abc100>del abc2.txt
d:\test100\abc100>del *.txt # 删除所有带.txt后缀文件
```
14. 清屏
```
cls
```
15. 退出 Dos
```
exit
```
