package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引(编号从 0 开始)。
 * <p></p>
 * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
 *
 * @author mcw 2021/1/11 20:54
 */
public class leetCode1202 {
    /**
     * 并查集
     */
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs){
        if (pairs.size() == 0) {
            return s;
        }

        //第一步，将任意交换的结点对输入并查集
        int len = s.length();
        UnionFind unionFind = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            int index1 = pair.get(0);
            int index2 = pair.get(1);
            unionFind.union(index1, index2);
        }

        //第二步，构建映射关系
        char[] charArray = s.toCharArray();
        //key:连通分量的代表  value:同一个连通分量的字符集合（保存在一个优先队列中）
        Map<Integer, PriorityQueue<Character>> hashMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
//            if (hashMap.containsKey(root)){
//                hashMap.get(root).offer(charArray[i]);
//            }else {
//                PriorityQueue<Character> minHeap=new PriorityQueue<>();
//                minHeap.offer(charArray[i]);
//                hashMap.put(root,minHeap);
//            }
            // 上面六行代码等价于下面一行代码
            hashMap.computeIfAbsent(root, key -> new PriorityQueue<>()).offer(charArray[i]);
        }

        //第三步，重组字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            int root = unionFind.find(i);
            stringBuilder.append(hashMap.get(root).poll());
        }
        return stringBuilder.toString();
    }

    private static class UnionFind {

        private final int[] parent;

        /**
         * 以 i 为根节点的子树的高度
         */
        private final int[] rank;

        public UnionFind(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
                this.rank[i] = 1;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                //此时，以 rootY 为根节点的树的高度仅加了 1
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                //此时，以 rootY 为根节点的树的高度不变
            } else {
                //同理，以 rootX 为根节点的树的高度不变
                parent[rootY] = rootX;
            }
        }

        private int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists=new ArrayList<>();
        List<Integer> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        List<Integer> list3=new ArrayList<>();
        list1.add(0);
        list1.add(3);
        list2.add(1);
        list2.add(2);
        list3.add(0);
        list3.add(2);
        lists.add(list3);
        lists.add(list2);
        lists.add(list1);
        System.out.println(smallestStringWithSwaps("dcba", lists));
    }
}
