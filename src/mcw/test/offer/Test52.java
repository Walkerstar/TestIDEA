package mcw.test.offer;

/**
 * @author mcw 2020\1\24 0024-21:02
 *
 * 模拟正则表达式
 */
public class Test52 {

    public static boolean match(char[] str, char[] pattern) {
        return matchHelper(str, pattern, 0, 0);
    }

    private static boolean matchHelper(char[] str, char[] pattern, int i, int j) {
        if (i == str.length && j == pattern.length)
            return true;
        else if (j >= pattern.length)
            return false;

        //模式串的下一个字符是'*'
        boolean isNextStar = (j + 1 < pattern.length && pattern[j + 1] == '*');

        if (isNextStar) {
            //模式匹配串的下一个字符是'*'
            if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                return matchHelper(str, pattern, i, j + 2)  //只匹配成功1个
                        ||
                        matchHelper(str, pattern, i + 1, j); //匹配成功一个或可能还有多个，str指针后移
            } else {
                return matchHelper(str, pattern, i, j + 2); //没有匹配成功'X*'匹配了0个
            }
        } else {
            //模式匹配串的下一个字符 不是 '*'
            if (i < str.length && (str[i] == pattern[j] || pattern[j] == '.')) {
                return matchHelper(str, pattern, i + 1, j + 1);
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        char[] chars = "aaa".toCharArray();
        char[] chars1 = "a.a".toCharArray();
        System.out.println(match(chars, chars1));
    }
}
