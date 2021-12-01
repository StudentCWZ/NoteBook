/*
* @Author: StudentCWZ
* @Date:   2020-06-09 10:13:50
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 10:14:33
*/


public class OperatorTest01
{
  public static void main(String[] args){

    int i = 10;
    int j = 3;
    System.out.println(i + j);//13
    System.out.println(i - j);//7
    System.out.println(i * j);//30
    System.out.println(i / j);//3
    System.out.println(i % j);//1

    //以下以++为例，--运算符自学！
    //关于++运算符【自加1】
    int k = 10;

    //++运算符可以出现在变量后面【单目运算符】
    //k ++;

    System.out.println(k);//11

    int y = 10;

    //++运算符可以出现在变量前面【单目运算符】
    ++ y;

    System.out.println(y);//11

    //小结：
    //++运算符可以出现在变量前，也可以出现在变量后，无论是变量前还是变量后，只要++运算结束，该变量中的值一定会自加1。


    //++ 出现在变量后
    //规则：先做赋值运算，再对变量中保存的值进行自加1。
    int a = 100;
    int b = a ++;
    System.out.println(a);//101
    System.out.println(b);//100

    //++ 出现在变量前
    //规则：先进行自加1运算，然后再进行赋值操作。
    int m = 20;
    int n = ++ m;
    System.out.println(m);//21
    System.out.println(n);//21


    int mm = 500;
    System.out.println(mm);

    int e = 100;
    System.out.println(e ++);//100
    System.out.println(e);//101


    int s = 100;
    System.out.println(s++);//101
    System.out.println(s);//101

    System.out.println(--s);//100
    System.out.println(s++);//100
    System.out.println(s--);//101
    System.out.println(s--);//100
    System.out.println(s--);//99
    System.out.println(s);//98

  }
}

