# Golang 程序流程控制
## 程序流程控制介绍
1. 在程序中，程序运行的流程控制决定程序是如何执行，是我们必须掌握的，主要有三大流程控制语句。
```
(1) 顺序控制
(2) 分支控制
(3) 循环控制
```

## 顺序控制
### 基本介绍
1. 程序从上到下逐行地运行，中间没有任何判断和跳转。

### 顺序控制的注意事项
1. Golang 中定义变量时采用合法的前向引用。
```
// 正确案例
func main() {
  var num1 int = 10
  var num int = num1 + 20
  fmt.Println(num2)
}

// 错误案例
func main() {
  var num2 int = num1 +20
  var num1 int = 10
  fmt.Println(num2)
}
```

## 分支控制
### 分支控制 if-else 介绍
1. 让程序有选择地执行，分支控制有三种：
```
(1) 单分支
(2) 双分支
(3) 多分支
```

### 单分支
1. 基本语法
```
if 条件表达式{
  执行代码块
}
```
2. 说明：当条件表达式为 true 时，就会执行 {} 的代码。
3. 注意： {} 是必须有的，就算你只写一行代码。
4. 案例演示
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main()  {

	//编写一个程序，可以输入人的年龄，如果该同志的年龄大于 18 岁，则输出 "你年龄大于18，要对自己的行为负责！"
	//分析
	//1. 年龄 ==> var age int
	//2. 从控制台接收一个输入 fmt.Scanln(&age)
	//3. if 判断
	var age int
	fmt.Println("请输入年龄：")
	fmt.Scanln(&age)
	if age > 18 {
		fmt.Println("你年龄大于18，要对自己的行为负责！")
	}
}
```
5. 细节说明：Go 的 if 还用一个强大的地方就是条件判断语句里面允许声明一个变量，这个变量的作用域只能在该条件逻辑块内，其他地方就不起作用了。
6. 相关案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main()  {
	// Golang 支持在if中，直接定义一个变量，比如下面
	if age := 20; age > 18 {
		fmt.Println("你年龄大于18，要对自己的行为负责！")
	}
}
```

### 双分支
1. 基本语法
```
if 条件表达式 {
  执行代码块1
} else {
  执行代码块2
}
```
2. 说明：当条件表达式成立，即执行代码块 1 ，否则执行代码块 2 。 {} 也是必须有的。
3. 相关案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//编写一个程序，可以输入人的年龄，如果该同志的年龄大于 18 岁，则输出"你的年龄大于 18 ，要对自己的行为负责！"。
	//否则，输出"你的年龄不大这次放过你了。"

	//思路分析
	//1. 年龄 ===> var age int
	//2. fmt.Scanln 接收
	//3. if --- else
	var age int
	fmt.Println("请输入年龄：")
	fmt.Scanln(&age)

	if age > 18 {
		fmt.Println("你年龄大于18，要对自己的行为负责！")
	} else {
		fmt.Println("你的年龄不大这次放过你了。")
	}
}
```
4. 单分支和双分支的相关案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	var x int  = 4
	var y int = 1
	if x > 2 {
		if y > 2 {
			fmt.Println(x + y)
		} else {
			fmt.Println("atguigu")
		}
	} else{
		fmt.Println("x is =", x)
	}
}
```
### 单分支和双分支的练习题
1. 编写程序，声明 2 个 int32 型变量并赋值。判断两数之和，如果大于等于 50 ，打印"hello world!"。
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//编写程序，声明 2 个 int32 型变量并赋值。判断两数之和，如果大于等于 50 ，打印"hello world!"。

	//分析
	//1. 变量
	//2. 单分支
	var x int32  = 40
	var y int32 = 10
	if x + y >= 50 {
		fmt.Println("hello world!")
	}
}
```
2. 编写程序，声明 2 个 float64 型变量并赋值。判断第一个数大于 10.0，且第 2 个数小于 20.0，打印两数之和。
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//编写程序，声明 2 个 float64 型变量并赋值。判断第一个数大于 10.0，且第 2 个数小于 20.0，打印两数之和。

	//分析
	//1. 变量
	//2. 单分支
	var x float64  = 10.1
	var y float64 = 19.1
	if x > 10.0 && y < 20.0 {
		fmt.Println(x + y)
	}
}
```
3. 定义两个变量 int32 ，判断两者的和，是否能被 3 又能被 5 整除，打印提示信息。
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//定义两个变量 int32 ，判断两者的和，是否能被 3 又能被 5 整除，打印提示信息。

	//分析
	//1. 变量
	//2. 单分支
	var x int32  = 10
	var y int32 = 20
	if (x + y) % 3 == 0 && (x + y) % 5 == 0 {
		fmt.println("x + y 能被 3 整除且能被 5 整除。")
	}
}
```
4. 判断一个年份是否是闰年，闰年的条件是符合下面两者之一：(1) 年份能被 4 整除，但不能被 100 整除；(2) 能被 400 整除。
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//判断一个年份是否是闰年，闰年的条件是符合下面两者之一：
	//(1) 年份能被 4 整除，但不能被 100 整除；(2)能被 400 整除

	var year int = 2020
	if (year % 4 == 0 && year % 100 != 0) || year % 400 == 0 {
		fmt.Printf("%v是闰年。", year)
	}
}
```

