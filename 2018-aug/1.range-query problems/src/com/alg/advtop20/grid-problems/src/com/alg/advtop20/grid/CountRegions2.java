package com.alg.advtop20.grid;

import java.util.Arrays;
import java.util.Random;
public class CountRegions2 {

	public static void print(int[][] in) {
		for(int[] tmp:in) {
			System.out.println(Arrays.toString(tmp));
		}
	}
	private static void connect(DisjointSet2 dset, int[][] in, int i1, int j1, int i2, int j2) {
		int n = in.length;
		if(i2 < 0 || j2 < 0 || in[i2][j2] == 0) return;
		dset.union(i1*n+j1, i2*n+j2);
	}
	public static int countRegions(int[][] in) {
		DisjointSet2 dset = new DisjointSet2(in);
		dset.display();
		for(int i = 0; i < in.length; ++i) {
			for(int j = 0; j < in.length; ++j) {
				if(in[i][j] == 1) {
					connect(dset, in, i, j, i-1, j);
					connect(dset, in, i, j, i, j-1);
				}
			}
		}
		return dset.getNumSets();
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
