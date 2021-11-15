# Golang 变量
## 为什么需要变量
1. 变量是程序的基本组成单位。
2. 不论是使用那种高级程序语言编写程序，变量都是其程序的基本组成单位。

## 变量的介绍
### 变量的概念
1. 变量相当于内存中一个数据存储空间的表示，你可以把变量看作是一个房间的门牌号，通过门牌号我们可以找到房间，同样的道理，通过变量名可以访问到变量值。

## 变量使用的基本步骤
1. 声明变量/定义变量
2. 赋值
3. 使用

### 变量快速入门案例
1. 案例一：
```
package main

import "fmt"  // fmt 包中提供格式化、输出、输入的函数

func main()  {
	// 定义变量
	var i int
	// 给 i 赋值
	i = 10
	fmt.Println("i =", i)
}
```

## 变量使用注意事项
1. 变量表示内存中的一个存储区域。
2. 该区域有自己的名称(变量名)和类型(数据类型)。
3. Golang 变量使用的三种方式
```
(1) 第一种：指定变量类型，声明后若不赋值，使用默认值。
(2) 第二种：根据值自行判定变量类型(类型推导)。
(3) 第三种：省略 var ，注意 := 左侧变量不应该是已经声明的，否则会导致编译错误。
(4) 多变量声明：在编程中，又是我们需要一次性声明多个变量，Golang 也提供这样的语法。
(5) 该区域的数据值可以在同一类型范围内不断变化。
(6) 变量在同一个作用域内不能重名。
(7) 变量 = 变量名 + 值 + 数据类型。
(8) Goalng 的变量如果没有赋初始值，编译器会使用默认值，比如 int 默认值 0 ；string 默认值为空字符串。
```
4. Golang 变量使用的第一种方式
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数

func main()  {
	// Golang 变量的使用方式一
	// 第一种：指定变量类型，声明后若不赋值，使用默认值
	// int 默认值是 0
	var i int
	fmt.Println("i =", i) // 输出结果：i = 0
}

```
5. Golang 变量使用的第二种方式
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数

func main()  {
	// 第二种：根据值自行判定变量类型(类型推导)
	var num = 10.11
	fmt.Println("num =", num)
}
```
6. Golang 变量使用的第三种方式
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数

func main()  {
	// 第三种：省略 var ，注意 := 左侧变量不应该是已经声明的，否则会导致编译错误。
	// 下面的方式等价于 var name string  name = "tom"
	name := "tom"
	fmt.Println("name =", name)
}

```
7. 多变量声明案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数

func main()  {
	//该案例演示 Golang 如何一次性声明多个变量
	//var n1, n2, n3 int
	//fmt.Println("n1 =",n1, "n2 =",n2, "n3 =",n3)

	//一次性声明多个变量的方式 2
	//var n1, name, n3 = 100, "tom", 888
	//fmt.Println("n1 =",n1, "name =",name, "n3 =",n3)

	//一次性声明多个变量的方式 3 , 同样可以使用类型推导
	n1, name, n3 := 100, "tom", 888
  fmt.Println("n1 =",n1, "name =",name, "n3 =",n3)
}
```
8. 多变量声明案例(全局变量)
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数

//定义全局变量
var n1 = 100
var n2 = 200
var name = "jack"

//上面的声明方式，也可以改成一次性声明

var (
	n3 = 300
	n4 = 900
	name2 = "mary"
)


func main()  {
	//输出全局变量
	fmt.Println("n1 =",n1, "name =",name, "n2 =",n2)
	fmt.Println("n3 =",n3, "name2 =",name2, "n4 =",n4)
}
```
9. 该区域的数据值可以在同一类型范围内不断变化-相关案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


//变量使用的注意事项
func main()  {
	//该区域的数据值可以在同一类型范围内不断变化
	var i int = 10
	i = 30
	i = 50
	fmt.Println("i =", i)
	// i = 1.2 //报错，原因是不能改变数据类型
}
```
10. 变量在(同一个作用域或代码块)内不能重名-相关案例
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


