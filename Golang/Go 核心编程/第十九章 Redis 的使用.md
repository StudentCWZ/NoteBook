# Redis 的使用
## Redis 的基本介绍
1. Redis 是 NoSQL 数据库，不是传统的关系型数据库。
```
(1) 官网：https://redis.io/ 和 http://www.redis.cn/
```
2. Redis: Remote Dictionary Server(远程字典服务器)，Redis 性能非常高，单机能够达到 15W qps，通常适合做缓存，也可以持久化。
3. Redis 是完全开源免费的、高性能的(key/value)分布式内存数据库，基于内存运行并支持持久化的 NoSQL 数据库，是最热门的 NoSql 数据库之一，也称为数据结构服务器。

## Redis 的安装和基本使用
1. 下载后直接解压就有 Redis 的服务器端程序(redis-server.exe)和客户端程序(redis-cli.exe)，直接双击即可运行，并不需要安装。

### Redis 的启动
1. 启动 Redis 的服务器端程序(redis-server.exe)，直接双击即可运行。

### Redis 操作的三种方式
1. 使用 telnet 操作 Redis
2. 使用 redis-cli 操作 Redis
3. Golang 操作 Redis

### Redis 的命令
1. Redis 命令一览：http://redisdoc.com

### Redis 的快速入门
1. 说明：Redis 安装好后，默认有16个数据库，初始默认使用0号库，编号是0...15。
2. 基本命令
```
(1) 添加 key-value [set]
set key1 hello
(2) 查看当前 redis 的所有 key [keys *]
keys *
(3) 获取 key 对应的值 [get key]
get key1
(4) 切换 redis 数据库 [select index]
select 1
(5) 如何查看当前数据库的 key-value [dbsize]
dasize 1
(6) 清空当前数据库的 key-value 和清空所有数据库的 key-value [flushdb flushall]
```

## Redis 数据类型和 CRUD
### Redis 的五大数据类型
1. Redis 的五大数据类型是：String(字符串)、Hash(哈希)、List(列表)、Set(集合)和Zset(sorted set：有序集合)。

### Redis CRUD 操作
#### Redis 中对 String 的基本使用
##### String 介绍
1. String 是 redis 最基本的类型，一个 key 对应一个 value。
2. String 类型是二进制安全的。除普通的字符串外，也可以存放图片等数据。
3. redis 中字符串 value 最大是 512M

##### Redis 操作 String
1. 举例
```
set address beijing
```
2. String 字符串的 CRUD 操作
```
set(如果存在就相当于修改，不存在就是添加)/get/del
```
3. Setex(Set with expire)键 秒 值
```
setex key seconds value
```
4. mset(同时设置一个或多个 key-value 对)
```
mset key value key1 value1 ...
```
5. mget(一次性返回多个 key 的值)
```
mget key key1
```

#### Redis 中对 Hash 的基本使用
##### Hash 介绍
1. Redis Hash 是一个键值对集合。
2. Redis Hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象。

##### Redis 操作 Hash
1. 举例
```
hset user1 name "smith"
hset user1 age 30
hset user1 job "golang coder"
```
2. hget
```
hget user1 name
```
3. hgetall
```
hgetall user1
```
4. hdel
```
hdel user1
```
5. hmset(一次性设置多个 field 的值)
```
hmset user1 name "smith" age 30 job "golang coder"
```
6. hmget(一次性返回多个 field 的值)
```
hmget user1 name age
```
7. hlen(统计一个 hash 有多少个元素)
```
hlen user1
```
8. hexits key field(查看哈希表 key 中，给定域 field 是否存在)
```
hexits user1 name
```

#### Redis 中对 List 的基本使用
##### List 介绍
1. 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部(左边)或者尾部(右边)
2. List 本质是一个链表，List 的元素是有序的，元素的值可以重复。

