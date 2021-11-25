# MySQL 初步学习
## 如何使用终端操作数据库
1. 登录数据库服务器
```
mysql -u root -ppassword
```
2. 查询数据库服务器中的所有数据库
```
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| bank               |
| mysql              |
| performance_schema |
| selectTest         |
| sys                |
| testJoin           |
+--------------------+
7 rows in set (0.01 sec)
```
3. 选中一个数据库进行操作
```
mysql> use bank;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

Database changed
```
4. SQL 语句中的查询
```
mysql> select * from user;
+----+-----------+-------+
| id | name      | money |
+----+-----------+-------+
|  1 | a         |   800 |
|  2 | b         |  1200 |
|  3 | 小明      |  1000 |
|  4 | 淘宝店    |  1000 |
|  5 | c         |   100 |
|  6 | d         |  1000 |
|  7 | 赵铁柱    |  1000 |
+----+-----------+-------+
7 rows in set (0.00 sec)
```
5. 查询时可增加相应条件
```
mysql> select * from user where id=1;
+----+------+-------+
| id | name | money |
+----+------+-------+
|  1 | a    |   800 |
+----+------+-------+
1 row in set (0.00 sec)
```
6. 退出数据库服务器
```
mysql> exit;
Bye
```
7. 在数据库服务器中创建我们的数据库
```
mysql> create database test;
Query OK, 1 row affected (0.00 sec)

mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| bank               |
| mysql              |
| performance_schema |
| selectTest         |
| sys                |
| test               |
| testJoin           |
+--------------------+
8 rows in set (0.00 sec)
```
8. 使用 test 数据库
```
mysql> use test;
Database changed
```
9. 查看 test 数据库中的数据表
```
mysql> show tables;
Empty set (0.00 sec)
```
10. 创建一个数据表
```
mysql> create table pet(
    -> name varchar(20),
    -> owner varchar(20),
    -> species varchar(20),
    -> sex char(1),
    -> birth date,
    -> death date
    -> );
Query OK, 0 rows affected (0.02 sec)
```
11. 查看数据表是否创建成功
```
mysql> show tables;
+----------------+
| Tables_in_test |
+----------------+
| pet            |
+----------------+
1 row in set (0.00 sec)
```
12. 查看数据表结构
```
mysql> describe pet;
+---------+-------------+------+-----+---------+-------+
| Field   | Type        | Null | Key | Default | Extra |
+---------+-------------+------+-----+---------+-------+
| name    | varchar(20) | YES  |     | NULL    |       |
| owner   | varchar(20) | YES  |     | NULL    |       |
| species | varchar(20) | YES  |     | NULL    |       |
| sex     | char(1)     | YES  |     | NULL    |       |
| birth   | date        | YES  |     | NULL    |       |
| death   | date        | YES  |     | NULL    |       |
+---------+-------------+------+-----+---------+-------+
6 rows in set (0.00 sec)
```
13. 查看数据表中的记录
```
mysql> select * from pet;
Empty set (0.00 sec)
```
14. 往数据表中添加数据记录
```
mysql> insert into pet values('Puffball', 'Diane', 'hamster', 'f', '1999-03-30', NULL);
Query OK, 1 row affected (0.00 sec)
```
15. 再一次查询数据表中的记录
```
mysql> select * from pet;
+----------+-------+---------+------+------------+-------+
| name     | owner | species | sex  | birth      | death |
+----------+-------+---------+------+------------+-------+
| Puffball | Diane | hamster | f    | 1999-03-30 | NULL  |
+----------+-------+---------+------+------------+-------+
1 row in set (0.00 sec)
```
16. 更改字符集
```
alter table pet change name name varchar(20) character set utf8;
alter table pet change owner owner varchar(20) character set utf8;
alter table pet change species species varchar(20) character set utf8;
alter table pet change sex sex char(1) character set utf8;
```
17. 再插入数据
```
mysql> insert into pet values('旺财', '周星驰', '狗', '公', '1990-01-01', NULL);
Query OK, 1 row affected (0.00 sec)
```
18. 再一次查询数据表中的记录
```
mysql> select * from pet;
+----------+-----------+---------+------+------------+-------+
| name     | owner     | species | sex  | birth      | death |
+----------+-----------+---------+------+------------+-------+
| Puffball | Diane     | hamster | f    | 1999-03-30 | NULL  |
| 旺财     | 周星驰    | 狗      | 公   | 1990-01-01 | NULL  |
+----------+-----------+---------+------+------------+-------+
2 rows in set (0.00 sec)
```
19. 插入数据
```
insert into pet values('Fluffy', 'Harold', 'cat', 'f', '1993-02-04', NULL);
insert into pet values('Claws', 'Gwen', 'cat', 'm', '1994-03-17',NULL);
insert into pet values('Buffy', 'Harold', 'dog', 'f', '1989-05-13', NULL);
insert into pet values('Fang', 'Benny', 'dog', 'm', '1990-08-27', NULL);
insert into pet values('Bowser', 'Diane', 'dog', 'm', '1979-08-31', '1995-07-29');
insert into pet values('Chirpy', 'Gwen', 'bird', 'f', '1998-09-11', NULL);
insert into pet values('Whistler', 'Gwen', 'bird', NULL, '1997-12-09', NULL);
insert into pet values('Slim', 'Benny', 'snake', 'm', '1996-04-29', NULL);
insert into pet values('Puffball', 'Diane', 'hamster', 'f', '1999-03-30', NULL);
```
20. 再一次查询数据表中的记录
```
mysql> select * from pet;
+----------+-----------+---------+------+------------+------------+
| name     | owner     | species | sex  | birth      | death      |
+----------+-----------+---------+------+------------+------------+
| Puffball | Diane     | hamster | f    | 1999-03-30 | NULL       |
| 旺财     | 周星驰    | 狗      | 公   | 1990-01-01 | NULL       |
| Fluffy   | Harold    | cat     | f    | 1993-02-04 | NULL       |
| Claws    | Gwen      | cat     | m    | 1994-03-17 | NULL       |
| Buffy    | Harold    | dog     | f    | 1989-05-13 | NULL       |
| Fang     | Benny     | dog     | m    | 1990-08-27 | NULL       |
| Bowser   | Diane     | dog     | m    | 1979-08-31 | 1995-07-29 |
| Chirpy   | Gwen      | bird    | f    | 1998-09-11 | NULL       |
| Whistler | Gwen      | bird    | NULL | 1997-12-09 | NULL       |
| Slim     | Benny     | snake   | m    | 1996-04-29 | NULL       |
| Puffball | Diane     | hamster | f    | 1999-03-30 | NULL       |
+----------+-----------+---------+------+------------+------------+
11 rows in set (0.00 sec)
```
21. 删除数据
```
mysql> delete from pet where name='Puffball';
Query OK, 2 rows affected (0.00 sec)

mysql> select * from pet;
+----------+-----------+---------+------+------------+------------+
| name     | owner     | species | sex  | birth      | death      |
+----------+-----------+---------+------+------------+------------+
| 旺财     | 周星驰    | 狗      | 公   | 1990-01-01 | NULL       |
| Fluffy   | Harold    | cat     | f    | 1993-02-04 | NULL       |
| Claws    | Gwen      | cat     | m    | 1994-03-17 | NULL       |
| Buffy    | Harold    | dog     | f    | 1989-05-13 | NULL       |
| Fang     | Benny     | dog     | m    | 1990-08-27 | NULL       |
| Bowser   | Diane     | dog     | m    | 1979-08-31 | 1995-07-29 |
| Chirpy   | Gwen      | bird    | f    | 1998-09-11 | NULL       |
| Whistler | Gwen      | bird    | NULL | 1997-12-09 | NULL       |
| Slim     | Benny     | snake   | m    | 1996-04-29 | NULL       |
+----------+-----------+---------+------+------------+------------+
9 rows in set (0.00 sec)
```
22. 再去插入一条
```
mysql> insert into pet values('Puffball', 'Diane', 'hamster', 'f', '1999-03-30', NULL);
Query OK, 1 row affected (0.00 sec)

mysql> select * from pet;
+----------+-----------+---------+------+------------+------------+
| name     | owner     | species | sex  | birth      | death      |
+----------+-----------+---------+------+------------+------------+
| 旺财     | 周星驰    | 狗      | 公   | 1990-01-01 | NULL       |
| Fluffy   | Harold    | cat     | f    | 1993-02-04 | NULL       |
| Claws    | Gwen      | cat     | m    | 1994-03-17 | NULL       |
| Buffy    | Harold    | dog     | f    | 1989-05-13 | NULL       |
| Fang     | Benny     | dog     | m    | 1990-08-27 | NULL       |
| Bowser   | Diane     | dog     | m    | 1979-08-31 | 1995-07-29 |
| Chirpy   | Gwen      | bird    | f    | 1998-09-11 | NULL       |
| Whistler | Gwen      | bird    | NULL | 1997-12-09 | NULL       |
| Slim     | Benny     | snake   | m    | 1996-04-29 | NULL       |
| Puffball | Diane     | hamster | f    | 1999-03-30 | NULL       |
+----------+-----------+---------+------+------------+------------+
10 rows in set (0.00 sec)
```
23. 修改数据
```
update pet set name='旺旺财' where owner='周星驰';

mysql> update pet set name='旺旺财' where owner='周星驰';
Query OK, 1 row affected (0.00 sec)
Rows matched: 1  Changed: 1  Warnings: 0

mysql> select * from pet;
+-----------+-----------+---------+------+------------+------------+
| name      | owner     | species | sex  | birth      | death      |
+-----------+-----------+---------+------+------------+------------+
| 旺旺财    | 周星驰    | 狗      | 公   | 1990-01-01 | NULL       |
| Fluffy    | Harold    | cat     | f    | 1993-02-04 | NULL       |
| Claws     | Gwen      | cat     | m    | 1994-03-17 | NULL       |
| Buffy     | Harold    | dog     | f    | 1989-05-13 | NULL       |
| Fang      | Benny     | dog     | m    | 1990-08-27 | NULL       |
| Bowser    | Diane     | dog     | m    | 1979-08-31 | 1995-07-29 |
| Chirpy    | Gwen      | bird    | f    | 1998-09-11 | NULL       |
| Whistler  | Gwen      | bird    | NULL | 1997-12-09 | NULL       |
| Slim      | Benny     | snake   | m    | 1996-04-29 | NULL       |
| Puffball  | Diane     | hamster | f    | 1999-03-30 | NULL       |
+-----------+-----------+---------+------+------------+------------+
10 rows in set (0.00 sec)
```