// 变量使用的注意事项
func main()  {
	// 该区域的数据值可以在同一类型范围内不断变化
	var i int = 10
	i = 30
	i = 50
	fmt.Println("i =", i)
	// i = 1.2 //报错，原因是不能改变数据类型

	//变量在(同一个作用域或代码块)内不能重名
	// var i int = 59
	// i := 99
}
```

## 变量的声明，初始化和赋值
1. 变量的基本语法
```
(1) 基本语法：var 变量名 数据类型
(2) var a int 这就是声明了一个变量，变量名是 a
(3) var num1 float32 这也声明了一个变量，表示一个单精度类型的小数，变量名是 num1
```
2. 初始化变量
```
(1) 在声明变量的时候，就给值
(2) var a int = 45 这就是初始化变量a
(3) 使用细节：如果生命时就直接赋值，可省略数据类型。
var b = 400
```
3. 给变量赋值
```
(1) 比如你先声明了变量: var num int // 默认 0
(2) 然后，再给值 num = 780 ;这就是给变量赋值。
```

## 程序中 + 号的使用
1. 当左右两边都是数值型，则做加法运算。
2. 当左右两边都是字符串，则做字符拼接。
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中 + 的使用
func main()  {
	var i = 1
	var j = 2
	var r = i + j // 做加法运算
	fmt.Println("r =", r)

	var str1 = "hello "
	var str2 = "world"
	var res = str1 + str2 //拼接操作
	fmt.Println("res =",res)
}
```

## 变量的数据类型
1. 每一种数据都定义了明确的数据类型，在内存中分配了不同大小的内存空间。
2. 数据类型中的基本数据类型包括：数值型、字符型(没有专门的字符型、使用 byte 来保存单个字母字符)、布尔型、字符串(官方将 string 归属到基本数据类型)。
3. 数据类型中的派生/复杂数据类型：指针(Pointer)、数组、结构体(struct)、管道(Channel)、函数、切片(slice)、接口(interface)、map。
4. 数值型变量包含：整数类型(int, int8 ,int16, int32, int64, uint, uint8, uint16, uint32, uint64, byte)、浮点类型：float32/float64 。

### 整数类型
#### 基本介绍
1. 简单来说，就是用来存放着整数值的，比如 0，-1，2345 等。

#### 整数类型的详细介绍

|类型|有无符号|占用存储空间|表数范围|备注|
|--|--|--|--|--|
|int8|有|1 字节|-128-127||
|int16|有|2 字节|-2^15~2^15-1||
|int32|有|4 字节|-2^31~2^31-1||
|int64|有|8 字节|-2^63~2^63-1||
|uint8|无|1 字节|0~255||
|uint16|无|2 字节|0~2^16-1||
|uint32|无|2 字节|0~2^32-1||
|uint64|无|2 字节|0~2^64-1||

#### 其他类型的详细介绍

|类型|有无符号|占用存储空间|表数范围|备注|
|--|--|--|--|--|
|int|有|32 位系统 4 个字节<br>64 位系统 8 个字节<br>|-2^31~2^31-1<br>-2^63~2^63-1<br>||
|uint|无|32 位系统 4 个字节<br>64 位系统 8 个字节<br>|0~2^32-1<br>0~2^64-1<br>||
|rune|有|与 int32 一样|-2^31~2^31-1|等价 int32，表示一个 Unicode 码||
|byte|无|与无符号的 uint8 等价|0~255|当要存储字符时选用 byte ||
#### 案例演示
```
package main

import "fmt" // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中整数类型的使用
func main()  {
	var i int = 1
	fmt.Println("i =", i)

	//测试一下 int8 范围 -128~127
	//其他的 int16，int32，int64，类推。
	var j int8 = -128
	fmt.Println("j =", j)
	//var j int8 = -129  报错，-129 已经超出范围

	//测试一下 uint8 的范围，其他的 uint16，uint32，uint64 类推即可
	var k uint8 = 0
	fmt.Println("k =", k)
	//var k uint8 = -1 报错，-1 已经超出范围


	//int, uint, rune, byte 的使用
	var a int = 8900
	fmt.Println("a =", a)

	var b uint = 1
	fmt.Println("b =", b)

	var c byte = 255
	fmt.Println("c =", c)
}
```

#### 整数类型的使用细节
1. Golang 各整数类型分：有符号和无符号，int、uint 的大小和系统有关。
2. Golang 的整型默认声明为 int 型。
```
package main

import (
	"fmt"
)  // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中整数类型的使用
func main()  {
	//整型的使用细节
	var n1 = 100 // n1 是什么类型
	//查看某个变量的数据类型
	//fmt.Printf()可以用于格式化输出
	fmt.Printf("n1的类型 %T \n", n1)
}
```
3. 如何在程序中查看某个变量的占用字节大小和数据类型。
```
package main

import (
	"fmt"
	"unsafe"
)  // fmt包中提供格式化、输出、输入的函数


func main()  {
	//如何在程序中查看某个变量的占用字节大小和数据类型
	var n2 int64 = 10
	//unsafe.Sizeof(n2) 是 unsafe 包的一个函数，可以返回 n2 变量占用的字节数。
	fmt.Printf("n2的类型 %T n2占用的字节数是 %d", n2, unsafe.Sizeof(n2))
}
```
4. Golang 程序中整型变量在使用时，遵守保小不保大的原则，即：在保证程序正确运行下，尽量使用占用空间小的数据类型。
```
package main

import (
	"fmt"
) // fmt 包中提供格式化、输出、输入的函数


func main()  {
	// Golang 程序中整型变量在使用时，遵守保小不保大的原则
	// var age int64 = 100 浪费内存
	var age byte = 90
	fmt.Println("age =", age)
}
```
5. bit：计算机中的最小存储单位。byte：计算机中基本存储单元。

