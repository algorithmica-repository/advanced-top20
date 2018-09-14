package com.alg.advtop20.graphs.traversal;

public class CourseSchedule {

	public static boolean courseSchedule1(int n, int[][] prereq) {
		int[][] in = GraphUtils.convertToGraph(n, prereq);
		int[] indegree = new int[n];
		for(int i = 0; i < n; ++i) {
			for(int j = 0; j < n; ++j) {
				if(in[i][j] == 1)
					++indegree[j];
			}
		}
		for(int k = 1; k <= n; ++k) {
			int i;
			for(i = 0; i < n; ++i) {
				if(indegree[i] == 0) {
					System.out.print(i+ " ->");
					for(int j = 0; j < n; ++j) {
						if(in[i][j] == 1)
							--indegree[j];
					}
					break;
				} 
			}
			if(i == n) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
