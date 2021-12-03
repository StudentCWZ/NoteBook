# Redis 入门介绍
## Redis 入门概述
### Redis 是什么
1. Redis：REmote DIctionary Server(远程字典服务器)
2. Redis 是完全开源免费的，用 C 语言编写的，遵守 BSD 协议，是一个高性能的(Key/Value)分布式内存数据库，基于内存运行并支持持久化的 NoSQL 数据库，是当前最热门的 NoSQL 数据库之一，也被人们称为数据结构服务器。
3. Redis 与其他 Key-Value 缓存产品有以下三个特点：
    - Redis 支持数据的持久化，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用
    - Redis 不仅仅支持简单的 key-value 类型的数据，同时还提供 list，set，zset，hash 等数据结构的存储
    - Redis 支持数据的备份，即 master-slave 模式的数据备份

### Redis 能干嘛
1. 内存存储和持久化：Redis 支持异步将内存中的数据写到硬盘上，同时不影响继续服务。
2. 取最新 N 个数据的操作，如：可以将最新的 10 条评论的 ID 放在 Redis 的 List 集合里面。
3. 模拟类似于 HttpSession 这种需要设定过期时间的功能
4. 发布、订阅消息系统。
5. 定时器、计数器。

### Redis 去哪里下
1. http://redis.io/
2. http://www.redis.cn/

## Redis 安装
### Windows 下安装
1. [Redis 下载地址](!https://github.com/dmajkic/redis/downloads)
2. 下载的 Redis 支持 32 bit 和 64 bit
3. 根据自己实际情况选择，将 64 bit 的内容 cp 到自定义盘符安装目录取名 redis
4. 打开一个 cmd 窗口，使用 cd 命令切换目录到 C:\redis 运行 redis-server.exe redis.conf
5. 如果想要方便的话，可以把 redis 的路径加到系统的环境变量里，这样省得再输路径了，后面的那个 redis.conf 可以省略
6. 这时候另起一个 cmd 窗口，原来的不要关闭，不然就无法访问服务端了
7. 切换到 redis 目录下运行 redis-cli.exe -h 127.0.0.1 -p 6379
8. 设置键值对 set myKey abc
9. 取出键值对 get myKey

### Linux 下安装
1. 下载获得 redis-3.0.4.tar.gz 后将它放入我们的 Linux 目录 /opt
2. /opt 目录下，解压命令：tar -zxvf redis-3.0.4.tar.gz
3. 解压完成后出现文件夹：redis-3.0.4
4. 进入目录：cd redis-3.0.4
5. 在 redis-3.0.4 目录下执行 make 命令
6. 如果 make 完成后继续执行 make install
7. 查看默认安装目录：/usr/local/bin
8. 启动
9. 关闭
    - shutdown
    - exit

## Redis 基础
1. 单进程模型处理客户端的请求。对读写等事件的响应是通过对 epoll 函数的包装来做到的。
2. Redis 的实际处理速度完全依靠主进程的执行效率。
3. Epoll 是 Linux 内核为处理大批量文件描述符。
4. 而做了改进的 epoll，是 Linux 下复用 IO 接口，它能显著提高程序在大量并发连接中只有少量活跃的情况下的系统 CPU 利用率。
5. 默认 16 个数据库，类似数组下标从零开始，初始默认使用零号库。
6. Select 命令切换数据库。
7. DBSIZE 查看当前数据库的 key 的数量。
8. FLUSHDB 清空当前库。
9. FLUSHALL 通杀全部库。
10. 统一密码管理，16 个库都是同样密码，那么都 ok，要么一个也连不上。
11. Redis 索引都是从零开始。
12. 默认端口是 6379 。