### 浮点类型
#### 基本介绍
1. Golang 的浮点类型可以表示一个小数，比如 123.4，7.8，0.12 等等。

#### 浮点型的分类

|类型|占用存储|表数范围|
|--|--|--|
|单精度 float32 |4 个字节|-3.403E38~3.403E38|
|双精度 float64 |8 个字节|-1.798E308~1.798E308|

1. 关于浮点数在机器中存放形式的简单说明，浮点数=符号数+指数位+尾数位。
```
package main

import (
	"fmt"
)  // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中浮点数类型的使用
func main()  {
	var price float32 = 89.12
	fmt.Println("price =", price)

	var num1 float32 = -0.00089
	var num2 float64 = -7809656.09
	fmt.Println("num1 =", num1, "num2 =", num2)
}
```
2. 尾数部分可能丢失，造成精度损失。
```
package main

import (
	"fmt"
)  // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中浮点数类型的使用
func main()  {
	//尾数部分可能丢失，造成精度损失。
	var num3 float32 = -123.0000901
	var num4 float64 = -123.0000901
	fmt.Println("num3 =", num3, "num4 =", num4)
	//说明：float64 的精度比 float32 的要准确
	//说明：如果我们要保存一个精度高的数，则应该选用 float64
}
```

#### 浮点型使用细节
1. Golang 浮点类型有固定的范围和字段长度，不受具体 OS(操作系统) 的影响。
2. Golang 的浮点型默认声明为 float64 类型。
3. 浮点型常量有两种表示形式
```
(1) 十进制数形式: 如：5.12  .512(必须有小数点)
(2) 科学技术法形式: 如：5.1234e2 = 5.12 * 10的两次方 5.12E-2 = 5.12、10的2次方。
```
4. 通常情况下，应该使用 float64 ，因为它比 float32 更准确。
5. 浮点型使用细节的相关案例
```
package main

import (
	"fmt"
) // fmt 包中提供格式化、输出、输入的函数


//演示 Golang 中浮点数类型的使用
func main()  {
	// Golang 的浮点型默认声明为 float64 类型
	var num5 = 1.1
	fmt.Printf("num5的数据类型是 %T \n", num5)

	//十进制数形式: 如：5.12  .512(必须有小数点)
	num6 := 5.12
	num7 := .123 // => 0.123
	fmt.Println("num6 =", num6, "num7 =", num7)

	//科学计数法形式
	num8 := 5.1234e2 //5.1234 * 10的二次方
	num9 := 5.1234E2 //5.1234 * 10的二次方
	num10 := 5.1234E-2 //5.1234 / 10的二次方
	fmt.Println("num8 =", num8)
	fmt.Println("num9 =", num9)
	fmt.Println("num10 =", num10)
}
```

### 字符类型(char)
#### 基本介绍
1. Golang 中没有专门的字符类型，如果要储存单个字符(字母)，一般使用 byte 来保存。
2. 字符串就是一串固定长度的字符连接起来的字符序列。Go 的字符串是由单个字节连接起来的。也就是说对于传统的字符串是由字符组成的，而 Go 的字符串不同，它是由字节组成的。

#### 相关案例
1. 如果我们保存的字符在 ASCII 表的，比如[0-1,a-z,A-Z]直接可以保存到byte。
2. 如果我保存的字符对应码值大于 255，这时我们可以考虑使用int类型保存。
3. 如果我们需要按字符的方式输出，这时我们需要格式化输出，即 fmt.print("%c", c1)。
```
package main

import (
	"fmt"
) // fmt 包中提供提供格式化、输出、输入的函数


//演示 Golang 中字符类型的使用
func main()  {
	var c1 byte = 'a'
	var c2 byte = '0' // 字符的 0

	//当我直接输出 byte 值，就是输出了对应的字符的码值
	fmt.Println("c1 =", c1)
	fmt.Println("c2 =", c2)

	//如果我们希望输出对应字符，需要使用格式化输出
	fmt.Printf("c1 = %c c2 = %c \n", c1, c2)

	//var c3 byte = '北'  overflow溢出
	var c3 int = '北'
	fmt.Printf("c3 = %c c3对应码值 = %d", c3, c3)
}
```

