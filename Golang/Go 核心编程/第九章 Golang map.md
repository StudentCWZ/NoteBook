# Golang map
## map 基本介绍
1. map 是 key-value 数据结构，又称为字段或者关联数组。类似其他编程语言的集合，在编程中是经常使用到。

## map 声明
### map 基本语法
1. 基本语法
```
var map变量名 map[keytype]valuetype
```
2. keytype 可以的类型
```
(1) golang 中的 map 的 key 可以是很多种类型，比如 bool，数组，string，指针，channel，还可以只是包含前面几个类型的接口，结构体，数组。通常为 int、string。
(2) slice, map 还有 function 不可以，因为这几个没法用 == 来判断
```
3. valuetype 可以是什么类型
```
(1) valuetype的类型和 key 基本一样
(2) 通常为：数字(整数、浮点数)，string，map，struct
```
### map 声明的举例
```
var a map[string]string
var a map[string]int
var a map[int][string]
var a map[string]map[string]string
注意：声明是不会分配内存的，初始化需要make，分配内存后才能赋值和使用。
```
#### 案例演示
1. 案例演示
```
package main

import "fmt"


func main() {
	//map 的声明和注意事项
	var a map[string]string
	//a["no1"] = "宋江" 如果不 make，直接赋值就会 panic
	//在使用 map 前，需要 make ，make 的作用就是给 map 分配数据空间
	a = make(map[string]string, 10)
	a["no1"] = "宋江"
	a["no2"] = "吴用"
	a["no1"] = "武松" // key 不能重复，相同 key 会覆盖
	a["no3"] = "吴用" // value 可以重复
	//golang 中 map 是 key 无序的。
	fmt.Println(a)
}
```
2. 对上面代码的说明
```
(1) map 在使用前一定 make 。
(2) map 的 key 不能重复，如果重复了，则以最后的key-value。
(3) map 的 value 是可以相同的。
(4) map 的 key-value 是无序的。
```

## map 的使用
1. map 的使用方式有以下多种，分别演示。
2. 方式一
```
package main

import "fmt"

func main() {
	//第一种使用方式

	var a map[string]string
	//a["no1"] = "宋江" 如果不 make，直接赋值就会 panic
	//在使用 map 前，需要 make ，make 的作用就是给 map 分配数据空间
	a = make(map[string]string, 10)
	a["no1"] = "宋江"
	a["no2"] = "吴用"
	a["no1"] = "武松" // key 不能重复，相同 key 会覆盖
	a["no3"] = "吴用" // value 可以重复
	//golang  map 的 key-value 是无序的。
	fmt.Println(a)
}
```
3. 方式二
```
package main

import "fmt"

func main() {
	//第二种使用方式
	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"

	fmt.Println(cites)

	//第三种方式
	heros := map[string]string{"hero1": "宋江", "hero2": "卢俊义", "hero3": "吴用"}
	fmt.Println(heros)
}
```
4. 方式三
```
package main

import "fmt"

func main() {
	//第三种方式
	heros := map[string]string{"hero1": "宋江", "hero2": "卢俊义", "hero3": "吴用"}
	heros["hero4"] = "林冲"
	fmt.Println("heros = ", heros)
}
```
4. 应用案例
```
package main

import "fmt"

/*
	应用案例：
		演示一个 key-value 的 value 是 map 的实例。
		比如：我们要存放3个学生信息，每个学生有 name 和 sex 信息。
*/

func main() {
	studentMap := make(map[string]map[string]string)

	studentMap["stu01"] = make(map[string]string, 3)
	studentMap["stu01"]["name"] = "Tom"
	studentMap["stu01"]["sex"] = "男"
	studentMap["stu01"]["address"] = "北京长安街~"

	studentMap["stu02"] = make(map[string]string, 3) //这句话不能少
	studentMap["stu02"]["name"] = "Mary"
	studentMap["stu02"]["sex"] = "女"
	studentMap["stu02"]["address"] = "上海黄浦江~"

	fmt.Println(studentMap)
	fmt.Println(studentMap["stu02"])
	fmt.Println(studentMap["stu02"]["address"])
}
```

