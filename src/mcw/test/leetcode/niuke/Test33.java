package mcw.test.leetcode.niuke;

import java.util.ArrayList;

/**
 * @author mcw 2020\3\25 0025-11:48
 *
 * 返回杨辉三角的前 K 行
 */
public class Test33 {

    public static ArrayList<ArrayList<Integer>> generate(int num){
        if(num<0)
            return null;
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ArrayList<Integer> list = ReturnK(i);
            arrayLists.add(list);
        }
        return arrayLists;
    }

    public static ArrayList<Integer> ReturnK(int k) {
        if (k == 0) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            return list;
        }
        ArrayList<Integer> list = ReturnK(k - 1);
        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i < k; i++) {
            row.add(list.get(i - 1) + list.get(i));
        }
        row.add(1);
        return row;
    }

    public static void main(String[] args) {
//        ArrayList<Integer> list = ReturnK(3);
//        System.out.println(list);
        ArrayList<ArrayList<Integer>> lists = generate(3);
        lists.forEach(System.out::println);
    }
}
