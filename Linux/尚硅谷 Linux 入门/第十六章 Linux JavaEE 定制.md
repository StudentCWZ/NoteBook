# Linux JavaEE 定制
## 搭建 JavaEE 环境
### 基本介绍
1. 概述
    - 如果需要在 Linux 下进行 JavaEE 的开发，我们需要安装如下软件：jdk、apache-tomcat、mysql、eclipse 。

### 安装 JDK
1. 安装步骤
```
(1) 先将软件通过 xftp5 上传到 /opt 下
(2) 解压缩到 /opt
(3) 配置环境变量的配置文件 vim /etc/profile
(4) JAVA_HOME=/opt/jdk1.70_79
(5) PATH=/opt/jdk1.70_79/bin:$PATH
(6) export JAVA_HOME PATH
```
2. 注意事项
```
(1) 需要注销用户，环境变量才能生效：
如果是在 3 运行级别，logout
如果是在 5 运行级别，图形化界面操作注销
(2) 在任何目录下就可以使用 java 和 javac
```
3. 测试程序
```
public class Hello{
    public static void main(String[] args){
        System.out.println("hello, world!");
    }

}
```

### Tomcat 安装和配置
1. Tomcat 安装
```
(1) 解压缩到 /opt 
(2) 启动 tomcat ./startup.sh 
(3) 开放端口 8080 vim/etc/sysconfig/iptables 
(4) 重启防火墙 Service iptables restart 
```
2. 测试
```
(1) 在 Windows、Linux 下访问 http://linuxip:8080 
```

### Eclipse 安装和配置
1. Eclipse 安装
```
(1) 解压缩到 /opt 
(2) 启动 Eclipse，配置 jre 和 server 
(3) 编写 Hello world 程序并测试成功
(4) 编写 jsp 页面，并测试成功
```

