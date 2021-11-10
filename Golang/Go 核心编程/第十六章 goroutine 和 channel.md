# goroutine 和 channel
## goroutine
### 看一个需求
1. 要求统计1-20000的数字中，哪些是素数？
```
分析思路：
(1) 传统的方法，就是使用一个循环，循环的判断各个数是不是素数。
(2) 使用并发或者并行的方式，将统计素数的任务分配给多个 goroutine 去完成，这时就会使用到 goroutine。
```

### 基本介绍
#### 进程和线程的说明
1. 进程就是程序在操作系统中一次执行过程，是系统进行资源分配和调度的基本单位。
2. 线程是进程的一个执行实例，是程序执行的最小单元，它是比进程更小的能独立运行的基本单位。
3. 一个进程可以创建销毁多个线程，同一个进程的多个线程可以并发执行。
4. 一个程序至少有一个进程，一个进程，至少有一个线程。

#### 并发和并行
1. 多线程程序在单核上运行，就是并发。
```
(1) 多个任务作用在一个 cpu
(2) 从微观的角度看，在一个时间点上，其实只有一个任务在执行。
```
2. 多线程程序在多核上运行，就是并行。
```
(1) 多个任务作用在多个cpu
(2) 从微观的角度看，在一个时间点上，就是多个任务在同时执行。
(3) 这样看来，并行的速度快。
```

#### Go 协程和 Go 主线程
1. Go 主线程(由程序员直接成为线程/也可以理解为进程)：一个 Go 线程上，可多个协程，你可以这样理解，协程是轻量级的线程。
2. Go 协程的特点
```
(1) 有独立的栈空间
(2) 共享程序堆空间
(3) 调度由用户控制
(4) 协程是轻量级的线程
```

### 快速入门
1. 编写一个程序，完成如下功能：
```
(1) 在主线程(可以理解成进程)中，开启一个 goroutine，该协程每隔1秒输出"heello, world!"
(2) 在主线程中也每隔一秒输出"hello,golang"，输出10次后，退出程序
(3) 要求主线程和 goroutine 同时执行。
```
```
package main

import (
	"fmt"
	"strconv"
	"time"
)


/*
	案例：
		(1) 在主线程(可以理解成进程)中，开启一个 goroutine，该协程每隔1秒输出"heello, world!"
		(2) 在主线程中也每隔一秒输出"hello,golang"，输出10次后，退出程序
		(3) 要求主线程和 goroutine 同时执行。
*/



//编写一个函数，每隔1秒输出 "hello，world"
func test() {
	for i :=0; i <= 10; i++ {
		fmt.Println("test() hello, world " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}
}


func main() {

	go test() //开启了一个协程

	for i :=0; i <= 10; i++ {
		fmt.Println("main() hello, golang " + strconv.Itoa(i))
		time.Sleep(time.Second)
	}

}
```
2. 总结
```
(1) 如果主线程退出了，则协程即使还没有执行完毕，也会退出。
(2) 当然协程也可以在主线程没有退出前，就自己结束了，比如完成了自己的任务。
```

3. 快速入门案例小结
```
(1) 主线程是一个物理进程，直接作用在 cpu 上的，是重量级的，非常耗费 cpu 资源。
(2) 协程从主线程开启的，是轻量级的线程，是逻辑态。对资源消耗相对小。
(3) Golang 的协程机制是重要的特点，可以轻松的开启上万个协程。其他编程语言的并发机制是一般基于线程的，开启过多线程，资源耗费大，这里就突显 Golang 在并发上的优势了。
```

