# Redis 数据类型
## Redis 的五大数据类型
### String(字符串)
1. string 是 redis 最基本的类型，你可以理解与 Memcached 一模一样的类型，一个 key 对应一个 value 。
2. string 类型是二进制安全的，意思是 redis 的 string 可以包含任何数据，比如 jpg 图片或者序列化的对象。
3. string 类型是 redis 最基本的数据类型，一个 redis 中字符串 value 最多可以是 512M 。

### Hash(哈希)
1. Redis hash 是一个键值对集合。
2. Redis hash 是一个 string 类型的 field 和 value 的映射，hash 特别适合用于存储对象。

### List(列表)
1. Redis 列表是简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部(左边)或者尾部(右边)。
2. Redis 列表底层实际上是个链表。

### Set(集合)
1. Redis 的 Set 是 string 类型的无序集合，它是通过 HashTable 实现的。

### Zset(有序集合)
1. Redis zset 和 set 一样也是 string 类型元素的集合，且不允许重复的成员。
2. 不同的是每个元素都会关联一个 double 类型的分数。
3. redis 正是通过分数作为集合中的成员进行大小排序，zset 的成员是唯一的，但是分数(score)却可以重复。

## Redis 键(Key)
1. 概述
```
1. key *
2. exists key: 判断某个 key 是否存在
3. move key db: 当前库就没有了，被移除了
4. expire key 秒钟: 为给定的 key 设置过期时间
5. ttl key: 查看还有多少秒过期，-1 表示永不过期，-2 表示已过期
6. type key: 查看你的 key 的类型
```

## String(字符串)
1. set 操作
```
127.0.0.1:6379> set k1 hello
OK
```
2. get 操作
```
127.0.0.1:6379> get k1
"hello"
```
3. del 操作
```
127.0.0.1:6379> del k1
(integer) 1
127.0.0.1:6379> get k1
(nil)
```
4. append 操作
```
127.0.0.1:6379> set k1 v1
OK
127.0.0.1:6379> get k1
"v1"
127.0.0.1:6379> append k1 12345
(integer) 7
127.0.0.1:6379> get k1
"v112345"
```
5. strlen 操作
```
127.0.0.1:6379> STRLEN k1
(integer) 7
```
6. Incr 操作
```
127.0.0.1:6379> set k2 2
OK
127.0.0.1:6379> INCR k2
(integer) 3
127.0.0.1:6379> get k2
"3"
```
7. decr 操作
```
127.0.0.1:6379> decr k2
(integer) 2
127.0.0.1:6379> get k2
"2"
```
8. incrby 操作
```
127.0.0.1:6379> INCRBY k2 3
(integer) 5
127.0.0.1:6379> get k2
"5"
```
9. decrby 操作
```
127.0.0.1:6379> DECRBY k2 2
(integer) 3
127.0.0.1:6379> get k2
"3"
```
10. getrange 操作
```
127.0.0.1:6379> GETRANGE k1 0 -1
"v112345"
```
11. setrange 操作
```
127.0.0.1:6379> setrange k1 0 xxx
(integer) 7
```
12. setex(set with expire) 操作
```
127.0.0.1:6379> SETEX k4 10 v4
OK
127.0.0.1:6379> get k4
(nil)
127.0.0.1:6379> ttl k4
(integer) -2
```
13. setnx(self if not exist) 操作
```
127.0.0.1:6379> SETNX k1 v11
(integer) 0
127.0.0.1:6379> get k1
"xxx2345"
127.0.0.1:6379> setnx k11 v11
(integer) 1
127.0.0.1:6379> get k11
"v11"
```
14. mset 操作
```
127.0.0.1:6379> mset k1 v1 k2 v2 k3 v3
OK
```
15. mget 操作
```
127.0.0.1:6379> mget k1 k2 k3
1) "v1"
2) "v2"
3) "v3"
```
16. msetnx 操作
```
127.0.0.1:6379> msetnx k3 v3 k4 v4
(integer) 0
127.0.0.1:6379> mget k3 k4
1) "v3"
2) (nil)
127.0.0.1:6379> msetnx k4 v4 k5 v5
(integer) 1
127.0.0.1:6379> mget k4 k5
1) "v4"
2) "v5"
```

