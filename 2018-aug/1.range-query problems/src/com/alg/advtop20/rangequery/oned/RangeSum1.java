package com.alg.advtop20.rangequery.oned;

public class RangeSum1 {
	private int[] in;
	
	public RangeSum1(int[] in) {
		this.in = in;
	}
	//O(1)
	public void update(int i, int x) {
		in[i] = x;
	}
	//O(n)
	public int rangeSum(int i, int j) {
		int sum = 0;
		for(int k = i; k <= j; ++k)
			sum += in[k];
		return sum;
	}
	
}