### goroutine 的调度模型
#### MPG 模型基本介绍
1. MPG 模型基本介绍
```
(1) M: 操作系统的主线程(物理进程)
(2) P: 协程执行需要的上下文
(3) G: 协程
```
2. MPG 模式运行的状态一
```
(1) 当前程序有三个 M ，如果三个 M 都在一个 CPU 运行，就是并发，如果在不同的 CPU 运行就是并行。
(2) M1, M2, M3 正在执行一个 G，M1 的协程队列有三个，M2 的协程队列有3个，M3 协程队列有2个。
(3) Go 的协程是轻量级的线程，是逻辑态的，Go 可以容易的起上万个协程。
(4) 其他程序 C/Java 的多线程，往往是内核态的，比较重量级，几千个线程可能耗光 cpu
```
3. MPG 模式运行的状态二
```
(1) M0 主线程正在执行 G0 协程，另外有三个协程在队列等待
(2) 如果 G0 协程阻塞，比如读取文件或者数据库等。
(3) 这时就会创建 M1 主线程(也可能是从已有的线程池中取出 M1)，并且将等待的3个协程挂到 M1 下执行，M0 的主线程下的 G0 仍然执行文件 io 的读写。
(4) 这样的 MPG 调度模式，可以既让 G0 执行，同时也不会让队列的其他协程一直堵塞，仍然可以并发/并行执行。
(5) 等到 G0 不再堵塞了，M0 会被放到空闲的主线程继续执行(从已有的线程池中取)，同时 G0 又会被唤醒。
```

### 设置 Golang 运行的 CPU 数目
1. 设置 Golang 运行的 CPU 数目
```
package main

import (
	"fmt"
	"runtime"
)



func main() {

	cpuNum := runtime.NumCPU()
	fmt.Println("cpuNum = ", cpuNum)

	//可以自己设置使用多少个 cpu
	runtime.GOMAXPROCS(cpuNum - 2)
	fmt.Println("ok")
}
```

## channel(管道)
### 看个需求
1. 需求：现在要计算1-200的各个数的阶乘，并且把各个数的阶乘放入 map 中，最后显示出来。要求使用 goroutine 完成。
2. 分析思路：
```
(1) 使用 goroutine 来完成，效率高，但是会出现并发/并行安全问题。
(2) 这里就提出了不同 goroutine 如何通信的问题
```
3. 代码实现
```
(1) 使用 goroutine 来完成
(2) 在运行某个程序时，在编译该程序时候，可以使用 go build -race xxx.go ，就会知道是否存在资源竞争问题。
```
4. 代码描述
```
package main

import (
	"fmt"
	"time"
)

/*
	案例一：
		需求：现在要计算1-200的各个数的阶乘，并且把各个数的阶乘放入 map 中，最后显示出来。
		     要求使用 goroutine 完成。
*/


//思路
//1. 编写一个函数，来计算各个数的阶乘，并放入到 map 中。
//2. 我们启动的协程多个，统一的将阶乘的结果放入到 map 中。
//3. map 应该做成一个全局的。

var (
	myMap = make(map[int]int, 10)
)


// test 函数就是计算 n!，然后将这个结果放入 myMap
func test(n int) {
	res := 1
	for i:=1; i<= n; i++ {
		res *= i
	}

	//这里我们将 res 放入到 myMap 中
	myMap[n] = res // fatal error: concurrent map writes -- 此段代码报错
}


func main() {
	//这里开启多个协程完成任务
	for i :=1; i <= 200; i++ {
		go test(i)
	}

	//休眠 10s
	time.Sleep(time.Second * 10)

	//输出结果，遍历这个结果
	for index, value := range myMap {
		fmt.Printf("map[%d] = %d \n", index, value)
	}

}
```

### 不同 goroutine 之间如何通讯
1. 全局变量的互斥锁
2. 使用管道 channel 来解决


### 使用全局变量加锁同步改进程序
1. 代码改进
```
package main

import (
	"fmt"
	"sync"
	"time"
)

/*
	案例一：
		需求：现在要计算1-200的各个数的阶乘，并且把各个数的阶乘放入 map 中，最后显示出来。
		     要求使用 goroutine 完成。
*/

/*
	思路:
		1. 编写一个函数，来计算各个数的阶乘，并放入到 map 中。
		2. 我们启动的协程多个，统一的将阶乘的结果放入到 map 中。
		3. map 应该做成一个全局的。
*/
var (
	myMap = make(map[int]int, 10)
	//声明一个全局的互斥锁
	//lock 是一个全局的互斥锁
	//sync 是包：syncchornized 同步
	//Mutex : 互斥
	lock sync.Mutex
)


// test 函数就是计算 n!，然后将这个结果放入 myMap
func test(n int) {
	res := 1
	for i:=1; i <= n; i++ {
		res *= i
	}

	//这里我们将 res 放入到 myMap 中
	//加锁
	lock.Lock()
	myMap[n] = res // fatal error: concurrent map writes -- 此段代码报错
	//解锁
	lock.Unlock()
}


func main() {
	//这里开启多个协程完成任务
	for i := 1; i <= 20; i++ {
		go test(i)
	}

	//休眠 10s
	time.Sleep(time.Second * 5)

	//输出结果，遍历这个结果
	lock.Lock()
	for index, value := range myMap {
		fmt.Printf("map[%d] = %d \n", index, value)
	}
	lock.Unlock()

}
```

