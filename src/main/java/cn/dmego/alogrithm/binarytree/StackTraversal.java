package cn.dmego.alogrithm.binarytree;

import com.sun.source.tree.Tree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 迭代前序，中序，后序 遍历
 * @author dmego
 * @date 2021/07/08 07:48
 */
public class StackTraversal {


    public static void preTraversal(TreeNode node) {

    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));
        TreeNode treeNode = TreeNode.createBinaryTree(list);

        System.out.print("前序遍历：");
        System.out.println();
        System.out.print("中序遍历：");
        System.out.println();
        System.out.print("后序遍历：");

    }
}
