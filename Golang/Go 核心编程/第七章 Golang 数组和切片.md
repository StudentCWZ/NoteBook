# 第七章 Golang 数组和切片
## 为什么需要数组
1. 看一个问题：
```
一个养鸡场有 6 只鸡，它们的体重分别是 3kg，5kg，1kg，3.4kg，2kg，50kg 。请问这六只鸡的总体重是多少？平均体重是多少？请你编一个程序。
```
2. 使用传统方法解决
```
package main

import "fmt"

/*
	一个养鸡场有 6 只鸡，它们的体重分别是 3kg，5kg，1kg，3.4kg，2kg，50kg。
	请问这六只鸡的总体重是多少？平均体重是多少？请你编一个程序。
*/

func main() {

	/*
		思路分析：定义六个变量，分别表示六只鸡的体重，然后求和，最后求出平均值。
	*/

	hen1 := 3.0
	hen2 := 5.0
	hen3 := 1.0
	hen4 := 3.4
	hen5 := 2.0
	hen6 := 50.0

	totalWeight := hen1 + hen2 + hen3 + hen4 + hen5 + hen6
	//avgWeight := totalWeight / 6
	//fmt.Sprintf("%.2f", totalWeight / 6) 将 totalWeight / 6 四舍五入保留到小数点后两位，然后返回值。
	avgWeight := fmt.Sprintf("%.2f", totalWeight / 6)
	fmt.Printf("totalWeight = %v avgWeight = %v", totalWeight, avgWeight)
}
```
3. 代码说明
  - 使用传统的方法不利于数据的管理和维护。
  - 传统的方法不够灵活，因此我们就引出需要学习的新的数据类型 => 数组。

## 数组
### 基本介绍
1. 数组可以存放多个同一类型数据。数组也是一种数据类型，在 Go 中，数组是值类型。

### 数组快速入门
1. 案例一
```
package main

import "fmt"

/*
	一个养鸡场有 6 只鸡，它们的体重分别是 3kg，5kg，1kg，3.4kg，2kg，50kg 。
	请问这六只鸡的总体重是多少？平均体重是多少？请你编一个程序。
*/

func main() {

	//使用数组的方式来解决问题

	//1.定义一个数组
	var hens [6]float64
	//2.给数组的每个元素赋值, 元素的下表是从 0 开始的。
	hens[0] = 3.0 	// hens 数组的第1个元素 hens[0]
	hens[1] = 5.0	// hens 数组的第2个元素 hens[1]
	hens[2] = 1.0
	hens[3] = 3.4
	hens[4] = 2.0
	hens[5] = 50.0

	//3.遍历数组求出总体重
	totalWeight := 0.0
	for i := 0; i < len(hens); i++{
		totalWeight += hens[i]
	}

	//4.求出平均体重
	avgWeight := fmt.Sprintf("%.2f", totalWeight / float64(len(hens)))
	fmt.Printf("totalWeight = %v avgWeight = %v", totalWeight, avgWeight)
}
```
2. 代码说明
  - 使用数组来解决问题，程序的可维护性增加。
  - 方法代码更加清晰，也容易扩展。

### 数组定义和内存布局
#### 数组定义
```
var 数组名 [数组大小] 数据类型
var a[5]int
赋初值 a[0] = 1 a[1] = 30 ...
```

#### 数组内存布局
1. 数组的地址可以通过数组名来获取 &intArr 。
2. 数组的第一个元素的地址就是数组的首地址。
3. 数组的各个元素的地址间隔是根据数组的类型决定。第二个元素的地址就是第一个元素的地址加上数组类型占用的字节大小。(int => 8 个字节)
4. 案例演示
```
package main

import "fmt"

func main() {

	var intArr [3]int
	//当我们定义完数组后，其实数组的各个元素有默认值 0 。
	fmt.Println(intArr) // [0 0 0]

	/*
		1.数组的地址可以通过数组名来获取 &intArr
		2.数组的第一个元素的地址就是数组的首地址。
		3.第二个元素的地址就是第一个元素的地址加上数组类型占用的字节大小。(int => 8 个字节)
	*/
	fmt.Printf("intArr 地址 = %p intArr[0] 地址 = %p intArr[1] 地址 = %p intArr[2] 地址 = %p \n", &intArr, &intArr[0], &intArr[1], &intArr[2])
	intArr[0] = 10
	intArr[1] = 20
	intArr[2] = 30
	fmt.Println(intArr) // [10 20 30]
	fmt.Printf("intArr 地址 = %p intArr[0] 地址 = %p intArr[1] 地址 = %p intArr[2] 地址 = %p", &intArr, &intArr[0], &intArr[1], &intArr[2])
}
```

