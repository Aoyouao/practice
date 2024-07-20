package com.mla.stack;

import java.util.LinkedList;

public class E03InfixToSufffix {
    public static void main(String[] args) {
        System.out.println(infixToSuffix("(a+b)*c"));
        System.out.println(infixToSuffix("(a+b*c-d)*e"));
        System.out.println(infixToSuffix("a*(b+c)"));
        System.out.println(infixToSuffix("a*b+c"));
    }
    static int priority(char c){
        return switch(c){
            case '+','-'->1;
            case '*','/'->2;
            case '('->0;
            default -> throw new RuntimeException();
        };
    }
    static String infixToSuffix(String exp){
        LinkedList<Character> stack = new LinkedList<>();
        StringBuilder sb = new StringBuilder(exp.length());
        for(int i =0;i < exp.length();i++){
            char c = exp.charAt(i);
            switch(c){
                case '*','/','+','-':
                    if(stack.isEmpty()){
                        stack.push(c);
                    }else {
                        if (priority(c) > priority(stack.peek())) {
                        stack.push(c);
                    } else {
                        while (!stack.isEmpty() && priority(c) <= priority(stack.peek())) {
                            sb.append(stack.pop());
                        }
                        stack.push(c);
                    }
                    }
                    break;
                case '(':
                    stack.push(c);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    }
                    stack.pop();
                    break;
                default:
                    sb.append(c);
            }
        }
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
