package mcw.test.leetcode.bzhan;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author mcw 2020\5\2 0002-16:02
 * given a digit string. return all possible letter combinations that the number could represent.
 * a mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * 给定一个数字字符串。返回数字可能代表的所有可能的字母组合。 下面给出了数字到字母的映射
 * （就像在电话按钮上一样）。 提示：（dfs/bfs）
 * 例： “23”  输出：{"ad","ae","af","bd","be","bf","cd","ce","cf"}
 */
public class leetCode17 {
    /**
     * DFS，深度优先
     */
    public static List<String> letterCombinations(String digits){
        LinkedList<String> res = new LinkedList<>();
        if(digits==null || digits.length()==0){return res;}
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('2',new char[]{'a','b','c'});
        map.put('3',new char[]{'d','e','f'});
        map.put('4',new char[]{'g','h','i'});
        map.put('5',new char[]{'j','k','l'});
        map.put('6',new char[]{'m','n','o'});
        map.put('7',new char[]{'p','q','r','s'});
        map.put('8',new char[]{'t','u','v'});
        map.put('9',new char[]{'w','x','y','z'});
        helper("",0,digits,res,map);
        return res;
    }

    private static void helper(String curr, int currIdx, String digits,
                               List<String> res, HashMap<Character, char[]> map) {
        if(currIdx==digits.length()){
            res.add(curr);
        }else {
            char c=digits.charAt(currIdx);
            if(map.containsKey(c)){
                for (char ch : map.get(c)) {
                    helper(curr+ch,currIdx+1,digits,res,map);
                }
            }else {
                //if seeing 1,not valid string. no output
                helper(curr,currIdx+1,digits,res,map);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("23"));
    }
}