#### 字符类型使用细节
1. 字符常量是用单引号 '' 括起来的单个字符。例如：var c1 byte = 'a' var c2 int = '中' var c3 byte = '9'。
2. Go 中允许使用转义字符 '\' 来将其后的字符转变为特殊字符型常量。例如：var c3 char = '\n' // '\n'表示换行符。
3. Go 语言的字符使用 UTF-8 编码。(英文字母 1 个字节、汉字 3个字符)
4. 在 Go 中，字符的本质是一个整数，直接输出时，是该字符对应的 UTF-8 编码的码值。
5. 可以直接给某个变量赋一个数字，然后按格式化输出时 %c，会输出该数字对应的 unicode 字符。
```
package main

import (
	"fmt"
) //fmt包中提供格式化、输出、输入的函数


//演示 Golang 中字符类型的使用
func main()  {
	//可以直接给某个变量赋一个数字，然后按格式化输出时 %c ，会输出该数字对应的 unicode 字符。
	var c4 int = 22269 // 22269 -> '国'
	fmt.Printf("c4 = %c", c4)
}

```
6. 字符类型是可以进行运算的，相当于一个整数，因为它都对应有Unicode码。
```
package main

import (
	"fmt"
) //fmt包中提供格式化，输出，输入的函数


//演示 Golang 中字符类型的使用
func main()  {
	//字符类型是可以进行运算的，相当于一个整数，因为它都对应有 Unicode 码。
	var n1 = 10 + 'a' // 10 + 97 = 107
	fmt.Println("n1 =", n1)
}
```

#### 字符类型本质探讨
1. 给付型储存到计算机，需要将字符对应的码值(整数)找出来
```
(1) 存储：字符-->对应码值-->二进制-->存储
(2) 读取：二进制-->码值-->字符-->读取
```
2. 字符和码值是通过字符编码表决定好的(是规定好)
3. Go语言的编码都同意成了utf-8，和其他的编程语言来说。非常的方便，很统一，再没有编码的困扰了。

### 布尔类型
#### 基本介绍
1. 布尔类型也叫 bool 类型，bool 类型数据只允许取值 true 和 false 。
2. bool 类型占一个字节。
3. bool 类型适于逻辑运算，一般用于程序流程控制。
```
(1) if 条件控制语句
(2) for 循环控制语句
```
4. 相关案例
```
package main

import (
	"fmt"
	"unsafe"
)


//演示 Golang 中 bool 类型的使用
func main() {
	var b  = false
	fmt.Println("b =", b)

	//注意事项
	//bool 类型占用存储空间是 1 个字节
	fmt.Println("b 的占用空间 =", unsafe.Sizeof(b))
	//bool 类型只能取 true 或者 false
}
```

### 字符串类型
#### 基本介绍
1. 字符串就是一串固定长度的字符连接起来的字符序列。Go 的字符串是由单个字节连接起来的。Go 语言的字符串的字节使用 UTF-8 编码标识 Unicode 文本。

#### 快速入门案例
```
package main

import (
	"fmt"
)


//演示 Golang 中 string 类型的使用
func main() {
	//string 的基本使用
	var address string = "北京长城 110 hello world!"
	fmt.Println(address)

}
```

