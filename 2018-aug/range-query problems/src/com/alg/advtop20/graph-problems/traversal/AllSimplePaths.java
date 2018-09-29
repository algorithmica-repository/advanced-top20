package com.alg.advtop20.graphs.traversal;

public class AllSimplePaths {

	private static void auxPaths(int s, int t, int[][] in, boolean[] visit, String prefix) {
		if(s == t) {
			System.out.println(prefix +"->" + t);
			return;
		}
		visit[s] = true;
		for(int v = 0; v < in.length; ++v) {
			if(in[s][v] == 1 && visit[v] == false)
				auxPaths(v, t, in, visit, prefix+"->"+s);
		}
		visit[s] = false;
	}
	//TC:O(V!) ~ O(V ^ V)  SC:O(V)
	public static void allSimplePaths(int s, int t, int[][] in) {
		boolean[] visit = new boolean[in.length];
		auxPaths(s, t, in, visit, "");
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.completeGraph(n);
		GraphUtils.printGraph(in);
		allSimplePaths(0, n-1, in);
	}

}
