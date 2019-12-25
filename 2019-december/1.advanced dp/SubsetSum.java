package com.alg.advtop20.dp2;

public class SubsetSum {

	private static boolean subsetSum1(int[] in, int s) {
		return auxSum1(0, 0, in, s);
	}

	private static boolean auxSum1(int i, int csum, int[] in, int s) {
		if (i == in.length)
			return csum == s;
		if(csum > s) return false;
		if (auxSum1(i + 1, csum, in, s))
			return true;
		return auxSum1(i + 1, csum + in[i], in, s);
	}

	private static boolean subsetSum2(int[] in, int s) {
		return auxSum2(0, in, s);
	}

	private static boolean auxSum2(int i, int[] in, int j) {
		if (i == in.length-1)
			return in[i] == j;
		if (in[i] > j)
			return false;
		if (auxSum2(i + 1, in, j))
			return true;
		return auxSum2(i + 1, in, j - in[i]);
	}
	
	private static boolean subsetSum31(int[] in, int s) {
		Boolean[][] mem = new Boolean[in.length][s+1];
		return auxSum31(0, in, s,  mem);
	}

	private static Boolean auxSum31(int i, int[] in, int j, Boolean[][] mem) {
		if (i == in.length-1)
			return in[i] == j;
		if (in[i] > j)
			return false;
		if(mem[i][j] != null) return mem[i][j];
		if (auxSum31(i + 1, in, j, mem))
			return mem[i][j] = true;
		return mem[i][j] = auxSum31(i + 1, in, j - in[i], mem);
	}
	
	private static boolean subsetSum32(int[] in, int s) {
		int n = in.length;
		boolean[][] mem = new boolean[n][s+1];
		for(int j = 0; j <= s; ++j)
			mem[n-1][j] = (in[n-1] == j);
		for(int i = n-2; i >= 0; --i) {
			for(int j = 1; j <= s; ++j) {
				if(in[i] > j)
					mem[i][j] = false;
				else 
					mem[i][j] = mem[i+1][j] || mem[i+1][j-in[i]];
			}
		}
		return mem[0][s];
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
