package mcw.test.offer;

/**
 * @author mcw 2020\1\23 0023-21:54
 *
 * 将一个字符串转换成一个整数，要求不能使用字符串转换成整数的函数库。
 * 数值为0或者字符串不是一个合法的数字则返回0
 */
public class Test49 {

    public static int StrToInt(String str) {
        if (str.length() == 0) {
            return 0;
        } else if (str.length() == 1 && (str.charAt(0) == '-' || str.charAt(0) == '+')) {
            return 0;
        } else {
            int flag = 0;//1是整数，2是负数
            int limit = -Integer.MAX_VALUE;
            boolean error = false;
            char[] chars = str.toCharArray();
            int i = 0;
            if (chars[0] == '-') {
                i++;
                flag = 2;
                limit = Integer.MIN_VALUE;
            } else if (chars[0] == '+') {
                i++;
                flag = 1;
            }
            int result = 0;
            for (int j = i; j < chars.length; j++) {
                if (chars[j] >= '0' && chars[j] <= '9') {
                    if (limit + (chars[j] - '0') > result * 10) {
                        error = true;
                        break;
                    }
                    result = result * 10 - (chars[j] - '0');
                } else {
                    error = true;
                    break;
                }
            }
            if (!error) {
                if (flag != 2) {
                    result = result * (-1);
                }
                return result;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(StrToInt("46a3131681"));
    }
}
