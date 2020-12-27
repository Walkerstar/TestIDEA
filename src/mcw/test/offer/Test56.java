package mcw.test.offer;

import com.sun.org.glassfish.gmbal.AMXMBeanInterface;
import mcw.test.common.ListNode;


/**
 * @author mcw 2020\1\25 0025-17:46
 *
 * 在一个排序的链表中，存在重复的节点，请删除重复的节点，该节点不保留，返回链表头指针
 */
public class Test56 {

    public static ListNode deleteDuplication(ListNode pHead){
        if(pHead==null || pHead.next==null)
            return pHead;
        if(pHead.val==pHead.next.val){
            ListNode pNode=pHead.next;
            while (pNode!=null && pNode.val==pHead.val){
                //跳过与当前节点值相同的节点
                pNode=pNode.next;
            }
            return deleteDuplication(pNode); //从第一个与当前节点不同的节点开始递归
        }else {
            //当前节点不是重复节点
            pHead.next=deleteDuplication(pHead.next); //保留当前节点，从下一个节点开始递归
            return pHead;
        }
    }

    public static ListNode deleteDuplication1(ListNode pHead){
        if(pHead==null || pHead.next==null)
            return pHead;
        ListNode q,p,r;//分别表示上个节点，当前节点，后继节点
        p=pHead;
        q=r=null;
        while(p!=null){
            boolean flag=false;
            r=p.next;
            while(r!=null && r.val==p.val){
                flag=true;
                r=r.next;
            }
            if(flag){
                if(q!=null)
                    q.next=r;
                else
                    pHead=null;
            }else {
                if(q==null)
                    pHead=p;
                q=p;
            }
            p=r;
        }
        return pHead;
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode h1=new ListNode(2);
        ListNode h2=new ListNode(3);
        ListNode h3=new ListNode(3);
        ListNode h4=new ListNode(3);
        ListNode h5=new ListNode(4);
        head.next=h1;
        h1.next=h2;
        h2.next=h3;
        h3.next=h4;
        h4.next=h5;
        h5.next=new ListNode(4);
        h5.next.next=new ListNode(6);

        System.out.println(deleteDuplication(head));
    }

}
