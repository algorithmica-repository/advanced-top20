package com.alg.advtop20.graphs.st;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class DisjointSet {
	private int[] parent;
	private int[] rank;
	int nsets;
	
	public DisjointSet(int n) {
		parent = new int[n];
		rank = new int[n];
		for(int i = 0; i < n; ++i) {
			parent[i] = i;
			rank[i] = 1;
		}
		nsets = n;
	}
	
	public int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	
	public void union(int x, int y)  {
		int rx = find(x);
		int ry = find(y);
		if(rx != ry) {
			--nsets;
			if(rank[rx] > rank[ry])
				parent[ry] = rx;
			else if(rank[ry] > rank[rx])
				parent[rx] = ry;
			else {
				parent[ry] = rx;
				++rank[rx]; 
			}				
		}
	}
	
	public int getNumSets() {
		return nsets;
	}
	
	public void display() {
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(rank));
	}
	
}
class Edge {
	int start;
	int end;
	int weight;
	public Edge(int start, int end, int weight) {
		super();
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	
}

class WeightComparator implements Comparator<Edge> {

	@Override
	public int compare(Edge e1, Edge e2) {
		return e1.weight - e2.weight;
	}
	
}
public class MinimumRentalConnections {

	public static int findMinRentalCost(int[][] in) {
		List<Edge> edges = new ArrayList<Edge>();
		//E
		for(int i = 0; i < in.length; ++i) {
			for(int j = i+1; j < in.length; ++j) {
				if(in[i][j] != 0)
					edges.add(new Edge(i, j, in[i][j]));
			}
		}
		//E log E(merge/quick) or E(for radix sort)
		Collections.sort(edges, new WeightComparator());
		DisjointSet dset = new DisjointSet(in.length);
		int totcost = 0, count = 0;
		//E
		for(Edge e: edges) {
			if(dset.find(e.start) != dset.find(e.end)){
				dset.union(e.start, e.end);
				totcost += e.weight;
				++count;
				if(count == in.length-1) break;
			}
		}
		return totcost;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