## 多分支
1. 基本语法
```
if 条件表达式 {
  执行代码块 1
} else if 条件表达式2 {
  执行代码块 2
}
...
else {
  执行代码块 n
}
```
2. 多分支的判断流程：
```
(1) 先判断条件表达式 1 是否成立。如果为真，就执行代码块 1 。
(2) 如果条件表达式 1 如果为假，就去判断条件表达式 2 是否成立，如果表达式 2 为真，就执行代码块 2 。
(3) 依次类推
(4) 如果所有的条件表达式不成立，则执行 else 的语句块。
```
3. 多分支基本语法的说明：
```
(1) 最后的 else 不是必须的。
(2) 多分支只能有一个执行入口。
```
4. 多分支的快速入门案例
```
// 案例一

package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	//岳小鹏参加 Golang 考试，他和他的父亲岳不群达成承诺：
	//如果：成绩为 100 分时，奖励一辆 BMW ；
	//成绩为 (80, 99] 时，奖励一台 iphone7Plus ；
	//成绩为 [60, 80] 时，奖励一台 ipad ；
	//其他时，什么奖励也没有。
	//请从键盘输入岳小鹏的期末成绩，并加以判断。

	//分析思路
	//1. score 分数变量 int
	//2. 多分支流程控制
	//3. 成绩从键盘输入 fmt.Scanln()

	var score int
	fmt.Println("请输入成绩：")
	fmt.Scanln(&score)


	if score == 100 {
		fmt.Println("奖励一辆 BMW")
	} else if score > 80 && score <= 99 {
		fmt.Println("奖励一台 iphone7Plus")
	} else if score >= 80 && score <= 80 {
		fmt.Println("奖励一台 ipad")
	} else {
		fmt.Println("什么奖励也没有")
	}
}
```
```
// 案例二
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	var b bool = true
	if b == false {
		fmt.Println("a")
	} else if b {
		fmt.Println("b") // b
	} else if !b {
		fmt.Println("c") // c
	} else {
		fmt.Println("d")
	}
}
```
```
// 案例三
package main

import (
	"fmt"  // fmt 包中提供格式化、输出、输入的函数
	"math"
)



func main() {
	//求 ax^2 + bx + c = 0 方程的根。a, b, c 分别为函数的参数，如果：b^2 - 4ac > 0，则有两个解；
	// b^2 - 4ac = 0， 则有一个解；b^2 - 4ac < 0，则无解；
	//提示1：x1 = (-b + sqrt(b^2 - 4ac < 0)) / 2a ; x2 = (-b - sqrt(b^2 - 4ac < 0)) / 2a
	//提示2：math.Sqrt(num); 可以求平方根，需要引入 math 包

	//分析思路
	//1. a, b, c 是三个 float64
	//2. 使用到给出的数学公式
	//3. 使用到多分支
	//4. 使用到 math.Sqrt 方法 => 手册

	var a float64 = 3.0
	var b  float64 = 100.0
	var c float64 = 6.0


	m := b * b - 4 * a * c
	//多分支判断
	if m > 0 {
		x1 := (-b + math.Sqrt(m)) / 2 * a
		x2 := (-b - math.Sqrt(m)) / 2 * a
		fmt.Printf("x1 = %v x2 = %v", x1, x2)
	} else if m == 0 {
		x := (-b + math.Sqrt(m)) / 2 * a
		fmt.Printf("x = %v", x)
	} else {
		fmt.Println("方程无解...")
	}
}
```
```
//案例四
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	// 大家都知道，男大当婚，女大当嫁。那么女方家长要嫁女儿，当然要提出一定条件：高 180 cm 以上；富：财富 1 千万以上；帅：是。 条件从控制台输入。
	// 1. 如果这三个条件同时满足，则："我一定要嫁给他！！！"。
	// 2. 如果三个条件有为真的情况，则："嫁吧，比上不足，比下有余。"
	// 3. 如果三个条件都不满足，则："不嫁！"

	//分析思路
	//1. 应该设计三个变量 var height int32 | var money float32 | var handsome bool
	//2. 而且需要从终端输入 fmt.Scanln
	//3. 使用多分支
	var height int32
	var money float32
	var handsome bool

	fmt.Println("请输入身高(厘米)")
	fmt.Scanln(&height)
	fmt.Println("请输入财富(千万)")
	fmt.Scanln(&money)
	fmt.Println("请输入是否帅(true/false)")
	fmt.Scanln(&handsome)

	if height > 180 && money > 1.0 && handsome == true {
		fmt.Println("我一定要嫁给他！！！")
	} else if height > 180 || money > 1.0 || handsome == true {
		fmt.Println("嫁吧，比上不足，比下有余。")
	} else {
		fmt.Println("不嫁！")
	}
}
```

