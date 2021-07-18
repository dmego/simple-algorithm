package cn.dmego.alogrithm.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author dmego
 * @date 2021/07/13 07:13
 */
public class StackTraversal2 {

    /**
     迭代前序遍历：按照 根节点，根节点左子树，根节点右子树顺序进行遍历
     1. 按照栈 先进后出 的特点，根节点的右子树必须要比左子树先入栈，这样出栈的就先是左子树
     2. 先将 根节点入栈，然后 循环 先判断根节点是否有右子树，有就入栈，再判断根节点是否有左子树，有就入栈
     3. 在循环中，先将栈顶元素（相对的根节点：例如右子树的根节点）出栈，然后再进行右左节点的非空判断，入栈
     4. 循环中出栈的结果就是前序遍历的顺序
     */
    public static void preTraversal(TreeNode node) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        // 首先将 根节点加入 stack
        stack.push(node);
        // 循环结束条件，stack 为 空
        while (!stack.isEmpty()) {
            TreeNode tempNode = stack.pop();
            System.out.print(tempNode.data + " ");
            if (tempNode.right != null) {
                stack.push(tempNode.right);
            }
            if (tempNode.left != null) {
                stack.push(tempNode.left);
            }
        }
    }

    /**
     迭代中序遍历：按照根节点左子(点)树，根节点，根节点右子(点)树遍历
     1. 访问根节点的左孩子，如果左孩子不为空，将左孩子入栈，循环此操作
     2. 当访问到某节点的左孩子为空，则出栈栈顶，指针指向栈顶的右孩子，重复 1 操作
     */
    private static void inTraversal(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode temp = root;
        while (!stack.isEmpty() || temp != null) {
            while (temp != null) {
                stack.push(temp);
                temp = temp.left;
            }
            temp = stack.pop();
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
    }

    /**
     迭代后序遍历：按照左 右 根 的顺序遍历，如果倒过来就是 根 右 左
     这和前序遍历的根 左 右 很像，所有我们可以按照前序遍历的思路去写完成
     根 右 左的遍历，然后将结果数组逆序一下就可以了
     1. 先将 根节点入栈
     2. 如果栈不为空，栈顶出栈，栈顶值加入结果集
     3. 栈顶节点的左节点不为空，入栈，
     4. 栈顶节点的右节点不为空，入栈
     5. 循环执行 2 3 4（之后循环体中先出栈的是右节点，这样就完成了 根 右 左的遍历）
     6. 逆序结果集
     */
    private static void postTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            result.add(root.data);
            if (root.left != null) {
                stack.push(root.left);
            }
            if (root.right != null) {
                stack.push(root.right);
            }
        }
        Collections.reverse(result);
        System.out.println(result);
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
