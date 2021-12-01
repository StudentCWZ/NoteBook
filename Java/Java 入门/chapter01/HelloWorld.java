/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:41:17
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-10 11:39:05
*/


//public表示公开的
//class表示定义一个类Colorcoder
//HelloWorld表示一个类名
public class HelloWorld{//定义一个公开的类，起名HelloWorld
  //类体【记住】
  //类体中不允许直接编写java语句【除声明变量之外】

  /*
    public表示公开的
    static表示静态的p
    void表示空
    main表示方法名是main
    (String[] args)是一个main方法的形式参数列表
    需要记住的是：以下的方法是一个程序的主方法，是程序的执行入口
  */
  public static void main(String[] args){//定义一个公开的静态的主方法
    //方法体

    //java语句【java语句以“;”终止，分号必须是半角分号】
    //先记住：以下这样代码的作用是向控制台输出一段消息
    //以下双引号必须是半角的双引号【是java语法的一部分】
    //java中所有的字符串都使用双引号括起来
    System.out.println("Hello world!");

    //再向控制台输出消息
    System.out.println("Hello jackson!");

    //输出中文
    System.out.println("你好，杰克!");

    //输出中文【提醒：以下程序员两边双引号是“全角的”，这里的双引号不是java语法的一部分，这里全角的双引号只是一个普通的字符串】
    System.out.println("我是个“程序员”");
  }
}