### Mysql 安装和配置
1. 检查 Linux 服务器上是否已经安装 mysql
```
[root@vultr ~]# rpm -qa|grep -i mysql # 未安装则无任何信息返回，若已安装则会返回已安装的版本信息
```
2. 如果已经安装可通过 --nodeps 关键字卸载
```
[root@vultr ~]# rpm -e 返回的mysql版本信息 --nodeps
```
3. 切换到 /usr/local 目录
```
[root@vultr ~]# cd /usr/local
```
4. 根据上面的 Linux 系统信息选择相应的[版本下载](https://dev.mysql.com/downloads/mysql/)
```
[root@vultr local]# wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz
```
5. mysql 下载成功
```
[root@vultr local]# ll
总用量 838992
drwxr-xr-x. 2 root root      4096 4月  11 2018 bin
drwxr-xr-x. 2 root root      4096 4月  11 2018 etc
drwxr-xr-x. 2 root root      4096 4月  11 2018 games
drwxr-xr-x. 2 root root      4096 4月  11 2018 include
drwxr-xr-x. 2 root root      4096 4月  11 2018 lib
drwxr-xr-x. 2 root root      4096 4月  11 2018 lib64
drwxr-xr-x. 2 root root      4096 4月  11 2018 libexec
-rw-r--r--  1 root root 859071704 9月  25 07:11 mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz
drwxr-xr-x. 2 root root      4096 4月  11 2018 sbin
-rw-r--r--. 1 root root      4532 1月   1 03:23 shadowsocks_install.log
drwxr-xr-x. 5 root root      4096 12月 22 21:02 share
drwxr-xr-x. 2 root root      4096 4月  11 2018 src
```
6. 解压 mysql
```
[root@vultr local]# tar -xvf mysql-8.0.22-linux-glibc2.12-x86_64.tar.xz
```
7. mysql-8.0.22-linux-glibc2.12-x86_64 重命名
```
[root@vultr local]# mv mysql-8.0.22-linux-glibc2.12-x86_64 mysql
```
8. 新建 mysql 用户、组及目录
```
[root@vultr local]# groupadd mysql
[root@vultr local]# useradd -r -s /sbin/nologin -g mysql mysql -d /usr/local/mysql
[root@vultr local]# groups mysql
mysql : mysql
```
9. 创建数据仓库目录
```
[root@vultr local]# cd mysql
[root@vultr mysql]# mkdir data
```
10. 改变目录属有者
```
[root@vultr mysql]# chown -R mysql.mysql /usr/local/mysql
[root@vultr mysql]# ll
总用量 408
drwx------  2 mysql mysql   4096 9月  23 14:11 bin
drwxr-xr-x  2 mysql mysql   4096 1月   2 05:43 data
drwx------  2 mysql mysql   4096 9月  23 14:11 docs
drwx------  3 mysql mysql   4096 9月  23 14:11 include
drwx------  6 mysql mysql   4096 9月  23 14:11 lib
-rw-r--r--  1 mysql mysql 378912 9月  23 12:37 LICENSE
drwx------  4 mysql mysql   4096 9月  23 14:11 man
-rw-r--r--  1 mysql mysql    687 9月  23 12:37 README
drwx------ 28 mysql mysql   4096 9月  23 14:11 share
drwx------  2 mysql mysql   4096 9月  23 14:11 support-files
```
11. 初始化
```
[root@vultr mysql]# bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
bin/mysqld: error while loading shared libraries: libaio.so.1: cannot open shared object file: No such file or directory
```
12. 上面操作命令报错，解决方法如下
```
[root@vultr mysql]# yum install -y libaio
```
13. 再次初始化
```
[root@vultr mysql]# bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
```
14. 初始化成功，记住原始密码
```
[root@vultr mysql]# bin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
2021-01-02T06:07:39.558532Z 0 [Warning] [MY-011070] [Server] 'Disabling symbolic links using --skip-symbolic-links (or equivalent) is the default. Consider not using this option as it' is deprecated and will be removed in a future release.
2021-01-02T06:07:39.558693Z 0 [System] [MY-013169] [Server] /usr/local/mysql/bin/mysqld (mysqld 8.0.22) initializing of server in progress as process 22663
2021-01-02T06:07:39.569768Z 1 [System] [MY-013576] [InnoDB] InnoDB initialization has started.
2021-01-02T06:07:40.537599Z 1 [System] [MY-013577] [InnoDB] InnoDB initialization has ended.
2021-01-02T06:07:41.427669Z 6 [Note] [MY-010454] [Server] A temporary password is generated for root@localhost: l%p,iqD;v6ih
```
15. mysql 用户只需作为 mysql/data/ 目录下所有文件的所有者。
```
[root@vultr mysql]# chown -R root:root ./
[root@vultr mysql]# chown -R mysql:mysql data
[root@vultr mysql]# ll
总用量 408
drwx------  2 root  root    4096 9月  23 14:11 bin
drwxr-xr-x  6 mysql mysql   4096 1月   2 06:07 data
drwx------  2 root  root    4096 9月  23 14:11 docs
drwx------  3 root  root    4096 9月  23 14:11 include
drwx------  6 root  root    4096 9月  23 14:11 lib
-rw-r--r--  1 root  root  378912 9月  23 12:37 LICENSE
drwx------  4 root  root    4096 9月  23 14:11 man
-rw-r--r--  1 root  root     687 9月  23 12:37 README
drwx------ 28 root  root    4096 9月  23 14:11 share
drwx------  2 root  root    4096 9月  23 14:11 support-files
```
16. 添加 mysqld 服务到系统
```
[root@vultr mysql]# cp support-files/mysql.server /etc/init.d/mysql
```
17. vim /etc/init.d/mysql
```
basedir=/usr/local/mysql
datadir=/usr/local/mysql/data
```
18. vim /etc/my.cnf
```
[mysql]
port=3306
socket=/tmp/mysql.sock
# 设置 mysql 客户端默认编码
default-character-set=utf8

[mysqld]
# Required Settings
basedir=/usr/local/mysql
bind_address=0.0.0.0 # Change to 0.0.0.0 to allow remote connections
datadir=/usr/local/mysql/data
max_allowed_packet=256M
max_connect_errors=1000000
log-error = /usr/local/mysql/data/error.log 
pid-file = /usr/local/mysql/data/mysql.pid
tmpdir = /tmp
# pid-file=/var/run/mysqld/mysqld.pid
port = 3306

skip_external_locking
skip_name_resolve
# socket=/var/run/mysqld/mysqld.sock
user=mysql
secure-file-priv='/tmp'
sql-mode='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTIO'

# 服务端默认utf8编码
character-set-server=utf8
# init_connect ='set collation_database=utf8_general_ci'

# InnoDB Settings
default_storage_engine=InnoDB
innodb_dedicated_server=ON              #自适应参数，on情况不打开下面注释的参数
# innodb_flush_method             = O_DIRECT
# innodb_log_file_size            = 512M
# innodb_buffer_pool_size         = 2G    # Use up to 70-80% of RAM
innodb_buffer_pool_instances=2
innodb_file_per_table=1
innodb_flush_log_at_trx_commit=0
innodb_log_buffer_size=16M
innodb_stats_on_metadata        = 0

# innodb_temp_data_file_path     = ibtmp1:64M:autoextend:max:20G # Control the maximum size for the ibtmp1 file
# innodb_thread_concurrency      = 4     # Optional: Set to the number of CPUs on your system (minus 1 or 2) to better
                                        # contain CPU usage. E.g. if your system has 8 CPUs, try 6 or 7 and check
                                        # the overall load produced by MySQL/MariaDB.
innodb_read_io_threads          = 64
innodb_write_io_threads         = 64

# MyISAM Settings
# query_cache_limit               = 4M    # UPD - Option supported by MariaDB & up to MySQL 5.7, remove this line on MySQL 8.x
# query_cache_size                = 64M   # UPD - Option supported by MariaDB & up to MySQL 5.7, remove this line on MySQL 8.x
# query_cache_type                = 1     # Option supported by MariaDB & up to MySQL 5.7, remove this line on MySQL 8.x

key_buffer_size                 = 32M   # UPD

low_priority_updates            = 1
concurrent_insert               = 2

# Connection Settings 
# Max_used_connections / max_connections * 100% ≈ 85%
max_connections                 = 1000   # UPD

back_log                        = 512
thread_cache_size               = 100
thread_stack                    = 192K

interactive_timeout             = 180
wait_timeout                    = 180

# For MySQL 5.7+ only (disabled by default)
# max_execution_time             = 30000 # Set a timeout limit for SELECT statements (value in milliseconds).
                                        # This option may be useful to address aggressive crawling on large sites,
                                        # but it can also cause issues (e.g. with backups). So use with extreme caution and test!
                                        # More info at: https://dev.mysql.com/doc/refman/5.7/en/server-system-variables.html#sysvar_max_execution_time

# For MariaDB 10.1.1+ only (disabled by default)
#max_statement_time             = 30    # The equivalent of "max_execution_time" in MySQL 5.7+ (set above)
                                        # The variable is of type double, thus you can use subsecond timeout.
                                        # For example you can use value 0.01 for 10 milliseconds timeout.
                                        # More info at: https://mariadb.com/kb/en/aborting-statements/

# Buffer Settings
join_buffer_size                = 4M    # UPD
read_buffer_size                = 3M    # UPD
read_rnd_buffer_size            = 4M    # UPD
sort_buffer_size                = 4M    # UPD

# Table Settings
# In systemd managed systems like Ubuntu 16.04+ or CentOS 7+, you need to perform an extra action for table_open_cache & open_files_limit
# to be overriden (also see comment next to open_files_limit).
# E.g. for MySQL 5.7, please check: https://dev.mysql.com/doc/refman/5.7/en/using-systemd.html
# and for MariaDB check: https://mariadb.com/kb/en/library/systemd/
table_definition_cache          = 40000 # UPD
table_open_cache                = 40000 # UPD
open_files_limit                = 60000 # UPD - This can be 2x to 3x the table_open_cache value or match the system's
                                        # open files limit usually set in /etc/sysctl.conf or /etc/security/limits.conf
                                        # In systemd managed systems this limit must also be set in:
                                        # /etc/systemd/system/mysqld.service.d/override.conf (for MySQL 5.7+) and
                                        # /etc/systemd/system/mariadb.service.d/override.conf (for MariaDB)

max_heap_table_size             = 128M
tmp_table_size                  = 128M

# Search Settings
ft_min_word_len                 = 3     # Minimum length of words to be indexed for search results

# Logging
# log-error=/var/log/mysql/mysql_error.log
# log_queries_not_using_indexes   = 0
# long_query_time                 = 2
# slow_query_log = 1     # Disabled for production
# slow_query_log_file = /var/log/mysql/mysql_slow.log

[mysqldump]
# Variable reference
# For MySQL 5.7: https://dev.mysql.com/doc/refman/5.7/en/mysqldump.html
# For MariaDB:   https://mariadb.com/kb/en/library/mysqldump/
quick
quote_names
max_allowed_packet              = 64M

[client]
# 设置客户端编码
default-character-set=utf8
```
19. 启动
```
[root@vultr mysql]# /etc/init.d/mysql start
Starting MySQL.Logging to '/usr/local/mysql/data/error.log'.
... SUCCESS!
```
20. 查看启动状态
```
[root@vultr mysql]# service mysql status
 SUCCESS! MySQL running (28162)
```
21. 添加系统路径
```
(1) vim /etc/profile
(2) export PATH=/usr/local/mysql/bin:$PATH
(3) source /etc/profile
```
22. 登录
```
[root@vultr mysql]# mysql -u root -p
Enter password:
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 8
Server version: 8.0.22

Copyright (c) 2000, 2020, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql>
```
23. 修改密码并使之生效
```
mysql> alter user 'root'@'localhost' identified by 'qwe!23';
Query OK, 0 rows affected (0.01 sec)

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```
24. 选择 mysql 数据库
```
mysql> use mysql;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
```
25. 修改远程连接并生效 
```
mysql> update user set host='%' where user='root';
Query OK, 1 row affected (0.01 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> flush privileges;
Query OK, 0 rows affected (0.00 sec)
```
