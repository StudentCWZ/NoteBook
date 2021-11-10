# Golang 面向对象编程
## 结构体
### 看一个问题
1. 张老太养了两只猫：一只名字叫小白，今年3岁，白色。还有一只叫小花，今年100岁，花色。请编写一个程序，当用户输入小猫的名字时，就显示该猫的名字，年龄，颜色。如果用户输入的小猫名错误，则显示张老太没有这只猫猫。

### 使用现有技术解决
1. 单独的定义变量解决
2. 使用数组解决
```
package main

import "fmt"

func main() {
	/*
		张老太养了两只猫：一只名字叫小白，今年3岁，白色。还有一只叫小花，今年100岁，花色。请编写一个程序，
		当用户输入小猫的名字时，就显示该猫的名字，年龄，颜色。如果用户输入的小猫名错误，则显示张老太没有这只猫猫。
	*/

	////1. 使用变量的处理(变量过多，就会显得冗余，不方便。)
	//var cat1Name string = "小白"
	//var cat1Age int = 3
	//var cat1Color string = "白色"

	//var cat2Name string = "小花"
	//var cat2Age int = 100
	//var cat2Color string = "花色"
	//
	////2. 使用数组解决
	//catNames := [...]string{"小白", "小花"}
	//catAge := [...]int{3, 100}
	//catColors := [...]string{"白色", "花色"}

	//... map[string]string
	/*
		前面三种方式都不好解决这个问题：
		不管是用变量或者数组，他们并不利于数据管理。
	 */
}
```
3. 现有技术解决的缺点分析
```
(1) 使用变量或者数组来解决养猫的来解决养猫的问题，都不利于数据的管理和利用。因为名字，年龄，颜色都是属于一只猫，但是这里是分开保存。
(2) 如果我们希望对一只猫的属性(名字、年龄、颜色)进行操作(绑定方法)，也不好处理。
(3) 引出我们要讲解的技术=>结构体
```

## Golang 语言面向对象编程说明
1. Golang 也支持面向对象编程(OOP)，但是和传统的面向对象编程有区别，并不是纯粹的面向对象语言。所以我们说 Golang 支持面向对象编程特性是比较准确的。
2. Golang 没有类(class)，Go语言的结构体(struct)和其他编程语言的类(class)油桶等的地位，你可以理解 Golang 是基于 struct 来实现 OOP 特性的。
3. Golang 面向对象编程非常简洁，去掉了传统 oop 语言的竭诚、方法重载、构造函数和析构函数、隐藏的 this 指针等等。
4. Golang 仍然有面向对象编程的继承，封装和多态的特性，只是实现的方式和其他 OOP 语言不一样，比如继承：Golang 没有 extends 关键字，继承是通过匿名字段来实现。
5. Golang 面向对象(OOP)很优雅，OOP 本身就是语言类型系统(type system)的一部分，通过接口(interface)关联，耦合性低，也非常灵活。Golang 中面向接口编程是非常重要的特性。

### 结构体与结构体变量(实例/对象)的关系
1. 将一类事物的特性提取出来(比如：猫类)，形成新的数据类型，就是一个结构体。
2. 通过这个结构体，我们可以创建多个变量(实例/对象)。

### 快速入门案例
```
package main

import "fmt"


//使用 struct 来完成案例


//定义一个 Cat 结构体，将 Cat 的各个字段/属性信息，放入到 Cat 结构体进行管理。
type Cat struct {
	Name string
	Age int
	Color string
	Hobby string
}

func main() {

	//创建一个 Cat 变量

	var cat1 Cat // var a int
	cat1.Name = "小白"
	cat1.Age = 3
	cat1.Color = "白色"
	cat1.Hobby = "吃鱼"
	fmt.Println(cat1)


	fmt.Println("猫猫的信息如下：")
	fmt.Println("Name =", cat1.Name)
	fmt.Println("Age =", cat1.Age)
	fmt.Println("Color =", cat1.Color)
	fmt.Println("Hobby =", cat1.Hobby)

}
```

### 结构体和结构体变量(实例)的区别和联系
1. 结构体是自定义的数据类型，代表一类事物。
2. 结构体变量(实例)视具体的，实际的，代表一个具体变量。


### 如何声明结构体
#### 声明结构体
```
type 标识符 struct {
    field1 type
    field2 type
}
```

#### 字段/属性
##### 基本介绍
(1) 从概念或叫法上看：结构体字段 = 属性 = field
(2) 字段是一个结构体的一个组成部分，一般是基本数据类型、数组，也可是引用类型。

