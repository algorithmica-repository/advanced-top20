package com.alg.advtop20.graphs.traversal;

public class Bipartitionable {

	private static boolean auxBipartite(int u, int[][] in, int[] visit, int groupid) {
		visit[u] = groupid;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1) {
				if(visit[v] == 0) { //forward edge
					if(auxBipartite(v, in, visit, 3-groupid)) return true;
				}
				else { //back edge
					if(visit[u] == visit[v]) return true;
				}
			}
		}
		return false;
	}
	//TC:O(V ^ 2)   SC:O(V)
	public static boolean isBipartite(int[][] in) {
		int[] visit = new int[in.length];
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == 0) {
				if(auxBipartite(u, in, visit, 1)) return false;
			}
		}
		System.out.print("Partition1:");
		for(int i = 0; i < visit.length; ++i)
			if(visit[i] == 1) System.out.print(i+" ");
		System.out.print("Partition2:");
		for(int i = 0; i < visit.length; ++i)
			if(visit[i] == 2) System.out.print(i+" ");
		return true;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.randomGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(isBipartite(in));
	}

}
