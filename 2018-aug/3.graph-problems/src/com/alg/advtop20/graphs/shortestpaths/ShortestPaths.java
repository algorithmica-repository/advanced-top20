package com.alg.advtop20.graphs.shortestpaths;

import com.alg.advtop20.graphs.GraphUtils;

public class ShortestPaths {

	private static int auxShortestPath1(int[][] in, int s, int t, int k) {
			if(s == t) return 0;
			if(k == 1) return in[s][t];
			int min = Integer.MAX_VALUE;
			for(int i = 0; i < in.length; ++i) {
				if(in[i][t] != 0 && in[i][t] != Integer.MAX_VALUE) {
					int tmp = auxShortestPath1(in, s, i, k-1);
					min = Math.min(min, getSum(in[i][t], tmp));
				}
			}
			System.out.println(s + " " + t + " " + k + " " + min);
			return min;
		}
		public static int shortestpath1(int[][] in, int s, int t) {
			return auxShortestPath1(in, s, t, in.length-1);
		}
		
		private static int getSum(int x, int y) {
			if(x == Integer.MAX_VALUE || y == Integer.MAX_VALUE) return Integer.MAX_VALUE;
			return x+y;
		}
		//TC:O(V ^ 3)   SC:O(V ^ 2)
		public static int shortestpath2(int[][] in, int s, int t) {
			int[][] mem = new int[in.length][in.length];
			for(int j = 1; j < in.length; ++j)
				mem[s][j] = 0;
			for(int i = 1; i < in.length; ++i)
				mem[i][1] = in[s][i];

			for(int j = 2; j < in.length; ++j) {
				for(int i = 0; i < in.length; ++i) {
					int min = Integer.MAX_VALUE;
					for(int v = 0; v < in.length; ++v) 
							min = Math.min(min, getSum(in[v][i], mem[v][j-1]));
					mem[i][j] = min;
				}
			}
			GraphUtils.printGraph(mem);
			return mem[t][in.length-1];
		}
		
		private static int auxShortestPath3(int[][] in, int s, int t, int k) {
			if(k == -1) return in[s][t];
			int inclusive = getSum(auxShortestPath3(in, s, k, k-1), auxShortestPath3(in, k, t, k-1));
			int exclusive = auxShortestPath3(in, s, t, k-1);
			int min = Math.min(inclusive, exclusive);
			System.out.println(s + " " + t + " " + k + " " + min);
			return min;
		}
		public static int shortestpath3(int[][] in, int s, int t) {
			return auxShortestPath3(in, s, t, in.length-1);
		}
		
		//TC:O(V ^ 3)   SC:O(V ^ 3)
		public static int shortestpath4(int[][] in, int s, int t) {
			int[][][] mem = new int[in.length+1][in.length][in.length];
			for(int i = 0; i < in.length; ++i)
				for(int j = 0; j < in.length; ++j)
					mem[0][i][j] = in[i][j];
			GraphUtils.printGraph(mem[0]);
			System.out.println();

			int min = Integer.MAX_VALUE, i, j, k;
			for(k = 1; k < in.length; ++k) {
				for(i = 0; i < in.length; ++i) {
					for(j = 0; j < in.length; ++j) {
						int inclusive = getSum(mem[k-1][i][k], mem[k-1][k][j]);
						int exclusive = mem[k-1][i][j];
						min = Math.min(inclusive, exclusive);
						mem[k][i][j] = min;
					}
				}
				GraphUtils.printGraph(mem[k]);
				System.out.println();
			}
			
			return mem[in.length][s][t];
		}
		
		public static void main(String[] args) {
			int n = Integer.parseInt(args[0]);
			int[][] in = GraphUtils.completeWeightedGraph1(n);
			GraphUtils.printGraph(in);
			/*System.out.println();
			System.out.println(shortestpath1(in, 0, n-1));
			System.out.println();
			System.out.println(shortestpath2(in, 0, n-1));
			System.out.println();*/
			//System.out.println(shortestpath3(in, 0, n-1));
			System.out.println();
			System.out.println(shortestpath4(in, 1, n));
		}




}
 