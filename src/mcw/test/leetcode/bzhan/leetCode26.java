package mcw.test.leetcode.bzhan;

/**
 * remove duplicates from sorted array
 * 删除重复的元素，返回剩余的元素个数
 * @author mcw 2020\5\11 0011-15:13
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

    /**
     * 双指针
     */
    public int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int fast = 1, slow = 1;
        while (fast < n) {
            if (nums[fast] != nums[fast - 1]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }
}
