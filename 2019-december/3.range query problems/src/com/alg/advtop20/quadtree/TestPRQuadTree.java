package com.alg.advtop20.quadtree;

import java.util.Random;

public class TestPRQuadTree {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		PRQuadTree tree = new PRQuadTree(new Rectangle(0, 0, 100, 100), 5);

		Random r = new Random(100);
		for (int i = 0; i < n; ++i) {
			int x = r.nextInt(100) + 1;
			int y = r.nextInt(100) + 1;
			Point point = new Point(x, y);
			tree.add(point);
			System.out.println(point);
			tree.display();
		}
		System.out.println(tree.rangeQuery(new Rectangle(0, 0, 50, 50)));
		System.out.println(tree.rangeQuery(new Rectangle(30, 30, 50, 50)));
		System.out.println(tree.rangeQuery(new Rectangle(50, 50, 70, 70)));

	}

}
