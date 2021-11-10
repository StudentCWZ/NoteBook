# Golang 面向对象编程思想-抽象
## 面向对象编程思想-抽象
1. 我们在前面去定义一个结构体时候，实际上就是把一类事物的共有的属性(字段)和行为(方法)提取出来，形成一个物理模型(模板)。这种研究问题的方法称为抽象。
2. 快速入门
```
package main

import "fmt"

//定义一个结构体
type Account struct {
	AccountNum string
	Pwd string
	Balance float64
}

//方法
//1.存款

func (account *Account) Deposite(money float64, pwd string) {
	//看看输入的密码是否正确
	if pwd != account.Pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	//看看存款金额是否正确
	if money <= 0 {
		fmt.Println("你输入的金额不正确")
		return
	}

	account.Balance += money

	fmt.Println("存款成功~~")
}

//2. 取款
func (account *Account) WithDraw(money float64, pwd string) {
	//看看输入的密码是否正确
	if pwd != account.Pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	//看看取款金额是否正确
	if money <= 0 || money > account.Balance {
		fmt.Println("你输入的金额不正确")
		return
	}

	account.Balance -= money

	fmt.Println("取款成功~~")
}


//3.查询余额
func (account *Account) Query(pwd string) {
	//看看输入的密码是否正确
	if pwd != account.Pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	fmt.Printf("你的账号 = %v 余额 = %v \n", account.AccountNum, account.Balance)
}

func main() {

	//测试
	account := &Account{
		AccountNum : "工商银行 9955",
		Pwd : "666666",
		Balance : 100 ,
	}
	
	
	//这里可以做得更灵活，就是让用户通过控制台来输入命令
	//菜单......
	account.Query("666666")

	account.Deposite(200.0, "666666")
	account.Query("666666")

	account.WithDraw(150.0, "666666")
	account.Query("666666")

}
```

## 面向对象编程三大特性
### 基本介绍
1. Golang 仍然有面向对象编程的继承、封装和多态的特性，只是实现的方式和其他OOP语言不一样。

### 封装
1. 封装(encapsulation)就是把抽象出的字段核对字段的操作封装在一起，数据被保存在内部，程序的其他包只有通过被授权的操作(方法)，才能对字段进行操作。

#### 封装的理解和好处
1. 隐藏实现细节
2. 可以对数据进行验证，保证安全合理。

#### 如何体现封装
1. 对结构体中的属性进行封装
2. 通过方法，包实现封装

#### 封装的实现步骤
1. 将结构体、字段(属性)的首字母小写(不能导出，其他包不能使用，类似 private )。
2. 给结构体所在包提供一个工厂模式的函数，首字母大写。类似一个构造函数。
3. 提供一个首字母大写的 Set 方法(类似其他语言的  public )，用于对属性判断并赋值。
```
func (var 结构体类型名) SetXxx(参数列表) (返回值列表) {
    //加入数据验证的业务逻辑
    var.字段 = 参数
}
```
4. 提供一个首字母大写的 Get 方法(类似其他语言的 public )，用于获取属性的值
```
func (var 结构体类型名) GetXxx() {
    return var.字段
}
```
5. 特别说明：在 Golang 开发中并没有特别强调封装，这点并不想Java。所以提醒学过 Java 的朋友，不用总是用 java 的语法特性来看待 Golang， Golang 本身对面向对象的特性做了简化的。

#### 快速入门案例
1. 编写一个程序 person.go，不能随便查看人的年龄、工资等隐私
，并对输入的年龄进行合理验证。
```
model 包(person.go) main 包(main.go)
```
2. main.go 调用 Person 结构体
```
//model 包
//person.go
package model

import "fmt"

type person struct {
	Name string
	age int  //其他包无法直接访问..
	sal float64
}


//写一个工厂模式的函数，相当于构造函数

func NewPerson(name string) *person {
	return &person{
		Name : name,
	}
}

//为了访问 age 和 sal 我们编写一对 SetXxx 的方法和 GetXxx的方法

func (p *person) SetAge(age int) {
	if age > 0 && age < 150 {
		p.age = age
	} else {
		fmt.Println("年龄范围不正确...")
		//有程序员给个默认值
	}
}

func (p *person) GetAge() int {
	return p.age
}


func (p *person) SetSal(sal float64) {
	if sal > 3000 && sal < 30000 {
		p.sal = sal
	} else {
		fmt.Println("薪水范围不正确...")
		//有程序员给个默认值
	}
}

func (p *person) GetSal() float64 {
	return p.sal
}
```
```
//main 包
//main.go
package main

import (
	"GoProject/src/go_code/chapter11/encapsulate_case_01/model"
	"fmt"
)




func main() {
	p := model.NewPerson("Smith")

	fmt.Println(p.Name)
	p.SetAge(18)
	p.SetSal(5000)
	fmt.Println(p)
	fmt.Println("Name =", p.Name, "age =", p.GetAge(), "sal =", p.GetSal())

}
```

