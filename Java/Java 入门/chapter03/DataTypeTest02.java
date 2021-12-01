/*
* @Author: StudentCWZ
* @Date:   2020-06-07 09:03:57
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-07 09:04:38
*/



public class DataTypeTest02
{
  public static void main(String[] args){
    //定义一个char类型的变量，起名c，同时赋值字符'a'
    char c = 'a';
    System.out.println(c);

    //一个中文占用2个字节，char类型正好是两个字节
    //所以java中的char类型变量可以存储一个中文字符
    char x = '国';
    System.out.println(x);

    //编译错误
    //ab是字符串，不能使用单引号括起来
    //char y = 'ab';

    //"a"是字符串类型
    //k变量是char类型
    //类型不兼容，编译错误
    //char k = "a";

    //声明
    char e;

    //赋值
    e = 'e';
    System.out.println(e);

    //再次赋值
    e = 'f';
    System.out.println(e);
  }
}
