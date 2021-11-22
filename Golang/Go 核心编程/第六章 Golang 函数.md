# 第六章 Golang 函数
## 为什么需要函数
1. 完成一个这样的需求：
```
(1) 输入两个数，再输入一个运算符(+, -, *, /)，得到结果。
(2) 对比传统的解决方法与使用函数方法的不同。
```
2. 传统方法的代码
```
package main

import "fmt"

func main() {
	/*
	1. 请大家完成这样一个需求：
		输入两个数，再输入一个运算符(+, -, *, /)，得到结果。
	*/


	var n1 float64 = 1.2
	var n2 float64 = 2.3
	var operator byte = '+'
	var res float64

	switch operator {
		case '+':
			res = n1 + n2
		case '-':
			res = n1 - n2
		case '*':
			res = n1 * n2
		case '/':
			res = n1 / n2
		default:
			fmt.Println("操作符输入有误...")
	}
	fmt.Println("res =", res)
}
```
3. 上面代码的问题
  - 上面的写法是可以完成功能，但是代码冗余。
  - 同时不利于代码维护。
  - 函数可以解决这个问题。

## 函数
1. 为了完成某一功能的程序指令(语句)的集合，称为函数。在 Go 中，函数分为：自定义函数、系统函数。

## 基本语法
```
func 函数名 (形参列表) (返回值列表) {
  执行语句
  return 返回值列表
}
(1) 形参列表：表示函数的输入。
(2) 函数中的语句：表示为了实现某一功能代码块。
(3) 函数可以有返回值，也可以没有。
```

## 快速入门案例
```
package main

import "fmt"


func cal(n1 float64, n2 float64, operator byte) float64 {
	//将计算功能，放到一个函数中，在需要使用时，调用即可。

	var res float64

	switch operator {
		case '+':
			res = n1 + n2
		case '-':
			res = n1 - n2
		case '*':
			res = n1 * n2
		case '/':
			res = n1 / n2
		default:
			fmt.Println("操作符输入有误...")
	}
	return res
}



func main() {
	/*
		1. 请大家完成这样一个需求：
			输入两个数，再输入一个运算符(+, -, *, /)，得到结果。
	*/


	//进行参数传递
	var n1 float64 = 1.2
	var n2 float64 = 2.3
	var operator byte = '+'

	result := cal(n1, n2, operator) //函数调用

	fmt.Println("result =", result)
}
```

## 包的引出
1. 在实际开发中，我们往往需要在不同的文件中，去调用其他文件的定义的函数，比如 main.go 中，去使用 utils.go 文件中的函数，如何实现？==> 包
2. 现在有两个程序员共同开发一个 Go 项目，程序员小明希望定义函数 Cal ，程序员小北也想定义函数也叫 Cal ，两个程序员为此还吵了起来，怎么办？==> 包

### 包的原理
1. 包的本质实际上就是创建不同的文件夹，来存放程序文件。

### 包的介绍
#### 包的基本概念
1. Golang 的每一个文件都是属于一个包的，也就是说 Golang 是以包的形式来管理文件和项目目录结构的。

#### 包的三大作用
1. 区分相同名字的函数、变量等标识符。
2. 当程序文件很多时，可以很好地管理项目。
3. 控制函数、变量等访问范围，即作用域。

#### 包的相关说明
1. 打包基本语法
```
package util
```
2. 引入包基本语法
```
import "包的路径"
```

#### 快速入门案例
1. 说明：我们将 func Cal 定义到文件 utils.go ，将 utils.go 放到一个包中，当其他文件需要使用到 utils.go 的方法时，可以使用 import 该包，就可以使用了。
2. util 包中 util.go 内容
```
package utils

import "fmt"


// 为了让其他包的文件使用cal函数，需要将c大写类似于其他语言的public
func Cal(n1 float64, n2 float64, operator byte) float64 {
	//将计算功能，放到一个函数中，在需要使用时，调用即可。

	var res float64

	switch operator {
	case '+':
		res = n1 + n2
	case '-':
		res = n1 - n2
	case '*':
		res = n1 * n2
	case '/':
		res = n1 / n2
	default:
		fmt.Println("操作符输入有误...")
	}
	return res
}
```
3. main 包中 main.go 的内容
```
package main

import (
	"GoProject/src/go_code/chapter06/function_case_03/utils" //导包
	"fmt"
)

func main() {
	/*
		1. 请大家完成这样一个需求：
	       输入两个数，再输入一个运算符(+, -, *, /)，得到结果。
	*/


	//进行参数传递
	var n1 float64 = 1.2
	var n2 float64 = 2.3
	var operator byte = '+'

	result := utils.Cal(n1, n2, operator) //函数调用
	
	fmt.Println("result = ", result)
}
```

#### 包的注意事项和细节说明
1. 在给一个文件打包时，该包对应一个文件，比如这里的 utils 文件夹对应的包名就是 utils ，文件的包名通常和文件所在文件夹一致，一般为小写字母。
2. 当一个文件要使用其他包函数或变量时，需要先引入对应的包。
```
(1) 引入方式1：import "包名"
(2) 引入方式2：
import (
    "包名"
    "包名"
)
(3) package 指令在文件第一行，然后是 import 指令。
(4) 在 import 包时，路径从 $GOPATH 的 src 下开始，不用带 src ，编译器会自动从 src 下开始引入。
```
3. 为了让其他包的文件，可以访问到本包的函数，则该函数名的首字母需要大写，类似其他语言的 public ，这样才能跨包访问。
4. 在访问其他包函数时，其语法是包名.函数名。
5. 如果包名较长，Go 支持给包取别名，注意细节，取别名后，原来的包名就不能使用了。
6. 在同一个包下，不能有相同的函数名(也不能有相同的全局变量名)，负责报重复定义。
7. 如果你要编译成一个可执行程序文件，就需要将这个包声明为 main ，即 package main 。这个就是一个语法规范，如果你是写一个库，包名可以自行定义。

