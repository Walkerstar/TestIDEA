package mcw.test.leetcode.bzhan;

/**
 * 给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 * 在比较时，字母是依序循环出现的
 *
 * @author MCW 2022/4/3
 */
public class leetCode744 {

    public char nextGreatestLetter(char[] letters, char target) {
        int length = letters.length;
        char nextGreater = letters[0];
        for (int i = 0; i < length; i++) {
            if (letters[i] > target) {
                nextGreater = letters[i];
                break;
            }
        }
        return nextGreater;
    }

    /**
     * 二分查找
     */
    public char nextGreatestLetter2(char[] letters, char target) {
        int length = letters.length;
        //当目标字母大于或等于列表中的最后一个字母时，答案是列表的首个字母。
        if (target >= letters[length - 1]) {
            return letters[0];
        }
        int low = 0, high = length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (letters[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return letters[low];
    }
}