package com.alg.advtop20.quadtree;

import java.util.ArrayList;
import java.util.List;

class QuadNode {
	Rectangle region;
	QuadNode q1, q2, q3, q4;
	Integer value;

	public QuadNode(Rectangle region) {
		this.region = region;
	}

	public QuadNode(Rectangle region, Integer value) {
		this.region = region;
		this.value = value;
	}

	public boolean intersect(Rectangle r) {
		if (r.row1 > region.row2 || r.row2 < region.row1 || r.col1 > region.col2 || r.col2 < region.col1)
			return false;
		return true;
	}

	public boolean isLeaf() {
		return q1 == null && q2 == null && q3 == null && q4 == null;
	}

	@Override
	public String toString() {
		return region + ":" + value;
	}

}

public class MXQuadTree {
	private QuadNode root;

	public MXQuadTree(int[][] in) {
		root = build(in, new Rectangle(0, 0, in.length - 1, in.length - 1));
	}

	private QuadNode build(int[][] in, Rectangle r) {
		if (r.row1 > r.row2 || r.col1 > r.col2)
			return null;
		if (r.row1 == r.row2 && r.col1 == r.col2)
			return new QuadNode(r, in[r.row1][r.col1]);
		QuadNode tmp = new QuadNode(r);
		int rmid = (r.row1 + r.row2) / 2;
		int cmid = (r.col1 + r.col2) / 2;
		tmp.q1 = build(in, new Rectangle(r.row1, r.col1, rmid, cmid));
		tmp.q2 = build(in, new Rectangle(r.row1, cmid + 1, rmid, r.col2));
		tmp.q3 = build(in, new Rectangle(rmid + 1, r.col1, r.row2, cmid));
		tmp.q4 = build(in, new Rectangle(rmid + 1, cmid + 1, r.row2, r.col2));
		return tmp;
	}

	public void update(int i, int j, int x) {
		auxUpdate(root, i, j, x);
	}

	private boolean auxUpdate(QuadNode root, int i, int j, int x) {
		if (root == null)
			return false;
		if (i == root.region.row1 && j == root.region.col1 && i == root.region.row2 && j == root.region.col2) {
			root.value = x;
			return true;
		}
		if (auxUpdate(root.q1, i, j, x))
			return true;
		if (auxUpdate(root.q2, i, j, x))
			return true;
		if (auxUpdate(root.q3, i, j, x))
			return true;
		return auxUpdate(root.q4, i, j, x);
	}

	public List<Integer> rangeQuery(Rectangle r) {
		List<Integer> values = new ArrayList<Integer>();
		auxQuery(root, r, values);
		return values;
	}

	private void auxQuery(QuadNode root, Rectangle r, List<Integer> values) {
		if (root == null || !root.intersect(r))
			return;
		if (root.isLeaf()) {
			values.add(root.value);
			return;
		}
		auxQuery(root.q1, r, values);
		auxQuery(root.q2, r, values);
		auxQuery(root.q3, r, values);
		auxQuery(root.q4, r, values);
	}

	public void display() {
		auxDisplay(root, 0, "Root");
	}

	private void auxDisplay(QuadNode root, int nspaces, String type) {
		if (root == null)
			return;
		for (int i = 0; i < nspaces; ++i)
			System.out.print(' ');
		System.out.println("(" + type + ")" + root);
		auxDisplay(root.q1, nspaces + 4, "q1");
		auxDisplay(root.q2, nspaces + 4, "q2");
		auxDisplay(root.q3, nspaces + 4, "q3");
		auxDisplay(root.q4, nspaces + 4, "q4");
	}
}