#### 注意事项和使用细节
1. Go 语言的字符串的字节使用 UTF-8 编码标识 Unicode 文本，这样 Golang 统一使用 UTF-8 编码，乱码的问题不会再困扰程序员。
2. 字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
```
package main

import (
	"fmt"
)


//演示 Golang 中 string 类型的使用
func main() {
	//string 的基本使用
	var address string = "北京长城 110 hello world!"
	fmt.Println(address)

	//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
	//var str = "hello"
	//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。
}

```
3. 字符串的两种表示形式
```
(1) 双引号，会识别转义字符
(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
```
```
package main

import (
	"fmt"
)


//演示 Golang 中 string 类型的使用
func main() {
	//string 的基本使用
	var address string = "北京长城 110 hello world!"
	fmt.Println(address)

	//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
	//var str = "hello"
	//str[0] = 'a' 这里就不能去修改str的内容，即 Go 中的字符串是不可变的。

	//(1) 双引号，会识别转义字符
	//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
	str2 := "abd\nabc"
	fmt.Println(str2)

	//使用的反引号 ``
	str3 := `
	package main

	import (
		"fmt"
	)
	
	
	//演示 Golang 中 string 类型的使用
	func main() {
		//string 的基本使用
		var address string = "北京长城 110 hello world!"
		fmt.Println(address)
	
		//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
		//var str = "hello"
		//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。
	
		//(1) 双引号，会识别转义字符
		//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
		str2 := "abd\nabc"
		fmt.Println(str2)
	}
	`
	fmt.Println(str3)
}
```
4. 字符串的拼接方式
```
package main

import (
	"fmt"
)


//演示 Golang 中 string 类型的使用
func main() {
	//string 的基本使用
	var address string = "北京长城 110 hello world!"
	fmt.Println(address)

	//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
	//var str = "hello"
	//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。

	//(1) 双引号，会识别转义字符
	//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
	str2 := "abd\nabc"
	fmt.Println(str2)

	//使用的反引号 ``
	str3 := `
	package main

	import (
		"fmt"
	)
	
	
	//演示 Golang 中 string 类型的使用
	func main() {
		//string 的基本使用
		var address string = "北京长城 110 hello world!"
		fmt.Println(address)
	
		//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
		//var str = "hello"
		//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。
	
		//(1) 双引号，会识别转义字符
		//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
		str2 := "abd\nabc"
		fmt.Println(str2)
	}
	`
	fmt.Println(str3)

	//字符串拼接方式
	var str4 = "hello " + "world"
	str4 += " haha!"
	fmt.Println(str4)
}
```
5. 当一行字符串太长，需要使用到多行字符串，可以如下处理：
```
package main

import (
	"fmt"
)


//演示 Golang 中 string 类型的使用
func main() {
	//string 的基本使用
	var address string = "北京长城 110 hello world!"
	fmt.Println(address)

	//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
	//var str = "hello"
	//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。

	//(1) 双引号，会识别转义字符
	//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
	str2 := "abd\nabc"
	fmt.Println(str2)

	//使用的反引号 ``
	str3 := `
	package main

	import (
		"fmt"
	)
	
	
	//演示 Golang 中 string 类型的使用
	func main() {
		//string 的基本使用
		var address string = "北京长城 110 hello world!"
		fmt.Println(address)
	
		//字符串一旦赋值了，字符串就不能修改了：在 Go 中字符串是不可变的。
		//var str = "hello"
		//str[0] = 'a' 这里就不能去修改 str 的内容，即 Go 中的字符串是不可变的。
	
		//(1) 双引号，会识别转义字符
		//(2) 反引号，以字符串的原生形式输出，包括换行和特殊字符，可以实现防止攻击、输出源代码等效果。
		str2 := "abd\nabc"
		fmt.Println(str2)
	}
	`
	fmt.Println(str3)

	//字符串拼接方式
	var str4 = "hello " + "world"
	str4 += " haha!"
	fmt.Println(str4)

	//当一个拼接的操作很长时，可以分行写。加号要写在上面一行。
	var str5 = "hello " + "world" +
		" hello " + "world"
	fmt.Println(str5)
}
```

### 基本数据类型默认值
#### 基本介绍
1. 在Go中，数据类型都有一个默认值，当程序员没有赋值时，就会保留默认值，在 Go 中，默认值又叫零值。
2. 基本数据类型默认值如下：

|数据类型|默认值|
|--|--|
|整型|0|
|浮点型|0|
|字符串|""|
|布尔类型|false|
3. 相关案例
```
package main

import "fmt"

//演示 Golang 中基本数据类型默认值的使用
func main() {
	var a int // 0
	var b float32 // 0
	var c float64 // 0
	var isMarried bool // false
	var name string // ""
	//这里的 %v 表示按照变量的值输出
	fmt.Printf("a = %d, b = %f, c = %f, isMarried=%v, name=%v", a, b, c, isMarried, name)
	fmt.Printf("a = %d, b = %v, c = %v, isMarried=%v, name=%v", a, b, c, isMarried, name)
}
```

### 基本数据类型的转换
#### 基本介绍
1. Golang 和 Java/c 不同，Go 在不同类型的变量之间赋值时需要显式转换。也就是说 Golang 中数据类型不能自动转换。

#### 基本语法
1. 表达式 T(v) 将值 v 转换为类型 T
```
(1) T: 就是数据类型，比如 int32、int64、float32 等
(2) v: 就是需要转换的变量
```

#### 细节说明
1. Go 中，数据类型的转换可以是从表示范围小-->表示范围大，也可以范围大-->范围小。
2. 被转换的是变量存储的数据(即值)，变量本身的数据类型并没有变化！
3. 在转换中，比如将 int64 转换成 int8 ，编译时不会报错，只是转换的结果是按溢出处理，和我们希望的结果不一样。
4. 相关案例如下：
```
package main

import "fmt"

//演示 Golang 中基本数据类型的转换
func main() {
	var i int32 =100
	//希望将 i => float
	var n1 float32 = float32(i)
	var n2 int8 = int8(i)
	var n3 int64 = int64(i) //低精度 -> 高精度(仍然要强制转换)

	fmt.Printf("i=%v n1=%v n2=%v n3=%v \n", i, n1, n2, n3)

	//被转换的是变量存储的数据(即值)，变量本身的数据类型并没有变化。
	fmt.Printf("i type is %T\n", i)
	
	
	//在转换中，比如将 int64 转换成 int8 ，编译时不会报错，只是转换的结果是按溢出处理，和我们希望的结果不一样。
	var num1 float64 = 999999
	var num2 int8 = int8(num1)
	fmt.Println("num2=", num2)
}
```

