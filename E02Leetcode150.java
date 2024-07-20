package com.mla.stack;

import java.util.LinkedList;

public class E02Leetcode150 {
    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }
    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String s : tokens) {
            switch(s){
                case "+":
                    Integer b = stack.pop();
                    Integer a = stack.pop();
                    stack.push(a + b);
                    break;
                case "-":
                    Integer d = stack.pop();
                    Integer c = stack.pop();
                    stack.push(c - d);
                    break;
                case "*":
                    Integer m = stack.pop();
                    Integer n = stack.pop();
                    stack.push(m * n);
                    break;
                case "/":
                    Integer i = stack.pop();
                    Integer j = stack.pop();
                    stack.push(j / i);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }
}
