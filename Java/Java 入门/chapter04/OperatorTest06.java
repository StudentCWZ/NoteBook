/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:25:23
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:26:04
*/


public class OperatorTest06
{
  public static void main(String[] args){
    //编译错误：不是一个完整的java语句
    //10;

    //编译错误: 不是一个完整的java语句

    //布尔类型的变量
    boolean sex = false;

    //编译报错，因为它不是一个完整的java语句
    //sex ? '男' : '女';


    char c = sex ? '男' : '女';
    System.out.println(c);


    sex = true;
    c = (sex ? '男' : '女');
    System.out.println(c);


    //语法错误，编译报错，结果可能是String，也可能是char，但是前面不能用char接收数据，类型不兼容。
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
