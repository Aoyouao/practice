package com.mla.Exercise;

@SuppressWarnings({"all"})
public class Leetcode02 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //对于链表问题，返回结果为头结点时，通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点 head。
        //防止头指针丢失
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        //进位数，存储两数相加大于10时的情况
        int carry = 0;
        while(l1 != null || l2 != null){
            int x = 0;
            int y = 0;
            if(l1 != null){
                x = l1.value;
            }
            if(l2 != null){
                y = l2.value;
            }
            int sum = x + y + carry;
            //计算进位数
            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);
            //将新链表的节点后移
            cur = cur.next;
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        //如果最后两个数，相加的时候有进位数的时候，就将进位数，赋予链表的新节点。
        //两数相加最多小于20，所以的的值最大只能时1
        if(carry == 1){
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }
}
