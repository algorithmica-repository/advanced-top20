package com.alg.advtop20.grid;

import java.util.Arrays;

public class DisjointSet2 {
	private int[] parent;
	private int[] rank;
	int nsets;
	
	public DisjointSet2(int[][] in) {
		int n = in.length * in.length;
		parent = new int[n];
		rank = new int[n];
		for(int i = 0; i < n; ++i) {
			parent[i] = i;
			rank[i] = 1;
		}
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1)
					++nsets;
			}
		}
	}
	
	public int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	public void union(int x, int y)  {
		int rx = find(x);
		int ry = find(y);
		if(rx != ry) {
			--nsets;
			if(rank[rx] > rank[ry])
				parent[ry] = rx;
			else if(rank[ry] > rank[rx])
				parent[rx] = ry;
			else {
				parent[ry] = rx;
				++rank[rx]; 
			}				
		}
	}
	
	public int getNumSets() {
		return nsets;
	}
	
	public void display() {
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
	}
	
}