### 数组使用
#### 访问数组元素
```
数组名[下标] 
比如：你要使用 a 数组的第三个元素 a[2] 
```

#### 快速入门
1. 案例一
```
package main

import "fmt"

/*
	从终端循环输入 5 个成绩，保存到 float64 数组，并输出。
*/

func main() {
	var score [5]float64

	for i := 0; i < len(score); i++ {
		fmt.Printf("请输入第 %d 个元素的值：\n", i + 1)
		fmt.Scanln(&score[i])
	}

	//遍历数组打印

	for i := 0; i < len(score); i++ {
		fmt.Printf("score[%d] = %v\n", i, score[i])
	}
}
```
2. 案例二
```
package main

import "fmt"

/*
	四种初始化数组的方式
*/


func main() {
	var numArr01 [3]int = [3]int{1, 2, 3}
	fmt.Println("numArr01 =", numArr01)

	var numArr02 = [3]int{5, 6, 7}
	fmt.Println("numArr02 =", numArr02)
	//这里的 [...] 是规定的写法
	var numArr03 = [...]int{8, 9, 10}
	fmt.Println("numArr03 =", numArr03)

	var numArr04 = [...]int{1: 800, 0: 900, 2: 999}
	fmt.Println("numArr04 =", numArr04)

	//类型推导
	strArr05 := [...]string{1: "Tom", 0: "Jack", 2: "Mary"}
	fmt.Println("strArr05 =", strArr05)
}
```

### 数组遍历
#### 常规遍历
1. 前面已经讲过了，不再赘述。

#### for-range 结构遍历
1. 这是 Go 语言一种独有的结构，可以用来遍历访问数组的元素。
2. 基本语法
```
for index, value := range array01 {
    ...
}
```
3. 语法说明
  - 第一个返回值 index 是数组的下标
  - 第二个 value 是在该下标位置的值
  - 他们都是在 for 循环内部可见的局部变量。
  - 遍历数组元素的时候，如果不想使用下标 index ，可以直接把下标 index 标为下划线 _ 。
  - index 和 value 的名称不是固定，即程序员可以自行指定，一般命令为 index 和 value 。
4. 快速入门
```
package main

import "fmt"

/*
	for-range 的案例
*/


func main() {
	//演示 for-range 遍历数组
	heroes := [...]string{"宋江", "吴用", "卢俊义"}

	//使用常规方式遍历

	//for-range 遍历数组
	for index, value := range heroes {
		fmt.Printf("index = %v value = %v\n", index, value)
	}

	//只关心值
	for _, value := range heroes {
		fmt.Printf("value = %v\n", value)
	}

}
```