#### 课堂练习
1. 课堂练习
```
(1) 创建程序，在 model 包中定义 Account 结构体：在 main 函数中体会 Golang 的封装性。
(2) Account 结构体要求具有字段：账号(长度在6-10之间)、余额(必须>20)、密码(必须是6位)。
(3) 通过 SetXxx 的方法给 Account 的字段赋值。
(4) 在 main 函数中测试。
```
```
package model

import "fmt"

//定义一个结构体
type account struct {
	accountNum string
	pwd string
	balance float64
}

//工厂模式的函数-构造函数
func NewAccount(accountNum string, pwd string, balance float64)  *account {
	if len(accountNum) < 6 || len(accountNum) > 10 {
		fmt.Println("账号的长度不对....")
		return nil
	}

	if len(pwd) != 6 {
		fmt.Println("密码的长度不对...")
		return nil
	}

	if balance < 20 {
		fmt.Println("余额的数目不对...")
	}

	return &account{
		accountNum : accountNum,
		pwd : pwd,
		balance : balance,
	}
}



//通过 SetXxx 的方法给 Account 的字段赋值。(这里实现)

func (account *account) SetAccountNum(accountNum string) {
	if len(accountNum) < 6 || len(accountNum) > 10 {
		fmt.Println("账号的长度不对....")
	}
	account.accountNum = accountNum
}

func (account *account) GetAccountNum(accountNum string)  string {
	return account.accountNum
}



func (account *account) Setpwd(pwd string) {
	if len(pwd) != 6 {
		fmt.Println("密码的长度不对...")
	}
	account.pwd = pwd
}


func (account *account) Getpwd(pwd string) string {
	return account.pwd
}


func (account *account) Setbalance(balance float64) {
	if balance < 20 {
		fmt.Println("余额的数目不对...")
	}
	account.balance = balance
}


//方法
//1.存款

func (account *account) Deposite(money float64, pwd string) {
	//看看输入的密码是否正确
	if pwd != account.pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	//看看存款金额是否正确
	if money <= 0 {
		fmt.Println("你输入的金额不正确")
		return
	}

	account.balance += money

	fmt.Println("存款成功~~")
}

//2. 取款
func (account *account) WithDraw(money float64, pwd string) {
	//看看输入的密码是否正确
	if pwd != account.pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	//看看取款金额是否正确
	if money <= 0 || money > account.balance {
		fmt.Println("你输入的金额不正确")
		return
	}

	account.balance -= money

	fmt.Println("取款成功~~")
}


//3.查询余额
func (account *account) Query(pwd string) {
	//看看输入的密码是否正确
	if pwd != account.pwd {
		fmt.Println("你输入的密码不正确")
		return
	}

	fmt.Printf("你的账号 = %v 余额 = %v \n", account.accountNum, account.balance)
}
```
```
package main

import (
	"GoProject/src/go_code/chapter11/encapsulate_case_03/model"
	"fmt"
)


/*
	课堂练习：
		1. 创建程序，在 model 包中定义 Account 结构体：在 main 函数中体会 Golang 的封装性。
		2. Account 结构体要求具有字段：账号(长度在6-10之间)、余额(必须>20)、密码(必须是6位)。
		3. 通过 SetXxx 的方法给 Account 的字段赋值。(这里实现)
		4. 在 main 函数中测试。
*/

func main() {

	//创建一个 account 变量
	account := model.NewAccount("gongshang","999999", 400.0)
	if account != nil {
		fmt.Println("account =", *account)
	} else {
		fmt.Println("account 实例化失败")
	}

	account.SetAccountNum("jianhang")
	account.Setpwd("666666")
	account.Setbalance(1000.0)

	fmt.Println("account =", *account)


	account.Query("666666")

	account.Deposite(200.0, "666666")
	account.Query("666666")

	account.WithDraw(150.0, "666666")
	account.Query("666666")

}
```

###  面向对象编程三大特性-继承
#### 为什么需要继承
1. 看个学生考试系统的程序，提出代码复用的问题。
2. 快速案例
```
package main

import "fmt"

//编写一个学生考试系统

//小学生要考试
type Pupil struct {
	Name string
	Age int
	Score int
}


//显示他的成绩
func (p *Pupil) ShowInfo() {
	fmt.Printf("学生名字 = %v 年龄 = %v 成绩 = %v", p.Name, p.Age, p.Score)
}



func (p *Pupil) SetScore(score int) {
	//业务判断
	p.Score = score
}

func (p *Pupil) Testing() {
	fmt.Println("小学生正在考试中.....")
}


//假设中学生、大学生要考试，方法类似，如果重写方法，代码冗余。==> 继承

func main() {

	//测试
	pupil := &Pupil{
		Name : "Tom",
		Age : 10,
	}

	pupil.Testing()
	pupil.SetScore(90)
	pupil.ShowInfo()
}
```

