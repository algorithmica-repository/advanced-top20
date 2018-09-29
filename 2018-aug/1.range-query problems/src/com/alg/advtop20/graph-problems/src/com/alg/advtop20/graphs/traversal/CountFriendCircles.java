package com.alg.advtop20.graphs.traversal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
public class CountFriendCircles {

	//TC:O(V ^ 2)   SC:O(V)
	public static int countCircles1(int[][] in) {
		DisjointSet dset = new DisjointSet(in.length);
		for(int i = 1; i < in.length; ++i) {
			for(int j = 0; j < i; ++j) {
				if(in[i][j] == 1)
					dset.union(i, j);
			}
		}
		return dset.getNumSets();
	}
	
	private static void dfs(int u, int[][] in, boolean[] visit) {
		visit[u] = true;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1 && visit[v] == false)
				dfs(v, in, visit);
		}
	}
	private static void bfs(int u, int[][] in, boolean[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u);
		visit[u] = true;
		while(! q.isEmpty()) {
			u = q.remove();
			for(int v = 0; v < in.length; ++v) {
				if(in[u][v] == 1 && visit[v] == false) {
					q.add(v);
					visit[v] = true;
				}
			}
		}
	}
	//TC:O(V ^ 2)   SC:O(V)
	public static int countCircles2(int[][] in) {
		int count = 0;
		boolean[] visit = new boolean[in.length];
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == false) {
				dfs(u, in, visit);
				//bfs(u, in, visit);
				++count;
			}
		}
		return count;
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.randomGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(countCircles1(in));
		System.out.println(countCircles2(in));
		in = GraphUtils.completeGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(countCircles1(in));
		System.out.println(countCircles2(in));

	}
}
