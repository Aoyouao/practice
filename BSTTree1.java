package com.mla.binarytree;

//Binary Search Tree 二叉搜索数
public class BSTTree1 {

    BSTNode root;
    static class BSTNode{
        int key;
        Object value;
        BSTNode left;
        BSTNode right;

        public BSTNode(int key) {
            this.key = key;
        }

        public BSTNode(int key, Object value, BSTNode left, BSTNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public BSTNode(int key, Object value) {
            this.key = key;
            this.value = value;
        }
    }

    //查找关键字对应的值
    public Object get(int key){
        BSTNode p = root;
        while(p != null){
            if(key < p.key){
                p = p.left;
            }else if(key > p.key){
                p = p.right;
            }else{
                return p.value;
            }
        }
        return null;
    }

    //查找最小关键字对应的值
    public Object min(){
        return min(root);
    }
    private Object min(BSTNode node){
        if(node == null){
            return null;
        }
        while(node.left != null){
            node = node.left;
        }
        return node.value;
    }

    //查找最大关键字对应的值
    public Object max(){
        return max(root);
    }
    private Object max(BSTNode node){
        if(node == null){
            return null;
        }
        while(node.right != null){
            node = node.right;
        }
        return node.value;
    }

    //添加(存储关键字和对应值)
    //1.key 存在 更新
    //2.key 不存在 添加

    public void put(int key,Object value){
        BSTNode p = root;
        BSTNode parent = null;
        while(p != null){
            parent = p;
            if(key < p.key){
                p = p.left;
            }else if(key > p.key){
                p = p.right;
            }else{
                p.value = value;
                return;
            }
        }

        if(parent == null){
            root = new BSTNode(key,value);
            return;
        }

        if(key < parent.key){
            parent.left = new BSTNode(key,value);
        }else{
            parent.right = new BSTNode(key,value);
        }
    }

    //查找关键字的前驱值 successor
    public Object successor(int key){
        BSTNode p = root;
        BSTNode ancestorFromLeft = null;
        while(p != null){
            if(key < p.key){
                p = p.left;
            }else if(p.key < key){
                ancestorFromLeft = p;
                p = p.right;
            }else{
                break;
            }
        }
        //没有找到节点
        if(p == null){
            return null;
        }

        //找到节点 情况1：节点有左子树，此时前任就是左子树的最大值
        if(p.left != null){
            return max(p.left);
        }
        //找到节点 情况2：节点没有左子树，离他最近的，自左而来的祖先就是前任
        if(ancestorFromLeft != null){
            return ancestorFromLeft.value;
        }else{
            return null;
        }
    }

    //查找关键字的后继值 predecessor
    public Object predecessor(int key){
        BSTNode p = root;
        BSTNode ancestorFromRight = null;
        while(p != null){
            if(key < p.key){
                ancestorFromRight = p;
                p = p.left;
            }else if(p.key < key){
                p = p.right;
            }else{
                break;
            }
        }
        //没有找到节点
        if(p == null){
            return null;
        }
        //找到节点 情况1：节点有右子树，右子树的最小值就是后任
        if(p.right != null){
            return min(p.right);
        }
        //找到节点 情况2：节点没有右子树，离节点最近的，自右而来的祖先就是后任
        if(ancestorFromRight != null){
            return ancestorFromRight.value;
        }else{
            return null;
        }
    }

    //根据关键字删除
    public Object delete(int key){
        BSTNode p = root;
        BSTNode parent = root;
        while(p != null){
            if(key < p.key){
                parent = p;
                p = p.left;
            }else if(p.key < key){
                parent = p;
                p = p.right;
            }else{
                break;
            }
        }
        if(p == null){
            return null;
        }
        //删除节点
        if(p.left == null){
            shift(parent,p,p.right);//情况1：删除节点没有左孩子，将右孩子托孤给parent
        }else if(p.right == null){
            shift(parent,p,p.left);//情况2：删除节点没有右孩子，将左孩子托孤给parent
        }else{
            //情况4
            //4.1 找到被删除节点的后继节点
            BSTNode s = p.right;
            BSTNode sParent = p;//后继节点的父亲
            while(s.left != null){
                sParent = s;
                s = s.left;
            }
            //后继节点就是s
            if(sParent != p) {
                //4.2 删除节点和后继节点不相邻，处理后继的后事
                shift(sParent,s,s.right);//不可能有左孩子
                s.right = p.right;
            }
            //4.3 后继节点取代被删除节点
            shift(parent,p,s);
            s.left = p.left;
        }
        return p.value;
    }

    //托孤方法
    //parent 被删除节点的父亲节点
    //deleted 被删除节点
    //child 被顶上去的节点
    private void shift(BSTNode parent,BSTNode deleted,BSTNode child){
        if(parent == null){
            root = child;
        }else if(deleted == parent.left){
            parent.left = child;
        }else{
            parent.right = child;
        }
    }
}
