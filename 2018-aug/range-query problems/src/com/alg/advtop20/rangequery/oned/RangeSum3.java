package com.alg.advtop20.rangequery.oned;

import java.util.Arrays;
import java.util.Random;

class SegmentTree {
	private int n;
	private int[] segment_tree;
	
	//O(n)
	public SegmentTree(int[] in) {
		this.n = in.length;
		this.segment_tree = new int[2*in.length-1];
		for(int i = 0; i < in.length; ++i)
			segment_tree[i+in.length-1] = in[i];
		for(int i = in.length-2; i >= 0; --i) 
			segment_tree[i] = segment_tree[2*i+1] + segment_tree[2*i+2];
	}
	//O(log n)
	public void update(int i, int x) {
		int current = i + n - 1;
		segment_tree[current] = x;
		while(current > 0) {
			if(current%2  != 0) 
				segment_tree[(current-1)/2] = segment_tree[current] + segment_tree[current+1];
			else 
				segment_tree[(current-1)/2] = segment_tree[current] + segment_tree[current-1];
			current = (current-1)/2;
		}
	}
	//O(log n)
	public int rangeSum(int i, int j) {
		i = i + n - 1;
		j = j + n - 1;
		int sum = 0;
		while(i <= j) {
			if(i%2 == 0) {
				sum += segment_tree[i];
				if(i == 0) break;
				++i;
			}
			if(j%2 == 1) {
				sum += segment_tree[j];
				--j;
			}
			i = (i-1)/2;
			j = (j-1)/2;
		}
		return sum;
	}
	
	
	public void display() {
		for(int i = 0; i < segment_tree.length; ++i)
			System.out.print(segment_tree[i]+ " ");
		System.out.println();
	}
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(n);
		System.out.println(Arrays.toString(in));
		RangeSum3 rs = new RangeSum3(in);
		rs.display();
		System.out.println(rs.rangeSum(0, n-1));
		System.out.println(rs.rangeSum(1, n-2));
		rs.update(1, -1);
		rs.display();
		System.out.println(rs.rangeSum(0, n-1));
		System.out.println(rs.rangeSum(1, n-2));
	}
	
}