### 基本介绍
#### 为什么需要 channel
1. 前面使用全局变量加锁同步来解决 goroutine 的通讯，但不完美。
2. 主线程在等待所有 goroutine 全部完成的时间很难确定，这里我们设置10秒，仅仅是估算。
3. 如果主线程休眠时间长了，会加长等待时间，如果等待时间短了，可能还有 goroutine 处于工作状态，这是也会随着主线程的退出而销毁。
4. 通过全局变量加锁同步来实现通讯，也并不利于多个协程对全局变量的读写操作。

#### channel 的介绍
1. channel 本质就是一个数据结构-队列
2. 数据是先进先出
3. 线程安全，多 goroutine 访问时，不需要加锁，就是说 channel 本身就是线程安全的。
4. channel 时有类型，一个 string 的 channel 只能存放 string 类型数据。

### 基本使用
#### 定义/声明变量 channel
1. 举例
```
var intChan chan int (intChan 用于存放 int 数据)
var mapChan chan map[int]string (mapChan 用于存放 map[int]string 类型)
var perChan chan Person
var perChan2 chan *Person
...
```
2. 说明
```
(1) channel 是引用类型
(2) channel 必须初始化才能写入数据，即 make 后才能使用
(3) 管道是有类型的，intChan 只能写入整数 int
```
3. channel 初始化
```
var intChan chan int
intChan = make(chan int, 10)
```
4. channel 写入数据
```
var intChan chan int
intChan = make(chan int, 10)
num := 999
intChan <- 10
intChan <- num
```
5. 快速入门案例
```
package main

import "fmt"



func main() {
	//演示一下管道的使用
	//1. 创建一个可以存放3个 int 类型的管道
	var intChan chan int
	intChan = make(chan int, 3)

	//2. 看看 intChan 是什么
	fmt.Printf("intChan 的值 = %v intChan 本身的地址 = %p \n", intChan, &intChan) // 地址

	//3. 向管道写入数据
	intChan <- 10
	num := 211
	intChan <- num

	//注意点，当给我们给管道写入数据时，不能超过其容量
	intChan <- 50
	//intChan <- 98 // deadlock!

	//4. 看看管道的长度和cap(容量)
	fmt.Printf("intChan len = %v cap = %v \n", len(intChan), cap(intChan))

	//5. 从管道中读取数据
	var num2 int
	num2 = <- intChan
	fmt.Println("num2 =", num2)
	fmt.Printf("intChan len = %v cap = %v \n", len(intChan), cap(intChan))

	//6. 在没有使用协程的情况下，如果我们的管道数据已经全部取出，再取就会报告 deadlock!
	num3 := <-intChan
	num4 := <-intChan
	// num5 := <-intChan 报错

	fmt.Println("num3 =", num3, "num4 =", num4)
}
```

#### channel 使用的注意事项
1. channel 使用的注意事项
```
1. channel 中只能存放指定的数据类型
2. channel 的数据放满后，就不能再放入了。
3. 如果从 channel 取出数据后，可以继续放入。
4. 在没有使用协程的情况下，如果 channel 数据取完了，再取就会报告 dead lock。
```

