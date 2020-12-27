package mcw.test.sort;

/**
 * @author mcw 2020\1\30 0030-19:01
 * <p>
 * 希尔排序 最好 最坏 无   平均 O(n^1.3)
 */
public class ShellSort {

    public static int[] sort(int[] array) {
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                temp = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] sort = sort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6, 32});
        for (int i : sort) {
            System.out.print(i + "\t");
        }
    }
}