#### 继承的基本介绍
1.继承可以解决代码复用，让我们的编程更加靠近人类思维。
2. 当多个结构体存在相同的属性(字段)和方法时，可以从这些结构体中抽象出结构体(比如刚才的Student)，在结构体中定义这些相同的属性和方法。
3. 其他结构体不需要重新定义这些属性和方法，只需要嵌套一个 Student 匿名结构体即可。
4. 在 Golang 中，如果一个 struct 嵌套了另一个匿名结构体，那么这个结构体可以直接访问匿名结构体的字段和方法，从而实现了继承特性。

#### 嵌套匿名结构体的基本语法
1. 基本语法
```
type Goods struct {
    Name string
    Price int
}

type Book struct {
    Goods //这里就是嵌套匿名结构体 Goods
    Writer string
}
```

#### 快速入门案例
1. 我们对上面的例子进行改进，使用嵌套匿名结构体来实现继承特性。
```
package main

import "fmt"


type Student struct {
	Name string
	Age int
	Score int
}

type Pupil struct {
	Student //嵌入了 Student 匿名结构体
}

type Graduate struct {
	Student //嵌入了 Student 匿名结构体
}


// 将 Pupil 和 Graduate 共有的方法也绑定到 * student
func (stu *Student) ShowInfo() {
	fmt.Printf("学生名字 = %v 年龄 = %v 成绩 = %v \n", stu.Name, stu.Age, stu.Score)
}

func (stu *Student) SetScore(score int) {
	stu.Score = score
}

//这是 Pupil 结构体特有的方法，保留
func (p *Pupil) Testing() {
	fmt.Println("小学生正在考试中.....")
}


//这是 Graduate 结构体特有的方法，保留
func (p *Graduate) Testing() {
	fmt.Println("大学生正在考试中.....")
}

func main() {
	//当我们对结构体嵌入了匿名结构体使用方法会发生变化
	pupil := &Pupil{}
	pupil.Student.Name = "Tom"
	pupil.Student.Age = 8
	pupil.Testing()
	pupil.Student.SetScore(70)
	pupil.Student.ShowInfo()


	graduate := &Graduate{}
	graduate.Student.Name = "Mary"
	graduate.Student.Age = 28
	graduate.Testing()
	graduate.Student.SetScore(90)
	graduate.Student.ShowInfo()
}
```
2. 继承给编程带来的便利
```
(1) 代码的复用性提高了
(2) 代码的扩展性和维护性提高了
```

