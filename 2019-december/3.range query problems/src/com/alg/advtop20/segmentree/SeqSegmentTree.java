package com.alg.advtop20.segmentree;

import java.util.Arrays;

public class SeqSegmentTree implements ISegmentTree {
	private int[] in;
	private int[] segment_tree;

	// O(n)
	public SeqSegmentTree(int[] in) {
		this.in = in;
		this.segment_tree = new int[2 * in.length - 1];
		for (int i = 0; i < in.length; ++i)
			segment_tree[i + in.length - 1] = in[i];
		for (int i = in.length - 2; i >= 0; --i)
			segment_tree[i] = segment_tree[2 * i + 1] + segment_tree[2 * i + 2];
	}

	// O(log n)
	public void update(int i, int x) {
		in[i] = x;
		int current = i + in.length - 1;
		segment_tree[current] = x;
		while (current > 0) {
			if (current % 2 != 0)
				segment_tree[(current - 1) / 2] = segment_tree[current] + segment_tree[current + 1];
			else
				segment_tree[(current - 1) / 2] = segment_tree[current] + segment_tree[current - 1];
			current = (current - 1) / 2;
		}
	}

	// O(log n)
	public int rangeSum(int i, int j) {
		i = i + in.length - 1;
		j = j + in.length - 1;
		int sum = 0;
		while (i <= j) {
			if (i % 2 == 0) {
				sum += segment_tree[i];
				++i;
			}
			if (j % 2 == 1) {
				sum += segment_tree[j];
				--j;
			}
			if (i > j)
				break;
			i = (i - 1) / 2;
			j = (j - 1) / 2;
		}
		return sum;
	}

	public void display() {
		System.out.println(Arrays.toString(in));
		System.out.println(Arrays.toString(segment_tree));
	}

}