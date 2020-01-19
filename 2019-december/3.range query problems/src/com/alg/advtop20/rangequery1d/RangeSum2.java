package com.alg.advtop20.rangequery1d;

public class RangeSum2 {
	private int[] in;
	private int[] csum;

	public RangeSum2(int[] in) {
		this.in = in;
		for (int k = 0; k < in.length; ++k) {
			csum[k] = (k != 0 ? csum[k - 1] : 0) + in[k];
		}
	}

	public void update(int i, int x) {
		in[i] = x;
		for (int k = i; k < in.length; ++k) {
			csum[k] = (k != 0 ? csum[k - 1] : 0) + in[k];
		}
	}

	public int rangeSum(int i, int j) {
		return csum[j] - (i != 0 ? csum[i - 1] : 0);
	}

}