## 函数的调用机制
### 函数调用机制的底层分析
1. 栈区：基本数据类型一般分配到栈区，编译器存在一个逃逸分析。
2. 堆区：引用数据类型一般分配到堆区，编译器存在一个逃逸分析。
3. 代码区：代码的存放位置。

### 快速入门案例
1. 案例代码一
```
package main

import "fmt"

func test(n1 int) {
	n1 = n1 + 1
	fmt.Println("n1 =", n1)
}

func main() {
	//定于变量
	n1 := 10
	//调用 test
	test(n1)
	fmt.Println("n1 =", n1)
}
```
2. 代码说明
  - 在调用一个函数时，会给该函数分配一个新的空间。编译器会通过自身的处理让这个新的空间和其他的栈的空间区分开来。
  - 在每个函数对应的栈中，数据空间式独立的，不会混淆。
  - 当一个函数调用完毕(执行完毕)，程序会销毁这个函数对应的栈空间。
3. 案例代码二
```
package main

import "fmt"

//一个函数 getSum
func getSum(n1 int, n2 int) int {
	sum := n1 + n2 // 30
	fmt.Println("getSum sum =", sum)
	//当函数有 return 语句是，就是将结果返回给调用者
	//即谁调用我，就返回给谁
	return sum
}

func main() {
	n1 := 10
	n2 := 20
	sum := getSum(n1, n2)
	fmt.Println("main sum = ", sum)
}
```

## return 语句
### 基本介绍
1. Go 语言支持返回多个值，这一点是其他编程语言没有的。
2. 基本语法
```
func 函数名 (形参列表) (返回值类型列表) {
    语句...
    return 返回值列表
}

(1) 如果返回多个值时，在接收时，希望忽略某个返回值，则使用 _ 符号表示占位忽略。
(2) 如果返回值只有一个，(返回值类型列表)可以不写()。
```

### 快速入门案例
1. 案例代码
```
package main

import "fmt"


//请编写一个函数，可以计算两个数的和与差，并返回结果。
func getSumAndSub(n1 int, n2 int) (int, int) {
	sum := n1 + n2
	sub := n1 - n2
	return sum, sub
}

func main() {
	//调用getSumAndSub
	res1, res2 := getSumAndSub(1, 2) // res1 = 3, res2 = -1
	fmt.Printf("res1 = %v res2 = %v \n", res1, res2)
}
```

## 函数的递归调用
### 基本介绍
1. 一个函数在函数体内又调用了本身，我们成为递归调用。

### 快速入门案例
1. 案例代码一
```
package main

import "fmt"

func test(n int) {
	if n > 2 {
		n--
		test(n)
	}
	fmt.Println("n =", n)
}


func main() {
	//看一段代码
	test(4)
}
```
2. 案例代码二
```
package main

import "fmt"

func test2(n int) {
	if n > 2 {
		n--
		test2(n)
	} else {
		fmt.Println("n =", n)
	}

}


func main() {
	//看一段代码
	test2(4)
}
```

### 递归调用的总结
1. 函数递归需要遵守的重要守则：
  - 执行一个函数式，就创建一个新的受保护的独立空间(新函数栈)。
  - 函数的局部变量是独立的，不会相互影响的。
  - 递归必须向退出递归的条件逼近，否则就是无限递归。
  - 当一个函数执行完毕，或者遇到 return ，就会返回，遵守谁调用，就将结果返回给谁，同时当函数执行完毕或者返回时，该函数本身也会被系统销毁了。

### 课堂练习
1. 练习一
```
package main

import "fmt"

/*
	请使用递归的方式，求出斐波那契数列1，1，2，3，5，8，13...
	给你一个整数n，求出它的斐波那契数是多少？
*/

func fbn(n int) int {
	if (n == 1 || n == 2) {
		return 1
	} else {
		return fbn(n-1) + fbn(n-2)
	}
}

func main() {
	//测试
	res := fbn(3)
	fmt.Println("res =", res)
	fmt.Println("res =", fbn(4)) // 3
	fmt.Println("res =", fbn(5)) // 5
	fmt.Println("res =", fbn(6)) // 8
}
```
2. 练习二
```
package main

import "fmt"

/*
	求函数值：已知f(1) = 3；f(n) = 2 * f(n-1) + 1;
	请使用递归的思想编程，求出f(n)的值
*/

func f(n int) int {
	if n == 1 {
		return 3
	} else {
		return 2 * f(n - 1) + 1
	}
}

func main() {
	//测试一下
	fmt.Println("f(1) =", f(1))
	fmt.Println("f(5) =", f(5))
}
```
3. 练习三
```
package main

import "fmt"

/*
	猴子吃桃子问题：有一堆桃子，猴子第一天吃了其中的一般，并再多吃了一个！以后每天猴子都吃其中的一半，
	然后再多吃一个。当到第十天时，想再吃时(还没吃)，发现只有一个桃子了。
	问题：最初共有多少个桃子？
*/

/*
	思路分析：
	1. 第十天只有一个桃子
	2. 第九天有几个桃子 = (第十天桃子数量 + 1) * 2
	3. 规律：第 n 天的桃子数量 peach(n) = (peach(n + 1) + 1) * 2
*/

//n 范围是 1--10 之间
func peach(n int) int {
	if n > 10 || n < 1 {
		fmt.Println("输入天数不对")
		return 0 //表示没有得到正确数量
	} else if n == 10 {
		return 1
	} else {
		return (peach(n + 1) + 1) * 2
	}
}

func main() {
	//测试一下
	fmt.Println("peach(1) =", peach(1))
	fmt.Println("peach(9) =", peach(9))
}
```

