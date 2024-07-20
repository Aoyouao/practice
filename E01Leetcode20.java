package com.mla.stack;

public class E01Leetcode20 {
    public static void main(String[] args) {
        E01Leetcode20 s = new E01Leetcode20();
        System.out.println(s.isValid("([{}])"));
        System.out.println("----------");
        System.out.println(s.isValid("[)"));
        System.out.println(s.isValid("("));
        System.out.println("------------");
        System.out.println(s.isValid("}]]"));
    }
    public boolean isValid(String s) {
        ArrayStack3<Character> stack = new ArrayStack3<>(s.length());
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(c == '('){
                stack.pushStack(')');
            }else if(c == '{'){
                stack.pushStack('}');
            }else if(c == '['){
                stack.pushStack(']');
            }else{
                if(!stack.isEmpty() && c == stack.peek()){
                    stack.popStack();
                }else{
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
