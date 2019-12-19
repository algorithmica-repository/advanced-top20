package com.alg.advtop20.advdp;

import java.util.Arrays;
import java.util.Random;

public class RestaurantMerging {

	public static int merge1(int[] in) {
		return auxMerge1(0, in.length - 1, in);
	}

	private static int auxMerge1(int i, int j, int[] in) {
		if (i == j)
			return 0;
		int mcost = Integer.MAX_VALUE;
		for (int k = i; k < j; ++k) {
			int leftMCost = auxMerge1(i, k, in);
			int rightMCost = auxMerge1(k + 1, j, in);
			int leftSum = 0, rightSum = 0;
			for (int tmp = i; tmp <= k; ++tmp)
				leftSum += in[tmp];
			for (int tmp = k + 1; tmp <= j; ++tmp)
				rightSum += in[tmp];
			int cost = leftMCost + rightMCost + Math.max(leftSum, rightSum);
			mcost = Math.min(mcost, cost);
		}
		return mcost;
	}

	public static int merge2(int[] in) {
		int[][] mem = new int[in.length][in.length];
		auxMerge2(0, in.length - 1, in, mem);
		//for(int[] tmp: mem)
		//	System.out.println(Arrays.toString(tmp));
		return mem[0][in.length-1];
	}

	private static int auxMerge2(int i, int j, int[] in, int[][] mem) {
		if (i == j)
			return 0;
		if (mem[i][j] != 0)
			return mem[i][j];
		int mcost = Integer.MAX_VALUE;
		for (int k = i; k < j; ++k) {
			int leftMCost = auxMerge2(i, k, in, mem);
			int rightMCost = auxMerge2(k + 1, j, in, mem);
			int leftSum = 0, rightSum = 0;
			for (int tmp = i; tmp <= k; ++tmp)
				leftSum += in[tmp];
			for (int tmp = k + 1; tmp <= j; ++tmp)
				rightSum += in[tmp];
			int cost = leftMCost + rightMCost + Math.max(leftSum, rightSum);
			mcost = Math.min(mcost, cost);
		}
		mem[i][j] = mcost;
		return mcost;
	}
	
	public static int merge3(int[] in) {
		int[][] mem = new int[in.length][in.length];
		for(int i = 0; i < in.length; ++i)
			mem[i][i] = 0;
		for(int i = in.length-1; i >= 0; --i) {
			for(int j = i+1; j < in.length; ++j) {
				int mcost = Integer.MAX_VALUE;
				for (int k = i; k < j; ++k) {
					int leftMCost = mem[i][k];
					int rightMCost = mem[k + 1][j];
					int leftSum = 0, rightSum = 0;
					for (int tmp = i; tmp <= k; ++tmp)
						leftSum += in[tmp];
					for (int tmp = k + 1; tmp <= j; ++tmp)
						rightSum += in[tmp];
					int cost = leftMCost + rightMCost + Math.max(leftSum, rightSum);
					mcost = Math.min(mcost, cost);
				}
				mem[i][j] = mcost;
			}
		}
		return mem[0][in.length-1];
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random();
		for (int i = 0; i < n; ++i)
			in[i] = r.nextInt(n) + 1;
		/// int[] in1 = {1, 3, 2, 5};
		//System.out.println(Arrays.toString(in));
		//System.out.println(merge1(in));
		System.out.println(merge2(in));
		System.out.println(merge3(in));
	}

}