## 函数注意事项和细节讨论
1. 函数的形参列表可以是多个，返回值列表也可以是多个。
2. 形参列表和返回值列表的数据类型可以是值类型和引用类型。
3. 函数的命名遵循表示符命名规范，首字母不能是数字，首字母大写该函数可以被本包文件和其他包文件使用，类似 public ，首字母小写，只能被本报文件使用，其他包文件不能使用，类似 private 。
4. 函数中的变量是局部的，函数外不生效。
```
package main

import "fmt"



//函数中的变量是局部的，函数外不生效。
func test() {
	//n1 是 test 函数的局部变量，只能在 test 函数中使用
	var n1 int = 10
	fmt.Println(n1)
}
func main() {
	// 这里不能使用 n1，因为 n1 是 test 函数的局部变量
	//fmt.Println("n1 =", n1)
	test()
}
```
5. 基本数据类型和数组默认都是值传递，即进行值拷贝。在函数内修改，不会影响到原来的值。
```
package main

import "fmt"


func test02(n1 int) {
	n1 = n1 + 10
	fmt.Println("test02 n1 =", n1)
}


func main() {
	n1 := 20
	test02(n1)
	fmt.Println("main() n1 =", n1)
}
```
6. 如果希望函数内的变量能修改函数外的变量，可以传入变量的地址，函数内以指针的方式操作变量。
```
package main

import "fmt"



func test03(n1 *int) {

	fmt.Printf("n1的地址 %v \n", &n1)
	*n1 = *n1 + 10
	fmt.Println("test03() n1 =", *n1) // 30
}


func main() {

	num := 20
	fmt.Printf("num的地址=%v \n", &num)
	test03(&num)
	fmt.Println("main() num =", num)  // 30
}
```
7. Go 函数不支持传统函数重载。
8. 在 Go 中，函数也是一种数据类型，可以赋值给一个变量，则该变量就是一个函数类型的变量了。通过该变量可以对函数调用。
```
package main

import "fmt"


//在 Go 中，函数也是一种数据类型，可以赋值给一个变量，则该变量就是一个函数类型的变量了。通过该变量可以对函数调用。

func getSum(n1 int, n2 int) int {
	return n1 + n2
}


func main() {

	a := getSum

	fmt.Printf("a的类型%T, getSum类型是%T \n", a, getSum)

	res := a(10, 40) // 等价 res := getSum(10, 40)
	fmt.Println("res =", res)
}
```
9. 函数既然是一种数据类型，因此在 Go 中，函数可以作为形参，并且调用！
```
package main

import "fmt"


//函数既然是一种数据类型，因此在 Go 中，函数可以作为形参，并且调用！

func getSum(n1 int, n2 int) int {
	return n1 + n2
}

func myFun(funvar func(int, int) int, num1 int, num2 int) int {
	return funvar(num1, num2)
}

func main() {

	//看案例
	res2 := myFun(getSum, 50, 60)
	fmt.Println("res2 =", res2)
}
```
10. 为了简化数据类型定义，Go 支持自定义数据类型。
```
(1) 基本语法：type 自定义数据类型名 数据类型 // 理解：相当于一个别名
(2) 案例：type myInt int // 这是 myInt 就等价 int 来使用了。
(3) 案例：type mySum func(int, int) int //这是 mySum 就等价于一个函数类型 func(int, int) int
```
  - 案例一
```
package main

import "fmt"


func main() {

	type myInt int //给 int 取了别名，在 Golang 中 myInt 和 int 虽然都是 int 类型，但是 Golang 认为 myInt 和 int 是两个类型

	var num1 myInt
	var num2 int
	num1 = 40
	// num2 = num1  Golang 认为类型不同
	num2 = int(num1)
	fmt.Println("num1 =", num1)
	fmt.Println("num2 =", num2)
}
```
  - 案例二
```
package main

import "fmt"


func getSum(n1 int, n2 int) int {
	return n1 + n2
}

type myFunType func(int, int) int //这是 myFunType 就是 func(int, int) int


func myFun(funvar myFunType, num1 int, num2 int) int {
	return funvar(num1, num2)
}

func main() {

	//看案例
	res3 := myFun(getSum, 50, 60)
	fmt.Println("res3 =", res3)
}
```
11. 支持对函数返回值命名
```
package main

import "fmt"


func getSumAndSub(n1 int, n2 int) (sum int, sub int) {
	sum = n1 + n2
	sub = n1 - n2
	return
}

func main() {
	sum, sub := getSumAndSub(10, 20)
	fmt.Printf("sum = %v sub= %v \n", sum, sub)
}
```
12. 使用 _ 标识符，忽略返回值
```
package main

import "fmt"


func getSumAndSub(n1 int, n2 int) (sum int, sub int) {
	sum = n1 + n2
	sub = n1 - n2
	return
}

func main() {
	sum, _ := getSumAndSub(10, 20)
	fmt.Printf("sum = %v \n", sum)
}
```
13. Go 支持可变参数
```
//支持 0 到多个参数
func sum(arg...int) sum int {
}
//支持 1 到多个参数
func sum(n1 int, arg...int) sum int {
}
说明：
(1) args 是 slice ，通过 arg[index] 可以访问到各个值。
(2) 如果一个函数的形参列表中有可变参数，则可变参数需要放在形参列表。
```
  - 案例一
