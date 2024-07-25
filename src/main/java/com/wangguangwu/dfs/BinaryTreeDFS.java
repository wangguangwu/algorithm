package com.wangguangwu.dfs;

import java.util.Stack;

/**
 * Binary tree DFS implementation.
 *
 * @author wangguangwu
 */
public class BinaryTreeDFS {

    /**
     * Pre-order DFS using recursion.
     *
     * @param root treeNode
     */
    public void preOrder(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.println(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    /**
     * Pre-order DFS using stack.
     *
     * @param root treeNode
     */
    public void preOrderIterative(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BinaryTreeDFS treeDfs = new BinaryTreeDFS();
        System.out.println("Binary tree pre-order DFS traversal (recursive):");
        treeDfs.preOrder(root);
        System.out.println("\nBinary tree pre-order DFS traversal (iterative):");
        treeDfs.preOrderIterative(root);
    }

}
