package mcw.test.leetcode.niuke;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mcw 2020\3\6 0006-21:48
 *
 * 给定一个回文串 S ，分割 S 使得 S 的每一个子串都是回文串，返回所有结果
 * （注意：返回结果的顺序与输入字符串的字母顺序一致）
 * <p>
 * 例：s="aab"   返回结果：{["aa","b"],["a","a","b"]}
 */
public class Test20 {

    public ArrayList<ArrayList<String>> partition(String s) {
        int len = s.length();
        //存放所有结果
        ArrayList<ArrayList<String>> list = new ArrayList<>();

        if (len <= 0) {
            return null;
        }
        if (len == 1) {
            ArrayList<String> tempList = new ArrayList<>();
            tempList.add(s);
            list.add(tempList);
        } else {
            for (int i = 1; i < len + 1; i++) {
                String temp = s.substring(0, i);
                if (isPalindrome(temp)) {
                    if (i == len) {
                        ArrayList<String> templist = new ArrayList<>();
                        templist.add(temp);
                        list.add(templist);
                    } else {
                        List<ArrayList<String>> list1 = partition(s.substring(i));
                        for (ArrayList<String> strings : list1) {
                            ArrayList<String> tempList = new ArrayList<>();
                            tempList.add(temp);
                            tempList.addAll(strings);
                            list.add(tempList);
                        }
                    }
                }
            }
        }
        return list;
    }


    /**
     * 判断是否是回文串
     * @param s 要判断的字符串
     * @return  true or  false
     */
    private boolean isPalindrome(String s) {
        int len = s.length();
        int mid=2;
        boolean flag = false;
        if (len == 1) {
            flag = true;
        } else {
            int index = len / 2;
            if (len % mid == 1) {
                index = len / 2 + 1;
            }
            if (s.substring(0, len / mid).equals(new StringBuffer(s.substring(index)).reverse().toString())) {
                flag = true;
            }
        }
        return flag;
    }


    public static void main(String[] args) {
        ArrayList<ArrayList<String>> list = new Test20().partition("abab");

        for (ArrayList<String> arrayList : list) {
            System.out.println(arrayList);
        }
    }
}