```
package main

import "fmt"


//案例编写：编写一个函数 sum，可以求出 1 到多个 int 的和
func sum(n1 int, args...int) int {
	sum := n1
	//遍历 args
	for i := 0; i < len(args); i++ {
		sum += args[i] //args[0] 表示取出 args 切片的第一个元素值，其他依次类推。
	}
	return sum
}

func main() {
	//测试一下可变参数的使用
	res4 := sum(10, 11, 13, 16)
	fmt.Println("res4 =", res4)
}
```

## init 函数
### 基本介绍
1. 每一个源文件都可以包含一个 init 函数，该函数会在 main 函数执行前，被 Go 运行框架调用，也就是说 init 会在 main 函数前被调用。

### 快速入门案例
1. 案例一
```
package main

import "fmt"


// init 函数，通常可以在 init 函数中完成初始化工作
func init() {
	fmt.Println("init()...")
}


func main() {
	fmt.Println("main()...")
}
```

### init 函数的注意事项和细节
1. 如果一个文件同时包含全局变量定义，init 函数和 main 函数，则执行的流程是变量定义 -> init 函数 -> main 函数。
```
package main

import "fmt"


var age = test()

//为了看到全局变量是先被初始化的，我们这里先写函数
func test() int {
	fmt.Println("test()")
	return 90
}
//init 函数，通常可以在 init 函数中完成初始化工作
func init() {
	fmt.Println("init()...")
}


func main() {
	fmt.Println("main()...")
}
```
2. init 函数最主要的作用，就是完成一些初始化的工作。

## 匿名函数
### 基本介绍
1. Go 支持匿名函数，如果我们某个函数只是希望使用一次，可以考虑使用匿名函数，匿名函数也可以实现多次调用。
2. 匿名函数使用方式一：在定义匿名函数时就直接调用，这种方式匿名函数只能调用一次。
```
package main

import "fmt"


func main() {
	//在定义匿名函数时就直接调用，这种方式匿名函数智能调用一次

	//案例演示，求来两个数的和，使用匿名函数的方式完成
	res1 := func (n1 int, n2 int) int {
		return n1 + n2
	}(10,20)

	fmt.Println("res1 =", res1)
}
```
3. 匿名函数使用方式二：将匿名函数赋给一个变量(函数变量，在通过该变量来调用匿名函数)

```
package main

import "fmt"


func main() {
	//在定义匿名函数时就直接调用，这种方式匿名函数智能调用一次

	//案例演示，求来两个数的和，使用匿名函数的方式完成
	res1 := func (n1 int, n2 int) int {
		return n1 + n2
	}(10,20)

	fmt.Println("res1 =", res1)

	//将匿名函数 func (n1 int, n2 int) int 赋给 a 变量。
	//则 a 的数据类型就是函数类型，此时，我们可以通过 a 完成调用。

	a := func (n1 int, n2 int) int {
		return n1 - n2
	}

	res2 :=a(10, 30)

	fmt.Println("res2 =", res2)

	res3 :=a(90, 30)

	fmt.Println("res3 =", res3)
}
```
4. 全局匿名函数：如果将匿名函数赋给一个全局变量，那么这个匿名函数，就成为一个全局匿名函数，可以在程序有效。

```
package main

import "fmt"

var (
	//Fun1就是一个全局匿名函数
	Fun1 = func (n1 int, n2 int) int {
		return n1 * n2
	}
)

func main() {
	//全局匿名函数的使用
	res4 := Fun1(4, 9)
	fmt.Println("res4 =", res4)
}
```

## 闭包
### 基本介绍
1. 闭包就是一个函数与其相关的引用环境组合的一个整体。

### 快速入门案例
1. 案例一
```
package main

import "fmt"

//累加器
func AddUpper() func (int) int {
	var n int = 10
	return func (x int) int {
		n = n + x
		return n
	}
}


func main() {
	//使用前面的代码
	f := AddUpper()
	fmt.Println(f(1)) // 11
	fmt.Println(f(2)) // 13
	fmt.Println(f(3)) // 13

}
```
2. 案例说明
  - AddUpper 是一个函数，犯规的数据类型是 fun(int) int
  - 闭包的说明：返回的是一个函数，但是这个匿名函数引用到函数外的 n ，因此这个匿名函数就和 n 形成一个整体，构成了闭包。 
  - 大家可以这样理解：闭包是类，函数是操作，n 是字段。函数和它使用到的 n 构成闭包。
  - 当我们反复的调用f函数时，因为这个 n 是初始化，因此每调用一次就进行累计。
  - 我们要搞清楚闭包的关键，就是要分析出返回的函数它使用(引用)到哪些变量，因为函数和它引用到的变量共同构成闭包。
3. 案例二
```

package main

import "fmt"

//累加器
func AddUpper() func (int) int {
	var n int = 10
	var str = "hello"
	return func (x int) int {
		n = n + x
		str += "a"
		fmt.Println("str =", str) // 1. str="helloa" 2. str = "helloaa" 3. str = "helloaaa"
		return n
	}
}


func main() {
	//使用前面的代码
	f := AddUpper()
	fmt.Println(f(1)) //11
	fmt.Println(f(2)) //13
	fmt.Println(f(3)) //13

}
```
4. 案例三
```
package main

import (
	"fmt"
	"strings"
)

/*
	请编写一个程序，具体要求如下：
	1. 编写一个函数 makeSuffix(suffix string) 可以接受一个文件后缀名(比如.jpg)，并返回一个闭包。
	2. 调用闭包，可以传入一个文件名，如果该文件名没有指定的后缀(比如.jpg)，则返回文件名 .jpg ，如果已经
    有 .jpg 后缀，则返回原文件名。
	3. 要求使用必报的方式完成。
	4. strings.HasSuffix，该函数可以判断某个字符串是否有指定的后缀。
*/

func makeSuffix(suffix string) func (string) string {
	return func (name string) string {
		//如果 name 没有指定的后缀，则加上，否则就返回原来的名字
		if strings.HasSuffix(name, suffix) == false {
			return name + suffix
		} else {
			return name
		}
	}
}

func main() {
	//测试 makeSuffix 的使用
	//返回一个闭包
	f := makeSuffix(".jpg")
	fmt.Println("文件名处理后 =", f("winter")) // winter.jpg
	fmt.Println("文件名处理后 =", f("bird.jpg"))
}
```
5. 案例说明
  - 返回的匿名函数和 makeSuffix(suffix string) 的 suffix 变量组合成一个闭包，因为返回的函数引用到 suffix 这个变量。
  - 我们体会一下闭包的好处，如果使用传统的方法，也可以轻松实现这个功能。