## MySQL 常用数据类型
### 数值类型
1. 数值
```
create table testType(
	number tinyint
);

mysql> create table testType(
    -> number tinyint
    -> );
Query OK, 0 rows affected (0.01 sec)

mysql> show tables;
+----------------+
| Tables_in_test |
+----------------+
| pet            |
| testType       |
+----------------+
2 rows in set (0.00 sec)


mysql> describe testType;
+--------+------------+------+-----+---------+-------+
| Field  | Type       | Null | Key | Default | Extra |
+--------+------------+------+-----+---------+-------+
| number | tinyint(4) | YES  |     | NULL    |       |
+--------+------------+------+-----+---------+-------+
1 row in set (0.00 sec)
```
2. 插入数据检测范围
```
mysql> insert into testType values(127);
Query OK, 1 row affected (0.00 sec)

mysql> select * from testType;
+--------+
| number |
+--------+
|    127 |
+--------+
1 row in set (0.00 sec)

mysql> insert into testType values(128);
ERROR 1264 (22003): Out of range value for column 'number' at row 1
```
3. 数值类型

|类型|大小|范围（有符号）|范围（无符号）|用途|
|:--:|:--:|:--:|:--:|:--:|
|TINYINT|1 字节|(-128，127)|(0，255)|小整数值|
|SMALLINT|2 字节|(-32768，32767)|(0，65535)|大整数值|
|MEDIUMINT|3 字节|(-8388608，8388607)|(0，16777215)|大整数值|
|INT或INTEGER|4 字节|(-2147483648，2147483647)|(0，4294967295)|大整数值|
|BIGINT|8 字节|(-9223372036854775808，9223372036854775807)|(0，18446744073709551615)|极大整数值|
|FLOAT|4 字节|(-3.402823466E+38，-1.175494351E-38)，0，(1.175494351E-38，3.402823466351E+38)|0，(1.175494351E-38，3.402823466E+38)|单精度|
|DOUBLE|8 字节|(-1.7976931348623157E+308，-2.2250738585072014E-308)，0，(2.2250738585072014E-308，1.7976931348623157E+308)|0，(2.2250738585072014E-308，1.7976931348623157E+308)|双精度|
|DECIMAL|对 DECIMAL(M,D)，如果 M>D，为 M+2 否则为 D+2 |依赖于 M 和 D 的值|依赖于 M 和 D 的值|小数值|