#### 案例演示
1. 案例一
```
package main

import "fmt"


func main() {
	var intChan chan int
	intChan = make(chan int, 3)
	intChan <- 10
	intChan <- 20
	intChan <- 10
	//因为 intChan 的容量为3，再存会报告 dead lock！
	//intChan <- 50
	num1 := <- intChan
	num2 := <- intChan
	num3 := <- intChan
	//因为 intChan 这是已经没有数据了，再取就会报告 dead lock
	//num4 := <- intChan
	fmt.Printf("num1 = %v num2 = %v, num3 = %v", num1, num2, num3)
}
```
2. 案例二
```
package main

import "fmt"


func main()  {
	var mapChan chan map[string]string
	mapChan = make(chan map[string]string, 10)
	m1 := make(map[string]string, 20)
	m1["city1"] = "北京"
	m1["city2"] = "天津"

	m2 := make(map[string]string, 20)
	m2["hero1"] = "宋江"
	m2["hero2"] = "武松"

	//将数据存放到 mapChan
	mapChan <- m1
	mapChan <- m2

	//取出
	m3 := <- mapChan
	m4 := <- mapChan

	fmt.Printf("mapChan = %v, mapChan = %p \n", mapChan, &mapChan)
	fmt.Println(m3, m4)

}
```
3.案例三
```
package main

import "fmt"

type Cat struct {
	Name string
	Age int
}



func main() {
	var catChan chan Cat
	catChan = make(chan Cat, 10)

	cat1 := Cat{Name: "Tom", Age: 18,}
	cat2 := Cat{Name: "Tom~", Age: 180,}

	catChan <- cat1
	catChan <- cat2

	//取出
	cat11 := <- catChan
	cat22 := <- catChan

	fmt.Println(cat11, cat22)
}
```
4. 案例四
```
package main

import "fmt"

type Cat struct {
	Name string
	Age int
}



func main() {
	var catChan chan *Cat
	catChan = make(chan *Cat, 10)

	cat1 := Cat{Name: "Tom", Age: 18,}
	cat2 := Cat{Name: "Tom~", Age: 180,}

	catChan <- &cat1
	catChan <- &cat2

	//取出
	cat11 := <- catChan
	cat22 := <- catChan


	fmt.Println(cat11, cat22)
	fmt.Println(*cat11, *cat22)
}

```
5. 案例五
```
package main

import "fmt"

type Cat struct {
	Name string
	Age int
}


func main() {
	var allChan chan interface{}
	allChan = make(chan interface{}, 10)

	cat1 := Cat{Name: "Tom", Age: 18,}
	cat2 := Cat{Name: "Tom~", Age: 180,}

	allChan <- cat1
	allChan <- cat2
	allChan <- 10
	allChan <- "jack"

	//取出
	cat11 := <- allChan
	cat22 := <- allChan
	v1 := <- allChan
	v2 := <- allChan
	fmt.Println(cat11, cat22, v1, v2)
}
```
6. 案例六
```
package main

import "fmt"


type Cat struct {
	Name string
	Age int
}

func main() {
	var allChan chan interface{}
	allChan = make(chan interface{}, 10)

	cat1 := Cat{Name: "Tom", Age: 18,}
	cat2 := Cat{Name: "Tom~", Age: 180,}

	allChan <- cat1
	allChan <- cat2
	allChan <- 10
	allChan <- "jack"

	//取出
	cat11 := <- allChan

	fmt.Printf("cat11 = %T cat11 = %v \n", cat11, cat11)
	// fmt.Println(cat11.Name) 报错，需要用到类型断言
	cat33 := cat11.(Cat)
	fmt.Println(cat33.Name)
}
```

### channel 的遍历和关闭
#### channel 的关闭
1. 使用内置函数 close 可以关闭 channel，当 channel 关闭后，就不能再向channel 写入数据了，但是仍然可以从该 channel 读取数据。
2. 案例演示
```
package main

import "fmt"

func main() {

	intChan := make(chan int, 3)
	intChan <- 100
	intChan <- 200

	close(intChan) //close

	//这是不能够再写入数到 channel
	//intChan <- 300
	//fmt.Println("okok~")

	//当管道关闭后，读取数据是可以的
	n1 := <- intChan
	fmt.Println("n1 =", n1)
}
```

