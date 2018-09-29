package com.alg.advtop20.rangequery.oned;

public class RangeSum2 {
	private int[] in;
	private int[] csum;
	
	//O(n)
	public RangeSum2(int[] in) {
		this.in = in;
		csum = new int[in.length];
		csum[0] = in[0];
		for(int i = 1; i < csum.length; ++i)
			csum[i] = csum[i-1] + in[i];
	}
	//O(n)
	public void update(int i, int x) {
		in[i] = x;
		for(int k = i; k <= csum.length; ++k)
			csum[k] = csum[k-1] + in[k];
	}
	//O(1)
	public int rangeSum(int i, int j) {
		if(i == 0) return csum[j];
		return csum[j] - csum[i-1];
	}
	
}
