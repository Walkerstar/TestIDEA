package mcw.test.leetcode.bzhan;

/**
 * 如果交换字符串 X 中的两个不同位置的字母，使得它和字符串 Y 相等，那么称 X 和 Y 两个字符串相似。如果这两个字符串本身是相等的，那它们也是相似的。
 * <p>
 * 例如，"tars" 和 "rats" 是相似的 (交换 0 与 2 的位置)； "rats" 和 "arts" 也是相似的，但是 "star" 不与 "tars"，"rats"，或 "arts" 相似。
 *<br>
 * 总之，它们通过相似性形成了两个关联组：{"tars", "rats", "arts"} 和 {"star"}。注意，"tars" 和 "arts" 是在同一组中，即使它们并不相似。
 * 形式上，对每个组而言，要确定一个单词在组中，只需要这个词和该组中至少一个单词相似。
 *<br>
 * 给你一个字符串列表 strs。列表中的每个字符串都是 strs 中其它所有字符串的一个字母异位词。请问 strs 中有多少个相似字符串组？
 *
 * @author mcw 2021/1/31 18:36
 */
public class leetCode839 {

    /**
     * 1.我们把每一个字符串看作点，字符串之间是否相似看作边，那么可以发现本题询问的是给定的图中有多少连通分量。<br>
     * 2.我们枚举给定序列中的任意一对字符串，检查其是否具有相似性，如果相似，那么我们就将这对字符串相连。<br>
     * 3.在实际代码中，我们可以首先判断当前这对字符串是否已经连通，如果没有连通，我们再检查它们是否具有相似性，可以优化一定的时间复杂度的常数。
     */
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        unionFind uf = new unionFind(n);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                uf.union(i, j, strs[i], strs[j], m);
            }
        }
        return uf.Count;
    }


    class unionFind {
        int[] parent;
        int Count;

        public unionFind(int n) {
            this.parent = new int[n];
            this.Count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (parent[x] = find(parent[x]));
        }

        public boolean union(int x, int y, String a, String b, int m) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return false;
            }
            if (check(a, b, m)) {
                parent[x] = y;
                --Count;
            }
            return true;
        }

        /**
         * 检查 a 和 b 是否是相似的
         *
         * @param a   字符串 a
         * @param b   字符串 b
         * @param len 字符串的长度
         * @return 返回 true 或 false
         */
        public boolean check(String a, String b, int len) {
            int num = 0;
            for (int i = 0; i < len; i++) {
                if (a.charAt(i) != b.charAt(i)) {
                    num++;
                    if (num > 2) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
