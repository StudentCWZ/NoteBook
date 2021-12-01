/*
* @Author: StudentCWZ
* @Date:   2020-06-09 10:01:09
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 10:02:30
*/


public class DataTypeTest06
{
  public static void main(String[] args){

    //3.0是double类型的字面值
    //d是double类型的变量
    //不存在类型转换
    double d = 3.0;
    System.out.println(d);


    //5.1是double类型的字面值
    //f是float类型的变量
    //大容量转换成小容量需要加强类型转换符，所以以下程序编译错误。
    //float f = 5.1;


    //解决方案：
    //第一种方式：强制类型转换
    //float f = (float)5.1;

    //第二种方式：没有类型转换
    float f = 5.1f;

  }
}
