package com.alg.top20.adv;
public class TreeTraversal {

	public static void preorder(TreeNode root) {
		while (root != null) {
			if (root.left == null) {
				System.out.println(root.data);
				root = root.right;
			} else {
				TreeNode tmp = root.left;
				while (tmp.right != null && tmp.right != root)
					tmp = tmp.right;
				if (tmp.right == null) {
					System.out.println(root.data);
					tmp.right = root;
					root = root.left;
				} else {
					tmp.right = null;
					root = root.right;
				}
			}
		}
	}
	
	public static void inorder(TreeNode root) {
		while (root != null) {
			if (root.left == null) {
				System.out.println(root.data);
				root = root.right;
			} else {
				TreeNode tmp = root.left;
				while (tmp.right != null && tmp.right != root)
					tmp = tmp.right;
				if (tmp.right == null) {
					tmp.right = root;
					root = root.left;
				} else {
					System.out.println(root.data);
					tmp.right = null;
					root = root.right;
				}
			}
		}
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		TreeNode root = BinaryTreeUtils.createBinaryTree(n);
		BinaryTreeUtils.displayTree2(root);
		preorder(root);
	}

}
