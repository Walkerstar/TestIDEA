package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 给定一个列表 accounts，每个元素 accounts[i]是一个字符串列表，其中第一个元素 accounts[i][0]是名称 (name)，
 * 其余元素是 emails 表示该账户的邮箱地址。
 * <p></p>
 * 现在，我们想合并这些账户。如果两个账户都有一些共同的邮箱地址，则两个账户必定属于同一个人。请注意，即使两个账户具有相同的名称，
 * 它们也可能属于不同的人，因为人们可能具有相同的名称。一个人最初可以拥有任意数量的账户，但其所有账户都具有相同的名称。
 * <p></p>
 * 合并账户后，按以下格式返回账户：每个账户的第一个元素是名称，其余元素是按字符 ASCII 顺序排列的邮箱地址。账户本身可以以任意顺序返回。
 * @author mcw 2021/1/18 19:38
 */
public class leetCode721 {

    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        //使用两个哈希表分别记录每个邮箱对应的编号和每个邮箱对应的名称，遍历所有的账户并在两个哈希表中记录相应的信息
        Map<String, Integer> emailToIndex = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int emailsCount = 0;
        for (List<String> account : accounts) {
            String name = account.get(0);
            int size = account.size();
            for (int i = 1; i < size; i++) {
                String email = account.get(i);
                if (!emailToIndex.containsKey(email)) {
                    emailToIndex.put(email, emailsCount++);
                    emailToName.put(email, name);
                }
            }
        }
        //由于同一个账户中的邮箱地址一定属于同一个人，因此遍历每个账户，对账户中的邮箱地址进行合并操作。
        // 并查集存储的是每个邮箱地址对应的编号，合并操作也是针对编号进行合并。
        UnionFind uf = new UnionFind(emailsCount);
        for (List<String> account : accounts) {
            String firstEmail = account.get(1);
            int firstIndex = emailToIndex.get(firstEmail);
            int size = account.size();
            for (int i = 2; i < size; i++) {
                String nextEmail = account.get(i);
                int nextIndex = emailToIndex.get(nextEmail);
                uf.union(firstIndex, nextIndex);
            }
        }
        //遍历所有的邮箱地址，对于每个邮箱地址，通过并查集得到该邮箱地址属于哪个合并后的账户，
        // 即可整理出每个合并后的账户包含哪些邮箱地址。
        Map<Integer, List<String>> indexToEmails = new HashMap<>();
        for (String email : emailToIndex.keySet()) {
            int index = uf.find(emailToIndex.get(email));
            List<String> account = indexToEmails.getOrDefault(index, new ArrayList<>());
            account.add(email);
            indexToEmails.put(index, account);
        }

        //将邮箱地址排序，账户的名称可以通过在哈希表中查找任意一个邮箱对应的名称得到，将名称和排序后的邮箱地址整理成一个账户列表。
        // 对所有合并后的账户整理出账户列表，即可得到最终答案。
        List<List<String>> merged = new ArrayList<>();
        for (List<String> emails : indexToEmails.values()) {
            Collections.sort(emails);
            String name = emailToName.get(emails.get(0));
            List<String> account = new ArrayList<>();
            account.add(name);
            account.addAll(emails);
            merged.add(account);
        }
        return merged;
    }

    private static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        private void union(int x, int y) {
            parent[find(y)] = find(x);
        }
    }
}
