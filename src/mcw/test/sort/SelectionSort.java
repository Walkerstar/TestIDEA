package mcw.test.sort;

/**
 * @author mcw 2020\1\30 0030-17:58
 * <p>
 * 选择排序  最好 最坏 平均 都是 O(n^2)
 */
public class SelectionSort {

    public static int[] sort(int[] array) {
        if (array.length == 0)
            return array;
        for (int i = 0; i < array.length - 1; i++) {
            int midIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[midIndex])
                    midIndex = j; //将最小数的索引保存
            }
            int temp = array[midIndex];
            array[midIndex] = array[i];
            array[i] = temp;
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