#### 继承的深入讨论
1. 结构体可以使用嵌套匿名结构体所有的字段和方法，即：首字母大写或者小写字段、方法，都可以使用。
```
package main

import "fmt"


type A struct {
	Name string
	age int
}

func (a *A) SayOk() {
	fmt.Println("A SayOk", a.Name)
}


func (a *A) hello() {
	fmt.Println("A hello", a.Name)
}

type B struct {
	A
}


func main() {

	var b B
	b.A.Name = "Tom"
	b.A.age = 19
	b.A.SayOk()
	b.A.hello()

}
```
2. 匿名结构体字段访问可以简化。
```
package main

import "fmt"


type A struct {
	Name string
	age int
}

func (a *A) SayOk() {
	fmt.Println("A SayOk", a.Name)
}


func (a *A) hello() {
	fmt.Println("A hello", a.Name)
}

type B struct {
	A
}


func main() {

	var b B
	b.A.Name = "Tom"
	b.A.age = 19
	b.A.SayOk()
	b.A.hello()

	//上面的写法可以简化
	b.Name = "Smith"
	b.age = 20
	b.SayOk()
	b.hello()


}

/*
    对上面的代码小结：
        (1) 当我们直接通过 b 访问字段或方法时，其执行流程如下比如 b.Name
        (2) 编译器会先看 b 对应的类型有没有 Name，如果有，则直接调用 B 类型的 Name 字段
        (3) 如果没有就去看 B 中嵌入的匿名结构体 A有没有声明 Name 字段，如果有就调用，如果没有，继续查找，如果都找不到，就报错。
*/
```
3. 当结构体和匿名结构体有相同的字段或者方法时，编译器采用就近原则访问，如希望访问匿名结构体的字段和方法，可以通过匿名结构体来区分。
```
package main

import "fmt"


type A struct {
	Name string
	age int
}

func (a *A) SayOk() {
	fmt.Println("A SayOk", a.Name)
}


func (a *A) hello() {
	fmt.Println("A hello", a.Name)
}

type B struct {
	A
	Name string
}

func (b *B) SayOk() {
	fmt.Println("B SayOk", b.Name)
}

func main() {

	var b B
	b.Name = "Jack" //ok
	b.age = 100 //ok
	b.A.Name = "Scott"
	b.SayOk() //B SayOk Jack
	b.A.SayOk() //A SayOk Scott
	b.hello() //A hello Scott

}
```
4. 结构体嵌入两个(或多个)匿名结构体，如两个匿名结构体有相同的字段和方法(同时结构体本身没有同名的字段和方法)，在访问时，就必须明确指定匿名结构体名字，否则编译报错。
```
package main

import "fmt"


type A struct {
	Name string
	age int
}


type B struct {
	Name string
	Score float64
}

type C struct {
	A
	B
}

func main() {
	var c C
	//如果c 没有 Name 字段，而 A 和 B 有 Name， 这时必须通过指定匿名结构体名字来区分。
	// c.Name = "Tom" //error
	c.A.Name = "Tom"

	fmt.Println(c)

}
```
5. 如果一个 Struct 嵌套了一个有名结构体，这种模式就是组合，如果是组合关系，那么在访问组合的结构体的字段或方法时，必须带上结构体的名字。
```
package main

import "fmt"


type A struct {
	Name string
	age int
}


type B struct {
	Name string
	Score float64
}

type C struct {
	A
	B
}

type D struct {
	a A //有名结构体 ==> 组合关系
}



func main() {
	var c C
	//如果c 没有 Name 字段，而 A 和 B 有 Name， 这时必须通过指定匿名结构体名字来区分。
	// c.Name = "Tom" //error
	c.A.Name = "Tom"

	fmt.Println(c)


	//如果 D 中是一个有名结构体，则访问有名结构体的字段时，就必须带上有名结构体的名字。
	var d D
	//d.Name = "Jack" error

	d.a.Name = "Jack"
	fmt.Println(d)

}
```
6. 创建匿名结构体后，也可以在创建结构体变量(实例)时，直接指定各个匿名结构体字段的值。
```
package main

import "fmt"

type Goods struct {
	Name string
	Price float64
}

type Brand struct {
	Name string
	Address string
}

type Tv struct {
	Goods
	Brand
}

type Tv2 struct {
	*Goods
	*Brand
}


func main() {

	tv := Tv{Goods{"电视机001", 5000.99}, Brand{"海尔", "山东"},}
	tv2 := Tv{
		Goods{
			Price : 5000.99,
			Name : "电视机002",
		},
		Brand{
			Name : "夏普",
			Address :"北京",
		},
	}

	tv3 := Tv2{&Goods{"电视机003", 7000.99}, &Brand{"创维", "河南"},}

	tv4 := Tv2{
		&Goods{
			Price : 9000.99,
			Name : "电视机004",
		},
		&Brand{
			Name : "长虹",
			Address :"四川",
		},
	}

	fmt.Println(tv)
	fmt.Println(tv2)
	fmt.Println(tv)
	fmt.Println(tv3)
	fmt.Println("tv3", *tv3.Goods, *tv3.Brand)
	fmt.Println("tv4", *tv4.Goods, *tv4.Brand)
}
```
7. 课堂练习
```
package main

import "fmt"


type Monster struct {
	Name string
	Age int
}

type E struct {
	Monster
	int
	n int
}



func main() {
	//演示一下匿名字段是基本数据类型的使用
	var e E

	e.Name = "狐狸精"
	e.Age = 300

	e.int = 20
	e.n = 40

	fmt.Println("e =", e)
}
```
8. 上面代码说明
```
(1) 如果一个结构体有 int 匿名字段，就不能有第二个。
(2) 如果需要有多个 int 字段，则必须给 int 字段指定名字。
```

#### 多重继承说明
1. 如果一个 struct 嵌套了多个匿名结构体，那么该结构体可以直接访问嵌套的匿名结构体的字段和方法，从而实现多重继承。

##### 多重继承细节说明
1. 如果嵌入的匿名结构体有相同的字段名或者方法名，则在访问时，需要通过匿名结构体类型名来区分。
2. 为了保证代码的简洁性，建议大家尽量不使用多重继承。

## 接口
### 基本介绍
1. 按顺序，我们应该讲解多态，但是在讲解多态前，我们需要讲解接口(interface)，因为在 Golang 中多态特性主要是通过接口来体现的。

