package mcw.test.offer;

import java.util.ArrayList;

/**
 * @author mcw 2020\1\21 0021-20:47
 * <p>
 * 找出所有和为S的连续正数序列（至少两个数）
 */
public class Test41 {
    public static ArrayList<ArrayList<Integer>> FindSquence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum < 2) return res;
        int half = (sum + 1) >> 1; //最大的数
        for (int i = 1; i < half; i++) { //序列开始的数字依次增大
            boolean flag = false;
            int tem = i;
            int he = 0;
            while (he < sum) {
                he += tem;
                if (he == sum) { //从i开始往后相加
                    flag = true;
                    break;
                }
                tem++;
            }
            if (flag) {
                ArrayList<Integer> al = new ArrayList<>();
                for (int j = i; j <= tem; j++) {
                    al.add(j);
                }
                res.add(al);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> arrayLists = FindSquence(100);
        for (ArrayList<Integer> arrayList : arrayLists) {
            for (Integer num : arrayList) {
                System.out.print(num+"\t");
            }
            System.out.println();
        }
    }
}
