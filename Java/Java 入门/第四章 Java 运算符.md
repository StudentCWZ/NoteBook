# Java 运算符
## 算术运算符
1. 算术运算符类型

|算术运算符类型|含义|
|:--:|:--:|
| + |求和|
| - |相减|
| * |乘积|
| / |商|
| % |求余数【取模】|
| ++ |自加 1 |
| -- |自减 1 |

2. 举例
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 10:13:50
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 10:14:33
*/


public class OperatorTest01
{
  public static void main(String[] args){

    int i = 10;
    int j = 3;
    System.out.println(i + j);// 13
    System.out.println(i - j);// 7
    System.out.println(i * j);// 30
    System.out.println(i / j);// 3
    System.out.println(i % j);// 1

    //以下以 ++ 为例，--运算符自学！
    //关于 ++ 运算符【自加1】
    int k = 10;

    // ++ 运算符可以出现在变量后面【单目运算符】
    //k ++;

    System.out.println(k);// 11

    int y = 10;

    // ++ 运算符可以出现在变量前面【单目运算符】
    ++ y;

    System.out.println(y);// 11

    //小结：
    // ++ 运算符可以出现在变量前，也可以出现在变量后，无论是变量前还是变量后，只要 ++ 运算结束，该变量中的值一定会自加 1 。


    // ++ 出现在变量后
    //规则：先做赋值运算，再对变量中保存的值进行自加 1 。
    int a = 100;
    int b = a ++;
    System.out.println(a);//101
    System.out.println(b);//100

    //++ 出现在变量前
    //规则：先进行自加 1 运算，然后再进行赋值操作。
    int m = 20;
    int n = ++ m;
    System.out.println(m);// 21
    System.out.println(n);// 21


    int mm = 500;
    System.out.println(mm);

    int e = 100;
    System.out.println(e ++);// 100
    System.out.println(e);// 101


    int s = 100;
    System.out.println(s++);// 101
    System.out.println(s);// 101

    System.out.println(--s);// 100
    System.out.println(s++);// 100
    System.out.println(s--);// 101
    System.out.println(s--);// 100
    System.out.println(s--);// 99
    System.out.println(s);// 98
  }
}
```

## 关系运算符
1. 关系运算符类型

|关系运算符类型|含义|
|:--:|:--:|
| > |大于|
| >= |大于等于|
| < |小于|
| <= |小于等于|
| == |等于|
| != |不等于|

2. 注意
```
= 是赋值运算符，== 是关系运算符。
```
3. 关系运算符的运算结果一定是布尔类型：true/false 。
4. 举例
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:14:06
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:14:20
*/


public class OperatorTest02
{
  public static void main(String[] args){
    int a = 10;
    int b = 10;
    System.out.println(a > b); // false
    System.out.println(a >= b); // true
    System.out.println(a < b); // false
    System.out.println(a <= b); // false
    System.out.println(a == b); // true
    System.out.println(a != b); // false
  }
}
```
5. 关系运算符的运算原理：比较的是两个变量之间保存的值的大小关系。

## 逻辑运算符
1. 逻辑运算符类型

|逻辑运算符类型|含义|
|:--:|:--:|
| & |逻辑与|
| &#124; |逻辑或|
| ! |逻辑非|
| ^ |逻辑异或|
| && |短路与|

2. 注意
```
|| 表示短路或
```
3. 逻辑运算符要求两边的算子都是布尔类型，并且逻辑运算符最终的运算结果也是一个布尔类型。
4. 逻辑与：两边的算子都是 true ，结果才是 true ；逻辑或：两边的算子只要有一个是 true ，结果就是 true ；逻辑非：取反，!false 就是 true ， !true 就是假，这是一个单目运算符；逻辑异或：两边的算子只要不一样，结果就是 true 。
5. 短路与和逻辑与最终的运算结果是相同的，只不过短路与存在短路现象。
6. 短路或和逻辑或最终的运算结果是相同的，只不过短路或存在短路现象。
7. 举例
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:18:32
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:19:28
*/


