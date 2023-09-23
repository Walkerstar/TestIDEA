package mcw.test.leetcode.bzhan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一棵 n 个节点的树，编号从 0 到 n - 1 ，以父节点数组 parent 的形式给出，其中 parent[i] 是第 i 个节点的父节点。树的根节点为 0 号节点，所以 parent[0] = -1 ，因为它没有父节点。你想要设计一个数据结构实现树里面对节点的加锁，解锁和升级操作。
 * <p>
 * 数据结构需要支持如下函数：
 * <p>
 * Lock：指定用户给指定节点 上锁 ，上锁后其他用户将无法给同一节点上锁。只有当节点处于未上锁的状态下，才能进行上锁操作。
 * Unlock：指定用户给指定节点 解锁 ，只有当指定节点当前正被指定用户锁住时，才能执行该解锁操作。
 * Upgrade：指定用户给指定节点 上锁 ，并且将该节点的所有子孙节点 解锁 。只有如下 3 个条件 全部 满足时才能执行升级操作：
 * 指定节点当前状态为未上锁。
 * 指定节点至少有一个上锁状态的子孙节点（可以是 任意 用户上锁的）。
 * 指定节点没有任何上锁的祖先节点。
 * 请你实现 LockingTree 类：
 * <p>
 * LockingTree(int[] parent) 用父节点数组初始化数据结构。
 * lock(int num, int user) 如果 id 为 user 的用户可以给节点 num 上锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 id 为 user 的用户 上锁 。
 * unlock(int num, int user) 如果 id 为 user 的用户可以给节点 num 解锁，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 变为 未上锁 状态。
 * upgrade(int num, int user) 如果 id 为 user 的用户可以给节点 num 升级，那么返回 true ，否则返回 false 。如果可以执行此操作，节点 num 会被 升级 。
 * <p>
 * <p>
 * 示例 1：
 * 输入：
 * ["LockingTree", "lock", "unlock", "unlock", "lock", "upgrade", "lock"]
 * [[[-1, 0, 0, 1, 1, 2, 2]], [2, 2], [2, 3], [2, 2], [4, 5], [0, 1], [0, 1]]
 * 输出：
 * [null, true, false, true, true, true, false]
 * <p>
 * 解释：
 * LockingTree lockingTree = new LockingTree([-1, 0, 0, 1, 1, 2, 2]);
 * lockingTree.lock(2, 2);    // 返回 true ，因为节点 2 未上锁。
 * // 节点 2 被用户 2 上锁。
 * lockingTree.unlock(2, 3);  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
 * lockingTree.unlock(2, 2);  // 返回 true ，因为节点 2 之前被用户 2 上锁。
 * // 节点 2 现在变为未上锁状态。
 * lockingTree.lock(4, 5);    // 返回 true ，因为节点 4 未上锁。
 * // 节点 4 被用户 5 上锁。
 * lockingTree.upgrade(0, 1); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
 * // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
 * lockingTree.lock(0, 1);    // 返回 false ，因为节点 0 已经被上锁了。
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == parent.length
 * 2 <= n <= 2000
 * 对于 i != 0 ，满足 0 <= parent[i] <= n - 1
 * parent[0] == -1
 * 0 <= num <= n - 1
 * 1 <= user <= 10^4
 * parent 表示一棵合法的树。
 * lock ，unlock 和 upgrade 的调用 总共 不超过 2000 次。
 *
 * @author MCW 2023/9/23
 */
public class leetCode1993 {

    /**
     * 方法一：深度优先搜索
     * 思路
     * <p>
     * 按照题目要求，依次实现各个函数即可：
     * <p>
     * Lock：可以用一个数组变量 lockNodeUser 记录给各个节点上锁的用户，lockNodeUser[num] 即表示给节点 num 上锁的用户。
     * 当 lockNodeUser[num]=−1 时，即表示 节点 num 未被上锁，通过给 lockNodeUser[num] 赋值实现上锁。
     * <p>
     * Unlock：通过比较变量 lockNodeUser[num] 和 user 是否先等来判断当前节点是否可以解锁，通过赋值来解锁。
     * <p>
     * Upgrade：实现较为复杂，首先需要判断三个条件是否同时成立，如果是，还需要给指定节点上锁并且给它的所有子孙节点解锁。三个条件中：
     * <p>
     * 1. 指定节点当前状态为未上锁：通过变量 lockNodeUser 来判断。
     * 2. 指定节点没有任何上锁的祖先节点：需要依次遍历当前节点的父亲节点，通过变量 lockNodeUser 和 parent 来判断。具体代码中，我们利用一个函数 hasLockedAncestor 来实现这一判断。
     * 3. 指定节点至少有一个上锁状态的子孙节点：我们将这一判断放到第三步来进行，使得它可以和「给它的所有子孙节点解锁」同时实现。
     * 三个状态的判断，我们用「短路与」来连接，当只有前两步都为真，才会进行第三步。
     * 当第三步也为真，那么我们就需要进行「给它的所有子孙节点解锁」这一步；当第三步为假，就说明指定节点没有上锁的子孙节点，
     * 那么我们仍可以进行「给它的所有子孙节点解锁」这一步，并不影响树的状态。
     * 我们定义一个递归函数 checkAndUnlockDescendant  来实现这一步，返回一个布尔值表示当前节点是否有上锁的子孙节点（也包括自己），
     * 同时将所有的子孙节点（也包括自己）解锁。遍历子孙节点时，我们提前构建一个变量 children ，表示当前节点的孩子节点，这一步可以在初始化时完成。
     * <p>
     * 最后，如果这三个条件与的结果为真，将当前节点上锁。
     */

    class LockingTree {
        private int[] parent;
        private int[] lockNodeUser;
        private List<Integer>[] children;

        public LockingTree(int[] parent) {
            int n = parent.length;
            this.parent = parent;
            this.lockNodeUser = new int[n];
            Arrays.fill(this.lockNodeUser, -1);
            this.children = new List[n];
            for (int i = 0; i < n; i++) {
                this.children[i] = new ArrayList<>();
            }
            for (int i = 0; i < n; i++) {
                int p = parent[i];
                if (p != -1) {
                    children[p].add(i);
                }
            }
        }

        public boolean lock(int num, int user) {
            if (lockNodeUser[num] == -1) {
                lockNodeUser[num] = user;
                return true;
            }
            return false;
        }
        public boolean unLock(int num, int user) {
            if (lockNodeUser[num] == user) {
                lockNodeUser[num] = -1;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            boolean res = lockNodeUser[num] == -1 && !hasLockAncestor(num) && checkAndUnlockDescendant(num);
            if (res) {
                lockNodeUser[num] = user;
            }
            return res;
        }

        public boolean hasLockAncestor(int num) {
            num = parent[num];
            while (num != -1) {
                if (lockNodeUser[num] != -1) {
                    return true;
                }
                num = parent[num];
            }
            return false;
        }

        private boolean checkAndUnlockDescendant(int num) {
            boolean res = lockNodeUser[num] != -1;
            lockNodeUser[num] = -1;
            for (int child : children[num]) {
                res |= checkAndUnlockDescendant(child);
            }
            return res;
        }

    }


}