## map 的增删改查操作
1. map 增加和更新
```
map["key"] = value //如果可以没有，就是增加，如果可以存在就是修改。
```
```
package main

import "fmt"

func main() {

	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"
	fmt.Println(cites)

	//因为 no3这个 key 已经存在，因此下面的这句话就是修改
	cites["no3"] = "上海~"

	fmt.Println(cites)

}
```
2. map 删除:
```
说明：
delete(map, "key")，delete是个内置函数，如果 key 存在，就删除该 key-value，如果 key 不存在，不操作，但是也不会报错。
```
```
package main

import "fmt"

func main() {

	// map 增加和更新
	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"
	fmt.Println(cites)

	//因为 no3这个 key 已经存在，因此下面的这句话就是修改
	cites["no3"] = "上海~"

	fmt.Println(cites)


	//map 删除
	delete(cites, "no1")
	fmt.Println(cites)

	//当 delete 指定的 key 不存在时，删除不会操作，也不会报错。
	delete(cites, "no4")
	fmt.Println(cites)

}
```
3. map 删除细节说明：
```
(1) 如果我们要删除 map 的所有 key，没有一个专门的方法一次删除，可以遍历一下 key，逐个删除。
(2) 或者 map = make(...)，make 一个新的，让原来的成为垃圾，被gc回收。
```
```
package main

import "fmt"

func main() {

	// map 增加和更新
	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"
	fmt.Println(cites)

	//因为 no3这个 key 已经存在，因此下面的这句话就是修改
	cites["no3"] = "上海~"

	fmt.Println(cites)


	//map 删除
	delete(cites, "no1")
	fmt.Println(cites)

	//当 delete 指定的 key 不存在时，删除不会操作，也不会报错。
	delete(cites, "no4")
	fmt.Println(cites)

	//如果希望一次性删除所有的 key
	//1. 遍历删除
	//2. 直接 make 一个新空间
	cites = make(map[string]string)
	fmt.Println(cites)

}
```
4. map 查找
```
package main

import "fmt"

func main() {

	// map 增加和更新
	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"
	fmt.Println(cites)

	//因为 no3这个 key 已经存在，因此下面的这句话就是修改
	cites["no3"] = "上海~"

	fmt.Println(cites)


	//map 删除
	delete(cites, "no1")
	fmt.Println(cites)

	//当 delete 指定的 key 不存在时，删除不会操作，也不会报错。
	delete(cites, "no4")
	fmt.Println(cites)


	//map 查找
	val, findRes := cites["no2"]
	if findRes {
		fmt.Printf("有 no1 key 值为%v\n", val)
	} else {
		fmt.Printf("没有 no1 key\n")
	}


	//如果希望一次性删除所有的 key
	//1. 遍历删除
	//2. 直接 make 一个新空间
	cites = make(map[string]string)
	fmt.Println(cites)

}
```
5. 对上面代码说明
```
(1) 如果 cites 这个 map 中存在“no1”，那么 findRes就会返回 true，否则返回false。
```

### map 遍历
1. map 的遍历使用 for-range的结构遍历

### 案例演示
```
package main

import (
	"fmt"
)

func main() {
	//使用 for-range 遍历 map

	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"

	for key, value := range cites {
		fmt.Printf("key = %v value = %v\n", key, value)
	}

	//使用 for-range 遍历一个结构比较复杂的 map

	studentMap := make(map[string]map[string]string)

	studentMap["stu01"] = make(map[string]string, 3)
	studentMap["stu01"]["name"] = "Tom"
	studentMap["stu01"]["sex"] = "男"
	studentMap["stu01"]["address"] = "北京长安街~"

	studentMap["stu02"] = make(map[string]string, 3) //这句话不能少
	studentMap["stu02"]["name"] = "Mary"
	studentMap["stu02"]["sex"] = "女"
	studentMap["stu02"]["address"] = "上海黄浦江~"

	for key1, value1 := range studentMap {
		fmt.Println("key1 =", key1)
		for key2, value2 := range value1 {
			fmt.Printf("\t key2 = %v value2 = %v\n", key2, value2)
		}
	}

}
```

