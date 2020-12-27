package mcw.test.common;

/**
 * @author mcw
 * @date 2019\11\25 0025-19:49
 */
public class RandomListNode {
    public int val;
    public RandomListNode next=null;
    public RandomListNode random=null;

    public RandomListNode(int val){
        this.val=val;
    }

    @Override
    public String toString() {
        return "RandomListNode{" +
                "val=" + val +
                ", next=" + next +
                ", random=" + random +
                '}';
    }
}