### 数组使用注意事项和细节
1. 数组是多个相同类型数据的组合，一个数组一旦声明/定义了，其长度是固定的，不能动态变化。
```
package main

import "fmt"

func main() {
	//1. 数组是多个相同类型数据的组合，一个数组一旦声明/定义了，其长度是固定的，不能动态变化。

	var arr01 [3]int
	arr01[0] = 1
	arr01[1] = 30
	// arr01[2] = 1.1 //这里会报错
	arr01[2] = 90
	// arr01[3] = 890 //其长度是固定的，不能动态变化，否则报越界。
	fmt.Println(arr01)
}
```
2. var arr[] int 这时 arr 就是一个 slice 切片，切片后面专门讲解。
3. 数组中的元素可以是任何数据类型，包括值类型和引用类型，但是不能混用。
4. 数组创建后，如果没有赋值，有默认值(零值)。
```
(1) 数值类型数组：默认值为 0
(2) 字符串数组：默认值为 ""
(3) bool 数组：默认值为 false
```
```
package main

import "fmt"

func main() {
	/*
		数组创建后，如果没有赋值，有默认值(零值)。
			1. 数值类型数组：默认值为 0
			2. 字符串数组：默认值为 ""
			3. bool 数组：默认值为 false
	*/


	var arr01[3]float32
	var arr02[3]string
	var arr03[3]bool

	fmt.Printf("arr01 = %v arr02 = %v arr03 = %v\n", arr01, arr02, arr03)
}
```
5. 使用数组的步骤
  - 声明数组并开辟空间
  - 给数组各个元素赋值
  - 使用数组
6. 数组的下标是从 0 开始的。
7. 数组下标必须在指定范围内使用，否则报 panic：数组越界，比如 var arr [5]int 则有效下表为 0-4 。
```
package main

import "fmt"

func main() {
	//数组的下表是从 0 开始的
	var arr04 [3]string // 0-2
	//var index int = 3
	//arr04[index] = "Tom" // 因为下标是 0-2 ，因此 arr04[3] 就越界。

	var index int = 2
	arr04[index] = "Tom"

	fmt.Println(arr04)
}
```
8. Go 的数组属值类型，在默认情况下的值传递，因此会进行值拷贝。数组间不会相互影响。
```
package main

import "fmt"

//函数
func test01(arr [3] int) {
	arr[0] = 88
	fmt.Println(arr)
}

func main() {
	//Go 的数组属值类型，在默认情况下的值传递，因此会进行值拷贝。数组间不会相互影响。

	arr := [3]int{11, 22, 33}
	test01(arr)
	fmt.Println(arr)
}
```
9. 如想在其他函数中，去修改原来的数组，可以使用引用传递(指针方式)
```
package main

import "fmt"


func test02(arr *[3]int) {
	fmt.Printf("arr 指针的地址 = %p\n", &arr)
	(*arr)[0] = 88
	fmt.Println((*arr))
}


func main() {

	// 如想在其他函数中，去修改原来的数组，可以使用引用传递(指针方式)

	arr := [3]int{11, 22, 33}
	fmt.Printf("arr 指针的地址 = %p\n", &arr)
	test02(&arr)
	fmt.Println(arr)
}
```
10. 长度是数组类型的一部分，在传递函数参数时，需要考虑数组的长度。