## 嵌套分支
### 基本介绍
1. 在一个分支结构中又完整的嵌套了另一个完整的分支结构，里面的分支结构称为内层分支外面的分支称为外层分支。
2. 基本语法
```
if 条件表达式 {
  if 条件表达式 {
  } else {
  }
}
```
3. 说明：嵌套分支不宜过多，建议控制在 3 层内。
4. 相关案例
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	//参加百米运动会，如果勇士 8 秒内进入决赛，否则提示淘汰。并根据性别提示进入男子组或女子组。
	//输入成绩和性别。

	//分析思路
	//1. 定义一个变量，来接收跑步的秒数，flaot64 。
	//2. 定义一个变量，来接收性别 string
	//3. 因为判断是嵌套的判断，因此我们会使用嵌套分支。

	var second float64
	fmt.Println("请输入秒数：")
	fmt.Scanln(&second)


	if second <= 8 {
		//进入决赛
		var gender string
		fmt.Println("请输入性别：")
		fmt.Scanln(&gender)
		if gender == "男" {
			fmt.Println("进入决赛的男子组")
		} else {
			fmt.Println("进入决赛的女子组")
		}
	} else {
		fmt.Println("out...")
	}
}
```
```
//案例二
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	/*
	出票系统：根据淡旺季的月份和年龄，打印票价

	旺季(4-10)：
		成人(18-60)：60
		儿童(<18)：半价
		老人(>60)：1/3

	淡季：
		成人：40
		其他：20
	*/

	//分析思路
	//1. month age 的两个变量 byte
	//2. 使用嵌套分支。

	var month byte
	var age byte
	var price float64 = 60.0
	fmt.Println("请输入旅游的月份：")
	fmt.Scanln(&month)
	fmt.Println("请输入游客的年龄：")
	fmt.Scanln(&age)

	if month >= 4 && month <= 10 {
		if age > 60 {
			fmt.Printf("票价：%v", price / 3)
		} else if age >= 18 {
			fmt.Printf("票价：%v", price)
		} else {
			fmt.Printf("票价：%v", price / 2)
		}
	} else {
		//淡季
		if age >= 18 && age <= 60 {
			fmt.Printf("票价：%v", 40)
		} else {
			fmt.Printf("票价：%v", 20)
		}
	}
}
```

## switch 分支结构
### switch 分支的基本介绍
1. switch 语句用于基于不同条件执行不同动作，每个 case 分支都是唯一的，从上到下逐一测试，直到匹配为止。
2. 匹配项后面也不需要再加 break 。

### switch 分支的基本语法
```
switch 表达式 {
case 表达式1,表达式2,...:
  语句块1
case 表达式3,表达式4,...:
  语句块2
// 这里可以有多个 case 语句
default:
  语句块
}
```

### switch 的快速入门案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	/*
		案例：
		请编写一个程序，该程序可以接收一个字符，比如：a, b, c, d, e, f, g。
		a 表示星期一，b 表示星期二...。根据用户的输入显示相应的信息。
		要求使用 switch 语句完成


		分析思路：
		1. 定义一个变量接受字符。
		2. 使用 switch 完成。

	*/

	var key byte
	fmt.Println("请输入一个字符 a, b, c, d, e, f, g:")
	fmt.Scanf("%c", &key)

	switch key {
		case 'a':
			fmt.Println("周一，猴子穿新衣")
		case 'b':
			fmt.Println("周二，猴子当小二")
		case 'c':
			fmt.Println("周三，猴子爬雪山")
		//...
		default:
			fmt.Println("输入有误...")
	}
}
```
### switch 细节讨论
1. case/switch 后是一个表达式(即：常量值、变量、一个有返回值的函数等都可以)。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	/*
		案例：
		请编写一个程序，该程序可以接收一个字符，比如：a, b, c, d, e, f, g。
		a 表示星期一，b 表示星期二...。根据用户的输入显示相应的信息。
		要求使用 switch 语句完成


		分析思路：
		1. 定义一个变量接受字符。
		2. 使用 switch 完成。

	*/

	var key byte
	fmt.Println("请输入一个字符 a, b, c, d, e, f, g: ")
	fmt.Scanf("%c", &key)

	switch 'a' {
	case 'a':
		fmt.Println("周一，猴子穿新衣")
	case 'b':
		fmt.Println("周二，猴子当小二")
	case 'c':
		fmt.Println("周三，猴子爬雪山")
	//...
	default:
		fmt.Println("输入有误...")
	}
}
```
```
//案例二
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


//写一个非常简单的函数
func test(char byte) byte {
	return char + 1
}