##### 注意事项和细节说明
1. 字段声明语法同变量，示例：字段名 字段类型
2. 字段的类型可以为：基本类型、数组或引用类型
3. 在创建一个结构体变量后，如果没有给字段赋值，都对应一个零值(默认值)，规定同前面讲的一样：
```
(1) 布尔类型是 false，整型是 0，字符串是""。
(2) 数组类型的默认值和它的元素类型相关。
(3) 指针，slice，和 map 的零值都是 nil，即还没分配空间。
```
```
package main

import "fmt"


type Person struct {
	Name string
	Age int
	scores [5]float64
	ptr *int //指针
	slice []int //切片
	map1 map[string]string //切片
}




func main() {

	//如果结构体的字段类型是：指针，slice，和 map 的零值都是 nil，即还没分配空间。
	//如果需要使用这样的字段，需要先 make，才能使用。

	//定义结构体变量
	var p1 Person
	fmt.Println(p1)

	if p1.ptr == nil {
		fmt.Println("ok1")
	}

	if p1.slice == nil {
		fmt.Println("ok2")
	}

	if p1.map1 == nil {
		fmt.Println("ok3")
	}

	//使用 slice, 一定要 make
	//p1.slice[0] = 100 报错
	p1.slice = make([]int, 10)
	p1.slice[0] = 100

	fmt.Println(p1)

	//使用 map ，一定要先 make
	//p1.map1["key1"] = "Tom" //报错
	p1.map1 = make(map[string]string)
	p1.map1["key1"] = "tom~"

	fmt.Println(p1)
}
```
4. 不同结构体变量的字段是独立，互不影响，一个结构体变量字段的更改，不影响另外一个。
```
package main

import "fmt"


type Monster struct {
	Name string
	Age int
}


func main() {
	//不同结构体变量的字段是独立，互不影响，一个结构体变量字段的更改，不影响另外一个。
	var monster1 Monster
	monster1.Name = "牛魔王"
	monster1.Age = 500

	monster2 := monster1 //结构体是值类型
	monster2.Name = "青牛精"

	fmt.Println("monster1 =", monster1) //monster1 = {牛魔王 500}
	fmt.Println("monster2 =", monster2) //monster2 = {青牛精 500}
}
```

### 创建结构体变量和访问结构体字段
1. 方式1-直接声明
```
var person Person
```
2. 方式2-{}
```
var person Person = Person{}
```
3. 方式3
```
var person *Person = new(Person)
```
4. 方式4
```
var person *Person = &Person{}
```
5. 说明
```
(1) 第3种和第4种方式返回的是结构体指针。
(2) 结构体指针访问字段的标准方式应该是：(*结构体指针).字段名，比如(*person).Name = "Tom"
(3) 但 go 做了一个简化，也支持结构体指针.字段名，比如 person.Name = "Tom"。更加符合程序员使用习惯，go编译器底层对 person.Name 做了转化(*person).Name
```
6. 案例演示
```
package main

import "fmt"


type Person struct {
	Name string
	Age int
}

func main() {

	//方式2
	p2 := Person{"Mary", 20}
	//p2.Name = "Tom"
	//p2.Age = 18
	fmt.Println(p2)

	//方式3
	var p3 *Person = new(Person)
	//因为 p3 是一个指针，因此标准的给字段赋值方式
	//(*p3).Name = "Smith" 也可以这样写 p3.Name = "Smith"
	//原因：go的设计者为了程序员使用方便，底层会对 p3.Name 进行处理，会给 p3 加上取值运算。
	(*p3).Name = "Smith"
	(*p3).Age = 30
	fmt.Println(*p3)

	p3.Name = "John"
	p3.Age = 40

	fmt.Println(*p3)

	//方式4
	//var person *Person = &Person{}
	//因为 person 是一个指针，因此标准的访问字段的方法
	//(*person.Name) = "Scott"
	//go 的设计者为了程序员使用方便，也可以 person.Name = "Scott"

	var person *Person = &Person{"Mary", 60}
	fmt.Println(*person)


	(*person).Name = "Scott"
	(*person).Age = 88
	fmt.Println(*person)

	person.Name = "Scott~~"
	person.Age = 10
	fmt.Println(*person)


}
```

### struct 类型的内存分配机制
1. 案例一
```
package main

import "fmt"

type Person struct {
	Name string
	Age int
}

//struct 类型的内存分配机制
func main() {
	var p1 Person
	p1.Name = "Mary"
	p1.Age = 10

	var p2 Person = p1

	fmt.Println(p2.Age)

	p2.Name = "Tom"

	fmt.Printf("p2.Name = %v p1.Name = %v \n", p2.Name, p1.Name)
}
```
2. 案例二
```
package main

import "fmt"

type Person struct {
	Name string
	Age int
}

//struct 类型的内存分配机制
func main() {
	var p1 Person
	p1.Name = "Mary"
	p1.Age = 10

	var p2 *Person = &p1 //这里是关键

	fmt.Println((*p2).Age)
	fmt.Println(p2.Age)

	p2.Name = "Tom~"

	fmt.Printf("p2.Name = %v p1.Name = %v \n", p2.Name, p1.Name)
	fmt.Printf("p2.Name = %v p1.Name = %v \n", (*p2).Name, p1.Name)

	fmt.Printf("p1 的地址 %p \n", &p1)
	fmt.Printf("p2 的地址 %p p2 的值 %p \n", &p2, p2)


}
```