### 数组应用案例
1. 创建一个 byte 类型的 26 个元素的数组，分别放置 'A'-'Z' 。使用 for 循环访问所有元素并打印出来。提示：字符数据运算 'A' + 1 -> 'B' 。
```
package main

import "fmt"

func main() {
	/*
		创建一个 byte 类型的 26 个元素的数组，分别放置 'A'-'Z' 。使用 for 循环访问所有元素并打印出来。提示：字符数据运算 'A' + 1 -> 'B' 。
	*/

	/*
		思路分析：
		1. 声明一个数组 var mychars [26]byte
		2. 使用 for 循环，利用字符可以进行运算的特点来赋值：'A' + 1 => 'B'
		3. 使用 for 循环打印
	*/

	var mychars [26]byte
	for i:=0; i < len(mychars); i++ {
		mychars[i] = 'A' + byte(i) // 注意需要将 i => byte
	}


	for i:=0; i < len(mychars); i++ {
		fmt.Printf("%c\n", mychars[i])
	}
}
```
2. 请求出一个数组的最大值，并得到对应的下标。
```
package main

import "fmt"

func main() {
	// 请求出一个数组的最大值，并得到对应的下标。

	/*
		思路分析
		1. 声明一个数组 var intArr = [...]int{1, -1, 9, 90, 11}
		2. 假定第一个元素就是最大值，下标就是 0
		3. 然后从第二个元素开始循环比较，如果发现更大，则交换
	*/

	var intArr = [...]int{1, -1, 9, 90, 11}
	maxVal := intArr[0]
	maxValIndex := 0

	for i := 1; i <len(intArr); i++ {
		//然后从第二个元素开始循环比较，如果发现更大，则交换
		if maxVal < intArr[i] {
			maxVal = intArr[i]
			maxValIndex = i
		}
	}
	fmt.Printf("maxval = %v maxValIndex = %v \n", maxVal, maxValIndex)
}
```
3. 请求出一个数组的和与平均值。
```
package main

import "fmt"


func main() {
	/*
		请求出一个数组的和和平均值。[for-range]
	*/

	/*
		思路分析：
			1. 先声明一个数组。 var intArr = [...]int{1, -1, 9, 90, 11}
			2. 求出和。 sum
			3. 求出平均值即可。
	*/

	var intArr = [...]int{1, -1, 9, 90, 12}
	sum := 0
	for _, value := range intArr {
		//累计求和
		sum += value
	}
	//如何让平均值保留到小数。
	fmt.Printf("sum = %v 平均值 = %v\n", sum, float64(sum) / float64(len(intArr)))
}
```
4. 随机生成五个数，并反转打印。
```
package main

import (
	"fmt"
	"math/rand"
	"time"
)

func main() {
	/*
		要求：随机生成五个数，并反转打印。
	*/

	/*
		思路分析：
			1. 随机生成五个数， rand.Intn() 函数。
			2. 当我们得到随机数后，就放到数组中。int 数组
			3. 反转打印，交换的次数是 len / 2，倒数第一个和第一个元素交换，倒数第二个和第二个元素交换。
	*/

	var intArr [5]int
	//节省资源，不需重复取调用内键函数
	len := len(intArr)
	//为了每次生成随机数不一样，我们需要给一个 seed 值
	rand.Seed(time.Now().UnixNano())
	for i := 0; i < len; i++ {
		intArr[i] = rand.Intn(100) //随机数大于 0 或小于 100
	}

	fmt.Println("intArr交换前 =", intArr)

	//反转打印，交换的次数是 len / 2，倒数第一个和第一个元素交换，倒数第二个和第二个元素交换。
	temp := 0 // 一个临时变量，用于交换
	for i := 0; i < len / 2; i++ {
		temp = intArr[len - 1 - i]
		intArr[len - 1 - i] = intArr[i]
		intArr[i] = temp
	}
	fmt.Println("intArr 交换后 =", intArr)
}
```

## 为什么需要切片
1. 我们需要一个数组用于保存学生的成绩，但是学生的个数是不确定的，请问怎么办？解决方案：使用切片。

### 基本介绍
1. 切片的英文是 slice 。
2. 切片是数组的一个引用，因此切片是引用类型，在进行传递时，遵循引用传递的机制。
3. 切片的使用和数组类似，遍历切片、访问切片的元素和求切片长度 len(slice) 都一样。
4. 切片的长度是可以变化的，因此切片是一个可以动态变化数组。
5. 切片定义的基本语法：
```
var 变量名 []类型
比如：var a []int
```

### 快速入门
1. 案例一
```
package main

import "fmt"

func main() {
	//演示切片的基本使用
	intArr := [...]int{1, 22, 33, 66, 99}
	//声明/定义一个切片
	/*
		1. slice 就是切片名
		2. intArr[1:3] 表示 slice 引用到 intArr 这个数组的起始下标为 1 ，最后的下标为 3 (但是不包含3)。
	*/
	slice := intArr[1:3]
	fmt.Println("intArr = ", intArr)
	fmt.Println("slice 的元素是 = ", slice)
	fmt.Println("slice 的元素个数是 = ", len(slice))
	fmt.Println("slice 的容量 = ", cap(slice)) // 切片的容量是可以动态变化 
}
```

