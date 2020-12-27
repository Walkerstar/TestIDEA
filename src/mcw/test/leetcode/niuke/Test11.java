package mcw.test.leetcode.niuke;

import java.util.*;

/**
 * @author mcw 2020\2\17 0017-19:37
 * <p>
 * 给定一个字符串 S 和一组单词 Dict,在 S 中添加空格将 S变成一个句子，
 * 使得句子的每一个单词都是Dict中的单词，返回所有的结果
 * 给定 S=“catsanddog”
 * Dict=["cat","cats","and","sand","dog"]
 * 返回结果：["cats and dog","cat sand dog"]
 */
public class Test11 {

    public ArrayList<String> list = new ArrayList<>();

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        //DFS(s, dict, s.length(), "");
        DFS1(s,dict,0,"");
        return list;
    }

    //从后往前遍历 index=s.length()
    private void DFS(String s, Set<String> dict, int index, String str) {

        if (index <= 0)
            if (str.length() > 0)
                list.add(str.substring(0, str.length() - 1));

        for (int i = index; i >= 0; i--) {
            if (dict.contains(s.substring(i, index)))
                DFS(s, dict, i, s.substring(i, index) + " " + str);
        }
    }

    //从前往后遍历 index=0
    private void DFS1(String s,Set<String> dict,int index,String str){

        int i = index+1;
        if(i>=s.length())
            if(str.length()>0)
                list.add(str.substring(0,str.length()-1));

        for (; i <= s.length(); i++) {
            if(dict.contains(s.substring(index,i))){
                DFS1(s,dict,i,str+s.substring(index,i)+" ");
            }
        }

    }

    public static void main(String[] args) {
        Test11 object=new Test11();
         Set<String> dict=new HashSet<>();
        String[] s={"cat","cats","and","sand","dog"};
        for (String str : s) {
            dict.add(str);
        }
        ArrayList<String> result = object.wordBreak("catsanddog", dict);
        result.forEach(System.out::println);
    }
}
