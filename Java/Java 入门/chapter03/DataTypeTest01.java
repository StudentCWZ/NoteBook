/*
* @Author: StudentCWZ
* @Date:   2020-06-07 08:56:27
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-07 09:03:03
*/


public class DataTypeTest01
{
  //这里static必须加
  static int k = 1000;

  //变量还是遵循这个语法：必须先声明，再赋值，才能访问。
  //成员变量没有手动赋值，系统会默认赋值。【局部变量不会】
  static int f; //成员变量

  public static void main(String[] args){
    /*
    int i;//局部变量
    System.outprintln(i);
    */
    System.out.println(k);
    System.out.println(f);//0

  }
}
