package com.alg.top20.adv.strings;

import java.util.Arrays;

public class LongestPalSubsequence {

	private static void display(int[][] mem) {
		for(int i = 0; i < mem.length; ++i)
			System.out.println(Arrays.toString(mem[i]));
	}
	public static int longestPalSubSeq3(String s) {
		int n = s.length();
		int[][] mem = new int[n+1][n+1];
		for(int i = 1; i <= n; ++i)
			mem[i][i] = 1;
		for(int l = 1; l < n; ++l) {
			for(int i = 1; i <= n-l; ++i) { 
				int j = i + l;
				if(s.charAt(i-1) == s.charAt(j-1))
					mem[i][j] = 2 + mem[i+1][j-1];
				else
					mem[i][j] = Math.max(mem[i+1][j], mem[i][j-1]);
			}
		}
		display(mem);
		return mem[1][n];
	}
	public static void main(String[] args) {
		System.out.println(longestPalSubSeq3(args[0]));

	}

}