### 接口快速入门
1. 这样的设计需求在 Golang 编程中也是会大量存在的，一个程序就是一个世界，在现实世界存在的情况，在程序中也会出现。
2. 代码实现
```
package main

import "fmt"

//声明/定义一个接口
type Usb interface {
	//声明了两个没有实现的方法
	Start()
	Stop()
}

type Phone struct {

}

type Camera struct {

}


//让 Phone 实现 Usb 接口的方法

func (p Phone) Start() {
	fmt.Println("手机开始工作...")
}

func (p Phone) Stop() {
	fmt.Println("手机停止工作...")
}

// 让 Camera 实现 Usb 接口的方法
func (c Camera) Start() {
	fmt.Println("相机开始工作...")
}

func ( Camera) Stop() {
	fmt.Println("相机停止工作...")
}


//计算机
type Computer struct {

}

//编写一个方法 working， 接收一个 Usb 接口类型变量
//只要是实现了 Usb 接口(所谓实现 Usb 接口，就是指实现了 Usb 接口声明的所有方法)
func (c Computer) Working(usb Usb) {
		//通过 usb 接口变量来调用 start 和 stop 方法
		usb.Start()
		usb.Stop()
}

func main() {

	//测试
	//先创建结构体变量
	computer := Computer{}
	phone := Phone{}
	camera := Camera{}

	//关键点
	computer.Working(phone)
	computer.Working(camera)

}
```

### 接口基本说明
1. interface 类型可以定义一组方法，但是这些不需要实现。并且 interface 不能包含任何变量。到某个自定义类型(比如结构体Phone)要使用的时候，再根据具体情况把这些方法写出来(实现)。

### 基本语法
1. 基本语法
```
type 接口名 interface{
    method1(参数列表) 返回值列表
    method2(参数列表) 返回值列表
}
```
2. 小结说明
```
(1) 接口里的所有方法都没有方法体，即接口的方法都是没有实现的方法。接口体现了程序设计的多态和高内聚低耦合的思想。
(2) Golang 中的接口，不需要显示的实现。只要一个变量，含有接口类型中的所有方法，那么这个变量就实现这个接口。因此，Golang 中没有 implement 这样的关键字。
```

### 注意事项和细节
1. 接口本身不能创建实例，但是可以指向一个实现了该接口的自定义类型的变量(实例)。
```
package main

import "fmt"

type AInterface interface {
	Say()
}

type Stu struct {
	Name string
}

func (stu Stu) Say() {
	fmt.Println("Stu Say()")
}

func main() {
	var stu Stu //结构体变量， 实现了 Say() 实现了 AInterface
	var a AInterface = stu
	a.Say()
}
```
2. 接口中所有的方法都没有方法体，即都是没有实现的方法。
3. 在 Golang 中，一个自定义类型需要将某个接口的所有方法都实现，我们说这个自定义类型实现了该接口。
4. 一个自定义类型只有实现了某个接口，才能将该自定义类型的实例(变量)赋给接口类型。
5. 只要是自定义数据类型，就可以实现接口，不仅仅是结构体类型。
```
package main

import "fmt"

type AInterface interface {
	Say()
}

type integer int

func (i integer) Say() {
	fmt.Println("Integer Say() i =", i)
}

func main() {
	var i integer = 10
	var b AInterface = i
	b.Say()
}
```
6. 一个自定义类型可以实现多个接口。
```
package main

import "fmt"


type AInterface interface {
	Say()
}

type BInterface interface {
	Hello()
}

type Monster struct {

}

func (m Monster) Hello() {
	fmt.Println("Monster Hello()~~")
}

func (m Monster) Say() {
	fmt.Println("Monster Say()~~")
}


func main() {

	//Monster 实现了 AInterface 和 BInterface
	var monster Monster
	var a AInterface = monster
	var b BInterface = monster
	a.Say()
	b.Hello()
}
```
7. Golang 接口中不能有任何变量。
8. 一个接口(比如A接口)可以继承多个别的接口(比如B，C接口)，这时如果要实现 A 接口，也必须将 B，C 接口的方法也全部实现。
```
package main

import (
	"fmt"
)

type BInterface interface {
	test01()
}


type CInterface interface {
	test02()
}

type AInterface interface {
	BInterface
	CInterface
	test03()
}

//如果我们需要实现 AInterface ，就需要将 BInterface、CInterface 的方法都实现

type Stu struct {

}

func (stu Stu) test01() {
	fmt.Println("test01()...")
}

func (stu Stu) test02() {
	fmt.Println("test02()...")
}

func (stu Stu) test03() {
	fmt.Println("test03()...")
}


func main() {
	var stu Stu
	var a AInterface = stu
	var b BInterface = stu
	a.test01()
	a.test02()
	a.test03()
	b.test01()
}
```
9. interface 类型默认是一个指针(引用类型)，如果没有对 interface 初始化就使用，那么会输出 nil。
10. 空接口 interface{} 没有任何方法，所以所有类型都实现了空接口。
```
package main

import (
	"fmt"
)

type Stu struct {

}

type T interface {

}

func main() {

	var stu Stu
	var t T = stu //ok
	fmt.Println(t)
	var t2 interface{} =stu
	var num1 float64 = 8.8
	t2 = num1
	fmt.Println(t2)
}
```

