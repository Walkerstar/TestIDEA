package mcw.test.offer;

import mcw.test.common.RandomListNode;

/**
 * @author mcw 2020\1\17 0017-16:37
 *
 * 输入一个复杂链表，返回结果为复制后复杂链表的head。
 */
public class Test25 {

    public RandomListNode Clone(RandomListNode pHead){
        if(pHead==null){
            return null;
        }
        RandomListNode currentNode=pHead;

        //1.复制每个结点，如复制结点A得到A1，将结点A1插到结点A后面
        while (currentNode != null){
            RandomListNode cloneNode=new RandomListNode(currentNode.val);
            RandomListNode nextNode=currentNode.next;
            //结点的插入
            currentNode.next=cloneNode;
            cloneNode.next=nextNode;
            currentNode=nextNode;
        }

        //2.重新遍历链表，复制老结点的随机指针给新结点，如A1.random=A.random.next;
        currentNode=pHead;
        while (currentNode !=null){
            currentNode.next.random=currentNode.random==null?null:currentNode.random.next;
            currentNode=currentNode.next.next;
        }

        //3.拆分链表，将链表拆分为原链表和复制后的链表
        currentNode=pHead;
        RandomListNode pCloneHead=pHead.next;
        while(currentNode!=null){
            RandomListNode cloneNode=currentNode.next;
            currentNode.next=cloneNode.next;
            cloneNode.next=cloneNode.next==null?null:cloneNode.next.next;
            currentNode=currentNode.next;
        }
        return pCloneHead;
    }
}