func main() {

	/*
		案例：
		请编写一个程序，该程序可以接收一个字符，比如：a, b, c, d, e, f, g。
		a 表示星期一，b 表示星期二...。根据用户的输入显示相应的信息。
		要求使用 switch 语句完成


		分析思路：
		1. 定义一个变量接受字符。
		2. 使用 switch 完成。

	*/

	var key byte
	fmt.Println("请输入一个字符 a, b, c, d, e, f, g:")
	fmt.Scanf("%c", &key)

	switch test(key) + 1 {
	case 'a':
		fmt.Println("周一，猴子穿新衣")
	case 'b':
		fmt.Println("周二，猴子当小二")
	case 'c':
		fmt.Println("周三，猴子爬雪山")
	//...
	default:
		fmt.Println("输入有误...")
	}
}
```
2. case 后的各个表达式的值的数据类型，必须和 switch 的表达式数据类型一致。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {
	var n1 int32 = 20
	var n2 int32 = 20

	switch n1 {
		case n2 :
			fmt.Println("ok1")
		default :
			fmt.Println("没有匹配到")
	}
}
```
3. case 后面可以带多个表达式，使用逗号间隔。比如 case 表达式1，表达式2...。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	var n1 int32 = 20
	var n2 int32 = 20

	switch n1 {
	case n2, 10, 5: // case 后面可以有多个表达式
		fmt.Println("ok1")
	default :
		fmt.Println("没有匹配到")
	}
}
```
4. case 后面的表达式如果是常量值(字面量)，则要求不能重复。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	var n1 int32 = 20
	var n2 int32 = 20
	var n3 int32 = 20


	switch n1 {
	case n2, 10, 5: // case 后面可以有多个表达式
		fmt.Println("ok1")
	//case 5 : //错误，因为前面我们有这个常量 5 ，因此重复，就会报错。
		//fmt.Println("ok2")
	case n3:
		fmt.Println("ok2")
	default :
		fmt.Println("没有匹配到")
	}
}
```
5. case 后面不需要带 break ，程序匹配到一个 case 后就会执行对应的代码块，然后退出 switch ，如果一个匹配不到，则执行 default 。
```
// 案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	var n1 int32 = 51
	var n2 int32 = 20


	switch n1 {
	case n2, 10, 5: // case 后面可以有多个表达式
		fmt.Println("ok1")
	case 90:
		fmt.Println("ok2")

	}
}
```
6. default 语句不是必须的。
7. switch 后也可以不带表达式，类似于 if-else 分支来使用。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	//switch 后也可以不带表达式，类似if--else分支来使用。
	var age int = 10

	switch {
		case age == 10 :
			fmt.Println("age == 10")
		case age == 20 :
			fmt.Println("age == 20")
		default :
			fmt.Println("没有匹配到")
	}
}
```
```
//案例二
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	// switch 后也可以不带表达式，类似 if--else 分支来使用。
	// case 中也可以对范围进行判断
	var score int = 90

	switch {
		case score > 90 :
			fmt.Println("成绩优秀..")
		case score >= 70 :
			fmt.Println("成绩优良..")
		case score >= 60 :
			fmt.Println("成绩及格..")
		default :
			fmt.Println("不及格")
	}
}
```
8. switch 后也可以直接声明/定义一个变量，分号结束，不推荐。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	//switch 后也可以直接声明/定义一个变量，分号结束，不推荐

	switch grade := 90; {
		case grade > 90 :
			fmt.Println("成绩优秀..")
		case grade >= 70 :
			fmt.Println("成绩优良..")
		case grade >= 60 :
			fmt.Println("成绩及格..")
		default :
			fmt.Println("不及格")
	}
}
```
9. switch 穿透- fallthrough ，如果在 case 语句块后增加 fallthrough ，则会继续执行下一个 case ，也叫 switch 穿透。
```
//案例一
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


func main() {

	//switch 的穿透 fallthrough
	var num int = 10

	switch num {
		case 10 :
			fmt.Println("ok1")
			fallthrough //默认只能穿透一层
		case 20 :
			fmt.Println("ok2")
		case 30 :
			fmt.Println("ok3")
		default :
			fmt.Println("没有匹配到...")
	}
}
```
10. Type Switch：switch 语句还可以被用于 type-switch 来判断某个 interface 变量中实际指向的变量类型。
```
// 案例一
package main

import "fmt"


func main() {

	var x interface{}
	var y = 10.0
	x = y

	switch i := x.(type){
	case nil :
		fmt.Printf("x 的类型~ :%T", i)
	case int :
		fmt.Printf("x 是 int 型")
	case float64 :
		fmt.Printf("x 是 float64 型")
	case bool, string :
		fmt.Printf("x 是 bool 或是 string 型")
	default :
		fmt.Printf("未知型")
	}
}
```
### switch 练习
1. 练习一
```
package main

import "fmt"


func main() {
	/*
	1. 使用 switch 把小写类型的 char 型转为大写(键盘输入)。
	2. 只转换 a, b, c, d, e。其他的输出 "other" 。
	*/
	var char byte
	fmt.Println("请输入一个字符..")
	fmt.Scanf("%c", &char)

	switch char {
		case 'a' :
			fmt.Println("A")
		case 'b' :
			fmt.Println("B")
		case 'c' :
			fmt.Println("C")
		case 'd' :
			fmt.Println("D")
		case 'e' :
			fmt.Println("E")
		default :
			fmt.Println("other")
	}
}
```
2. 练习二
```
package main

import "fmt"

func main() {
	/*
	1. 对学生成绩大于 60 分，输出"合格"。低于 60 分，输出"不合格"。
	2. (注：输入的成绩不能大于 100)
	*/

	var score float64
	fmt.Println("请输入成绩：")
	fmt.Scanln(&score)

	switch int(score / 60) {
		case 1 :
			fmt.Println("及格")
		case 0 :
			fmt.Println("不及格")
		default :
			fmt.Println("输入有误..")
	}
}
```
3. 练习三
```
package main

import "fmt"


func main() {

		/*
		1. 根据用户指定月份，打印该月份所属的季节。
		2. 3，4，5 春季；6，7，8 夏季；9，10，11 秋季；12，1，2 冬季。
		*/

		var month byte
		fmt.Println("请输入月份：")
		fmt.Scanln(&month)

	switch month {
		case 3, 4, 5 :
			fmt.Println("spring")
		case 6, 7, 8 :
			fmt.Println("summer")
		case 9, 10, 11 :
			fmt.Println("autumn")
		case 12, 1, 2:
			fmt.Println("winter")
		default:
			fmt.Println("输入有误..")
	}
}
```
4. 练习四
```
package main

import "fmt"

func main() {

	/*
	1. 根据用户输入显示对应的的星期时间(string)。
	2. 如果是"星期一"，显示"干煸豆角"；如果是"星期二"，显示"酸溜土豆"；
	3. 如果"星期三"，显示"红烧狮子头"；如果是"星期四"，显示"油炸花生米"；
	4. 如果是"星期五"，显示"蒜蓉扇贝"；如果是"星期六"，显示"东北乱炖"；如果是"星期日"，显示"大盘鸡"。
	*/

	var today string
	fmt.Println("请输入今天星期几：")
	fmt.Scanln(&today)

	switch today {
	case "星期一" :
		fmt.Println("干煸豆角")
	case "星期二" :
		fmt.Println("酸溜土豆")
	case "星期三" :
		fmt.Println("红烧狮子头")
	case "星期四" :
		fmt.Println("油炸花生米")
	case "星期五" :
		fmt.Println("蒜蓉扇贝")
	case "星期六" :
		fmt.Println("东北乱炖")
	case "星期日" :
		fmt.Println("大盘鸡")
	default :
		fmt.Println("输入有误..")
	}
}
```

