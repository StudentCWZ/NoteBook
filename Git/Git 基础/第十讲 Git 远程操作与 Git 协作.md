# Git 远程操作与 Git 协作
## Git 远程操作
1. 建立 SSH 安全连接
```
# 1.配置远程
git remote add orgin git@github.com:repository_name/repository_name.git # 远程仓库 SSH 链接地址
git remote show orgin
# 2.把公钥放置远程
cd .ssh
ls
vi known_hosts
which ssh-keygen # 生成公钥
ssh-keygen # 生成公钥(敲入命令回车，要求输入密码连续两次回车)
ls id_rsa.pub # 进入公钥文件夹(把公钥粘贴放置 GitHub 中 settings 的 Deploy keys 上面)
# 3.把本地分支推送到远程
git push -u orgin master # 将本地的 master 分支推送到远程
```
2. 查看所有远程仓库的别名
```
git remote show
```
3. 查看远程仓库所有信息
```
git remote show repository_name
```
4. 重命名远程仓库
```
git remote rename origin origin2
```
5. 删除远程仓库
```
git remote rm origin
```
6. 删除之后再次创建
```
git remote add orgin git@github.com:repository_name/epository_name.git
```

## 更新 git
1. 查看 git 版本
```
git --version
```
2. 查看 git 的位置
```
which git
```
3. 查看 mac 的系统环境
```
echo $PATH
```
4. 修改环境目录
```
vi .zshrc
```
5. 安装新版 git
```
# 新增的 git 一般被安装在 usr/local/git 中 
```

## git 协作
1. 查看所有远程仓库的名字
```
git remote show
```
2. 查看所有远程仓库的详细信息
```
git remote show remote_repository_name
```
4. 关联远程(过程理解)
```
# 与远程关联之后，本地有一个 master 分支，还有一个 origin/master 分支(追踪着远程版本库的 mater 分支)
# 进行 git pull 操作，git 会将远程的修改拉到本地，同时将 origin/master 分支的 commit_id 对应到远程 master 最新的那次提交,如果这种合并是可以直接合并，git 会将远程的 master 分支直接合并到本地的 master 分支上。
# 进行 git push 操作，git 会将本地的 origin/master 分支最新的 commit_id 指向本地的 master 分支上，将本地的 master 的分支内容 push 到远程
```
5. 查看远程分支
```
git branch -a # 查看所有远程分支
git branch -av # 查看所有远程分支以及其最新的提交 commit_id
```

## 拉取代码操作
1. 拉取远程代码(克隆)
```
git clone repository_url_ssh
```
2. 拉取远程代码并重命名仓库
```
git clone repository_url_ssh repository_new_name
```

## git pull 与 git fetch 区别
1. git fetch
```
git fetch # 一定会成功的(不执行合并操作，仅仅是将远程的修改拉到本地，更新本地的 orgin/master)
git merge orgin/master # 将本地的 orgin/master 的修改 merge 到本地的 master 分支上
```
2. git pull == git fetch + git merge

## 项目开发
1. Gitflow
2. 关于 Git 分支的最佳实践(基于 Git 分支的开发模型)
  - master 分支(生产发布)--生产发布分支，变化非常不频繁的一个分支
  - test 分支(测试)--供测试与产品人员使用的一个分支，变化不是特别频繁  
  - develop 分支(开发)--频繁变化的一个分支  
  - hotfix 分支(修复 bug 分支)--生产系统当中出现了紧急 bug ，用于紧急修复的分支
