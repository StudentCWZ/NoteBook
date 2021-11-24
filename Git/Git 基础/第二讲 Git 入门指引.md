# Git 入门指引
## Git 简史
1. Linux 内核开源项目有着位数众多的参与者。一开始整个项目组使用 BitKeeper 来管理和维护代码。2005 年，BitKeeper 不再能免费使用，这就迫使 Linux 开源社区开发一套属于自己的版本控制系统。
2. 自诞生于 2005 年以来，Git 日臻成熟完善，它的速度飞快，极其适合管理大型项目，它还有着令人难以置信的非线性分支管理系统，可以应付各种复杂的项目开发需求。
3. 众多的开源、非开源项目已经逐步由 SVN 迁移到 Git 。

## CVS、SVN 与 Git
1. CVCS: 集中式版本控制系统
2. DVCS: 分布式版本控制系统
3. 有了 Git ，编程真正成了一种乐趣。

## Git、GitHub 与 GitLab
1. Git 是一个版本控制软件
2. GitHub 与 GitLab 都是用于管理版本的服务端软件
3. GitHub 提供免费服务(代码需公开)以及付费服务(代码私有)
4. GitLab 用于在企业内部管理 Git 版本库，功能上类似于 GitHub

## Git 设计目标
1. 快速
2. 简单(学习曲线陡峭)
3. 高效存储
4. 完全分布
5. 满足大规模项目需要

## Git 优点
1. 本地建立版本库
2. 本地版本控制
3. 多主机异地协同工作
4. 重写提交说明
5. 有后悔药可以吃
6. 更好用的提交列表
7. 更好的差异比较
8. 更完善的分支系统
9. 速度极快

## Git 工作模式
1. 版本库初始化
  - 个人计算机从版本服务器同步

2. 操作
  - 90% 以上的操作在个人计算机上
  - 添加文件
  - 修改文件
  - 提交变更
  - 查看版本历史等

3. 版本库同步
  - 将本地修改推送到版本服务器

## Git 基础
1. 直接记录快照，而非差异比较
2. 近乎所有操作都在本地执行
3. 时刻保持数据完整性
4. 多数操作仅添加操作
5. 文件的三种状态
  - modified: 已修改
  - staged: 已暂存
  - committed: 已提交

## Git 文件状态
1. Git 文件
  - 已被版本库管理的文件

2. 已修改
  - 在工作目录修改 Git 文件

3. 已暂存
  - 对已修改的文件执行 Git 暂存操作，将文件存入暂存区

4. 已提交
  - 将已暂存的文件执行 Git 提交操作，将文件存入版本库

## Git 安装
1. Linux: Ubuntu
```
sudo apt-get install git
```
2. Mac
  - 安装命令行工具：如已安装 Xcode ，命令行工具会在首次启动 Xcode 时提示安装
  - homebrew
    - 安装 homebrew
```
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
```
3. Windows
  - 安装 [msysGit](!http://code.google.com/p/msysgit)
  - 完成安装之后，就可以使用命令行的 git 工具了，另外还有一个图形界面的 Git 项目管理工具。
  - 建议使用 Git 命令行，方便又快捷，GUI 反而繁琐。
  - 如果需要使用 GUI ，推荐使用 SourceTree ，拥有 Mac 与 Windows 版本；此外，Windows 下还可以使用 TortoiseGit 。

## Git 常用命令
1. 获得版本库
```
git init # 版本库初始化(本地创建 git 版本库)
git clone # 克隆远程版本库
```
2. 版本管理
```
git add # 将已修改的文件从工作区纳入到暂存区
git commit # 将暂存区文件提交到 git 本地版本库
git rm # 删除文件
```
3. 查看信息
```
git help # 查看 git 命令使用
git log # 查看日志
git diff # 查看文件不同状态的差异
```
4. 远程协作
```
git pull # 将远程版本库当中的文件拉到本地
git push # 将本地版本库当中的版本内容推送到远程
```