## 函数中 - defer
### 为什么需要 defer
1. 在函数中，程序员经常需要创建资源(比如：数据库连接、文件句柄、锁等)，为了爱函数执行完毕后，及时的释放资源，Go 的设计者提供 defer(延时机制)。

### 快速入门案例
1. 案例一
```
package main

import "fmt"


func sum(n1 int, n2 int) int {

	//当执行到 defer 时，暂时不执行，会将 defer 后面的语句压入到独立的栈(defer 栈)。
	//当函数执行完毕后，再从 defer 栈，按照先入后出的方式出栈，执行。
	defer fmt.Println("ok1 n1 =", n1)
	defer fmt.Println("ok2 n2 =", n2)

	res := n1 + n2
	fmt.Println("ok3 res =", res)
	return res
}


func main() {
	res := sum(1, 2)
	fmt.Println("res =", res)
}
```

### defer 的细节说明
1. 当 Golang 执行到一个 defer 时，不会立即执行 defer 后的语句，而是将 defer 后的语句压入到一个栈中(暂时将该栈称为 defer 栈)，然后继续执行函数下一个语句。
2. 当函数执行完毕后，在从 defer 栈中，一次从栈顶取出语句执行(注：遵守栈先入后出的机制)。
3. 在 defer 将语句放入到栈时，也会将相关的值拷贝同时入栈。
```
package main

import "fmt"


func sum(n1 int, n2 int) int {

	//当执行到 defer 时，暂时不执行，会将 defer 后面的语句压入到独立的栈。
	//当函数执行完毕后，再从 defer 栈，按照先入后出的方式出栈，执行。
	defer fmt.Println("ok1 n1 =", n1) // 1
	defer fmt.Println("ok2 n2 =", n2) // 2
	//增加一句话
	n1++ //2
	n2++ //3

	res := n1 + n2
	fmt.Println("ok3 res =", res)
	return res
}


func main() {
	res := sum(1, 2)
	fmt.Println("res =", res)
}
```

### defer 的最佳实践
1. defer 最主要的价值是在，当函数执行完毕后，可以及时的释放函数创建的资源。
2. defer 的最佳实践
  - 在 Golang 编程中的通常做法是，创建资源后，比如(打开了文件，获取了数据库的连接，或者是锁资源)，可以利用 defer 延时机制。
  - 在 defer 后，可以继续使用创建资源。
  - 当函数完毕后，系统会依次从 defer 栈中，取出语句，关闭资源。
  - 这种机制，非常简洁，程序元不用再为在什么时机关闭资源而烦心了。

## 函数参数的传递方式
### 基本介绍
1. 我们在讲解函数注意事项和使用细节时，已经讲过值类型和引用类型了，这里再系统总结一下。值类型参数默认就是值传递，而引用类型参数默认就是引用传递。

### 两种传递方式
  - 值传递
  - 引用传递
    - 说明：其实，不管是值传递还是引用传递，传递给函数的都是变量的副本，不同的是，值传递的是值的拷贝，引用传递的是地址的拷贝，一般来说，地址拷贝效率高，因为数据量小，而值拷贝决定拷贝的数据大小，数据越大，效率越低。

### 值类型和引用类型
1. 值类型：基本数据类型 int 系列，float 系列，bool，string、数组和结构体 struct 。
2. 引用类型：指针、slice 切片、map、管道 chan、interface 等都是引用类型。

### 值传递和引用传递使用特点
1. 值类型默认是值传递：变量直接存储值，内存通常在栈中分配。
2. 引用类型默认是引用传递：变量存储的是一个地址，这个地址对应的空间才真正存储数据，内存通常在堆上分配，当没有任何变量引用这个地址时，该地址对应的数据空间就成为一个垃圾，由 GC 来回收。
3. 如果希望函数内的变量能修改函数外的变量，可以传入变量的地址，函数内指针的方式操作变量。

