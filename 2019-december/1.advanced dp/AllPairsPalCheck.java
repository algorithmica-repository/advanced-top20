package com.alg.advtop20.advdp;

import java.util.Arrays;

public class AllPairsPalCheck {

	public static void allPairsPalCheck1(String in) {
		for (int i = 0; i < in.length(); ++i) {
			for (int j = i + 1; j < in.length(); ++j) {
				System.out.println(auxPalCheck1(in, i, j));
			}
		}
	}

	private static boolean auxPalCheck1(String in, int i, int j) {
		if (i >= j)
			return true;
		if (in.charAt(i) == in.charAt(j))
			return auxPalCheck1(in, i + 1, j - 1);
		else
			return false;
	}

	public static void allPairsPalCheck21(String in) {
		int[][] mem = new int[in.length()][in.length()];
		for (int i = 0; i < in.length(); ++i) {
			for (int j = i + 1; j < in.length(); ++j) {
				System.out.println(auxPalCheck21(in, i, j, mem));
			}
		}
	}

	private static int auxPalCheck21(String in, int i, int j, int[][] mem) {
		if (i >= j)
			return 1;
		if (mem[i][j] != 0)
			return mem[i][j];
		if (in.charAt(i) == in.charAt(j))
			return mem[i][j] = auxPalCheck21(in, i + 1, j - 1, mem);
		else
			return mem[i][j] = -1;
	}

	public static void allPairsPalCheck22(String in) {
		boolean[][] mem = new boolean[in.length()][in.length()];
		for (int i = 0; i < in.length()-1; ++i) {
			mem[i][i] = true;
			mem[i+1][i] = true;
		}

		for (int i = in.length() - 2; i >= 0; --i) {
			for (int j = i + 1; j < in.length(); ++j) {
				if (in.charAt(i) == in.charAt(j))
					mem[i][j] = mem[i + 1][j - 1];
				else
					mem[i][j] = false;
			}
		}
		for(boolean[] tmp: mem)
			System.out.println(Arrays.toString(tmp));
	}

	public static void main(String[] args) {
		//allPairsPalCheck1(args[0]);
		//System.out.println();
		allPairsPalCheck22(args[0]);
	}

}
