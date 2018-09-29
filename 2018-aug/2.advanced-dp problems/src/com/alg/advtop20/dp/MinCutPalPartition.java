package com.alg.advtop20.dp;

import java.util.Arrays;

class MyInteger {
	private int value;

	public MyInteger(int value) {
		this.value = value;
	}

	public int get() {
		return value;
	}

	public void set(int value) {
		this.value = value;
	}

}

public class MinCutPalPartition {

	private static boolean isPalindrome(String s) {
		for (int i = 0, j = s.length() - 1; i < j; ++i, --j)
			if (s.charAt(i) != s.charAt(j))
				return false;
		return true;
	}

	private static boolean isPalindrome(String s, int i, int j) {
		for (; i < j; ++i, --j)
			if (s.charAt(i) != s.charAt(j))
				return false;
		return true;
	}

	private static void auxPartitions1(String in, int depth, MyInteger mincuts,
			String path) {
		if (in.length() == 1) {
			System.out.println(path + "+" + in + ":" + depth);
			mincuts.set(Math.min(mincuts.get(), depth));
			return;
		}
		for (int i = 0; i < in.length(); ++i) {
			String tmp = in.substring(0, i + 1);
			if (isPalindrome(tmp))
				auxPartitions1(in.substring(i + 1), depth + 1, mincuts, path
						+ "+" + tmp);
		}
	}

	// TC:O(n!) SC:O(n)
	public static int minCuts1(String s) {
		MyInteger mincuts = new MyInteger(Integer.MAX_VALUE);
		if (isPalindrome(s))
			return 0;
		auxPartitions1(s, 0, mincuts, "");
		return mincuts.get();
	}

	private static int auxCuts2(int i, int j, String s) {
		if (i > j || isPalindrome(s, i, j))
			return 0;
		int min = Integer.MAX_VALUE;
		for (int k = i; k < j; ++k) {
			int leftcuts = auxCuts2(i, k, s);
			int rightcuts = auxCuts2(k + 1, j, s);
			min = Math.min(min, leftcuts + rightcuts + 1);
		}
		return min;
	}

	// TC:min(2 ^ n) SC:O(n)
	public static int minCuts2(String s) {
		return auxCuts2(0, s.length() - 1, s);
	}

	private static void print(int[][] mem) {
		for (int[] tmp : mem)
			System.out.println(Arrays.toString(tmp));
	}

	// TC:O(n ^ 3) SC:O(n ^ 2)
	public static int minCuts3(String s) {
		int n = s.length();
		int[][] mem = new int[n][n];
		for (int i = 0; i < n; ++i)
			mem[i][i] = 0;
		for (int l = 1; l < n; ++l) {
			for (int i = 0; i < n - l; ++i) {
				int j = i + l;
				if (isPalindrome(s, i, j))
					mem[i][j] = 0;
				else {
					int min = Integer.MAX_VALUE;
					for (int k = i; k < j; ++k) {
						int leftcuts = mem[i][k];
						int rightcuts = mem[k + 1][j];
						min = Math.min(min, leftcuts + rightcuts + 1);
					}
					mem[i][j] = min;
				}
			}
		}
		print(mem);
		return mem[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(minCuts1(args[0]));
		//System.out.println(minCuts2(args[0]));
		System.out.println(minCuts3(args[0]));
	}

}
