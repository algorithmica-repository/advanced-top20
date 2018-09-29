package com.alg.advtop20.graphs.traversal;


public class CycleDetection {

	private static boolean dfs(int u, int parent, int[][] in, boolean[] visit) {
		visit[u] = true;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1){
				if(visit[v] == false) { //forward edge
					if(dfs(v, u, in, visit)) return true;
				}
				else { //back edge
					if(parent != v) return true;
				}
			}
		}
		return false;	
	}
	//TC:O(V ^ 2)   SC:O(V)
	public static boolean hasCycle1(int[][] in) {
		boolean[] visit = new boolean[in.length];
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == false) {
				if(dfs(u, u, in, visit)) return true;
			}
		}
		return false;
	}
	
	public static boolean hasCycle2(int[][] in) {
		DisjointSet dset = new DisjointSet(in.length);
		for(int i = 1; i < in.length; ++i) {
			for(int j = 0; j < i; ++j) {
				if(in[i][j] == 1) {
					//is i and j are connected previously?
					if(dset.find(i) != dset.find(j)) 
						dset.union(i, j);
					else return true;
				}
			}
		}
		return false;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.randomGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(hasCycle2(in));
		in = GraphUtils.completeGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(hasCycle2(in));
	}

}
