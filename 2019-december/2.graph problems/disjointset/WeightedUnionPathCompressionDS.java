package com.alg.advtop20.graph.disjointset;

import java.util.Arrays;

public class WeightedUnionPathCompressionDS implements IDisjointSet {
	private int size;
	private int[] parent;
	private int[] weights;

	public WeightedUnionPathCompressionDS(int n) {
		parent = new int[n];
		weights = new int[n];
		for (int i = 0; i < n; ++i) {
			parent[i] = i;
			weights[i] = 1;
		}
		size = n;
	}

	@Override
	public int find(int x) {
		int current = x;
		while (parent[current] != current)
			current = parent[current];
		while (parent[x] != x) {
			int tmp = parent[x];
			parent[x] = current;
			x = tmp;
		}
		return current;
	}

	@Override
	public void union(int x, int y) {
		int idx = find(x);
		int idy = find(y);
		if (idx == idy)
			return;
		--size;
		if (weights[idx] > weights[idy]) {
			parent[idy] = idx;
			weights[idx] += weights[idy];
		} else {
			parent[idx] = idy;
			weights[idy] += weights[idx];
		}
	}

	@Override
	public void display() {
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(weights));
	}

	@Override
	public int size() {
		return size;
	}

}
