package com.alg.advtop20.graphs.traversal.countregions;

import java.util.Arrays;
import java.util.Random;

class DisjointSet {
	private int[] parent;
	private int[] rank;
	int nsets;
	
	public DisjointSet(int[][] in) {
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


public class CountRegions {

	private static void auxRegions(int i, int j, int[][] in, boolean[][] visit) {
		if(i < 0 || i >= in.length || j < 0 || j >= in.length) return;
		if(in[i][j] == 0 || visit[i][j] == true) return;
		visit[i][j] = true;
		auxRegions(i, j-1, in, visit);
		auxRegions(i, j+1, in, visit);
		auxRegions(i-1, j, in, visit);
		auxRegions(i+1, j, in, visit);
	}
	public static int countRegions1(int[][] in) {
		int count = 0;
		boolean[][] visit = new boolean[in.length][in.length];
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1 && visit[i][j] == false) {
					++count;
					auxRegions(i, j, in, visit);
				}
			}
		}
		return count;
	}
	
	private static void connect(DisjointSet2 dset, int[][] in, int i1, int j1, int i2, int j2) {
		int n = in.length;
		if(i2 < 0 || j2 < 0 || in[i2][j2] == 0) return;
		dset.union(i1*n+j1, i2*n+j2);
	}
	public static int countRegions2(int[][] in) {
		DisjointSet dset = new DisjointSet(in);
		dset.display();
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1) {
					connect(dset, in, i, j, i-1, j);
					connect(dset, in, i, j, i, j-1);
				}
			}
		}
		return dset.getNumSets();
	}
	
	public static void print(int[][] in) {
		for(int[] tmp:in) {
			System.out.println(Arrays.toString(tmp));
		}
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int k = 1; k <= 2*n; ++k) {
			int ri = r.nextInt(n);
			int rj = r.nextInt(n);
			in[ri][rj] = 1;
		}
		print(in);
		System.out.println(countRegions1(in));
		System.out.println(countRegions2(in));

	}
}