## 变量的作用域
### 基本介绍
1. 函数内部声明/定义的变量叫局部变量，作用域仅限于函数内部。
```
package main

import "fmt"


//函数
func test() {
	//age 和 Name 的作用域就只在 test 函数内部
	age := 10
	Name := "tom~"

	fmt.Println("age =", age)
	fmt.Println("Name =", Name)
}


func main() {
	age := 11
	Name := "tom"
	test()
	fmt.Println("age =", age)
	fmt.Println("Name =", Name)
}
```
2. 函数外部声明/定义的变量叫全局变量，作用域在整个包都有效，如果其首字母为大写，则作用域在整个程序有效。
```
package main

import "fmt"


// 函数外部声明/定义的变量叫全局变量，作用域在整个包都有效，如果其首字母为大写，则作用域在整个程序有效。
var age int = 50
var Name string = "jack~"


//函数
func test() {
	//age 和 Name 的作用域就只在 test 函数内部
	age := 10
	Name := "tom~"

	fmt.Println("age =", age)
	fmt.Println("Name =", Name)
}


func main() {
	test()
	fmt.Println("age =", age)
	fmt.Println("Name =", Name)
}
```
3. 如果变量是在一个代码块，比如 for/if 中，那么这个变量的作用域就在该代码块。
```
package main

import "fmt"


func main() {
	//如果变量是在一个代码块，比如 for/if 中，那么这个变量的作用域就在该代码块。

	for i := 0; i <= 10; i++ {
		fmt.Println("i =", i)
	}

	for i := 0; i <= 10; i++ {
		fmt.Println("i =", i)
	}

	// fmt.Println("i =", i) 用不了

	var j int //局部变量
	for j = 0; j <= 10; j++ {
		fmt.Println("j =", j)
	}
	fmt.Println("j =", j)
}
```
4. 案例入门
```
package main

import "fmt"

/*
	变量的作用域
*/

var name = "Tom" //全局变量

// Name := "mary" //这种写法不能用于全部变量

func test01() {
	fmt.Println(name) // Tom
}

func test02() {
	name := "Jack" //如果去掉冒号相当于给全局变量重新赋值。
	fmt.Println(name) // Jack
}

func main() {
	fmt.Println(name) // Tom
	test01() // Tom
	test02() // Jack
	test01() // Tom
}
```

## 字符串常用的系统函数
### 基本介绍
1. 说明：字符串在我们程序开发中，使用的是非常多。
2. 基本用法
```
(1) 统计字符串的长度，按字节 len(str)
(2) 字符串遍历，同时处理有中文的问题 r := []rune(str)
(3) 字符串转整数：
n, err := strconv.Atoi("12")
(4) 整数转字符串 str = strconv.Itoa(12345)
(5) 字符串转[]byte: var bytes = []byte("hello go")
(6) []byte 转字符串：str=string([]byte{97,98,99})
(7) 10 进制转 2, 8, 16 进制：str = strconv.FormatInt(123, 2) // 2->8,16
(8) 查找子串是否在指定的字符串中：strings.Contains("seafood", "foo") // true
(9) 统计一个字符串有几个指定的子串：strings.Count("ceheese","e") // 4
(10) 不区分大小写的字符串比较( == 是区分字母大小写的)：fmt.Println(strings.EqualFold("abc", "Abc")) // true
(11) 返回子串在字符串第一次出现的 index 值，如果没有返回 -1：strings.Index("NLT_abc", "abc") // 4
(12) 返回子串在字符串最后一次出现的 index ，如果没有返回 -1：strings.LastIndex("go golang", "go")
(13) 将指定的子串替换成另一个子串：strings.Replace("go go Hello", "go", "go语言", n) // n 可以指定你希望替换几个，如果 n=-1 表示全部替换。
(14) 按照指定的某个字符为分割表示，将一个字符串拆分成字符串数组：strings.Split("hello,world,ok", ",")
(15) 将字符串的字母进行大小写的转换：
strings.ToLower("GO") // go
strings.ToUpper("Go") // GO
(16) 将字符串左右两边的空格去掉：strings.TrimSpace(" tn a lone gopher ntyn  ")
(17) 将字符串左右两边指定的字符去掉：strings.Trim("! hello! ", " !")
(18) 将字符串左边指定的字符去掉：strings.TrimLeft("! hello! ", " !")
(19) 将字符串右边指定的字符去掉：strings.TrimRight("! hello! ", " !")
(20) 判断字符串是否以指定的字符串开头：strings.HasPrefix("ftp://192.168.10.1", "ftp") // true
(21) 判断字符串是否以指定的字符串结束：strings.HasSuffix("NLT_abd.jpg", "abc") // false
```
3. 快速入门案例
```
package main

import (
	"fmt"
	"strconv"
	"strings"
)


func main() {
	//统计字符串的长度，按字节 len(str)
	str := "hello"
	fmt.Println("str len =", len(str))

	str1 := "hello北" // golang的编码统一为 utf-8 (ascii 的字符(字母和数字)占一个字节，汉字占用 3 个字节)
	fmt.Println("str len =", len(str1))

	str2 := "hello北京"
	// 字符串遍历，同时处理有中文的问题 r := []rune(str)
	r := []rune(str2)
	for i := 0; i < len(r); i++ {
		fmt.Printf("字符 = %c \n", r[i])
	}

	//字符串转整数：n, err := strconv.Atoi("12")
	n, err := strconv.Atoi("123")
	if err != nil {
		fmt.Println("转换错误：", err)
	} else {
		fmt.Println("转成的结果是：", n)
	}

	//整数转字符串 str = strconv.Itoa(12345)
	str3 := strconv.Itoa(12345)
	fmt.Printf("str3 = %v, str3 = %T\n", str3, str3)

	//字符串转 []byte: var bytes = []byte("hello go")
	var bytes = []byte("hello go")
	fmt.Printf("bytes = %v\n", bytes)

	//[]byte 转字符串：str=string([]byte{97,98,99})
	str4 := string([]byte{97,98,99})
	fmt.Printf("str4 = %v\n", str4)

	//10 进制转2, 8, 16进制：str = strconv.FormatInt(123, 2) // 2->8,16
	str5 := strconv.FormatInt(123, 2)
	fmt.Printf("123对应的二进制是 = %v\n", str5)

	str6 := strconv.FormatInt(123, 16)
	fmt.Printf("123对应的十六进制是 = %v\n", str6)

	//查找子串是否在指定的字符串中：strings.Contains("seafood", "foo") //true
	b := strings.Contains("seafood", "foo")
	fmt.Printf("b = %v\n", b)

	//统计一个字符串有几个指定的子串：strings.Count("ceheese","e") //4
	num := strings.Count("ceheese","e")
	fmt.Printf("num = %v\n", num)

	//不区分大小写的字符串比较(==是区分字母大小写的)：fmt.Println(strings.EqualFold("abc", "Abc")) // true
	b1 := strings.EqualFold("abc", "Abc")
	fmt.Printf("b1 = %v\n", b1) //true

	fmt.Println("结果：","abc" == "Abc") //false

	//返回子串在字符串第一次出现的 index 值，如果没有返回-1：strings.Index("NLT_abc", "abc") //4
	index := strings.Index("NLT_abc", "abc")
	fmt.Printf("index = %v\n", index)

	//返回子串在字符串最后一次出现的 index，如果没有返回 -1：strings.LastIndex("go golang", go)
	index1 := strings.LastIndex("go golang", "go")
	fmt.Printf("index1 = %v\n", index1)

	//将指定的子串替换成另一个子串：strings.Replace("go go Hello", "go", "go语言", n) // n 可以指定你希望替换几个，如果 n=-1 表示全部替换。
	str7 := strings.Replace("go go Hello", "go", "go语言", -1)
	fmt.Printf("str7 = %v\n", str7)

	//按照指定的某个字符为分割表示，将一个字符串拆分成字符串数组：strings.Split("hello,world,ok", ",")
	strArr := strings.Split("hello,world,ok", ",")
	for i := 0; i < len(strArr); i++ {
		fmt.Printf("str[%v] = %v\n", i, strArr[i])
	}
	fmt.Printf("strArr = %v\n", strArr)

	//将字符串的字母进行大小写的转换：
	//strings.ToLower("GO") // go
	//strings.ToUpper("Go") // GO
	str8 := "golang Hello"
	str8 = strings.ToLower(str8)
	fmt.Printf("str8 = %v\n", str8)

	str9 := strings.ToUpper(str8)
	fmt.Printf("str9 = %v\n", str9)

	//将字符串左右两边的空格去掉：strings.TrimSpace(" tn a lone gopher ntyn  ")
	str10 := strings.TrimSpace(" tn a lone gopher ntyn  ")
	fmt.Printf("str10 = %q\n", str10)

	//将字符串左右两边指定的字符去掉：strings.Trim("! hello! ", " !")
	str11 := strings.Trim("! hello! ", " !")
	fmt.Printf("str11 = %q\n", str11)

	//将字符串左边指定的字符去掉：strings.TrimLeft("! hello! ", " !")
	str12 := strings.TrimLeft("! hello! ", " !")
	fmt.Printf("str12 = %q\n", str12)

	//将字符串右边指定的字符去掉：strings.TrimRight("! hello! ", " !")
	str13 := strings.TrimRight("! hello! ", " !")
	fmt.Printf("str13 = %q\n", str13)

	//判断字符串是否以指定的字符串开头：strings.HasPrefix("ftp://192.168.10.1", "ftp") // true
	b2 := strings.HasPrefix("ftp://192.168.10.1", "ftp") // true
	fmt.Printf("b2 = %v\n", b2)

	//判断字符串是否以指定的字符串结束：strings.HasSuffix("NLT_abd.jpg", "abc") // false
	b3 := strings.HasSuffix("NLT_abd.jpg", "abc") // false
	fmt.Printf("b3 = %v\n", b3)
}
```

