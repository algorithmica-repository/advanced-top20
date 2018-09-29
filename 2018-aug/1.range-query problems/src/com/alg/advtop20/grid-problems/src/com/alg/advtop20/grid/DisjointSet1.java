package com.alg.advtop20.grid;

import java.util.Arrays;

public class DisjointSet1 {
	private int[] parent;
	int nsets;
	
	public DisjointSet1(int[][] in) {
		int n = in.length * in.length;
		parent = new int[n];
		for(int i = 0; i < n; ++i) {
			parent[i] = i;
		}
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1)
					++nsets;
			}
		}
	}
	
	public int find(int x) {
		while(parent[x] != x)
			x = parent[x];
		return x;
	}
	
	public void union(int x, int y)  {
		int rx = find(x);
		int ry = find(y);
		if(rx != ry) {
			parent[rx] = ry;
			--nsets;
		}
	}
	
	public int getNumSets() {
		return nsets;
	}
	
	public void display() {
		System.out.println(Arrays.toString(parent));
		System.out.println(nsets);
	}	
	
}