### 结构体的注意事项和使用细节
1. 结构体的所有字段在内存中是连续的。
```
package main

import "fmt"


//结构体
type Point struct {
	x int
	y int
}


//结构体
type Rect struct {
	leftUp, rightDown Point
}


//结构体
type Rect2 struct {
	leftUp, rightDown *Point
}



func main()  {

	r1 := Rect{Point{1,2}, Point{3,4}}

	//r1 有四个 int，在内存中是连续分布的
	//打印地址
	fmt.Printf("r1.leftUp.x 的地址 %p r1.leftUp.y 的地址 %p r1.rightDown.x 的地址 %p r1.rightDown.y 的地址 %p \n",
		&r1.leftUp.x, &r1.leftUp.y, &r1.rightDown.x, &r1.rightDown.y)


	//r2 有两个 *Point 类型，这两个 *Point 类型的本身地址也是连续的，但是他们指向的地址不一定是连续的。
	r2 := Rect2{&Point{10,20}, &Point{30,40}}

	fmt.Printf("r2.leftUp 本身的地址 %p r2.rightDown 本身的地址 %p \n",
		&r2.leftUp, &r2.rightDown)

	//他们指向的地址不一定是连续...， 这个要看系统运行时是如何分配的。
	fmt.Printf("r2.leftUp 指向的地址 %p r2.rightDown 指向的地址 %p \n",
		r2.leftUp, r2.rightDown)

	fmt.Printf("r2.leftUp.x 的地址 %p r2.leftUp.y 的地址 %p r2.rightDown.x 的地址 %p r2.rightDown 指向的地址 %p \n",
		&r2.leftUp.x, &r2.leftUp.y, &r2.rightDown.x, &r2.rightDown.y)


}
``` 
2. 结构体是用户单独定义的类型，和其他类型进行转换时需要有完全相同的字段(名字、个数和类型)。
```
package main

import "fmt"

type A struct {
	Num int
}

type B struct {
	Num int
}

func main() {

	//结构体是用户单独定义的类型，和其他类型进行转换时需要有完全相同的字段(名字、个数和类型)。


	var a A
	var b B

	//a = b 报错
	a = A(b) // 结构体是用户单独定义的类型，和其他类型进行转换时需要有完全相同的字段(名字、个数和类型)。
	fmt.Println(a, b)
}
```
3. 结构体进行 type 重新定义(相当于取别名)，Golang 认为是新的数据类型，但是相互间可以强转。
```
package main

import "fmt"

type Student struct {
	Name string
	Age int
}

type Stu Student

func main() {
	//结构体进行 type 重新定义(相当于取别名)，Golang 认为是新的数据类型，但是相互间可以强转。
	var stu1 Student
	var stu2 Stu

	//stu2 = stu1 报错
	stu2 = Stu(stu1)

	fmt.Println(stu1, stu2)
}
```
4. struct 的每个字段上，可以写上一个 tag，该 tag 可以通过反射机制获取，使用场景就是序列好和反序列化。 
```
package main

import (
	"encoding/json"
	"fmt"
)

type Monster struct {
	Name string `json:"name"` //结构体的 tag 
	Age int `json:"age"`
	Skill string `json:"skill"`
}


func main() {
	//1. 创建 monster 变量

	monster := Monster{"牛魔王",500, "芭蕉扇"}

	//2. 将 monster 变量序列化为 json 格式字符串
	jsonStr, err := json.Marshal(monster)
	if err != nil{
		fmt.Println("json 处理错误", err)
	} else {
		fmt.Println("jsonStr =", string(jsonStr))
	}

}
```

## 方法
### 基本介绍
1. 在某些情况下，我们要需要声明(定义方法)。比如 Person 结构体：除了有一些字段外(年龄，姓名..)，Person 结构体还有一些行为比如说话、跑步...等等。这时就要用到方法才能完成。
2. Golang 中的方法式作用在指定的数据类型上的(即：和指定的数据类型绑定)，因此自定义类型，都可以有方法，而不仅仅是 struct。

### 方法的声明和调用
1. 方法的声明和调用
```
type A struct {
    Num int
}
func (a A) test() {
    fmt.Println(a.Num)
}
```
2. 对上面语法的说明
```
(1) func (a A) test() {} 表示 A 结构体有一方法，方法名为 test。
(2) (a A) 体现 test 方法是和 A类型绑定的。
```
3. 举例说明
```
package main

import "fmt"

type Person struct {
	Name string
}


//给 A 类型绑定一个方法
func (p Person) test() {
	fmt.Println("test () name =", p.Name)
}

func main() {
	var p Person
	p.Name = "Tom"
	p.test() //调用方法
}
```
4. 对上面代码的总结和说明
```
(1) test 方法和 Person 类型绑定
(2) test 方法只能通过 Person 类型的变量来调用，而不能直接调用，也不能使用其他类型的变量来调用。
(3) func (p Person) 里面的 p 表示哪个 Person 变量调用 ，这个 p 就是它的副本，这点和函数传参非常相似。
(4) p 这个名字，可以由程序员指定，不是固定。
```

