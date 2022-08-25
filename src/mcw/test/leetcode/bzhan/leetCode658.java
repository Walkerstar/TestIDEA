package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * <p>
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *
 * @author MCW 2022/8/25
 */
public class leetCode658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int i : arr) {
            list.add(i);
        }
        list.sort((a, b) -> {
            if (Math.abs(a - x) != Math.abs(b - x)) {
                return Math.abs(a - x) - Math.abs(b - x);
            } else {
                return a - b;
            }
        });
        List<Integer> ans = list.subList(0, k);
        Collections.sort(ans);
        return ans;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        //left , right 指向的元素都是各自部分最接近 x 的元素
        int right = binarySearch(arr, x);
        int left = right - 1;
        while (k-- > 0) {
            if (left < 0) {
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    public List<Integer> findClosestElements3(int[] arr, int k, int x) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k - 1] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left > 0 && Math.abs(arr[left - 1] - x) <= Math.abs(arr[left + k - 1] - x)) {
            --left;
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        leetCode658 object = new leetCode658();
        int[] arr = new int[]{1, 2, 2, 3, 4, 5, 8, 9, 10, 11, 12};
        List<Integer> list = object.findClosestElements(arr, 4, 5);
        List<Integer> list2 = object.findClosestElements2(arr, 4, 5);
        List<Integer> list3 = object.findClosestElements3(arr, 4, 5);
        System.out.println(list.toString());
        System.out.println(list2.toString());
        System.out.println(list3.toString());
    }

}
