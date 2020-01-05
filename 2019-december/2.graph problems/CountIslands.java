package com.alg.advtop20.graph;

public class CountIslands {

	public static int countIslands1(int[][] in) {
		int n = in.length;
		int m = in[0].length;
		boolean[][] visit = new boolean[n][m];
		int count = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (in[i][j] == 1 && visit[i][j] == false) {
					dfs(i, j, in, visit);
					++count;
				}
			}
		}
		return count;
	}

	private static void dfs(int i, int j, int[][] in, boolean[][] visit) {
		visit[i][j] = true;
		int n = in.length;
		int m = in[0].length;
		int[][] neighbours = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		for (int k = 0; k < neighbours.length; ++k) {
			int r = neighbours[k][0];
			int c = neighbours[k][1];
			if (i + r >= 0 && i + r < n && j + c >= 0 && j + c < m && in[i + r][j + c] == 1
					&& visit[i + r][j + c] == false)
				dfs(i + r, j + c, in, visit);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
