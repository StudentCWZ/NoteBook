# Git 裸库与 Git submodule
## Git 裸库
1. 没有工作区的 Git 仓库(一般在服务器端)
2. 创建裸库命令
```
git init --bare # 没有工作区
```

## Git submodule
1. 项目的相互协作(多模块开发-项目的依赖)
  - 准备工作
    - 在 GitHub 上创建仓库 `git_parent` 和 `git_children`
    - 在本地创建仓库 `git_parent` 和 `git_children`
    - 在本地创建的仓库 `git_parent` 新建文件 parent.txt ，将修改纳入暂存区，并进行提交
    - 配置远程仓库 `git_parent` 信息
    - 将本地的 `git_parent` 提交推送到远程 `git_parent`
    - 在本地创建的仓库 `git_children` 新建 submodule.txt ，将修改纳入暂存区，并进行提交
    - 配置远程仓库 `git_children` 信息
    - 将本地的 `git_children` 提交推送到远程 `git_children`
    - 在本地创建的仓库 `git_childern` 新建 hello.txt ，将修改纳入暂存区，并进行提交
    - 将本地的 `git_children` 提交推送到远程 `git_children`
    - 执行 git submodule 命令，将子依赖拉到主项目
    - 将文件修改纳入暂存区，接着进行提交
    - 如果 `git_childern` 发生变化，`git_parent` 也随之变化 

2. git submodule 命令
```
git submodule git@github.com:gitlecture/git_children.git my module
```
3. 拉取 `git_children` 更新
```
git pull # 在 git_parent/mymodule 目录下
```
4. 拉取所有依赖子模块的所有更新
```
git submodule foreach git pull
```
5. 如果一个项目包含着 submodelue ，进行 git clone 操作的时候，默认情况下是不会把 submodelue 的代码克隆下来，需要手工的去执行一次
```
git clone git@github.com:gitlecture/git_parent.git parent2 # 克隆
git submodule init # submodelue 初始化
git submodule update --recursive # 进行子模块更新
```
6. 进行 git clone 操作时候，使用后面加参数将所有子模块的更新全部克隆下来
```
git clone git@github.com:gitlecture/git_parent.git parent3 --recursive
```
7. 删除 submodule (命令组合)
```
# 1.将 submodule 从缓存区删除
git rm --cached mymodule
# 2.将 submodule 的实体文件从工作区删除
rm -rf mymodule
git add .
git commit -m 'remove submodule'
git push
# 3.将 .gitmodule 目录删除
rm .gitmodules
git add .
git commit -m 'remove submodules'
git push
```
