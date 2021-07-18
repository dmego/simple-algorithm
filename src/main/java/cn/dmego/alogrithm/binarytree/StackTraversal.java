package cn.dmego.alogrithm.binarytree;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 迭代前序，中序，后序 遍历
 * @author dmego
 * @date 2021/07/08 07:48
 */
public class StackTraversal {

    /**
     * 使用 stack 实现前序遍历
     * 1. 访问根节点
     * 2. 遍历左子树
     * 3. 遍历右子树
     */
    public static void preTraversal(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                // 输出根节点
                System.out.print(node.data +" ");
                // 根节点入栈
                stack.push(node);
                // 遍历根节点的左子树
                node = node.left;
            }
            // 如果 node == null 说明没有左节点，此时要遍历右节点
            if (!stack.isEmpty()) {
                // pop 出来的是 node 的根节点
                node = stack.pop();
                // 遍历根节点的右子树
                node = node.right;
            }
        }
    }

    /** 使用 stack 实现中序遍历
     * 1. 遍历左子树
     * 2. 访问根节点
     * 3. 遍历右子树

     1. 每遍历到一个节点就加入到栈中
     2. 对这个节点的 左子树 重复 1 的过程，直到 左子树(左节点)为空
     3. 栈出栈，将出栈节点值加入结果集(访问根节点)
     4. 对出栈节点的右子树重复 1，2 的过程


     */
    public static void inTraversal(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                // 根节点入栈
                stack.push(node);
                // 遍历根节点的左子树
                node = node.left;
            }
            // 如果 node == null 说明没有左节点了，此时要输出根节点，再遍历右节点
            if (!stack.isEmpty()) {
                // pop 出来的是 node 的根节点
                node = stack.pop();
                System.out.print(node.data +" ");
                // 遍历根节点的右子树
                node = node.right;
            }
        }
    }

    /** 使用 stack 实现后序遍历
     * 1. 遍历左子树
     * 2. 遍历右子树
     * 3. 访问根节点

     1. 每遍历到一个节点就加入到栈中
     2. 对这个节点的 左子树 重复 1 的过程，直到 左子树(左节点)为空
     3. 栈出栈，判断该出栈节点的右子树是否为 空，或者 是否已经访问过了 ，是则输出该出栈节点
     4. 否则，将该出栈节点再次压入栈，访问其右子树，重复 1 2 过程
     */
    public static void postTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 记录上一个访问的节点，用来判断根节点的右节点是否已经访问过了
        TreeNode pre = null;
        while (!stack.isEmpty() || root != null) {
            // 对 root 节点的左子树不断遍历入栈
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            // 如果左节点为空，出栈左节点的根节点
            root = stack.pop();
            // 如果根节点的右节点为空，或者该右节点已经访问过了，此时访问(输出)根节点
            if (root.right == null || root.right == pre) {
                System.out.print(root.data + " ");
                // 记录一下当前节点已经被访问
                pre = root;
                // 当节点输出后，需要继续出栈，root 指向 null
                root = null;

            } else {
                // 如果根节点的右节点不为空, 将根节点再次入栈
                stack.push(root);
                // 指针指向根节点右子树，继续不断遍历该右节点的左子树
                root = root.right;
            }
        }
    }





    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(3,2,9,null,null,10,null,null,8,null,4));
        TreeNode treeNode = TreeNode.createBinaryTree(list);

        System.out.print("前序遍历：");
        preTraversal(treeNode);
        System.out.println();
        System.out.print("中序遍历：");
        inTraversal(treeNode);
        System.out.println();
        System.out.print("后序遍历：");
        postTraversal(treeNode);
    }
}