### switch 和 if 的比较
1. 如果判断的具体数值不多，而且符合整数、浮点数、字符、字符串这几种类型。建议使用 switch 语句，简洁高效。
2. 其他情况：结果为 bool 类型的判断，使用 if ，if 的使用范围更广。

## for 循环
### for 循环的基本介绍
1. 听其名而知其意，就是让我们的一段代码循环的执行。

### for 循环的快速入门
```
package main

import "fmt"

func main() {
	//输出十句，"你好，尚硅谷！"
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")
	//fmt.Println("你好，尚硅谷！")

	// Golang 中，有循环控制语句来处理循环的执行某段代码的方法 ==> for 循环
	//for 循环快速入门

	for i := 1; i <= 10; i++ {
		fmt.Println("你好，尚硅谷！", i)
	}
}
```

### for 循环
1. 语法格式
```
for 循环变量初始化；循环条件；循环变量迭代 {
  循环操作(语句)
}
```
2. 对 for 循环来说，有四个要素
```
(1) 循环变量初始化
(2) 循环条件
(3) 循环操作语句
(4) 循环变量迭代
```
3. for 循环执行的顺序说明
```
(1) 执行循环变量初始化，比如 i := 1
(2) 执行循环条件，比如 i <= 10
(3) 如果循环条件为真，就执行循环操作。 比如：fmt.Println("...")
(4) 执行循环变量迭代，比如 i++
(5) 反复执行 2，3，4 步骤，直到循环条件为 False，就退出 for 循环。
```

### for 循环的注意事项和细节说明
1. 循环条件是返回一个布尔值的表达式。
2. for 循环的第二种的使用方式。
```
for 循环条件 {
  //循环执行语句
}
//将变量初始化和变量迭代写到其他位置。
```
3. for 循环的第三种使用方式
```
for {
  //循环执行语句
}
// 上面的写法等价 for;;{} 是一个无限循环，通常需要配合 break 语句使用。
```
4. Golang 提供 for-range 的方式，可以方便遍历字符串和数组。
```
package main

import "fmt"

func main() {

	//字符串遍历方式一(传统方式)
	var str string = "hello,world"
	for i := 0; i < len(str); i++ {
		fmt.Printf("%c \n", str[i]) //使用到下标
	}


	//字符串遍历方式二( for-range 方式)
	str = "abc~ok"
	for index, val := range str {
		fmt.Printf("index = %d, val = %c \n", index, val)
	}
}
```
5. 上面代码细节讨论
```
(1) 如果我们的字符串含有中文，那么传统的遍历字符串方式，就是错误，会出现乱码。原因是：传统的对字符串的遍历是按照字节来遍历，而一个汉字在 utf-8 编码是对应 3 个字节。
(2) 只需要将 str 转成 []rune 切片
(3) 对应 for-range 遍历而言，是按照字符方式遍历。因此如果有字符串有中文，也是 ok 。
```
6. 具体案例
```
package main

import "fmt"

func main() {

	//字符串遍历方式一(传统方式)
	var str string = "hello,world!北京"
	str2 := []rune(str) //就是把 str 转成 []rune
	for i := 0; i < len(str2); i++ {
		fmt.Printf("%c \n", str2[i]) //使用到下标
	}


	//字符串遍历方式二( for-range 方式)
	str = "abc~ok上海"
	for index, val := range str {
		fmt.Printf("index = %d, val = %c \n", index, val)
	}
}
```

