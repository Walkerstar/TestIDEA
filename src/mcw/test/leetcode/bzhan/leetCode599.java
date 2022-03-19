package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 * <p>
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 *
 * @author MCW 2022/3/14
 */
public class leetCode599 {

    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> index = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            index.put(list1[i], i);
        }
        List<String> ret = new ArrayList<>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            //第二次循环寻找餐厅的时候，如果当前索引已经超过了最小索引和，则可以直接退出循环，这样能打败100%。
            //if (i > indexSum) {
            //    return ret.toArray(new String[ret.size()]);
            //}

            if (index.containsKey(list2[i])) {
                int j = index.get(list2[i]);
                //如果该索引和比最小索引和小，则清空结果，将该餐厅加入结果中，该索引和作为最小索引和；
                if (i + j < indexSum) {
                    ret.clear();
                    ret.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    ret.add(list2[i]);
                }
            }
        }
        return ret.toArray(new String[ret.size()]);
    }

    public static void main(String[] args) {
        String[] list1 = {"Shogun", "Tapioca Express", "Burger King", "KFC"};
        String[] list2 = {"KFC", "Shogun", "Burger King"};
        String[] restaurant = new leetCode599().findRestaurant(list1, list2);
        System.out.println(Arrays.toString(restaurant));
    }
}
