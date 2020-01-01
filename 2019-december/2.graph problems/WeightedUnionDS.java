package com.alg.advtop20.graph;

import java.util.Arrays;

public class WeightedUnionDS implements IDisjointSet {
	private int size;
	private int[] parent;
	private int[] weights;	
	
	public WeightedUnionDS(int n) {
		parent = new int[n];
		weights = new int[n];
		for(int i = 0; i < n; ++i) {
			parent[i] = i;
			weights[i] = 1;
		}
		size = n;
	}

	@Override
	public int find(int x) {
		while(parent[x] != x)
			x = parent[x];
		return x;
	}

	@Override
	public void union(int x, int y) {
		int idx = find(x);
		int idy = find(y);
		if(idx == idy) return ;
		--size;
		if(weights[idx] > weights[idy]) {
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
