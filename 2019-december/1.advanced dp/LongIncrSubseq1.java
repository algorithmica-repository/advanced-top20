package com.alg.advtop20.dp3;

public class LongIncrSubseq1 {

	public static int lis1(int[] in) {
		int gmax = Integer.MIN_VALUE;
		for (int i = 0; i < in.length; ++i) {
			MyInteger max = new MyInteger(Integer.MIN_VALUE);
			auxlis1(i, 0, max, in);
			gmax = Math.max(gmax, max.get());
		}
		return gmax;
	}

	private static void auxlis1(int i, int csum, MyInteger max, int[] in) {
		if (i == in.length - 1) {
			max.set(Math.max(max.get(), csum + 1));
			return;
		}
		for (int j = i + 1; j < in.length; ++j) {
			if (in[j] > in[i])
				auxlis1(j, csum + 1, max, in);
		}
	}

	public static int lis2(int[] in) {
		int gmax = Integer.MIN_VALUE;
		for (int i = 0; i < in.length; ++i) {
			int res = auxlis2(i, in);
			gmax = Math.max(gmax, res);
		}
		return gmax;
	}

	private static int auxlis2(int i, int[] in) {
		if (i == in.length - 1)
			return 1;
		int max = Integer.MIN_VALUE;
		for (int j = i + 1; j < in.length; ++j) {
			if (in[j] > in[i]) {
				int res = auxlis2(j, in);
				max = Math.max(max, res);
			}
		}
		return max;
	}

	public static int lis32(int[] in) {
		int gmax = Integer.MIN_VALUE;
		int n = in.length;
		int[] mem = new int[n];
		mem[n - 1] = 1;
		for (int i = n - 2; i >= 0; --i) {
			int max = Integer.MIN_VALUE;
			for (int j = i + 1; j < in.length; ++j) {
				if (in[j] > in[i])
					max = Math.max(max, mem[j]);
			}
			mem[i] = max;
			gmax = Math.max(gmax, max);
		}
		return gmax;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
