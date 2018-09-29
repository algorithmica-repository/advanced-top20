package com.alg.advtop20.grid;

import java.util.Arrays;
import java.util.Random;

public class CountRegions {

	private static void auxRegions(int i, int j, int[][] in, boolean[][] visit) {
		if(i < 0 || i >= in.length || j < 0 || j >= in.length) return;
		if(in[i][j] == 0 || visit[i][j] == true) return;
		visit[i][j] = true;
		auxRegions(i, j-1, in, visit);
		auxRegions(i, j+1, in, visit);
		auxRegions(i-1, j, in, visit);
		auxRegions(i+1, j, in, visit);
	}
	public static int countRegions(int[][] in) {
		int count = 0;
		boolean[][] visit = new boolean[in.length][in.length];
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1 && visit[i][j] == false) {
					++count;
					auxRegions(i, j, in, visit);
				}
			}
		}
		return count;
	}
	
	public static void print(int[][] in) {
		for(int[] tmp:in) {
			System.out.println(Arrays.toString(tmp));
		}
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[][] in = new int[n][n];
		Random r = new Random(100);
		for(int k = 1; k <= 2*n; ++k) {
			int ri = r.nextInt(n);
			int rj = r.nextInt(n);
			in[ri][rj] = 1;
		}
		print(in);
		System.out.println(countRegions(in));
	}

}
