package com.alg.advtop20.graphs.traversal;

import java.util.Arrays;
import java.util.Random;

public class GraphUtils {

	public static void printGraph(int[][] in) {
		for(int[] tmp:in) {
			System.out.println(Arrays.toString(tmp));
		}
	}
	
	public static int[][] randomGraph(int n) {
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int k = 1; k <= n; ++k) {
			int ri = r.nextInt(n);
			int rj = r.nextInt(n);
			if(ri == rj) continue;
			in[ri][rj] = 1;
			in[rj][ri] = 1;
		}
		return in;
	}
	
	public static int[][] completeGraph(int n) {
		int[][] in = new int[n][n];
		for(int i = 0; i < n; ++i) {
			for(int j = i+1; j < n; ++j)  {
				in[i][j] = 1;
				in[j][i] = 1;
			}
		}
		return in;
	}
	
	public static int[][] convertToGraph(int n, int[][] input) {
		int[][] in = new int[n][n];
		for(int[] tmp:input) {
			in[tmp[0]][tmp[1]] = 1;
			in[tmp[1]][tmp[0]] = 1;
		}
		return in;
	}

	public static int[][] randomDirectedGraph(int n) {
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int k = 1; k <= n; ++k) {
			int ri = r.nextInt(n);
			int rj = r.nextInt(n);
			if(ri == rj) continue;
			in[ri][rj] = 1;
		}
		return in;
	}
	public static int[][] completeDirectedGraph(int n) {
		int[][] in = new int[n][n];
		for(int i = 0; i < n; ++i) {
			for(int j = i+1; j < n; ++j)  {
				in[i][j] = 1;
			}
		}
		return in;
	}
	

}
