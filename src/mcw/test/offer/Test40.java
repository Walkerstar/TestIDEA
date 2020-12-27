package mcw.test.offer;

import java.util.Arrays;

/**
 * @author mcw 2020\1\21 0021-20:37
 * <p>
 * 一个整型数组里除了两个数字外，其他的数字都出现了两次。找出这两个数字
 */
public class Test40 {

    public static void FindNums(int[] array, int[] num1, int[] num2) {
        Arrays.sort(array);
        int[] arr = new int[2];
        int j = 0;
        if (array[0] != array[1]) {
            arr[0] = array[0];
            j = 1;
        }
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] != array[i - 1] && array[i] != array[i + 1]) {
                arr[j] = array[i];
                j++;
            }
        }
        if (array[array.length - 2] != array[array.length - 1]) {
            arr[1] = array[array.length - 1];
        }
        num1[0] = arr[0];
        num2[0] = arr[1];
    }
}