#### 日期/时间类型

|类型|大小（字节）|范围|格式|用途|
|:--:|:--:|:--:|:--:|:--:|
|DATE|3|1000-01-01/9999-12-31|YYYY-MM-DD|日期值|
|TIME|3|'-838:59:59'/'838:59:59'|HH:MM:SS|时间值或持续时间|
|YEAR|1|1901/2155|YYYY|年份值|
|DATETIME|8|1000-01-01 00:00:00/9999-12-31 23:59:59|YYYY-MM-DD HH:MM:SS|混合日期和时间值|
|TIMESTAMP|4|1970-01-01 00:00:00/2038|YYYYMMDD HHMMSS	|混合日期和时间值，时间戳|

#### 字符串类型

|类型|大小|用途|
|:--:|:--:|:--:|
|CHAR|0-255 字节|定长字符串|
|VARCHAR|0-65535 字节|变长字符串|
|TINYBLOB|0-255 字节|不超过 255 个字符的二进制字符串|
|TINYTEXT|0-255 字节|短文本字符串|
|BLOB|0-65535 字节|二进制形式的长文本数据|
|TEXT|0-65535 字节|长文本数据|
|MEDIUMBLOB|0-16777215 字节|二进制形式的中等长度文本数据|
|MEDIUMTEXT|0-16777215 字节|中等长度文本数据|
|LONGBLOB|0-4294967295 字节|二进制形式的极大文本数据|
|LONGTEXT|0-4294967295 字节|极大文本数据|

#### 数据类型如何选择
1. 日期选择按照格式  
2. 数值和字符串按照大小


