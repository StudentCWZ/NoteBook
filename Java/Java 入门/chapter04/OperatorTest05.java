/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:23:38
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:24:20
*/



public class OperatorTest05
{
  public static void main(String[] args){

    System.out.println(10 + 20);//30 这里的加号是求和
    System.out.println(10 + 20 + 30);//60 这里的加号也是求和
    System.out.println(10 + 20);//"3030" 自左向右的顺序依次运算，第一个加号是求和，第二个加号是字符串连接符。
    System.out.println(10 + (20 + "30"));//"102030"


    int a = 10;
    int b = 20;

    //要求在控制台上输出“10 + 20 = 30”
    System.out.println("10 + 20 = 30");


    //注意：要求以动态的方式输出
    System.out.println("10 + 20 = " + a + b);//"10 + 20 = 1020"
    System.out.println("10 + 20 = " + (a + b));
    System.out.println( a + "+" + b + " = " + (a + b));

    a = 200;
    b = 300;
    System.out.println( a + "+" + b + " = " + (a + b));//500

    //引用类型String
    //String是SUN在JAVASE当中提供的字符串类型
    //String.class字节码文件


    //int是基本数据类型，i是变量名，10是int类型的字面值
    int i =10;

    //String是引用数据类型，s是变量名，"abc"是String类型的字面值
    String s = "abc";

    //编译报错，类型不兼容
    //String ss = 10;

    //定义一个String类型的变量，起名username，赋值"zhangsan"
    String username = "zhangsan";
    System.out.println("登录成功，欢迎username回来");
    System.out.println("登录成功，欢迎" + username +"回来");

    username = "jack";
    System.out.println("登录成功，欢迎" + username +"回来");
  }
}
