package sudoku;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SudokuInfo {
	//private final static int SIZE = 9;
	private static int[][] sudokuArray = new int[9][9];
	private int[] canNum;
	private int totalZero = 0;
	
	public void generateSudoku(int total) {
		int k = 0;
		if(total == totalZero) {
			output();
			return;
		}
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				if(sudokuArray[i][j] != 0) {
					continue;
				}
				
				k = isCan(i, j, total);
				if(k == -1) {
					return;
				}
				
				sudokuArray[i][j] = k;
				generateSudoku(total + 1);
				canNum[total]++;
				sudokuArray[i][j] = 0;
				if(j!=0) {
					j--;
				}
				else {
					i--;
					j=8;
				}
			}
		}
	}
	
	private int isCan(int row, int col, int location) {
		
		for(int k = canNum[location]; k <=9; k++) {
			if(checkRow(row, k) && checkCol(col, k) && checkGrid(row, col, k)) {
				for(int i = location + 1; i <totalZero; i++) {
					canNum[i] = 1;
				}
				canNum[location] = k;
				return k;
			}
		}
		return -1;
	}
	
	private boolean checkRow(int row, int k) {
		for(int j = 0; j < 9; j++) {
			if(sudokuArray[row][j] == k) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkCol(int col, int k) {
		for(int i = 0; i < 9; i++) {
			if(sudokuArray[i][col] == k)
				return false;
		}
		return true;
	}
	
	private boolean checkGrid(int row, int col, int k) {
		row = row / 3 * 3;
		col = col / 3 * 3;
		for(int i = row; i < row + 3; i++) {
			for(int j = col; j < col + 3; j++) {
				if(sudokuArray[i][j] == k)
					return false;
			}
		}
		return true;
	}
	
	private void canNumCopyOne() {
		canNum = new int[totalZero];
		for(int i = 0; i < totalZero; i++) {
			canNum[i] = 1;
		}
	}
	
	public void read(String fileName) {
		//canNum = new int[82];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			try {
				String s;
				int i = 0;
				while((s = br.readLine()) != null) {
					char[] ch = s.toCharArray();
					for(int j = 0; j < ch.length; j++) {
						sudokuArray[i][j] = ch[j] - 48;
						if(sudokuArray[i][j] == 0) {
							totalZero++;
							//canNum[totalZero - 1] = 1;
						}
					}
					i++;
				}
				canNumCopyOne();
			} finally {
				br.close();
			}
		} catch(IOException e) {
				throw new RuntimeException(e);
		}
	}
	
	public void output() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(sudokuArray[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		SudokuInfo sudoku = new SudokuInfo();	
		sudoku.read("input.txt");
		sudoku.generateSudoku(0);
		//sudoku.output();
	}
}
