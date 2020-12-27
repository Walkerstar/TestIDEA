package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。
 * 返回一个表示每个字符串片段的长度的列表。
 *
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * @author mcw 2020/10/22 12:15
 */
public class leetCode763 {
    public List<Integer> partitionLabels(String s){
        int[] last = new int[26];
        int length=s.length();
        //记录每一个字母最后出现的位置
        for (int i = 0; i < length; i++) {
            last[s.charAt(i)-'a']=i;
        }
        List<Integer> partition = new ArrayList<>();
        int start=0;
        int end=0;
        //遍历字符串，对于当前访问的字母，其当前片段的结束下标一定不会小于该字母最后一次出现的下标位置 end，
        //所以当访问到下标 end 时，当前片段访问结束，
        for (int i = 0; i < length; i++) {
            end= Math.max(end,last[s.charAt(i)-'a']);
            if (i==end) {
                partition.add(end-start+1);
                start=end+1;
            }
        }
        return partition;
    }

    /**
     *如果要从i开始分割，结尾最少要到达i对应字符最后出现的位置，然后遍历中间的字符，如中间有的字符最后位置比之前定义的结尾要长，
     *这时把结尾更新（加长），接着遍历。只要遍历到尾部，那么就找到本次要分割的长度，然后索引加1继续以上的步骤。
     */
    public List<Integer> partitionLabels1(String s) {
        List<Integer> res = new ArrayList<>();
        if (s.length() == 0) {
            return res;
        }
        int[] last = new int[26];
        char[] sarr = s.toCharArray();
        for (int i = 0; i < sarr.length; i++) {
            last[sarr[i] - 'a'] = i;
        }
        int left;
        int right = -1;
        while (right < s.length() - 1) {
            left = right + 1;
            right = last[sarr[left] - 'a'];
            int index = left + 1;
            while (index < right) {
                if (last[sarr[index] - 'a'] > right) {
                    right = last[sarr[index] - 'a'];
                }
                index++;
            }
            res.add(right - left + 1);
        }
        return res;
    }
}
