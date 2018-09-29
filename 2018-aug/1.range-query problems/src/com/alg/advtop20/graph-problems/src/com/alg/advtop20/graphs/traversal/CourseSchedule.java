package com.alg.advtop20.graphs.traversal;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CourseSchedule {

	//TC:O(V ^ 2)  SC:O(V)
	public static Queue<Integer> courseSchedule1(int[][] in) {
		int[] indegree = new int[in.length];
		Queue<Integer> q = new LinkedList<Integer>();
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1)
					++indegree[j];
			}
		}
		for(int k = 1; k <= in.length; ++k) {
			int i;
			for(i = 0; i < in.length; ++i) {
				if(indegree[i] == 0) {
					indegree[i] = -1;
					q.add(i);
					for(int j = 0; j < in.length; ++j) {
						if(in[i][j] == 1)
							--indegree[j];
					}
					break;
				} 
			}
			if(i == in.length) return null;
		}
		return q;
	}
	
	private static boolean dfs(int u, int[][] in, int[] visit, Stack<Integer> st) {
		visit[u] = 1;
		for(int v = 0; v < in.length; ++v) {
			if(in[u][v] == 1) {
				//forward edge
				if(visit[v] == 0) { 
					if(dfs(v, in, visit, st)) return true;
				} else { //cyclic edge
					if(visit[v] == 1) return true;
				}
			}
		}
		visit[u] = 2;
		st.push(u);
		return false;
	}
	//TC:O(V ^ 2)   SC:O(V)
	public static Stack<Integer> courseSchedule2(int[][] in) {
		int[] visit = new int[in.length];
		Stack<Integer> st = new Stack<Integer>();
		for(int u = 0; u < in.length; ++u) {
			if(visit[u] == 0) 
				if(dfs(u, in, visit, st)) return null;
		}
		return st;
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = GraphUtils.completeDirectedGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(courseSchedule1(in));
		System.out.println(courseSchedule2(in));

		System.out.println();
		in = GraphUtils.randomDirectedGraph(n);
		GraphUtils.printGraph(in);
		System.out.println(courseSchedule1(in));
		System.out.println(courseSchedule2(in));


	}

}
