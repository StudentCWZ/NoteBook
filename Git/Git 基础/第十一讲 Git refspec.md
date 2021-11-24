# Git refspec
## Git 别名
1. 配置别名
```
git config --global alias.br branch # 配置信息在 ~/.gitconfig 文件下
git config --global alias.st status
git config --global alias.co checkout
git config --global alias.unstage 'reset HEAD'
git config --global alias.ui '!git'
```

## Git refspec
### git push 相关操作
1. git push 操作的完整命令
```
git push origin srcbranch:destbranch # 建议的 git push 命令
```
2. 将本地分支推送到远程
```
git push --set-upstream origin branch_name # 将本地分支推送到远程(默认远程分支名与本地一样)
git push -u origin branch_name # 跟上一条命令一样效果
git push --set-upstream origin branch_name:branch_new_name # 将本地分支推送到远程并且远程分支名与本地所关联的分支不同名
# 如果不同名，则进行 git push 操作，则要执行以下命令
git push origin HEAD:branch_new_name # 尽可能同名(不同名无法简写 git push 命令)
```

### git pull 相关操作
1. git pull 操作的完整命令
```
git pull origin srcbranch:destbranch
```
2. 将远程分支拉到本地
```
git pull # 将远程所有分支改变都拉到本地
git checkout -b branch_name origin/branch_name # 创建跟远程分支同名的本地分支，并关联。
git checkout --track origin/branch_name分支来追踪 # 本地创建同名的 branch_name 分支来追踪 origin/branch_name (与上一条命令意思完全一样)
```
3. 删除远程分支
```
git push origin :branch_name # 将本地的一个空分支推送到远程某个分支(意味着删除远程的该分支)
git push origin --delete branch_name # 与上一个命令一样效果
```
4. 重命名远程分支
  - 删除远程分支
  - 本地重命名分支
  - 将本地重命名后的分支推送到远程

## HEAD 文件相关内容
1. HEAD 标记
  - HEAD 文件是一个指向你当前所在分支的引用标识符，该文件内部并不包含 SHA-1 值，而是包含一个指向另外一个引用的指针。
  - 当执行 git commit 命令时，git 会创建一个 commit 对象，并且将这个 commit 对象的 parent 指针设置为 HEAD 所指向的引用的 SHA-1 值。
  - 我们对于 HEAD 修改的任何操作都会被 git reflog 完整记录下来。
  - 实际上，我们可以通过对 git 底层命令 symboic-ref 来实现对 HEAD 文件内容的修改。
```
git symbolic-ref HEAD # 读取
git symbolic-ref HEAD refs/heads/branch_name # 写入
```
