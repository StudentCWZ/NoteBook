# MySQL 相关了解和安装
## MySQL 相关了解
### MySQL 含义
1. MySQL 是最流行的关系型数据库管理系统，在 WEB 应用方面 MySQL 是最好的 RDBMS (Relational Database Management System：关系数据库管理系统) 应用软件之一。

### 数据库含义
1. 数据库 (Database) 是按照数据结构来组织、存储和管理数据的仓库。
2. 每个数据库都有一个或多个不同的 API 用于创建，访问，管理，搜索和复制所保存的数据。
3. 我们也可以将数据存储在文件中，但是在文件中读写数据速度相对较慢。所以，现在我们使用关系型数据库管理系统 (RDBMS) 来存储和管理大数据量。
4. 所谓的关系型数据库，是建立在关系模型基础上的数据库，借助于集合代数等数学概念和方法来处理数据库中的数据。

### RDBMS 特点
1. 数据以表格的形式出现
2. 每行为各种记录名称
3. 每列为记录名称所对应的数据域
4. 许多的行和列组成一张表单
5. 若干的表单组成 database

### RDBMS 术语
1. 数据库：数据库是一些关联表的集合。
2. 数据表：表是数据的矩阵。在一个数据库中的表看起来像一个简单的电子表格。
3. 表头 (header)：每一列的名称。
4. 列(col)：一列(数据元素) 包含了相同类型的数据, 例如邮政编码的数据。
5. 行(row)：一行(=元组，或记录)是一组相关的数据，例如一条用户订阅的数据。
6. 值(value)：行的具体信息, 每个值必须与该列的数据类型相同。
7. 键(key)：键的值在当前列中具有唯一性。
8. 冗余：存储两倍数据，冗余降低了性能，但提高了数据的安全性。
9. 主键：主键是唯一的。一个数据表中只能包含一个主键。你可以使用主键来查询数据。
10. 外键：外键用于关联两个表。
11. 复合键：复合键(组合键)将多个列作为一个索引键，一般用于复合索引。
12. 索引：使用索引可快速访问数据库表中的特定信息。索引是对数据库表中一列或多列的值进行排序的一种结构。类似于书籍的目录。
13. 参照完整性：参照的完整性要求关系中不允许引用不存在的实体。与实体完整性是关系模型必须满足的完整性约束条件，目的是保证数据的一致性。

### MySQL 数据库
1. MySQL 是一个关系型数据库管理系统，由瑞典 MySQL AB 公司开发，目前属于 Oracle 公司。
2. MySQL 是一种关联数据库管理系统，关联数据库将数据保存在不同的表中，而不是将所有数据放在一个大仓库内，这样就增加了速度并提高了灵活性。
3. MySQL 是开源的，所以你不需要支付额外的费用。
4. MySQL 支持大型的数据库。可以处理拥有上千万条记录的大型数据库。
5. MySQL 使用标准的 SQL 数据语言形式。
6. MySQL 可以运行于多个系统上，并且支持多种语言。这些编程语言包括 C、C++、Python、Java、Perl、PHP、Eiffel、Ruby 和 Tcl 等。
7. MySQL 对 PHP 有很好的支持，PHP 是目前最流行的 Web 开发语言。
8. MySQL 支持大型数据库，支持 5000 万条记录的数据仓库，32位系统表文件最大可支持 4 GB，64 位系统支持最大的表文件为 8 TB 。
9. MySQL 是可以定制的，采用了 GPL 协议，你可以修改源码来开发自己的 MySQL 系统。

