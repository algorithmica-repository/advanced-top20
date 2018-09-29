package com.alg.top20.adv.strings;

import java.util.Arrays;

public class StringInterleave {

	private static void display(boolean[][] mem) {
		for(int i = 0; i < mem.length; ++i) {
			System.out.println(Arrays.toString(mem[i]));
		}
	}
	public static boolean isInterleave1(String s1, String s2, String s3) {
		return auxInterleave(0, 0, "", s1, s2, s3 );
		//return true;
	}
	private static boolean auxInterleave(int i, int j, String res, String s1, String s2, String s3) {
		if(i == s1.length()) {
			System.out.println(res+s2.substring(j));
			return s3.equals(res+s2.substring(j));
		}
		if(j == s2.length()) {
			System.out.println(res+s1.substring(i));
			return s3.equals(res+s1.substring(i));
		}
		if(auxInterleave(i+1, j, res+s1.charAt(i), s1, s2, s3)) return true;
		return auxInterleave(i, j+1, res+s2.charAt(j), s1, s2, s3);
	}
	
	public static boolean isInterleave2(String s1, String s2, String s3) {
		int n = s1.length();
		int m = s2.length();
		boolean[][] mem = new boolean[n+1][m+1];
		for(int j = 1; j <= m; ++j) {
			mem[0][j] = s2.substring(0,j).equals(s3.substring(0,j));
		}
		for(int i = 1; i <= n; ++i) {
			mem[i][0] = s1.substring(0,i).equals(s3.substring(0,i));
		}
		for(int i = 1; i <= n; ++i) {
			for(int j = 1; j <= m; ++j) {
				mem[i][j] = ((s1.charAt(i-1) == s3.charAt(i+j-1)) && mem[i-1][j]) ||
							((s2.charAt(j-1) == s3.charAt(i+j-1)) && mem[i][j-1]);
			}
		}
		display(mem);
		return mem[n][m];		
	}
	public static void main(String[] args) {
		System.out.println(isInterleave2(args[0], args[1], args[2]));

	}

}
