package com.alg.top20.adv.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class LISLength {

	public static int LIS1(int[] in) {
		int max = 0;
		for(int i = in.length; i >= 1; --i)
			max = Math.max(max, auxLIS1(i, in));
		return max;
	}
	private static int auxLIS1(int i, int[] in) {
		int res = 0;
		for(int j = 1; j < i; ++j) {
			if(in[i-1] > in[j-1])
				res = Math.max(res, auxLIS1(j, in));
		}
		return res+1;
	}
	//TC:O(n ^ 2)   SC:O(n) ~ n + n
	public static int LIS2(int[] in) {
		int[] mem = new int[in.length+1];
		int max = 0;
		for(int i = in.length; i >= 1; --i)
			max = Math.max(max, mem[i]!=0?mem[i]:auxLIS2(i, in, mem));
		return max;
	}
	private static int auxLIS2(int i, int[] in, int[] mem) {
		int res = 0;
		for(int j = 1; j < i; ++j) {
			if(in[i-1] > in[j-1])
				res = Math.max(res, mem[j] != 0? mem[j]: auxLIS2(j, in, mem));
		}
		return mem[i] = res+1;
	}
	
	//TC:O(n ^ 2)   SC:O(n)
	public static int LIS3(int[] in) {
		int[] mem = new int[in.length+1];
		int max = 0;
		mem[0] = 0;
		for(int i = 1; i <= in.length; ++i) {
			int res = 0;
			for(int j = 1; j < i; ++j) {
				if(in[i-1] > in[j-1])
					res = Math.max(res, mem[j]);
			}
			mem[i] = res+1;
			max = Math.max(max, mem[i]);
		}
		return max;
	}
	
	//TC:O(n log n)   SC:O(n)
	public static int LIS4(int[] in) {
		ArrayList<Integer> mem = new ArrayList<Integer>();
		for(int i = 0; i < in.length; ++i) {
			int pos = Collections.binarySearch(mem, in[i]);
			if(pos >= 0) continue;
			pos *= -1;
			if(pos > mem.size())
				mem.add(in[i]);
			else
				mem.set(pos-1, in[i]);			
		}
		return mem.size();
	}
	
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random();
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(2*n) + 6;
		//System.out.println(Arrays.toString(in));
		//System.out.println(Arrays.toString(in));
		//System.out.println(LIS1(in));
		System.out.println(LIS3(in));
		System.out.println(LIS4(in));
	}

}
