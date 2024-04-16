/*
 * Board.java
 * Ankur
 * 4/15/2024
 * 
 * The class handles basic game board functions such as initialization, cell 
 * type management, display, and move validation based on cell types.
 */

public class Board {

	//The class represents the board of the game


	protected LVCell[][] board;
	private int rowNum;
	private int colNum;

	public Board(LVCell[][] board, int rowNum, int colNum) {
		this.board = board;
		this.rowNum = rowNum;
		this.colNum = colNum;
	}

	public Board(int rowNum, int colNum) {
		this(new LVCell[rowNum][colNum], rowNum,colNum);
	}

	public Board(int rowNum) {
		this(rowNum,rowNum);
	}

	public Board(){
		this(8);
	}

	public int getRowNum() {
		return this.rowNum;
	}
	
	public int getColNum() {
		return this.colNum;
	}
	
	public void setCelltype(int row, int col, char cell) {
		LVCell temp = board[row][col];
		temp.setTileType(cell);
	}
	
	public char getCelltype(int row, int col) {
		LVCell temp = board[row][col];
		return temp.getTileType();
	}

	public void printBoard() {
		System.out.print("+");
		for(int k = 0; k < colNum; k++) {
			System.out.print("--+");
		}
		for(int i = 0; i<rowNum; i++) {
			System.out.print("\n|");
			for(int j = 0; j<colNum; j++) {
				char t = board[i][j].getTileType();
				System.out.print(t +" |");
			}
			System.out.print("\n+");
			for(int p = 0; p<colNum;p++) {
				System.out.print("--+");
			}
		}
		System.out.println("\n");
		System.out.println("X: Location	M:Market	&:NonAccessable");
		System.out.println();
	}
	
	public int checkCellAccess(int row, int col, char cell) {
		if(row>=rowNum || col>=colNum || row<0 || col<0) {
			System.out.println("Invalid move!");
			return 1;
		}
		if(board[row][col].getTileType() == '&') {
			System.out.println("Inaccessible Cell!");
			return 2;
		}
		if(board[row][col].getTileType() == 'M') {
			return 3;
		}
		setCelltype(row, col, cell);
		return 0;
	}
	

}