### 切片在内存中形式
1. 案例一
```
package main

import "fmt"

func main() {
	//切片的内存布局
	/*
		1. slice 的确是一个引用类型
		2. slice 从底层来说，其实就是一个数据结构(struct结构体)
		type slice struct {
			ptr *[2]int
			len int
			cap int
		}
	*/
	intArr := [...]int{1, 22, 33, 66, 99}

	slice := intArr[1:3]

	fmt.Printf("intArr[1] 地址 = %p\n", &intArr[1])
	fmt.Printf("slice[0] 地址 = %p\n", &slice[0])
	fmt.Printf("slice 本身地址 = %p\n", &slice)

	//修改 slice，intArr必然变化(引用类型)
	slice[1] = 34
	fmt.Println("intArr = ", intArr)
	fmt.Println("slice = ", slice)

}
```
2. 代码说明
```
(1) slice 的确是一个引用类型
(2) slice 从底层来说，其实就是一个数据结构(struct结构体)
type slice struct {
	ptr *[2]int
	len int
	cap int
}
```

### 切片使用
1. 第一种方式：定义一个切片，然后让切片去引用一个已经创建好的数组，比如前面的案例就是这样的。
2. 第二种方式：通过 make 来创建切片。
```
基本语法：var 切片名 []type = make([]type, len, [cap])
参数说明：
type：就是数据类型
len：大小
cap：指定切片容量，可选
```
```
package main

import "fmt"


func main() {
	var slice []int = make([]int, 4, 10)
	fmt.Println(slice) //默认值为 0
	fmt.Println("slice len = ", len(slice), " slice cap = ", cap(slice))
	slice[0] = 100
	slice[1] = 200
	fmt.Println(slice)
}
```
3. 代码说明
```
(1) 通过 make 方式创建切片可以指定切片的大小和容量。
(2) 如果没有给切片的各个元素赋值，那么就会使用默认值 [int,float => 0; string => ""; bool => false]
(3) 通过 make 方式创建的切片对应的数组是由 make 底层1维护，对外不可见，即只能通过 slice 去访问各个元素。
```
4. 第三种方式：定义一个切片，直接就指定具体数组，使用原理类似 make 的方式。
```
package main

import "fmt"

func main() {
	//第三种方式：定义一个切片，直接就指定具体数组，使用原理类似 make 的方式。

	var strSlice []string = []string{"Tom", "Jack", "Mary"}
	
	fmt.Println("strSlice = ", strSlice)
	fmt.Println("strSlice size = ", len(strSlice)) // 3
	fmt.Println("strSlice cap = ", cap(strSlice)) // 3
}
```
5. 方式 1 和 方式 2 的区别
```
(1) 方式 1 是直接引用数组，这个数组是事先存在的，程序员是可见的。
(2) 方式 2 是通过 make 来创建切片，make 也会创建一个数组，是由切片在底层进行维护，程序员是看不见的。
```

### 切片遍历
1. 切片的遍历和数组一样，也有两种方式：
```
(1) for 循环常规方式遍历
(2) for range 方式遍历切片
```
```
package main

import "fmt"

func main() {
	//切片的遍历
	//第一种方式：for 循环常规方式遍历

	var arr [5]int = [...]int{10, 20, 30, 40,50}
	slice := arr[1:4] // 20, 30, 40

	for i := 0; i < len(slice); i++ {
		fmt.Printf("slice[%v] = %v\n", i, slice[i])
	}


	//第二种方式：for--range 方式遍历切片
	for index, value := range slice {
		fmt.Printf("slice[%v] = %v\n", index, value)
	}
}
```

