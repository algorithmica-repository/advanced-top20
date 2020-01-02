package com.alg.advtop20.graph.disjointset;

import java.util.Random;

public class Test {

	public static void test1(IDisjointSet dset) {
		Random r = new Random(0);
		int n = dset.size();
		dset.display();
		for (int i = 0; i < n; ++i) {
			int first = r.nextInt(n);
			int second = r.nextInt(n);
			dset.union(first, second);
			System.out.println("union of sets:" + first + "," + second);
			dset.display();
		}
		System.out.println(dset.size());
		System.out.println(dset.find(0));
		System.out.println(dset.find(n-1));
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		test1(new WeightedUnionDS(n));
	}

}
