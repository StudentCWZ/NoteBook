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
    System.out.println(i);//10
    i = i + 5;
    System.out.println(i);//15

    //扩展的赋值运算符【+= 运算符可以翻译为“追加/累加”】
    i +=  5;//等同于：i = i + 5;
    System.out.println(i);//20

    i -= 5;//等同于：i = i - 5;
    System.out.println(i);//15


    i *= 2;//等同于：i = i * 2;
    System.out.println(i);//30

    i /= 4;//等同于：i = i / 4;
    System.out.println(i);//7


    i %= 2;//等同于：i = i % 2;
    System.out.println(i);//1


    //10没有超出byte取值范围，可以直接赋值
    byte b = 10;
    //b = 15;//可以，编译通过，15没有超出byte取值范围
    //编译错误
    //编译器只检查语法，不运行程序，编译器发现b+5的类型是int类型，b变量的数据类型是byte
    //大容量向小容量转换需要加强制类型转换符，所以以下程序编译报错。
    //b = b + 5;


    //纠正错误
    b = (byte)(b + 5);
    System.out.println(b);//15


    byte x = 10;
    x += 5;//等同于：x = (byte)(b + 5)，其实并不等同于：x = x + 5
    System.out.println(x);//15


    byte z = 0;
    z += 128;//等同于：z = (byte)(z + 128);
    System.out.println(z);//-128


    z += 10000;//等同于：z = (byte)(z + 10000);
    System.out.println(z);//-112

  }
}