### map 长度
#### 案例演示
```
package main

import (
	"fmt"
)

func main() {
	//使用 for-range 遍历 map

	cites := make(map[string]string)
	cites["no1"] = "北京"
	cites["no2"] = "天津"
	cites["no3"] = "上海"

	for key, value := range cites {
		fmt.Printf("key = %v value = %v\n", key, value)
	}

	//求 map 长度
	fmt.Println("cites 有", len(cites), "对 key-value")
}
```

## map 切片
### 基本介绍
1. 切片的数据类型如果是 map ，则我们称为 slice of map， map 切片，这样使用则 map 个数就可以动态变化了。

### 案例演示
1. 要求：使用一个 map 来记录 monster 的信息 name 和 age，也就是说一个 monster 对应一个 map，并且妖怪的个数可以动态的增加 => map 切片。
```
package main

import "fmt"

func main() {
	/*
		要求：使用一个 map 来记录 monster 的信息 name 和 age，也就是说一个 monster 对应一个 map，
		并且妖怪的个数可以动态的增加 => map 切片。
	*/

	//1. 声明一个 map 切片
	var monsters []map[string]string

	monsters = make([]map[string]string, 2) //准备放入两个妖怪

	//2. 增加第一个妖怪信息
	if monsters[0] == nil {
		monsters[0] = make(map[string]string, 2)
		monsters[0]["name"] = "牛魔王"
		monsters[0]["age"] = "500"
	}

	if monsters[1] == nil {
		monsters[1] = make(map[string]string, 2)
		monsters[1]["name"] = "玉兔精"
		monsters[1]["age"] = "400"
	}

	//下面这个写法越界了
	//if monsters[2] == nil {
	//	monsters[2] = make(map[string]string, 2)
	//	monsters[2]["name"] = "狐狸精"
	//	monsters[2]["age"] = "400"
	//}

	//这里我们需要使用到切片的 append 函数，可以动态的增加 monster
	//先定义一个 monster 信息
	newMonster := map[string]string{
		"name": "新妖怪~火云邪神",
		"age": "200",
	}
	monsters = append(monsters, newMonster)
	fmt.Println(monsters)
}
```

## map 排序
### 基本介绍
1. golang 中没有一个专门的方法针对 map 的 key 进行排序。
2. golang 中的 map 默认是无序的，注意也不是按照添加的顺序存放的，你每次遍历输出的结果可能不一样。
3. golang 中的 map 的排序，是先将 key 进行排序，然后根据 key 值遍历输出即可。

### 案例演示
```
package main

import (
	"fmt"
	"sort"
)

func main() {

	//map 的排序

	map1 := make(map[int]int, 10)

	map1[10] = 100
	map1[1] = 13
	map1[4] = 56
	map1[8] = 90

	fmt.Println(map1)

	//如果按照 map 的 key 的顺序进行排序输出
	//1. 先将 map 的 key 放入切片中
	//2. 对切片排序
	//3. 遍历切片，然后按照 key 来输出 map 的值

	var keys []int

	for key, _ := range map1 {
		keys = append(keys, key)
	}

	//排序
	sort.Ints(keys)
	fmt.Println(keys)

	for _, key := range keys {
		fmt.Printf("map1[%v] = %v \n", key, map1[key])
	}

}
```