## List(列表)
1. lpush 操作
```
127.0.0.1:6379> LPUSH list01 1 2 3 4 5
(integer) 5
127.0.0.1:6379> LRANGE list01 0 -1
1) "5"
2) "4"
3) "3"
4) "2"
5) "1"
```
2. rpush 操作
```
127.0.0.1:6379> rpush list02 1 2 3 4 5
(integer) 5
127.0.0.1:6379> LRANGE list02 0 -1
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
```
3. lrange 操作
```
127.0.0.1:6379> LRANGE list02 0 -1
1) "1"
2) "2"
3) "3"
4) "4"
5) "5"
```
4. lpop 操作
```
127.0.0.1:6379> lpop list01
"5"
127.0.0.1:6379> lpop list02
"1"
```
5. rpop 操作
```
127.0.0.1:6379> rpop list01
"1"
127.0.0.1:6379> rpop list02
"5"
```
6. lindex 操作
```
127.0.0.1:6379> LINDEX list01 2
"2"
127.0.0.1:6379> LINDEX list02 2
"4"
```
7. llen 操作
```
127.0.0.1:6379> llen list01
(integer) 3
127.0.0.1:6379> llen list02
(integer) 3
```
8. lrem 操作
```
127.0.0.1:6379> RPUSH list03 1 1 1 2 2 2 3 3 3 4 4 4 5 6 7
(integer) 15
127.0.0.1:6379> lrem list03 2 3
(integer) 2
```
9. ltrim 操作
```
127.0.0.1:6379> lpush list04 0 1 2 3 4 5 6 7 8 9
(integer) 10
127.0.0.1:6379> lrange list04 0 -1
 1) "9"
 2) "8"
 3) "7"
 4) "6"
 5) "5"
 6) "4"
 7) "3"
 8) "2"
 9) "1"
10) "0"
127.0.0.1:6379> LTRIM list04 0 4
OK
127.0.0.1:6379> lrange list04 0 -1
1) "9"
2) "8"
3) "7"
4) "6"
5) "5"
```
10. rpoplpush 操作
```
127.0.0.1:6379> RPOPLPUSH list04 list02
"5"
127.0.0.1:6379> lrange list02 0 -1
1) "5"
2) "2"
3) "3"
4) "4"
```
11. lset 操作
```
127.0.0.1:6379> lset list01 1 x
OK
127.0.0.1:6379> lrange list01 0 -1
1) "4"
2) "x"
3) "2"
```
12. linset 操作
```
127.0.0.1:6379> LINSERT list01 before x java
(integer) 4
127.0.0.1:6379> lrange list01 0 -1
1) "4"
2) "java"
3) "x"
4) "2"
127.0.0.1:6379> LINSERT list01 after x oracle
(integer) 5
127.0.0.1:6379> lrange list01 0 -1
1) "4"
2) "java"
3) "x"
4) "oracle"
5) "2"
```
13. 性能总结
    - List 是一个字符串链表，left、right 都可以插入添加
    - 如果键不存在，创建新的链表
    - 如果值全移除，对应的键也就消失了。
    - 链表的操作无论是头和尾效率都极高，但假如是对中间元素进行操作，效率就很惨淡了。

## Set(集合)
1. sadd 操作
```
127.0.0.1:6379> sadd set01 1 1 2 2 3 3
(integer) 3
127.0.0.1:6379> SMEMBERS set01
1) "1"
2) "2"
3) "3"
```
2. smembers 操作
```
127.0.0.1:6379> SMEMBERS set01
1) "1"
2) "2"
3) "3"
```
3. sismember 操作
```
127.0.0.1:6379> SISMEMBER set01 1
(integer) 1
127.0.0.1:6379> SISMEMBER set01 x
(integer) 0
```
4. scard 操作
```
127.0.0.1:6379> SCARD set01
(integer) 3
```
5. srem 操作
```
127.0.0.1:6379> srem set01 3
(integer) 1
127.0.0.1:6379> SMEMBERS set01
1) "1"
2) "2"
```
6. srandmember 操作
```
127.0.0.1:6379> sadd set01 1 2 3 4 5 6 7 8
(integer) 6
127.0.0.1:6379> SRANDMEMBER set01 3
1) "8"
2) "2"
3) "1"
127.0.0.1:6379> SRANDMEMBER set01 3
1) "8"
2) "2"
3) "6"
127.0.0.1:6379> SRANDMEMBER set01 3
1) "8"
2) "4"
3) "3"
```
7. spop 操作
```
127.0.0.1:6379> spop set01
"5"
127.0.0.1:6379> spop set01
"2"
127.0.0.1:6379> SMEMBERS set01
1) "1"
2) "3"
3) "4"
4) "6"
5) "7"
6) "8"
```
8. smove 操作
```
127.0.0.1:6379> sadd set02 x y z
(integer) 3
127.0.0.1:6379> smove set01 set02 4
(integer) 1
127.0.0.1:6379> SMEMBERS set02
1) "x"
2) "4"
3) "z"
4) "y"
```
9. sdiff 操作
```
127.0.0.1:6379> sadd set03 1 2 3 4 5
(integer) 5
127.0.0.1:6379> sadd set04 1 2 3 a b
(integer) 5
127.0.0.1:6379> SDIFF set03 set04
1) "4"
2) "5"
```
10. sinter 操作
```
127.0.0.1:6379> SINTER set03 set04
1) "1"
2) "2"
3) "3"
```
11. sunion 操作
```
127.0.0.1:6379> SUNION set03 set04
1) "2"
2) "1"
3) "4"
4) "a"
5) "b"
6) "3"
7) "5"
```

