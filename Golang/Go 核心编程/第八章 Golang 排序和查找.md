# Golang 排序和查找
## 排序
### 基本介绍
1. 排序是将一组数据，依指定的顺序进行排序的过程。
2. 排序的分类：
```
(1) 内部排序：
指将需要处理的所有数据都加载到内部存储器中进行排序。
包括(交换式排序法、选择式排序法和插入式排序法)。
(2) 外部排序法：
数据量过大，无法全部加载到内存中，需要借助外部存储进行排序。
包括(合并排序法和直接合并排序法)。
```

### 交换式排序法
1. 交换式排序属于内部排序法，是运用数据值比较厚，依判断规则对数据位置进行交换，以达到排序的目的。
2. 交换式排序算法又可分为两种：
```
(1) 冒泡排序(Bubble sort)
(2) 快速排序法(Quick sort)
``` 

#### 冒泡排序法
1. 冒泡排序(Bubble Sorting)的基本思想是：通过对待排序序列从后往前(从下标较大的元素开始)，依次比较相邻元素的排序码，若发现逆序则交换，是排序码较小的元素逐渐从后部移向前部(从下标较大的单元移向较小的单元)，就象水底下的气泡一样逐渐向上冒。
2. 因为排序的过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此要在排序过程中设置一个标志 flag 判断元素是否进行过交换，从而减少不必要的比较。(优化)

##### 冒泡排序法的案例
1. 我们将一个无序数列[24, 69, 80, 57, 13]使用冒泡排序法将其排成一个从小大大的有序数列。
2. 冒泡排序的规则
```
(1) 一共会经过 arr.length-1轮数比较，每一轮将会确定一个数的位置。
(2) 每一轮的比较次数在逐渐的减少。【4，3，2，1】
(3) 当发现前面的一个数比后面的一个数大的时候，就进行了交换。
```
3. 快速入门案例
```
package main

import "fmt"


//冒泡排序
func BubbleSort(arr *[5]int) {
	fmt.Println("排序前 arr = ", *arr)
	temp := 0 //临时变量用于做交换

	//冒泡排序: 一步一步推导出来
	for i := 0; i < len(*arr) - 1; i++ {
		//第一轮的排序(外层第一次)
		for j := 0; j < len(*arr) - 1 - i; j++ {
			if (*arr)[j] > (*arr)[j+1] {
				//交换
				temp = (*arr)[j]
				(*arr)[j] = (*arr)[j+1]
				(*arr)[j+1] = temp
			}
		}
		fmt.Printf("第%v次排序后 arr = %v \n", i+1, *arr)
	}
	fmt.Println("arr = ", *arr)
}

func main() {
	var arr = [...]int{24, 69, 80, 57, 13}
	//将数组传递给一个函数，完成排序
	BubbleSort(&arr)

	fmt.Println("main arr = ", arr) //有序
}
```


