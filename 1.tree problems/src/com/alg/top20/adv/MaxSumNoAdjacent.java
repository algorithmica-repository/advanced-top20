package com.alg.top20.adv;
import java.util.HashMap;


public class MaxSumNoAdjacent {

	public static long maxSum1(TreeNode root) {
		if(root == null) return 0;
		if(root.left == null && root.right == null)
			return root.data;
		long exclusive_profit = maxSum1(root.left) + maxSum1(root.right);
		long inclusive_profit = root.data;
		TreeNode lc = root.left;
		TreeNode rc = root.right;
		if(lc != null) {
			inclusive_profit += maxSum1(lc.left) + maxSum1(lc.right);
		}
		if(rc != null) {
			inclusive_profit += maxSum1(rc.left) + maxSum1(rc.right);
		}
		return Math.max(exclusive_profit, inclusive_profit);
	}
	
	//TC:O(n)   SC:O(n)
	public static long maxSum2(TreeNode root) {
		HashMap<TreeNode, Long> mem = new HashMap<TreeNode, Long>();
		auxSum2(root, mem);
		return mem.get(root);
	}
	
	public static 
	long auxSum2(TreeNode root, HashMap<TreeNode, Long> mem) {
		if(root == null) return 0;
		if(root.left == null && root.right == null)
			return root.data;
		long exclusive_profit = 0;
		exclusive_profit += mem.get(root.left) != null?mem.get(root.left):auxSum2(root.left, mem);
		exclusive_profit += mem.get(root.right) != null? mem.get(root.right):auxSum2(root.right, mem);
		
		long inclusive_profit = root.data;
		TreeNode lc = root.left;
		TreeNode rc = root.right;
		if(lc != null) {
			inclusive_profit += mem.get(lc.left)!=null?mem.get(lc.left):auxSum2(lc.left, mem);
			inclusive_profit += mem.get(lc.right)!=null?mem.get(lc.right):auxSum2(lc.right, mem);
		}
		if(rc != null) {
			inclusive_profit += mem.get(rc.left)!=null?mem.get(rc.left):auxSum2(rc.left, mem);
			inclusive_profit += mem.get(rc.right)!=null?mem.get(rc.right):auxSum2(rc.right, mem);
		}
		long res = Math.max(exclusive_profit, inclusive_profit);
		mem.put(root, res);
		return res;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		TreeNode root = BinaryTreeUtils.createBinaryTree(n);
		//BinaryTreeUtils.displayTree2(root);
		//System.out.println(maxSum1(root));
		System.out.println(maxSum2(root));
	}

}
