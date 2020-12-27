package mcw.test.leetcode.niuke;

import java.util.HashSet;

/**
 * @author mcw 2020\3\10 0010-21:01
 *
 * 给定一个无序的整数类型数组，求最长的连续元素序列的长度。时间复杂度为 O(n)
 * 例： 输入 [100,4,200,1,2,3]  最长连续序列为[1,2,3,4] 返回结果 4
 */
public class Test23 {
    public int longestConsecutive(int[] nums){
        HashSet<Integer> list=new HashSet<>();
        for (int num : nums) {
            list.add(num);
        }

        int res=0;
        for(int num:list){
            if(!list.contains(num-1)){ //找出当前集合中连续序列中最小的元素
                int index=1;
                while(list.contains(num+index)){ //不停累加，直至找到当前元素的最大连续数
                    index++;
                }
                res=Math.max(index,res);
            }
        }
        return res;
    }

    /**
     * 记录遍历中的当前值，向该值两边搜索，如果存在集合中就删除，最后计算连续数的长度
     * @param nums
     * @return
     */
    public int longestConsecutive1(int[] nums){
        HashSet<Integer> set=new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int max=0;
        for (int num : nums) {
            while(set.remove(num)){
                int left=num,right=num;
                while(set.remove(left-1)) left--;
                while(set.remove(right+1)) right++;
                max=Math.max(max,right-left+1);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int i = new Test23().longestConsecutive(new int[]{ 4,  2, 3, 6,5});
        System.out.println(i);
    }
}
