package com.alg.advtop20.rangequery.twod;

import java.util.Arrays;
import java.util.Random;

class Rectangle {
	int row1;
	int col1;
	int row2;
	int col2;
	public Rectangle(int row1, int col1, int row2, int col2) {
		super();
		this.row1 = row1;
		this.col1 = col1;
		this.row2 = row2;
		this.col2 = col2;
	}	
}
class TreeNode {
	Rectangle key;
	int sum;
	TreeNode left, right;
	public TreeNode(int row1, int col1, int row2, int col2) {
		key = new Rectangle(row1, col1, row2, col2);
	}
	public TreeNode(int row1, int col1, int row2, int col2, int sum) {
		this(row1, col1, row2, col2);
		this.sum = sum;
	}
}
class KDTree {
	private TreeNode root;
	
	//O(n ^ 2)
	public KDTree(int[][] in) {
		root = build(in, 0, 0, in.length-1,  in.length-1, 1);
	}
	private int get(TreeNode tmp) {
		return tmp == null?0:tmp.sum;
	}
	private TreeNode build(int[][] in, int row1, int col1, int row2, int col2, int depth) {
		if(row1 > row2 || col1 > col2) return null;
		if(row1 == row2 && col1 == col2) {
			return new TreeNode(row1, col1, row2, col2,  in[row1][col1]);
		}
		TreeNode tmp = new TreeNode(row1, col1, row2, col2);

		if(depth % 2 == 1) {
			int cmid = col1 + (col2-col1)/2;
			tmp.left = build(in, row1, col1, row2, cmid, depth+1);
			tmp.right = build(in, row1, cmid+1, row2, col2, depth+1);
		}
		else {
			int rmid = row1 + (row2-row1)/2;
			tmp.left = build(in, row1, col1, rmid, col2, depth+1);
			tmp.right = build(in, rmid+1, col1, row2, col2, depth+1);
		}
		tmp.sum = get(tmp.left) + get(tmp.right);
		return tmp;
	}
	//O(log (2) n^2)
	public void update(int i, int j, int x) {
		auxUpdate(root, i, j, x, 1);
	}
	private void auxUpdate(TreeNode root, int i, int j, int x, int depth) {
		if(i == root.key.row1 && j == root.key.col1 && i == root.key.row2 && j == root.key.col2) {
			root.sum = x;
			return;
		}
		if(depth % 2 == 1) {
			int cmid = root.key.col1 + (root.key.col2 - root.key.col1) / 2;
			if(j <= cmid) auxUpdate(root.left, i, j, x, depth+1);
			else auxUpdate(root.right, i, j, x, depth+1);
		}
		else {
			int rmid = root.key.row1 + (root.key.row2 - root.key.row1) / 2;
			if(i <= rmid) auxUpdate(root.left, i, j, x, depth+1);
			else auxUpdate(root.right, i, j, x, depth+1);
		}
		root.sum = get(root.left) + get(root.right);

	}
	//O(log (2) n^2)
	public int rangeSum(int row1, int col1, int row2, int col2) {
		return auxRangeSum(root, row1, col1, row2, col2, 1);
	}
	private int auxRangeSum(TreeNode root, int row1, int col1, int row2, int col2, int depth) {
		if(root.key.row1 == row1 && root.key.col1 == col1 && root.key.row2 == row2 && root.key.col2 == col2) 
			return root.sum;
		if(depth % 2 == 1) {
			int cmid = root.key.col1 + (root.key.col2 - root.key.col1) / 2;
			if(col2 <= cmid)
				return auxRangeSum(root.left, row1, col1, row2, col2, depth+1);
			else if(col1 > cmid)
				return auxRangeSum(root.right, row1, col1, row2, col2, depth+1);
			else
				return auxRangeSum(root.left, row1, col1, row2, cmid, depth+1) + auxRangeSum(root.right, row1, cmid+1, row2, col2, depth+1);
		} else {
			int rmid = root.key.row1 + (root.key.row2 - root.key.row1) / 2;
			if(row2 <= rmid)
				return auxRangeSum(root.left, row1, col1, row2, col2, depth+1);
			else if(row1 > rmid)
				return auxRangeSum(root.right, row1, col1, row2, col2, depth+1);
			else
				return auxRangeSum(root.left, row1, col1, rmid, col2, depth+1) + auxRangeSum(root.right, rmid+1, col1, row2, col2, depth+1);
		}

	}
	
	public void display() {
		auxDisplay(root, 0, "Root");
	}
	private void auxDisplay(TreeNode root, int nspaces, String type) {
		if(root == null) return;
		for(int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println("(" + root.key.row1 + "," + root.key.col1 + "," + root.key.row2 + "," + root.key.col2 + "," + root.sum + "," + type + ")");
		auxDisplay(root.left, nspaces + 4, "L");		
		auxDisplay(root.right, nspaces + 4, "R");
	}	
	
}
public class RangeSum4 {
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				in[i][j] = r.nextInt(n);
		for(int[] tmp:in)
			System.out.println(Arrays.toString(tmp));
		KDTree tree = new KDTree(in);
		tree.display();
		System.out.println(tree.rangeSum(0, 0, n-1,n-1));
		System.out.println(tree.rangeSum(1, 1, n-2,n-2));
		System.out.println();
		tree.update(1,1, -1);
		tree.display();
		System.out.println(tree.rangeSum(0, 0, n-1,n-1));
		System.out.println(tree.rangeSum(1, 1, n-2,n-2));
	}
	
}


