package com.mla.queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class E01Leetcode102103 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(
                new TreeNode(
                        new TreeNode(4),
                        2,
                        new TreeNode(5)
                ),
                1,
                new TreeNode(
                        new TreeNode(6),
                        3,
                        new TreeNode(7)
                )
        );
        E01Leetcode102103 e01Leetcode102 = new E01Leetcode102103();
        System.out.println(e01Leetcode102.levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedListQueue<TreeNode> queue = new LinkedListQueue<>();
        queue.offer(root);
        int c1 = 1;
        boolean ood = true;
        while (!queue.isEmpty()) {
            int c2 = 0;
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < c1; i++) {
                TreeNode n = queue.pool();
                if(ood){
                    level.offerLast(n.val);
                }else{
                    level.offerFirst(n.val);
                }
                if (n.left != null) {
                    queue.offer(n.left);
                    c2++;
                }
                if (n.right != null) {
                    queue.offer(n.right);
                    c2++;
                }
            }
            ood = !ood;
            result.add(level);
            c1 = c2;
        }
        return result;
    }
}
