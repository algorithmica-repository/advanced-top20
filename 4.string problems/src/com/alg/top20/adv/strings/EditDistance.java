package com.alg.top20.adv.strings;

public class EditDistance {

	//TC:O(3 ^ m+n)  SC:O(m+n)
	public static int editDistance1(String s1, String s2) {
		return auxDistance1(s1.length(), s2.length(), s1, s2);
	}
	private static int auxDistance1(int i, int j, String s1, String s2) {
		if(i == 0) return j;
		if(j == 0) return i;
		if(s1.charAt(i-1) == s2.charAt(j-1))
			return auxDistance1(i-1, j-1, s1, s2);
		else {
			int icost = auxDistance1(i, j-1, s1, s2);
			int rcost = auxDistance1(i-1, j-1, s1, s2);
			int dcost = auxDistance1(i-1, j, s1, s2);
			return Math.min(icost, Math.min(rcost, dcost)) + 1;
		}
	}
	
	//TC:O(mn)  SC:O(m+n)
	public static int editDistance2(String s1, String s2) {
		int[][] mem = new int[s1.length()+1][s2.length()+1];
		for(int i = 0; i <= s1.length(); ++i)
			mem[i][0] = i;
		for(int j = 1; j <= s2.length(); ++j)
			mem[0][j] = j;
		for(int i = 1; i <= s1.length(); ++i) {
			for(int j = 1; j <= s2.length();++j) {
				if(s1.charAt(i-1) == s2.charAt(j-1))
					mem[i][j] = mem[i-1][j-1];
				else {
					mem[i][j] = Math.min(mem[i][j-1], Math.min(mem[i-1][j-1], mem[i-1][j])) + 1;
				}
			}
		}
		return mem[s1.length()][s2.length()];
	}
	public static void main(String[] args) {
		//System.out.println(editDistance1(args[0], args[1]));
		System.out.println(editDistance2(args[0], args[1]));
	}

}