## 查找
### 基本介绍
1. 在 Golang 中，我们常用的查找有两种：
```
(1) 顺序查找
(2) 二分查找(该数组是有序的)
```
### 案例演示
1. 有一个数列：白眉鹰王、金毛狮王、紫衫龙王、青翼蝠王。猜数游戏：从键盘中任意输入一个名称，判断数列中是否包含此名称[顺序查找]。
```
package main

import "fmt"

func main() {
	/*
		有一个数列：白眉鹰王、金毛狮王、紫衫龙王、青翼蝠王。
		猜数游戏：从键盘中任意输入一个名称，判断数列中是否包含此名称[顺序查找]。
	*/

	/*
		思路分析：
			1. 定义一个数组：白眉鹰王、金毛狮王、紫衫龙王、青翼蝠王。(字符串数组)
			2. 从控制台接收一个名词，一次比较，如果发现有，提示。
	*/

	names := [...]string{"白眉鹰王", "金毛狮王", "紫衫龙王", "青翼蝠王"}
	var heroName string
	fmt.Println("请输入要查找的人名...")
	fmt.Scanln(&heroName)

	//顺序查找：第一种方式
	for i := 0;i < len(names); i++ {
		if heroName == names[i] {
			fmt.Printf("找到%v , 下标是%v\n", heroName, i)
			break
		}else if i == (len(names) - 1) {
			fmt.Printf("没有找到%v\n", heroName)
		}
	}

	
	//顺序查找：第二种方式(推荐使用)
	index := -1
	for i := 0;i < len(names); i++ {
		if heroName == names[i] {
			index = i //将找到的值对应下标赋给 index
			break
		}
	}
	if index != -1 {
		fmt.Printf("找到%v , 下标是%v\n", heroName, index)
	} else {
		fmt.Printf("没有找到%v\n", heroName)
	}
}
```
2. 二分查找的思路：比如我们要找的数是findVal。
 ```
(1) arr是一个有序数组，并且是从小到大排序
(2) 先找到中间的下标 middle = (leftIndex + rightIndex) / 2，然后让中间下标的值和findVal进行比较。
(3) 如果 arr[middle] > findVal，就应该向 leftIndex --- (middle - 1)
(4) 如果 arr[middle] < findVal，就应该向 middle + 1 --- rightIndex
(5) 如果 arr[middle] == findVal，就找到
(6) 上面的逻辑会递归执行。
(7) 递归结束的条件
if leftIndex > rightIndex {
    //找不到
    return ...
}
```
3. 请对一个有序数组进行二分查找{1, 8, 10, 89, 1000, 1234}，输入一个数看看该数组是否存在此数，并且求出下表，如果没有就提示“没有这个数”。[会使用到递归]。
```
package main

import "fmt"


/*
	二分查找的思路：比如我们要查找的数是findVal。
		(1) arr是一个有序数组，并且是从小到大排序
		(2) 先找到中间的下标 middle = (leftIndex + rightIndex) / 2，然后让中间下标的值和findVal进行比较。
		(3) 如果 arr[middle] > findVal，就应该向 leftIndex --- (middle - 1)
		(4) 如果 arr[middle] < findVal，就应该向 middle + 1 --- rightIndex
		(5) 如果 arr[middle] == findVal，就找到
		(6) 上面的逻辑会递归执行。
		(7) 递归结束的条件
		if leftIndex > rightIndex {
			//找不到
			return ...
		}
*/


//二分查找的函数
func BinaryFind(arr *[6]int, leftIndex int, rightIndex int, findVal int) {

	//判断leftIndex 是否大于 rightIndex
	if leftIndex > rightIndex {
		fmt.Println("找不到")
		return
	}

	//先找到中间的下标
	middle := (leftIndex + rightIndex) / 2
	if (*arr)[middle] > findVal {
		//说明我们要查找的数，应该在 leftIndex --- (middle - 1)
		BinaryFind(arr, leftIndex, middle - 1, findVal)
	} else if (*arr)[middle] < findVal {
		//说明我们要查找的数，应该在 middle + 1 --- rightIndex
		BinaryFind(arr, middle + 1, rightIndex, findVal)
	} else {
		//找到了
		fmt.Printf("找到了，下标为%v \n", middle)
	}

}


func main() {
	arr := [...]int{1, 8, 10, 89, 1000, 1234}
	findVal := 89
	leftIndex := 0
	rightIndex := len(arr) - 1
	BinaryFind(&arr, leftIndex, rightIndex, findVal)

}
```

## 多维数组-二维数组
### 二维数组的应用场景
1. 比如我们开发一个五子棋游戏，棋盘就是需要二维数组来表示。

### 二维数组的快速入门
1. 请用二维数组输出如下图形：
```
0 0 0 0 0 0
0 0 1 0 0 0
0 2 0 3 0 0
0 0 0 0 0 0
```
3. 案例
```
package main

import "fmt"

func main() {
	//二维数组的演示案例
	/*
		0 0 0 0 0 0
		0 0 1 0 0 0
		0 2 0 3 0 0
		0 0 0 0 0 0
	*/

	//定义/声明二维数组
	var arr [4][6]int

	//fmt.Println("arr = ", arr)

	//赋初值
	arr[1][2] = 1
	arr[2][1] = 2
	arr[2][3] = 3

	//fmt.Println("arr = ", arr)


	//遍历二维数组，按照要求输出图形。
	for i := 0; i < len(arr); i++ {
		for j := 0; j < len(arr[i]); j++ {
			fmt.Print(arr[i][j], " ")
		}
		fmt.Println()
	}
}
```

