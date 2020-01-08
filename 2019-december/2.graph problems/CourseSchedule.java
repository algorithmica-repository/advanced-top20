package com.alg.advtop20.graph;

import java.util.LinkedList;
import java.util.List;

public class CourseSchedule {

	// TC:O(n ^ 2)
	// SC:O(n)
	public static void courseSchedule1(int[][] in) {
		int[] indegree = new int[in.length];
		for (int i = 0; i < in.length; ++i) {
			for (int j = 0; j < in.length; ++j) {
				if (in[i][j] == 1)
					++indegree[j];
			}
		}
		int i, j;
		while (true) {
			for (i = 0; i < in.length; ++i) {
				if (indegree[i] == 0) {
					System.out.print(i + "->");
					indegree[i] = -1;
					break;
				}
			}
			if (i == in.length)
				break;
			for (j = 0; j < in.length; ++j) {
				if (in[i][j] == 1)
					--indegree[j];
			}
		}
		System.out.println();
	}

	// TC:O(n ^ 2)
	// SC:O(n)
	public static void courseSchedule2(int[][] in) {
		boolean[] visit = new boolean[in.length];
		List<Integer> list = new LinkedList<Integer>();
		for (int u = 0; u < in.length; ++u) {
			if (visit[u] == false)
				dfs(u, in, visit, list);
		}
		System.out.println(list);
	}

	private static void dfs(int u, int[][] in, boolean[] visit, List<Integer> list) {
		visit[u] = true;
		for (int v = 0; v < in.length; ++v) {
			if (in[u][v] == 1) {
				if (visit[v] == false)
					dfs(v, in, visit, list);
			}
		}
		list.add(0, u);
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.directedCompleteGraph(n);
		GraphUtils.display(in);
		courseSchedule1(in);
		courseSchedule2(in);
	}

}