##### Redis 操作 List
1. 举例
```
lpush city beijing shanghai shengzhen
```
2. lrang
```
lrange city 0 -1
```
3. List 的 CRUD 操作
```
lpush/rpush/lrange/lpop/rpop/del
```
4. 举例
```
lpush herolist aaa bbb ccc
lrange herolist 0 -1
取出的数据：ccc bbb aaa
rpush herolist ddd eee
lrange herolist 0 -1
取出的数据：ccc bbb aaa ddd eee
lpop herolist
弹出的数据：ccc
lrange herolist 0 -1
取出的数据：bbb aaa ddd eee
rpop herolist
弹出的数据：eee
取出的数据：bbb aaa ddd
del herolist
删除 herolist
```

##### List 使用细节和注意事项
1. index ，按照索引下标获得元素(从左到右，编号从0开始)
2. llen key
```
(1) 返回列表 key 的长度，如果 key 不存在，则 key 被解释为一个空列表，返回 0。
```
3. List 的其他说明
```
(1) List 数据，可以从左或者从右插入添加。
(2) 如果值全移除，对应的键也就消失了。
```

#### Redis 中对 Set 的基本使用
##### Set 介绍
1. Redis 的 Set 是 String 类型的无序集合。
2. Set 底层是 Hash Table 数据结构，Set 也是存放很多字符串元素，字符串元素是无序的，而且元素的值不能重复。

##### Redis 操作 Set
1. 举例
```
sadd emails sgg@sohu.com tom@sohu.com
```
2. smembers
```
smembers emails
```
3. Set 的 CRUD 操作
```
sadd/smembers(取出所有值)/sismembers(判断值是否是成员)/srem(删除指定值)
```

## Golang 操作 Redis
### 安装第三方开源 Redis 库
1. 使用第三方开源的 Redis 库：github.com/garyburd/redigo/redis
2. 在使用 Redis 前，先安装第三方 Redis 库，在 GOPATH 路径下执行指令
```
D:\goproject>go get github.com/gaurd/redigo/redis
```
3. 特别说明：在安装 Redis 库前，确保已经安装并配置了 Git，因为是从 github 下载安装 Redis 库，需要使用 Git。如果没有安装配置过 Git。

### Set/Get 接口
1. 说明：通过 Golang 添加和获取 key-value。
```
package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
)

func main() {
	// 通过 go 向 redis 写入数据和读取数据
	// 1. 链接到 redis
	conn, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err != nil {
		fmt.Println("redis.Dial err =", err)
		return
	}
	fmt.Println("Connect successful...", conn)

	defer conn.Close() //关闭

	// 2. 通过 go 向 redis 写入数据 string [key-value]
	_, err = conn.Do("Set", "name", "Tom")
	if err != nil {
		fmt.Println("Set err =", err)
		return
	}
	fmt.Println("Insert data successful...")

	// 3. 通过 go 向 redis 读取数据 string [key-value]
	str, err := redis.String(conn.Do("Get", "name"))
	if err != nil {
		fmt.Println("Get err =", err)
		return
	}

	fmt.Println("Get data successful...", str)
}
```