### 二维数组的使用方式
1. 使用方式1：先声明/定义 再赋值
```
(1) 语法：var 数组名 [大小][大小]类型
(2) 比如：var arr [2][3]int[][]，再赋值。
(3) 使用演示
(4) 二维数组在内存的存在形式(重点)
```
```
package main

import "fmt"

func main() {

	var arr2 [2][3]int //以这个为例来分析 arr2 在内存的布局！！
	arr2[1][1] = 10
	fmt.Println(arr2)

	fmt.Printf("arr2[0]的地址 %p\n", &arr2[0])
	fmt.Printf("arr2[1]的地址 %p\n", &arr2[1])

	fmt.Printf("arr2[0][0]的地址 %p\n", &arr2[0][0])
	fmt.Printf("arr2[1][0]的地址 %p\n", &arr2[1][0])
}
```
2. 使用方式2：直接初始化
```
(1) 声明：var 数组名 [大小][大小]类型 = [大小][大小]类型{{初值..},{初值...}}
(2) 赋值(有默认值，比如int 类型的就是0)
(3) 使用演示
(4) 说明：二维数组在声明/定义时也对应有四种写法[和一维数组类似]
var 数组名 [大小][大小]类型 = [大小][大小]类型{{初值..},{初值...}}
var 数组名 [大小][大小]类型 = [...][大小]类型{{初值..},{初值...}}
var 数组名 = [大小][大小]类型{{初值..},{初值...}}
var 数组名 = [...][大小]类型{{初值..},{初值...}}
```
```
package main

import "fmt"

func main() {
	//使用方式2：直接初始化
	var arr3 [2][3]int = [2][3]int{{1,2,3},{4,5,6}}
	fmt.Println("arr3 = ", arr3)

}
```

### 二维数组的遍历
1. 双层 for 循环完成遍历
2. for-range 方式完成遍历
3. 案例演示
```
package main

import "fmt"

func main() {
	//演示二维数组的遍历

	//双层 for 循环完成遍历

	var arr [2][3]int = [2][3]int{{1,2,3},{4,5,6}}

	for i := 0; i < len(arr); i++ {
		for j:= 0; j < len(arr[i]); j++ {
			fmt.Printf("%v\t", arr[i][j])
		}
		fmt.Println()
	}

	//for-range 方式完成遍历
	for i, v := range arr {
		for j, v2 :=range v {
			fmt.Printf("arr[%v][%v] = %v\t", i, j, v2)
		}
		fmt.Println()
	}

}
```
### 二维数组应用案例
1. 定义二维数组，用于保存三个班，每个班五名同学成绩，并求出每个班级平均分、以及所有班级平均分。
```
package main

import "fmt"

func main() {
	/*
		定义二维数组，用于保存三个班，每个班五名同学成绩，并求出每个班级平均分、以及所有班级平均分。
	*/
	
	//1. 定义一个二维数组
	var scores [3][5]float64

	//2. 循环的输入成绩
	for i := 0; i < len(scores); i++ {
		for j := 0; j < len(scores[i]); j++ {
			fmt.Printf("请输入第%d班的第%d个学生的成绩：\n", i+1, j+1)
			fmt.Scanln(&scores[i][j])
		}
	}

	//fmt.Println(scores)


	//3.遍历输入成绩后的二维数组，统计平均分。
	totalSum := 0.0 //定义一个变量，用于累计所有班级的总分。
	for i := 0; i < len(scores); i++ {
		sum := 0.0 // 定义一个变量累计各个班级的总分
		for j := 0; j < len(scores[i]); j++ {
			sum += scores[i][j]

		}
		totalSum += sum
		fmt.Printf("第%d班级的总分为%v, 平均分%v\n", i + 1, sum, sum / float64(len(scores[i])))
	}
	fmt.Printf("所有班级的总分为%v, 所有班级的平均分%v\n", totalSum, totalSum / 15)

}
```
