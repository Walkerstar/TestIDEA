package mcw.test.offer;

import java.util.LinkedList;

/**
 * @author mcw 2020\1\24 0024-21:40
 * <p>
 * 实现一个函数用来判断字符串是否表示数值（包括整数和小数）
 * 例如： "+100","-45","5e2"
 */
public class Test53 {
    private static int index = 0;

    public static boolean isNumberic(char[] str) {
        if (str.length < 1)
            return false;
        //1.扫描开头整数(带符号或不带符号)
        boolean flag = scanInteger(str);
        if (index < str.length && str[index] == '.') {
            //2.有小数点存在
            index++;
            flag = scanUnsignedInteger(str) || flag; //继续扫描无符号整数
        }
        if (index < str.length && (str[index] == 'E' || str[index] == 'e')) {
            //3.有E,e?  有的话后边必须有数字
            index++;
            flag = flag && scanInteger(str);
        }
        return flag && index == str.length;
    }

    private static boolean scanInteger(char[] str) {
        if (index < str.length && (str[index] == '+' || str[index] == '-'))
            index++; //匹配'+'或者'-'
        return scanUnsignedInteger(str);  //如果没有匹配，则进行无符号整数匹配
    }

    private static boolean scanUnsignedInteger(char[] str) {
        int start = index;
        while (index < str.length && str[index] >= '0' && str[index] <= '9')
            index++;
        return start < index; //是否存在整数
    }


    public static void main(String[] args) {
        char[] chars = "+10e+4".toCharArray();
        System.out.println(isNumberic(chars));
    }
}
