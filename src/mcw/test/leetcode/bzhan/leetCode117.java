package mcw.test.leetcode.bzhan;

import mcw.test.common.TreeLinkedNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 II
 *
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 *
 *       1                    1  --> null
 *    /    \                /   \
 *   2      3    ====>     2 --> 3 -->null
 *  / \      \           /   \    \
 * 4   5      7         4 --> 5 -->7 -->null
 * @author mcw 2020\9\28 0028-16:58
 */
public class leetCode117 {

    public TreeLinkedNode connect3(TreeLinkedNode root) {
        if (root == null)
            return null;
        //cur我们可以把它看做是每一层的链表
        TreeLinkedNode cur = root;
        while (cur != null) {
            //遍历当前层的时候，为了方便操作在下一
            //层前面添加一个哑结点（注意这里是访问
            //当前层的节点，然后把下一层的节点串起来）
            TreeLinkedNode dummy = new TreeLinkedNode(0);
            //pre表示访下一层节点的前一个节点
            TreeLinkedNode pre = dummy;
            //然后开始遍历当前层的链表
            while (cur != null) {
                if (cur.left != null) {
                    //如果当前节点的左子节点不为空，就让pre节点
                    //的next指向他，也就是把它串起来
                    pre.next = cur.left;
                    //然后再更新pre
                    pre = pre.next;
                }
                //同理参照左子树
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                //继续访问这一行的下一个节点
                cur = cur.next;
            }
            //把下一层串联成一个链表之后，让他赋值给cur，
            //后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }

    TreeLinkedNode last = null, nextStar = null;

    public TreeLinkedNode connect2(TreeLinkedNode root) {
        if (root == null) {
            return null;
        }
        TreeLinkedNode star = root;
        while (star != null) {
            last = null;
            nextStar = null;
            for (TreeLinkedNode p = star; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            star = nextStar;
        }
        return root;
    }

    private void handle(TreeLinkedNode p) {
        if (last != null) {
            last.next = p;
        }
        if (nextStar == null) {
            nextStar = p;
        }
        last = p;
    }

    //out time
    public TreeLinkedNode connect1(TreeLinkedNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeLinkedNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            TreeLinkedNode last = null;
            for (int i = 1; i < n; ++i) {
                TreeLinkedNode f = queue.poll();
                assert f != null;
                if (f.left != null) {
                    queue.offer(f.left);
                }
                if (f.right != null) {
                    queue.offer(f.right);
                }
                if (i != 1) {
                    last.next = f;
                }
                last = f;
            }
        }
        return root;
    }
}