## 时间和日期相关函数
### 基本介绍
1. 说明：在编程中，程序员会经常使用到日期相关的函数，比如：统计某段代码执行花费的时间等等。
2. 时间和日期相关函数，需要导入 time 包。
3. time.Time 类型，用于表示时间。
4. 获取当前时间
```
now := time.Now()
fmt.Printf("now = %v, now = %T\n", now, now)
```
5. 通过 now 可以获取到年月日时分秒
```
fmt.Printf("年 = %v\n", now.Year())
fmt.Printf("月 = %v\n", int(now.Month()))
fmt.Printf("日 = %v\n", now.Day())
fmt.Printf("时 = %v\n", now.Hour())
fmt.Printf("分 = %v\n", now.Minute())
fmt.Printf("秒 = %v\n", now.Second())
```
6. 格式化日期时间
```
//第一种方式
fmt.Printf("当前年月 %02d-%02d-%02d %02d:%02d:%02d \n", now.Year(),
	int(now.Month()), now.Day(), now.Hour(), now.Minute(), now.Second())

// Sprintf 返回对应的字符串
dataStr := 	fmt.Sprintf("当前年月 %02d-%02d-%02d %02d:%02d:%02d \n", now.Year(),
	int(now.Month()), now.Day(), now.Hour(), now.Minute(), now.Second())

fmt.Printf("dataStr = %v\n", dataStr)

//第二种方式
fmt.Printf(now.Format("2006/01/02 15:04:05"))
fmt.Println()

fmt.Printf(now.Format("2006/01/02"))
fmt.Println()

fmt.Printf(now.Format("15:04:05"))
fmt.Println()
```
7. 时间的常量
```
conset (
    Nanosecond Duration = 1 // 纳秒
    MicroSecond = 1000 * Nanosecond // 微秒
    Millisecond = 1000 * Microsecond // 毫秒
    Second = 1000 * Millisecond // 秒
    Minute = 60 * Second // 分钟
    Hour = 60 * Minute // 小时
)
常量的作用：在程序中可用于获取指定时间单位的时间，比如想得到 100 毫秒
100 * time.Millisecond
```
8. 休眠
```
func Sleep(d Duration)
```
```
//需求：每隔 1 秒中打印一个数字，打印到 10 时退出
i := 0
for {
	i ++
	fmt.Println(i)
	//休眠
	time.Sleep(time.Second)
	if i >= 10 {
		break
	}
}

//需求：每隔 0.1 秒中打印一个数字，打印到10时退出
j := 0
for {
	j ++
	fmt.Println(j)
	//休眠
	time.Sleep(time.Millisecond * 100)
	if j >= 10 {
		break
	}
}
```
9. 获取当前 unix 时间戳和 unixnano 时间戳。(作用是可以获取随机数字)
```
// Unix 和 UnixNano 的使用
fmt.Printf("unix 时间戳 = %v unixnano 时间戳 = %v", now.Unix(), now.UnixNano())
```
10. 快速入门案例
```
package main

import (
	"fmt"
	"strconv"
	"time"
)

/*
	编写一段代码来统计函数 test 03 执行的时间。
*/
func test03() {
	str := ""
	for i := 0; i < 100000; i++ {
		str += "hello" + strconv.Itoa(i)
	}
}

func main() {
	//在执行 test03 前，先获取到当前的 unix 时间戳
	start := time.Now().Unix()
	test03()
	end := time.Now().Unix()
	fmt.Printf("执行 test03() 耗费时间为%v秒\n", end - start)
}
```

