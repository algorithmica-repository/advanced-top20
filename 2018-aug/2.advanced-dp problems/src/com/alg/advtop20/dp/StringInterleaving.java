package com.alg.advtop20.dp;

public class StringInterleaving {

	public static boolean isInterleave1(String s1, String s2, String s3) {
		return auxInterleave1(s1.length(), s2.length(), s1, s2, s3);
	}
	private static boolean auxInterleave1(int i, int j, String s1, String s2, String s3) {
		if(i == 0 && j == 0) return true;
		if(i == 0 && j != 0) return s3.substring(0, j).equals(s2.substring(0, j));
		if(j == 0 && i != 0) return s3.substring(0, i).equals(s1.substring(0, i));
		if(s1.charAt(i-1) == s3.charAt(i+j-1))
			if(auxInterleave1(i-1, j, s1, s2, s3)) return true;
		if(s2.charAt(j-1) == s3.charAt(i+j-1))
			return auxInterleave1(i, j-1, s1, s2, s3);
		return false;
	}
	
	public static boolean isInterleave2(String s1, String s2, String s3) {
		boolean[][] mem = new boolean[s1.length()+1][s2.length()+1];
		mem[0][0] = true;
		for(int i = 1; i <= s1.length(); ++i)
			mem[i][0] = s3.substring(0, i).equals(s1.substring(0, i));
		for(int j = 1; j <= s2.length(); ++j)
			mem[0][j] = s3.substring(0, j).equals(s2.substring(0, j));
		for(int i = 1; i <= s1.length(); ++i) {
			for(int j = 1; j <= s2.length(); ++j) {
				if(s1.charAt(i-1) == s3.charAt(i+j-1)) {
					mem[i][j] = mem[i-1][j];
					if(mem[i][j] == true) continue;
				}
				if(s2.charAt(j-1) == s3.charAt(i+j-1))
					mem[i][j] = mem[i][j-1];
				mem[i][j] = false;				
			}
		}
        return  mem[s1.length()][s2.length()];                             
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
