package com.alg.advtop20.graph;

import java.util.LinkedList;
import java.util.Queue;

import com.alg.advtop20.graph.disjointset.IDisjointSet;
import com.alg.advtop20.graph.disjointset.WeightedUnionDS;

public class CountFriendCircles {
	private static void dfs(int u, int[][] in, boolean[] visit) {
		visit[u] = true;
		for (int v = 0; v < in.length; ++v) {
			if (in[u][v] == 1) {
				if (visit[v] == false)
					dfs(v, in, visit);
			}
		}
	}

	private static void bfs(int u, int[][] in, boolean[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u);
		visit[u] = true;
		while (!q.isEmpty()) {
			u = q.remove();
			for (int v = 0; v < in.length; ++v) {
				if (in[u][v] == 1 && visit[v] == false) {
					q.add(v);
					visit[v] = true;
				}
			}
		}
	}

	// TC:O(n ^ 2) SC:O(n)
	public static int countCircles1(int[][] in) {
		int count = 0;
		boolean[] visit = new boolean[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false) {
				dfs(u, in, visit);
				++count;
			}
		}
		return count;
	}

	// TC:O(n ^ 2) SC:O(n)
	public static int countCircles2(int[][] in) {
		int count = 0;
		boolean[] visit = new boolean[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false) {
				bfs(u, in, visit);
				++count;
			}
		}
		return count;
	}

	// TC:O(n ^ 2) SC:O(n)
	public static int countCircles3(int[][] in) {
		IDisjointSet dset = new WeightedUnionDS(in.length);
		for (int i = 1; i < in.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (in[i][j] == 1)
					dset.union(i, j);
			}
		}
		return dset.size();
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.undirectedRandomGraph(n);
		//GraphUtils.display(in);
		System.out.println(countCircles1(in));
		System.out.println(countCircles2(in));
		System.out.println(countCircles3(in));

	}
}
