/*
* @Author: StudentCWZ
* @Date:   2020-06-09 10:10:06
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 10:10:18
*/

public class DataTypeTest08
{
  public static void main(String[] args){

    //出现错误，1000超出了byte的范围
    //byte a = 1000;
    //正确，因为20没有超出byte的范围
    //所以赋值
    byte a = 20;
    //变量名不能重名
    //short a = 1000;
    //正确，因为数值1000没有超出short类型的范围
    //所以赋值正确
    short b = 1000;
    //正确，因为默认就是int，并且没有超出int类型
    int c = 1000;
    //正确，可以自动转换
    long d = c;
    //错误，出现精度丢失问题，大类型-->小类型会出现问题
    //int e = d;
    //将long强制转换成int类型
    //因为值1000，没有超出int范围，所以转换是正确的。
    int e = (int) d;
    //因为java中运算会转成最大类型。
    //而10和3默认值为int，所以运算后最大的类型也是int
    //所以是正确的
    int f = 10/3;//3
    //声明10为long类型
    long g = 10;
    //出现错误，多个数值在运算过程中，会转换成容量最大的类型
    //以下示例最大的类型为double，而h为int，所以就会出现大类型(long)到小类型(int)的转换，将会出现精度丢失的问题。
    //int h = g/3；
    //可以强制转换，因为运算结果没有超出int范围
    //int h = (int) g/3;
    //可以采用long类型来接收运算结果
    //long h = g/3;
    //出现精度损失问题，以下问题主要是优先级的问题
    //将g转换成int，然后又将int类型的g转换成byte，最后byte类型的g和3运算，那么它的运算结果类型就是int，所以int赋值给byte就出现了精度损失问题。
    //byte h = (byte)(int)g/3;
    //正确
    //byte h = (byte)(int)(g/3);
    //不能转换，因为优先级的问题
    //byte h = (byte)g/3;
    //可以转换，因为运算结果没有超出byte范围
    //byte h = (byte)(g/3);
    //可以转换，因为运算结果没有超出short范围
    short h = (short)(g/3);
    short i = 10;
    byte j = 5;
    //错误，short和byte运算，首先会先转换成int再运算
    //所以运算结果为int，int赋值给short就会出现精度丢失问题
    //short k = i + j;
    //可以将运行结果强制转换成short
    //short k = short(i + j);
    //因为运算结果为int，所以可以采用int类型接收
    int k = i + j;
    char l = 'a';
    System.out.println(l);//a
    //输出结果为97，也就是a的ascii值
    System.out.println((byte)l);//97
    int m = l + 100;
    //输出结果为197，取得a的ascii码值，然后与100进行相加运算
    System.out.println(m);
  }
}
