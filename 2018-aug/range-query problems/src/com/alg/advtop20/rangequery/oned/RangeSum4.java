package com.alg.advtop20.rangequery.oned;

import java.util.Arrays;
import java.util.Random;

class Range {
	int l;
	int r;
	public Range(int l, int r) {
		this.l = l;
		this.r = r;
	}
}
class TreeNode {
	Range key;
	int sum;
	TreeNode left;
	TreeNode right;
	public TreeNode(int l, int r) {
		key = new Range(l, r);
	}
	public TreeNode(int l, int r, int sum) {
		this(l,r);
		this.sum = sum;
	}
}
class SegmentTree {
	private TreeNode root;
	
	//O(n)
	public SegmentTree(int[] in) {
		root = build(in, 0, in.length-1);
	}
	private int get(TreeNode tmp) {
		return tmp == null?0:tmp.sum;
	}
	private TreeNode build(int[] in, int l, int r) {
		if(l > r) return null;
		if(l == r) return new TreeNode(l, r, in[l]);
		int m = l + (r-l)/2;
		TreeNode tmp = new TreeNode(l,r);
		tmp.left = build(in, l, m);
		tmp.right = build(in, m+1, r);
		tmp.sum = get(tmp.left) + get(tmp.right);
		return tmp;
	}
	//O(log n)
	public void update(int i, int x) {
		auxUpdate(root, i, x);
	}
	private void auxUpdate(TreeNode root, int i, int x) {
		//todo: boundary case handling
		int m = root.key.l + (root.key.r - root.key.l) / 2;
		if(i == root.key.l && i == root.key.r) {
			root.sum = x;
			return;
		}
		if(i <= m)
			auxUpdate(root.left, i, x);
		else
			auxUpdate(root.right, i, x);
		root.sum = get(root.left) + get(root.right); 
	}
	//O(log n)
	public int rangeSum(int i, int j) {
		return auxRangeSum(root, i, j);
	}
	private int auxRangeSum(TreeNode root, int i, int j) {
		//todo: boundary case handling
		if(root.key.l == i && root.key.r == j) return root.sum;
		int m = root.key.l + (root.key.r - root.key.l) / 2;
		if(j <= m)
			return auxRangeSum(root.left, i, j);
		if(i > m)
			return auxRangeSum(root.right, i, j);
		return auxRangeSum(root.left, i, m) + auxRangeSum(root.right, m+1, j);
	}
	
	public void display() {
		auxDisplay(root, 0, 'R');
	}
	private void auxDisplay(TreeNode root, int nspaces, char type) {
		if(root == null) return;
		for(int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println("(" + root.key.l + "," + root.key.r+ "," + root.sum + "," + type + ")");
		auxDisplay(root.left, nspaces + 4, 'L');		
		auxDisplay(root.right, nspaces + 4, 'R');
	}
}
public class RangeSum5 {	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(n);
		System.out.println(Arrays.toString(in));
		SegmentTree tree = new SegmentTree(in);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
		tree.update(1, -1);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
	}
	
}
