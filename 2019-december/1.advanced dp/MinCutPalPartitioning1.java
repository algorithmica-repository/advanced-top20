package com.alg.advatop20.advdp;

public class MinCutPalPartitioning1 {
	
	private static boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			++i;
			--j;
		}
		return true;
	}

	//TC:O(n!)
	//SC:O(n)
	public static int minCuts1(String s) {
		return auxCuts1(0, s.length() - 1, s);
	}

	private static int auxCuts1(int i, int j, String s) {
		if (i >= j || isPalindrome(s, i, j))
			return 0;
		int minCuts = Integer.MAX_VALUE;
		for (int k = i; k < j; ++k) {
			int leftCuts = auxCuts1(i, k, s);
			int rightCuts = auxCuts1(k + 1, j, s);
			minCuts = Math.min(minCuts, leftCuts + rightCuts + 1);
		}
		return minCuts;
	}

	//TC:O(n^2)
	//SC:O(n)
	public static int minCuts21(String s) {
		int[][] mem = new int[s.length()][s.length()];
		auxCuts21(0, s.length() - 1, s, mem);
		return mem[0][s.length() - 1];
	}

	private static int auxCuts21(int i, int j, String s, int[][] mem) {
		if (i >= j || isPalindrome(s, i, j))
			return 0;
		if (mem[i][j] != 0)
			return mem[i][j];
		int minCuts = Integer.MAX_VALUE;
		for (int k = i; k < j; ++k) {
			int leftCuts = auxCuts21(i, k, s, mem);
			int rightCuts = auxCuts21(k + 1, j, s, mem);
			minCuts = Math.min(minCuts, leftCuts + rightCuts + 1);
		}
		mem[i][j] = minCuts;
		return minCuts;
	}

	//TC:O(n^3)
	//SC:O(n)
	public static int minCuts22(String s) {
		int n = s.length();
		int[][] mem = new int[n][n];
		for (int i = 0; i < n; ++i)
			mem[i][i] = 0;
		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j) {
				if (isPalindrome(s, i, j))
					mem[i][j] = 0;
				else {
					int mcuts = Integer.MAX_VALUE;
					for (int k = i; k < j; ++k) {
						int leftCuts = mem[i][k];
						int rightCuts = mem[k + 1][j];
						mcuts = Math.min(mcuts, leftCuts + rightCuts + 1);
					}
					mem[i][j] = mcuts;
				}
			}
		}
		return mem[0][n - 1];
	}
	
	//TC:O(n^2)
	//SC:O(n)
	public static int minCuts3(String s) {
		int n = s.length();
		int[][] mem = new int[n][n];
		for (int i = 0; i < n; ++i)
			mem[i][i] = 0;
		for (int i = n - 1; i >= 0; --i) {
			for (int j = i + 1; j < n; ++j) {
				if (isPalindrome(s, i, j))
					mem[i][j] = 0;
				else {
					int mcuts = Integer.MAX_VALUE;
					for (int k = i; k < j; ++k) {
						int leftCuts = mem[i][k];
						int rightCuts = mem[k + 1][j];
						mcuts = Math.min(mcuts, leftCuts + rightCuts + 1);
					}
					mem[i][j] = mcuts;
				}
			}
		}
		return mem[0][n - 1];
	}

	public static void main(String[] args) {
		System.out.println(minCuts1(args[0]));
		System.out.println(minCuts21(args[0]));
		System.out.println(minCuts22(args[0]));
	}

}
