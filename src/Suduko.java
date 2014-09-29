//
public class Suduko {
	private int[][] suduko;
	
	public Suduko (int[][] suduko) {
		this.suduko = suduko;
	}
	
	public boolean recursionSearch () {
		int rowIndex = startPoint().rowIndex;
		int columnIndex = startPoint().columnIndex;
		
		if (rowIndex >= 8 && columnIndex >= 8 && suduko[rowIndex][columnIndex] != 0) {
			return true;
		}
		
		for (int value = 1; value < 10; value++) {
			if (checkAll(rowIndex, columnIndex, value)) {
				suduko[rowIndex][columnIndex] = value;
				if (!recursionSearch()) {
					suduko[rowIndex][columnIndex] = 0;
				}
				else {
					return true;
				}
			}
		}
		
		return false;
	}

//	public void print () {
//		for (int i = 0; i < 9; i++) {
//			for (int j = 0; j < 9; j++) {
//				System.out.print(suduko[i][j] + " ");
//			}
//			System.out.println();
//		}
//	}

	private boolean checkRow (int rowIndex, int value) {
		for (int i = 0; i < 9; i++) {
			if (suduko[rowIndex][i] == value) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkColumn (int columnIndex, int value) {
		for (int i = 0; i < 9; i++) {
			if (suduko[i][columnIndex] == value) {
				return false;
			}
		}
		return true;
	}
	
	private boolean checkSquare (int rowIndex, int columnIndex, int value) {
		for (int i = 3 * (rowIndex / 3); i < 3 * (rowIndex / 3) + 3; i++) {
			for (int j = 3 * (columnIndex / 3); j < 3 * (columnIndex / 3) + 3; j++) {
				if (suduko[i][j] == value) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkAll (int rowIndex, int columnIndex, int value) {
		return checkRow(rowIndex, value) && checkColumn(columnIndex, value) && checkSquare(rowIndex, columnIndex, value);
	}
	
	private IndexPair startPoint() {
		int i = 0, j = 0;
		IndexPair indexPair = new IndexPair();
		
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (suduko[i][j] == 0) {
					indexPair.rowIndex = i;
					indexPair.columnIndex = j;
					return indexPair;
				}
			}
		}
		
		indexPair.rowIndex = i - 1;
		indexPair.columnIndex = j - 1;
		return indexPair;
	}
	
	private class IndexPair {
		public int rowIndex, columnIndex;
	}
}
