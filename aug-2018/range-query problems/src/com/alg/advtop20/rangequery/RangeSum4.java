package com.alg.advtop20.rangequery;

import java.util.Arrays;
import java.util.Random;

class Range {
	int start;
	int end;
}
class TreeNode {
	Range key;
	int sum;
	TreeNode left;
	TreeNode right;
	public TreeNode(int l, int r) {
		this.key.start = l;
		this.key.end = r;
	}
	public TreeNode(int l, int r, int sum) {
		this(l,r);
		this.sum = sum;
	}
}
public class RangeSum {
	private TreeNode root;
	
	//O(n)
	public RangeSum(int[] in) {
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
		int current = i + in.length - 1;
		segment_tree[current] = x;
		while(current > 0) {
			if(current%2  != 0) 
				segment_tree[(current-1)/2] = segment_tree[current] + segment_tree[current+1];
			else 
				segment_tree[(current-1)/2] = segment_tree[current] + segment_tree[current-1];
			current = (current-1)/2;
		}
	}
	//O(log n)
/*	public int rangeSum_1(int i, int j) {
		i = i + in.length - 1;
		j = j + in.length - 1;
		int sum = 0;
		while(j-i > 1) {
			if(i%2 == 1) 
				sum += segment_tree[(i-1)/2];
			else
				sum += segment_tree[i];
			if(j%2 == 0) 
				sum += segment_tree[(j-1)/2];
			else
				sum += segment_tree[j];
			i = (i-1)/2;
			j = (j-1)/2;
		}
		return sum;
	}*/
	//O(log n)
	public int rangeSum(int i, int j) {
		i = i + in.length - 1;
		j = j + in.length - 1;
		int sum = 0;
		while(i <= j) {
			if(i%2 == 0) {
				sum += segment_tree[i];
				if(i == 0) break;
				++i;
			}
			if(j%2 == 1) {
				sum += segment_tree[j];
				--j;
			}
			i = (i-1)/2;
			j = (j-1)/2;
		}
		return sum;
	}
	
	
	public void display() {
		for(int i = 0; i < segment_tree.length; ++i)
			System.out.print(segment_tree[i]+ " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(n);
		System.out.println(Arrays.toString(in));
		RangeSum3 rs = new RangeSum3(in);
		rs.display();
		System.out.println(rs.rangeSum(0, n-1));
		System.out.println(rs.rangeSum(1, n-2));
		rs.update(1, -1);
		rs.display();
		System.out.println(rs.rangeSum(0, n-1));
		System.out.println(rs.rangeSum(1, n-2));
	}
	
}
