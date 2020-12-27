package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\14 0014-15:21
 * Count and Say
 * 1,11,21,1211,111221....
 * 1   is read off as "one 1" or 11
 * 11  is read off as "two 1" or 21
 * 21  is read off as "one 2" ,then "one 1" or 1211
 */
public class leetCode38 {
    public static String countAndSay(int n) {
        if (n <= 0) return "";
        String str = "1";
        for (int i = 1; i < n; i++) {
            int count = 0;
            char prev = '.';
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == prev || prev == '.') {
                    count++;
                } else {
                    sb.append(count).append(prev);
                    count = 1;
                }
                prev = str.charAt(idx);
            }
            sb.append(count).append(prev);
            str = sb.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }
}
