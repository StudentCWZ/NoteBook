/*
* @Author: StudentCWZ
* @Date:   2020-06-07 08:54:36
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-07 08:55:03
*/


public class VarTest03
{
  //成员变量
  int k = 200;

  //主方法：入口
  public static void main(String[] args){

    //i变量就是局部变量
    int i = 10;
    //java遵循就近原则
    System.out.println(i);
  }

  //成员变量
  int i = 100;

  //doSome方法
  public static void doSome(){
    //局部变量
    int i = 90;
  }
}
