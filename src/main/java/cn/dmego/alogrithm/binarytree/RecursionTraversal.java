package cn.dmego.alogrithm.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 递归 前序，后序，中序 遍历
 * @author dmego
 * @date 2021/07/07 22:56
 */
public class RecursionTraversal {

    /**
     * 递归前序遍历
     * @param node
     */
    public static void preTraversal(TreeNode node) {
        // 这个判断必须写在递归程序里，这是递归的的终止条件：左节点或右节点为空的时，直接返回
        if (node == null) {
            return;
        }
        System.out.print(node.data + " ");
        preTraversal(node.left);
        preTraversal(node.right);
    }

    /**
     * 递归中序遍历
     * @param node
     */
    public static void inTraversal(TreeNode node) {
        if (node == null) {
           return;
        }
        inTraversal(node.left);
        System.out.print(node.data + " ");
        inTraversal(node.right);
    }

    /**
     * 递归中序遍历
     * @param node
     */
    public static void inTraversal2(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inTraversal2(node.left, list);
        list.add(node.data);
        inTraversal2(node.right, list);
    }

    /**
     * 递归后序遍历
     * @param node
     */
    public static void postTraversal(TreeNode node) {
        if(node == null) {
            return;
        }
        postTraversal(node.left);
        postTraversal(node.right);
        System.out.print(node.data + " ");
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));
        TreeNode treeNode = TreeNode.createBinaryTree(list);

        System.out.print("前序遍历：");
        preTraversal(treeNode);
        System.out.println();
        System.out.print("中序遍历：");
        inTraversal(treeNode);
        List<Integer> result = new ArrayList<>();
        inTraversal2(treeNode, result);
        System.out.println();
        System.out.print("后序遍历：");
        postTraversal(treeNode);

    }
}
