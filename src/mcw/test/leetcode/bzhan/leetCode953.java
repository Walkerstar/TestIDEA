package mcw.test.leetcode.bzhan;

/**
 * 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * <p>
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 * @author mcw 2022/5/17 17:43
 */
public class leetCode953 {

    public boolean isAlienSorted(String[] words, String order) {
        /**
         * 首先将给定的 order 转化为字典序索引 index，index[i] 表示字符 i 在字母表 order 的排序索引，
         * index[i] > index[j] 即表示字符 i 在字母表中的字典序比字符 j 的字典序大，
         * index[i] < index[j] 即表示字符 i 在字母表中的字典序比字符 j 的字典序小。
         */
        int[] index = new int[26];

        for (int i = 0; i < order.length(); i++) {
            index[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            boolean valid = false;
            for (int j = 0; j < words[i - 1].length() && j < words[i].length(); j++) {
                int prev = index[words[i - 1].charAt(j) - 'a'];
                int curr = index[words[i].charAt(j) - 'a'];
                if (prev < curr) {
                    valid = true;
                    break;
                } else if (prev > curr) {
                    return false;
                }
            }
            if (!valid) {
                //比较两个字符串的长度
                if (words[i - 1].length() > words[i].length()) {
                    return false;
                }
            }
        }
        return true;
    }
}