## 内置函数
### 基本介绍
1. Golang 设计者为了编程方便，提供了一些函数，这些函数可以直接使用，我们称为 Go 的内置函数。

### 内置函数详解
1. len: 用来求长度，比如 string、array、slice、map、channel 。
2. new: 用来分配内存，主要用来分配值类型，比如 int、float32、struct... 返回的是指针。
```
package main

import "fmt"

/*
	new: 用来分配内存，主要用来分配值类型，比如 int、float32、struct...返回的是指针。(值类型分配内存)
*/


func main() {
	num1 := 100
	fmt.Printf("num1的类型 = %T num1的值 = %v num1的地址 = %v\n", num1, num1, &num1)

	num2 := new(int) // *int
	//num2的类型%T => *int
	//num2的值 0xc000094008
	//num2的地址%v = 地址 0xc000094008
	//num2指向的值 = 0
	*num2 = 100
	fmt.Printf("num2的类型 = %T num2的值 = %v num2的地址 = %v num2这个指针指向的值 = %v\n", num2, num2, &num2, *num2)
}
```
3. make: 用来分配内存，主要用来分配引来类型，比如 chan、map、slice 。

## 错误处理
### 错误处理引入
1. 在默认情况下，当发生错误后(panic)，程序就会退出(程序崩溃)。
2. 如果我们希望，当发生错误后，我们可以捕获到错误，并进行处理，保证程序可以继续执行。还可以在捕获到错误后，给管理员一个提示(邮件或者是短信)。

### 基本说明
1. Go 语言追求简洁优雅，所以，Go 语言不支持传统的 try...catch...finally 这种处理。
2. Go 中引入的处理方式为：defer，panic，recover
3. 这几个异常的使用场景可以这么简单描述：Go 中可以抛出一个 panic 的异常，然后在 defer 中通过 recover 捕获这个异常，然后正常处理。

### 错误处理实践
1. 案例一
```
package main

import (
	"fmt"
	"time"
)

func test() {
	//使用 defer + recover 来捕获和处理异常
	defer func() {
		err := recover() // recover() 内置函数，可以捕获到异常
		if err != nil { //说明捕获到异常/错误
			fmt.Println("err = err", err)
		}
	}()

	num1 := 10
	num2 := 0
	res := num1 / num2
	fmt.Println("res =", res)

}

func main() {
	//测试
	test()
	for {
		fmt.Println("main() 下面的代码...")
		time.Sleep(time.Second)
	}
}
```

### 错误处理的好处
1. 进行错误处理后，程序不会轻易挂掉，如果加入预警代码就可以让程序更加健壮。
```
package main

import (
	"fmt"
	"time"
)

func test() {
	//使用defer + recover 来捕获和处理异常
	defer func() {
		err := recover() // recover() 内置函数，可以捕获到异常
		if err != nil { //说明捕获到异常/错误
			fmt.Println("err = err", err)
			//这里就可以将错误信息发送给管理员
			fmt.Println("发送邮件给 admin@sohu.com")
		}
	}()

	num1 := 10
	num2 := 0
	res := num1 / num2
	fmt.Println("res=", res)

}

func main() {
	//测试
	test()
	for {
		fmt.Println("main() 下面的代码...")
		time.Sleep(time.Second)
	}
}
```

### 自定义错误
1. Go 程序中，也支持自定义错误，使用 errors.New 和 panic 内置函数。
```
(1) errors.New("错误说明")，会返回一个 error 类型的值，表示一个错误。
(2) panic 内置函数，接受一个 interface{} 类型的值(也就是任何值了)作为参数。可以接受 error 类型的变量，输出错误信心，并退出程序。
```
2. 快速入门案例
```
package main

import (
	"errors"
	"fmt"
)

/*
	函数去读取一个配置文件 init.conf 的信息：如果文件名传入不正确，我们就返回一个自定义的错误。
*/


func readConf(name string) (err error) {
	if name == "config.ini" {
		//读取...
		return nil
	} else {
		//返回一个自定义错误
		return errors.New("读取文件错误...")
	}
}


func test02() {
	err := readConf("config2.ini")
	if err != nil {
		//如果读取文件发送错误，就输出这个错误，并终止程序。
		panic(err)
	}

	fmt.Println("test02() 继续执行...")
}


func main() {
	//测试自定义错误的使用
	test02()
	fmt.Println("main() 下面的代码...")
}
```