### for 循环练习
1. 练习一
```
package main

import "fmt"


func main() {
	//打印 1~100 之间所有是 9 的倍数的整数的个数及总和。

	//分析思路
	//1. 使用 for 循环对 max 进行遍历
	//2. 当一个数 %9==0 就是 9 的倍数
	//3. 我们需要声明两个变量 count 和 sum 来保存个数和总和
	var max uint64 = 100
	var count uint64 = 0
	var sum uint64 = 0
	var i uint64 = 0

	for ; i <= max; i++ {
		if i % 9 == 0 {
			count ++
			//sum = sum + i
			sum += i
		}
	}
	fmt.Printf("count = %v sum = %v \n", count, sum)
}
```
2. 练习二
```
package main

import "fmt"


func main() {
	var n int = 6
	for i := 0; i <= n; i++ {
		fmt.Printf("%v + %v = %v \n", i, n-i, n)
	}
}
```

### while 和 do...while 的实现
#### for 循环实现 while 的效果
1. 基本语法
```
循环变量初始化
for {
  if循环表达式{
    break // 跳出 for 循环..
  }
  循环操作(语句)
  循环变量迭代
}
```
2. 基本语法说明
```
(1) for 循环是一个无限循环
(2) break 语句就是跳出 for 循环
```

#### for 循环实现 do...while 的效果
1. 基本语法
```
循环变量初始化
for {
  循环操作(语句)
  循环变量迭代
  if 循环条件表达式 {
    break // 跳出 for 循环...
  }
}
```
2. 基本语法的说明
```
(1) 上面的循环是先执行，再判断，因此至少执行一次。
(2) 当循环条件成立后，就会执行 break ，break 就是跳出 for 循环，结束循环。
```

### 多重循环控制
1. 将一个循环放在另一个循环体内，就形成了嵌套循环。在外边的 for 称为外层循环在里面的 for 循环称为内层循环。
2. 实质上，嵌套循环就是把内层循环当成外层循环的循环体。当只有内层循环的循环条件为 false 时，才会完全跳出内层循环，才可结束外层的当次循环，开始下一次的循环。
3. 设外层循环次数为 m 次，内层为 n 次，则内层循环体实际上需要执行 m x n 次。

