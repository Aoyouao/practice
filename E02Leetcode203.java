package com.mla.Exercise;

import java.util.List;

public class E02Leetcode203 {
    /*public Node removeElements(Node head, int val) {
        Node s = new Node(-1, head);
        Node p1 = s;
        Node p2 = s.next;
        while(p2 != null){
            if(p2.value == val){
                p1.next = p2.next;
                p2 = p2.next;
            }else{
                p1 = p1.next;
                p2 = p2.next;
            }
        }
        return s.next;
    }*/
    public Node removeElements(Node p, int val) {
        if(p == null){
            return p;
        }
        if(p.value == val){
            return removeElements(p.next,val);
        }else{
            p.next = removeElements(p.next,val);
            return p;
        }
    }
}
class Node{
    int value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(64);
        sb.append("[");
        Node p = this;
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
