package mcw.test.leetcode.bzhan;

/**
 * @author mcw 2020\6\11 0011-20:01
 * Edit Distance
 * given two words,find the minimum number of steps required to convert word1 to word2(each operation is counted as 1 step)
 * (insert / delete / replace ) a character
 */
public class leetCode72 {
    public static int minDistance(String s, String t) {
        int m = s.length();
        int n = t.length();

        //state: dis[i][j]; distance between s(0,i) and t(0,j),inclusive
        int[][] dis = new int[m + 1][n + 1];
        //init: [0][j],[i][0] are all 0
        for (int i = 0; i <= m; i++) { dis[i][0] = i; }
        for (int i = 0; i <= n; i++) { dis[0][i] = i; }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dis[i][j] = Integer.MAX_VALUE;
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dis[i][j] = Math.min(dis[i - 1][j], dis[i][j - 1]) + 1;
                    dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1]);
                    //dis[i][j]=dis[i-1][j-1]
                } else {
                    dis[i][j] = Math.min(dis[i - 1][j], dis[i][j - 1]) + 1;
                    dis[i][j] = Math.min(dis[i][j], dis[i - 1][j - 1] + 1);
                }
            }
        }
        return dis[m][n];
    }
}
