package mcw.test.offer;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author mcw 2020\1\25 0025-16:06
 *
 * 请实现一个函数用来找出字符流中的第一个只出现一次的字符。
 */
public class Test54 {


    public static char FirstAppearingOnce(String string) {
        Map<Character, Integer> map = new LinkedHashMap<>();

        char[] chars = string.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        char c = '#';
        for (Character key : map.keySet()) {
            if (map.get(key) == 1) {
                c = key;
                break;
            }
        }

        return c;
    }


    public static void main(String[] args) {
        System.out.println(FirstAppearingOnce("google"));
    }
}