### 方法快速入门
1. 给 Person 结构体添加 speak 方法，输出 xxx 是个好人。
```
package main

import "fmt"

/*
	快速入门：
		给 Person 结构体添加 speak 方法，输出 xxx 是个好人。
*/

type Person struct {
	Name string
}


// 给 Person 结构体添加 speak 方法，输出 xxx 是个好人。
func (p Person) speak() {
	fmt.Printf("%v 是一个好人! \n", p.Name)
}

func main() {
	var p Person
	p.Name = "Tom"
	p.speak()
}
```
2. 给 Person 结构体添加 jisun 方法，可以计算从 1 + .. + 1000的结果。
```
package main

import "fmt"

/*
	快速入门：
		给 Person 结构体添加 jisun 方法，可以计算从 1 + .. + 1000的结果。
*/


type Person struct {
	Name string
}

func (p Person) jisuan() {
	res := 0
	for i := 1; i < 1001; i++ {
		res += i
	}

	fmt.Println(p.Name, "计算的结果 res =", res)
}

func main() {
	var p Person
	
	p.Name = "Tom"
	p.jisuan() //调用方法
}
```
3. 给 Person 结构体 jisuan2 方法，该方法可以接受一个参数 n，计算从 1+..+n的结果。
```
package main

import "fmt"

/*
	快速入门：
		给 Person 结构体 jisuan2 方法，该方法可以接受一个参数 n，计算从 1+..+n的结果。
*/

type Person struct {
	Name string
}

func (p Person) jisuan2(n int) {
	res := 0
	for i := 1; i < n + 1; i++ {
		res += i
	}

	fmt.Println(p.Name, "计算的结果 res =", res)
}

func main() {
	var p Person
	n := 1000

	p.Name = "Tom"

	p.jisuan2(n) //调用方法
}
```
4. 给 Person 结构体添加 getSum 方法，可以计算两个数的和，并返回结果。
```
package main

import "fmt"

/*
	快速入门：
		给 Person 结构体添加 getSum 方法，可以计算两个数的和，并返回结果。
*/

type Person struct {
	Name string
}

func (p Person) getsum(n1 int, n2 int) int{
	return n1 + n2
}

func main() {
	var p Person
	n1 := 55
	n2 := 45
	p.Name = "Tom"

	res := p.getsum(n1, n2)
	fmt.Println(p.Name, "计算的结果 res =", res)

}
```

### 方法的调用和传参机制原理
#### 基本介绍
1. 方法的调用和传参机制核函数基本一样，不一样的地方是方法调用时，会将调用方法的变量，当做实参也传递给方法。
2. 案例一
```
package main

import "fmt"

/*
	快速入门：
		给 Person 结构体添加 getSum 方法，可以计算两个数的和，并返回结果。
*/

type Person struct {
	Name string
}

func (p Person) getsum(n1 int, n2 int) int{
	return n1 + n2
}

func main() {
	var p Person
	n1 := 55
	n2 := 45
	p.Name = "Tom"

	res := p.getsum(n1, n2)
	fmt.Println(p.Name, "计算的结果 res =", res)

}
```
3. 对上面代码进行说明：
```
(1) 在通过一个变量去调用方法时，其调用机制和函数一样。
(2) 不一样的地方，变量调用方法时，该变量本身也会作为参数传递到方法(如果变量是值类型，则进行值拷贝，如果变量是引用类型，则进行地址拷贝)。
```
4. 案例二
```
package main

import "fmt"

/*
	声明一个结构体 Circle ，字段为 radius；
	声明一个方法 area 和 Circle 绑定，可以返回面积。
	提示：画出 area 执行过程 + 说明
*/



type Circle struct {
	radius float64
}

func (c Circle) area() float64 {
	return 3.14 * c.radius * c.radius
}


func main() {
	var c Circle

	c.radius = 4.0
	area := c.area()
	fmt.Println("面积是 area =", area)
}
```

### 方法的声明(定义)
1. 方法的声明(定义)
```
func (recevier type) methodName (参数列表) (返回值列表) {
    方法体
    return 返回值
}
(1) 参数列表：表示方法输入
(2) recevier type: 表示这个方法和 type 这个类型进行绑定，或者说该方法作用于 type 类型
(3) receiver type: type可以是结构体，也可以其他的自定义类型。
(4) receiver: 就是一个 type 类型的一个变量(实例)。
(5) 参数列表：表示方法输入
(6) 返回值列表：表示返回的值，可以多个
(7) 方法主体：表示为了实现某一功能代码块
(8) return 语句不是必须的。
```