public class OperatorTest03
{
  public static void main(String[] args){

    //运算符优先级不确定，加小括号。
    System.out.println(5 > 3 & 5 > 2);// true
    System.out.println(5 > 3 & 5 > 6);// false
    System.out.println(5 > 3 | 5 > 6);// true

    System.out.println(true & true);// true
    System.out.println(true & false);// false
    System.out.println(false & true);// false
    System.out.println(false & false);// false

    System.out.println(true | false);// true
    System.out.println(false | false);// false


    System.out.println(!false);// true
    System.out.println(!true);// false

    System.out.println(true ^ false);// true
    System.out.println(false ^ false);// false
    System.out.println(true ^ true);// false

    /*
    //逻辑与和短路与
    int x = 10;
    int y = 8;
    //逻辑与
    System.out.println(x < y & ++x < y);// false
    System.out.println(x);// 11
    */

    //逻辑与和短路与
    int x = 10;
    int y = 8;
    //短路与
    // x < y 结果是 false ，整个表达式结果已经确定是 false 。
    //后面的表达式没有再执行，这种现象被称为短路现象。
    //短路与才会有短路现象，逻辑与是不会存在短路现象的
    System.out.println(x < y && ++x < y);// false
    System.out.println(x);// 10

    /*
      从某个角度看，短路与更智能。由于后面表达式可能不执行，所以执行效率较高。这种方式在实际的开发中使用较多，短路与比逻辑与使用得多，短路与更常用。
      但是，在某些特殊的业务逻辑当中，要求运算符两边算子必须全部执行，此时必须使用逻辑与，不能使用短路与，使用短路与就可能导致右边的表达式不执行。
    */

    /*
      什么情况下发生短路或？
        * 第一个表达式执行结果是 true ，会发生短路或。
      什么情况下发生短路与？
        * 第一个表达式执行结果是 false ，会发生短路与。
    */
  }
}
```

## 赋值类运算符
1. 赋值运算符包括两种：基本的赋值运算符和扩展的赋值运算符。
2. 基本的赋值运算符包括：= 。
3. 扩展的赋值运算符包括：+=、-=、*=、/=、%= 。
4. 赋值类的运算符优先级：先执行等号右边的表达式，将执行结果赋值给左边的变量。
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:21:00
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:22:16
*/



public class OperatorTest04
{
  public static void main(String[] args){

    //基本的赋值运算符
    int i = 10;
    System.out.println(i);// 10
    i = i + 5;
    System.out.println(i);// 15

    //扩展的赋值运算符【+= 运算符可以翻译为“追加/累加”】
    i +=  5;//等同于：i = i + 5;
    System.out.println(i);// 20

    i -= 5;//等同于：i = i - 5;
    System.out.println(i);// 15


    i *= 2;//等同于：i = i * 2;
    System.out.println(i);// 30

    i /= 4;//等同于：i = i / 4;
    System.out.println(i);// 7


    i %= 2;//等同于：i = i % 2;
    System.out.println(i);// 1


    //10 没有超出 byte 取值范围，可以直接赋值
    byte b = 10;
    //b = 15;// 可以，编译通过，15 没有超出byte取值范围
    //编译错误
    //编译器只检查语法，不运行程序，编译器发现 b + 5 的类型是 int 类型，b 变量的数据类型是 byte 
    //大容量向小容量转换需要加强制类型转换符，所以以下程序编译报错。
    //b = b + 5;


    //纠正错误
    b = (byte)(b + 5);
    System.out.println(b);// 15


    byte x = 10;
    x += 5;//等同于：x = (byte)(b + 5)，其实并不等同于：x = x + 5
    System.out.println(x);// 15


    byte z = 0;
    z += 128;// 等同于：z = (byte)(z + 128);
    System.out.println(z);// -128


    z += 10000;// 等同于：z = (byte)(z + 10000);
    System.out.println(z);// -112
  }
}
```
5. 注意
```
byte i = 10;
i += 5; // 等同于：i = (byte)(i + 5);
```
6. 重要结论：扩展类的赋值运算符不改变运算结果类型，假设最初这个变量的类型是 byte 类型，无论怎么进行追加或追减，最终该变量的数据类型还是 byte 类型。