### 接口最佳实践
1. 实现对 Hero 结构体切片的排序：sort.Sort(data Interface)。
```
package main

import (
	"fmt"
	"math/rand"
	"sort"
)



//1.声明 Hero 结构体
type Hero struct {
	Name string
	Age int
}


//2. 声明一个 Hero 结构体切片类型
type HeroSlice []Hero


//3. 实现 Interface 接口

func (hs HeroSlice) Len() int {
	return len(hs)
}


//Less 方法就是决定你使用什么标准进行排序
//1. 按 Hero 年龄的从小到大排序
func (hs HeroSlice) Less(i, j int) bool {
	return hs[i].Age < hs[j].Age
}

func (hs HeroSlice) Swap(i, j int) {
	//交换
	//temp := hs[i]
	//hs[i] = hs[j]
	//hs[j] = temp

	//下面一句话等价上面三句话
	hs[i], hs[j] = hs[j], hs[i]
}


func main() {
	//先定义一个数组/切片
	var intSlice = []int{0, -1, 10, 7, 90}
	//要求对 intSlice 进行排序
	//1. 冒泡排序...
	//2. 可以使用系统提供的方法
	sort.Ints(intSlice)
	fmt.Println(intSlice)

	//请大家对结构体切片进行排序
	//1. 冒泡排序
	//2. 可以使用系统提供的方法
	//测试看看我们是否可以对结构体切片进行排序
	var heroes HeroSlice
	for i :=0; i < 5; i++ {
		hero := Hero{
			Name :fmt.Sprintf("英雄~%d", rand.Intn(100)),
			Age : rand.Intn(100),
		}
		//将 hero append 到 heroes 切片
		heroes = append(heroes, hero)
	}

	//看看排序前的顺序
	//for _ , v := range heroes {
	//	fmt.Println(v)
	//}
	fmt.Println(heroes)

	//调用 sort.Sort
	sort.Sort(heroes)


	fmt.Println("排序后----")
	//看看排序后的顺序
	fmt.Println(heroes)

}
```

### 实现接口 VS 继承
1. 相关案例
```
package main

import "fmt"


// Monkey 结构体
type Monkey struct {
	Name string
}

//声明接口
type BirdAble interface {
	Flying()
}

//声明接口
type FishAble interface {
	Swimming()
}



func (this *Monkey) climbing() {
	fmt.Println(this.Name, "生来会爬树..")
}

//littleMonkey 结构体
type LittleMonkey struct {
	Monkey //继承
}

//让 LittleMonkey 实现 BirdAble 接口
func (this *LittleMonkey) Flying() {
	fmt.Println(this.Name, "通过学习，会飞翔...")
}

func (this *LittleMonkey) Swimming() {
	fmt.Println(this.Name, "通过学习，会游泳...")
}

func main() {
	//常见一个 LittleMonkey 实例
	monkey := LittleMonkey{
		Monkey{
			Name : "悟空",
		},
	}

	monkey.climbing()
	monkey.Flying()
	monkey.Swimming()

}
```
2. 对上面代码的小结
```
(1) 当 A 结构体继承了 B 结构体，那么 A 结构体就自动的继承了 B 结构体的字段和方法，并且可以直接使用。
(2) 当 A 结构体需要扩展功能，同时不希望去破坏继承关系，则可以去实现某个接口即可，因此我们可以认为：实现接口是对继承机制的补充。
```
3. 接口和继承解决问题的不同
```
(1) 继承的价值主要在于：解决代码的复用性和可维护性。
(2) 接口的价值主要在于：设计，设计好各种规范(方法)，让其他自定义类型去实现这些方法。
```
4. 接口比继承更加灵活
```
(1) 接口比继承更加灵活，继承是满足 is-a 的关系，而接口只需满足 like - a的关系。
```
5. 接口在一定程度上实现代码解耦。

## 面向对象编程-多态
### 基本介绍
1. 变量(实例)具有多种形态。面对对象的第三大特征，在 Go 语言，多态特征是通过接口实现的。
2. 多态可以按照统一的接口来调用不同的实现。这时接口变量就呈现不同的形态。