### 方法注意事项和细节讨论
1. 结构体类型是值类型，在方法调用中，遵守值类型的传递机制，是值拷贝传递方式。
2. 如程序员希望在方法中，修改结构体变量的值，可以通过结构体指针的方式来处理。
```
package main

import "fmt"

/*
	声明一个结构体 Circle ，字段为 radius；
	声明一个方法 area 和 Circle 绑定，可以返回面积。
	提示：画出 area 执行过程 + 说明
*/



type Circle struct {
	radius float64
}

func (c Circle) area() float64 {
	return 3.14 * c.radius * c.radius
}


//为了提高效率，通常我们方法和结构体的指针类型绑定。

func (c *Circle) area2() float64 {
	// 因为 c 是指针，因此我们标准的访问其字段的方式是 (*c).radius
	// return 3.14 * (*c).radius * (*c).radius
	// (*c).radius 等价 c.radius
	fmt.Printf("c 是 *Circle 指向的地址 = %p \n", c)
	c.radius = 10.0
	return 3.14 * c.radius * c.radius
}

func main() {
	var c1 Circle

	c1.radius = 4.0
	area1 := c1.area()
	fmt.Println("面积是 area1 =", area1)


	var c2 Circle
	fmt.Printf("main c2 结构体变量地址 = %p \n", &c2)
	c2.radius = 5.0
	// area2 := (&c2).area2()
	// 编译器底层做了优化，(&c2).area2() 等价 c2.area2()
	//因为编译器会自动的给我们加上 &
	area2 := c2.area2()
	fmt.Println("面积是 area2 =", area2)

	fmt.Println("main() c2.radius =", c2.radius)

}
```
3. Golang 中的方法作用在指定的数据类型上(即：和指定的数据类型绑定)，因此自定义类型，都可以有方法，而不仅仅是 struct， 比如 int， float 等都可以有方法。
```
package main

import "fmt"

/*
	Golang 中的方法作用在指定的数据类型上(即：和指定的数据类型绑定)，因此自定义类型，都可以有方法，而不仅仅是 struct， 比如 int， float 等都可以有方法。
*/



type integer int


func (i integer) print() {
	fmt.Println("i =", i)
}


//编写一个方法，可以改变 i 的值
func (i *integer) change() {
	*i = *i + 1
}

func main() {
	var i integer = 10

	i.print()
	i.change()
	fmt.Println("i =", i)
}
```
4. 方法的访问范围控制的规则，和函数一样。方法名首字母小写，智能在本包访问，方法首字母大写，可以在本包和其他包访问。
5. 如果一个类型实现了 String() 这个方法，那么 fmt.Println 默认会调用这个变量的 String() 进行输出。 
```
package main

import "fmt"


type Student struct {
	Name string
	Age int
}


//给 *Student 实现方法 String()
func (stu *Student) String() string {
	str := fmt.Sprintf("Name = [%v] Age = [%v]", stu.Name, stu.Age)
	return str
}

func main() {


	//定义一个 Student 变量
	stu := Student{
		Name : "Tom",
		Age : 20,
	}

	fmt.Println(stu)
	//如果你实现了 *Student 类型的 String 方法，就会自动调用。
	fmt.Println(&stu)
}
```

### 方法的课堂练习
1. 编写结构体(MethodUtils)，编程一个方法，方法不需要参数，在方法中打印一个 10 * 8 的矩形，在 main 方法中调用该方法。
```
package main

import "fmt"


/*
	课堂练习：
		编写结构体(MethodUtils)，编程一个方法，方法不需要参数，在方法中打印一个 10 * 8 的矩形，在 main 方法中调用该方法。
*/

type MethodUtils struct {
	//字段...
}

//给 MethodUtils 编写方法
func (mu MethodUtils) print() {
	for i:=1; i <= 10; i++ {
		for j := 1; j <= 8; j++ {
			fmt.Print("*")
		}
		fmt.Println("")
	}
}

func main() {
	var mu MethodUtils

	mu.print()
}
```
2. 编写一个方法，提供 m 和 n 两个参数，方法中打印一个 m*n 的矩形。
```
package main

import "fmt"

/*
	课堂练习：
		编写一个方法，提供 m 和 n 两个参数，方法中打印一个 m*n 的矩形。
*/

type MethodUtils struct {
	//字段...
}


func (mu MethodUtils) print(m int, n int) {
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			fmt.Print("*")
		}
		fmt.Println()
	}
}

func main() {
	var mu MethodUtils

	mu.print(10,8)
}
```
3. 编写一个方法算该矩形的面积(可以接受长len，和宽width)，将其作为方法返回值。在 main 方法中调用该方法，接受返回的面积之并打印。
```
package main

import "fmt"

/*
	课堂练习：
		编写一个方法算该矩形的面积(可以接受长len，和宽width)，将其作为方法返回值。在 main 方法中调用该方法，接受返回的面积之并打印。
*/

type MethodUtils struct {
	//字段...
}


func (mu MethodUtils) area(len float64, width float64) float64 {
	return len * width
}

func main() {
	var mu MethodUtils

	areaRes := mu.area(2.5,8.7)
	fmt.Println("面积 area =", areaRes)
}
```
4. 编写方法：判断一个数是奇数还是偶数。
```
package main

import "fmt"

/*
	课堂练习：
		编写方法：判断一个数是奇数还是偶数。
*/

type MethodUtils struct {
	//字段...
}

func (mu * MethodUtils) JudgeNum(num int) {
	if num % 2 == 0 {
		fmt.Println("是偶数...")
	} else {
		fmt.Println("是奇数...")
	}
}

func main() {
	var mu MethodUtils

	//(&mu).JudgeNum(10)
	mu.JudgeNum(10)
}


```
5. 根据行、列、字符打印对应行数和列数的字符，比如：行：3，列：2，字符 *，则打印相应的结果。
```
package main

import "fmt"

/*
	课堂练习：
		根据行、列、字符打印对应行数和列数的字符，比如：行：3，列：2，字符 *，则打印相应的结果。
*/

type MethodUtils struct {
	//字段...
}

func (mu * MethodUtils) Print(m int, n int, key string) {
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			fmt.Print(key)
		}
		fmt.Println()
	}
}

func main() {
	var mu MethodUtils

	mu.Print(7, 20, "#")
}

```
6. 定义小小计算器的结构体(Calcuator)，实现加减乘除四个功能
```
实现形式1：分四个方法完成。
实现形式2：用一个方法搞定
```
```
package main

import "fmt"

/*
	课堂练习：
		定义小小计算器的结构体(Calculator)，实现加减乘除四个功能：
			实现形式1：分四个方法完成。
			实现形式2：用一个方法搞定
*/

//定义结构体
type Calculator struct {
	Num1 float64
	Num2 float64
}


//方式1
//只写加减，其他类似，不写了
func (calculator *Calculator) GetSum() float64 {
	return  calculator.Num1 + calculator.Num2
}


func (calculator *Calculator) GetSub() float64 {
	return  calculator.Num1 - calculator.Num2
}


//方式2

func (calculator *Calculator) getRes(operator byte) float64 {
	res := 0.0
	switch operator {
	case '+' :
		res = calculator.Num1 + calculator.Num2
	case '-' :
		res = calculator.Num1 - calculator.Num2
	case '*' :
		res = calculator.Num1 * calculator.Num2
	case '/' :
		res = calculator.Num1 / calculator.Num2
	default:
		fmt.Println("运算符输入有误...")
	}
	return res
}

func main() {

	var calculator Calculator

	calculator.Num1 = 1.2
	calculator.Num2 = 2.2

	sum := fmt.Sprintf("%.2f", calculator.GetSum())
	sub := fmt.Sprintf("%.2f", calculator.GetSub())

	fmt.Printf("sum = %v sub = %v \n", sum, sub)

	res := calculator.getRes('+')
	fmt.Printf("res = %v \n", fmt.Sprintf("%.2f", res))


}
```

