package mcw.test.leetcode.bzhan;

/**
 * 删除指定元素，返回剩下的有效数字的个数
 * @author mcw 2020\5\4 0004-15:54
 */
public class leetCode27 {
    public static int removeElement(int[] nums,int val){
        if(nums==null) return 0;
        int slow=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=val){
                nums[slow++]=nums[i];
            }
        }
        return slow;
    }

    /**
     * 优化双指针
     * 
     * 如果左指针 left 指向的元素等于 val，此时将右指针 right 指向的元素复制到左指针 left 的位置，然后右指针 right 左移一位。
     * 如果赋值过来的元素恰好也等于 val，可以继续把右指针 right 指向的元素的值赋值过来（左指针 left 指向的等于 val 的元素的
     * 位置继续被覆盖），直到左指针指向的元素的值不等于 val 为止。
     *
     * 当左指针 left 和右指针 right 重合的时候，左右指针遍历完数组中所有的元素。
     *
     */
    public int removeElement1(int[] nums,int val){
        int left=0;
        int right= nums.length;
        while (left<right){
            if (nums[left]==val){
                nums[left]=nums[right-1];
                right--;
            }else {
                left++;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] ints = {2, 2, 3, 4, 5, 5, 7, 7};
        System.out.println(removeElement(ints, 4));
        for (int i : ints) {
            System.out.print(i+" ");
        }
    }

}
