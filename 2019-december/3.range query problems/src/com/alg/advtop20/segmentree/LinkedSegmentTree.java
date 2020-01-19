package com.alg.advtop20.segmentree;

import java.util.Arrays;

class SegmentNode {
	int start;
	int end;
	int sum;
	SegmentNode left;
	SegmentNode right;

	public SegmentNode(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public SegmentNode(int start, int end, int sum) {
		this(start, end);
		this.sum = sum;
	}

	public boolean intersect(int i, int j) {
		if (j < start || i > end)
			return false;
		return true;
	}
}

public class LinkedSegmentTree implements ISegmentTree {
	private SegmentNode root;
	private int[] in;

	// O(n)
	public LinkedSegmentTree(int[] in) {
		this.in = in;
		root = build(in, 0, in.length - 1);
	}

	private int get(SegmentNode left) {
		return left == null ? 0 : left.sum;
	}

	private SegmentNode build(int[] in, int l, int r) {
		if (l > r)
			return null;
		if (l == r)
			return new SegmentNode(l, r, in[l]);
		SegmentNode tmp = new SegmentNode(l, r);
		int m = l + (r - l) / 2;
		tmp.left = build(in, l, m);
		tmp.right = build(in, m + 1, r);
		tmp.sum = get(tmp.left) + get(tmp.right);
		return tmp;
	}

	// O(log n)
	public void update(int i, int x) {
		in[i] = x;
		auxUpdate(root, i, x);
	}

	private void auxUpdate(SegmentNode root, int i, int x) {
		if (i == root.start && i == root.end) {
			root.sum = x;
			return;
		}
		int m = (root.start + root.end) / 2;
		if (i <= m)
			auxUpdate(root.left, i, x);
		else
			auxUpdate(root.right, i, x);
		root.sum = get(root.left) + get(root.right);
	}

	// O(log n)
	public int rangeSum(int i, int j) {
		return auxRangeSum(root, i, j);
	}

	private int auxRangeSum(SegmentNode root, int i, int j) {
		if (i > j || !root.intersect(i, j))
			return 0;
		if (root.left == null && root.right == null)
			return root.sum;
		return auxRangeSum(root.left, i, j) + auxRangeSum(root.right, i, j);
	}

	public void display() {
		System.out.println(Arrays.toString(in));
		auxDisplay(root, 0, "Root");
	}

	private void auxDisplay(SegmentNode root, int nspaces, String type) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println("(" + root.start + "," + root.end + "," + root.sum + "," + type + ")");
		auxDisplay(root.left, nspaces + 4, "L");
		auxDisplay(root.right, nspaces + 4, "R");
	}
}