#### channel 的遍历
1. channel 支持 for-range 的方式进行遍历，请注意两个细节
```
(1) 在遍历时，如果 channel 没有关闭，则会出现 deadlock 的错误。
(2) 在遍历时，如果 channel 已经关闭，则会正常遍历数据，遍历完后，就会退出遍历。
```
2. 案例演示
```
package main

import "fmt"

func main() {
	//遍历管道
	intChan := make(chan int, 100)
	for i := 0; i < 100; i++ {
		intChan <- i * 2 //放入100个数据到管道
	}

	//遍历管道不能使用普通的 for 循环结构

	//在遍历时，如果 channel 没有关闭，则会出现 deadlock 的错误。
	close(intChan) //关闭管道
	for value := range intChan {
		fmt.Println("value = ", value)
	}

}
```

#### goroutine 和 channel 结合
1. 应用案例
```
(1) 开启一个 writeData 协程，向管道 intChan 中写入50个整数。
(2) 开启一个readData 协程，从管道 intChan 中读取 writeData 写入的数据。
(3) 注意：writeData 和 readData 操作的是同一个管道
(4) 主线程需要等待 writeData 和 readData 协程都完成工作才能退出。
```
```
package main

import (
	"fmt"
)


/*
	案例要求：
		(1) 开启一个 writeData 协程，向管道 intChan 中写入50个整数。
		(2) 开启一个readData 协程，从管道 intChan 中读取 writeData 写入的数据。
		(3) 注意：writeData 和 readData 操作的是同一个管道
		(4) 主线程需要等待 writeData 和 readData 协程都完成工作才能退出。
*/

func writeData(intChan chan int) {
	for i := 1; i <= 50; i++ {
		//放入数据
		intChan <- i
		fmt.Println("writeData value =", i)
		//time.Sleep(time.Second)
	}
	close(intChan) // close()
}

func readData(intChan chan int, exitChan chan bool) {
	for {
		value , ok := <- intChan
		if !ok {
			break
		} else {
			//time.Sleep(time.Second)
			fmt.Println("readData() value =", value)
		}
	}
	//readData 读取完数据后，即任务完成
	exitChan <- true
	close(exitChan)
}

func main() {
	//创建两个管道
	intChan := make(chan int, 50)
	exitChan := make(chan bool, 3)

	go writeData(intChan)
	go readData(intChan, exitChan)

	for {
		_, ok := <- exitChan
		if !ok {
			break
		}
	}

}
```
2. 应用案例
```
func main() {
    intChan := make(chan int, 10) //50->10
    exitChan := make(chan bool, 1)
    go writeData(intChan)
    go readData(intChan, exitChan)
    
    //就是为了等待...readData 协程完成
    for _ := range exitChan {
        fmt.Println("ok...")
    }
}
```
3. 对上面问题分析
```
(1) 对上面 go readData(intChan, exitChan) 注销，会造成 dead lock！
(2) 如果只向管道写入数据，而没有读取，就会出现阻塞而 dead lock，原因是 intChan 容量是10，而代码 writeData 会写入50个数据，因此会阻塞。
(3) 如果编译器(运行)，只有写，而没有毒，则该管道会堵塞；如果写管道和读管道的频率不一致，无所谓。
```
4. 应用实例3
```
需求：要求统计1-80000的数字中，哪些是素数？
分析思路：
(1) 传统的方法，就是使用一个循环，循环的判断各个数是不是素数。
(2) 使用并发/并行的方式，将统计素数的任务分配给多个(4个) goroutine 去完成，完成任务时间短。
```
```
package main

import (
	"fmt"
	"time"
)


// intChan 放入 1-8000个数
func putNum(intChan chan int) {
	for i := 0; i < 8000; i++ {
		intChan <- i
	}

	//关闭 intChan
	close(intChan)
}


// 从 intChan 取出数据，并判断是否为素数，如果是，就放入 primeChan
func primeNum(intChan chan int, primeChan chan int, exitChan chan bool) {

	// 使用 for 循环
	var flag bool
	for {
		time.Sleep(time.Microsecond)
		num, ok := <- intChan
		if !ok { //intChan 取不到
			break
		}
		flag = true //假设是素数
		//判断 num 是不是素数
		for i := 2; i < num; i++ {
			if num % i == 0{
				//说明该 num 不是素数
				flag = false
				break
			}
		}
		if flag {
			//将这个数放入到 primeChan
			primeChan <- num
		}
	}

	fmt.Println("有一个 primeNum 协程取不到数据，退出")
	//这里我们还不能关闭 primeChan
	//向 exitChan 写入 true
	exitChan <- true
}




func main() {

	intChan := make(chan int, 1000)
	primeChan := make(chan int, 2000) //放结果

	//表示退出的管道
	exitChan := make(chan bool, 4) //4个

	//开启一个协程，向 intChan 放入 1-8000个数
	go putNum(intChan)
	//开启四个协程， 从 intChan 取出数据，并判断是否为素数，如果是，就放入 primeChan
	for i := 0; i < 4; i++ {
		go primeNum(intChan, primeChan, exitChan)
	}

	//这里我们主线程，进行处理
	//直接
	go func() {
		for i := 0; i < 4; i++ {
			<-exitChan
		}

		//当我们从 exitchan 取出 4 个结果，就可以放心关闭 primeNum
		close(primeChan)
	}()

	//遍历我们的 primeNum，把结果取出
	for {
		result, ok := <- primeChan
		if !ok {
			break
		} else {
			//将结果输出
			fmt.Printf("素数 = %d \n", result)
		}
	}

	fmt.Println("main() 主线程退出...")

}
```
5. 协程求素数代码效率测试
```
/main/main.go
package main

import (
	"fmt"
	"time"
)


// intChan 放入 1-8000个数
func putNum(intChan chan int) {
	for i := 0; i < 800000; i++ {
		intChan <- i
	}

	//关闭 intChan
	close(intChan)
}


// 从 intChan 取出数据，并判断是否为素数，如果是，就放入 primeChan
func primeNum(intChan chan int, primeChan chan int, exitChan chan bool) {

	// 使用 for 循环
	var flag bool
	for {
		//time.Sleep(time.Microsecond)
		num, ok := <- intChan
		if !ok { //intChan 取不到
			break
		}
		flag = true //假设是素数
		//判断 num 是不是素数
		for i := 2; i < num; i++ {
			if num % i == 0{
				//说明该 num 不是素数
				flag = false
				break
			}
		}
		if flag {
			//将这个数放入到 primeChan
			primeChan <- num
		}
	}

	fmt.Println("有一个 primeNum 协程取不到数据，退出")
	//这里我们还不能关闭 primeChan
	//向 exitChan 写入 true
	exitChan <- true
}


func main() {

	intChan := make(chan int, 1000)
	primeChan := make(chan int, 200000) //放结果

	//表示退出的管道
	exitChan := make(chan bool, 4) //4个

	start := time.Now().Unix()
	//开启一个协程，向 intChan 放入 1-8000个数
	go putNum(intChan)
	//开启四个协程， 从 intChan 取出数据，并判断是否为素数，如果是，就放入 primeChan
	for i := 0; i < 4; i++ {
		go primeNum(intChan, primeChan, exitChan)
	}

	//这里我们主线程，进行处理
	//直接
	go func() {
		for i := 0; i < 4; i++ {
			<-exitChan
		}

		end := time.Now().Unix()
		fmt.Println("使用协程耗费的时间 =", end - start)

		//当我们从 exitChan 取出 4 个结果，就可以放心关闭 primeNum
		close(primeChan)

	}()

	//遍历我们的 primeNum，把结果取出
	for {
		_, ok := <- primeChan
		if !ok {
			break
		} else {
			//将结果输出
			//fmt.Printf("素数 = %d \n", result)
		}
	}

	fmt.Println("main() 主线程退出...")

}
```
6. 传统求素数方法代码效率测试
```
/test/test.go
package main

import (
	"fmt"
	"time"
)


func main() {
	start := time.Now().Unix()
	for num := 1; num <= 800000; num++ {
		flag := true //假设是素数
		//判断 num 是不是素数
		for i := 2; i < num; i++ {
			if num % i == 0{
				//说明该 num 不是素数
				flag = false
				break
			}
		}
		if flag {
			//将这个数放入到 primeChan
			//primeChan <- num
		}
	}
	end := time.Now().Unix()
	fmt.Println("普通的方法耗时 = ", end - start)
}
```
7. 结论
```
(1) 使用 go 协程，执行的速度比普通方法提高了至少4倍。(跟 CPU 有关系)
```

