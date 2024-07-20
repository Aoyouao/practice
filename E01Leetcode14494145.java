package com.mla.binarytree;


import java.util.LinkedList;

@SuppressWarnings({"all"})
//前中后遍历 非递归
public class E01Leetcode14494145 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(new TreeNode(4),2,null),
                1,
                new TreeNode(new TreeNode(5),3,new TreeNode(6))
        );


        LinkedList<TreeNode> stack = new LinkedList<>();

        TreeNode curr = root;//代表当前节点
        TreeNode pop = null;
        /*while(curr != null || !stack.isEmpty()){
            if(curr != null){
                System.out.println("去："+curr.val);//前序遍历  Leetcode144
                stack.push(curr);
                curr = curr.left;
            }else{
                pop = stack.pop();
                System.out.println("回："+pop.val);//中序遍历 Leetcode94
                curr = pop.right;
            }
        }*/

        //后序遍历 左右值 Leetcode145
        /*while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode peek = stack.peek();
                if(peek.right == null || peek.right == pop){
                    pop = stack.pop();
                    System.out.println(pop.val);
                }else{
                    curr = peek.right;
                }
            }
        }*/


        //前中后序遍历
        while(curr != null || !stack.isEmpty()){
            if(curr != null){
                stack.push(curr);
                //待处理左子树
                System.out.println("前："+curr.val);
                curr = curr.left;
            }else{
                TreeNode peek = stack.peek();
                //没有右子树
                if(peek.right == null ){
                    System.out.println("中："+peek.val);
                    pop = stack.pop();
                    System.out.println("后："+pop.val);
                }
                //右子树处理完成
                else if(peek.right == pop) {
                    pop = stack.pop();
                    System.out.println("后："+pop.val);
                }

                //待处理右子树
                else{
                    System.out.println("中："+peek.val);
                    curr = peek.right;
                }
            }
        }
    }
}
