/*
* @Author: StudentCWZ
* @Date:   2020-06-09 10:04:47
* @Last Modified by:   StudentCWZ
* @Last Modified time: 2020-06-09 10:06:04
*/

public class DataTypeTest07
{
  public static void main(String[] args){

    //编译错误，不兼容的类型
    //boolean flag = 1;

    boolean loginSuccess = true;
    //if语句以后讲【条件控制语句】
    if(loginSuccess){
      System.out.println("恭喜你，登陆成功");
    }else{
       System.out.println("对不起，用户名不存在或者密码错误");
    }
  }
}

