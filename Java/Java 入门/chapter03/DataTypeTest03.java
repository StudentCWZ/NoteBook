/*
* @Author: StudentCWZ
* @Date:   2020-06-07 09:05:58
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-07 09:07:42
*/


public class DataTypeTest03
{
  public static void main(String[] args){

    //普通的n字符
    char c1 = 'n';
    System.out.println(c1);

    //依照目前所学知识，以下程序无法编译通过，因为显然是一个字符串，不能用单引号括起来
    //但是经过编译发现，发现编译通过，这说明以下并不是一个字符串，而是一个字符。
    //这是一个换行符，属于char类型的数据。
    //反斜杠在java语言当中具有转义功能。
    char c2 = '\n';

    /*
    System.out.println("Hello");
    System.out.println("World!");
    */

    //System.outprintln();和System.outprint();的区别：println表示输出之后换行，print表示输出，但是不换行。
    /*
    System.out.print("Hello");
    System.out.println("World!");
    */

    System.out.print("A");
    System.out.print(c2);
    System.out.println("B");

    //普通的t字符
    char x = 't';
    System.out.println(x);

    //制表符Tab
    //强调制表符和空格不同，它们ASCII码不一样，体现在键盘两个不同的按键。
    char y = '\t';
    System.out.print("A");
    System.out.print(y);
    System.out.println("B");

    //要求在控制台上输出“反斜杠字符”。
    //反斜杠将后面的单引号转义成不具备特殊含义的普通单引号字符，左边的单引号缺少了结束的单引号字符，编译报错。
    /*
    char k = '\';
    System.outprintln(k);
    */

    /*
      //解释：第一个反斜杠具有转义功能，将后面的反斜杠转义为普通的反斜杠字符。
      //结论：在java当中两个反斜杠代表一个普通的反斜杠。
    */
    char k = '\\';
    System.out.println(k);


    //在控制台上输出一个普通的单引号字符
    //java不允许这样编写程序，编译报错
    //char a = '';

    //以下编译报错：第一个单引号和第二个单引号配对，最后的单引号找不到另一半。
    //char a = ''';
    //System.outprintln(a);

    //反斜杠具有转义功能，将第二个单引号转换成普通的单引号字符，第一个单引号和最后单引号配对。
    char a = '\'';
    System.out.println(a);


    char f = '"';
    System.out.println(f);


    System.out.println("HelloWorld!");
    System.out.println("“HelloWorld!”");
    //编译错误
    //System.outprintln(""HelloWorld!"");
    //纠正
    System.out.println("\"HelloWorld!\"");


    char m = '中';
    System.out.println(m);

    //JDK自带的native2ascii.exe命令，可以将文字转换成unicode编码形式。
    //怎么使用这个命令：在命令行输入native2ascii，回车，然后输入文字回车即可得到unicode编码。

    char n ='\u4e2d';//"中"对应的unicode编码是4e2d
    System.out.println(n);

    //编译错误
    //char g = '4e2d'
    //编译错误
    //char g = 'u4e2d'
    //编译通过：反斜杠u联合起来后面一串数字是某个文字的unicode编码。
    char g = '\u4e2d';
    System.out.println(g);

    //char类型的默认值
    char c10 = '\u0000';
    System.out.println(c10);
  }
}
