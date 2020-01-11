package com.alg.advtop20.graph;

public class AllPairsShortestPath {

	public static void allPairsShortestPath1(int[][] in) {
		for (int i = 0; i < in.length; ++i) {
			for (int j = 0; j < in.length; ++j) {
				System.out.println(shortestPath1(i, j, 0, in));
			}
		}
	}

	private static int getSum(int left, int right) {
		if (left == Integer.MAX_VALUE || right == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return left + right;
	}

	private static int shortestPath1(int i, int j, int k, int[][] in) {
		if (i == j || k == in.length)
			return in[i][j];
		int exclusive = shortestPath1(i, j, k + 1, in);
		int inclusive_left = shortestPath1(i, k, k + 1, in);
		int inclusive_right = shortestPath1(k, j, k + 1, in);
		return Math.min(exclusive, getSum(inclusive_left, inclusive_right));
	}

	public static void allPairsShortestPath2(int[][] in) {
		int[][][] mem = new int[in.length][in.length][in.length];
		for (int i = 0; i < in.length; ++i) {
			for (int j = 0; j < in.length; ++j) {
				System.out.println(shortestPath2(i, j, 0, in, mem));
			}
		}
	}

	private static int shortestPath2(int i, int j, int k, int[][] in, int[][][] mem) {
		if (i == j || k == in.length)
			return in[i][j];
		if (mem[i][j][k] != 0)
			return mem[i][j][k];
		int exclusive = shortestPath2(i, j, k + 1, in, mem);
		int inclusive_left = shortestPath2(i, k, k + 1, in, mem);
		int inclusive_right = shortestPath2(k, j, k + 1, in, mem);
		return mem[i][j][k] = Math.min(exclusive, getSum(inclusive_left, inclusive_right));
	}

	public static void allPairsShortestPath3(int[][] in) {
		int[][][] mem = new int[in.length][in.length][in.length + 1];
		for (int i = 0; i < in.length; ++i) {
			for (int j = 0; j < in.length; ++j) {
				mem[i][j][in.length] = in[i][j];
			}
		}
		for (int k = in.length - 1; k >= 0; --k) {
			for (int i = 0; i < in.length; ++i) {
				for (int j = 0; j < in.length; ++j) {
					int exclusive = mem[i][j][k + 1];
					int inclusive_left = mem[i][k][k + 1];
					int inclusive_right = mem[k][j][k + 1];
					mem[i][j][k] = Math.min(exclusive, getSum(inclusive_left, inclusive_right));
				}
			}
		}
		for (int i = 0; i < in.length; ++i) {
			for (int j = 0; j < in.length; ++j)
				System.out.println(mem[i][j][0]);
		}

	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.directedWeightedRandomGraph(n);
		GraphUtils.display(in);
		allPairsShortestPath1(in);
		System.out.println();
		allPairsShortestPath2(in);
		System.out.println();
		allPairsShortestPath3(in);
	}
	

}