### 基本数据类型和 string 的转换
#### 基本介绍
1. 在程序开发中，我们经常需要将基本数据类型转成 string 类型，或者将 string 类型转换成基本数据类型。

#### 基本数据类型转 string 类型
1. 方式1：fmt.Sprintf("%参数"，表达式)
```
(1) 参数需要和表达式的数据类型相匹配
(2) fmt.Sprintf()..会返回转换后的字符串
```
2. 方式2：使用 strconv 包的函数

#### 相关案例
##### 方式一：
```
package main

import "fmt"

//演示 Golang 中基本数据类型转成 string 使用
func main() {
	var num1 int = 99
	var num2 float64 = 23.456
	var b bool = true
	var myChar byte = 'h'
	var str string //空的 str

	//使用第一种方式来转换 fmt.Sprintf 方法

	str = fmt.Sprintf("%d", num1)
	fmt.Printf("str type %T str=%v \n", str, str)

	str = fmt.Sprintf("%f", num2)
	fmt.Printf("str type %T str=%v \n", str, str)

	str = fmt.Sprintf("%t", b)
	fmt.Printf("str type %T str=%v \n", str, str)

	str = fmt.Sprintf("%c", myChar)
	fmt.Printf("str type %T str=%v", str, str)

}
```
##### 方式二：
```
package main

import (
	"fmt"
	"strconv"
)


//演示 Golang 中基本数据类型转成 string 使用
func main() {
	//第二种方式 strconv 函数
	var num3 int = 99
	var num4 float64 = 23.456
	var b2 bool = true

	str = strconv.FormatInt(int64(num3),10)
	fmt.Printf("str type %T str=%v \n", str, str)

	//说明：'f' 格式 10：表示小数位保留 10 位 64：表示这个小数 float64 。
	str = strconv.FormatFloat(num4, 'f', 10, 64)
	fmt.Printf("str type %T str=%v \n", str, str)

	str = strconv.FormatBool(b2)
	fmt.Printf("str type %T str=%v", str, str)

	//strconv 包中有一个函数 Itoa
	var num5 int64 = 4567
	str = strconv.Itoa(int(num5))
	fmt.Printf("str type %T str=%v", str, str)
}

```
### string 类型转基本数据类型
1. 使用 strconv 包的函数
```
package main

import (
	"fmt"
	"strconv"
)

//演示 Golang 中 string 转成基本数据类型
func main() {

	var str string = "true"
	var b bool

	//b, _ = strconv.ParseBool(str)
	//1. strconv.ParseBool(str) 函数会返回两个值(value bool, err error)
	//2. 因为我只想获取到 value bool ，不想获取 err ，所以使用 _ 忽略。
	b, _ = strconv.ParseBool(str)
	fmt.Printf("b type %T b=%v \n", b, b)


	var str2 string = "1234590"
	var n1 int64
	var n2 int
	n1, _ = strconv.ParseInt(str2, 10, 64)
	n2 = int(n1)
	fmt.Printf("n1 type %T n1=%v \n", n1, n1)
	fmt.Printf("n2 type %T n2=%v \n", n2, n2)


	var str3 string = "123.456"
	var f1 float64
	f1, _ = strconv.ParseFloat(str3, 64)
	fmt.Printf("f1 type %T f1=%v \n", f1, f1)
}
```
2. 函数一般返回 int64 或者 float64 ，如果要获得 int32 或 float32 ，还需加一步转换。

#### 注意事项
1. 在将 String 类型转成基本数据类型时，要确保 String 类型能够转成有效的数据，比如我们可以把 “123”，转成一个整数，但是不能把 “hello” 转成一个整数，如果这样做，Golang 直接将其转成 0 。
```
package main

import (
	"fmt"
	"strconv"
)

//演示 Golang 中 string 转成基本数据类型
func main() {

	var str string = "true"
	var b bool

	//b, _ = strconv.ParseBool(str)
	//1. strconv.ParseBool(str)函数会返回两个值(value bool, err error)
	//2. 因为我只想获取到 value bool，不想获取 err，所以使用 _ 忽略。
	b, _ = strconv.ParseBool(str)
	fmt.Printf("b type %T b=%v \n", b, b)


	var str2 string = "1234590"
	var n1 int64
	var n2 int
	n1, _ = strconv.ParseInt(str2, 10, 64)
	n2 = int(n1)
	fmt.Printf("n1 type %T n1=%v \n", n1, n1)
	fmt.Printf("n2 type %T n2=%v \n", n2, n2)


	var str3 string = "123.456"
	var f1 float64
	f1, _ = strconv.ParseFloat(str3, 64)
	fmt.Printf("f1 type %T f1=%v \n", f1, f1)

	//注意事项:  在将 String 类型转成基本数据类型时，要确保 String 类型能够转成有效的数据，比如我们可以把 "123" ，转成一个整数，但是不能把 "hello" 转成一个整数，如果这样做，Golang 直接将其转成 0 。
	//哪怕 var n3 int64 = 11，最后 n3 结果还是 0 。
	var str4 string = "hello"
	var n3 int64
	n3, _ = strconv.ParseInt(str4,10,64)
	fmt.Printf("n3 type %T n3=%v \n", n3, n3)

}
```

