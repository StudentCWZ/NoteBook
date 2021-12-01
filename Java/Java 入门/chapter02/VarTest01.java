/*
* @Author: StudentCWZ
* @Date:   2020-06-07 08:46:09
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-07 08:47:21
*/

public class VarTest01
{
  public static void main(String[] args){
    //声明一个int类型的变量，起名i
    int i;
    //编译报错：变量i并没有初始化
    //System.out.println(i);

    //给i变量赋值，i变量在这里完成初始化，内存开辟。
    i = 100;
    System.out.println(i);

    //i再次重新赋值
    i = 200;
    System.out.println(i);

    //一行上可以同时声明多个变量
    //a和b尚未初始化，c赋值300
    int a,b,c = 300;

    //编译错误
    //System.out.println(a);
    //编译错误
    //System.out.println(b);
    System.out.println(c);

    a = 0;
    b = 1;
    System.out.println(a);
    System.out.println(b);
  }
}

