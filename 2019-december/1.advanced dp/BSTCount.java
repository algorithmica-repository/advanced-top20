package com.alg.advtop20.advdp;

public class BSTCount {

	public static int bstCount1(int n) {
		if (n <= 1)
			return 1;
		int count = 0;
		for (int k = 1; k <= n; ++k) {
			int leftCount = bstCount1(k - 1);
			int rightCount = bstCount1(n - k);
			count += (leftCount * rightCount);
		}
		return count;
	}

	public static int bstCount21(int n) {
		int[] mem = new int[n + 1];
		auxCount21(n, mem);
		return mem[n];
	}

	private static int auxCount21(int i, int[] mem) {
		if (i <= 1)
			return 1;
		if (mem[i] != 0)
			return mem[i];
		int count = 0;
		for (int k = 1; k <= i; ++k) {
			int leftCount = auxCount21(k - 1, mem);
			int rightCount = auxCount21(i - k, mem);
			count += (leftCount * rightCount);
		}
		mem[i] = count;
		return count;
	}

	public static int bstCount22(int n) {
		int[] mem = new int[n + 1];
		mem[0] = mem[1] = 1;
		for (int i = 2; i <= n; ++i) {
			int count = 0;
			for (int k = 1; k <= i; ++k) {
				int leftCount = mem[k - 1];
				int rightCount = mem[i - k];
				count += (leftCount * rightCount);
			}
			mem[i] = count;
		}

		return mem[n];
	}

	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		//System.out.println(bstCount21(n));
		System.out.println(bstCount22(n));
	}

}
