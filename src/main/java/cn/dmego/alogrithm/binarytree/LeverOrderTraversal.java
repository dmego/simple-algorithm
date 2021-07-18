package cn.dmego.alogrithm.binarytree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 二叉树层序遍历
 * @author dmego
 * @date 2021/07/18 08:49
 */
public class LeverOrderTraversal {

    /**
     利用队列先进先出的特性，将每一个节点的左右两个节点加入队列最后
     */
    public static void leverOrderTraversal(TreeNode root) {
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            root = deque.removeFirst();
            System.out.print(root.data + " ");
            if (root.left != null) {
                deque.addLast(root.left);
            }
            if (root.right != null) {
                deque.addLast(root.right);
            }
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));
        TreeNode treeNode = TreeNode.createBinaryTree(list);

        System.out.print("层序遍历：");
        leverOrderTraversal(treeNode);
    }

}
