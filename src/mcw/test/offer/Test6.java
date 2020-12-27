package mcw.test.offer;

/**
 * @author mcw 2020\1\13 0013-14:37
 * 非递减旋转数组查找最小元素
 */
public class Test6 {

    public static int minNumberInRotateArray(int[] array) {
        if (array.length == 0)
            return 0;
        int left = 0;
        int right = array.length - 1;
        int middle = -1;
        while (array[left] >= array[right]) {
            if (right - left == 1) {
                middle = right;
                break;
            }
            middle = left + (right - left) / 2;
            if (array[middle] >= array[left]) {
                left = middle;
            }
            if (array[middle] <= array[right]) {
                right = middle;
            }
        }
        return array[middle];
    }

    public static void main(String[] args) {
        int[] a={2,3,3,4,4,1,1,1,2};
        int[] b={3,4,5,8,8,8,2,2};
        System.out.println(minNumberInRotateArray(b));
    }
}