## 字符串的连接运算符
1. `+` 运算符在 java 语言当中有两个作用：加法运算和字符串的连接运算。
2. 当 + 运算符两边的数据都是数字的话，一定是进行加法运算。
3. 当 + 运算符两边的数据只要有一个数据是字符串，一定会进行字符串连接运算，并且连接运算之后的结果还是一个字符串类型。
4. 在一个表达式当中可以出现多个 + ，在没有添加小括号的前提之下，遵循自左向右的顺序依次运算。
5. 举例
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:23:38
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:24:20
*/



public class OperatorTest05
{
  public static void main(String[] args){

    System.out.println(10 + 20);// 30 这里的加号是求和
    System.out.println(10 + 20 + 30);// 60 这里的加号也是求和
    System.out.println(10 + 20);// "3030" 自左向右的顺序依次运算，第一个加号是求和，第二个加号是字符串连接符。
    System.out.println(10 + (20 + "30")); // "102030" 


    int a = 10;
    int b = 20;

    //要求在控制台上输出 10 + 20 = 30
    System.out.println("10 + 20 = 30");


    //注意：要求以动态的方式输出
    System.out.println("10 + 20 = " + a + b);// "10 + 20 = 1020"
    System.out.println("10 + 20 = " + (a + b));
    System.out.println( a + "+" + b + " = " + (a + b));

    a = 200;
    b = 300;
    System.out.println( a + "+" + b + " = " + (a + b));// 500

    //引用类型 String
    // String 是 SUN 在 JAVASE 当中提供的字符串类型
    // String.class 字节码文件


    // int 是基本数据类型，i 是变量名，10 是 int 类型的字面值
    int i =10;

    // String 是引用数据类型，s 是变量名，"abc" 是 String 类型的字面值
    String s = "abc";

    //编译报错，类型不兼容
    //String ss = 10;

    //定义一个 String 类型的变量，起名 username，赋值 "zhangsan"
    String username = "zhangsan";
    System.out.println("登录成功，欢迎 username 回来");
    System.out.println("登录成功，欢迎" + username + "回来");

    username = "jack";
    System.out.println("登录成功，欢迎" + username + "回来");
  }
}
```

## 三元运算符
1. 三元运算符语法规则
```
布尔表达式 ？表达式1 : 表达式2 
```
2. 三元运算符的执行原理
```
1. 当布尔表达式的结果是 true 的时候，选择表达式1作为整个表达式的执行结果。
2. 当布尔表达式的结果是 false 的时候，选择表达式2作为整个表达式的执行结果。
```
3. 举例
```
/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:25:23
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:26:04
*/


public class OperatorTest06
{
  public static void main(String[] args){
    //编译错误：不是一个完整的 java 语句
    //10;

    //编译错误: 不是一个完整的 java 语句

    //布尔类型的变量
    boolean sex = false;

    //编译报错，因为它不是一个完整的 java 语句
    //sex ? '男' : '女';


    char c = sex ? '男' : '女';
    System.out.println(c);


    sex = true;
    c = (sex ? '男' : '女');
    System.out.println(c);


    //语法错误，编译报错，结果可能是 String ，也可能是 char ，但是前面不能用 char 接收数据，类型不兼容。
    //char c1 = sex ? "男" : '女';


    /*
    //编译错误，类型不兼容
    sex = false;
    char c1 = sex ? "男" : '女';
    */

    System.out.println(10);
    System.out.println("10");
    System.out.println('1');

    //可以
    System.out.println(sex ? '男' : "女");


    String s = sex ? "男的" : "女的";
    System.out.println(s);

  }
}
```