#### 多重循环控制相关案例
1. 案例一
```
package main

import "fmt"

func main() {
	//应用案例
	//1. 统计 3 个班的成绩情况，每个班有 5 名同学。
	//2. 求出各个班的平均分和所有班级的平均分[学生的成绩从键盘输入]

	//编程时两大绝招
	//1. 先易后难，即将一个复杂的问题分解成简单的问题。
	//2. 先死后活，先把程序写死，再用变量灵活替换。

	//分析实现思路1
	//1. 统计 1 个班成绩情况，每个班有5名同学，求出该班的平均分[学生的成绩从键盘输入] ==> [先易后难]
	//2. 学生数就是 5 个 ==> [先死后活]
	//3. 声明一个 sum 统计班级总分

	//分析实现思路1：代码实现
	//class_sum := 0.0
	//for j := 1; j <= 5; j++ {
	//	var score float64
	//	fmt.Printf("请输入第 %d 学生成绩：\n", j)
	//	fmt.Scanln(&score)
		//累计总分
	//	class_sum = class_sum + score
	//}
	//fmt.Printf("班级的平均分为：%v \n", class_sum / 5)

	//分析实现思路2
	//1. 统计三个班成绩情况，每个班有 5 名同学，求出每个班的的平均分[学生的成绩从键盘输入]
	//2. i 表示第几个班级
	//3. 定义一个变量存放总成绩

	//分析实现思路2：代码实现方式
	//var total_sum float64 = 0.0
	//
	//for i := 1; i <= 3; i++ {
	//	class_sum := 0.0
	//	for j := 1; j <= 5; j++ {
	//		var score float64
	//		fmt.Printf("请输入第 %d 班 第 %d 学生成绩：\n", i, j)
	//		fmt.Scanln(&score)
	//		class_sum = class_sum + score
	//	}
	//	fmt.Printf("第 %d 个班级的平均分为：%v \n", i, class_sum / 5)
	//	//将各个班的总成绩累计到 total_sum
	//	total_sum = total_sum + class_sum
	//}
	//fmt.Printf("各个班级的总成绩 %v 所有班级平均分为：%v \n", total_sum, total_sum / (3 * 5))

	//分析实现思路3
	//1. 我们可以把代码做活
	//2. 定义两个变量，表示班级的个数和班级的人数。

	var class_num int = 3
	var student_num int = 5
	var total_sum float64 = 0.0

	for i := 1; i <= class_num; i++ {
		class_sum := 0.0
		for j := 1; j <= student_num; j++ {
			var score float64
			fmt.Printf("请输入第 %d 班 第 %d 学生成绩：\n", i, j)
			fmt.Scanln(&score)
			class_sum = class_sum + score
		}
		fmt.Printf("第%d个班级的平均分为：%v \n", i, class_sum / float64(student_num))
		//将各个班的总成绩累计到total_sum
		total_sum = total_sum + class_sum
	}
	fmt.Printf("各个班级的总成绩 %v 所有班级平均分为：%v \n", total_sum, total_sum / float64(class_num * student_num))
}
```
2. 案例二
```
package main

import "fmt"

func main() {
	//应用案例
	//统计三个班及格人数，每个班有 5 名同学
	//分析思路
	//1. 声明一个变量 pass_count 用于保存及格人数

	var class_num int = 3
	var student_num int = 5
	var total_sum float64 = 0.0
	var pass_count int = 0

	for i := 1; i <= class_num; i++ {
		class_sum := 0.0
		for j := 1; j <= student_num; j++ {
			var score float64
			fmt.Printf("请输入第 %d 班 第 %d 学生成绩：\n", i, j)
			fmt.Scanln(&score)
			class_sum = class_sum + score
			//判断分数是否及格
			if score >= 60 {
				pass_count++
			}
		}
		fmt.Printf("第 %d 个班级的平均分为：%v \n", i, class_sum / float64(student_num))
		//将各个班的总成绩累计到 total_sum
		total_sum = total_sum + class_sum
	}
	fmt.Printf("各个班级的总成绩 %v 所有班级平均分为：%v \n", total_sum, total_sum / float64(class_num * student_num))
	fmt.Printf("及格人数为 %v \n", pass_count)
}
```
3. 案例三
```
package main

import "fmt"

func main() {
	//应用案例
	//使用 for 循环完成下面的案例：请编写一个案例，可以接收一个整数，打印出金字塔。

	//编程思路
	//1. 打印一个矩形
	/*
	 ***
	 ***  ==> 矩形
	 ***
	*/

	//2. 打印半个金字塔
	/*
	 *		1 个 *
	 **		2 个 *
	 ***	3 个 *
	*/

	//3. 打印整个金字塔
	/*
	    *		  1层 1 个*	规律：2 * 层数 -1		空格 2	规律：总层数 - 当前层数
	   ***		2层 3 个*	规律：2 * 层数 -1		空格 1	规律：总层数 - 当前层数
	  *****		3层 4 个*	规律：2 * 层数 -1		空格 0	规律：总层数 - 当前层数
	*/

	//4. 将层数做成一个变量即可
	// var total_level int = 3

	//5. 打印空心金字塔
	/*
	    *
	   * *
	  *   *
	分析：在我们给每行打印 * 号，需要考虑是打印 * 还是打印 空格
	分析结果是：每层的第一个和最后一个是打印 * ，其他就应该是空，即输出空格。
	分析到一个例外情况，底层全部打 *
	*/
	var total_level int = 3
	//i 表示层数
	for i := 1; i <= total_level; i++ {
		//在打印*前先打印空格
		for k := 1; k <= total_level - i; k++ {
			fmt.Print(" ")
		}	//j 表示每层打印多少 *
			for j := 1; j <= 2 * i - 1; j++ {
				if j == 1 || j == 2 * i - 1 || i == total_level {
					fmt.Print("*")
				} else {
					fmt.Print(" ")
				}
			}
			fmt.Println()
	}
}
```
4. 案例四
```
package main

import "fmt"

func main() {
	//打印出九九乘法表
	//i 表示层数
	var num int = 9
	for i := 1; i <= num; i ++ {
		for j := 1; j <= i; j ++ {
			fmt.Printf("%v * %v = %v \t", j, i, j * i)
		}
		fmt.Println()
	}
}
```

### 跳转控制语句 - break
#### 快速入门
```
package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	//案例
	//随机生成 1-100 的一个数，知道生成了 99 整数，看看你一共用了几次

	//分析：编写一个无限循环的控制，然后不停的随机生成树，当生成了 99 时，就退出这个无限循环 ==> break

	//我们为了生成一个随机数，还需要个 rand 设置一个种子
	//time.Now().Unix(): 返回一个从 1970:01:01 的 0 时 0 分 0 秒到现在的秒数
	//如何随机的生成 1-100 的整数
	//rand.Seed(time.Now().Unix())
	//n := rand.Intn(100) + 1  // [0，100)
	//fmt.Println(n)

	var count int = 0
	for {
		rand.Seed(time.Now().UnixNano())
		n := rand.Intn(100) + 1  // [0，100)
		fmt.Println("n =", n)
		count ++
		if n == 99 {
			break //表示跳出for循环
		}
	}
	fmt.Printf("生成 99 一共使用了 %v 次 \n", count)
}
```
#### 基本介绍
1. break 语句用于终止某个语句块的执行，用于中断当前 for 循环或跳出 switch 语句。

#### 基本语法
```
{ ...
  break;
  ...
}
```

