package com.alg.top20.bt;

import java.util.Arrays;

public class SudokuSolver {

	// recursion:
	public static void sudokuSolver1(int[][] in) {
		auxSolver1(0, 0, in);
	}

	private static void display(int[][] in) {
		for (int i = 0; i < 9; ++i) {
			System.out.println(Arrays.toString(in[i]));
		}
		System.out.println();
	}

	private static boolean isValid1(int[][] in) {
		//validate the 81 cell grid
		return true;
	}

	private static void auxSolver1(int r, int c, int[][] in) {
		if (r == 9) {
			if (isValid1(in))
				display(in);
			return;
		}
		if (in[r][c] != 0) {
			auxSolver1(c == 8 ? r + 1 : r, (c + 1) % 9, in);
		} else {
			for (int d = 1; d <= 9; ++d) {
				in[r][c] = d;
				auxSolver1(c == 8 ? r + 1 : r, (c + 1) % 9, in);
			}
			in[r][c] = 0;
		}
	}

	// backtracking:
	public static void sudokuSolver2(int[][] in) {
		auxSolver2(0, 0, in);
	}

	private static boolean isValid2(int r, int c, int d, int[][] in) {
		//row & column check
		for(int j = 0; j < 9; ++j) {
			if(in[r][j] == d) return false;
			if(in[j][c] == d) return false;
		}
		//box check
		int startr = r / 3 * 3;
		int startc = c / 3 * 3;
		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				if(in[startr+i][startc+j] == d) return false;
			}
		}
		return true;
	}

	private static void auxSolver2(int r, int c, int[][] in) {
		if (r == 9) {
			display(in);
			return;
		}
		if (in[r][c] != 0) {
			auxSolver2(c == 8 ? r + 1 : r, (c + 1) % 9, in);
		} else {
			for (int d = 1; d <= 9; ++d) {
				if (isValid2(r,c,d,in)) {
					in[r][c] = d;
					auxSolver2(c == 8 ? r + 1 : r, (c + 1) % 9, in);
				}
			}
			in[r][c] = 0;
		}
	}

	//todo: automate test case
	public static void main(String[] args) {
		int[][] in = 
			{ 	{1,0,0,0,5,0,0,0,9},
				{0,0,0,4,0,9,0,0,0},
				{0,0,0,3,8,6,0,0,0},
				{5,9,0,0,0,0,0,6,8},
				{4,0,0,8,9,2,0,0,7},
				{0,0,0,0,0,0,0,0,0},
				{0,0,1,7,0,5,8,0,0},
				{0,3,0,0,0,0,0,1,0},
				{9,8,5,0,4,0,7,3,2}
			};
		display(in);
		System.out.println();
		sudokuSolver2(in);
	}

}
