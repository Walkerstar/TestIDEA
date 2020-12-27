package mcw.test.sort;

/**
 * @author mcw 2020\1\30 0030-17:37
 * <p>
 * 冒泡排序 最好的情况时间复杂度 O(n) 最坏 O(n^2) 平均 O(n^2)
 */
public class BubbleSort {

    public static int[] sort(int[] array) {
        if (array==null || array.length <= 2)
            return array;
        for (int i = 0; i < array.length - 1; i++) {  //控制趟数
            for (int j = 0; j < array.length - 1 - i; j++) {  //比较大小
                if (array[j + 1] < array[j]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        /* or
        for (int end = array.length - 1; end > 0; end--) {
            for (int i = 0; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i + 1];
                    array[i + 1] = array[i];
                    array[i] = temp;
                }
            }
        }*/
        return array;
    }


    public static void main(String[] args) {
        int[] sort = sort(new int[]{1, 8, 3, 4, 996, 32, 1, 4, 98, 9, 6, 32});
        for (int i : sort) {
            System.out.print(i + "\t");
        }
    }
}