### 指针
#### 基本介绍
1. 基本数据类型，变量存的就是值，也叫值类型。
2. 获取变量的地址，用 & ，比如: var num int，获取num的地址：&num
3. 指针类型，变量存的是一个地址，这个地址指向的空间存的才是值。比如：var ptr *int = &num
4.获取指针类型指向的值，使用: *，比如: var ptr *int，使用 *ptr 获取 ptr 指向值。 
5. 相关案例
```
//案例一
package main

import "fmt"

//演示 Golang 中 指针类型
func main() {
	//基本数据类型在内存布局
	var i int = 10
	// i 的地址是什么，&i
	fmt.Println("i的内存地址=", &i)

	//下面的 var ptr *int = &i
	//1. ptr 是一个指针变量
	//2. ptr 的类型 *int
	//3. ptr 本身的值&i
	var ptr *int = &i
	fmt.Printf("ptr=%v \n", ptr)
	fmt.Printf("ptr的地址 = %v \n", &ptr)
	fmt.Printf("ptr指向的值 = %v", *ptr)
}
```
```
//案例二
package main

import "fmt"

//演示 Golang 中 指针类型
func main() {
	//1. 写一个程序，获取一个 int 变量 num 的地址，并显示到终端。
	//2. 将 num 的地址赋给指针 ptr ，并通过 ptr 去修改 num 值。
	var num int = 9
	fmt.Printf("num address=%v \n", &num)

	var ptr *int
	ptr = &num
	*ptr = 10
	fmt.Println("num =", num)
}
```
```
//案例三
package main

import "fmt"

//演示 Golang 中 指针类型
func main() {
	//深入理解指针案例
	var a int = 300
	var b int = 400
	var ptr *int = &a
	*ptr = 100
	ptr = &b
	*ptr = 200
	fmt.Printf("a = %d, b = %d, *ptr = %d", a, b, *ptr)
}
```

#### 指针的细节说明
1. 值类型，都有对应的指针类型，形式为 * 数据类型，比如 int 的对应的指针就是  *int，float32 对应的指针类型就是 *float32 ，依此类推。
2. 值类型包括：基本数据类型 int 系列、float 系列、bool、string、数组和结构体 struct。

### 值类型和引用类型
#### 常见的值类型和引用类型
1. 值类型：基本数据类型 int 系列、float 系列、bool、string、数组和结构体 struct 。
2. 引用类型：指针、slice 切片、map、管道 chan、interface 都是引用类型。

#### 值类型和引用类型使用特点
1. 值类型：变量直接存储值，内存通常在栈中分配。
2. 引用类型：变量存储的是一个地址，这个地址对应的空间才真正存储数据(值)，内存通常在堆上分配，当没有任何变量引用这个地址时，该地址对应的数据空间就成为一个垃圾，由 GC 来回收。

### 标识符的命名规范
#### 标识符概念
1. Golang 对各种变量、方法、函数等命令时使用的字符序列称为标识符。
2. 凡是自己可以起名字的地方都叫标识符。

