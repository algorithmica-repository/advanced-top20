package com.alg.advtop20.segmentree;

public interface ISegmentTree {
	void update(int i, int x);
	int rangeSum(int i, int j);
	void display();
}
