package mcw.test.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 计数排序的最终步骤：
 * <p>
 * 1、取无序数组arr中的最大值max和最小值min，新建(max-min +1)长度的数组newArr和长度为(max-min +1)的统计数组countArr。
 * <p>
 * 2、遍历原数组arr，将其值作为newArr的键，元素的个数作为值存放在该键处。
 * <p>
 * 3、遍历newArr，使统计数组countArr和newArr相同索引处存放的是newArr该索引之前元素的和。
 * <p>
 * 4、新建一个最终数组result，反向遍历原数组，取原数组的值arr[i]-min作为索引，从统计数组countArr取出该索引的值减1，作为最终数组result的索引，值为原数组的arr[i]，同时统计数组该索引处值减1，遍历结束后，最终数组result为排序后的数组。
 *
 * @author mcw 2023/2/28 17:59
 */
public class CountSort {
    public static int[] countSort(int[] arr) {
        //检测数据
        if (arr == null || arr.length == 0) {
            return arr;
        }

        //缩短不必要的长度，获取最大和最小值
        int min = arr[0];
        int max = arr[0];
        for (int j : arr) {
            if (j > max) {
                max = j;
            }
            if (j < min) {
                min = j;
            }
        }

        //用数组中的值作为索引，个数作为值
        int[] newArr = new int[(max - min) + 1];
        for (int j : arr) {
            newArr[j - min]++;
        }

        //记录当前数据拍在第几位
        int[] countArr = new int[newArr.length];
        //统计数组，记录后面的元素等于前面的元素之和
        for (int i = 0; i < newArr.length; i++) {
            if (i == 0) {
                countArr[i] = newArr[i];
                continue;
            }
            countArr[i] = newArr[i] + countArr[i - 1];
        }

        //最终结果
        int[] result = new int[arr.length];
        //反向遍历
        for (int i = arr.length - 1; i >= 0; i--) {
            //将数据放入指定的位置，统计数组中记录的元素排在第几位
            result[countArr[arr[i] - min] - 1] = arr[i];
            //排列一个后，就减少一个
            countArr[arr[i] - min]--;
        }
        return result;
    }

}
