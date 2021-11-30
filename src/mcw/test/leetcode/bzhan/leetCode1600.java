package mcw.test.leetcode.bzhan;

import java.util.*;

/**
 * 一个王国里住着国王、他的孩子们、他的孙子们等等。每一个时间点，这个家庭里有人出生也有人死亡。
 * <p>
 * 这个王国有一个明确规定的皇位继承顺序，第一继承人总是国王自己。我们定义递归函数 Successor(x, curOrder) ，给定一个人 x 和当前的继承顺序，该函数返回 x 的下一继承人。
 * <p>
 * Successor(x, curOrder):
 * 如果 x 没有孩子或者所有 x 的孩子都在 curOrder 中：<br>
 * 如果 x 是国王，那么返回 null  <br>
 * 否则，返回 Successor(x 的父亲, curOrder) <br>
 * 否则，返回 x 不在 curOrder 中最年长的孩子 <br>
 * 比方说，假设王国由国王，他的孩子 Alice 和 Bob （Alice 比 Bob 年长）和 Alice 的孩子 Jack 组成。
 * <p>
 * 一开始， curOrder 为 ["king"].
 * 调用 Successor(king, curOrder) ，返回 Alice ，所以我们将 Alice 放入 curOrder 中，得到 ["king", "Alice"] 。
 * 调用 Successor(Alice, curOrder) ，返回 Jack ，所以我们将 Jack 放入 curOrder 中，得到 ["king", "Alice", "Jack"] 。
 * 调用 Successor(Jack, curOrder) ，返回 Bob ，所以我们将 Bob 放入 curOrder 中，得到 ["king", "Alice", "Jack", "Bob"] 。
 * 调用 Successor(Bob, curOrder) ，返回 null 。最终得到继承顺序为 ["king", "Alice", "Jack", "Bob"] 。
 * 通过以上的函数，我们总是能得到一个唯一的继承顺序。
 *
 * @author mcw 2021/6/20 10:28
 */
public class leetCode1600 {

    /**
     * 多叉树的前序遍历
     */
    class ThroneInheritance {
        Map<String, List<String>> edges;
        Set<String> dead;
        String king;

        /**
         * 我们将 kingName 作为树的根节点；
         */
        public ThroneInheritance(String kingName) {
            edges = new HashMap<>();
            dead = new HashSet<>();
            this.king = kingName;
        }

        /**
         * 我们在树中添加一条从 parentName 到 childName 的边，将 childName 作为 parentName 的子节点；
         */
        public void birth(String parentName, String childName) {
            List<String> children = edges.getOrDefault(parentName, new ArrayList<>());
            children.add(childName);
            edges.put(parentName, children);
        }

        public void death(String name) {
            dead.add(name);
        }

        /**
         * 我们从根节点开始对整棵树进行前序遍历。需要注意的是，如果遍历到死亡人员，那么不能将其加入继承顺序列表中。
         */
        public List<String> getInheritanceOrder() {
            List<String> ans = new ArrayList<>();
            preorder(ans, king);
            return ans;
        }

        private void preorder(List<String> ans, String name) {
            if (!dead.contains(name)) {
                ans.add(name);
            }
            List<String> children = edges.getOrDefault(name, new ArrayList<>());
            for (String childName : children) {
                preorder(ans, childName);
            }
        }
    }
}