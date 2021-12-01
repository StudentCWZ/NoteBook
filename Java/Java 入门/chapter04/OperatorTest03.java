/*
* @Author: StudentCWZ
* @Date:   2020-06-09 13:18:32
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 13:19:28
*/


public class OperatorTest03
{
  public static void main(String[] args){

    //运算符优先级不确定，加小括号。
    System.out.println(5 > 3 & 5 > 2);//true
    System.out.println(5 > 3 & 5 > 6);//false
    System.out.println(5 > 3 | 5 > 6);//true

    System.out.println(true & true);//true
    System.out.println(true & false);//false
    System.out.println(false & true);//false
    System.out.println(false & false);//false

    System.out.println(true | false);//true
    System.out.println(false | false);//false


    System.out.println(!false);//true
    System.out.println(!true);//false

    System.out.println(true ^ false);//true
    System.out.println(false ^ false);//false
    System.out.println(true ^ true);//false

    /*
    //逻辑与和短路与
    int x = 10;
    int y = 8;
    //逻辑与
    System.out.println(x < y & ++x < y);//false
    System.out.println(x);//11
    */

    //逻辑与和短路与
    int x = 10;
    int y = 8;
    //短路与
    //x < y结果是false，整个表达式结果已经确定是false。
    //后面的表达式没有再执行，这种现象被称为短路现象。
    //短路与才会有短路现象，逻辑与是不会存在短路现象的
    System.out.println(x < y && ++x < y);//false
    System.out.println(x);//10

    /*
      从某个角度看，短路与更智能。由于后面表达式可能不执行，所以执行效率较高。这种方式在实际的开发中使用较多，短路与比逻辑与使用得多，短路与更常用。
      但是，在某些特殊的业务逻辑当中，要求运算符两边算子必须全部执行，此时必须使用逻辑与，不能使用短路与，使用短路与就可能导致右边的表达式不执行。
    */

    /*
      什么情况下发生短路或？
        * 第一个表达式执行结果是true，会发生短路或。
      什么情况下发生短路与？
        * 第一个表达式执行结果是false，会发生短路与。
    */
  }
}
