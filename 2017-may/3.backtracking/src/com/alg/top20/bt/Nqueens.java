package com.alg.top20.bt;

import java.util.Arrays;

public class Nqueens {
	
	//TC:O(n ^ (n+2))  SC:O(n)
	public static void nqueens1(int n) {
		int[] pos = new int[n];
		auxqueens1(0, n, pos);
	}
	private static boolean isValid1(int[] pos) {
		for(int i = 0; i < pos.length; ++i) {
			for(int j = i+1; j < pos.length; ++j) {
				if(pos[i] == pos[j] || (Math.abs(i-j) == Math.abs(pos[i]-pos[j])))
					return false;
			}
		}
		return true;
	}
	private static void auxqueens1(int d, int n, int[] pos) {
		if(d == n) {
			if(isValid1(pos))
				System.out.println(Arrays.toString(pos));
			return;
		}
		for(int c = 0; c < n; ++c) {
			pos[d] = c;
			auxqueens1(d+1, n, pos);
		}
	}
	
	//TC:O(?)  SC:O(n)
	public static void nqueens2(int n) {
		int[] pos = new int[n];
		auxqueens2(0, n, pos);
	}
	private static boolean isValid2(int d, int c, int[] pos) {
		//add more heuristics to eliminate more branches
		//derive heuristics with experience of solving problem
		for(int q = 0; q < d; ++q) {
				if(pos[q] == c || (Math.abs(q-d) == Math.abs(pos[q]-c)))
					return false;
		}
		return true;
	}
	private static void auxqueens2(int d, int n, int[] pos) {
		if(d == n) {
			System.out.println(Arrays.toString(pos));
			return;
		}
		for(int c = 0; c < n; ++c) {
			//backtrack:conditional branching
			if(isValid2(d, c, pos)) {
				pos[d] = c;
				auxqueens2(d+1, n, pos);
			}
		}
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		nqueens2(n);
	}
}