#### 标识符的命名规则
1. 由 26 个英文字母大小写，0-9 ，_组成。
2. 数字不可以开头。
3. Golang 中严格区分大小写。
```
package main

import "fmt"

//演示 Golang 中标识符的使用
func main() {
	//Golang 中严格区分大小写
	//Golang 中认为 num 和 Num 是不同的变量
	var num int64 = 10
	var Num int64 = 12
	fmt.Println("num =", num, "Num =", Num)
	fmt.Printf("num = %v Num = %v", num, Num)
}
```
4. 标识符不能包含空格。
```
package main

import "fmt"

//演示 Golang 中标识符的使用
func main() {
	//Golang 中严格区分大小写
	//Golang 中认为 num 和 Num 是不同的变量
	var num int64 = 10
	var Num int64 = 12
	fmt.Println("num =", num, "Num =", Num)
	fmt.Printf("num = %v Num = %v", num, Num)
	
	//标识符不能包含空格
	//var ab c int = 30
}
```
5. 下划线 _ 本身在 Go 中是一个特殊的标识符，称为空标识符。可以代表任何其他的标识符，但是它对应的值会被忽略(比如：忽略某个返回值)。所以仅能被作为占位符使用，不能作为标识符使用。
```
package main

import "fmt"

//演示 Golang 中标识符的使用
func main() {
	//Golang 中严格区分大小写
	//Golang 中认为 num 和 Num 是不同的变量
	var num int64 = 10
	var Num int64 = 12
	fmt.Println("num =", num, "Num=", Num)
	fmt.Printf("num = %v Num = %v", num, Num)

	//标识符不能包含空格
	//var ab c int = 30

	//_是空标志符，用于占用
	//var _ int = 40
}
```
6. 不能以系统保留关键字作为标识符，比如 break，if 等等...。
```
package main

import "fmt"

//演示 Golang 中标识符的使用
func main() {
	//Golang 中严格区分大小写
	//Golang 中认为 num 和 Num是不同的变量
	var num int64 = 10
	var Num int64 = 12
	fmt.Println("num =", num, "Num =", Num)
	fmt.Printf("num = %v Num = %v \n", num, Num)

	//标识符不能包含空格
	//var ab c int = 30

	//_是空标志符，用于占用
	//var _ int = 40


	//语法通过，但是尽量不要使用
	var int int = 90
	fmt.Println(int)
}
```

#### 标识符命名注意事项
1. 包名：保持 package 的名字和目录保持一致，尽量采取有意义的包名，简短，有意义，不要和标准库冲突。
2. 变量名、函数名、常量名:采用驼峰法。
3. 如果变量名、函数名、常量名首字母大写，则可以被其他的包访问；如果首字母小写，则只能在本包中使用(注：可以简单的理解成，首字母大写是公有的，首字母小写是私有的)
```
//目录: /Users/mac/Desktop/GoProject/src/go_code/chapter03/variable_case_19/main/main.go
package main

import (
	"fmt"
	//为了使用 utils.go 文件的变量或者函数，我们需要先引入该包
	"GoProject/src/go_code/chapter03/variable_case_19/model"
)

//演示 Golang 中标识符的使用
func main() {
	//我们使用 utils.go 的 heroName 包名.标识符
	//fmt.Println(model.heroName) 首字母小写表示私有，无法导入
	fmt.Println(model.HeroName)
}


//目录: /Users/mac/Desktop/GoProject/src/go_code/chapter03/variable_case_19/model/utils.go

package model

//定义变量
//var heroName string = "宋江"
var HeroName string = "公孙胜"
```

#### 保留关键字介绍
1. 在 Go 中，为了简化代码编译过程中对代码的解析，其定义的保留关键字只有 25 个。

|保留关键字|保留关键字|保留关键字|保留关键字|保留关键字|
|--|--|--|--|--|--|
|break|default|func|interface|select|
|case|defer|go|map|struct|
|chan|else|goto|package|switch|
|const|fallthrought|if|range|type|
|continue|for|import|return|var|

#### 预定义标识符介绍
1. 除了保留关键字外，Go 还提供了 36 个预定的标识符，其中包括基础数据类型和系统内嵌函数。

|预定义标识符|预定义标识符|预定义标识符|预定义标识符|预定义标识符|预定义标识符|
|--|--|--|--|--|--|
|append|bool|byte|cap|close|complex|
|complex64|complex128|unit16|copy|false|float32|
|float64|imag|int|int8|int16|int16|
|int32|int64|iota|len|make|new|
|nil|panic|uint64|print|println|real|
|recover|string|true|uint|uint8|unitprt|

## 常量
### 常量介绍
1. 常量使用 const 修改
2. 常量在定义的时候，必须初始化
3. 常量不能修改
4. 常量只能修饰 bool、数值类型(int、float系列)、string 类型
5. 语法
```
const identifier [type] = value
```
6. 举例说明
```
package main

import "fmt"


func main() {
	var num int
	num = 90 // ok
	// 常量在声明的时候，必须赋值
	const tax int = 0
	// 常量是不能修改
	// tax = 10
	// 常量只能修饰 bool、数值类型(int、float系列)、string 类型(基本数据类型)
	// const name  = "Tom" // ok
	// const tax float64  = 0.8 // ok
	// const a int //error
	// const b  = 9 /3 // ok
	// const c = getVal() // error


	fmt.Println(num, tax)
}
```

### 常量的注意事项
1. 比较简洁的写法
```
const (
    a = 1
    b = 2
)
```
2. 专业写法
```
func main() {
    const (
        a = iota //逐行递增
        b
        c
    )
    fmt.Println(a, b, c) // 0, 1, 2
}
```
3. Golang 中没有常量名必须字母大写的规范
4. 仍然通过首字母的大小写来控制常量的访问范围
