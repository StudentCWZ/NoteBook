# Linux Shell 编程
## 为什么要学习 Shell 编程
1. 概述
    - Linux 运维工程师在进行服务器集群管理时，需要编写 Shell 程序来进行服务器管理。
    - 对于 JavaEE 和 Python 程序员来说，工作的需要，你的老大会要求你编写一些 Shell 脚本进行程序或者是服务器的维护，比如编写一个定时备份数据库的脚本。
    - 对于大数据程序员来说，需要编写 Shell 程序来管理集群。

## Shell 是什么
1. 概述
```
(1) Shell 是一个命令行解释器，它为用户提供了一个向 Linux 内核发送请求以便运行程序的界面系统级程序，用户可以用 Shell 来启动、挂起、停止甚至是编写一些程序。
```

### Shell 快速入门
1. Shell 脚本的格式要求
```
(1) 脚本以 #!/bin/bash 开头
(2) 脚本需要有可执行权限
```
2. 编写第一个 Shell 脚本
```
#!/bin/bash
echo "Hello,world!"
```

3. 脚本的常用执行方式
```
(1) 方式一：输入脚本的绝对路径或相对路径
首先要赋予脚本的 +x 权限
执行脚本
(2) 方式二: sh + 脚本
不用赋予脚本 +x 权限，直接执行即可
```

## Shell 的变量
### Shell 变量的介绍
1. 概述
```
(1) Linux Shell 中的变量分为：系统变量和用户自定义变量。
(2) 系统变量：$HOME、$PWD、$USER 等等
(3) 显示当前 shell 中所有变量：set
```
2. 举例
```
#!/bin/bash
echo "PATH=$PATH"
echo "user=$USER"
```

### Shell 变量的定义
1. Shell 基本语法
```
(1) 定义变量：变量=值
(2) 撤销变量：unset 变量
(3) 声明静态变量：readonly 变量，注意：不能 unset
```

### 快速入门
1. 案例一
```
定义变量 A
A=100
echo "A=$A"
撤销变量 A
unset
echo "A=$A"
```
2. 案例二
```
# 声明静态的变量 B=2 ，不能 unset
readonly B=2
echo "B=$B"
unset B
echo "B=$B"
```

### 定义变量的规则
1. 概述
```
(1) 变量名称可以由字母、数字和下划线组成，但是不能以数字开头。
(2) 等号两侧不能有空格
(3) 变量名称一般习惯为大写
```

### 将命令的返回值赋给变量
1. 概述
```
(1) A=`ls -al`反引号，运行里面的命令，并把结果返回给变量A
(2) A=$(ls -al)等价于反引号。
```

2. 举例
```
RESULT=`ls -al`
echo $RESULT
echo ""
MY_DATE=$(date)
echo "date=$MY_DATE"
```

## 设置环境变量
### 基本语法
1. Shell 指令
```
(1) export 变量名=变量名 # 功能描述：将 Shell 变量输出为环境变量
(2) source 配置文件 # 功能描述：让修改后的配置信息立即生效
(3) echo $变量名 # 功能描述：查询环境变量的值
```

### 快速入门
1. 在 /etc/profile 文件中定义 TOMCAT_HOME 环境变量
```
(1) vim /etc/profile
(2) export TOMCAT_HOME=/opt/tomcat
(3) source /etc/profile
```
2. 查找环境变量 TOMCAT_HOME 的值
```
(1) echo $TOMCAT_HOME
```
3. 在另外一个 Shell 程序中使用 TOMCAT_HOME
```
(1) echo "tomcat_home=$TOMCAT_HOME"
```
4. 多行注释
```
:<<!
...
...
!
```

## 位置参数变量
### 基本介绍
1. 概述
```
(1) 当我们执行一个 Shell 脚本时候，如果希望获取到命令行的参数信息，就可以使用到位置参数。
```

### 基本语法
2. Shell 指令
```
(1) $n # 功能描述：n 位数字，$0 代表命令本身，$1-$9 代表第一到第九各参数，十以上的参数，需要用大括号包含，如 ${10}
(2) $* # 功能描述：这个变量代表命令中所有的参数，$* 把所有的参数看成一个整体
(3) $@ # 功能描述：这个变量也代表命令行中所有的参数，不过 $@ 把每个参数区分对待
(4) $# # 功能描述：这个变量代表命令行中所有参数的个数
```

### 应用案例
1. 举例
```
#!/bin/bash
# 获取到各个参数
echo "$0 $1 $2"
echo "$*"
echo "$@"
echo "参数个数=$#"
```

## 预定义变量
### 基本介绍
1. 概述
```
(1) 就是 Shell 设计者事先已经定义好的变量，可以直接在 Shell 基本中使用。
```

