package com.alg.advtop20.graphs.traversal;

import java.util.LinkedList;
import java.util.Queue;

public class CountFriendCircles2 {

	private static void dfs(int u, int[][] in, boolean[] visit) {
		visit[u] = true;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1 && visit[v] == false)
				dfs(v, in, visit);
		}
	}
	private static void bfs(int u, int[][] in, boolean[] visit) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(u);
		visit[u] = true;
		while(! q.isEmpty()) {
			u = q.remove();
			for(int v = 0; v < in.length; ++v) {
				if(in[u][v] == 1 && visit[v] == false) {
					q.add(v);
					visit[v] = true;
				}
			}
		}
	}
	public static int countCircles(int[][] in) {
		int count = 0;
		boolean[] visit = new boolean[in.length];
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == false) {
				dfs(u, in, visit);
				++count;
			}
		}
		return count;
	}
}
