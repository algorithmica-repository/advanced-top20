package com.alg.advtop20.quadtree;

import java.util.ArrayList;
import java.util.List;

class Rectangle {
	int row1, col1, row2, col2;

	public Rectangle(int row1, int col1, int row2, int col2) {
		super();
		this.row1 = row1;
		this.col1 = col1;
		this.row2 = row2;
		this.col2 = col2;
	}

	public boolean contains(Point p) {
		if (p.x >= col1 && p.x <= col2 && p.y >= row1 && p.y <= row2)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "Rectangle [row1=" + row1 + ", col1=" + col1 + ", row2=" + row2 + ", col2=" + col2 + "]";
	}

}

class Point {
	int x, y;

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Point p = (Point) obj;
		return this.x == p.x && this.y == p.y;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}

}

class PRQuadNode {
	Rectangle region;
	List<Point> points;
	PRQuadNode q1, q2, q3, q4;

	public PRQuadNode(Rectangle region) {
		this.region = region;
		this.points = new ArrayList<Point>();
	}

	public PRQuadNode(Rectangle region, List<Point> points) {
		this.region = region;
		this.points = points;
	}

	public boolean contains(Point p) {
		return region.contains(p);
	}

	// split and distribute points across 4 children
	public void split() {
		int rmid = (region.row1 + region.row2) / 2;
		int cmid = (region.col1 + region.col2) / 2;

		Rectangle r1 = new Rectangle(region.row1, region.col1, rmid, cmid);
		Rectangle r2 = new Rectangle(region.row1, cmid + 1, rmid, region.col2);
		Rectangle r3 = new Rectangle(rmid + 1, region.col1, region.row2, cmid);
		Rectangle r4 = new Rectangle(rmid + 1, cmid + 1, region.row2, region.col2);
		List<Point> p1 = new ArrayList<Point>();
		List<Point> p2 = new ArrayList<Point>();
		List<Point> p3 = new ArrayList<Point>();
		List<Point> p4 = new ArrayList<Point>();

		for (Point point : points) {
			if (r1.contains(point))
				p1.add(point);
			else if (r2.contains(point))
				p2.add(point);
			else if (r3.contains(point))
				p3.add(point);
			else
				p4.add(point);
		}
		q1 = new PRQuadNode(r1, p1);
		q2 = new PRQuadNode(r2, p2);
		q3 = new PRQuadNode(r3, p3);
		q4 = new PRQuadNode(r4, p4);
		points = null;

	}

	public boolean isLeaf() {
		return q1 == null && q2 == null && q3 == null && q4 == null;
	}

	@Override
	public String toString() {
		return region + ":" + points;
	}

	public boolean intersect(Rectangle r) {
		if (r.row1 > region.row2 || r.row2 < region.row1 || r.col1 > region.col2 || r.col2 < region.col1)
			return false;
		return true;
	}
}

public class PRQuadTree {
	private PRQuadNode root;
	public static int MAX_POINTS_PER_REGION = 10;

	public PRQuadTree(Rectangle region) {
		root = new PRQuadNode(region);
	}

	public PRQuadTree(Rectangle region, int max_points) {
		root = new PRQuadNode(region);
		MAX_POINTS_PER_REGION = max_points;
	}

	public void add(Point point) {
		auxAdd(root, point);
	}

	private boolean auxAdd(PRQuadNode root, Point p) {
		if (root.isLeaf()) {
			if (!root.contains(p))
				return false;
			// add the point if current node can accommodate
			if (root.points.size() < PRQuadTree.MAX_POINTS_PER_REGION) {
				root.points.add(p);
				return true;
			}
			root.split();
		}

		if (auxAdd(root.q1, p))
			return true;
		if (auxAdd(root.q2, p))
			return true;
		if (auxAdd(root.q3, p))
			return true;
		return auxAdd(root.q4, p);
	}

	public List<Point> rangeQuery(Rectangle r) {
		List<Point> points = new ArrayList<Point>();
		auxQuery(root, r, points);
		return points;
	}

	private void auxQuery(PRQuadNode root, Rectangle r, List<Point> points) {
		if (root == null || !root.intersect(r))
			return;
		if (root.isLeaf()) {
			for (Point point : root.points)
				if (r.contains(point))
					points.add(point);
			return;
		}
		auxQuery(root.q1, r, points);
		auxQuery(root.q2, r, points);
		auxQuery(root.q3, r, points);
		auxQuery(root.q4, r, points);
	}

	public List<Point> kNearestNeighbors(Point p, int k) {
		List<Point> points = new ArrayList<Point>();
		return points;
	}

	public void display() {
		auxDisplay(root, 0, "Root");
	}

	private void auxDisplay(PRQuadNode root, int nspaces, String type) {
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