### 快速入门
1. 相关案例
```
package main

import "fmt"

//声明/定义一个接口
type Usb interface {
	//声明了两个没有实现的方法
	Start()
	Stop()
}

type Phone struct {

}

type Camera struct {

}


//让 Phone 实现 Usb 接口的方法

func (p Phone) Start() {
	fmt.Println("手机开始工作...")
}

func (p Phone) Stop() {
	fmt.Println("手机停止工作...")
}

// 让 Camera 实现 Usb 接口的方法
func (c Camera) Start() {
	fmt.Println("相机开始工作...")
}

func ( Camera) Stop() {
	fmt.Println("相机停止工作...")
}


//计算机
type Computer struct {

}

//编写一个方法 working， 接收一个 Usb 接口类型变量
//只要是实现了 Usb 接口(所谓实现 Usb 接口，就是指实现了 Usb 接口声明的所有方法)
func (c Computer) Working(usb Usb) {
		//通过 usb 接口变量来调用 start 和 stop 方法
		usb.Start()
		usb.Stop()
}

func main() {

	//测试
	//先创建结构体变量
	computer := Computer{}
	phone := Phone{}
	camera := Camera{}

	//关键点
	computer.Working(phone)
	computer.Working(camera)

}
```

### 接口体现多态特征
1.多态参数在前面的Usb接口案例，就体现了 Usb 接口的多态
2. 多态数组
```
演示一个案例：给 Usb 数组中，存放 Phone 结构体和 Camera 结构体变量。
```
```
package main

import "fmt"

//声明/定义一个接口
type Usb interface {
	//声明了两个没有实现的方法
	Start()
	Stop()
}

type Phone struct {
	Name string
}

type Camera struct {
	Name string
}


//让 Phone 实现 Usb 接口的方法

func (p Phone) Start() {
	fmt.Println("手机开始工作...")
}

func (p Phone) Stop() {
	fmt.Println("手机停止工作...")
}

// 让 Camera 实现 Usb 接口的方法
func (c Camera) Start() {
	fmt.Println("相机开始工作...")
}

func ( Camera) Stop() {
	fmt.Println("相机停止工作...")
}


//计算机
type Computer struct {

}

//编写一个方法 working， 接收一个 Usb 接口类型变量
//只要是实现了 Usb 接口(所谓实现 Usb 接口，就是指实现了 Usb 接口声明的所有方法)
func (c Computer) Working(usb Usb) {
	//通过 usb 接口变量来调用 start 和 stop 方法
	usb.Start()
	usb.Stop()
}

func main() {
	//定义一个 usb 接口数组，可以存放 phone 和 Camera 的结构体变量
	//这里就体现出多态数组
	var usbArr [3]Usb
	usbArr[0] = Phone{"vivo"}
	usbArr[1] = Phone{"小米"}
	usbArr[2] = Camera{"尼康"}
	fmt.Println(usbArr)


}
```

## 类型断言
### 先看一个需求
1. 案例一
```
package main

import "fmt"

type Point struct {
	x int
	y int
}

func main() {
	var a interface{}
	var point Point = Point{1, 2}
	a = point //ok
	//如何将 a 赋给一个 Point 变量？
	var b Point
	//b = a // error
	b = a.(Point) //类型断言
	fmt.Println(b)
}
```
2. 上面代码进行总结：
```
(1) b = a.(Point)就是类型断言，表示判断 a 是否指向 Point 类型的变量，如果是就转成 Point 类型并赋给 b 变量，否则报错。
```

### 基本介绍
1. 类型断言：由于接口是一般类型，不知道具体类型，如果要转成具体类型，就需要适用类型断言，具体的如下：
2. 案例二
```
package main

import "fmt"

func main()  {
	//float32 可以是其他类型，比如 Point
	var t float32
	var x interface{}
	x = t //ok
	y := x.(float32) //转成 float


	fmt.Println(y)
}

```
3. 案例三
```
package main

import "fmt"

func main() {
	//float32 可以是其他类型，比如 Point
	var t float32
	var x interface{}
	x = t
	//转成 float，带检查的
	y, ok := x.(float32)
	if ok {
		fmt.Println("convert success")
		fmt.Println(y)
	} else {
		fmt.Println("Convert fail")
	}
}
```
4. 对上面代码的说明
```
(1) 进行类型断言时，如果类型不匹配，就会报 panic，因此进行类型断言时，要确保原来空接口类型指向的就是要断言的类型。
```