## 方法和函数区别
1. 调用方法不一样。
```
(1) 函数的代用方式
(2) 方法的调用方式
```
2. 对于普通函数，接收者为值类型时，不能将指针类型的数据直接传递，反之亦然。
3. 对于方法(如 struct 的方法)，接收者为值类型时，可以直接用指针类型的变量调用方法，反过来同样可以。
```
package main

import "fmt"

type Person struct {
	Name string
}


//函数
//对于普通函数，接收者为值类型时，不能将指针类型的数据直接传递，反之亦然。
func test01(p Person) {
	fmt.Println(p.Name)
}


func test02(p *Person) {
	fmt.Println(p.Name)
}


//方法
//对于方法(如 struct 的方法)，接收者为值类型时，可以直接用指针类型的变量调用方法，反过来同样可以。

func (p Person) test03() {
	p.Name = "Jack"
	fmt.Println("test03()", p.Name)
}

func (p *Person) test04() {
	p.Name = "Mary"
	fmt.Println("test03()", p.Name)
}


func main() {
	p := Person{"Tom"}

	test01(p)
	//test01(&p) 报错

	test02(&p)
	//test02(p) 报错

	//都可以，但是 (&p).test03() 还是值拷贝。
	p.test03()
	fmt.Println("main() p.name = ", p.Name)
	(&p).test03() //只是形式上传递了一个地址，本质仍然是值拷贝。
	fmt.Println("main() p.name = ", p.Name)


	(&p).test04()
	fmt.Println("main() p.name = ", p.Name)
	p.test04() // 等价于 (&p).test04()
	fmt.Println("main() p.name = ", p.Name)
}
```
4. 代码总结
```
(1) 不管调用形式如何，真正决定是值拷贝还是地址拷贝，主要是看这个方法是和哪种类型绑定的。
(2) 如果和值类型绑定，则是值拷贝，如果是和指针类型绑定，则是地址拷贝。
```

## 面向对象编程应用实例
### 步骤
1. 声明(定义)结构体，确定结构体名
2. 编写结构体的字段
3. 编写结构体的方法

### 案例
1. 案例一
```
(1) 编写一个 Student 结构体，包含 name、gender、age、id、score字段，分别为 string、string、int、int、float64类型。
(2) 结构体中声明一个 say 方法，返回 string 类型，方法返回信息中包含所有字段值。
(3) 在 main 方法中，创建 Student 结构体实例(变量)，并访问 say 方法，并将调用结果打印输出。
```
```
package main

import (
	"fmt"
)

/*
	案例：
		(1) 编写一个 Student 结构体，包含 name、gender、age、id、score字段，分别为 string、string、int、int、float64类型。
		(2) 结构体中声明一个 say 方法，返回 string 类型，方法返回信息中包含所有字段值。
		(3) 在 main 方法中，创建 Student 结构体实例(变量)，并访问 say 方法，并将调用结果打印输出。
*/

type Student struct {
	Name string `json:"name"`
	Gender string `json:"gender"`
	Age int `json:"age"`
	Id int `json:"id"`
	Score float64 `json:"score"`
}

func (stu *Student) say() string {

	infoStr := fmt.Sprintf("student的信息 name = [%v] gener = [%v] age = [%v] id = [%v] score = [%v]",
		stu.Name, stu.Gender, stu.Age, stu.Id, stu.Score)
	return  infoStr

}

func main() {
	var stu Student

	stu.Name = "Tom"
	stu.Gender = "男"
	stu.Age = 20
	stu.Id = 1001
	stu.Score = 90.0

	infoStr := stu.say()

	fmt.Println(infoStr)

}
```

