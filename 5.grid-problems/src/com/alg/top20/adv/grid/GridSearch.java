package com.alg.top20.adv.grid;

import java.util.HashMap;


public class GridSearch {

	//TC:O(n ^ 3)   SC:O(n ^ 2) ~ n ^ 2 + n
	public static boolean hasWord(char[][] grid, String word) {
		boolean[][] visit = new boolean[grid.length][grid.length];
		for(int i = 0; i < grid.length; ++i) {
			for(int j = 0; j < grid.length; ++j) {
				if(auxHasWord(i, j, grid, word, visit) == true)
					return true;
			}
		}
		return false;
	}
	private static boolean auxHasWord(int i, int j, char[][] grid, String word, boolean[][] visit) {
		if(word.length() == 0) return true;
		if(visit[i][j]== true) return false;		
		if(grid[i][j] != word.charAt(0)) return false;
		visit[i][j] = true;
		 word = word.substring(1);
		 boolean res = auxHasWord(i+1, j, grid, word, visit) ||
		 auxHasWord(i-1, j, grid, word, visit) ||
		 auxHasWord(i, j-1, grid, word, visit) ||
		 auxHasWord(i, j+1, grid, word, visit);
		 visit[i][j] = false;
		 return res;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
