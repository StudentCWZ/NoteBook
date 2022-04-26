# Git subtree 与 Git cherry-pick
## Git subtree
1. Git submodule 如果在主项目修改依赖项目会造成很多问题，Git subtree 和 Git submodule 解决问题一样，Git subtree 可以解决针对双向修改。
  - 新建 git_subtree_parent 和 git_subtree_children 两个工程(远程仓库)，其中 git_subtree_children 是子工程。
  - 在本地创建仓库 git_subtree_parent 和 git_subtree_children
  - 在本地创建的仓库 git_subtree_parent 新建文件 parent.txt ，将修改纳入暂存区，并进行提交
  - 配置远程仓库 git_subtree_parent 信息
  - 将本地的 git_subtree_parent 提交推送到远程 git_subtree_parent 中
  - 在本地创建的仓库 git_subtree_children 新建 children.txt，将修改纳入暂存区，并进行提交
  - 配置远程仓库 git_subtree_children 信息
  - 将本地的 git_subtree_children 提交推送到远程 git_subtree_children
  - 在本地创建的仓库 git_subtree_children 新建 hello.txt ，将修改纳入暂存区，并进行提交
  - 将本地的 git_subtree_children 提交推送到远程 git_subtree_children
  - 在主项目关联子项目的远程库
  - 执行 git subtree 命令，将子依赖拉到主项目
  - 如果 git_children 发生变化，git_parent 也随之变化
  - 在主工程的 git_subtree_parent/subtree 目录下修改文件 children.txt ，将修改纳入暂存区，并进行提交
  - 将本地的 git_subtree_parent/subtree 目录下修改推送到远程 git_subtree_parent 中
  - 将本地的 git_subtree_parent/subtree 目录下修改推送到远程 git_subtree_children

2. 在主项目关联子项目的远程库命令
```Bash
git remote add subtree-origin git@github.com:gitlecture/git_subtree_children.git
```
3. 执行 git subtree 命令
```Bash
git subtree add --prefix=subtree subtree-orign master --squash # squash 参数表示子项目的多次提交被压缩成一次提交
```
4. 拉取 git_subtree_children 更新
```Bash
git subtree pull --prefix=subtree subtree-origin --squash # squash 也可以不用(注意：要是不用就所有 subtree 命令不用，要用就所有 subtree 命令都用)
```
5. 将本地的 git_subtree_parent/subtree 目录下修改推送到远程 git_subtree_parent 中
```Bash
git push
```
6. 将本地的 git_subtree_parent/subtree 目录下修改推送到远程 git_subtree_children
```Bash
git subtree push --prefix=subtree subtree-origin master
```
7. 将远程的 git_subtree_children 拉到本地 git_subtree_children
```Bash
git pull # 在 git_subtree_children 目录下
```

## Git cherry-pick
1. 作用：将在一个分支的修改应用到另一个分支上(主要用于本地分支)
2. 将一个分支的修改应用到另一个分支上(例如将在 develop 分支的修改应用于 master 分支)
```Bash
git cherry-pick commit_id # 将其他分支的一个提交所做的修改应用到当前分支
```