### 基本语法
2. 概述
```
(1) $$ # 功能描述：当前进程的进程号(PID)
(2) $! # 功能描述：后台运行的最后一个进程的进程号(PID)
(3) $? # 功能描述：最后一次执行的命令的返回状态。如果这个变量值为 0 ，证明上一个正确执行，如果这个变量的值为非 0，则证明上一个命令执行不正确了。
```

### 应用实例
1. 在一个 shell 脚本中使用预定义变量
```
#!/bin/bash
echo "当前的进程号=$$"
# 后台的方式运行 myShell.sh
./myShell.sh &
echo "最后的进程号=$!"
echo "执行的值=$?"
```


## 运算符
### 基本语法
1. 概述
```
(1) "$((运算式))" 或 "$[运算式]"
(2) expr m + n
(3) expr m - n
(4) expr \*,/,%  # 乘，除，取余
```

### 应用案例
1. 计算 (2 + 3) × 4 的值
```
#!/bin/bash
# 第一种方式
RESULT1=$(((2+3)*4))
echo "result1=RESULT1"
# 第二种方式
RESULT2=$[(2+3)*4]
echo "result2=RESULT2"
# 第三种方式
TEMP=`expr 2 + 3`
RESULT3=`expr $TEMP \* 4`
echo "result3=RESULT3"
```
2. 请求出命令行的两个参数和
```
#!/bin/bash 
# 求出两个参数的和
(1) SUM=$($1+$2)
```

## 流程控制
### 条件判断
#### 基本语法
1. 概述
```
(1) [ condition ] # 注意 condition 前后要有空格
```

#### 应用实例
1. 举例
```
 [ atguigu ] # 返回 true
 []       # 返回 false
 [ condition ]&&echo OK || echo notok # 条件满足，执行的后面语句
```

#### 判断语句
##### 常用判断条件
1. 两个整数的比较
```
(1) =  # 字符串比较
(2) -lt # 小于
(3) -le # 小于等于
(4) -eq # 等于
(5) -gt # 大于
(6) -ge # 大于等于
(7) -ne # 不等于
```
2. 按照文件权限进行判断
```
(1) -r # 有读的权限
(2) -w # 有写的权限
(3) -x # 有执行的权限
```
3. 按照文件类型进行判断
```
-f # 文件存在并且是一个常规的文件
-e # 文件存在
-d # 文件存在并是一个目录
```
##### 应用实例
1. "ok" 是否等于 "ok"
```
if [ "ok" = "ok" ]
then 
    echo "equal"
fi
```
2. 23 是否大于等于 22
```
if [ 23 -ge 22 ]
then
    echo "大于等于"
fi
```
3. /root/install.log 目录中的文件是否存在
```
if [ -e /root/install.log ]
then
    echo "存在"
fi
```

### if 判断
#### 基本语法
1. if 指令
```
if [ 条件判断式 ];then
    程序
fi
或者
if [ 条件判断式 ]
then
    程序
elif [ 条件判断式 ]
then
    程序
fi
```
2. 注意事项
```
(1) 中括号和条件判断式之间必须有空格
(2) 推荐使用第二种方式
```

#### 应用实例
1. 举例
```
#!/bin/bash
# 案例：请编写一个 Shell 程序，如果输入的参数，大于等于 60 ，则输出“及格了”，如果小于 60 ，则输出“不及格”。
if [ $1 -ge 60 ]
then
    echo "及格了"
elif [ $1 -lt 60 ]
then
    echo "不及格"
fi
```

### case 语句
#### 基本语法
1. case 语句
```
case $变量名 in 
"值1")
如果变量的值等于值1，则执行程序1
;;
"值2")
如果变量的值等于值2，则执行程序2
;;
...省略其他分支...
*)
如果变量的值都不是以上的值，则执行此程序
;;
esac
```

#### 应用实例
1. 举例
```
#!/bin/bash
# 案例：当命令行参数是 1 时，输出 “周一”，是 2 时，就输出“周二”，其他情况输出 “other”
case $1 in
1)
echo "周一"
;;
2)
echo "周二"
;;
*)
echo "other"
;;
esac
```

### for 循环
#### 基本语法一
1. for 指令
```
for 变量 in 值1 值2 值3...
do
    程序
done
```
2. 应用实例
```
#!/bin/bash
# 案例：打印命令行输入的参数
(1) 方式一
for i in "$*"
do
    echo "the num is $i"
done
(2) 方式二
for i in "$@"
do
    echo "the num is $i"
done
```

#### 基本语法二
1. for 指令
```
for ((初始值;循环控制;变量变化))
do
    程序
done
```
2. 应用实例
```
#!/bin/bash
# 案例：从 1 加到 100 的值输出显示
SUM=0
for ((i=1;i<=100;i++))
do
    SUM=$[$SUM+$i]
done
echo "sum=$SUM"
```

### while 循环
#### 基本语法
```
while [ 条件判断式 ]
do
    程序
done
```

