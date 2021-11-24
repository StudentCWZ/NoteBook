# 远程与 GitHub
## 远程版本库(GitHub)
1. [Github 官网网址](!https://github.com/)
2. 注册账号: 利用邮箱注册账号(公共仓库)
3. 将本地仓库推送到 GitHub 上
- 初始化仓库
```
git init repository_name
```
- 配置信息 
```
git config --global user.name 'name'
git config --global user.email 'email'
```
- 修改配置的命令
```
git config --unset user.name
git config --unset user.email
```
- 修改文件
- 将修改纳入暂存区
- 进行提交
- 本地版本库推送
  - 对 GitHub 进行配置
  - 新建 README.md 进行说明，并提交到本地版本库
  - 现在网页上创建一个远程仓库
  - 推送到远程命令
```
git remote add origin https://github.com/repository_name.git # 远程仓库 url
git push -u origin master # 将本地的 master 分支推送到远程
```