#### channel 使用细节和注意事项
1. channel 可以声明为只读，或者只写性质。
2. channel 只读和只写的最佳实践案例
```
package main

import "fmt"

//ch chan<- int, 这样 ch 就只能写操作
func send(ch chan<- int, exitChan chan struct{}) {
	for i := 0; i < 10; i++ {
		ch <- i
	}
	close(ch)

	var a struct{}
	exitChan <- a
}

//ch <-chan int, 这样 ch 就只能读操作
func recv(ch <-chan int, exitChan chan struct{}) {
	for {
		value, ok := <- ch
		if !ok {
			break
		} else {
			fmt.Println(value)
		}
	}
	var a struct{}
	exitChan <- a
}


func main() {
	//默认情况下，管道是双向的
	var ch chan int
	ch = make(chan int, 10)
	exitChan := make(chan struct{}, 2)
	go send(ch, exitChan)
	go recv(ch, exitChan)

	var total = 0
	for b := range exitChan {
		total++
		if total == 2 {
			break
		} else {
			fmt.Println(b)
		}
	}

	fmt.Println("结束....")
}
```
3. 使用 select 可以解决从管道取数据的阻塞问题。
```
package main

import (
	"fmt"
	"time"
)


func main() {
	//使用 select 可以解决从管道取数据的阻塞问题

	//1. 定义一个管道 10 个数据 int
	intChan := make(chan int, 10)
	for i := 0; i < 10; i++ {
		intChan <- i
	}
	//2. 定义一个管道 5个数据 string
	stringChan := make(chan string, 5)
	for i := 0; i < 5; i++ {
		stringChan <- "hello" + fmt.Sprintf("%d", i)
	}

	// 传统的方法在遍历管道时，如果不关闭会阻塞，导致 dead lock!
	//在实际开发中，可能不好确定什么时候关闭该管道。
	//可以使用 select 方式可以解决
	label :
	for {
		select {
			case value := <- intChan ://注意：这里，如果 intchan 一直没有关闭，不会一直 dead lock！，会自动到下个 case 匹配
				fmt.Printf("从 intChan 读取的数据 %d \n", value)
				time.Sleep(time.Second)
			case value := <- stringChan :
				fmt.Printf("从 stringChan 读取的数据 %s \n", value)
				time.Sleep(time.Second)
			default:
				fmt.Printf("都取不到，不玩了 \n")
				time.Sleep(time.Second)
				break label
		}
	}

}
```
4. goroutine 中使用 recover，解决协程中出现 panic，导致程序崩溃问题。
```
package main

import (
	"fmt"
	"time"
)


//函数
func sayHello() {
	for i := 0; i < 10; i++ {
		time.Sleep(time.Second)
		fmt.Println("hello, world!")
	}
}

//函数
func test() {
	//这里我们可以使用错误处理机制 defer + recover
	//定义了一个 map
	defer func() {
		//捕获 test 抛出的 panic
		if err := recover(); err != nil {
			fmt.Println("test() 发生错误", err)
		}
	}()
	var myMap map[int]string
	myMap[0] = "golang" //error
}

func main() {
	go sayHello()
	go test()

	for i := 0; i < 10; i++ {
		fmt.Println("main() ok =", i)
		time.Sleep(time.Second)
	}
}
```
5. 说明：如果我们起了一个协程，但是这个协程出现 panic， 如果我们没有捕获这个 panic，就会造成整个程序的崩溃，这时我们可以在 goroutine 中使用 recover 来捕获 panic，进行处理，这样即使这个协程发生的问题，但是主线程仍然不受影响，可以继续运行。