#### 注意事项和细节说明
1. break 语句出现在多层嵌套的语句块中时，可以通过标签指明要终止的是哪一层语句块。
2. 相关案例
```
package main

import "fmt"


func main() {
	//案例
	//这里演示以下指定标签的形式来使用 break
	label2:
	for i := 0; i < 4; i++ {
		//label1: // 设置一个标签
		for j := 0; j < 10; j++ {
			if j == 2 {
				//break // break 默认会跳出最近的 for 循环
				//break label1
				break label2
			}
			fmt.Println("j = ", j)
		}
	}
}
```
3. 对上面案例的说明
```
(1) break 默认会跳出最近的 for 循环
(2) break 后面可以指定标签，跳出标签对应的 for 循环。
```
4. 相关练习
```
//练习一
package main

import "fmt"


func main() {
	//100以内的数求和，求出 当和 第一次大于 20 的当前数。
	sum := 0
	for i := 1; i <= 100; i++ {
		sum += i // 求和
		if sum > 20 {
			fmt.Printf("当 sum > 20 ，当前数是 %v \n", i)
			break
		}
	}
}
```
```
package main

import "fmt"

func main() {
	//实现登录验证，有三次机会，如果用户名为"张无忌"，密码 "888" 提示登录成功，否则提示还有几次机会。

	var name string
	var pwd string

	var loginChance int = 3

	for i := 1; i <= 3; i++ {
		fmt.Println("请输入用户名：")
		fmt.Scanln(&name)
		fmt.Println("请输入密码：")
		fmt.Scanln(&pwd)

		if name == "张无忌" && pwd == "888" {
			fmt.Println("登录成功")
			break
		} else {
			loginChance--
			if loginChance > 0 {
				fmt.Printf("你还有 %v 登录机会，请珍惜 \n", loginChance)
			} else {
				fmt.Println("机会用完，没有登录成功")
			}
		}
	}
}
```

### 跳转控制语句 - continue
#### 基本介绍
1. continue 语句用于结束本次循环，继续执行下一次循环。
2. continue 语句出现在多层嵌套的循环语句体中时，可以通过标签指明要跳过的是哪一层循环，这个和前面的标签使用的规则一样。
3. 基本语法
```
{ ...
  continue;
  ...
}
```
4. 相关案例
```
package main

import "fmt"

func main() {
	// continue 案例
	label2:
	for i := 0; i < 4; i++ {
		//label1: // 设置一个标签
		for j := 0; j < 10; j++ {
			if j == 2 {
				//continue
				continue label2
			}
			fmt.Println("j =", j)
		}
	}
}
```
5. 相关练习
```
//练习一
package main

import "fmt"

func main() {
	for i := 0; i < 2; i++ {
		for j :=1; j < 4; j++ {
			if j == 2 {
				continue;
			}
			fmt.Println("i =", i, "j =", j)
		}
	}
}
```
```
//练习二
package main

import "fmt"


func main() {
	here:
	for i := 0; i < 2; i++ {
		//here:
		for j := 1; j < 4; j++ {
			if j == 2 {
				continue here
			}
			fmt.Println("i =", i ,"j =", j)
		}
	}
}
```
```
//练习三
package main

import "fmt"

func main()  {
	// 练习
	/*
	continue 实现打印 1-100 之内的奇数[要求使用 for 循环 + continue ]
	*/

	fmt.Println("1-100 之内的奇数如下所示：")
	for i := 1; i <= 100; i++ {
		if i % 2 == 0 {
			continue
		}
		fmt.Println(i)
	}
}
```
```
//练习四
package main

import "fmt"

func main() {
	//从键盘读取个数不确定的整数，并判断读入的正数和负数的个数，输入位0时结束程序。
	var positiveCount int //正数个数
	var negativeCount int //负数个数
	var num int
	for {
		fmt.Println("请输入一个整数：")
		fmt.Scanln(&num)

		if num == 0 {
			break // 终止 for 循环
		}
		if num > 0 {
			positiveCount++
			continue // 结束本次循环，进入下次循环
		}
		negativeCount++
	}
	fmt.Printf("正数个数是 %v 负数的个数是 %v", positiveCount, negativeCount)
}
```

### 跳转控制语句 - goto
#### 基本介绍
1. Go 语言的 goto 语句可以无条件地转移到程序中指定的行。
2. goto 语句通常与条件控制语句配合使用。可用来实现条件转移，跳出循环体等功能。
3. 在 Go 程序设计中一般不主张使用 goto 语句，以免造成程序的混乱，使理解和调试程序都产生困难。

#### 基本语法
```
goto label
...
label:statement
```

#### 快速入门案例
```
package main

import "fmt"


func main() {

	//演示 goto 的使用
	//goto 一般配合 if 语句使用
	//尽量避免使用 goto 语句

	var n int = 30
	fmt.Println("ok1")

	if n > 20 {
		goto label1
	}

	fmt.Println("ok2")
	fmt.Println("ok3")
	fmt.Println("ok4")
	label1:
	fmt.Println("ok5")
	fmt.Println("ok6")
	fmt.Println("ok7")
}
```

### 跳转控制语句 - return
#### 基本介绍
1. return 使用在方法或者函数中，表示跳出所在的方法或函数。
2. 说明：如果 return 是在普通的函数中，则表示跳出该函数，即不再执行函数中 return 后面代码，也可以理解成终止函数；如果 return 是在 main 函数，表示终止 main 函数，也就是说终止程序。

#### 快速入门案例
```
package main

import "fmt"


func main() {

	//演示 return 的使用
	var n int = 30
	fmt.Println("ok1")

	if n > 20 {
		return
	}
	fmt.Println("ok2")
	fmt.Println("ok3")
	fmt.Println("ok4")
	fmt.Println("ok5")
	fmt.Println("ok6")
	fmt.Println("ok7")
}
```
