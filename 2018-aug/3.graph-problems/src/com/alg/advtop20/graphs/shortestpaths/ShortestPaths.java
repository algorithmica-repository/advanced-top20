package com.alg.advtop20.graphs.shortestpaths;

import com.alg.advtop20.graphs.GraphUtils;

public class ShortestPaths {

	private static int auxShortestPath1(int[][] in, int s, int t, int k) {
		if(s == t) return 0;
		if(k == 1) return in[s][t];
		int min = Integer.MAX_VALUE;
		for(int v = 0; v < in.length; ++v) {
			if(in[s][v] != 0 && in[s][v] != Integer.MAX_VALUE) {
				int tmp = auxShortestPath1(in, v, t, k-1);
				tmp = (tmp == Integer.MAX_VALUE?0:tmp);
				min = Math.min(min, in[s][v] + tmp);
			}
		}
		System.out.println(s + " " + t + " " + k + " " + min);
		return min;
	}
	public static int shortespath1(int[][] in, int s, int t) {
		return auxShortestPath1(in, s, t, in.length-1);
	}
	
	public static int shortespath2(int[][] in, int s, int t) {
		int[][] mem = new int[in.length][in.length];
		for(int i = 0; i < in.length; ++i)
			mem[i][1] = in[i][t];
		for(int j = 1; j < in.length; ++j)
			mem[t][j] = 0;
		for(int j = 2; j < in.length; ++j) {
			for(int i = 0; i < in.length; ++i) {
				int min = Integer.MAX_VALUE;
				for(int v = 0; v < in.length; ++v) {
						int tmp = mem[v][j-1];
						tmp = (tmp == Integer.MAX_VALUE?0:tmp);
						min = Math.min(min, in[i][v] + tmp);
				}
				mem[i][j] = min;
			}
		}
		GraphUtils.printGraph(mem);
		return mem[s][t];	
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.completeWeightedGraph(n);
		GraphUtils.printGraph(in);
		System.out.println();
		System.out.println(shortespath2(in, 0, n-1));

	}

}
 