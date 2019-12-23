package com.alg.advatop20.advdp;

public class MinCutPalPartitioning2 {

	private static boolean isPalindrome(String s, int i, int j) {
		while (i < j) {
			if (s.charAt(i) != s.charAt(j))
				return false;
			++i;
			--j;
		}
		return true;
	}

	public static int minCuts1(String in) {
		MyInteger gmin = new MyInteger(Integer.MAX_VALUE);
		auxCuts1(in, 0, 0, gmin);
		return gmin.get();
	}

	private static void auxCuts1(String in, int i, int cuts, MyInteger gmin) {
		if (i == in.length() - 1 || isPalindrome(in, i, in.length() - 1)) {
			gmin.set(Math.min(gmin.get(), cuts));
			return;
		}
		for (int j = i; j < in.length() - 1; ++j) {
			if (isPalindrome(in, i, j))
				auxCuts1(in, j + 1, cuts + 1, gmin);
		}
	}

	public static int minCuts2(String in) {
		return auxCuts2(in, 0);
	}

	private static int auxCuts2(String in, int i) {
		if (i == in.length() - 1 || isPalindrome(in, i, in.length() - 1))
			return 0;
		int mincuts = Integer.MAX_VALUE;
		for (int j = i; j < in.length() - 1; ++j) {
			if (isPalindrome(in, i, j)) {
				int res = auxCuts2(in, j + 1);
				mincuts = Math.min(mincuts, res);
			}
		}
		return mincuts + 1;
	}

	public static int minCuts31(String in) {
		int[] mem = new int[in.length()];
		return auxCuts31(in, 0, mem);
	}

	private static int auxCuts31(String in, int i, int[] mem) {
		if (i == in.length() - 1 || isPalindrome(in, i, in.length() - 1))
			return 0;
		if (mem[i] != 0)
			return mem[i];
		int mincuts = Integer.MAX_VALUE;
		for (int j = i; j < in.length() - 1; ++j) {
			if (isPalindrome(in, i, j)) {
				int res = auxCuts31(in, j + 1, mem);
				mincuts = Math.min(mincuts, res);
			}
		}
		return mem[i] = mincuts + 1;
	}

	public static int minCuts32(String in) {
		int[] mem = new int[in.length() + 1];
		mem[in.length()] = 0;
		for (int i = in.length(); i >= 0; --i) {
			if (isPalindrome(in, i, in.length() - 1))
				mem[i] = 0;
			else {
				int mincuts = Integer.MAX_VALUE;
				for (int j = i; j < in.length() - 1; ++j) {
					if (isPalindrome(in, i, j)) {
						int res = auxCuts31(in, j + 1, mem);
						mincuts = Math.min(mincuts, res);
					}
				}
				mem[i] = mincuts + 1;
			}
		}
		return mem[0];
	}

	public static void main(String[] args) {
		System.out.println(minCuts1(args[0]));
		System.out.println(minCuts2(args[0]));
		System.out.println(minCuts31(args[0]));
		System.out.println(minCuts32(args[0]));

	}

}