2. 案例二
```
(1) 编写一个 Dog 结构体，包含 name、age、weight字段
(2) 结构体中声明一个 say 方法，返回 string 类型，方法返回信息中包含所有字段值。
(3) 在 main 方法中，创建 Dog 结构体实例(变量)，并访问 say 方法，将调用结果打印输出。
```
```
package main

import (
	"fmt"
)

/*
	案例：
		(1) 编写一个 Dog 结构体，包含 name、age、weight字段
		(2) 结构体中声明一个 say 方法，返回 string 类型，方法返回信息中包含所有字段值。
		(3) 在 main 方法中，创建 Dog 结构体实例(变量)，并访问 say 方法，将调用结果打印输出。
*/

type Dog struct {
	Name string
	Age int
	Weight float64
}

func (dog *Dog) say() string {

	infoStr := fmt.Sprintf("Dog 的信息 name = [%v] age = [%v] weight = [%v]",
		dog.Name, dog.Age, dog.Weight)
	return  infoStr

}

func main() {
	dog := Dog{
		Name : "奶茶",
		Age : 5,
		Weight: 15.6,
	}


	infoStr := dog.say()

	fmt.Println(infoStr)

}
```
3. 案例三
```
(1) 编程创建一个 Box 结构体，在其中声明三个字段表示一个立方体的长、宽和高，长宽高要从终端获取。
(2) 声明一个方法获取立方体的体积。
(3) 创建一个 Box 结构体变量，打印给定尺寸的立方体的体积。
```
```
package main

import "fmt"

/*
	案例：
		(1) 编程创建一个 Box 结构体，在其中声明三个字段表示一个立方体的长、宽和高，长宽高要从终端获取。
		(2) 声明一个方法获取立方体的体积。
		(3) 创建一个 Box 结构体变量，打印给定尺寸的立方体的体积。
*/


type Box struct {
	Length float64
	Width float64
	Height float64
}

func (box *Box) Volume() float64 {
	return box.Length * box.Width * box.Height
}

func main() {
	var box Box

	fmt.Println("请输入立方体的长度：")
	fmt.Scanln(&box.Length)

	fmt.Println("请输入立方体的宽度：")
	fmt.Scanln(&box.Width)

	fmt.Println("请输入立方体的高度：")
	fmt.Scanln(&box.Height)


	volume := box.Volume()

	fmt.Println("立方体的体积 volume =", volume)
}
```
4. 案例四
```
(1) 一个景区根据游人的年龄收取不同价格的门票，比如年龄大于18，收费20元，其他情况门票免费。
(2) 请编写 Visitor 结构体，根据年龄段决定能够购买的门票价格并输出。
```
```
package main

import "fmt"

/*
	案例：
		(1) 一个景区根据游人的年龄收取不同价格的门票，比如年龄大于18，收费20元，其他情况门票免费。
		(2) 请编写 Visitor 结构体，根据年龄段决定能够购买的门票价格并输出。
*/

type Visitor struct {
	Name string
	Age int
}

func (visitor *Visitor) ShowPrice() {
	if visitor.Age >= 90 || visitor.Age <= 5 {
		fmt.Println("考虑到安全，就不玩耍...")
		return
	}
	if visitor.Age > 18 {
		fmt.Printf("%v的年龄为：%v，门票价格为：20元 \n", visitor.Name, visitor.Age)
	} else {
		fmt.Printf("%v的年龄为：%v，门票价格免费 \n", visitor.Name, visitor.Age)

	}
}

func main() {
	var visitor Visitor

	for {

		fmt.Println("请输入姓名：")
		fmt.Scanln(&visitor.Name)

		if visitor.Name == "n" {
			fmt.Println("退出程序...")
			break
		}

		fmt.Println("请输入年龄：")
		fmt.Scanln(&visitor.Age)

		visitor.ShowPrice()



	}

}
```

## 创建结构体变量时指定字段的值
### 说明
1. Golang 再创建结构体实例(变量)时，可以直接指定字段的值。

