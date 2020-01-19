package com.alg.advtop20.rangequery1d;

import com.alg.advtop20.rangesum.segtree.ISegmentTree;
import com.alg.advtop20.rangesum.segtree.SeqSegmentTree;

public class RangeSum3 {
	private ISegmentTree tree;

	public RangeSum3(int[] in) {
		tree = new SeqSegmentTree(in);
	}

	public void update(int i, int x) {
		tree.update(i, x);
	}

	public int rangeSum(int i, int j) {
		return tree.rangeSum(i, j);
	}

}
