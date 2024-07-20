package com.mla.Exercise;

public class E01Leetcode206 {
    public static void main(String[] args) {
        ListNode o5 = new ListNode(5, null);
        ListNode o4 = new ListNode(4, o5);
        ListNode o3 = new ListNode(3, o4);
        ListNode o2 = new ListNode(2, o3);
        ListNode o1 = new ListNode(1, o2);
        System.out.println(o1);
        ListNode n1 = new E01Leetcode206().reverseLast(o1);
        System.out.println(n1);
    }
    public ListNode reverseLast(ListNode p){
        if(p == null || p.next == null){
            return p;
        }
        ListNode listNode = reverseLast(p.next);
        p.next.next = p;
        p.next = null;
        return listNode;
    }
}
    /*static class List {//容器类 记录新链表
        ListNode head;

        public List(ListNode head) {
            this.head = head;
        }

        public void addFirst(ListNode first) {
            first.next = head;
            head = first;
        }

        public ListNode removeFirst() {
            ListNode first = head;
            if (first != null) {
                head = first.next;
            }
            return first;
        }
    }
    public ListNode reverseLast(ListNode head){
            List list1 = new List(head);
            List list2 = new List(null);
            while(true){
                ListNode listNode = list1.removeFirst();
                if(listNode ==  null){
                    break;
                }
                list2.addFirst(listNode);
            }
            return list2.head;
    }

}*/
class ListNode{
    int value;
    ListNode next;

    public ListNode(int value, ListNode next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        ListNode p = this;
        while(p != null){
            sb.append(p.value);
            if(p.next != null){
                sb.append(",");
            }
            p = p.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
