package com.alg.advtop20.graph;

import java.util.Arrays;
import java.util.Random;

public class GraphUtils {

	public static int[][] undirectedRandomGraph(int n) {
		int[][] in = new int[n][n];
		Random r = new Random(0);
		int nedges = n;
		for (int i = 0; i < nedges; ++i) {
			int u = r.nextInt(n);
			int v = r.nextInt(n);
			if(u != v)
				in[u][v] = in[v][u] = 1;
		}
		return in;
	}

	public static int[][] undirectedCompleteGraph(int n) {
		int[][] in = new int[n][n];
		for (int u = 0; u < n; ++u) {
			for (int v = u + 1; v < n; ++v)
				in[u][v] = in[v][u] = 1;
		}
		return in;
	}

	public static void display(int[][] in) {
		for (int[] tmp : in) {
			System.out.println(Arrays.toString(tmp));
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
