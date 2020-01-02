package com.alg.advtop20.graph;

import java.util.LinkedList;
import java.util.Queue;

import com.alg.advtop20.graph.disjointset.IDisjointSet;
import com.alg.advtop20.graph.disjointset.WeightedUnionDS;

public class GraphCycle {
	// TC:O(n ^ 2) SC:O(n)
	public static boolean hasCycle1(int[][] in) {
		boolean[] visit = new boolean[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false) {
				if (dfs(u, u, in, visit))
					return true;
			}
		}
		return false;
	}

	private static boolean dfs(int u, int parent, int[][] in, boolean[] visit) {
		visit[u] = true;
		for (int v = 0; v < in.length; ++v) {
			if (in[u][v] == 1) {
				if (visit[v] == false) { // action to be taken if v is not visited
					if (dfs(v, u, in, visit))
						return true;
				} else { // action to be taken if v is already visited
					if (v != parent)
						return true;
				}
			}
		}
		return false;
	}

	// TC:O(n ^ 2) SC:O(n)
	public static boolean hasCycle2(int[][] in) {
		int[] visit = new int[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == 0) {
				if (bfs(u, in, visit))
					return true;
			}
		}
		return false;
	}

	private static boolean bfs(int u, int[][] in, int[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u);
		visit[u] = 1;
		while (!q.isEmpty()) {
			u = q.remove();
			visit[u] = 2;
			for (int v = 0; v < in.length; ++v) {
				if (in[u][v] == 1) {
					if (visit[v] == 0) { // node not yet visited
						q.add(v);
						visit[v] = 1;
					} else { // node already visited
						if (visit[v] == 1)
							return true;
					}
				}
			}
		}
		return false;
	}

	// TC:O(n ^ 2) SC:O(n)
	public static boolean hasCycle3(int[][] in) {
		IDisjointSet dset = new WeightedUnionDS(in.length);
		for (int i = 1; i < in.length; ++i) {
			for (int j = 0; j < i; ++j) {
				if (in[i][j] == 1) {
					if (dset.find(i) == dset.find(j))
						return true;
					dset.union(i, j);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.undirectedRandomGraph(n);
		GraphUtils.display(in);
		System.out.println(hasCycle1(in));
		System.out.println(hasCycle2(in));
		System.out.println(hasCycle3(in));

	}
}
