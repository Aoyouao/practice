package com.mla.Exercise;

public class Leetcode23 {
    public ListNode mergeTwoLists1(ListNode p1, ListNode p2) {
        ListNode s = new ListNode(-1,null);
        ListNode p =s;
        while(p1 != null && p2 != null){
            if(p1.value < p2.value){
                p.next = p1;
                p1 = p1.next;
            }else{
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        if(p1 != null){
            p.next = p1;
        }
        if(p2 != null){
            p.next = p2;
        }
        return s.next;
    }
    public ListNode mergeKLists(ListNode[] lists){
        if(lists.length == 0){
            return null;
        }
        return split(lists,0, lists.length -1);
    }
    private ListNode split(ListNode[] lists,int i,int j){
        if(i == j){
            return lists[i];
        }
        int m = (i + j)>>>1;
        ListNode left = split(lists,i,m);
        ListNode right = split(lists,m+1,j);
        return mergeTwoLists1(left,right);
    }
}
