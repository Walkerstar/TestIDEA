package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author mcw 2020\6\2 0002-21:19
 * Group Anagrams
 * given an array of string .group anagrams together.
 */
public class leetCode49 {
    public static List<List<String>> groupAnagrams(String[] str) {
        ArrayList<List<String>> results = new ArrayList<>();
        if (str == null || str.length == 0) return results;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String s : str) {
            char[] curr = s.toCharArray();
            Arrays.sort(curr);
            String key = String.valueOf(curr);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
    
}

