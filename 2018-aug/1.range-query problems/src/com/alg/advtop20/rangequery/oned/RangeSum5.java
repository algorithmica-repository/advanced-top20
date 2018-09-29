package com.alg.advtop20.rangequery.oned;

import java.util.Arrays;
import java.util.Random;

class FenwickTree {
	private int[] in;
	private int[] aux;
	
	//O(n^2)
	public FenwickTree(int[] in) {
		this.in = in;
		aux = new int[in.length];
		aux[0] = in[0];
		for(int i = 1; i < aux.length; i+=2)
			aux[i] = in[i];
		for(int i = 2; i < aux.length; i+=2) {
			int sum = 0;
			int r = getRightmostOne(i);
			for(int j = i - (1<<r) + 1; j <= i; ++j)
				sum += in[j];
			aux[i] = sum;
		}
	}
	
	private int getRightmostOne(int i) {
		int pos = 0;
		while(i != 0) {
			if( (i & 1) != 0)
				return pos;
			++pos;
			i >>>= 1;
		}
		return pos;
	}
	//O(log n)
	public void update(int i, int x) {
		int old = in[i];
		in[i] = x;
		while(i < aux.length) {
			aux[i] += (x-old);
			i += (i & -i);
		}
	}
	private int cumulativeSum(int i) {
		int sum = aux[0];
		while(i > 0) {
			sum += aux[i];
			i -= (i & -i);
		}
		return sum;
	}
	//O(log n)
	public int rangeSum(int i, int j) {
		if(i == 0) return cumulativeSum(j);
		return cumulativeSum(j) - cumulativeSum(i-1);
	}
	
	public void display() {
		System.out.println(Arrays.toString(in));
		System.out.println(Arrays.toString(aux));
	}
}

public class RangeSum5 {
	
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int[] in = new int[n];
		Random r = new Random(100);
		for(int i = 0; i < n; ++i)
			in[i] = r.nextInt(n);
		FenwickTree tree = new FenwickTree(in);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
		tree.update(1, -1);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
		tree.update(2, 3);
		tree.display();
		System.out.println(tree.rangeSum(0, n-1));
		System.out.println(tree.rangeSum(1, n-2));
	}
}