## Hash(哈希)
1. hset 操作
```
127.0.0.1:6379> hset user id 11
(integer) 1
127.0.0.1:6379> hget user id
"11"
```
2. hget 操作
```
127.0.0.1:6379> hget user id
"11"
```
3. hmset 操作
```
127.0.0.1:6379> hmset customer id 11 name lisi age 26
OK
```
4. hmget 操作
```
127.0.0.1:6379> hmget customer id name
1) "11"
2) "lisi"
```
5. hgetall 操作
```
127.0.0.1:6379> HGETALL customer
1) "id"
2) "11"
3) "name"
4) "lisi"
5) "age"
6) "26"
```
6. hdel 操作
```
127.0.0.1:6379> hdel customer name
(integer) 1
127.0.0.1:6379> HGETALL customer
1) "id"
2) "11"
3) "age"
4) "26"
127.0.0.1:6379> hset customer name lisi
(integer) 1
127.0.0.1:6379> HGETALL customer
1) "id"
2) "11"
3) "age"
4) "26"
5) "name"
6) "lisi"
```
7. hlen 操作
```
127.0.0.1:6379> hlen customer
(integer) 3
```
8. hexists 操作
```
127.0.0.1:6379> HEXISTS customer id
(integer) 1
127.0.0.1:6379> HEXISTS customer email
(integer) 0
```
9. hkeys 操作
```
127.0.0.1:6379> HKEYS customer
1) "id"
2) "age"
3) "name"
```
10. hvals 操作
```
127.0.0.1:6379> hvals customer
1) "11"
2) "26"
3) "lisi"
```
11. hincrby 操作
```
127.0.0.1:6379> HINCRBY customer age 2
(integer) 28
127.0.0.1:6379> HINCRBY customer age 2
(integer) 30
127.0.0.1:6379> HINCRBY customer age 2
(integer) 32
```
12. hincrbyfloat 操作
```
127.0.0.1:6379> hset customer score 91.5
(integer) 1
127.0.0.1:6379> HGETALL customer
1) "id"
2) "11"
3) "age"
4) "32"
5) "name"
6) "lisi"
7) "score"
8) "91.5"
127.0.0.1:6379> HINCRBYFLOAT customer score 0.5
"92"
127.0.0.1:6379> HGETALL customer
1) "id"
2) "11"
3) "age"
4) "32"
5) "name"
6) "lisi"
7) "score"
8) "92"
```
13. hsetnx 操作
```
127.0.0.1:6379> hsetnx customer age 25
(integer) 0
127.0.0.1:6379> hsetnx customer email abc@126.com
(integer) 1
127.0.0.1:6379> HGETALL customer
 1) "id"
 2) "11"
 3) "age"
 4) "32"
 5) "name"
 6) "lisi"
 7) "score"
 8) "92"
 9) "email"
10) "abc@126.com"
```

## Zset(有序集合)
1. zadd 操作
```
127.0.0.1:6379> zadd zset01 60 v1 70 v2 80 v3 90 v4 100 v5
(integer) 5
```
2. zrange 操作
```
127.0.0.1:6379> ZRANGE zset01 0 -1
1) "v1"
2) "v2"
3) "v3"
4) "v4"
5) "v5"
127.0.0.1:6379> ZRANGE zset01 0 -1 withscores
 1) "v1"
 2) "60"
 3) "v2"
 4) "70"
 5) "v3"
 6) "80"
 7) "v4"
 8) "90"
 9) "v5"
10) "100"
```
3. zrangebyscore 操作
```
127.0.0.1:6379> ZRANGEBYSCORE zset01 60 90
1) "v1"
2) "v2"
3) "v3"
4) "v4"
127.0.0.1:6379> ZRANGEBYSCORE zset01 60 (90
1) "v1"
2) "v2"
3) "v3"
127.0.0.1:6379> ZRANGEBYSCORE zset01 (60 (90
1) "v2"
2) "v3"
127.0.0.1:6379> ZRANGEBYSCORE zset01 60 90 limit 2 2
1) "v3"
2) "v4"
```
4. zrem 操作
```
127.0.0.1:6379> zrem zset01 v5
(integer) 1
127.0.0.1:6379> ZRANGE zset01 0 -1 withscores
1) "v1"
2) "60"
3) "v2"
4) "70"
5) "v3"
6) "80"
7) "v4"
8) "90"
```
5. zcard 操作
```
127.0.0.1:6379> zcard zset01
(integer) 4
```
6. zcount 操作
```
127.0.0.1:6379> zcount zset01 60 80
(integer) 3
```
7. zrank 操作
```
127.0.0.1:6379> ZRANk zset01 v4
(integer) 3
```
8. zscore 操作
```
127.0.0.1:6379> zscore zset01 v4
"90"
```
9. zrevrank 操作
```
127.0.0.1:6379> ZREVRANk zset01 v4
(integer) 0
```
10. zrevrange 操作
```
127.0.0.1:6379> ZREVRANGE zset01 0 -1
1) "v4"
2) "v3"
3) "v2"
4) "v1"
```
11. zrevrangebyscore 操作
```
127.0.0.1:6379> ZREVRANGEBYSCORE zset01 90 60
1) "v4"
2) "v3"
3) "v2"
4) "v1"
```
