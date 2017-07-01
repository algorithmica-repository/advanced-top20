package com.alg.top20.adv;

public class BSTCounting {

	//TC:O(c ^ n)   SC:O(n)
	public static long countBST1(int n) {
		if(n <= 1) return 1;
		int count = 0;
		for(int i = 1; i <= n; ++i) {
			count += (countBST1(i-1) * countBST1(n-i));
		}
		return count;
	}
	
	//TC:O(n ^ 2)   SC:O(n) ~ n + n
	public static long countBST2(int n) {
		long[] mem = new long[n+1];
		auxCountBST2(n, mem);
		return mem[n];
	}
	public static long auxCountBST2(int n, long[] mem) {
		if(n <= 1) return 1;
		int count = 0;
		for(int i = 1; i <= n; ++i) {
			long left = mem[i-1] != 0?mem[i-1]: auxCountBST2(i-1, mem);
			long right = mem[n-i] != 0?mem[n-i]: auxCountBST2(n-i, mem);
			count += (left * right);
		}		
		return mem[n] = count;
	}
	
	//TC:O(n ^ 2)   SC:O(n) ~ n
	public static long countBST3(int n) {
		long[] mem = new long[n+1];
		mem[0] = mem[1] = 1;
		for(int i = 2 ; i <= n; ++i) {
			int count = 0;
			for(int j = 1; j <= i; ++j) {
				count += (mem[j-1] * mem[i-j]);
			}	
			mem[i] = count;
		}
		return mem[n];
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		//System.out.println(countBST1(n));
		//System.out.println(countBST2(n));
		System.out.println(countBST3(n));
	}

}
