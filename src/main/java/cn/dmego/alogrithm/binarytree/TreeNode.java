package cn.dmego.alogrithm.binarytree;

import java.util.LinkedList;

/**
 * @author dmego
 * @date 2021/07/07 22:52
 */
public class TreeNode {

    public TreeNode left;
    public int data;
    public TreeNode right;

    public TreeNode() {

    }

    public TreeNode(int data) {
        this.data = data;
    }

    /**
     * 按照前序遍历的集合，构建二叉树
     * @param list
     * @return 二叉树 root node
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> list) {
        TreeNode node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        // 从 list 取出一个元素
        Integer data = list.removeFirst();
        // 如果元素为 null 说明该位置没有元素，直接返回即可
        if (data != null) {
            // 构建节点
            node = new TreeNode(data);
            // 递归创建节点的左子树
            node.left = createBinaryTree(list);
            // 递归创建节点的右子树
            node.right = createBinaryTree(list);
        }
        return node;
    }

}
