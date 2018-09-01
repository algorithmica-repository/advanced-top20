package com.alg.advtop20.rangequery;

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
class QuadNode {
	Rectangle key;
	int sum;
	QuadNode q1, q2, q3, q4;
	public QuadNode(int row1, int col1, int row2, int col2) {
		key = new Rectangle(row1, col1, row2, col2);
	}
	public QuadNode(int row1, int col1, int row2, int col2, int sum) {
		this(row1, col1, row2, col2);
		this.sum = sum;
	}
}
public class QuadTree {
	private QuadNode root;
	
	//O(n ^ 2)
	public QuadTree(int[][] in) {
		root = build(in, 0, 0, in.length-1,  in.length-1);
	}
	private int get(QuadNode tmp) {
		return tmp == null?0:tmp.sum;
	}
	private QuadNode build(int[][] in, int row1, int col1, int row2, int col2) {
		if(row1 > row2 || col1 > col2) return null;
		if(row1 == row2 && col1 == col2) return new QuadNode(row1, col1, row2, col2, in[row1][col1]);
		int rmid = row1 + (row2-row1)/2;
		int cmid = col1 + (col2-col1)/2;
		QuadNode tmp = new QuadNode(row1, col1, row2, col2);
		tmp.q1 = build(in, row1, col1, rmid, cmid);
		tmp.q2= build(in, row1, cmid+1, rmid, col2);
		tmp.q3 = build(in, rmid+1, col1, row2, cmid);
		tmp.q4= build(in, rmid+1, cmid+1, row2, col2);
		tmp.sum = get(tmp.q1) + get(tmp.q2) + get(tmp.q3) + get(tmp.q4);
		return tmp;
	}
	//O(log n)
	public void update(int i, int x) {
		auxUpdate(root, i, x);
	}
	private void auxUpdate(TreeNode root, int i, int x) {
		//todo: boundary case handling
		int m = root.key.start + (root.key.end - root.key.start) / 2;
		if(i == root.key.start && i == root.key.end) {
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
		if(root.key.start == i && root.key.end == j) return root.sum;
		int m = root.key.start + (root.key.end - root.key.start) / 2;
		if(j <= m)
			return auxRangeSum(root.left, i, j);
		if(i > m)
			return auxRangeSum(root.right, i, j);
		return auxRangeSum(root.left, i, m) + auxRangeSum(root.right, m+1, j);
	}
	
	public void display() {
		auxDisplay(root, 0, "R");
	}
	private void auxDisplay(QuadNode root, int nspaces, String type) {
		if(root == null) return;
		for(int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println("(" + root.key.row1 + "," + root.key.col1 + "," + root.key.row2 + "," + root.key.col2 + "," + root.sum + "," + type + ")");
		auxDisplay(root.q1, nspaces + 4, "q1");		
		auxDisplay(root.q2, nspaces + 4, "q2");
		auxDisplay(root.q3, nspaces + 4, "q3");		
		auxDisplay(root.q4, nspaces + 4, "q4");
	}	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			for(int j = 0; j < n; ++j)
				in[i][j] = r.nextInt(n);
		for(int[] tmp:in)
			System.out.println(Arrays.toString(tmp));
		QuadTree tree = new QuadTree(in);
		tree.display();
		/*System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
		tree.update(1, -1);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
*/	}
	
}
