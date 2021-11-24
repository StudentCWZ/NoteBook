# Git 重要命令操练
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
5. 查看工作区状态
```
git status 
```
6. 将文件从暂存区回退到工作区
```
git rm --cached file
```
7. 将文件从暂存区提交到本地版本库
```
git commit -m '提交消息'
```
8. 查看提交历史
  - Git 的提交(commit id)是一个在摘要值。这个摘要值实际是 sha1 计算出来的
```
git log
```
9. 配置信息
  - 对于 user.name 和 user.email 来说，有三个地方可以设置
```
/etc/gitconfig(几乎不会使用)
git config --system

~/.gitconfig(很常用) # 针对用户(优先级其次)
git config --global

.git/config # 针对于特定项目的(优先级最高)
git config --local
```
  - 查看配置信息
```
git config --list
```
  - 删除配置信息
```
git config --local --unset user.name # 针对特定项目的
```
  - 查看配置文件
```
cat config # 查看针对特定项目的配置信息文件
cat ~/.gitconfig # 查看针对用户的配置信息文件
```
  - 丢弃在工作区所做的修改
```
git checkout -- file
```
  - 完全修改文件内容
```
echo 'weclome' > file # 文件重定向
```
  - 追加文件内容
```
echo 'weclome' >> file
```
  - 从将文件从暂存区回退到工作区
```
git reset HEAD file
```
