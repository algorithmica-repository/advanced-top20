package com.alg.advtop20.graph;

import java.util.LinkedList;
import java.util.Queue;

public class CountFriendCircles {

	public static int countCircles1(int[][] in) {
		int ncircles = 0;
		boolean[] visit = new boolean[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false) {
				// System.out.println(u);
				auxDfs(u, in, visit);
				++ncircles;
			}
		}
		return ncircles;
	}

	private static void auxDfs(int u, int[][] in, boolean[] visit) {
		visit[u] = true;
		for (int v = 0; v < in.length; ++v) {
			if (in[u][v] == 1) {
				if (visit[v] == false)
					auxDfs(v, in, visit);
			}
		}
	}

	public static int countCircles2(int[][] in) {
		int ncircles = 0;
		boolean[] visit = new boolean[in.length];
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false) {
				auxBfs(u, in, visit);
				++ncircles;
			}
		}
		return ncircles;
	}

	private static void auxBfs(int u, int[][] in, boolean[] visit) {
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

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.undirectedRandomGraph(n);
		GraphUtils.display(in);
		System.out.println(countCircles1(in));
		System.out.println(countCircles2(in));

	}

}
