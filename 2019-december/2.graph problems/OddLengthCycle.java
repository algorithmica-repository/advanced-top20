package com.alg.advtop20.graph;

public class OddLengthCycle {
	// TC:O(n ^ 2) SC:O(n)
	public static boolean hasOddCycle1(int[][] in) {
		int[] visit = new int[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == 0) {
				if (dfs(u, u, 1, in, visit))
					return true;
			}
		}
		System.out.println("partition1:");
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == 1)
				System.out.print(u +",");
		}
		System.out.println();
		System.out.println("partition2:");
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == 2)
				System.out.print(u +",");
		}
		System.out.println();
		return false;
	}

	private static boolean dfs(int u, int parent, int color, int[][] in, int[] visit) {
		visit[u] = color;
		for (int v = 0; v < in.length; ++v) {
			if (in[u][v] == 1) {
				if (visit[v] == 0) { //forward edge 
					if (dfs(v, u, color==1?2:1, in, visit))
						return true;
				} else { //back edge
					if (v != parent && visit[u] == visit[v])
						return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.undirectedRandomGraph(n);
		GraphUtils.display(in);
		System.out.println(hasOddCycle1(in));
	}

}
