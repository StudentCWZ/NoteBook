# Git rebase
## 基本介绍
1. rebase：变基，意即改变分支的根基
2. rebase 作用：从某种程度上来说，rebase 与 merge 可以完成类似的工作，不过两者的工作方式有着显著的差异
3. Git rebase 操作
```
git checkout branch_name
git rebase branch_new_name
```
4. Git merge 操作和 Git rebase 操作的区别: 两者结果内容一样，过程和操作方式完全不一样。
5. Git rebase 操作会修改提交历史，会让提交历史变成一条直线。 
6. Git rebase 原理：实际上是将一个分支的修改内容应用到里另外一个分支上，不断的应用，所以提交历史变成一条直线。
7. Git rebase 注意事项
- rebase 过程中也会出现冲突
- 解决冲突后，使用 git add 添加，然后执行如下命令：
```
git rebase --continue
```
- 接下来 Git 会继续应用余下的补丁
- 任何时候都可以通过如下命令终止 rebase ，分支会恢复到 rebase 开始前的状态
```
git rebase --abort
```
8. Git rebase 最佳实践
  - 不要对 master 分支执行 rebase ，否则会引起很多问题
  - 一般来说，执行 rebase 的分支都是自己的本地分支，没有推送到远程的版本库。
