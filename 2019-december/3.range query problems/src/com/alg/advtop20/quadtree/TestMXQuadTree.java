package com.alg.advtop20.quadtree;

import java.util.Arrays;
import java.util.Random;

public class TestMXQuadTree {

	public static void displayGrid(int[][] in) {
		for (int[] tmp : in)
			System.out.println(Arrays.toString(tmp));
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for (int i = 0; i < n; ++i)
			for (int j = 0; j < n; ++j)
				in[i][j] = r.nextInt(n);
		MXQuadTree tree = new MXQuadTree(in);
		displayGrid(in);
		tree.display();
		tree.update(1, 1, -1);
		displayGrid(in);
		tree.display();
		System.out.println(tree.rangeQuery(new Rectangle(1, 1, n - 2, n - 2)));
		System.out.println(tree.rangeQuery(new Rectangle(0, 0, n - 1, 0)));
		System.out.println(tree.rangeQuery(new Rectangle(0, n - 1, n - 1, n - 1)));
		System.out.println(tree.rangeQuery(new Rectangle(0, 0, 0, n - 1)));
	}

}
