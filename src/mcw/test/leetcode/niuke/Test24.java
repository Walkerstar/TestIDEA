package mcw.test.leetcode.niuke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mcw 2020\3\11 0011-19:44
 * 给定两个单词和一个单词词典，找出所有的从初始单词到目标单词的转换序列
 *  例：hit --> cog    [hot,dot,dog,lot,log]
 *  转换序列为: hit -> hot -> dot -> dog -> cog
 */
public class Test24 {
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {

        int minSize = Integer.MAX_VALUE,
                wordSize = start.length(),
                level = 1;
        Queue<ArrayList<String>> paths = new LinkedList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<>();

        paths.offer(new ArrayList<String>() {{ add(start); }});
        ArrayList<String> words = new ArrayList<>();
        words.add(start);
        while (!paths.isEmpty()) {
            ArrayList<String> path = paths.poll();
            int currentSize = path.size();
            if (currentSize > level) {
                if (currentSize + 1 > minSize)
                    break;
                for (String str : words) {
                    dict.remove(str);
                }
                words.clear();
                level = currentSize;
            }
            String lastword = path.get(currentSize - 1);
            for (int i = 0; i < wordSize; i++) {
                for (char c = 'a'; c <= 'z'; c++) {
                    char[] charArr = lastword.toCharArray();
                    charArr[i] = c;
                    String newWord = new String(charArr);
                    if (newWord.equals(end)) {
                        minSize = currentSize + 1;
                        result.add(deepCopy(path, newWord));
                    } else if (dict.contains(newWord)) {
                        words.add(newWord);
                        paths.offer(deepCopy(path, newWord));
                    }
                }
            }
        }
        return result;
    }

    private ArrayList<String> deepCopy(ArrayList<String> arr, String newWord) {
        ArrayList<String> newPath = new ArrayList<>(arr);
        newPath.add(newWord);
        return newPath;
    }

    public static void main(String[] args) {
        HashSet<String> set=new HashSet<>();
        String[] strings={"hot","dot","dog","lot","log"};
        for (String s : strings) {
            set.add(s);
        }

        ArrayList<ArrayList<String>> ladders = new Test24().findLadders("hit", "cog", set);
        for (ArrayList<String> ladder : ladders) {
            System.out.println(ladder);
        }
    }
}