## MySQL 安装
### Linux/UNIX 上安装 MySQL
1. Linux 平台上推荐使用 RPM 包来安装 MySQL， MySQL AB 提供了以下 RPM 包的下载地址：
```
# MySQL
MySQL 服务器。你需要该选项，除非你只想连接运行在另一台机器上的 MySQL 服务器。  
# MySQL-client
MySQL 客户端程序，用于连接并操作 MySQL 服务器。  
# MySQL-devel
库和包含文件，如果你想要编译其它 MySQL 客户端，例如 Perl 模块，则需要安装该 RPM 包。  
# MySQL-shared
该软件包包含某些语言和应用程序需要动态装载的共享库(libmysqlclient.so*)，使用 MySQL。  
# MySQL-bench
MySQL 数据库服务器的基准和性能测试工具。
```
2. 安装前，我们可以检测系统是否自带安装 MySQL
```
rpm -qa | grep mysql
```
3. 如果你系统有安装，那可以选择进行卸载
```
rpm -e mysql　　// 普通删除模式
rpm -e --nodeps mysql　　// 强力删除模式，如果使用上面命令删除时，提示有依赖的其它文件，则用该命令可以对其进行强力删除
```
4. 接下来我们在 Centos7 系统下使用 yum 命令安装 MySQL，需要注意的是 CentOS 7 版本中 MySQL 数据库已从默认的程序列表中移除。
5. 所以在安装前我们需要先去官网下载 yum 资源包，下载地址为：https://dev.mysql.com/downloads/repo/yum/ 。
```
wget http://repo.mysql.com/mysql-community-release-el7-5.noarch.rpm
rpm -ivh mysql-community-release-el7-5.noarch.rpm
yum update
yum install mysql-server
```
6. 权限设置
```
chown mysql:mysql -R /var/lib/mysql
```
7. 初始化 MySQL
```
mysqld --initialize
```
8. 启动 MySQL
```
systemctl start mysqld
```
9. 查看 MySQL 运行状态
```
systemctl status mysqld
```
10. 注意：如果我们是第一次启动 MySQL 服务， MySQL 服务器首先会进行初始化的配置。
11. 此外,你也可以使用 MariaDB 代替， MariaDB 数据库管理系统是 MySQL 的一个分支，主要由开源社区在维护，采用 GPL 授权许可。
12. 开发这个分支的原因之一是：甲骨文公司收购了 MySQL 后，有将 MySQL 闭源的潜在风险，因此社区采用分支的方式来避开这个风险。
13. MariaDB 的目的是完全兼容 MySQL ，包括 API 和命令行，使之能轻松成为 MySQL 的代替品。
```
yum install mariadb-server mariadb 
```
14. MariaDB 数据库的相关命令
```
systemctl start mariadb  # 启动 MariaDB
systemctl stop mariadb  # 停止 MariaDB
systemctl restart mariadb  # 重启 MariaDB
systemctl enable mariadb  # 设置开机启动
```
15. 验证 MySQL 安装：在成功安装 MySQL 后，一些基础表会表初始化，在服务器启动后，你可以通过简单的测试来验证 MySQL 是否工作正常。
16. 使用 mysqladmin 工具来获取服务器状态：使用 mysqladmin 命令来检查服务器的版本，在 Linux 上该二进制文件位于 /usr/bin 目录，在 Windows 上该二进制文件位于 C:\mysql\bin
```
[root@host]# mysqladmin --version
```
17. Linux 上该命令将输出以下结果，该结果基于你的系统信息:
```
mysqladmin  Ver 8.23 Distrib 5.0.9-0, for redhat-linux-gnu on i386
```