### 操作 Hash
1. 说明：通过 Golang 对 Redis 操作 Hash 数据类型。
2. 举例
```
package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
)

func main() {
	// 通过 go 向 redis 写入数据和读取数据
	// 1. 链接到 redis
	conn, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err != nil {
		fmt.Println("redis.Dial err =", err)
		return
	}
	fmt.Println("Connect successful...", conn)

	defer conn.Close() //关闭

	// 2. 通过 go 向 redis 写入数据
	_, err = conn.Do("HSet", "user1", "name", "John")
	if err != nil {
		fmt.Println("HSet err =", err)
		return
	}

	fmt.Println("Insert data successful...")

	_, err = conn.Do("HSet", "user1", "age", 18)
	if err != nil {
		fmt.Println("HSet err =", err)
		return
	}

	fmt.Println("Insert data successful...")

	// 3. 通过 go 向 redis 读取数据
	str, err := redis.String(conn.Do("HGet", "user1", "name"))
	if err != nil {
		fmt.Println("HGet err =", err)
		return
	}
	fmt.Println("Get data successful...", str)

	age, err := redis.Int(conn.Do("HGet", "user1", "age"))
	if err != nil {
		fmt.Println("HGet err =", err)
		return
	}

	fmt.Println("Get data successful...", age)
}
```
3. 批量操作 hash
```
package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
)

func main() {
	// 通过 go 向 redis 写入数据和读取数据
	// 1. 链接到 redis
	conn, err := redis.Dial("tcp", "127.0.0.1:6379")
	if err != nil {
		fmt.Println("redis.Dial err =", err)
		return
	}
	fmt.Println("Connect successful...", conn)

	defer conn.Close() //关闭

	// 2. 通过 go 向 redis 写入数据
	_, err = conn.Do("HMSet", "user2", "name", "Tom", "age", 19)
	if err != nil {
		fmt.Println("HMSet err =", err)
		return
	}

	fmt.Println("Insert data successful...")


	// 3. 通过 go 向 redis 读取数据
	str, err := redis.Strings(conn.Do("HMGet", "user2", "name", "age"))
	if err != nil {
		fmt.Println("HMGet err =", err)
		return
	}

	// 4. 遍历取值
	for index, value := range str {
		fmt.Printf("str[%d] = %v \n", index, value)
	}

	fmt.Println("Get data successful...")

}
```

### 批量 Set/Get 数据
1. 说明：通过 Golang 对 Redis 操作，一次操作可以 Set/Get 多个 key-value 数据。
2. 举例
```
_, err = conn.Do("Mset", "name", "尚硅谷", "address", "北京昌平")
str, err := redis.Strings(conn.Do("Mget", "name", "adress"))

for _, value := range str {
    fmt.Println(value)
}
```

### 给数据设置有效时间
1. 说明：通过 Golang 对 Redis 操作，给 key-value 设置有效时间。
2. 举例
```
_, err = conn.Do("expire", "name", 10)
```

### 操作 List
1. 说明：通过 Golang 对 Redis 操作 List 数据类型。
2. 举例
```
_, err = conn.Do("lpush", "herolist", "no1:宋江", 30, "no2:卢俊义", 28)
str, err := redis.String(conn.DO("rpop", "herolist"))
```

## Redis 链接池
1. 说明：通过 Golang 对 Redis 操作，还可以通过 Redis 链接池。
2. 链接池流程如下：
```
(1) 事先初始化一定数量的链接，放入链接池
(2) 当 Go 需要操作 Redis 时，直接从 Redis 链接池取出链接即可。
(3) 这样可以节省临时获取 Redis 链接时间，从而提高效率。
```
3. 举例
```
package main

import (
	"fmt"
	"github.com/gomodule/redigo/redis"
)

//定义一个全局的 pool
var pool *redis.Pool

//当启动程序时，就初始化链接池
func init() {
	pool = &redis.Pool{
		MaxIdle: 8, // 最大空闲链接数
		MaxActive: 0, // 表示和数据库的最大链接数，0 表示没有限制
		IdleTimeout: 100, // 最大空闲时间
		Dial: func() (conn redis.Conn, e error) { // 初始化链接代码
			return redis.Dial("tcp", "localhost:6379")
		},
	}
}

func main() {
	// 先从 pool 取出一个链接
	conn := pool.Get()
	defer conn.Close() // 延时关闭

	// 插入数据
	_, err := conn.Do("Set", "name", "Tom")
	if err != nil {
		fmt.Println("Set err =", err)
		return
	}
	fmt.Println("Insert data successful...")

	// 取出数据
	str, err := redis.String(conn.Do("get", "name"))
	if err != nil {
		fmt.Println("Get err =", err)
		return
	}
	fmt.Println("Get data successful...")
	fmt.Println("str =", str)
	
	//defer pool.Close()

}
```