### 类型断言的最佳实践一
1. 在前面的 Usb 接口案例做改进：给 Phone 结构体增加一个特有的方法 call()，当 Usb 接口接受的是 Phone 变量时，还需要调用 call 方法。
```
package main

import "fmt"

//声明/定义一个接口
type Usb interface {
	//声明了两个没有实现的方法
	Start()
	Stop()
}

type Phone struct {
	Name string
}

type Camera struct {
	Name string
}


//让 Phone 实现 Usb 接口的方法

func (p Phone) Start() {
	fmt.Println("手机开始工作...")
}

func (p Phone) Stop() {
	fmt.Println("手机停止工作...")
}

// Phone 独有的方法
func (p Phone) Call() {
	fmt.Println("手机打电话...")
}

// 让 Camera 实现 Usb 接口的方法
func (c Camera) Start() {
	fmt.Println("相机开始工作...")
}

func ( Camera) Stop() {
	fmt.Println("相机停止工作...")
}


//计算机
type Computer struct {

}

//编写一个方法 working， 接收一个 Usb 接口类型变量
//只要是实现了 Usb 接口(所谓实现 Usb 接口，就是指实现了 Usb 接口声明的所有方法)
func (c Computer) Working(usb Usb) {
	//通过 usb 接口变量来调用 start 和 stop 方法
	usb.Start()
	//如果 usb 是指向 Phone 结构体变量，则还需要调用 Call 方法
	//类型断言...
	if phone, ok := usb.(Phone); ok {
		phone.Call()
	}
	usb.Stop()
}

func main() {
	//定义一个 usb 接口数组，可以存放 phone 和 Camera 的结构体变量
	//这里就体现出多态数组
	var usbArr [3]Usb
	usbArr[0] = Phone{"vivo"}
	usbArr[1] = Phone{"小米"}
	usbArr[2] = Camera{"尼康"}
	fmt.Println(usbArr)

	//遍历usbArr
	//Phone 还有一个特有的方法 call()，请遍历 Usb 数组，如果是 Phone 变量，
	//除了调用 Usb 接口声明的方法外，还需要调用 Phone 特有方法 Call。
	var c Computer
	for _, value := range usbArr {
		c.Working(value)
	}

}
```

### 类型断言的最佳实践二
1. 相关案例
```
package main

import "fmt"


//编写一个函数，可以判断输入的参数是什么类型

func TypeJudge(items... interface{}) {
	for index, value := range items {
		switch value.(type) {
		case bool:
			fmt.Printf("第%v个参数是 bool 类型，值是%v\n", index + 1, value)
		case float32:
			fmt.Printf("第%v个参数是 float32 类型，值是%v\n", index + 1, value)
		case float64:
			fmt.Printf("第%v个参数是 float64 类型，值是%v\n", index + 1, value)
		case int, int32, int64:
			fmt.Printf("第%v个参数是 整数 类型，值是%v\n", index + 1, value)
		case string:
			fmt.Printf("第%v个参数是 string 类型，值是%v\n", index + 1, value)
		default:
			fmt.Printf("第%v个参数类型不确定，值是%v\n", index + 1, value)
		}
	}
}


func main() {
	var n1 float32 = 1.1
	var n2 float64 = 2.3
	var n3 int32 = 30
	var name string = "Tom"
	address := "北京"
	n4 := 300
	TypeJudge(n1, n2, n3, n4, name, address)
}
```
### 类型断言的最佳实践三
1. 在前面代码的基础上，增加判断 Student 类型和 *Student。
```
package main

import "fmt"


//定义 Student 类型
type Student struct {
	Name string
}


//编写一个函数，可以判断输入的参数是什么类型

func TypeJudge(items... interface{}) {
	for index, value := range items {
		switch value.(type) {
		case bool:
			fmt.Printf("第%v个参数是 bool 类型，值是%v\n", index + 1, value)
		case float32:
			fmt.Printf("第%v个参数是 float32 类型，值是%v\n", index + 1, value)
		case float64:
			fmt.Printf("第%v个参数是 float64 类型，值是%v\n", index + 1, value)
		case int, int32, int64:
			fmt.Printf("第%v个参数是 整数 类型，值是%v\n", index + 1, value)
		case string:
			fmt.Printf("第%v个参数是 string 类型，值是%v\n", index + 1, value)
		case Student:
			fmt.Printf("第%v个参数是 Student 类型，值是%v\n", index + 1, value)
		case *Student:
			fmt.Printf("第%v个参数是 *Student 类型，值是%v\n", index + 1, value)
		default:
			fmt.Printf("第%v个参数类型不确定，值是%v\n", index + 1, value)
		}
	}
}




func main() {
	var n1 float32 = 1.1
	var n2 float64 = 2.3
	var n3 int32 = 30
	var name string = "Tom"
	address := "北京"
	n4 := 300

	stu1 := Student{"Tom"}
	stu2 := &Student{"Jack"}

	TypeJudge(n1, n2, n3, n4, name, address, stu1, stu2)
}
```

