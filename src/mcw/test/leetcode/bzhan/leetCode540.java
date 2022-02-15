package mcw.test.leetcode.bzhan;

/**
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * @author MCW 2022/2/14
 */
public class leetCode540 {

    /**
     * 由于给定数组有序 且 常规元素总是两两出现，因此如果不考虑“特殊”的单一元素的话，
     * 我们有结论：成对元素中的第一个所对应的下标必然是偶数，成对元素中的第二个所对应的下标必然是奇数。
     *
     * 然后再考虑存在单一元素的情况，假如单一元素所在的下标为 x，那么下标 x 之前（左边）的位置仍满足上述结论，
     * 而下标 x 之后（右边）的位置由于 x 的插入，导致结论翻转。
     *
     * 
     * 假设只出现一次的元素位于下标 x，由于其余每个元素都出现两次，因此下标 x 的左边和右边都有偶数个元素，数组的长度是奇数。
     *
     * 由于数组是有序的，因此数组中相同的元素一定相邻。
     * 对于下标 x 左边的下标 y，如果 nums[y]=nums[y+1]，则 y 一定是偶数；对于下标 x 右边的下标 z，如果 nums[z]=nums[z+1]，则 z 一定是奇数。
     * 由于下标 x 是相同元素的开始下标的奇偶性的分界，因此可以使用二分查找的方法寻找下标 x。
     *
     * 初始时，二分查找的左边界是 0，右边界是数组的最大下标。每次取左右边界的平均值 mid 作为待判断的下标，根据 mid 的奇偶性决定和左边或右边的相邻元素比较：
     * 如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等；
     * 如果 mid 是奇数，则比较 nums[mid−1] 和 nums[mid] 是否相等。
     *
     * 如果上述比较相邻元素的结果是相等，则  mid < x，调整左边界，否则  mid ≥ x，调整右边界。调整边界之后继续二分查找，直到确定下标 x 的值。
     * 得到下标 x 的值之后，nums[x] 即为只出现一次的元素。
     *
     * 细节:
     * 利用按位异或的性质，可以得到 mid 和相邻的数之间的如下关系，其中 \oplus⊕ 是按位异或运算符：
     * 当 mid 是偶数时， mid + 1= mid ⊕ 1；
     * 当 mid 是奇数时， mid − 1= mid ⊕ 1。
     * 因此在二分查找的过程中，不需要判断 mid 的奇偶性，mid 和  mid⊕1 即为每次需要比较元素的两个下标。
     *
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            if (nums[mid] == nums[mid ^ 1]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    public int singleNonDuplicate2(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

}
