package com.alg.advtop20.graphs;

import java.util.Arrays;

class DisjointSet {
	private int[] parent;
	private int[] rank;
	int nsets;
	
	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		for(int i = 0; i < n; ++i) {
			parent[i] = i;
			rank[i] = 1;
		}
		nsets = n;
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
public class CountFriendCircles1 {

	public static int countCircles(int[][] in) {
		DisjointSet dset = new DisjointSet(in.length);
		for(int i = 1; i < in.length; ++i) {
			for(int j = 0; j < i; ++j) {
				if(in[i][j] == 1)
					dset.union(i, j);
			}
		}
		return dset.getNumSets();
	}
}