### 应用实例
1. 举例
```
#!/bin/bash
# 案例：从命令行输入一个数 n，统计从 1 + ... + n 的值

SUM=0
i=0
while [ $i -le $1 ]
do
    SUM=$[$SUM+$1]
    i=$[$i+1]
done
echo "sum=$SUM"
```

## read 读取控制台输入
### 基本语法
1. read 指令
```
(1) read(选项)(参数)
(2) 选项
-p: 指定读取值时的提示符
-t: 指定读取值时等待时间(秒)，如果没有在指定的时间内输入，就不在等待了。。
(3) 参数：指定读取值的变量名
```

### 应用实例
1. 案例一
```
#!/bin/bash
# 案例：读取控制台输入一个 num 值
read -p "请输入一个数num1=" NUM1
echo "你输入的值是num1=$NUM1"
```
2. 案例二
```
#!/bin/bash
# 案例：读取控制台输入一个 num 值，在 10 秒内输入
read -t 10 -p "请输入一个数num2=" NUM2 
echo "你输入的值是num2=$NUM2"
```

## 函数
### 函数介绍
```
(1) Shell 编程和其他编程语言一样，有系统函数，也有自定义函数。
(2) 系统函数中，我们这里就介绍两个。
```

### 系统函数
#### basename 基本语法
1. basename 指令
```
(1) basename 功能：返回完整路径最后 / 的部分，常用于获取文件名
(2) basename [pathname] [suffix]
(3) basename [string] [suffix] # 功能描述：basename 命令会删掉所有的前缀包括最后一个('/')字符，然后将字符串显示出来
(4) 选项：
suffix 为后缀，如果 suffix 被指定了，basename 会将 pathname 或 string 中的 suffix 去掉
```

#### 应用实例
1. 举例
```
#!/bin/bash
# 案例：请返回 /home/aaa/test.txt 的 test.txt 部分
basename /home/aaa/test.txt
```

#### dirname 基本语法
1. dirname 指令
```
(1) 功能：返回完整路径最后 / 的前面部分，常用语返回路径部分
(2) dirname 文件绝对路径 # 功能描述：从给定的包含绝对路径的文件名中去除文件名(非目录部分)，然后返回剩下的路径(目录部分)。
```

#### 应用实例
1. 举例
```
#!/bin/bash
# 案例：请返回 /home/aaa/test.txt 的 /home/aaa
dirname /home/aaa/test.txt
```

### 自定义函数
#### 基本语法
1. 相关指令
```
function funname(){
    Action;
    [return int;]
}
```

#### 应用实例
1. 举例
```
#!/bin/bash
# 案例：计算输入两个参数的和(read)
function getSum(){
    SUM=$[$n1+$n2]
    echo "和是=$SUM"
}

read -p "请输入第一个数n1=" n1
read -p "请输入第一个数n2=" n2

# 调用 getSum
getSum $n1 $n2
```

## Shell 编程综合案例
### 需求分析
1. 概述
```
(1) 每天凌晨 2:10 备份数据库 atguiguDB 到 /data/backup/db
(2) 备份开始和备份结束能够给出相应的提示的信息
(3) 备份后的文件要求以备份时间为文件名，并打包成 .tar.gz 的形式，比如：2018-03-12_230201.tar.gz
(4) 在备份的同时，检查是否有 10 天前备份的数据库文件，如果有就将其删除。
```
2. `mysql_db_backup.sh` 代码
```
#!/bin/bash

# 完成数据库的定时备份
# 备份路径
BACKUP=/data/backup/db
# 当前的时间做为文件名
DATETIME=$(date +%Y-%m-%d_%H%M%S)
# 可以输出变量调试
echo ${DATETIME}

echo "========开始备份========"
echo "========备份的路径是 $BACKUP/$DATETIME.tar.gz========"

# 主机
HOST=localhost
# 用户名
DB_USER=root
# 密码
DB_PWD=root
# 备份数据库名
DATABASE=atguiguDB
# 创建备份的路径
# 如果备份的路径文件夹存在，就使用，否则就创建
[ ! -d "$BACKUP/$DATETIME"] && mkdir -p $BACKUP/$DATETIME
# 执行 mysql 的备份数据库的指令
mysqldump -u${DB_USER} -p{DB_PWD} --host=$HOST $DATABASE ｜ gzip > $BACKUP/$DATETIME/$DATETIME.sql.gz
# 打包备份文件
cd $BACKUP
tar -zcvf $DATETIME.tar.gz $DATETIME
## 删除临时目录
rm -rf $BACKUP/$DATETIME
## 删除 10 天前的备份文件
find $BACKUP -mtime +10 -name ".tar.gz" -exec rm -rf {} \;

echo "========备份文件成功========"
```
3. crond 任务调度
```
10 2 * * * /usr/sbin/mysql_db_backup.sh
```
