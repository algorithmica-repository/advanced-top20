package com.alg.advtop20.segmentree;

import java.util.Random;

public class TestSegmentTree {

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(100);
		for (int i = 0; i < n; ++i)
			in[i] = r.nextInt(5);
		ISegmentTree rs = new LinkedSegmentTree(in);
		rs.display();
		rs.update(0, -2);
		rs.display();
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				System.out.println(rs.rangeSum(i, j));
			}
		}

	}

}
