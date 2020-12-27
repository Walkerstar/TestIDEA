package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\5\4 0004-15:54
 * 删除指定元素，返回剩下的有效数字的个数
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

    public static void main(String[] args) {
        int[] ints = {2, 2, 3, 4, 5, 5, 7, 7};
        System.out.println(removeElement(ints, 4));
        for (int i : ints) {
            System.out.print(i+" ");
        }
    }

}