## map 使用细节
1. map 是引用类型，遵守引用类型传递的机制，在一个函数接收 map，修改后，会直接修改原来的 map。
```
package main

import "fmt"


func modify(map1 map[int]int) {
	map1[10] = 900
}



func main() {
	//map 是引用类型，遵守引用类型传递的机制，在一个函数接收 map，修改后，会直接修改原来的 map。
	map1 := make(map[int]int)

	map1[1] = 90
	map1[2] = 88
	map1[10] = 1
	map1[20] = 2

	modify(map1)
	fmt.Println(map1) //看结果 map[1:90 2:88 10:900 20:2] ，则说明 map 为引用类型。

}
```
2. map 的容量达到后，再想 map 增加函数，会自动扩容，并不会发生 panic ，也就是说 map 能动态地增长键值对(key-value)。
```
package main

import "fmt"


func modify(map1 map[int]int) {
	map1[10] = 900
}



func main() {
	//map 是引用类型，遵守引用类型传递的机制，在一个函数接收 map，修改后，会直接修改原来的 map。
	map1 := make(map[int]int, 2)

	map1[1] = 90
	map1[2] = 88
	map1[10] = 1
	map1[20] = 2 // map 可以自动扩容

	modify(map1)
	fmt.Println(map1) //看结果 map[1:90 2:88 10:900 20:2] ，则说明 map 为引用类型。

}
```
3. map 的 value 也经常使用 struct 类型，更适合管理复杂的数据(比前面 value 是一个 map 更好)。
```
package main

import "fmt"


//定义一个学生结构体

type stu struct {
	Name string
	Age int
	Address string
}


func main() {
	// map 的 value 也经常使用 struct 类型，更适合管理复杂的数据(比前面 value 是一个 map 更好)。
	// 1. map 的 key 为学生的学号，是唯一的。
	// 2. map 的 value为结构体，包含学生的 名字，年龄，地址


	students := make(map[string]stu, 10)

	//创建两个学生
	stu1 := stu{"Tom", 18, "北京"}
	stu2 := stu{"Mary", 28, "上海"}

	students["no1"] = stu1
	students["no2"] = stu2

	fmt.Println(students)
	fmt.Println()


	//遍历各个学生信息
	for key, value := range students {
		fmt.Printf("学生的编号是%v \n", key)
		fmt.Printf("学生的名字是%v \n", value.Name)
		fmt.Printf("学生的年龄是%v \n", value.Age)
		fmt.Printf("学生的地址是%v \n", value.Address)
		fmt.Println()
	}
}
```

## 课堂练习
1. 课堂练习
```
(1) 使用 map[string]map[string]string 的 map 类型。
(2) key：表示用户名，是唯一的，不可以重复。
(3) 如果某个用户存在，就将其密码修改“888888”，如果不存在就增加这个用户信息。(包括昵称(nickname 和 密码 pwd))。
(4) 编写一个函数 modifyUser(user map[string]map[string]string, name string)完成上述功能。
```
```
package main

import "fmt"


/*
	课堂练习：
		(1) 使用 map[string]map[string]string 的 map 类型。
		(2) key：表示用户名，是唯一的，不可以重复。
		(3) 如果某个用户存在，就将其密码修改“888888”，如果不存在就增加这个用户信息。(包括昵称(nickname 和 密码 pwd))。
		(4) 编写一个函数 modifyUser(user map[string]map[string]string, name string)完成上述功能。
*/


func modifyUser(users map[string]map[string]string, name string) {

	//判断 user 中是否有 name
	//v, ok := user[name]
	if users[name] != nil {
		// 有这个用户
		users[name]["pwd"] = "888888"

	} else {
		//目前没有这个用户
		users[name] = make(map[string]string, 2)
		users[name]["pwd"] = "888888"
		users[name]["nikcename"] = "昵称~" + name //示意
	}

}


func main() {
	users := make(map[string]map[string]string, 10)
	users["Smith"] = make(map[string]string, 2)
	users["Smith"]["pwd"] = "999999"
	users["Smith"]["nikename"] = "小花猫"

	modifyUser(users, "Tom")
	modifyUser(users, "Marry")
	modifyUser(users, "Smith")

	fmt.Println(users)
}
```
