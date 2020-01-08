 package com.alg.advtop20.graph;

public class AllSimplePaaths {

	public static void allSimplePaths(int s, int t, int[][] in) {
		boolean[] visit = new boolean[in.length];
		auxPaths(s, t, in, visit, s+"");
	}
	private static void auxPaths(int u, int t, int[][] in, boolean[] visit, String  path) {
		if(u == t) {
			System.out.println(path);
			return;
		}
		visit[u] = true;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1) {
				if(visit[v] == false)
					auxPaths(v, t, in, visit, path+"->"+v);
			}
		}
		visit[u] = false;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.undirectedCompleteGraph(n);
		GraphUtils.display(in);
		allSimplePaths(0, n-1, in);
	}

}