### Windows 上安装 MySQL
1. 进入官网找到自己所需的[安装包](!https://dev.mysql.com/)，路径：DOWNLOAD --> MySQL Community Edition(GRL) --> MySQL on Windows(Installer&Tool) 或直接点击 https://dev.mysql.com/downloads/windows/installer/ 查看最新版本。
2. Windows 上安装 MySQL 相对来说会较为简单，更详细安装：https://www.runoob.com/w3cnote/windows10-mysql-installer.html 。
3. 下载完后，我们将 zip 包解压到相应的目录，这里我将解压后的文件夹放在 C:\web\mysql-8.0.11 下。
4. 接下来我们需要配置下 MySQL 的配置文件：打开刚刚解压的文件夹 C:\web\mysql-8.0.11，在该文件夹下创建 my.ini 配置文件，编辑 my.ini 配置以下基本信息。
```
[client]
# 设置 mysql 客户端默认字符集
default-character-set=utf8
 
[mysqld]
# 设置 3306 端口
port = 3306
# 设置 mysql 的安装目录
basedir=C:\\web\\mysql-8.0.11
# 设置 mysql 数据库的数据的存放目录， MySQL 8+ 不需要以下配置，系统自己生成即可，否则有可能报错
# datadir=C:\\web\\sqldata
# 允许最大连接数
max_connections=20
# 服务端使用的字符集默认为 8 比特编码的 latin1 字符集
character-set-server=utf8
# 创建新表时将使用的默认存储引擎
default-storage-engine=INNODB
```
5.  接下来我们来启动下 MySQL 数据库
```
cd C:\web\mysql-8.0.11\bin
```
6. 初始化数据库
```
mysqld --initialize --console
```
7. 执行完成后，会输出 root 用户的初始默认密码
```
2018-04-20T02:35:05.464644Z 5 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: APWCY5ws&hjQ
```
8. APWCY5ws&hjQ 就是初始密码，后续登录需要用到，你也可以在登陆后修改密码。
9. 输入以下安装命令
```
mysqld install
```
10. 启动输入以下命令即可
```
net start mysql
```
11. 注意: 在 5.7 需要初始化 data 目录
```
cd C:\web\mysql-8.0.11\bin mysqld --initialize-insecure 
```
12. 初始化后再运行 net start mysql 即可启动 mysql 。
13. 登录 MySQL：当 MySQL 服务已经运行时，我们可以通过 MySQL 自带的客户端工具登录到 MySQL 数据库中，首先打开命令提示符，输入以下格式的命令。
```
mysql -h 主机名 -u 用户名 -p
```
14. 参数说明:
```
-h : 指定客户端所要登录的 MySQL 主机名, 登录本机(localhost 或 127.0.0.1)该参数可以省略;  
-u : 登录的用户名;
-p : 告诉服务器将会使用一个密码来登录, 如果所要登录的用户名密码为空, 可以忽略此选项。
```
15. 如果我们要登录本机的 MySQL 数据库，只需要输入以下命令即可
```
mysql -u root -p
```
16. 按回车确认, 如果安装正确且 MySQL 正在运行, 会得到以下响应
```
Enter password:
```
17. 若密码存在, 输入密码登录, 不存在则直接按回车登录。
18. 登录成功后你将会看到 Welcome to the MySQL monitor... 的提示语。
19. 然后命令提示符会一直以 mysq> 加一个闪烁的光标等待命令的输入，输入 exit 或 quit 退出登录。

### Mac 上安装 MySQL
1. [下载地址](!https://www.mysql.com/downloads/)
2. 滚动网页至最下方，选择 DOWNLOADS => MySQL Community Server
3. 选择 dmg 文件下载
4. 直接下载
5. 双击安装包，进行安装
6. 注意：安装完成之后会弹出一个对话框，告诉我们生成了一个 root 账户的临时密码。请注意保存，否则重设密码会比较麻烦。
7. 打开系统偏好设置，会发现多了一个 MySQL 图标，点击它，会进入 MySQL 的设置界面：安装之后，默认 MySQL 的状态是 stopped ，是关闭的，需要点击 Start MySQL Server 按钮来启动它，启动之后，状态会变成 running 。
8. 下方还有一个复选框按钮，可以设置是否在系统启动的时候自动启动 MySQL ，默认是勾选的，建议取消，节省开机时间。
此时我们在命令行输入 mysql -u root -p 命令会提示没有 commod not found ，我们还需要将 mysql 加入系统环境变量。
```
(1) 进入 /usr/local/mysql/bin , 查看此目录下是否有 mysql。
(2) 执行 vim ~/.bash_profile
在该文件中添加 mysql/bin 的目录
PATH=$PATH:/usr/local/mysql/bin
添加完成后，按 esc ，然后输入 wq 保存。
最后在命令行输入 source ~/.bash_profile
```
9. 现在你就可以通过 mysql -u root -p 登录 mysql 了，会让你输入密码改密码是 6 中的临时密码。
10. 登录成功后，你可以通过下面的命令修改密码
```
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('newpassword');
```
