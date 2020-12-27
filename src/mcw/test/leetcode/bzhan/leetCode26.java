package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\11 0011-15:13
 * remove duplicates from sorted array
 * 删除重复的元素，返回剩余的元素个数
 */
public class leetCode26 {
    public static int removeDuplicates(int[] nums){
        int slow=1;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i-1]!=nums[i]){
                nums[slow++]=nums[i];
            }
        }
        return slow;
    }
}