### 创建结构体变量时指定字段值方式
1. 方式一
```
var stu1 Student = Student{"tom", 10}
stu2 := Student("tom~", 10)

var stu3 Student = Student{
    Name: "Mary",
    Age: 30,
}

stu4 := Student{
    Name: "Mary~",
    Age: 20,
}
```
```
//举例
package main

import "fmt"

type Stu struct {
	Name string
	Age int
}

func main() {
	//方式1
	//在创建结果体变量时，就直接指定字段的值
	var stu1 = Stu{"小明",19}
	stu2 := Stu{"小明~", 20}

	//在创建结构体变量时，把字段名和字段值写在一起， 这种写法就不依赖这个字段的定义顺序
	var stu3 = Stu{
		Name : "Jack",
		Age : 20,
	}
	stu4 := Stu{
		Age : 30,
		Name : "Mary",
	}

	fmt.Println(stu1, stu2, stu3, stu4)
}
```
2. 方式二
```
var stu5 *Student = &Student{"Smith", 30}
var stu6 *Student = &Student{
    "Name" : "Scott",
    "Age" : "80",
}
```
```
package main

import "fmt"

type Stu struct {
	Name string
	Age int
}

func main() {
	//方式1
	//在创建结果体变量时，就直接指定字段的值
	var stu1 = Stu{"小明",19}
	stu2 := Stu{"小明~", 20}

	//在创建结构体变量时，把字段名和字段值写在一起， 这种写法就不依赖这个字段的定义顺序
	var stu3 = Stu{
		Name : "Jack",
		Age : 20,
	}
	stu4 := Stu{
		Age : 30,
		Name : "Mary",
	}

	fmt.Println(stu1, stu2, stu3, stu4)

	//方式2
	//返回结构体的指针类型(!!!)
	var stu5 = &Stu{"小王", 29}
	stu6 :=  &Stu{"小王~", 39}

	//在创建结构体指针变量时，把字段名和字段值写在一起，这种写法，就不依赖这个字段的定义顺序
	var stu7 = &Stu{
		Name : "小李",
		Age : 49,
	}
	stu8 := &Stu{
		Age : 59,
		Name : "小李~",
	}

	fmt.Println(stu5, stu6, stu7, stu8)
	fmt.Println(*stu5, *stu6, *stu7, *stu8)

}
```

## 工厂模式
### 说明
1. Golang 的结构体没有构造函数，通常可以使用工厂模式来解决这个问题。

### 看一个需求
1. 一个结构体的声明是这样的：
```
package model
type Student struct{
    Name string...
}
```
2. 因为这里的 Student 的首字母 S 是大写的，如果我们想在其他包创建 Student 的实例(比如 main 包)，引入 model 包后，就可以直接创建 Student 结构体的变量(实例)，但是问题来了，如果首字母是小写，比如是 type student struct 就不行了。==> 工厂模式来解决。

### 工厂模式来解决问题
1. 使用工厂模式实现跨包创建结构体实例(变量)的案例：
```
(1) 如果 model 包的结构体变量首字母大写，引入后，直接使用，没有问题。
(2) 如果 model 包的结构体变量字母小写，引入后，不能直接使用，可以工厂模式解决。
```
```
//案例
//model包
//student.go中的内容

package model

//定义一个结构体
type student struct {
	Name string
	Score float64
}

//因为 student 结构体首字母是小写，因此是只能在 model 使用。
//我们通过工厂模式来解决

func NewStudent(n string, s float64) *student {
	return &student {
		Name : n,
		Score : s,
	}
}
```
```
//main 包
//main.go
package main

import (
	"GoProject/src/go_code/chapter10/factory_case_02/model"
	"fmt"
)

func main() {
	//创建一个 Student 实例
	//var stu = model.Student{
	//	Name : "Tom",
	//	Score : 78.9,
	//}

	//当 student 结构体是首字母小写，我们可以通过工厂模式解决。
	var stu = model.NewStudent("Tom~", 88.8)
	fmt.Println(stu)
	fmt.Println(*stu)
	fmt.Println("name =", stu.Name, "score =", stu.Score)

}
```
2. 思考题
```
如果 model 包的 student 的结构体的字段 Score 改成 score，我们还能正常访问吗？又应该如何解决这个问题呢？
```
```
//案例
//model包
//student.go中的内容

package model

//定义一个结构体
type student struct {
	Name string
	score float64
}

//因为 student 结构体首字母是小写，因此是只能在 model 使用。
//我们通过工厂模式来解决

func NewStudent(n string, s float64) *student {
	return &student {
		Name : n,
		score : s,
	}
}

//如果 score 字段首字母小写，则在其他包不可以直接方法，我们可以绑定一个方法
func (s *student) GetScore() float64 {
	return s.score //ok
}
```
```
//main 包
//main.go
package main

import (
	"GoProject/src/go_code/chapter10/factory_case_02/model"
	"fmt"
)

func main() {
	//创建一个 Student 实例
	//var stu = model.Student{
	//	Name : "Tom",
	//	Score : 78.9,
	//}

	//当 student 结构体是首字母小写，我们可以通过工厂模式解决。
	var stu = model.NewStudent("Tom~", 88.8)
	fmt.Println(stu)
	fmt.Println(*stu)
	fmt.Println("name =", stu.Name, "score =", stu.GetScore())

}
```

## 面向对象编程思想-抽象
1. 我们在前面去定义一个结构体时候，实际上就是把一类事物的共有的属性(字段)和行为(方法)提取出来，形成一个物理模型(模板)。这种研究问题的方法称为抽象。