### 切片注意事项和细节说明
1. 切片初始化时 var slice = arr[startIndex:endIndex] 。
```
说明：从 arr 数组下标为 startIndex ，取到下标为 endIndex 的元素(不含 arr[endIndex] )。
```
2. 切片初始化时，仍然不能越界。范围在 [0-len(arr)] 之间，但是可以动态增长。
```
(1) var slice = arr[0:end] 可以简写：var slice = arr [:end]
(2) var slice = arr[start:len(arr)] 可以简写 var slice = arr[start:]
(3) var slice = arr[0:len(arr)] 可以简写：var slice = arr[:]
```
3. cap 是一个内置函数，用于统计切片的容量，即最大可以存放多少个元素。
4. 切片定义完后，还不能使用，因为本身是一个空的，需要让其引用到一个数组，或者 make 一个空间供切片来使用。
5. 切片可以继续切片。
```
package main

import "fmt"

func main() {
	//切片的再切片

	var arr [5]int = [...]int{10, 20, 30, 40,50}
	slice := arr[:]
	slice2 := slice[1:2]
	slice2[0] = 100 // 因为 arr，slice 和 slice2 指向的数据空间是同一个，因此 slice[0]=100，其他的都改变。

	fmt.Println("slice2 = ", slice2)
	fmt.Println("slice = ", slice)
	fmt.Println("arr = ", arr)
}
```
6. 使用 append 内置函数，可以对切片进行动态追加。
```
package main

import "fmt"

func main() {

	// 使用 append 内置函数，可以对切片进行动态追加。

	var slice []int = []int{100, 200, 300}
	fmt.Println("slice =", slice)
	//通过 append 直接给 slice 追加具体的元素
	slice = append(slice, 400, 500, 600)
	//通过 append 将切片 slice 追加给 slice
	slice = append(slice, slice...) // 100 200 300 400 500 600 100 200 300 400 500 600

	fmt.Println("slice =", slice) // 100, 200, 300, 400, 500, 600
}
```
7. 切片 append 操作的底层原理分析：
```
(1) 切片 append 操作的本质就是对数组扩容
(2) Go 底层会创建一下新的数组 newArr(安装扩容后大小)。
(3) 将 slice 原来包含的元素拷贝道心的数组 newArr 。
(4) slice 重新引用到 newArr 。
(5) 注意 newArr 是在底层来维护的，程序员不可见。
```
8. 切片使用 copy 内置函数完成拷贝，举例说明：
```
package main

import "fmt"

func main() {
	var a []int = []int{1, 2, 3, 4, 5}
	var slice = make([]int, 10)
	fmt.Println(slice)

	copy(slice, a)
	fmt.Println(slice)
}
```
9. 对上面代码的说明
```
(1) copy(para1, para2): para1 和 para2 参数数据类型都是切片
(2) 按照上面的代码来看，a 和 slice 的数据空间是独立，相互不影响，也就是说 a[0] = 999, slice[1] 仍然是 1 。
```

### string 和 slice
1. string 底层是一个 byte 数组，因此 string 也可以进行切片处理。
```
package main

import "fmt"

func main() {
	//string 底层是一个 byte 数组，因此 string 也可以进行切片处理
	str := "hello@atguigu"
	//使用切片获取到 atguigu
	slice := str[6:]
	fmt.Println("slice =", slice)
}
```
2. string和切片在内存的形式。
3. string 是不可变的，也就说不能通过 str[0] = 'z' 方式修改字符串。
4. 如果需要修改字符串，可以先将 string -> []byte / 或者 []rune -> 修改 -> 重写转成 string 。
```
package main

import "fmt"

func main() {
	//string 底层是一个 byte 数组，因此 string 也可以进行切片处理
	str := "hello@atguigu"
	//使用切片获取到 atguigu
	slice := str[6:]
	fmt.Println("slice =", slice)

	//string 是不可变的，也就说不能通过 str[0] = 'z' 方式修改字符串。
	//str[0] = 'z' // 编译不会通过，string 是不可变

	//如果需要修改字符串，可以先将 string -> []byte /或者[]rune -> 修改 -> 重写转成 string。
	//"hello@atguigu" => "zello@atguigu"
	arr1 := []byte(str)
	arr1[0] = 'z'
	str = string(arr1)

	fmt.Println("str =", str)

	//细节，我们转成 []byte 后，可以处理英文和数字，但是不能处理中文。
	//原因是 []byte 字节来处理，而一个汉字，是 3 个字节，因此就会出现乱码。
	//解决方法：是将 string 转成 []rune 即可，因为 []rune 是按字符处理，兼容汉字。
	arr2 := []rune(str)
	arr2[0] = '北'
	str = string(arr2)

	fmt.Println("str =", str)
}
```
