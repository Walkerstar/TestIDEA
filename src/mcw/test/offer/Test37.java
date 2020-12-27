package mcw.test.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mcw 2020\1\20 0020-21:09
 *
 * 统计一个数在排序数组中出现的次数
 */
public class Test37 {

    public static int GetNumberOfK(int[] arr, int k) {

        int length = arr.length;
        if (length == 0)
            return 0;
        int firstK = getFirstK(arr, k, 0, length - 1);
        int lastK = getLastK(arr, k, 0, length - 1);
        if (firstK != -1 && lastK != -1)
            return lastK - firstK + 1;
        return 0;
    }

    private static int getLastK(int[] arr, int k, int start, int end) {
        int length = arr.length;
        int mid = (start + end) >> 1;
        while (start <= end) {
            if (arr[mid] > k) {
                end = mid - 1;
            } else if (arr[mid] < k) {
                start = mid + 1;
            } else if (mid + 1 < length && arr[mid + 1] == k) {
                start = mid + 1;
            } else {
                return mid;
            }
            mid = (start + end) >> 1;
        }
        return -1;
    }

    private static int getFirstK(int[] arr, int k, int start, int end) {
        if (start > end)
            return -1;
        int mid = (start + end) >> 1;
        if (arr[mid] > k) {
            return getFirstK(arr, k, start, mid - 1);
        } else if (arr[mid] < k) {
            return getFirstK(arr, k, mid + 1, end);
        } else if (mid - 1 >= 0 && arr[mid - 1] == k) {
            return getFirstK(arr, k, start, mid - 1);
        } else
            return mid;
    }

    public static int GetNumber(int[] arr, int k) {
        Map<Integer,Integer> map=new HashMap<>();
        if(arr.length==0)
            return 0;
        for (int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],1);
            }else{
                map.put(arr[i],map.get(arr[i])+1);
            }
        }
        return map.get(k)==null?0:map.get(k);
    }

    public static void main(String[] args) {
//        int numberOfK = GetNumberOfK(new int[]{1, 1, 4, 5, 6, 8, 8, 9, 10}, 1);
        int numberOfK = GetNumber(new int[]{1, 1, 4, 5, 6, 8, 8, 9, 10}, 2);
        System.out.println(numberOfK);
    }

}
